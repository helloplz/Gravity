package com.gravity.player;

import java.util.List;

import org.newdawn.slick.geom.Shape;
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
    private final float           JUMP_POWER         = 5;
    private final float           MOVEMENT_INCREMENT = 1.0f / 1000f;
    private float                 MAX_HEALTH         = 10;
    private float                 MAX_VEL            = 2f / 1000f;
    private float                 VEL_DAMP           = 0.5f;
    
    // PLAYER CURRENT VALUES
    private GameWorld             map;
    
    // position and magnitude
    private Vector2f              acceleration;
    private Vector2f              position;
    private Vector2f              velocity;
    private Vector2f              facing;
    private float                 health;
    
    // GAME STATE STUFF
    private boolean               onGround           = true;
    
    public Player(GameWorld map, GravityGameController game) {
        health = MAX_HEALTH;
        velocity = new Vector2f(0, 0);
        this.map = map;
        this.game = game;
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
            return;
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
            }
            case RIGHT: {
                acceleration.x = MOVEMENT_INCREMENT;
            }
            case STOP: {
                acceleration.x = 0;
            }
        }
        
    }
    
    @Override
    public Vector2f getPosition(int ticks) {
        return position;
    }
    
    @Override
    public Shape getShape(int ticks) {
        // TODO
        return null;
    }
    
    @Override
    public Vector2f getVelocity(int ticks) {
        return velocity;
    }
    
    @Override
    public Shape handleCollisions(int ticks, List<Collision> collisions) {
        // TODO Auto-generated method stub
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
    
    /*
     * Makes sure maximum velocities are respected, player position is updated, etc
     */
    public void handleMotion(float millis) {
        // Check to see if the player is on the ground
        isOnGround();
        // Change the velocity depending on the accelerations
        velocity.add(acceleration);
        // Makes sure velocity does not exceed max velocity
        if (velocity.length() > MAX_VEL * millis)
            velocity = velocity.scale(velocity.length() * millis / MAX_VEL);
    }
    
    /*
     * Sets onGround depending on if the player is on the ground or not
     */
    public void isOnGround() {
        // TODO
    }
}
