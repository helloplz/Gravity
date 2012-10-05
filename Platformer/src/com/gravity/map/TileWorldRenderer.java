package com.gravity.map;

import org.newdawn.slick.Graphics;

import com.gravity.root.Renderer;

public class TileWorldRenderer implements Renderer {
    private TileWorld tileMap;

    public TileWorldRenderer(TileWorld tileMap) {
        this.tileMap = tileMap;
    }

    @Override
    public void render(Graphics g) {

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
