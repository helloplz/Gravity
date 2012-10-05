package com.gravity.map;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

public abstract class Tile {

    /**
     * Shape of tile, used for collisions. If shape is null, then there is no collision
     */
    private final Shape shape;

    /** Image of tile, used for drawing */
    private final Image image;

    public Tile(Shape shape, Image image) {
        this.shape = shape;
        this.image = image;
    }

    public Tile(Tile tile, Vector2f position) {
        this.image = tile.image;
        if (tile.shape == null) {
            this.shape = null;
        } else {
            this.shape = tile.shape.transform(Transform.createTranslateTransform(position.getX(), position.getY()));
        }
    }

    public Shape getShape() {
        return shape;
    }

    public Image getImage() {
        return image;
    }

    public boolean isIntersecting(Shape shape) {
        if (shape == null || this.shape == null)
            return false;
        return shape.intersects(this.shape);
    }
}
