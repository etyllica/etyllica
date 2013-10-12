/*
 * @(#)gl_cp_tex_clipping.java 0.2 99/12/03
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

package jgl.context.clipping;

import jgl.context.GL_Context;
import jgl.context.GL_Vertex;

/**
 * gl_cp_tex_clipping is the clipping class for clipping plane with
 * texturing of JavaGL 2.1.
 *
 * @version 	0.2, 3 Dev 1999
 * @author 	Robin Bing-Yu Chen
 */

public class GL_CP_Tex_Clipping extends GL_CP_Clipping {

	protected GL_Vertex inter_point (GL_Vertex v1, GL_Vertex v2, int i, int j) {
		GL_Vertex temp = super.inter_point (v1, v2, i, j);
		temp.TexCoord = inter_tex (v1, v2);
		return temp;
	}

	public GL_CP_Tex_Clipping (GL_Context  cc) {
		super (cc);
		//	nf_clipping = new gl_nf_tex_clipping (CC);
	}

}
