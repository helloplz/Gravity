package com.gravity.map;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class BasicGroundTile extends Tile {

	static private BasicGroundTile template;

	static public void setTileImage(Image image) {
		template = new BasicGroundTile(image);
	}

	// Ground tiles are impassible - make the shape so
	private BasicGroundTile(Image image) {
		super(new Rectangle(0, 0, 1, 1), image);
	}

	public BasicGroundTile(Vector2f position) {
		super(template, position);
	}

}