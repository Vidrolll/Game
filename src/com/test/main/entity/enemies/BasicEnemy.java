package com.test.main.entity.enemies;

import com.test.main.entity.Collision;
import com.test.main.util.base.Entity;
import com.test.main.util.enums.EntityID;
import com.test.main.util.handlers.EntityHandler;

import java.awt.*;

public class BasicEnemy extends Entity {
    private Collision collision;
    public BasicEnemy(EntityHandler eh, int x, int y) {
        super(eh, x, y);
        id = EntityID.BASIC_ENEMY;
        collision = new Collision(this,eh);
    }

    @Override
    public void update() {

    }
    @Override
    public void render(Graphics2D g) {

    }
    @Override
    public void collision() {

    }
    @Override
    public Rectangle getBounds() {
        return null;
    }
}