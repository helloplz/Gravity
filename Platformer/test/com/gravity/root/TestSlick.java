package com.gravity.root;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.AppGameContainer;

/**
 * 
 * @author dxiao
 */
public class TestSlick extends BasicGame {

	public TestSlick() {
		super("SimpleTest");
	}

	@Override
	public void init(GameContainer container) throws SlickException {
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		g.drawString("Hello, Slick world!", 0, 100);
	}

	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(new TestSlick());
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}