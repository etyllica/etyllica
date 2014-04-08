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
package org.dyn4j.collision.broadphase;

import org.dyn4j.collision.Collidable;
import org.dyn4j.geometry.Convex;

/**
 * Represents a pair of {@link Collidable}s that have been detected as
 * colliding in the {@link BroadphaseDetector}.
 * @author William Bittle
 * @param <E> the object type; intended to be of type {@link Collidable} or {@link Convex}
 * @version 3.1.5
 * @since 1.0.0
 */
public class BroadphasePair<E> {
	/** The first object */
	protected E a;
	
	/** The second object */
	protected E b;
	
	/**
	 * Default constructor.
	 */
	public BroadphasePair() {}
	
	/**
	 * Full constructor.
	 * @param a the first object
	 * @param b the second object
	 */
	public BroadphasePair(E a, E b) {
		this.a = a;
		this.b = b;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("BroadphasePair[A=").append(this.a)
		.append("|B=").append(this.b)
		.append("]");
		return sb.toString();
	}

	/**
	 * Returns the first object.
	 * @return E
	 * @since 3.1.5
	 */
	public E getA() {
		return this.a;
	}

	/**
	 * Sets the first object.
	 * @param a the first object
	 * @since 3.1.5
	 */
	public void setA(E a) {
		this.a = a;
	}

	/**
	 * Returns the second object.
	 * @return E
	 * @since 3.1.5
	 */
	public E getB() {
		return this.b;
	}

	/**
	 * Sets the second object.
	 * @param b the second object
	 * @since 3.1.5
	 */
	public void setB(E b) {
		this.b = b;
	}
}
