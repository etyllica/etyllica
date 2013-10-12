/*
 * @(#)gl_stipple_line_pixel.java 0.2 99/11/28
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

package jgl.context.render.pixel;

import jgl.context.GL_Context;
import jgl.context.GL_Pointer;
import jgl.context.GL_Vertex;

import jgl.context.clipping.GL_2D_Clipping;

/**
 * gl_stipple_line_pixel is the pixel class for stippled line of JavaGL 2.1.
 *
 * @version 	0.2, 28 Nov 1999
 * @author 	Robin Bing-Yu Chen
 */

public class GL_Stipple_Line_Pixel extends GL_Stipple_Pixel {

	private GL_Pointer CR;
	private GL_2D_Clipping clip;
	private float width [];
	private int repeat;
	private int step;

	/** Calculate the line width for GL_LINE, return du and dv of it. So, the
	point (x,y) will be a short line from (x+du,y-dv) to (x-du,y+dv) */
	private float [] cal_line_size (float dx, float dy) {
		float [] xy = new float [2];    /* 0 for x, 1 for y */
		float wl = glContext.Raster.LineWidth / (float)Math.sqrt (dx*dx + dy*dy);

		xy [0] = (float)(0.5 * wl * dy);
		xy [1] = (float)(0.5 * wl * dx);

		return xy;
	}

	/** Initial the stippled line parameters */
	public void init (int dx, int dy) {
		if ((glContext.Raster.LineWidth > 1) && ((dx != 0) || (dy != 0))) {
			width = cal_line_size (dx, dy);
		} else {
			width = new float [2];
			width [0] = 0;
			width [1] = 0;
		}
		repeat = glContext.Raster.LineStippleRepeat;
		step = 0;
	}

	/** Put a pixel for stippled line */
	public void put_pixel (int x, int y, int color) {
		put_pixel (x, y, 0, color);
	}

	public void put_pixel (int x, int y, float z, int color) {
		int shift = step;

		if (glContext.Raster.LineStipple) {
			repeat--;
			if (repeat == 0) {
				repeat = glContext.Raster.LineStippleRepeat;
				step++;
				if (step == 16) { step = 0; }
			}
			if (((1 << shift) & glContext.Raster.LineStipplePattern) == 0) { return; }
		}

		GL_Vertex temp[] = new GL_Vertex [2];

		temp[0] = new GL_Vertex ();
		temp[1] = new GL_Vertex ();

		temp[0].Vertex [0] = x + width[0];
		temp[0].Vertex [1] = y - width[1];
		temp[1].Vertex [0] = x - width[0];
		temp[1].Vertex [1] = y + width[1];

		temp=clip.clip_line (temp);

		if (temp == null) { return; }

		temp[0].Vertex [2] = z;
		temp[1].Vertex [2] = z;

		CR.line.draw_line (temp[0], temp[1], color);
	}

	public GL_Stipple_Line_Pixel (GL_Context cc, GL_Pointer cr) {
		super (cc);
		CR = cr;
		clip = new GL_2D_Clipping (glContext.getViewport().Width-1, glContext.getViewport().Height-1);
	}

}
