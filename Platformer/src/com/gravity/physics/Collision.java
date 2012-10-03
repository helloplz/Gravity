package com.gravity.physics;

/**
 * Represents a collision at a certain point in time between two entities
 * 
 * @author xiao
 */
public class Collision {

	Entity entityA, entityB;
	float time;
	int[] contactsA, contactsB;

	public Collision(Entity entityA, Entity entityB, float time,
			int[] contactsA, int[] contactsB) {
		this.entityA = entityA;
		this.entityB = entityB;
		this.time = time;
		this.contactsA = contactsA;
		this.contactsB = contactsB;
	}

	/** Get the other entity in the collision */
	public Entity getOtherEntity(Entity me) {
		if (me == entityA) {
			return entityB;
		} else {
			return entityA;
		}
	}

	/** Get the indices of the points in my shape which collided */
	public int[] getMyContacts(Entity me) {
		if (me == entityA) {
			return contactsA;
		} else {
			return contactsB;
		}
	}

	/** Get the indices of the points in the other shape which collided */
	public int[] getOthersContacts(Entity me) {
		if (me == entityA) {
			return contactsB;
		} else {
			return contactsA;
		}
	}
}
