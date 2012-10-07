package com.gravity.map;

import java.util.List;
import java.util.Map;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.gravity.physics.Entity;

public class TileWorld implements GameWorld {
    private Tile[][] terrain;

    public final int height;
    public final int width;

    public final int tileHeight;
    public final int tileWidth;

    private List<Entity> entities;
    private Map<Shape, Entity> touchingBoxes;

    private TiledMap map;
    private Vector2f curPosition = new Vector2f(0, 0);

    public TileWorld(TiledMap map) {
        // Get width/height
        this.tileWidth = map.getTileWidth();
        this.tileHeight = map.getTileHeight();
        this.width = map.getWidth() * tileWidth;
        this.height = map.getHeight() * tileHeight;

        this.terrain = new Tile[map.getWidth()][map.getHeight()];
        this.map = map;

        touchingBoxes = Maps.newHashMap();
        Rectangle bound = new Rectangle(0, this.height - this.tileHeight, this.width, 1 * tileHeight);
        Entity bottom = new TileWorldEntity(bound, this);
        bound.grow(.1f, .1f);
        touchingBoxes.put(bound, bottom);
        entities = Lists.newArrayList(bottom);

        // Iterate over and find all tiles
        int layerId = 0;
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                int tileId = map.getTileId(i, j, layerId);
                if (tileId != 0) {
                    // Tile exists at this spot
                    Rectangle r = new Rectangle(i * tileWidth, j * tileHeight, tileWidth, tileHeight);
                    Entity e = new TileWorldEntity(r, this);

                    touchingBoxes.put(r, e);
                    entities.add(e);
                }
            }
        }

    }

    @Override
    public List<Entity> getCollisions(Shape shape) {
        List<Entity> collisions = Lists.newArrayList();
        for (Entity ent : entities) {
            if (shape.intersects(ent.getShape(0))) {
                collisions.add(ent);
            }
        }
        return collisions;
    }

    @Override
    public List<Entity> getTouching(Shape shape) {
        List<Entity> touches = Lists.newArrayList();
        for (Shape terrain : touchingBoxes.keySet()) {
            if (shape.intersects(terrain)) {
                touches.add(touchingBoxes.get(terrain));
            }
        }
        return touches;
    }

    public Tile getTerrain(int x, int y) {
        x = x % width; // Automatically handle wrapping around
        if (y >= 0 && y < height) {
            Tile ret = terrain[x][y];
            if (ret == null)
                return new BasicBlankTile(new Vector2f(x, y));
            return terrain[x][y];
        } else {
            System.out.println("TileMap error: called getTerrain with y=" + y + ", but did not meet bounds of [0," + height + ")");
        }
        return new BasicBlankTile(new Vector2f(x, y));
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public List<Entity> getTerrainEntities() {
        return entities;
    }

    @Override
    public void render(Graphics g) {
        g.pushTransform();
        map.render((int) curPosition.x, (int) curPosition.y);
        g.resetTransform();
        g.popTransform();
    }
}
