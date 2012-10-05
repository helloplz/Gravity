package com.gravity.root;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import com.gravity.gameplay.GravityGameController;
import com.gravity.map.TileWorld;
import com.gravity.map.TileWorldRenderer;
import com.gravity.player.Player;
import com.gravity.player.PlayerKeyboardController;
import com.gravity.player.PlayerRenderer;

public class GameplayState extends BasicGameState implements GravityGameController {

    @Override
    public int getID() {
        return 1;
    }

    private TileWorld map;
    private Player playerA, playerB;
    private TileWorldRenderer rendererMap;
    private PlayerRenderer rendererA, rendererB;
    private PlayerKeyboardController controllerA, controllerB;

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        resetState();
    }

    public void resetState() throws SlickException {
        map = new TileWorld(new TiledMap("assets/test2.tmx"));
        playerA = new Player(map, this);
        playerB = new Player(map, this);
        rendererMap = new TileWorldRenderer(map);
        rendererA = new PlayerRenderer(playerA);
        rendererB = new PlayerRenderer(playerB);
        controllerA = new PlayerKeyboardController(playerA);
        controllerB = new PlayerKeyboardController(playerB);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        // TODO call the render stack
        rendererMap.render(g);
        rendererA.render(g);

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        // TODO update on CollisionEngine and other players

    }

    @Override
    public void keyPressed(int key, char c) {
        System.out.println("Key pressed: " + key + " " + c);
    }

    @Override
    public void keyReleased(int key, char c) {
        System.out.println("Key released: " + key + " " + c);
    }

    @Override
    public void playerDies(Player player) {
        // TODO Auto-generated method stub
    }
}
