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
package org.dyn4j.dynamics.contact;

import org.dyn4j.collision.manifold.ManifoldPointId;
import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Vector2;

/**
 * Represents a contact point between two {@link Body} objects.
 * @author William Bittle
 * @version 3.1.2
 * @since 1.0.0
 */
public class Contact {
	/** The manifold point id for warm starting */
	protected ManifoldPointId id;
	
	/** Whether the contact is enabled or not */
	protected boolean enabled;
	
	/** The contact point in world space */
	protected Vector2 p;
	
	/** The contact penetration depth */
	protected double depth;
	
	/** The contact point in {@link Body}1 space */
	protected Vector2 p1;
	
	/** The contact point in {@link Body}2 space */
	protected Vector2 p2;
	
	/** The {@link Vector2} from the center of {@link Body}1 to the contact point */
	protected Vector2 r1;
	
	/** The {@link Vector2} from the center of {@link Body}2 to the contact point */
	protected Vector2 r2;
	
	/** The accumulated normal impulse */
	protected double jn;
	
	/** The accumulated tangent impulse */
	protected double jt;
	
	/** The accumulated position impulse */
	protected double jp;
	
	/** The mass normal */
	protected double massN;
	
	/** The mass tangent */
	protected double massT;
	
	/** The velocity bias */
	protected double vb;
	
	/**
	 * Full constructor.
	 * @param id the manifold point id used for warm starting
	 * @param point the world space collision point
	 * @param depth the penetration depth of this point
	 * @param p1 the collision point in {@link Body}1's local space
	 * @param p2 the collision point in {@link Body}2's local space
	 */
	public Contact(ManifoldPointId id, Vector2 point, double depth, Vector2 p1, Vector2 p2) {
		this.id = id;
		this.enabled = true;
		this.p = point;
		this.depth = depth;
		this.p1 = p1;
		this.p2 = p2;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Contact[Id=").append(this.id)
		.append("|IsEnabled=").append(this.enabled)
		.append("|Point=").append(this.p)
		.append("|PointInBody1=").append(this.p1)
		.append("|PointInBody2=").append(this.p2)
		.append("|Depth=").append(this.depth)
		.append("|R1=").append(this.r1)
		.append("|R2=").append(this.r2)
		.append("|NormalImpulse=").append(this.jn)
		.append("|TangentImpulse=").append(this.jt)
		.append("|PositionImpulse=").append(this.jp)
		.append("|NormalMass=").append(this.massN)
		.append("|TangentMass=").append(this.massT)
		.append("|VelocityBias=").append(this.vb)
		.append("]");
		return sb.toString();
	}
	
	/**
	 * Returns the manifold point id.
	 * @return {@link ManifoldPointId}
	 * @since 3.1.2
	 */
	public ManifoldPointId getId() {
		return this.id;
	}
	
	/**
	 * Returns true if this contact is enabled.
	 * @return boolean
	 */
	public boolean isEnabled() {
		return this.enabled;
	}
	
	/**
	 * Returns the world space collision point.
	 * @return {@link Vector2} the collision point in world space
	 */
	public Vector2 getPoint() {
		return this.p;
	}
	
	/**
	 * Returns the penetration depth of this point.
	 * @return double the penetration depth
	 */
	public double getDepth() {
		return this.depth;
	}
	
	/**
	 * Returns the accumulated normal impulse applied at this point.
	 * @return double the accumulated normal impulse
	 */
	public double getNormalImpulse() {
		return this.jn;
	}
	
	/**
	 * Returns the accumulated tangential impulse applied at this point.
	 * @return double the accumulated tangential impulse
	 */
	public double getTangentialImpulse() {
		return this.jt;
	}
}
