package com.gravity.player;

import org.newdawn.slick.geom.Vector2f;

public class HumanPlayer implements Entity {

	private float jumpPower = 5;
	private float moveSpeed = 3;
	private float maxHealth = 10;

	private Vector2f velocity;
	private float health;

	public HumanPlayer() {
		health = maxHealth;
		velocity = new Vector2f(0, 0);
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
	public Vector2f update() {
		Vector2f retval = new Vector2f(velocity);
		velocity.set(velocity.getX(), 0);
		return retval;
	}
}
