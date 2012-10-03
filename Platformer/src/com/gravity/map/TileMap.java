package com.gravity.map;

import java.util.List;
import java.util.Map;

import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

import com.gravity.player.Entity;
import com.gravity.player.HumanPlayer;

public class TileMap {

    Tile[][] terrain;
    List<Entity> entities;
    Map<Entity, Vector2f> positions;

    int height;
    int width;

    public TileMap(TiledMap map) {
        // Get width/height
        this.width = new Integer(map.getMapProperty("width", "1024"));
        this.height = new Integer(map.getMapProperty("height", "1024"));

        // Assume all objects are currently terrain
        int groupCount = map.getObjectGroupCount();
        for (int groupID = 0; groupID < groupCount; groupID++) {
            int objectCount = map.getObjectCount(groupID);
            for (int objectID = 0; objectID < objectCount; objectID++) {
                String type = map.getObjectType(groupID, objectID);

                if (type.equals("terrain")) {
                    int x = map.getObjectX(groupID, objectID);
                    int y = map.getObjectY(groupID, objectID);

                    Vector2f position = new Vector2f(x, y);

                    terrain[x][y] = new BasicGroundTile(position);
                } else {
                    // Entity
                    Entity hp = new HumanPlayer();
                    entities.add(hp);
                    positions.put(hp, new Vector2f(0, 0));
                }
            }
        }
    }

    public void update() {
        // TODO: for each entity, get the desired velocity, and calculate
        // an actual velocity based on the entity's location, terrain, and
        // gravity. Then calculate each entity's new position
        for (Entity entity : entities) {
            Vector2f desiredV = entity.update();

        }
    }

    public Tile getTerrain(int x, int y) {
        x = x % width; // Automatically handle wrapping around
        if (y >= 0 && y < height) {
            return terrain[x][y];
        } else {
            System.out.println("TileMap error: called getTerrain with y=" + y + ", but did not meet bounds of [0," + height + ")");
        }
        return new BasicBlankTile(new Vector2f(x, y));
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
