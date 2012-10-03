package com.gravity.player;

/**
 * Takes input events and converts them into commands for player to handle.
 * 
 * @author xiao
 * 
 */
public class PlayerKeyboardController {
	private Player player;

	public PlayerKeyboardController(Player player) {
		this.player = player;
	}

	// TODO: create methods for setting keybindings

	/**
	 * Handle a key press event.
	 * 
	 * @return returns whether or not the keypress was handled.
	 */
	public boolean handleKeyPress(int key, char c) {
		// TODO: turn key presses into commands against player
		return false;
	}
}
