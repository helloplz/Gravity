package com.gravity.physics;

import java.util.Set;

import org.newdawn.slick.geom.Vector2f;

/**
 * Represents a collision at a certain point in time between two entities
 * 
 * @author xiao
 */
public class Collision {
    
    public final Entity entityA, entityB;
    public final int    time;
    public final Set<Integer> collisionsA, collisionsB;
    public final Vector2f     position;
    
    public Collision(Entity entityA, Entity entityB, int time, Vector2f position) {
        this.entityA = entityA;
        this.entityB = entityB;
        this.time = time;
        this.position = position;
        collisionsA = null;
        collisionsB = null;
    }
    
    public Collision(Entity entityA, Entity entityB, int time, Set<Integer> collisionA, Set<Integer> collisionB) {
        this.entityA = entityA;
        this.entityB = entityB;
        this.time = time;
        this.position = null;
        this.collisionsA = collisionA;
        this.collisionsB = collisionB;
    }
    
    /** Get the other entity in the collision */
    public Entity getOtherEntity(Entity me) {
        if (me == entityA) {
            return entityB;
        } else {
            return entityA;
        }
    }
    
    public Set<Integer> getMyCollisions(Entity me) {
        if (me == entityA) {
            return collisionsA;
        } else {
            return collisionsB;
        }
    }
    
    public Set<Integer> getOtherCollisions(Entity me) {
        if (me == entityA) {
            return collisionsB;
        } else {
            return collisionsA;
        }
    }
    
    @Override
    public String toString() {
        return "Collision [entityA=" + entityA + ", entityB=" + entityB + ", time=" + time + ", collisionsA=" + collisionsA + ", collisionsB="
                + collisionsB + "]";
    }
    
}
