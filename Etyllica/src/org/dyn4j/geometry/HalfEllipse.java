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

import org.dyn4j.resources.Messages;

/**
 * Represents a half ellipse shape.
 * <p>
 * A half ellipse must have a width and height greater than zero. 
 * The height parameter is the height of the half.
 * <p>
 * <b>This shape is only supported by the GJK collision detection algorithm</b>. An 
 * <code>UnsupportedOperationException</code> is thrown when this shape is used with SAT.  If you are using
 * or are planning on using the SAT collision detection algorithm, you can use the 
 * {@link Geometry#createPolygonalHalfEllipse(int, double, double)} method to create a half ellipse
 * {@link Polygon} approximation. Another option is to use the GJK or your own collision detection
 * algorithm for this shape only and use SAT on others (fallback).
 * @author William Bittle
 * @since 3.1.5
 * @version 3.1.7
 */
public class HalfEllipse extends AbstractShape implements Convex, Shape, Transformable {
	/** The half ellipse inertia constant. See http://www.efunda.com/math/areas/ellipticalhalf.cfm */
	private static final double INERTIA_CONSTANT = Math.PI / 8.0 - 8.0 / (9.0 * Math.PI);
	
	/** The ellipse width */
	protected double width;
	
	/** The ellipse height */
	protected double height;
	
	/** The half-width */
	protected double a;
	
	/** A local vector to  */
	protected Vector2 localXAxis;

	/** The ellipse center */
	protected Vector2 ellipseCenter;
	
	/** The vertices of the bottom */
	protected Vector2[] vertices;

	/**
	 * Minimal constructor.
	 * <p>
	 * This creates an axis-aligned half ellipse fitting inside a rectangle
	 * of the given width and height.
	 * @param width the width
	 * @param height the height of the half
	 * @throws IllegalArgumentException if either the width or height is less than or equal to zero
	 */
	public HalfEllipse(double width, double height) {
		// validate the width and height
		if (width <= 0.0) throw new IllegalArgumentException(Messages.getString("geometry.halfEllipse.invalidWidth"));
		if (height <= 0.0) throw new IllegalArgumentException(Messages.getString("geometry.halfEllipse.invalidHeight"));
		
		this.width = width;
		this.height = height;
		
		// compute the major and minor axis lengths
		// (the x,y radii)
		this.a = width * 0.5;

		// set the ellipse center
		this.ellipseCenter = new Vector2();
		// compute the real center
		this.center = new Vector2(0, (4.0 * height) / (3.0 * Math.PI));
		
		// since we create ellipses as axis aligned we set the local x axis
		// to the world space x axis
		this.localXAxis = new Vector2(1.0, 0.0);
		
		// setup the vertices
		this.vertices = new Vector2[] {
			// the left point
			new Vector2(-this.a, 0),
			// the right point
			new Vector2( this.a, 0)
		};

		// set the rotation radius
		this.radius = this.center.distance(this.vertices[1]);
	}
	
	/* (non-Javadoc)
	 * @see org.dyn4j.geometry.Wound#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("HalfEllipse[").append(super.toString())
		.append("|Width=").append(this.width)
		.append("|Height=").append(this.height)
		.append("|UserData=").append(this.userData)
		.append("]");
		return sb.toString();
	}

	/* (non-Javadoc)
	 * @see org.dyn4j.geometry.Convex#getAxes(org.dyn4j.geometry.Vector2[], org.dyn4j.geometry.Transform)
	 */
	@Override
	public Vector2[] getAxes(Vector2[] foci, Transform transform) {
		// this shape is not supported by SAT
		throw new UnsupportedOperationException(Messages.getString("geometry.halfEllipse.satNotSupported"));
	}

	/* (non-Javadoc)
	 * @see org.dyn4j.geometry.Convex#getFoci(org.dyn4j.geometry.Transform)
	 */
	@Override
	public Vector2[] getFoci(Transform transform) {
		// this shape is not supported by SAT
		throw new UnsupportedOperationException(Messages.getString("geometry.halfEllipse.satNotSupported"));
	}
	
	/* (non-Javadoc)
	 * @see org.dyn4j.geometry.Convex#getFarthestPoint(org.dyn4j.geometry.Vector2, org.dyn4j.geometry.Transform)
	 */
	@Override
	public Vector2 getFarthestPoint(Vector2 n, Transform transform) {
		// convert the world space vector(n) to local space
		Vector2 localAxis = transform.getInverseTransformedR(n);
		// include local rotation
		double r = this.getRotation();
		// invert the local rotation
		localAxis.rotate(-r);
		// an ellipse is a circle with a non-uniform scaling transformation applied
		// so we can achieve that by scaling the input axis by the major and minor
		// axis lengths
		localAxis.x *= this.a;
		localAxis.y *= this.height;
		// then normalize it
		localAxis.normalize();
		
		Vector2 p = null;
		if (localAxis.y <= 0 && localAxis.x >= 0) {
			return transform.getTransformed(this.vertices[1]);
		} else if (localAxis.y <= 0 && localAxis.x <= 0) {
			return transform.getTransformed(this.vertices[0]);
		} else {
			// add the radius along the vector to the center to get the farthest point
			p = new Vector2(localAxis.x * this.a, localAxis.y  * this.height);
		}
		
		// include local rotation
		// invert the local rotation
		p.rotate(r);
		p.add(this.ellipseCenter);
		// then finally convert back into world space coordinates
		transform.transform(p);
		return p;
	}

