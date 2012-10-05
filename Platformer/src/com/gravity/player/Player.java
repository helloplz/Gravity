package com.gravity.player;

import java.util.List;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

import com.gravity.gameplay.GravityGameController;
import com.gravity.map.GameWorld;
import com.gravity.physics.Collision;
import com.gravity.physics.Entity;

public class Player implements Entity {
    
    public enum Movement {
        LEFT, RIGHT, STOP
    }
    
    private GravityGameController game;
    
    // PLAYER STARTING CONSTANTS
    private final float JUMP_POWER = 5;
    private final float MOVEMENT_INCREMENT = 10000.0f / 1000f;
    private float MAX_HEALTH = 10;
    private float MAX_VEL = 200f / 1000f;
    private float VEL_DAMP = 0.5f;
    private float GRAVITY = 1.0f / 1000f;
    
    // PLAYER CURRENT VALUES
    private GameWorld map;
    
    // position and magnitude
    private Vector2f acceleration = new Vector2f(0, 0);
    public Vector2f oldPosition = new Vector2f(0, 0);
    private Vector2f newPosition = new Vector2f(0, 0);
    private Vector2f velocity = new Vector2f(0, 0);
    private Vector2f facing = new Vector2f(0, 1);
    private float health;
    private Shape myShape;
    
    // GAME STATE STUFF
    private boolean onGround = true;
    
    public Player(GameWorld map, GravityGameController game) {
        health = MAX_HEALTH;
        velocity = new Vector2f(0, 0);
        this.map = map;
        this.game = game;
        this.myShape = new Rectangle(-.5f, -.5f, 1f, 1f);
    }
    
    public void takeDamage(float damage) {
        health -= damage;
    }
    
    public void heal(float heal) {
        health += heal;
    }
    
    /**
     * @param jumping
     *            true if keydown, false if keyup
     */
    public void jump(boolean jumping) {
        if (onGround) {
            onGround = false;
            velocity.y += JUMP_POWER;
        } else {
            acceleration.y = -(GRAVITY);
        }
    }
    
    /**
     * 
     * @param direction
     */
    public void move(Movement direction) {
        switch (direction) {
            case LEFT: {
                acceleration.x = -MOVEMENT_INCREMENT;
                break;
            }
            case RIGHT: {
                acceleration.x = MOVEMENT_INCREMENT;
                break;
            }
            case STOP: {
                acceleration.x = 0;
                break;
            }
        }
    }
    
    // Get where you WILL be in "ticks" time
    @Override
    public Vector2f getPosition(int ticks) {
        return newPosition;
    }
    
    @Override
    public Shape getShape(int ticks) {
        return myShape.transform(Transform.createTranslateTransform(oldPosition.x + (velocity.x * ticks), oldPosition.y + (velocity.y * ticks)));
    }
    
    @Override
    public Vector2f getVelocity(int ticks) {
        return velocity.copy();
    }
    
    @Override
    public Shape handleCollisions(int ticks, List<Collision> collisions) {
        Collision first = null;
        int earliest = -1;
        for (Collision c : collisions) {
            if (earliest == -1) {
                earliest = c.time;
                first = c;
            } else if (c.time < earliest) {
                earliest = c.time;
                first = c;
            } else {
                continue;
            }
        }
        if (earliest == -1) {
            System.out.println("WTF???");
            return this.getShape(0);
        }
        Entity me = this;
        Entity them = first.getOtherEntity(this);
        
        if ((them.getShape(earliest) instanceof Rectangle)) {
            newPosition = oldPosition.add(velocity.scale((float) (earliest - (1.0 / 1000))));
            // If I'm overlapping their xcoord
            if (me.getShape(earliest + 1).getMaxX() > them.getShape(earliest + 1).getMinX()) {
                velocity.x = 0;
            } else if (me.getShape(earliest + 1).getMinX() < them.getShape(earliest + 1).getMaxX()) {
                velocity.x = 0;
            }
            // If I'm overlapping their ycoord
            else if (me.getShape(earliest + 1).getMinY() < them.getShape(earliest + 1).getMaxY()) {
                velocity.y = 0;
            } else if (me.getShape(earliest + 1).getMinY() < them.getShape(earliest + 1).getMaxY()) {
                velocity.y = 0;
            }
        }
        // TODO: Write code for non-rectangles
        return null;
    }
    
    @Override
    public Shape rehandleCollisions(int ticks, List<Collision> collisions) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public void tick(int millis) {
        handleMotion(millis);
    }
    
    /**
     * Makes sure maximum velocities are respected, player position is updated, etc
     */
    private void handleMotion(int millis) {
        oldPosition = newPosition;
        // Check to see if the player is on the ground
        isOnGround();
        // Change the velocity depending on the accelerations
        velocity.add(acceleration.copy().scale(millis));
        // Makes sure velocity does not exceed max velocity
        if (velocity.length() > MAX_VEL * millis)
            velocity.scale(MAX_VEL * millis / velocity.length());
        // Updates position to reflect velocity
        newPosition = oldPosition.add(velocity);
        System.out.println("oldPosition = " + oldPosition.x);
    }
    
    /**
     * Sets onGround depending on if the player is on the ground or not
     */
    private void isOnGround() {
        // TODO
    }
}
