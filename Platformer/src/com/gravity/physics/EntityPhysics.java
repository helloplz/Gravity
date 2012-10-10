package com.gravity.physics;


import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;


/**
 * A Entity delegate class for handling common computations during either collisions or time ticks
 * 
 * Subclasses here should handle such things as friction, gravity, etc. for Entities.
 * 
 * @author pgruevski
 */
public class EntityPhysics {
    
    private EntityPhysics() {
        // never instantiated
    }
    
    public static Vector2f collisionPoint(Shape a, Shape b) {
        float arad, brad, sum;
        arad = a.getBoundingCircleRadius();
        brad = b.getBoundingCircleRadius();
        sum = arad + brad;
        float x, y;
        x = (a.getCenterX() * arad + b.getCenterY() * brad) / sum;
        y = (a.getCenterY() * arad + b.getCenterY() * brad) / sum;
        return new Vector2f(x, y);
    }
    
    public static Vector2f postCollisionVelocity(Shape collider, Shape obstacle, Vector2f velocity, Vector2f collisionPoint) {
        Vector2f point = collisionPoint.copy();
        point.x -= obstacle.getCenterX();
        point.y -= obstacle.getCenterY();
        Vector2f normal = point.getNormal().normalise();
        if (normal.dot(velocity) < 0) {
            normal.scale(-1);
        }
        return normal.scale(velocity.length());
    }
}
