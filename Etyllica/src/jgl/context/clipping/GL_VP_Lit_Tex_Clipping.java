/*
 * @(#)gl_vp_lit_tex_clipping.java 0.1 01/02/16
 *
 * jGL 3-D graphics library for Java
 * Copyright (c) 2001 Robin Bing-Yu Chen (robin@is.s.u-tokyo.ac.jp)
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
 * gl_vp_lit_tex_clipping is the clipping class for viewport with texturing
 * and lighting of jGL 2.3.
 *
 * @version 	0.1, 16 Feb 2001
 * @author 	Robin Bing-Yu Chen
 */

public class GL_VP_Lit_Tex_Clipping extends GL_VP_Color_Clipping {

	protected GL_Vertex inter_point_pos (GL_Vertex v1, GL_Vertex v2, int xy) {
		GL_Vertex temp = super.inter_point_pos (v1, v2, xy);
		temp.TexCoord = inter_tex (v1, v2);
		return temp;
	}

	protected GL_Vertex inter_point_neg (GL_Vertex v1, GL_Vertex v2, int xy) {
		GL_Vertex temp = super.inter_point_neg (v1, v2, xy);
		temp.TexCoord = inter_tex (v1, v2);
		return temp;
	}

	public GL_VP_Lit_Tex_Clipping (GL_Context cc) {
		super (cc);
	}

}
