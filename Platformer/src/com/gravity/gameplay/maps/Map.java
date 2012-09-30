package com.gravity.gameplay.maps;

public interface Map {
	
	/**
	 * @returns the height of the terrain at the given index.
	 */
	int getHeight(int index);
	
	/**
	 * @returns the gravity of the map
	 */
	int getGravity();
	
}
