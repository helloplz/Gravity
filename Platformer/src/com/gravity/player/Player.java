package com.gravity.player;

import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import com.gravity.map.GameMap;
import com.gravity.physics.Entity;

public abstract class Player implements Entity {

	private float jumpPower = 5;
	private float moveSpeed = 3;
	private float maxHealth = 10;

	private GameMap map;

	private Vector2f position;
	private Vector2f velocity;
	private Vector2f normal;
	private float health;

	public Player(GameMap map) {
		health = maxHealth;
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
		velocity.add(new Vector2f(0, jumpPower));
	}

	public void land() {
		velocity.set(velocity.getX(), 0);
	}

	public void moveLeft() {
		velocity.set(-moveSpeed, velocity.getY());
	}

	public void moveRight() {
		velocity.set(moveSpeed, velocity.getY());
	}

	public void stopMove() {
		velocity.set(0, velocity.getY());
	}

	public float jumpPower() {
		return jumpPower;
	}

	public float moveSpeed() {
		return moveSpeed;
	}

	@Override
	public Shape getPosition(float ticks) {
		return null;
	}
}