	/* (non-Javadoc)
	 * @see org.dyn4j.geometry.Convex#getFarthestFeature(org.dyn4j.geometry.Vector2, org.dyn4j.geometry.Transform)
	 */
	@Override
	public Feature getFarthestFeature(Vector2 n, Transform transform) {
		Vector2 localAxis = transform.getInverseTransformedR(n);
		if (localAxis.getAngleBetween(this.localXAxis) < 0) {
			// then its the farthest point
			Vector2 point = this.getFarthestPoint(n, transform);
			return new Vertex(point);
		} else {
			// return the full bottom side
			return Segment.getFarthestFeature(this.vertices[0], this.vertices[1], n, transform);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.dyn4j.geometry.Shape#project(org.dyn4j.geometry.Vector2, org.dyn4j.geometry.Transform)
	 */
	@Override
	public Interval project(Vector2 n, Transform transform) {
		// get the world space farthest point
		Vector2 p1 = this.getFarthestPoint(n, transform);
		Vector2 p2 = this.getFarthestPoint(n.getNegative(), transform);
		// project the point onto the axis
		double d1 = p1.dot(n);
		double d2 = p2.dot(n);
		// get the interval along the axis
		return new Interval(d2, d1);
	}

	/* (non-Javadoc)
	 * @see org.dyn4j.geometry.Shape#createAABB(org.dyn4j.geometry.Transform)
	 */
	@Override
	public AABB createAABB(Transform transform) {
		Interval x = this.project(Vector2.X_AXIS, transform);
		Interval y = this.project(Vector2.Y_AXIS, transform);
		
		return new AABB(x.getMin(), y.getMin(), x.getMax(), y.getMax());
	}
	
	/* (non-Javadoc)
	 * @see org.dyn4j.geometry.Shape#createMass(double)
	 */
	@Override
	public Mass createMass(double density) {
		double area = Math.PI * this.a * this.height;
		double m = area * density * 0.5;
		// moment of inertia given by: http://www.efunda.com/math/areas/ellipticalhalf.cfm
		double I = m * (this.a * this.a + this.height * this.height) * INERTIA_CONSTANT;
		return new Mass(this.center, m, I);
	}

	/* (non-Javadoc)
	 * @see org.dyn4j.geometry.Shape#getRadius(org.dyn4j.geometry.Vector2)
	 */
	@Override
	public double getRadius(Vector2 center) {
		return this.radius + center.distance(this.center);
	}
	
	/* (non-Javadoc)
	 * @see org.dyn4j.geometry.Shape#contains(org.dyn4j.geometry.Vector2, org.dyn4j.geometry.Transform)
	 */
	@Override
	public boolean contains(Vector2 point, Transform transform) {
		// equation of an ellipse:
		// (x - h)^2/a^2 + (y - k)^2/b^2 = 1
		// for a point to be inside the ellipse, we can plug in
		// the point into this equation and verify that the value
		// is less than or equal to one
		
		// get the world space point into local coordinates
		Vector2 localPoint = transform.getInverseTransformed(point);
		// account for local rotation
		double r = this.getRotation();
		localPoint.rotate(-r, this.ellipseCenter.x, this.ellipseCenter.y);
		
		// translate into local coordinates
		double x = (localPoint.x - this.ellipseCenter.x);
		double y = (localPoint.y - this.ellipseCenter.y);
		
		// for half ellipse we have an early out
		if (y < 0) return false;
		
		double x2 = x * x;
		double y2 = y * y;
		double a2 = this.a * this.a;
		double b2 = this.height * this.height;
		double value = x2 / a2 + y2 / b2;
		
		if (value <= 1.0) {
			return true;
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see org.dyn4j.geometry.AbstractShape#rotate(double, double, double)
	 */
	@Override
	public void rotate(double theta, double x, double y) {
		super.rotate(theta, x, y);
		// rotate the local axis as well
		this.localXAxis.rotate(theta);
		// rotate the vertices
		for (int i = 0; i < this.vertices.length; i++) {
			this.vertices[i].rotate(theta, x, y);
		}
		// rotate the ellipse center
		this.ellipseCenter.rotate(theta, x, y);
	}

	/* (non-Javadoc)
	 * @see org.dyn4j.geometry.AbstractShape#translate(double, double)
	 */
	@Override
	public void translate(double x, double y) {
		// translate the centroid
		super.translate(x, y);
		// translate the pie vertices
		for (int i = 0; i < this.vertices.length; i++) {
			this.vertices[i].add(x, y);
		}
		// translate the ellipse center
		this.ellipseCenter.add(x, y);
	}

	/**
	 * Returns the rotation about the local center in radians.
	 * @return double the rotation in radians
	 */
	public double getRotation() {
		return Vector2.X_AXIS.getAngleBetween(this.localXAxis);
	}
	
	/**
	 * Returns the width.
	 * @return double
	 */
	public double getWidth() {
		return this.width;
	}
	
	/**
	 * Returns the height.
	 * @return double
	 */
	public double getHeight() {
		return this.height;
	}
	
	/**
	 * Returns the half width.
	 * @return double
	 */
	public double getHalfWidth() {
		return this.a;
	}
	
	/**
	 * Returns the center of the ellipse.
	 * @return {@link Vector2}
	 */
	public Vector2 getEllipseCenter() {
		return this.ellipseCenter;
	}
}
