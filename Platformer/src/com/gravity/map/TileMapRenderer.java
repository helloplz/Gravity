package com.gravity.map;

import java.util.Map;

import org.newdawn.slick.Graphics;

import com.google.common.collect.Maps;
import com.gravity.physics.Entity;
import com.gravity.root.Renderer;

public class TileMapRenderer implements Renderer {
	private TileMap tileMap;
	private Map<Entity, Renderer> entityRenderers;

	public TileMapRenderer(TileMap tileMap) {
		this.tileMap = tileMap;
		this.entityRenderers = Maps.newHashMap();
		// TODO: populate entityRenderers - either through construction here
		// or adding in an arguement
	}

	@Override
	public void render(Graphics g) {
		int height = tileMap.getHeight();
		int width = tileMap.getWidth();
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				// TODO: for each tile, render the image in the appropriate area
			}
		}
		/*
		 * TODO: move this to GameEngin for (Entity entity : tileMap.entities) {
		 * Vector2f position = entity.getPosition(); g.pushTransform();
		 * g.translate(position.getX(), position.getY());
		 * 
		 * entityRenderers.get(entity).render(g);
		 * 
		 * g.resetTransform(); g.popTransform(); }
		 */
	}
}
