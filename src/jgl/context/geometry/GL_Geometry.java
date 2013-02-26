/*
 * @(#)gl_geometry.java 0.5 02/12/17
 *
 * jGL 3-D graphics library for Java
 * Copyright (c) 1996-2002 Robin Bing-Yu Chen (robin@is.s.u-tokyo.ac.jp)
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

import jgl.GL;

import jgl.context.GL_Context;
import jgl.context.GL_Pointer;
import jgl.context.GL_Vertex;
import jgl.context.GL_Polygon;

/**
 * gl_geometry is the basic geometry class of jGL 2.4.
 *
 * @version 	0.5, 17 Dec. 2002
 * @author 	Robin Bing-Yu Chen
 */

public class GL_Geometry {

	protected GL_Context CC;
	protected GL_Pointer CR;

	/** Data Members */
	private float VertexArray [][];
	private int VertexIndex;
	protected int VertexSize = 0;

	protected boolean LineReset;

	protected void VertexTransformation (GL_Polygon p) {
		int i;
		for (i = 0; i < p.n; i++) {
			p.Polygon[i].Vertex = CC.VertexTransformation(p.Polygon[i].Vertex);
		}
	}

	protected void PerspectiveDivision (GL_Polygon p) {
		int i;
		for (i = 0; i < p.n; i++) {
			p.Polygon[i].Vertex = CC.PerspectiveDivision(p.Polygon[i].Vertex);
		}
	}

	protected void draw_point (float p [], int i) {
		CR.pixel.put_pixel ((int)(p[0]+(float)0.5), (int)(p[1]+(float)0.5),
				CC.ColorTransformation ());
	}

	private void draw_point (int i) {
		float temp [];

		//	if (CR.clipping.IsInside (VertexArray [i])) {
		if (CR.clipping != null && !CR.clipping.IsInside (VertexArray [i])) {
			return;
		}
		temp = CC.VertexTransformation (VertexArray [i]);
		if (CR.vp_clipping.IsInside (temp)) {
			temp = CC.PerspectiveDivision (temp);
			draw_point (temp, i);
		}
	}

	protected GL_Vertex [] pack_line (int i, int j) {
		GL_Vertex temp[] = new GL_Vertex [2];
		temp [0] = new GL_Vertex ();
		temp [1] = new GL_Vertex ();
		temp [0].Vertex = VertexArray [i];
		temp [1].Vertex = VertexArray [j];
		return temp;
	}

	private void draw_line (GL_Vertex v[]) {
		CR.render.draw_line (v[0], v[1], CC.ColorTransformation ());
	}

	private void draw_line (int i, int j) {
		GL_Vertex temp[] = pack_line (i, j);
		//	temp=CR.clipping.clip_line(temp);
		if (CR.clipping != null) { temp=CR.clipping.clip_line(temp); }
		if (temp == null) { return; }
		temp[0].Vertex=CC.VertexTransformation(temp[0].Vertex);
		temp[1].Vertex=CC.VertexTransformation(temp[1].Vertex);
		temp=CR.vp_clipping.clip_line(temp);
		if (temp == null) { return; }
		temp[0].Vertex=CC.PerspectiveDivision(temp[0].Vertex);
		temp[1].Vertex=CC.PerspectiveDivision(temp[1].Vertex);
		//	if (CC.RenderMode == GL.GL_FEEDBACK) {
		//	    CC.Feedbacker.write_feedback_token (GL.GL_LINE_TOKEN);
		//	}
		CR.render.set_pixel (CR.line_pixel);
		draw_line (temp);
	}

	protected GL_Polygon pack_polygon (int size) {
		GL_Polygon tpoly = new GL_Polygon ();
		tpoly.n = size;
		tpoly.Polygon = new GL_Vertex [size];
		for (int i = 0; i < size; i++) {
			tpoly.Polygon [i] = new GL_Vertex ();
			tpoly.Polygon [i].Vertex = VertexArray [i];
		}
		return tpoly;
	}

	private void draw_polygon (GL_Polygon p) {
		CR.render.draw_polygon (p, CC.ColorTransformation ());
	}

	private void draw_polygon (int size) {
		GL_Polygon tpoly = pack_polygon (size);
		//	tpoly=CR.clipping.clip_polygon(tpoly);
		if (CR.clipping != null) { tpoly=CR.clipping.clip_polygon(tpoly); }
		VertexTransformation(tpoly);
		tpoly=CR.vp_clipping.clip_polygon(tpoly);
		PerspectiveDivision(tpoly);
		CR.render.set_pixel (CR.poly_pixel);
		draw_polygon (tpoly);
	}

	protected void set_vertex (int i) {
		CC.EyeCoord = CC.ModelViewTransformation (CC.Current.Vertex);
		VertexArray [i] = CC.EyeCoord;
	}

