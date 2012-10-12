package com.gravity.map;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

public abstract class Tile {
    
    /** Image of tile, used for drawing */
    private final Image image;
    
    public Tile(Image image) {
        this.image = image;
    }
    
    public Tile(Tile tile, Vector2f position) {
        this.image = tile.image;
    }
    
    public Image getImage() {
        return image;
    }
    
}
