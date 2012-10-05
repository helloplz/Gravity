package com.gravity.physics;

import org.newdawn.slick.geom.Vector2f;

/**
 * Represents a collision at a certain point in time between two entities
 * 
 * @author xiao
 */
public class Collision {
    
    final Entity   entityA, entityB;
    final int      time;
    final Vector2f position;
    
    public Collision(Entity entityA, Entity entityB, int time, Vector2f position) {
        this.entityA = entityA;
        this.entityB = entityB;
        this.time = time;
        this.position = position;
    }
    
    /** Get the other entity in the collision */
    public Entity getOtherEntity(Entity me) {
        if (me == entityA) {
            return entityB;
        } else {
            return entityA;
        }
    }
    
}
