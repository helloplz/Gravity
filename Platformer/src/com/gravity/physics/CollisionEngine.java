package com.gravity.physics;

import java.util.List;

import com.google.common.collect.Lists;
import com.gravity.map.GameWorld;

public class CollisionEngine {

	private GameWorld gameMap;
	private List<Entity> entities;
	private List<Entity> terrain;

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

	public void update(float ticks) {
		// TODO: for each entity, get their desired movement. Then alert
		// entities of any collisions with each other. Re-adjust collisions
		// until everything works out. Then update everybody
	}
}
