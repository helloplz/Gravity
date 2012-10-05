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

	private float jumpPower = 5;
	private float moveSpeed = 3;
	private float maxHealth = 10;

	private GameWorld map;

	private Vector2f position;
	private Vector2f velocity;
	private Vector2f normal;
	private float health;

	public Player(GameWorld map, GravityGameController game) {
		health = maxHealth;
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
		velocity.add(new Vector2f(0, jumpPower));
	}

	/**
	 * 
	 * @param direction
	 */
	public void move(Movement direction) {

	}

	public void land() {
		velocity.set(velocity.getX(), 0);
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
