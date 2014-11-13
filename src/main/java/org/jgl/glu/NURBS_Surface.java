/*
 * @(#)nurbs_surface.java 0.1 99/10/31
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

import static org.jgl.GL.GL_MAP2_COLOR_4;
import static org.jgl.GL.GL_MAP2_INDEX;
import static org.jgl.GL.GL_MAP2_NORMAL;
import static org.jgl.GL.GL_MAP2_TEXTURE_COORD_1;
import static org.jgl.GL.GL_MAP2_TEXTURE_COORD_2;
import static org.jgl.GL.GL_MAP2_TEXTURE_COORD_3;
import static org.jgl.GL.GL_MAP2_TEXTURE_COORD_4;
import static org.jgl.GL.GL_MAP2_VERTEX_3;
import static org.jgl.GL.GL_MAP2_VERTEX_4;
import static org.jgl.GLU.GLU_NO_ERROR;


/**
 * nurbs_surface is one of the GLU NURBS class of JavaGL 2.1.
 *
 * @version 	0.1, 31 Oct 1999
 * @author 	Robin Bing-Yu Chen
 */

public class NURBS_Surface extends NURBS_Nurbs {

	public float     ctrlarray [][][];
	public NURBS_Object s = new NURBS_Object ();
	public NURBS_Object t = new NURBS_Object ();

	private void set_dim () {
		switch (type) {
		case GL_MAP2_VERTEX_4:
		case GL_MAP2_COLOR_4:
		case GL_MAP2_TEXTURE_COORD_4:
			dim = 4;
			break;
		case GL_MAP2_VERTEX_3:
		case GL_MAP2_NORMAL:
		case GL_MAP2_TEXTURE_COORD_3:
			dim = 3;
			break;
		case GL_MAP2_TEXTURE_COORD_2:
			dim = 2;
			break;
		case GL_MAP2_INDEX:
		case GL_MAP2_TEXTURE_COORD_1:
			dim = 1;
			break;
		}
	}

	public int test () {
		int err;

		err = s.test (); if (err != GLU_NO_ERROR) { return err; }
		err = t.test (); if (err != GLU_NO_ERROR) { return err; }
		return GLU_NO_ERROR;
	}

	public void fill (int sknot_count, float sknot [],
			int tknot_count, float tknot [],
			int s_stride, int t_stride,
			float surctrlarray [][][],
			int sorder, int torder, int surtype) {
		s.fill (sknot_count, sknot, s_stride, sorder);
		t.fill (tknot_count, tknot, t_stride, torder);
		ctrlarray  = surctrlarray;
		type  = surtype;
		set_dim ();
	}

}

