package com.gravity.player;

import org.newdawn.slick.geom.Vector2f;

/**
 * Represents an object which can move and be rendered on the screen.
 * 
 * @author xiao
 */
public interface Entity {

	/**
	 * Alert the entity that a timestep has passed, and get the entity's desired
	 * velocity of travel.
	 * 
	 * @return a vector representing the direction the entity wants to go
	 */
	public Vector2f update();
}
