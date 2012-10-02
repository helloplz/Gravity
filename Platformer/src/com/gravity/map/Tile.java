package com.gravity.map;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

public abstract class Tile {

	/** Shape of tile, used for collisions */
	private Shape shape;

	/** Image of tile, used for drawing */
	private Image image;

	public Tile(Shape shape, Image image) {
		this.shape = shape;
		this.image = image;
	}

	public Tile(Tile tile, Vector2f position) {
		this.image = tile.image;
		this.shape = tile.shape.transform(Transform.createTranslateTransform(
				position.getX(), position.getY()));
	}

	public Shape getShape() {
		return shape;
	}

	public Image getImage() {
		return image;
	}

	public boolean isIntersecting(Shape shape) {
		return shape.intersects(this.shape);
	}
}
