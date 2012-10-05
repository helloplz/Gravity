package com.gravity.physics;

import java.util.List;

import org.newdawn.slick.geom.Shape;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.gravity.map.GameWorld;

public class CollisionEngine {
    
    private final GameWorld    gameMap;
    private final List<Entity> entities;
    private final List<Entity> terrain;
    
    public CollisionEngine(GameWorld map) {
        gameMap = map;
        entities = Lists.newArrayList();
        terrain = map.getTerrainEntities();
    }
    
    public boolean addEntity(Entity entity) {
        return entities.add(entity);
    }
    
    public boolean removeEntity(Entity entity) {
        return entities.remove(entity);
    }
    
    private static final int PARTS_PER_TICK = 5;
    
    public void update(int ticks) {
        // for each entity, get their desired movement. Then alert
        // entities of any collisions with each other. Re-adjust collisions
        // until everything works out. Then update everybody
        Preconditions.checkArgument(ticks > 0, "Number of ticks must be positive: ticks=" + ticks);
        
        int increment = ticks < PARTS_PER_TICK ? 1 : ticks / PARTS_PER_TICK;
        int entitiesSize = entities.size();
        int terrainSize = terrain.size();
        int i, j;
        for (int time = increment; time <= ticks; time += increment) {
            // terrain does not change, not calling update on it
            for (i = 0; i < entitiesSize; i++) {
                Shape a = entities.get(i).getShape(increment);
                List<Collision> collisions = Lists.newArrayList();
                Shape b;
                for (j = 0; j < terrainSize; j++) {
                    b = terrain.get(j).getShape(increment);
                    if (a.intersects(b)) {
                        collisions.add(new Collision(entities.get(i), terrain.get(j), increment, EntityPhysics.collisionPoint(a, b)));
                    }
                }
                
                for (j = 0; j < entitiesSize; j++) {
                    if (i != j) {
                        b = entities.get(j).getShape(increment);
                        if (a.intersects(b)) {
                            collisions.add(new Collision(entities.get(i), entities.get(j), increment, EntityPhysics.collisionPoint(a, b)));
                        }
                    }
                }
                if (!collisions.isEmpty()) {
                    a = entities.get(i).handleCollisions(increment, collisions);
                    
                    while (!collisions.isEmpty()) {
                        collisions.clear();
                        for (j = 0; j < terrainSize; j++) {
                            b = terrain.get(j).getShape(increment);
                            if (a.intersects(b)) {
                                collisions.add(new Collision(entities.get(i), terrain.get(j), increment, EntityPhysics.collisionPoint(a, b)));
                            }
                        }
                        
                        for (j = 0; j < entitiesSize; j++) {
                            if (i != j) {
                                b = entities.get(j).getShape(increment);
                                if (a.intersects(b)) {
                                    collisions.add(new Collision(entities.get(i), entities.get(j), increment, EntityPhysics.collisionPoint(a, b)));
                                }
                            }
                        }
                        if (!collisions.isEmpty()) {
                            a = entities.get(i).rehandleCollisions(increment, collisions);
                        }
                    }
                }
            }
            for (Entity e : entities) {
                e.tick(increment);
            }
        }
        
    }
}
