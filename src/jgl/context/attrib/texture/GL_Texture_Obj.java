/*
 * @(#)gl_texture_obj.java 0.4 03/05/16
 *
 * jGL 3-D graphics library for Java
 * Copyright (c) 1999-2003 Robin Bing-Yu Chen (robin@nis-lab.is.s.u-tokyo.ac.jp)
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

package jgl.context.attrib.texture;

import jgl.GL;

import jgl.context.GL_Util;
import jgl.context.GL_Image;

/**
 * gl_texture_obj is the texturing x-D class of jGL 2.4.
 *
 * @version 	0.4, 16 May 2003
 * @author 	Robin Bing-Yu Chen
 */

public class GL_Texture_Obj {

    /** GL_TEXTURE_x: Ture if x-D texturing enabled */
//    public boolean Enable = false;

    /** GL_TEXTURE: x-D texture image at level of detail i */
    public GL_Texture_Img Image[] = new GL_Texture_Img[10]; //MAX_TEXTURE_LEVELS=10

    /** GL_TEXTURE_BORDER_COLOR: Texture border color */
    public int BorderColor[] = {0, 0, 0, 0};

    /** GL_TEXTURE_MIN_FILTER: Texture minification function */
    public int MinFilter = GL.GL_NEAREST_MIPMAP_LINEAR;

    /** GL_TEXTURE_MAG_FILTER: Texture magnification function */
    public int MagFilter = GL.GL_LINEAR;

    /** GL_TEXTURE_WRAP_x: Texture wrap mode (x is S or T or R) */
    public int WrapS = GL.GL_REPEAT;
    public int WrapT = GL.GL_REPEAT;
    public int WrapR = GL.GL_REPEAT;

    /****** will delete the following ******/
    public int RefCount;			/* reference count */
    public int Name;
    public int Dimensions;			/* 1 or 2 */
    public float Priority;			/* in [0,1] */

    public void set_tex (int pname, float params []) {
	switch (pname) {
	    case GL.GL_TEXTURE_MIN_FILTER: MinFilter = (int)params[0]; break;
	    case GL.GL_TEXTURE_MAG_FILTER: MagFilter = (int)params[0]; break;
	    case GL.GL_TEXTURE_WRAP_S:     WrapS     = (int)params[0]; break;
	    case GL.GL_TEXTURE_WRAP_T:     WrapT     = (int)params[0]; break;
	    case GL.GL_TEXTURE_WRAP_R:     WrapR     = (int)params[0]; break;
	    case GL.GL_TEXTURE_BORDER_COLOR:
		BorderColor[0] = GL_Util.FtoI (params[0]);
		BorderColor[1] = GL_Util.FtoI (params[1]);
		BorderColor[2] = GL_Util.FtoI (params[2]);
		BorderColor[3] = GL_Util.FtoI (params[3]);
		break;
	    default: break;
	}
    }

    public void set_tex_image (int level, int border, GL_Image img) {
	Image [level] = new GL_Texture_Img (border, img);
    }

    public GL_Image get_tex_image (int level) {
	return Image [level].Image;
    }

}
