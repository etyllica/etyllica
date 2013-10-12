/*
 * @(#)nurbs_obj.java 0.1 99/11/2
 *
 * jGL 3-D graphics library for Java
 * Copyright (c) 1999 Robin Bing-Yu Chen (robin@is.s.u-tokyo.ac.jp)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or any later version. the GNU Lesser
 * General Public License should be included with this distribution
 * in the file LICENSE.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 */

package org.jgl.glu;

//import jgl.GLU;
import static org.jgl.GLU.GLU_INVALID_VALUE;
import static org.jgl.GLU.GLU_NO_ERROR;
import static org.jgl.GLU.GLU_NURBS_ERROR1;
import static org.jgl.GLU.GLU_NURBS_ERROR2;
import static org.jgl.GLU.GLU_NURBS_ERROR34;
import static org.jgl.GLU.GLU_NURBS_ERROR36;
import static org.jgl.GLU.GLU_NURBS_ERROR4;
import static org.jgl.GLU.GLU_NURBS_ERROR5;

/**
 * nurbs_obj is one of the GLU NURBS class of JavaGL 2.1.
 *
 * @version 	0.1, 2 Nov 1999
 * @author 	Robin Bing-Yu Chen
 */

public class NURBS_Object {

	public int   knot_count;
	public float knot [];
	public int   stride;
	public int   order;

	private int knot_test () {
		float temp = knot [0];
		int mult = 1;		// multiplicity
		int i;

		for (i = 1; i < knot_count; i++) {
			if (knot [i] < temp)  { return GLU_NURBS_ERROR4; }
			if (temp == knot [i]) { mult++; }
			else {
				if (mult > order) { return GLU_NURBS_ERROR5; }
				mult = 1; temp = knot [i];
			}
		}
		return GLU_NO_ERROR;
	}

	public int test () {
		if (order < 0)		     { return GLU_INVALID_VALUE; }
		// assume MAX_EVAL_ORDER = 30
		if (order > 30 || order < 2) { return GLU_NURBS_ERROR1;  }
		if (knot_count < order + 2)  { return GLU_NURBS_ERROR2;  }
		if (stride < 0) 	     { return GLU_NURBS_ERROR34; }
		if (knot == null)	     { return GLU_NURBS_ERROR36; }
		return knot_test ();
	}

	public void fill (int c, float k [], int s, int o) {
		knot_count = c;
		knot       = k;
		stride     = s;
		order      = o;
	}

}

