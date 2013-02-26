/*
 * @(#)nurbs_ct_surfs.java 0.1 99/11/5
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
 * nurbs_ct_surfs is one of the GLU NURBS class of JavaGL 2.1.
 *
 * @version 	0.1, 5 Nov 1999
 * @author 	Robin Bing-Yu Chen
 */

public class NURBS_Ct_Surfs extends HasGL{

	public NURBS_Ct_Surfs(GL gl) {
		super(gl);
	}

	public NURBS_Ct_Surf geom    = new NURBS_Ct_Surf (gl);
	public NURBS_Ct_Surf color   = new NURBS_Ct_Surf (gl);
	public NURBS_Ct_Surf normal  = new NURBS_Ct_Surf (gl);
	public NURBS_Ct_Surf texture = new NURBS_Ct_Surf (gl);

	public int s_bezier_cnt = 0;
	public int t_bezier_cnt = 0;

	public void draw (GLUnurbsObj nobj, int display_mode,
			NURBS_Surfaces surface, int sfactors [][],
			int tfactors [][]) {
		
		NURBS_Bz_Surface n_map_b = new NURBS_Bz_Surface(gl);
		boolean do_color, do_normal, do_texture;
		int i, j;

		n_map_b.set_property (display_mode, sfactors, tfactors,
				s_bezier_cnt, t_bezier_cnt);

		gl.glEnable (surface.geom.type);
		if (color.ctrl != null) {
			gl.glEnable (surface.color.type);
			do_color = true;
		} else {
			do_color = false;
		}
		if (normal.ctrl != null) {
			gl.glEnable (surface.normal.type);
			do_normal = true;
		} else {
			do_normal = false;
		}
		if (texture.ctrl != null) {
			gl.glEnable (surface.texture.type);
			do_texture = true;
		} else {
			do_texture = false;
		}

		for (j = 0; j < s_bezier_cnt; j++) {
			for (i = 0; i < t_bezier_cnt; i++) {
				if (!nobj.culling_test_3d (geom.offsets [j][i],
						surface.geom.s.order,
						surface.geom.t.order,
						geom.s_stride, geom.t_stride,
						surface.geom.dim)) {
					geom.draw (surface.geom, j, i);
					if (do_color)  { color.draw  (surface.color,  j,i); }
					if (do_normal) { normal.draw (surface.normal, j,i); }
					if (do_texture){ texture.draw(surface.texture,j,i); }
					n_map_b.map (j, i);
				}
			}
		}
	}

	public void augment (NURBS_Surfaces surface) {
		s_bezier_cnt = geom.s_pt_cnt / surface.geom.s.order;
		t_bezier_cnt = geom.t_pt_cnt / surface.geom.t.order;

		geom.augment (s_bezier_cnt, t_bezier_cnt, surface.geom);

		if (color.ctrl != null) {
			color.augment (s_bezier_cnt, t_bezier_cnt, surface.color);
		}
		if (normal.ctrl != null) {
			normal.augment (s_bezier_cnt, t_bezier_cnt, surface.normal);
		}
		if (texture.ctrl != null) {
			texture.augment (s_bezier_cnt, t_bezier_cnt, surface.texture);
		}
	}

	public void convert (NURBS_Knot geom_s_knot,    NURBS_Knot geom_t_knot,
			NURBS_Knot color_s_knot,   NURBS_Knot color_t_knot,
			NURBS_Knot normal_s_knot,  NURBS_Knot normal_t_knot,
			NURBS_Knot texture_s_knot, NURBS_Knot texture_t_knot,
			NURBS_Surfaces surface) {
		geom.convert (geom_s_knot, geom_t_knot, surface.geom);

		// Yes, if xxx_s_knot has been set, xxx_t_knot must be set, too.
		if (color_s_knot.unified_nknots != 0) {
			color.convert (color_s_knot, color_t_knot, surface.color);
		}
		if (normal_s_knot.unified_nknots != 0) {
			normal.convert (normal_s_knot, normal_t_knot, surface.normal);
		}
		if (texture_s_knot.unified_nknots != 0) {
			texture.convert (texture_s_knot, texture_t_knot, surface.texture);
		}
	}

}

