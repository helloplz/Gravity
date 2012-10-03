package com.gravity.map;

import java.util.List;

import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.tiled.TiledMap;

public class TileMap implements GameMap {

	Tile[][] terrain;

	int height;
	int width;

	public TileMap(TiledMap map) {
		// TODO: using tiledmap, populate terrain and entities
	}

	@Override
	public List<Shape> getCollisions(Shape shape) {
		return null;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getWidth() {
		return width;
	}
}
