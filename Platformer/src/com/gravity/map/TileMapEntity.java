package com.gravity.map;

import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import com.gravity.physics.Collision;
import com.gravity.physics.Entity;

/**
 * Represents a convex shape in map terrain for collision detection.
 * 
 * @author xiao
 */
public class TileMapEntity implements Entity {

	private Shape position;
	private TileMap map;

	public TileMapEntity(Shape shape, TileMap map) {
		this.position = shape;
		this.map = map;
	}

	@Override
	public Shape getPosition(float ticks) {
		return position;
	}

	@Override
	public Vector2f getVelocity(float ticks) {
		return new Vector2f(0, 0);
	}

	@Override
	public Shape handleCollisions(float ticks, Collision... collisions) {
		return position;
	}

	@Override
	public Shape rehandleCollisions(float ticks, Collision... collisions) {
		return position;
	}

	@Override
	public void tick(float ticks) {
		// Map Entity does not change over time
	}

}
