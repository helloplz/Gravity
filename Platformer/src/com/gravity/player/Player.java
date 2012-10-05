package com.gravity.player;

import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import com.gravity.gameplay.GravityGameController;
import com.gravity.map.GameWorld;
import com.gravity.physics.Collision;
import com.gravity.physics.Entity;

public class Player implements Entity {

	public enum Movement {
		LEFT, RIGHT, STOP
	}

	private GravityGameController game;

	// PLAYER STARTING CONSTANTS
	private final float JUMP_POWER = 5;
	private final float MOVEMENT_INCREMENT = 3;
	private float MAX_HEALTH = 10;

	// PLAYER CURRENT VALUES
	private GameWorld map;

	private Vector2f position;
	private Vector2f velocity;
	private Vector2f facing;
	private float health;

	public Player(GameWorld map, GravityGameController game) {
		health = MAX_HEALTH;
		velocity = new Vector2f(0, 0);
		this.map = map;
		this.game = game;
	}

	public void takeDamage(float damage) {
		health -= damage;
	}

	public void heal(float heal) {
		health += heal;
	}

	/**
	 * @param jumping
	 *            true if keydown, false if keyup
	 */
	public void jump(boolean jumping) {
	}

	/**
	 * 
	 * @param direction
	 */
	public void move(Movement direction) {
	}

	public float jumpPower() {
		return JUMP_POWER;
	}

	public float moveSpeed() {
		return MOVEMENT_INCREMENT;
	}

	@Override
	public Shape getPosition(float ticks) {
		return null;
	}

	@Override
	public Vector2f getVelocity(float ticks) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Shape handleCollisions(float ticks, Collision... collisions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Shape rehandleCollisions(float ticks, Collision... collisions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void tick(float ticks) {
		// TODO Auto-generated method stub

	}
}
