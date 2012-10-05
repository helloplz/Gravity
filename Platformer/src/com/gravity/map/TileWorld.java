package com.gravity.map;

import java.util.List;

import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

import com.gravity.physics.Entity;

public class TileWorld implements GameWorld {

    private Tile[][] terrain;

    public final int height;
    public final int width;

    public TileWorld(TiledMap map) {
        // Get width/height
        this.width = new Integer(map.getMapProperty("width", "1024"));
        this.height = new Integer(map.getMapProperty("height", "1024"));

        this.terrain = new Tile[this.width][this.height];

        // Assume all objects are currently terrain
        int groupCount = map.getObjectGroupCount();
        for (int groupID = 0; groupID < groupCount; groupID++) {
            int objectCount = map.getObjectCount(groupID);
            for (int objectID = 0; objectID < objectCount; objectID++) {
                int x = map.getObjectX(groupID, objectID);
                int y = map.getObjectY(groupID, objectID);

                Vector2f position = new Vector2f(x, y);

                terrain[x][y] = new BasicGroundTile(position);
            }
        }
    }

    @Override
    public List<Shape> getCollisions(Shape shape) {
        // TODO
        return null;
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
        // TODO Auto-generated method stub
        return null;
    }
}
