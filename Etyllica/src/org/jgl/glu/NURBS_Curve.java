/*
 * @(#)nurbs_curve.java 0.1 99/10/31
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

//import jgl.GL;

//import jgl.GLU;
import static org.jgl.GL.GL_MAP1_COLOR_4;
import static org.jgl.GL.GL_MAP1_INDEX;
import static org.jgl.GL.GL_MAP1_NORMAL;
import static org.jgl.GL.GL_MAP1_TEXTURE_COORD_1;
import static org.jgl.GL.GL_MAP1_TEXTURE_COORD_2;
import static org.jgl.GL.GL_MAP1_TEXTURE_COORD_3;
import static org.jgl.GL.GL_MAP1_TEXTURE_COORD_4;
import static org.jgl.GL.GL_MAP1_VERTEX_3;
import static org.jgl.GL.GL_MAP1_VERTEX_4;
import static org.jgl.GLU.GLU_NO_ERROR;

/**
 * nurbs_curve is one of the GLU NURBS class of JavaGL 2.1.
 *
 * @version 	0.1, 31 Oct 1999
 * @author 	Robin Bing-Yu Chen
 */

public class NURBS_Curve extends NURBS_Nurbs {

	public float     ctrlarray [][];
	public NURBS_Object c = new NURBS_Object ();

	private void set_dim () {
		switch (type) {
		case GL_MAP1_VERTEX_4:
		case GL_MAP1_COLOR_4:
		case GL_MAP1_TEXTURE_COORD_4:
			dim = 4;
			break;
		case GL_MAP1_VERTEX_3:
		case GL_MAP1_NORMAL:
		case GL_MAP1_TEXTURE_COORD_3:
			dim = 3;
			break;
		case GL_MAP1_TEXTURE_COORD_2:
			dim = 2;
			break;
		case GL_MAP1_INDEX:
		case GL_MAP1_TEXTURE_COORD_1:
			dim = 1;
			break;
		}
	}

	public int test () {
		int err = c.test (); if (err != GLU_NO_ERROR) { return err; }
		return GLU_NO_ERROR;
	}

	public void fill (int knot_count, float knot [],
			int stride, 
			float surctrlarray [][],
			int order, int surtype) {
		c.fill (knot_count, knot, stride, order);
		ctrlarray  = surctrlarray;
		type  = surtype;
		set_dim ();
	}

}


