package com.gravity.physics;

import java.util.List;

import com.google.common.collect.Lists;
import com.gravity.map.GameMap;

public class PhysicsEngine {

	private GameMap gameMap;
	private List<Entity> entities;

	public PhysicsEngine(GameMap map) {
		gameMap = map;
		entities = Lists.newArrayList();
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
