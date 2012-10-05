package com.gravity.physics;

import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

/**
 * Represents an object which can move and is subject to collisions
 * 
 * @author xiao
 */
public interface Entity {

	/**
	 * Get the position of the Entity after specified time has passed
	 * 
	 * @param ticks
	 *            time since the last tick() call
	 */
	public Vector2f getPosition(float ticks);

	/**
	 * Get the position of the Entity after specified time has passed
	 * 
	 * @param ticks
	 *            time since the last tick() call
	 */
	public Shape getShape(float ticks);

	/**
	 * Get the velocity of the Entity at the specifed time
	 * 
	 * @param ticks
	 *            time since the last tick() call
	 */
	public Vector2f getVelocity(float ticks);

	/**
	 * Entity will collide with another entity - handle it.
	 * 
	 * @param collision
	 *            an object containing info about the collision
	 * @param ticks
	 *            the length of this timestep
	 * @return the new position of the object at the full time, as specified by
	 *         ticks
	 */
	public Shape handleCollisions(float ticks, Collision... collisions);

	/**
	 * Same as {@link Entity#handleCollisions(Collision, float)}, but may not
	 * change player's game state (health, etc) - useful for when
	 * handleCollision proposes a new posision which creates new collision
	 * problems.
	 */
	public Shape rehandleCollisions(float ticks, Collision... collisions);

	/** Advance the entity a certain amount in time. */
	public void tick(float ticks);
}
