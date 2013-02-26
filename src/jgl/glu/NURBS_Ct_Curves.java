/*
 * @(#)nurbs_ct_curvs.java 0.1 99/11/11
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

package jgl.glu;

import jgl.GL;
import jgl.has.HasGL;


/**
 * nurbs_ct_curvs is one of the GLU NURBS class of JavaGL 2.1.
 *
 * @version 	0.1, 11 Nov 1999
 * @author 	Robin Bing-Yu Chen
 */

public class NURBS_Ct_Curves extends HasGL{

	public NURBS_Ct_Curves(GL gl) {
		super(gl);
	}

	public NURBS_Ct_Curve geom    = new NURBS_Ct_Curve (gl);
	public NURBS_Ct_Curve color   = new NURBS_Ct_Curve (gl);
	public NURBS_Ct_Curve normal  = new NURBS_Ct_Curve (gl);
	public NURBS_Ct_Curve texture = new NURBS_Ct_Curve (gl);

	public int bezier_cnt = 0;

	public void draw (GLUnurbsObj nobj, NURBS_Curves curve, int factors []) {
		NURBS_Bz_Curve n_map_b = new NURBS_Bz_Curve (gl);
		boolean do_color, do_normal, do_texture;
		int i;

		n_map_b.set_property (factors, bezier_cnt);

		gl.glEnable (curve.geom.type);
		if (color.ctrl != null) {
			gl.glEnable (curve.color.type);
			do_color = true;
		} else {
			do_color = false;
		}
		if (normal.ctrl != null) {
			gl.glEnable (curve.normal.type);
			do_normal = true;
		} else {
			do_normal = false;
		}
		if (texture.ctrl != null) {
			gl.glEnable (curve.texture.type);
			do_texture = true;
		} else {
			do_texture = false;
		}

		for (i = 0; i < bezier_cnt; i++) {
			if (!nobj.culling_test_2d (geom.offsets [i],
					curve.geom.c.order,
					geom.stride, curve.geom.dim)) {
				geom.draw (curve.geom, i);
				if (do_color)  { color.draw  (curve.color,  i); }
				if (do_normal) { normal.draw (curve.normal, i); }
				if (do_texture){ texture.draw(curve.texture,i); }
				n_map_b.map (i);
			}
		}
	}

	public void augment (NURBS_Curves curve) {
		bezier_cnt = geom.pt_cnt / curve.geom.c.order;

		geom.augment (bezier_cnt, curve.geom);

		if (color.ctrl != null) {
			color.augment (bezier_cnt, curve.color);
		}
		if (normal.ctrl != null) {
			normal.augment (bezier_cnt, curve.normal);
		}
		if (texture.ctrl != null) {
			texture.augment (bezier_cnt, curve.texture);
		}
	}

	public void convert (NURBS_Knot geom_knot,   NURBS_Knot color_knot,   
			NURBS_Knot normal_knot, NURBS_Knot texture_knot, 
			NURBS_Curves curve) {
		geom.convert (geom_knot, curve.geom);

		if (color_knot.unified_nknots != 0) {
			color.convert (color_knot, curve.color);
		}
		if (normal_knot.unified_nknots != 0) {
			normal.convert (normal_knot, curve.normal);
		}
		if (texture_knot.unified_nknots != 0) {
			texture.convert (texture_knot, curve.texture);
		}
	}

}

