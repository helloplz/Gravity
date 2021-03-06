package com.gravity.root;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameWinState extends BasicGameState {
    
    static final int ID = 11;
    
    private StateBasedGame game;
    private Rectangle restart;
    
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.game = game;
    }
    
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.drawString("You win! Congratulations!", 50, 100);
        g.draw(restart = new Rectangle(48, 148, 200, 48));
        g.drawString("Back to main menu...", 50, 150);
    }
    
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
    }
    
    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        if (restart.contains(x, y)) {
            game.enterState(MainMenuState.ID);
        }
    }
    
    @Override
    public int getID() {
        return ID;
    }
    
}
