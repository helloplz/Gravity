package com.gravity.physics;

import java.util.Set;

/**
 * Represents a collision at a certain point in time between two entities
 * 
 * @author xiao
 */
public class Collision {
    
    public final Entity entityA, entityB;
    public final int    time;
    public final Set<Integer> collisionsA, collisionsB;
    
    public Collision(Entity entityA, Entity entityB, int time, Set<Integer> collisionA, Set<Integer> collisionB) {
        this.entityA = entityA;
        this.entityB = entityB;
        this.time = time;
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
