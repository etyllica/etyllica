/*
 * @(#)nurbs_curves.java 0.1 99/11/5
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

package org.jgl.glu;

import org.jgl.GLU;

/**
 * nurbs_curves is one of the GLU NURBS class of JavaGL 2.1.
 *
 * @version 	0.1, 5 Nov 1999
 * @author 	Robin Bing-Yu Chen
 */

public class NURBS_Curves {

    public NURBS_Curve geom    = new NURBS_Curve ();
    public NURBS_Curve color   = new NURBS_Curve ();
    public NURBS_Curve normal  = new NURBS_Curve ();
    public NURBS_Curve texture = new NURBS_Curve ();

    public int fill_knot (NURBS_Knot geom_knot,   NURBS_Knot color_knot,  
			  NURBS_Knot normal_knot, NURBS_Knot texture_knot){
	int err;

        err = geom_knot.fill (geom.c);
        if (err != GLU.GLU_NO_ERROR) { return err; }

        if (color.type != GLU.GLU_INVALID_ENUM) {
            err = color_knot.fill (color.c);
            if (err != GLU.GLU_NO_ERROR) { return err; }
        }

	if (normal.type != GLU.GLU_INVALID_ENUM) {
            err = normal_knot.fill (normal.c);
            if (err != GLU.GLU_NO_ERROR) { return err; }
        }

        if (texture.type != GLU.GLU_INVALID_ENUM) {
            err = texture_knot.fill (texture.c);
            if (err != GLU.GLU_NO_ERROR) { return err; }
        }

        return GLU.GLU_NO_ERROR;
    }

    public int test () {
        int err = geom.test ();
        if (err != GLU.GLU_NO_ERROR) { return (err); }
        if (color.type != GLU.GLU_INVALID_ENUM) {
            err = color.test ();
            if (err != GLU.GLU_NO_ERROR) { return (err); }
	}
        if (normal.type != GLU.GLU_INVALID_ENUM) {
            err = normal.test ();
            if (err != GLU.GLU_NO_ERROR) { return (err); }
        }
        if (texture.type != GLU.GLU_INVALID_ENUM) {
            err = texture.test ();
            if (err != GLU.GLU_NO_ERROR) { return (err); }
        }
        return GLU.GLU_NO_ERROR;
    }

}
