/*
 * @(#)gl_render_point.java 0.2 99/11/28
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

package org.jgl.context.render.pixel;

import org.jgl.context.GL_Context;

/**
 * gl_render_point is the point rendering class of JavaGL 2.1.
 *
 * @version 	0.2, 28 Nov 1999
 * @author 	Robin Bing-Yu Chen
 */

public class GL_Render_Point extends GL_Render_Pixel {

	/** Calculate the point size for GL_POINTS, return a bounding box of it */
	private int [] cal_point_size (int x, int y) {
		int [] xy = new int [4];	/* 0,1 for x, 2,3 for y */
		int size = (int)(glContext.Raster.PointSize + (float)0.5);

		xy [0] = x - (size >> 1);
		xy [2] = y - (size >> 1);
		xy [1] = xy [0] + size;
		xy [3] = xy [2] + size;

		/* maybe call clipping functions for checking */
		if (xy [0] < 0) { xy [0] = 0; }
		if (xy [2] < 0) { xy [2] = 0; }
		if (xy [1] >= glContext.getViewport().Width)  { xy [1] = glContext.getViewport().Width - 1; }
		if (xy [3] >= glContext.getViewport().Height) { xy [3] = glContext.getViewport().Height - 1; }

		return xy;
	}

	/** Draw a point for GL_POINTS with size other than 1 */
	public void put_pixel (int x, int y, int color) {
		int xy [] = cal_point_size (x, y);
		int i, j;

		for (i = xy [0]; i < xy [1]; i++) {
			for (j = xy [2]; j < xy [3]; j++) {
				super.put_pixel (i, j, color);
			}
		}
	}

	/** Draw a point for GL_POINTS with size other than 1 */
	public void put_pixel (int x, int y, float z, int color) {
		int xy [] = cal_point_size (x, y);
		int i, j;

		for (i = xy [0]; i < xy [1]; i++) {
			for (j = xy [2]; j < xy [3]; j++) {
				super.put_pixel (i, j, z, color);
			}
		}
	}

	/** Draw a texturing point for GL_POINTS with size other than 1 */
	public void put_pixel (int x, int y, float s, float t, float r) {
		put_pixel (x, y, glContext.Texture.tex_vertex (s, t, r));
	}

	/** Draw a texturing point for GL_POINTS with size other than 1 */
	public void put_pixel (int x, int y, float z, float s, float t, float r) {
		put_pixel (x, y, z, glContext.Texture.tex_vertex (s, t, r));
	}

	/** Draw a mip-mapping point for GL_POINTS with size other than 1 */
	public void put_pixel (int x, int y, float w, float s, float t, float r,
			float dsdx, float dsdy, float dtdx, float dtdy, float drdx, float drdy) {
		put_pixel (x, y, glContext.Texture.tex_vertex(s, t, r, w, dsdx, dsdy, dtdx, dtdy, drdx, drdy));
	}

	/** Draw a mip-mapping point for GL_POINTS with size other than 1 */
	public void put_pixel (int x, int y, float z, float w, float s, float t, float r,
			float dsdx, float dsdy, float dtdx, float dtdy, float drdx, float drdy) {
		put_pixel (x, y, z, glContext.Texture.tex_vertex(s, t, r, w, dsdx, dsdy, dtdx, dtdy, drdx, drdy));
	}

	public GL_Render_Point (GL_Context cc) {
		super (cc);
	}

}
