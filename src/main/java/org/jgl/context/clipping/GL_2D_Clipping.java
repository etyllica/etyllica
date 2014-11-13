/*
 * @(#)gl_2d_clipping.java 0.4 99/12/03
 *
 * jGL 3-D graphics library for Java
 * Copyright (c) 1997-1999 Robin Bing-Yu Chen (robin@is.s.u-tokyo.ac.jp)
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

package org.jgl.context.clipping;

import org.jgl.context.GL_Polygon;
import org.jgl.context.GL_Vertex;

/**
 * gl_2d_clipping is the clipping class for frame buffer of JavaGL 2.1.
 *
 * @version 	0.4, 3 Dev 1999
 * @author 	Robin Bing-Yu Chen
 */

public class GL_2D_Clipping extends GL_Clipping {

	private int bound [];

	private GL_Vertex my_inter_point(GL_Vertex v1, GL_Vertex v2, int xy, int v){
		// point v1 is out, point v2 is in....
		float dvertex [] = new float [4];
		GL_Vertex temp = new GL_Vertex ();

		dvertex[0]=v1.Vertex[0]-v2.Vertex[0];
		dvertex[1]=v1.Vertex[1]-v2.Vertex[1];
		dvertex[2]=v1.Vertex[2]-v2.Vertex[2];
		dvertex[3]=v1.Vertex[3]-v2.Vertex[3];
		if (dvertex[xy] == 0) { t = 0; }
		else { t = ((float)v - v2.Vertex[xy])/dvertex[xy]; }
		temp.Vertex[0]=v2.Vertex[0]+t*dvertex[0];
		temp.Vertex[1]=v2.Vertex[1]+t*dvertex[1];
		temp.Vertex[2]=v2.Vertex[2]+t*dvertex[2];
		temp.Vertex[3]=v2.Vertex[3]+t*dvertex[3];
		return temp;
	}

	protected GL_Vertex inter_point_pos (GL_Vertex v1, GL_Vertex v2, int xy) {
		return my_inter_point (v1, v2, xy, bound[xy]);
	}

	protected GL_Vertex inter_point_neg (GL_Vertex v1, GL_Vertex v2, int xy) {
		return my_inter_point (v1, v2, xy, 0);
	}

	protected boolean IsInside_pos (float p [], int xy) {
		if (p [xy] > bound [xy]) { return false; }
		else { return true; }
	}

	protected boolean IsInside_neg (float p [], int xy) {
		if (p [xy] < 0) { return false; }
		else { return true; }
	}

	public boolean IsInside (float p []) {
		if (!(IsInside_neg (p, 0))) { return false; }
		if (!(IsInside_neg (p, 1))) { return false; }
		if (!(IsInside_pos (p, 0))) { return false; }
		if (!(IsInside_pos (p, 1))) { return false; }
		return true;
	}

	public GL_Vertex[] clip_line (GL_Vertex temp []) {
		for (int i = 0; i < 2; i++) {
			temp = clip_line (temp, i);
			if (temp == null) { return null; }
		}
		return temp;
	}

	public GL_Polygon clip_polygon (GL_Polygon inlist) {
		for (int i = 0; i < 2; i++) { inlist = clip_polygon (inlist, i); }
		return inlist;
	}

	public GL_2D_Clipping (int w, int h) {
		bound = new int [2];
		bound [0] = w;
		bound [1] = h;
	}

}
