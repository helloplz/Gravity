package com.gravity.physics;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

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
    
    public static boolean isIntersecting(float[] p1, float[] p2, float[] p3, float[] p4) {
        double unknownA, unknownB;
        /*
         * Intersection formula used: (x4 - x3)(y1 - y3) - (y4 - y3)(x1 - x3) UA = --------------------------------------- (y4 - y3)(x2 - x1) - (x4 -
         * x3)(y2 - y1)
         * 
         * (x2 - x1)(y1 - y3) - (y2 - y1)(x1 - x3) UB = --------------------------------------- (y4 - y3)(x2 - x1) - (x4 - x3)(y2 - y1)
         * 
         * if UA and UB are both between 0 and 1 then the lines intersect.
         * 
         * Source: http://local.wasp.uwa.edu.au/~pbourke/geometry/lineline2d/
         */
        unknownA = (((p4[0] - p3[0]) * (double) (p1[1] - p3[1])) - ((p4[1] - p3[1]) * (p1[0] - p3[0])))
                / (((p4[1] - p3[1]) * (p2[0] - p1[0])) - ((p4[0] - p3[0]) * (p2[1] - p1[1])));
        unknownB = (((p2[0] - p1[0]) * (double) (p1[1] - p3[1])) - ((p2[1] - p1[1]) * (p1[0] - p3[0])))
                / (((p4[1] - p3[1]) * (p2[0] - p1[0])) - ((p4[0] - p3[0]) * (p2[1] - p1[1])));
        
        if (unknownA >= 0 && unknownA <= 1 && unknownB >= 0 && unknownB <= 1) {
            return true;
        }
        return false;
    }
    
    public static Collision collisionLines(Entity entityA, Entity entityB, int time) {
        // @formatter:off
        /*
         * Intersection formula used:
         *      (x4 - x3)(y1 - y3) - (y4 - y3)(x1 - x3)
         * UA = ---------------------------------------
         *      (y4 - y3)(x2 - x1) - (x4 - x3)(y2 - y1)
         *      
         *      (x2 - x1)(y1 - y3) - (y2 - y1)(x1 - x3)
         * UB = ---------------------------------------
         *      (y4 - y3)(x2 - x1) - (x4 - x3)(y2 - y1)
         *      
         * if UA and UB are both between 0 and 1 then the lines intersect.
         * 
         * Source: http://local.wasp.uwa.edu.au/~pbourke/geometry/lineline2d/
         */
        // @formatter:on
        
        Shape a = entityA.getShape(time);
        Shape b = entityB.getShape(time);
        Map<Integer, List<Integer>> aCollisions = Maps.newHashMap();
        Map<Integer, List<Integer>> bCollisions = Maps.newHashMap();
        float points[] = a.getPoints(); // (x3, y3) and (x4, y4)
        float thatPoints[] = b.getPoints(); // (x1, y1) and (x2, y2)
        int length = points.length;
        int thatLength = thatPoints.length;
        double unknownA;
        double unknownB;
        
        if (!a.closed()) {
            length -= 2;
        }
        if (!b.closed()) {
            thatLength -= 2;
        }
        
        // x1 = thatPoints[j]
        // x2 = thatPoints[j + 2]
        // y1 = thatPoints[j + 1]
        // y2 = thatPoints[j + 3]
        // x3 = points[i]
        // x4 = points[i + 2]
        // y3 = points[i + 1]
        // y4 = points[i + 3]
        for (int i = 0; i < length; i += 2) {
            int iNext = i + 2;
            if (iNext >= points.length) {
                iNext = 0;
            }
            
            for (int j = 0; j < thatLength; j += 2) {
                int jNext = j + 2;
                if (jNext >= thatPoints.length) {
                    jNext = 0;
                }
                
                unknownA = (((points[iNext] - points[i]) * (double) (thatPoints[j + 1] - points[i + 1])) - ((points[iNext + 1] - points[i + 1]) * (thatPoints[j] - points[i])))
                        / (((points[iNext + 1] - points[i + 1]) * (thatPoints[jNext] - thatPoints[j])) - ((points[iNext] - points[i]) * (thatPoints[jNext + 1] - thatPoints[j + 1])));
                unknownB = (((thatPoints[jNext] - thatPoints[j]) * (double) (thatPoints[j + 1] - points[i + 1])) - ((thatPoints[jNext + 1] - thatPoints[j + 1]) * (thatPoints[j] - points[i])))
                        / (((points[iNext + 1] - points[i + 1]) * (thatPoints[jNext] - thatPoints[j])) - ((points[iNext] - points[i]) * (thatPoints[jNext + 1] - thatPoints[j + 1])));
                
                if (unknownA >= 0 && unknownA <= 1 && unknownB >= 0 && unknownB <= 1) {
                    if (aCollisions.containsKey(i / 2)) {
                        aCollisions.get(i / 2).add(j / 2);
                    } else {
                        aCollisions.put(i / 2, Lists.newArrayList(j / 2));
                    }
                    if (bCollisions.containsKey(j / 2)) {
                        bCollisions.get(j / 2).add(i / 2);
                    } else {
                        bCollisions.put(j / 2, Lists.newArrayList(i / 2));
                    }
                }
            }
        }
        
        Set<Integer> aPoints = Sets.newHashSet();
        Set<Integer> bPoints = Sets.newHashSet();
        getIntersectPoints(a, b, aCollisions, aPoints, bPoints);
        getIntersectPoints(b, a, bCollisions, bPoints, aPoints);
        
        // if true, then bPoints better be empty as well.
        if (!aPoints.isEmpty()) {
            return new Collision(entityA, entityB, time, aPoints, bPoints);
        }
        
        return null;
    }
    
    private static void getIntersectPoints(Shape a, Shape b, Map<Integer, List<Integer>> aCollisions, Set<Integer> aPoints, Set<Integer> bPoints) {
        int aLength = a.getPointCount();
        int bLength = b.getPointCount();
        float[] centerA = a.getCenter();
        float[] centerB = b.getCenter();
        Vector2f centerV = new Vector2f(centerB[0] - centerA[0], centerB[1] - centerA[1]);
        for (int aLine : aCollisions.keySet()) {
            List<Integer> bLines = aCollisions.get(aLine);
            for (int bLine : bLines) {
                List<Integer> aAdj;
                if (bLines.contains((bLine + 1) % bLength)) {
                    aPoints.add(aLine);
                    aPoints.add((aLine + 1) % aLength);
                    bPoints.add((bLine + 1) % bLength);
                } else if (bLines.contains((bLine + 2) % bLength)) {
                    if (isIntersecting(centerB, b.getPoint((bLine + 1) % bLength), a.getPoint(aLine), a.getPoint((aLine + 1) % aLength))) {
                        aPoints.add(aLine);
                        aPoints.add((aLine + 1) % aLength);
                        bPoints.add((bLine + 1) % bLength);
                        bPoints.add((bLine + 2) % bLength);
                    }
                } else if ((aAdj = aCollisions.get((aLine + 1) % aLength)) != null) {
                    if (aAdj.contains((bLine + 1) % bLength)) {
                        aPoints.add((aLine + 1) % aLength);
                        bPoints.add((bLine + 1) % bLength);
                    } else if (aAdj.contains((bLine + bLength - 1) % bLength)) {
                        aPoints.add((aLine + 1) % aLength);
                        bPoints.add(bLine);
                    }
                }
            }
        }
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
