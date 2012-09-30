package com.gravity.gameplay;

import com.gravity.gameplay.maps.Map;

public interface Location {
	
	float getXPosition();

	float getYPosition();
	
	boolean isValid(Map m);
	
}
