package com.test.main.util;

import java.awt.Graphics2D;

import com.test.main.Main;

public class Camera {
	Main main;
	
	private double xOffset,yOffset;
	private double xScale, yScale;
	private double xZoom, yZoom;
	
	private double minXOffset,maxXOffset;
	private double minYOffset,maxYOffset;
	
	public Camera(Main main) {
		this.main = main;
		xOffset = 0;
		yOffset = 0;
		xScale = 1;
		yScale = 1;
//		minXOffset = 0;
//		maxXOffset = Integer.MAX_VALUE;
//		minYOffset = 0;
//		maxYOffset = 0;
		minXOffset = 0;
		maxXOffset = 1920*3;
		minYOffset = 0;
		maxYOffset = 1080*3;
//		minXOffset = Integer.MIN_VALUE;
//		maxXOffset = Integer.MAX_VALUE;
//		minYOffset = Integer.MIN_VALUE;
//		maxYOffset = Integer.MAX_VALUE;
		
		xScale = .5;
		yScale = .5;
	}
	
	public void render(Graphics2D g) {
		fixVariables();
		g.translate(xOffset, yOffset);
		g.translate(xZoom, yZoom);
		g.scale(xScale, yScale);
		g.translate(-xZoom, -yZoom);
	}
	
	public void fixVariables() {
		int distX = (int)(960-(960/xScale));
		if(this.xOffset>-(minXOffset-distX)) this.xOffset = -(minXOffset-distX);
		if(this.xOffset<-((maxXOffset-1920)+distX)) this.xOffset = -((maxXOffset-1920)+distX);
		int distY = (int)(540-(540/yScale));
		if(this.yOffset>-(minYOffset-distY)) this.yOffset = -(minYOffset-distY);
		if(this.yOffset<-((maxYOffset-1080)+distY)) this.yOffset = -((maxYOffset-1080)+distY);
		if(1920/xScale>(maxXOffset-minXOffset)) xScale = 1920/(maxXOffset-minXOffset);
		if(1080/yScale>(maxYOffset-minYOffset)) yScale = 1080/(maxYOffset-minYOffset);
		if(xScale!=yScale) {
			if(xScale>yScale)yScale=xScale;
			else xScale=yScale;
		}
	}
	
	public void addXOffset(double xOffset) {
		this.xOffset+=xOffset;
		int distX = (int)(960-(960/xScale));
		if(this.xOffset>-(minXOffset-distX)) this.xOffset = -(minXOffset-distX);
		if(this.xOffset<-((maxXOffset-1920)+distX)) this.xOffset = -((maxXOffset-1920)+distX);
	}
	public void setXOffset(double xOffset) {
		this.xOffset=xOffset;
		int distX = (int)(maxXOffset-((maxXOffset/2)+(960/xScale)));
		if(this.xOffset>-(minXOffset-distX)) this.xOffset = -(minXOffset-distX);
		if(this.xOffset<-((maxXOffset-1920)+distX)) this.xOffset = -((maxXOffset-1920)+distX);
	}
	public double getXOffset() {
		return xOffset;
	}
	public void addYOffset(double yOffset) {
		this.yOffset+=yOffset;
		int distY = (int)(540-(540/yScale));
		if(this.yOffset>-(minYOffset-distY)) this.yOffset = -(minYOffset-distY);
		if(this.yOffset<-((maxYOffset-1080)+distY)) this.yOffset = -((maxYOffset-1080)+distY);
	}
	public void setYOffset(double yOffset) {
		this.yOffset=yOffset;
		int distY = (int)(540-(540/yScale));
		if(this.yOffset>-(minYOffset-distY)) this.yOffset = -(minYOffset-distY);
		if(this.yOffset<-((maxYOffset-1080)+distY)) this.yOffset = -((maxYOffset-1080)+distY);
	}
	public double getYOffset() {
		return yOffset;
	}
	public void addXZoom(double xZoom) {
		this.xZoom+=xZoom;
	}
	public void setXZoom(double xZoom) {
		this.xZoom=xZoom;
	}
	public double getXZoom() {
		return xZoom;
	}
	public void addYZoom(double yZoom) {
		this.yZoom+=yZoom;
	}
	public void setYZoom(double yZoom) {
		this.yZoom=yZoom;
	}
	public double getYZoom() {
		return yZoom;
	}
	public void addXScale(double xScale) {
		this.xScale+=xScale;
		if(1920/xScale>maxXOffset) xScale = 1920/maxXOffset;
	}
	public void setXScale(double xScale) {
		this.xScale=xScale;
		if(1920/xScale>maxXOffset) xScale = 1920/maxXOffset;
	}
	public double getXScale() {
		return xScale;
	}
	public void addYScale(double yScale) {
		this.yScale+=yScale;
		if(1080/yScale>maxYOffset) yScale = 1080/maxYOffset;
	}
	public void setYScale(double yScale) {
		this.yScale=yScale;
		if(1080/yScale>maxYOffset) yScale = 1080/maxYOffset;
	}
	public double getYScale() {
		return yScale;
	}
	public void setMinX(double minXOffset) {
		this.minXOffset=minXOffset;
	}
	public void setMaxX(double maxXOffset) {
		this.maxXOffset=maxXOffset;
	}
	public void setMinY(double minYOffset) {
		this.minYOffset=minYOffset;
	}
	public void setMaxY(double maxYOffset) {
		this.maxYOffset=maxYOffset;
	}
}
