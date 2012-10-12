package com.gravity.root;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * The main menu loop.
 * 
 * @author dxiao
 */
public class MainMenuState extends BasicGameState {
    
    static public final int ID = 0;
    
    private GameContainer container;
    private StateBasedGame game;
    
    private Rectangle startGame;
    private Rectangle credits;
    
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.game = game;
        this.container = container;
    }
    
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.drawString("The Platformers [MainMenu]", 50, 100);
        g.draw(startGame = new Rectangle(48, 148, 200, 48));
        g.draw(credits = new Rectangle(48, 198, 200, 48));
        g.drawString("Start!", 50, 150);
        g.drawString("Credits", 50, 200);
    }
    
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        // no-op
    }
    
    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        if (startGame.contains(x, y)) {
            try {
                game.getState(GameplayState.ID).init(container, game);
                game.enterState(GameplayState.ID);
            } catch (SlickException e) {
                throw new RuntimeException(e);
            }
        } else if (credits.contains(x, y)) {
            try {
                game.getState(CreditsState.ID).init(container, game);
                game.enterState(CreditsState.ID);
            } catch (SlickException e) {
                throw new RuntimeException(e);
            }
        }
    }
    
    @Override
    public int getID() {
        return ID;
    }
    
}
