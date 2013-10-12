/*
 * @(#)gl_vp_clipping.java 0.4 99/12/03
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

import org.jgl.context.GL_Context;
import org.jgl.context.GL_Vertex;

/**
 * gl_vp_clipping is the clipping class for viewport of JavaGL 2.1.
 *
 * @version 	0.4, 3 Dev 1999
 * @author 	Robin Bing-Yu Chen
 */

public class GL_VP_Clipping extends GL_Clipping {

	protected GL_Vertex inter_point_pos (GL_Vertex v1, GL_Vertex v2, int xy) {
		// point v1 is out, point v2 is in....
		float dvertex [] = new float [4];
		GL_Vertex temp = new GL_Vertex ();
		//	int yx = 1 - xy;

		dvertex[0]=v1.Vertex[0]-v2.Vertex[0];
		dvertex[1]=v1.Vertex[1]-v2.Vertex[1];
		dvertex[2]=v1.Vertex[2]-v2.Vertex[2];
		dvertex[3]=v1.Vertex[3]-v2.Vertex[3];
		t = (v2.Vertex[xy]-v2.Vertex[ 3])/(dvertex[3]-dvertex[xy]);
		temp.Vertex[3 ]=v2.Vertex[3 ]+t*dvertex[3 ];
		if (xy != 2) { temp.Vertex[2 ]=v2.Vertex[2 ]+t*dvertex[2 ]; }
		if (xy != 1) { temp.Vertex[1 ]=v2.Vertex[1 ]+t*dvertex[1 ]; }
		if (xy != 0) { temp.Vertex[0 ]=v2.Vertex[0 ]+t*dvertex[0 ]; }
		//	temp.Vertex[yx]=v2.Vertex[yx]+t*dvertex[yx];
		temp.Vertex[xy]=temp.Vertex[3 ];
		return temp;
	}

	protected GL_Vertex inter_point_neg (GL_Vertex v1, GL_Vertex v2, int xy) {
		// point v1 is out, point v2 is in....
		float dvertex [] = new float [4];
		GL_Vertex temp = new GL_Vertex ();
		//	int yx = 1 - xy;

		dvertex[0]=v1.Vertex[0]-v2.Vertex[0];
		dvertex[1]=v1.Vertex[1]-v2.Vertex[1];
		dvertex[2]=v1.Vertex[2]-v2.Vertex[2];
		dvertex[3]=v1.Vertex[3]-v2.Vertex[3];
		t = -(v2.Vertex[xy]+v2.Vertex[ 3])/(dvertex[3]+dvertex[xy]);
		temp.Vertex[3 ]=v2.Vertex[3 ]+t*dvertex[3 ];
		//	temp.Vertex[2 ]=v2.Vertex[2 ]+t*dvertex[2 ];
		if (xy != 2) { temp.Vertex[2 ]=v2.Vertex[2 ]+t*dvertex[2 ]; }
		if (xy != 1) { temp.Vertex[1 ]=v2.Vertex[1 ]+t*dvertex[1 ]; }
		if (xy != 0) { temp.Vertex[0 ]=v2.Vertex[0 ]+t*dvertex[0 ]; }
		//	temp.Vertex[yx]=v2.Vertex[yx]+t*dvertex[yx];
		temp.Vertex[xy]=-temp.Vertex[3 ];
		return temp;
	}

	protected boolean IsInside_pos (float p [], int xy) {
		if (p [xy] > p [3]) { return false; } else { return true; }
	}

	protected boolean IsInside_neg (float p [], int xy) {
		if (p [xy] < -p [3]) { return false; } else { return true; }
	}

	public GL_VP_Clipping (GL_Context cc) {
		super (cc);
	}

}
