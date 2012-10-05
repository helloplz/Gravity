package com.gravity.map;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class BasicGroundTile extends Tile {

    static private BasicGroundTile template;
    static {
        try {
            template = new BasicGroundTile(new Image("assets/perspective_walls.png"));
        } catch (SlickException e) {
            throw new RuntimeException(e);
        }
    }

    static public void loadTileImage(Image image) {
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
