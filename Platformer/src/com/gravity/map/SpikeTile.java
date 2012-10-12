package com.gravity.map;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class SpikeTile extends Tile {
    
    static private SpikeTile template;
    static {
        try {
            template = new SpikeTile(new Image("assets/Spikes.png"));
        } catch (SlickException e) {
            throw new RuntimeException(e);
        }
    }
    
    static public void loadTileImage(Image image) {
        template = new SpikeTile(image);
    }
    
    // Ground tiles are impassible - make the shape so
    private SpikeTile(Image image) {
        super(image);
    }
    
    public SpikeTile(Vector2f position) {
        super(template, position);
    }
}