	protected void copy_vertex (int s, int d) {
		VertexArray [d] = VertexArray [s];
	}

	protected void extend_array () {
		VertexSize += 5;
		float tempArray [][] = new float [VertexSize][4];
		System.arraycopy (VertexArray, 0, tempArray, 0, VertexSize-5); 
		VertexArray = tempArray;
	}

	public void gl_begin () {
		VertexIndex = 0;
		switch (CC.Mode) {
		case GL.GL_POINTS:
			VertexSize = 1;
			break;
		case GL.GL_LINES:
		case GL.GL_LINE_STRIP:
			VertexSize = 2;
			break;
		case GL.GL_LINE_LOOP:
		case GL.GL_TRIANGLES:
		case GL.GL_TRIANGLE_STRIP:
		case GL.GL_TRIANGLE_FAN:
			VertexSize = 3;
			break;
		case GL.GL_QUADS:
		case GL.GL_QUAD_STRIP:
			VertexSize = 4;
			break;
		case GL.GL_POLYGON:
			VertexSize = 5;
			break;
		}
		VertexArray = new float [VertexSize][4];
	}

	public void gl_end () {
		switch (CC.Mode) {
		case GL.GL_LINE_LOOP:
			if (VertexIndex == 2) {
				draw_line (1, 0);
			}
			break;
		case GL.GL_POLYGON:
			if (VertexIndex < 3) {
				switch (VertexIndex) {
				case 0: break;
				case 1:
					draw_point (0);
					break;
				case 2:
					draw_line (1, 0);
					break;
				}
				break;
			}
			draw_polygon (VertexIndex);
			break;
		default:
			break;
		}
	}

	public void gl_vertex () {
		switch (CC.Mode) {
		case GL.GL_POINTS:
			set_vertex (0);
			draw_point (0);
			break;
		case GL.GL_LINES:
			if (VertexIndex == 1) {
				set_vertex (1);
				draw_line (0, 1);
				VertexIndex = 0;
			} else { // VertexIndex == 0
					set_vertex (0);
			VertexIndex = 1;
			}
			break;
		case GL.GL_LINE_STRIP:
			if (VertexIndex == 1) {
				set_vertex (1);
				draw_line (0, 1);
				copy_vertex (1, 0);
			} else { // VertexIndex == 0
				set_vertex (0);
				VertexIndex = 1;
				LineReset = true;
			}
			break;
		case GL.GL_LINE_LOOP:
			if (VertexIndex == 2) {
				set_vertex (2);
				draw_line (1, 2);
				copy_vertex (2, 1);
			} else { // VertexIndex == 0
				set_vertex (0);
				copy_vertex (0, 1);
				VertexIndex = 2;
				LineReset = true;
			}
			break;
		case GL.GL_TRIANGLES:
			if (VertexIndex == 2) {
				set_vertex (2);
				draw_polygon (3);
				VertexIndex = 0;
			} else { // VertexIndex == 0 or 1
				set_vertex (VertexIndex++);
			}
			break;
		case GL.GL_TRIANGLE_STRIP:
			if ((VertexIndex == 0) || (VertexIndex == 1)) {
				set_vertex (VertexIndex++);
			} else { // VertexIndex == 2
				set_vertex (2);
				draw_polygon (3);
				if (VertexIndex == 2) {
					copy_vertex (2, 0);
					VertexIndex = 3;
				} else { // VertexIndex == 3 for order control
					copy_vertex (2, 1);
					VertexIndex = 2;
				}
			}
			break;
		case GL.GL_TRIANGLE_FAN:
			if (VertexIndex == 2) {
				set_vertex (2);
				draw_polygon (3);
				copy_vertex (2, 1);
			} else { // VertexIndex == 0 or 1
				set_vertex (VertexIndex++);
			}
			break;
		case GL.GL_QUADS:
			if (VertexIndex == 3) {
				set_vertex (3);
				draw_polygon (4);
				VertexIndex = 0;
			} else {
				set_vertex (VertexIndex++);
			}
			break;
		case GL.GL_QUAD_STRIP:
			// NOTICE the sequence....
			if (VertexIndex == 3) {
				set_vertex (2);
				draw_polygon (4);
				copy_vertex (3, 0);
				copy_vertex (2, 1);
				VertexIndex = 2;
			} else if (VertexIndex == 2) {
				set_vertex (3);
				VertexIndex = 3;
			} else { // VertexIndex == 0 or 1
				set_vertex (VertexIndex++);
			}
			break;
		case GL.GL_POLYGON:
			if (VertexIndex == VertexSize) {
				extend_array ();
			}
			set_vertex (VertexIndex++);
			break;
		}
	}

	public GL_Geometry (GL_Context cc, GL_Pointer cr) {
		CC = cc;
		CR = cr;
	}

}
