package com.gravity.map;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class BasicBlankTile extends Tile {
    
    static private BasicBlankTile template;
    static {
        try {
            template = new BasicBlankTile(new Image("assets/blank.png"));
        } catch (SlickException e) {
            throw new RuntimeException(e);
        }
    }
    
    // Blank tiles are passable - make the shape to reflect that
    private BasicBlankTile(Image image) {
        super(image);
    }
    
    public BasicBlankTile(Vector2f position) {
        super(template, position);
    }
}
