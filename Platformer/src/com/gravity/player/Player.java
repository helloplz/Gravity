package com.gravity.player;

import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import com.gravity.map.GameWorld;
import com.gravity.physics.Entity;

public abstract class Player implements Entity {

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

	public Player(GameWorld map) {
		health = MAX_HEALTH;
		velocity = new Vector2f(0, 0);
		this.map = map;
	}

	public void takeDamage(float damage) {
		health -= damage;
	}

	public void heal(float heal) {
		health += heal;
	}

	public void jump() {
		velocity.add(new Vector2f(0, JUMP_POWER));
	}

	public void land() {
		velocity.set(velocity.getX(), 0);
	}

	public void moveLeft() {
		velocity.set(-MOVEMENT_INCREMENT, velocity.getY());
	}

	public void moveRight() {
		velocity.set(MOVEMENT_INCREMENT, velocity.getY());
	}

	public void stopMove() {
		velocity.set(0, velocity.getY());
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
}
