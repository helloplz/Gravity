package com.gravity.map;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Vector2f;

public class BasicBlankTile extends Tile {

	static private BasicBlankTile template;

	static public void setTileImage(Image image) {
		template = new BasicBlankTile(image);
	}

	// Blank tiles are passable - make the shape to reflect that
	private BasicBlankTile(Image image) {
		super(new Polygon(), image);
	}

	public BasicBlankTile(Vector2f position) {
		super(template, position);
	}
}
