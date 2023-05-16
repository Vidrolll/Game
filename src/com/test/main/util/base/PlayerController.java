package com.test.main.util.base;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import com.test.main.entity.Collision;
import com.test.main.util.EnvironmentVariables;

public class PlayerController implements Controller {
	private boolean[] keyDown = new boolean[3];
	private Collision collision;
	private Entity plr;
	
	private int xSpeed;
	private int jumpPower;
	private int wallSlideSpeed;
	private int acceleration;
	private int deceleration;
	private boolean running;
	
	private double camVelX;
	private double camVelY;
	private double camSpeed = 15.0;
	
	public PlayerController(Entity plr, int xSpeed, int jumpPower, int wallSlideSpeed, int acceleration, int deceleration) {
		this.plr = plr;
		collision = new Collision(plr,plr.eh);
		this.xSpeed = xSpeed;
		this.jumpPower = jumpPower;
		this.wallSlideSpeed = wallSlideSpeed;
		this.acceleration = acceleration;
		this.deceleration = deceleration;
		camVelX = 0;
		camVelY = 0;
		running = false;
	}
	
	public PlayerController(Entity plr) {
		this(plr,10,20,5,1,1);
	}
	
	public void update() {
		if(keyDown[2]&&collision.groundCheck()) {
			plr.velY = -jumpPower;
			if(!keyDown[0]&&!keyDown[1]) {
				plr.velX+=-Math.signum(plr.velX)*(Math.abs(plr.velX)/2);
				if(Math.abs(plr.velX)/2==0)plr.velX=0;
			}
			if(keyDown[0]) plr.velX = -Math.abs(plr.velX);
			if(keyDown[1]) plr.velX = Math.abs(plr.velX);
		} else if(keyDown[2]&&collision.collisionX()) {
			plr.velY = -jumpPower;
			plr.velX *= -2;
		}
		if(keyDown[0]&&plr.velX>-(xSpeed/2)&&(collision.groundCheck()||collision.checkX())) plr.velX+=-acceleration;
		if(keyDown[1]&&plr.velX<(xSpeed/2)&&(collision.groundCheck()||collision.checkX())) plr.velX+=acceleration;
		if(keyDown[0]&&(plr.velX>-(xSpeed/5)&&plr.velX<=0)&&(!collision.groundCheck())) plr.velX+=-acceleration;
		if(keyDown[1]&&(plr.velX<(xSpeed/5)&&plr.velX>=0)&&(!collision.groundCheck())) plr.velX+=acceleration;
		if((!keyDown[0]&&!keyDown[1])&&collision.groundCheck()||(collision.groundCheck()&&(plr.velX>5||plr.velX<-5))) 
			plr.velX+=deceleration*Math.signum(plr.velX*-1);
		if(keyDown[0]&&plr.velX>-xSpeed&&running&&(collision.groundCheck()||collision.checkX())) plr.velX+=-acceleration*2;
		if(keyDown[1]&&plr.velX<xSpeed&&running&&(collision.groundCheck()||collision.checkX())) plr.velX+=acceleration*2 ;
		if(!keyDown[0]&&!keyDown[1]&&collision.checkX()) plr.velX=0;
		if(collision.checkX()&&plr.velY>wallSlideSpeed&&plr.velX!=0) plr.velY-=EnvironmentVariables.GRAVITY*2;
		if(plr.velX>xSpeed)plr.velX=xSpeed;
		if(plr.velX<-xSpeed)plr.velX=-xSpeed;
		plr.x += plr.velX;
		plr.y += plr.velY;
		collision();
		camCheck();
	}
	
	public void collision() {
		collision.collisionY();
		if (!collision.groundCollide() && plr.velY < EnvironmentVariables.TERMINAL_VELOCITY)
			plr.velY+=EnvironmentVariables.GRAVITY;
		collision.collisionX();
	}
	
	public void camCheck() {
		double xOffset = EnvironmentVariables.CAM.getXOffset();
		double yOffset = EnvironmentVariables.CAM.getYOffset();
		double xScale = EnvironmentVariables.CAM.getXScale();
		double yScale = EnvironmentVariables.CAM.getYScale();
		double xPos = 960-((plr.getX())+plr.getBounds().width/2);
		double yPos = 540-((plr.getY())+plr.getBounds().height/2);
		if(Math.abs((xOffset-xPos)*xScale)>0) camVelX = -((xOffset-xPos)*xScale)/camSpeed;
		if(Math.abs((yOffset-yPos)*yScale)>0) camVelY = -((yOffset-yPos)*yScale)/camSpeed;
		EnvironmentVariables.CAM.addXOffset(camVelX);
		EnvironmentVariables.CAM.addYOffset(camVelY);
		EnvironmentVariables.CAM.setXZoom((1920/2)-EnvironmentVariables.CAM.getXOffset());
		EnvironmentVariables.CAM.setYZoom((1080/2)-EnvironmentVariables.CAM.getYOffset());
	}
	
	@Override
	public void input(KeyEvent e) {
		if(e.getID()==KeyEvent.KEY_PRESSED) {
			if(e.getKeyCode()==KeyEvent.VK_A) keyDown[0] = true;
			if(e.getKeyCode()==KeyEvent.VK_D) keyDown[1] = true;
			if(e.getKeyCode()==KeyEvent.VK_SPACE) keyDown[2] = true;
			if(e.getKeyCode()==KeyEvent.VK_SHIFT) running = true;
		}
		if(e.getID()==KeyEvent.KEY_RELEASED) {
			if(e.getKeyCode()==KeyEvent.VK_A) keyDown[0] = false;
			if(e.getKeyCode()==KeyEvent.VK_D) keyDown[1] = false;
			if(e.getKeyCode()==KeyEvent.VK_SPACE) keyDown[2] = false;
			if(e.getKeyCode()==KeyEvent.VK_SHIFT) running = false;
		}
	}
	@Override
	public void input(MouseEvent e, int mouseX, int mouseY) {}
	@Override
	public void input(MouseWheelEvent e) {}
}
