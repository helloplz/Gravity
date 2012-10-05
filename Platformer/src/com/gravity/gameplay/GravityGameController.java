package com.gravity.gameplay;

import com.gravity.player.Player;

/**
 * Interface which specifies any kind of logic that crosses between model,
 * controller, and renderers.
 * 
 * @author xiao
 */
public interface GravityGameController {

	public void playerDies(Player player);
}
