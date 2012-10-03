package com.gravity.root;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.gravity.gameplay.GravityGameController;
import com.gravity.map.TileMap;
import com.gravity.map.TileMapRenderer;
import com.gravity.player.Player;
import com.gravity.player.PlayerRenderer;

public class GameplayState extends BasicGameState implements
		GravityGameController {

	@Override
	public int getID() {
		return 1;
	}

	private TileMap map;
	private Player playerA, playerB;
	private TileMapRenderer rendererMap;
	private PlayerRenderer rendererA, rendererB;

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		resetState();
	}

	public void resetState() {
		// TODO start up and create Maps and Players
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		// TODO call the render stack

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		// TODO update on CollisionEngine and other players

	}

	@Override
	public int remapKey(int key) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int playerWins(Player player) {
		// TODO Auto-generated method stub
		return 0;
	}
}
