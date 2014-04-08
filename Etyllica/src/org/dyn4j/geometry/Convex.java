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
package org.dyn4j.geometry;

/**
 * Represents a {@link Convex} {@link Shape}.
 * <p>
 * A {@link Convex} {@link Shape} is a {@link Shape} that given a line, the line will only 
 * intersect at most 2 non-coincident non-colinear edges.
 * @author William Bittle
 * @version 1.0.3
 * @since 1.0.0
 */
public interface Convex extends Shape, Transformable {
	/**
	 * Returns an array of separating axes to test for this {@link Shape}.
	 * <p>
	 * Given some points, this method will return the separating axes for 
	 * this {@link Shape}'s voronoi regions also.  The voronoi axes will not be included
	 * if the foci array is null.
	 * <p>
	 * The points in the foci array are assumed to be in world space.
	 * <p>
	 * The returned axes are normalized.
	 * @param foci the world space points representing foci of curved {@link Shape}s; can be null
	 * @param transform the local to world space {@link Transform} of this {@link Convex} {@link Shape}
	 * @return {@link Vector2}[]
	 */
	public abstract Vector2[] getAxes(Vector2[] foci, Transform transform);
	
	/**
	 * Returns an array of world space foci points for circular curved edges.
	 * <p>
	 * This method returns null if the {@link Shape} has zero curved edges.
	 * @param transform the local to world space {@link Transform} of this {@link Convex} {@link Shape}
	 * @return {@link Vector2}[]
	 */
	public abstract Vector2[] getFoci(Transform transform);
	
	/**
	 * Returns the feature farthest in the direction of n.
	 * @param n the direction
	 * @param transform the local to world space {@link Transform} of this {@link Convex} {@link Shape}
	 * @return {@link Feature}
	 */
	public abstract Feature getFarthestFeature(Vector2 n, Transform transform);
	
	/**
	 * Returns the point farthest in the direction of n.  If two points are 
	 * equally distant along the given {@link Vector2} the first one in the vertex 
	 * list is used.
	 * <p>
	 * The returned point is in world space.
	 * @param n the direction
	 * @param transform the local to world space {@link Transform} of this {@link Convex} {@link Shape}
	 * @return {@link Vector2}
	 */
	public abstract Vector2 getFarthestPoint(Vector2 n, Transform transform);
}
