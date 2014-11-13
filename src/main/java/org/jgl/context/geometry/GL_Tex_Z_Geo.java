/*
 * @(#)gl_tex_z_geo.java 0.2 99/12/04
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

package org.jgl.context.geometry;

import org.jgl.context.GL_Context;
import org.jgl.context.GL_Pointer;

/**
 * gl_tex_z_geo is the geometry class for texturing with depth value of JavaGL 2.1.
 *
 * @version 	0.2, 4 Dec 1999
 * @author 	Robin Bing-Yu Chen
 */

public class GL_Tex_Z_Geo extends GL_Tex_Geo {

	protected void draw_point (float p [], int i) {
		CR.pixel.put_pixel ((int)(p[0]+(float)0.5), (int)(p[1]+(float)0.5), p[2],
				TexCoord [i][0], TexCoord[i][1], TexCoord[i][2]);
	}

	public GL_Tex_Z_Geo (GL_Context cc, GL_Pointer cr) {
		super (cc, cr);
	}

}
