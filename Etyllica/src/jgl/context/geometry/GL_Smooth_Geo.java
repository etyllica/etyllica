/*
 * @(#)gl_smooth_geo.java 0.6 06/11/24
 *
 * jGL 3-D graphics library for Java
 * Copyright (c) 1996-2006 Robin Bing-Yu Chen (robin@ntu.edu.tw)
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

package jgl.context.geometry;

import jgl.context.GL_Context;
import jgl.context.GL_Pointer;
import jgl.context.GL_Vertex;
import jgl.context.GL_Polygon;
import jgl.context.GL_Util;

/**
 * gl_smooth_geo is the geometry class for smooth shading of jGL 2.4.
 *
 * @version 	0.6, 24 Nov 2006
 * @author 	Robin Bing-Yu Chen
 */

public class GL_Smooth_Geo extends GL_Geometry {

	/** Data Members */
	protected int VertexColor [];

	protected void draw_point (float p [], int i) {
		CR.pixel.put_pixel ((int)(p[0]+(float)0.5), (int)(p[1]+(float)0.5),
				VertexColor [i]);
	}

	protected GL_Vertex [] pack_line (int i, int j) {
		GL_Vertex temp [] = super.pack_line (i, j);
		//	temp [0].Color = new int [4];
		//	temp [1].Color = new int [4];
		//	temp [0].Color [0] = (VertexColor [i] & 0x00ff0000) >> 16;
		//	temp [0].Color [1] = (VertexColor [i] & 0x0000ff00) >> 8;
		//	temp [0].Color [2] =  VertexColor [i] & 0x000000ff;
		//	temp [1].Color [0] = (VertexColor [j] & 0x00ff0000) >> 16;
		//	temp [1].Color [1] = (VertexColor [j] & 0x0000ff00) >> 8;
		//	temp [1].Color [2] =  VertexColor [j] & 0x000000ff;
		temp [0].Color = GL_Util.ItoRGBA (VertexColor[i]);
		temp [1].Color = GL_Util.ItoRGBA (VertexColor[j]);
		return temp;
	}

	private void draw_line (GL_Vertex v[]) {
		CR.render.draw_line (v[0], v[1]);
	}

	protected GL_Polygon pack_polygon (int size) {
		GL_Polygon tpoly = super.pack_polygon (size);
		for (int i = 0; i < size; i++) {
			//	    tpoly.Polygon [i].Color = new int [3];
			//	    tpoly.Polygon [i].Color [0] = (VertexColor [i] & 0x00ff0000) >> 16;
			//	    tpoly.Polygon [i].Color [1] = (VertexColor [i] & 0x0000ff00) >> 8;
			//	    tpoly.Polygon [i].Color [2] =  VertexColor [i] & 0x000000ff;
			tpoly.Polygon [i].Color = GL_Util.ItoRGBA (VertexColor[i]);
		}
		return tpoly;
	}

	private void draw_polygon (GL_Polygon p) {
		CR.render.draw_polygon (p);
	}

	protected void set_vertex (int i) {
		super.set_vertex (i);
		VertexColor [i] = CC.ColorTransformation();
	}

	protected void copy_vertex (int s, int d) {
		super.copy_vertex (s, d);
		VertexColor [d] = VertexColor [s];
	}

	protected void extend_array () {
		super.extend_array ();
		int tempColor [] = new int [VertexSize];
		System.arraycopy (VertexColor, 0, tempColor, 0, VertexSize-5);
		VertexColor = tempColor;
	}

	public void gl_begin () {
		super.gl_begin ();
		VertexColor = new int [VertexSize];
	}

	public GL_Smooth_Geo (GL_Context cc, GL_Pointer cr) {
		super (cc, cr);
	}

}
