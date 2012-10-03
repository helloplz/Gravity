package com.gravity.map;

import java.util.Map;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.gravity.physics.Entity;
import com.gravity.root.Renderer;

public class TileMapRenderer implements Renderer {
	private TileMap tileMap;
	private Map<Entity, Renderer> entityRenderers;

	public TileMapRenderer(TileMap tileMap, Map<Entity, Renderer> eAndR) {
		this.tileMap = tileMap;
		this.entityRenderers = eAndR;
	}

	@Override
	public void render(Graphics g) {
		int height = tileMap.getHeight();
		int width = tileMap.getWidth();
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				// Get the spot and it's image
				Tile spot = tileMap.getTerrain(x, y);
				Image img = spot.getImage();

				// Do maths to figure out where to put it
				// We do assume here that all tiles are the same width and
				// height
				int imgWidth = img.getWidth();
				int imgHeight = img.getHeight();
				int finalX = imgWidth * x;
				int finalY = imgHeight * y;

				// Put it
				g.drawImage(img, finalX, finalY);
			}
		}
		/*
		 * TODO: move this to GameEngine. for (Entity entity : tileMap.entities)
		 * { Vector2f position = entity.getPosition(); g.pushTransform();
		 * g.translate(position.getX(), position.getY());
		 * 
		 * entityRenderers.get(entity).render(g);
		 * 
		 * g.resetTransform(); g.popTransform(); }
		 */
	}
}
