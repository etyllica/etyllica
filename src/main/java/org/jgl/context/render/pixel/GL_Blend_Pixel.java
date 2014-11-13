/*
 * @(#)gl_blend_pixel.java 0.1 06/11/20
 *
 * jGL 3-D graphics library for Java
 * Copyright (c) 2006 Robin Bing-Yu Chen (robin@ntu.edu.tw)
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

import org.jgl.GL;
import org.jgl.context.GL_Context;
import org.jgl.context.GL_Util;

/**
 * gl_blend_pixel is the pixel blending class of jGL 2.4.
 *
 * @version 	0.1, 20 Nov. 2006
 * @author 	Robin Bing-Yu Chen
 */

public class GL_Blend_Pixel extends GL_Render_Pixel {

	private void blend_pixel (float rst [], float tgt [], float src [], float dst [], int BlendFunc) {
		float afa;

		switch (BlendFunc) {
		case GL.GL_ZERO:
			break;
		case GL.GL_ONE:
			rst [0] += tgt [0];
			rst [1] += tgt [1];
			rst [2] += tgt [2];
			rst [3] += tgt [3];
			break;
		case GL.GL_DST_COLOR:
			rst [0] += (tgt [0] * dst [0]);
			rst [1] += (tgt [1] * dst [1]);
			rst [2] += (tgt [2] * dst [2]);
			rst [3] += (tgt [3] * dst [3]);
			break;
		case GL.GL_SRC_COLOR:
			rst [0] += (tgt [0] * src [0]);
			rst [1] += (tgt [1] * src [1]);
			rst [2] += (tgt [2] * src [2]);
			rst [3] += (tgt [3] * src [3]);
			break;
		case GL.GL_ONE_MINUS_DST_COLOR:
			rst [0] += (tgt [0] * (1.0f - dst [0]));
			rst [1] += (tgt [1] * (1.0f - dst [1]));
			rst [2] += (tgt [2] * (1.0f - dst [2]));
			rst [3] += (tgt [3] * (1.0f - dst [3]));
			break;
		case GL.GL_ONE_MINUS_SRC_COLOR:
			rst [0] += (tgt [0] * (1.0f - src [0]));
			rst [1] += (tgt [1] * (1.0f - src [1]));
			rst [2] += (tgt [2] * (1.0f - src [2]));
			rst [3] += (tgt [3] * (1.0f - src [3]));
			break;
		case GL.GL_SRC_ALPHA:
			rst [0] += (tgt [0] * src [3]);
			rst [1] += (tgt [1] * src [3]);
			rst [2] += (tgt [2] * src [3]);
			rst [3] += (tgt [3] * src [3]);
			break;
		case GL.GL_ONE_MINUS_SRC_ALPHA:
			afa = 1.0f - src [3];
			rst [0] += (tgt [0] * afa);
			rst [1] += (tgt [1] * afa);
			rst [2] += (tgt [2] * afa);
			rst [3] += (tgt [3] * afa);
			break;
		case GL.GL_DST_ALPHA:
			rst [0] += (tgt [0] * dst [3]);
			rst [1] += (tgt [1] * dst [3]);
			rst [2] += (tgt [2] * dst [3]);
			rst [3] += (tgt [3] * dst [3]);
			break;
		case GL.GL_ONE_MINUS_DST_ALPHA:
			afa = 1.0f - dst [3];
			rst [0] += (tgt [0] * afa);
			rst [1] += (tgt [1] * afa);
			rst [2] += (tgt [2] * afa);
			rst [3] += (tgt [3] * afa);
			break;
		case GL.GL_SRC_ALPHA_SATURATE:
			afa = Math.min (src [3], (1.0f - dst [3]));
			rst [0] += (tgt [0] * afa);
			rst [1] += (tgt [1] * afa);
			rst [2] += (tgt [2] * afa);
			rst [3] += tgt [3];
			break;
		default:
			break;
		}
	}

	/** Blend the pixel in the Color Buffer with the input color*/
	public void put_pixel_by_index (int index, int color) {
		float src [] = GL_Util.ItoRGBAf(color);
		float dst [] = GL_Util.ItoRGBAf(glContext.ColorBuffer.Buffer [index]);
		float rst [] = {0.0f, 0.0f, 0.0f, 0.0f};

		blend_pixel (rst, src, src, dst, glContext.ColorBuffer.BlendSrc);
		blend_pixel (rst, dst, src, dst, glContext.ColorBuffer.BlendDst);

		glContext.ColorBuffer.Buffer [index] = GL_Util.RGBtoI(GL_Util.CLAMP(rst[0],0.0f,1.0f),
				GL_Util.CLAMP(rst[1],0.0f,1.0f),
				GL_Util.CLAMP(rst[2],0.0f,1.0f));
	}

	/** Put a pixel in the Color Buffer */
	public void put_pixel (int x, int y, int color) {
		put_pixel_by_index (x+glContext.getViewport().Width*y, color);
	}

	/** Put a pixel in the Color Buffer, if the pixel is near View Point */
	public void put_pixel_by_index (int index, float z, int color) {
		if (glContext.DepthBuffer.Test (z, index)) {
			put_pixel_by_index (index, color);
			//	    glContext.ColorBuffer.Buffer [index] = color;
			//	    glContext.DepthBuffer.Buffer [index] = z;
			if (glContext.DepthBuffer.Mask) glContext.DepthBuffer.Buffer [index] = z;
		}
	}

	/** Put a pixel in the Color Buffer, if the pixel is near View Point */
	public void put_pixel (int x, int y, float z, int color) {
		int index = x+glContext.getViewport().Width*y;
		if (glContext.DepthBuffer.Test (z, index)) {
			put_pixel_by_index (index, color);
			//	    glContext.ColorBuffer.Buffer [index] = color;
			//	    glContext.DepthBuffer.Buffer [index] = z;
			if (glContext.DepthBuffer.Mask) glContext.DepthBuffer.Buffer [index] = z;
		}
	}

	public GL_Blend_Pixel (GL_Context cc) {
		super (cc);
	}

}
