package com.gravity.map;

import java.util.List;
import java.util.Map;

import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

import com.gravity.player.Entity;

public class TileMap {

	Tile[][] terrain;
	List<Entity> entities;
	Map<Entity, Vector2f> positions;

	int height;
	int width;

	public TileMap(TiledMap map) {
		// TODO: using tiledmap, populate terrain and entities
	}

	public void update() {
		// TODO: for each entity, get the desired velocity, and calculate
		// an actual velocity based on the entity's location, terrain, and
		// gravity. Then calculate each entity's new position
		for (Entity entity : entities) {
			Vector2f desiredV = entity.update();

		}
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
}
