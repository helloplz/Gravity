package com.gravity.gameplay;

import com.gravity.player.Player;

/**
 * Interface which specifies any kind of logic that crosses between model,
 * controller, and renderers.
 * 
 * @author xiao
 */
public interface GravityGameController {

	/**
	 * A key needs to be remapped - ask the controller what key to swap to. <br>
	 * NOTE: useful if, say, players have jump keys swapped when one hits an
	 * enemy.
	 * 
	 * @param key
	 *            the key to swap out
	 * @return the key to swap in
	 */
	public int remapKey(int key);

	public int playerWins(Player player);
}
