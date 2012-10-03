package com.gravity.map;

import java.util.List;

import org.newdawn.slick.geom.Shape;

public interface GameMap {

	/**
	 * Returns the set of collisions between the given shape and the map
	 * 
	 * @param shape
	 *            the hitbox for the shape we care about
	 * @return a list of shapes it collided with
	 */
	public List<Shape> getCollisions(Shape shape);

	/** Get the height of this map, in tiles */
	public int getHeight();

	/** Get the width of this map, in tiles*/
	public int getWidth();

}