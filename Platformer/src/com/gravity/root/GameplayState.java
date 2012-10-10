package com.gravity.root;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import com.gravity.gameplay.GravityGameController;
import com.gravity.map.TileWorld;
import com.gravity.map.TileWorldRenderer;
import com.gravity.physics.CollisionEngine;
import com.gravity.player.Player;
import com.gravity.player.PlayerKeyboardController;
import com.gravity.player.PlayerKeyboardController.Control;
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
    private CollisionEngine collisions;
    private GameContainer container;
    private final Random rand = new Random();

    private float offsetX; // Current offset x... should be negative
    private float offsetY; // Current offset y
    private float maxOffsetX; // Maximum offset x can ever be
    private int totalTime; // Time since start

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.container = container;
        resetState();
    }

    public void resetState() throws SlickException {
        map = new TileWorld(new TiledMap("assets/game_map.tmx"));
        playerA = new Player(map, this, "A");
        playerB = new Player(map, this, "B");
        rendererMap = new TileWorldRenderer(map);
        rendererA = new PlayerRenderer(playerA);
        rendererB = new PlayerRenderer(playerB);
        controllerA = new PlayerKeyboardController(playerA).setLeft(Input.KEY_A).setRight(Input.KEY_D).setJump(Input.KEY_W).setMisc(Input.KEY_S);
        controllerB = new PlayerKeyboardController(playerB).setLeft(Input.KEY_LEFT).setRight(Input.KEY_RIGHT).setJump(Input.KEY_UP)
                .setMisc(Input.KEY_DOWN);
        collisions = new CollisionEngine(map);
        // collisions.addEntity(playerA);
        collisions.addEntity(playerB);
        offsetX = 0;
        offsetY = 0;
        maxOffsetX = (map.getWidth() - container.getWidth()) * -1;
        totalTime = 0;
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        // TODO call the render stack
        rendererMap.render(g, (int) offsetX, (int) offsetY);
        rendererB.render(g, (int) offsetX, (int) offsetY);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        totalTime += delta;
        collisions.update(delta);
        offsetX -= delta * getOffsetXDelta();
        offsetX = Math.max(offsetX, maxOffsetX);
        // playerB.tick(delta);
        // TODO update on CollisionEngine and other players

    }

    private float getOffsetXDelta() {
        return 0.05f + (float) totalTime / (1000 * 1000);
    }

    @Override
    public void keyPressed(int key, char c) {
        if (!controllerA.handleKeyPress(key)) {
            controllerB.handleKeyPress(key);
        }
    }

    @Override
    public void keyReleased(int key, char c) {
        if (!controllerA.handleKeyRelease(key)) {
            controllerB.handleKeyRelease(key);
        }
    }

    @Override
    public void playerDies(Player player) {
        // TODO Auto-generated method stub
    }

    @Override
    public void swapPlayerControls(Control ctrl) {
        int akey, bkey;
        akey = controllerA.getControl(ctrl);
        bkey = controllerB.getControl(ctrl);
        controllerA.setControl(ctrl, akey);
        controllerB.setControl(ctrl, bkey);
    }

    @Override
    public void playerHitSpikes(Player player) {
        swapPlayerControls(Control.getById(rand.nextInt(Control.size())));
        System.out.println("Player " + player.toString() + " hit spikes -- remapping controls.");
        System.out.println("ControllerA: " + controllerA.toString());
        System.out.println("ControllerB: " + controllerB.toString());
    }
}
