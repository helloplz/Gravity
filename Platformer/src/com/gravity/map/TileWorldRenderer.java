package com.gravity.map;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.gravity.root.Renderer;

public class TileWorldRenderer implements Renderer {
    private TileWorld tileMap;
    private Image Background;

    public TileWorldRenderer(TileWorld tileMap) {
        this.tileMap = tileMap;
        try {
        	// "./assets/BambooForest2small.png"
        	// "./assets/2ufa5h5.png"
            Background = new Image("./assets/2ufa5h5.png");
        } catch (SlickException e) {
            throw new RuntimeException(e);
        }
    }
    

    @Override
    public void render(Graphics g) {
    	
    	g.drawImage(Background,0,0);
    	
        // TiledMap supports easy rendering. Let's use it!
        // Later we'll need to some how adjust x,y for offset/scrolling
        tileMap.render(g);

        /*
         * TODO: move this to GameEngine. for (Entity entity : tileMap.entities) { Vector2f position = entity.getPosition(); g.pushTransform();
         * g.translate(position.getX(), position.getY());
         * 
         * entityRenderers.get(entity).render(g);
         * 
         * g.resetTransform(); g.popTransform(); }
         */
    }
}
