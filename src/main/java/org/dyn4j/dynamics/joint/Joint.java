/*
 * Copyright (c) 2010-2013 William Bittle  http://www.dyn4j.org/
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted 
 * provided that the following conditions are met:
 * 
 *   * Redistributions of source code must retain the above copyright notice, this list of conditions 
 *     and the following disclaimer.
 *   * Redistributions in binary form must reproduce the above copyright notice, this list of conditions 
 *     and the following disclaimer in the documentation and/or other materials provided with the 
 *     distribution.
 *   * Neither the name of dyn4j nor the names of its contributors may be used to endorse or 
 *     promote products derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR 
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND 
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR 
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL 
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, 
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER 
 * IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT 
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.dyn4j.dynamics.joint;

import java.util.UUID;

import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.Constraint;
import org.dyn4j.geometry.Vector2;

/**
 * Represents constrained motion between two {@link Body}s.
 * @author William Bittle
 * @version 3.1.4
 * @since 1.0.0
 */
public abstract class Joint extends Constraint {
	/**
	 * Enumeration for the limit states a joint can have.
	 * @author William Bittle
	 * @version 1.0.3
	 * @since 1.0.0
	 */
	public static enum LimitState {
		/** The state if the upper and lower limits are equal within tolerance */
		EQUAL,
		/** The state if the joint has reached or passed the lower limit */
		AT_LOWER,
		/** The state if the joint has reached or passed the upper limit */
		AT_UPPER,
		/** The state if the joint limits are disabled or if the joint is between the limits */
		INACTIVE;
	}
	
	/** The joint's unique identifier */
	protected UUID id = UUID.randomUUID();
	
	/** Whether the pair of bodies joined together can collide with each other */
	protected boolean collisionAllowed;
	
	/**
	 * Optional constructor.
	 * <p>
	 * Assumes that the joined bodies do not participate 
	 * in collision detection and resolution.
	 * @param body1 the first {@link Body}
	 * @param body2 the second {@link Body}
	 * @throws NullPointerException if body1 or body2 is null
	 */
	public Joint(Body body1, Body body2) {
		this(body1, body2, false);
	}
	
	/**
	 * Full constructor.
	 * @param body1 the first {@link Body}
	 * @param body2 the second {@link Body}
	 * @param collisionAllowed true if the joined {@link Body}s can take part in collision detection
	 * @throws NullPointerException if body1 or body2 is null
	 */
	public Joint(Body body1, Body body2, boolean collisionAllowed) {
		super(body1, body2);
		this.collisionAllowed = collisionAllowed;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Id=").append(this.id)
		// body1, body2, island
		.append("|").append(super.toString())
		.append("|IsCollisionAllowed=").append(this.collisionAllowed);
		return sb.toString();
	}
	
	/**
	 * Performs any initialization of the velocity and position constraints.
	 */
	public abstract void initializeConstraints();
	
	/**
	 * Solves the velocity constraints.
	 */
	public abstract void solveVelocityConstraints();
	
	/**
	 * Solves the position constraints.
	 * @return boolean true if the position constraints were solved
	 */
	public abstract boolean solvePositionConstraints();
	
	/**
	 * Returns the anchor point on the first {@link Body} in
	 * world coordinates.
	 * @return {@link Vector2}
	 */
	public abstract Vector2 getAnchor1();
	
	/**
	 * Returns the anchor point on the second {@link Body} in
	 * world coordinates.
	 * @return {@link Vector2}
	 */
	public abstract Vector2 getAnchor2();
	
	/**
	 * Returns the force applied to the {@link Body}s in order
	 * to satisfy the constraint in newtons.
	 * @param invdt the inverse delta time
	 * @return {@link Vector2}
	 */
	public abstract Vector2 getReactionForce(double invdt);
	
	/**
	 * Returns the torque applied to the {@link Body}s in order
	 * to satisfy the constraint in newton-meters.
	 * @param invdt the inverse delta time
	 * @return double
	 */
	public abstract double getReactionTorque(double invdt);
	
	/**
	 * Returns the unique identifier for this joint instance.
	 * @return String
	 * @since 3.0.1
	 */
	public UUID getId() {
		return this.id;
	}
	
	/**
	 * Returns true if this {@link Joint} is active.
	 * <p>
	 * A joint is only active if both joined {@link Body}s are active.
	 * @return boolean
	 */
	public boolean isActive() {
		return this.body1.isActive() && this.body2.isActive();
	}
	
	/**
	 * Returns true if collision between the joined {@link Body}s is allowed.
	 * @return boolean
	 */
	public boolean isCollisionAllowed() {
		return this.collisionAllowed;
	}
	
	/**
	 * Sets whether collision is allowed between the joined {@link Body}s.
	 * @param flag true if collisions are allowed
	 */
	public void setCollisionAllowed(boolean flag) {
		// is it different than the current value
		if (this.collisionAllowed != flag) {
			// wake up both bodies
			this.body1.setAsleep(false);
			this.body2.setAsleep(false);
			// set the new value
			this.collisionAllowed = flag;
		}
	}
}
