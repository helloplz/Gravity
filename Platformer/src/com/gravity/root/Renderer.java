package com.gravity.root;

import org.newdawn.slick.Graphics;

public interface Renderer {

	/**
	 * Render the object that this renderer is attached to.
	 * 
	 * @param g
	 *            the graphics object to render to. Should be in the object's
	 *            coordinate frame.
	 */
	public void render(Graphics g);

}