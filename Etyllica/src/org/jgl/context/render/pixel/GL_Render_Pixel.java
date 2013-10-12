/*
 * @(#)gl_render_pixel.java 0.6 03/05/15
 *
 * jGL 3-D graphics library for Java
 * Copyright (c) 1996-2003 Robin Bing-Yu Chen (robin@nis-lab.is.s.u-tokyo.ac.jp)
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

import org.jgl.context.GL_Context;
import org.jgl.context.GL_Util;

/**
 * gl_render_pixel is the basic pixel rendering class of jGL 2.3.
 *
 * @version 	0.6, 15 May. 2003
 * @author 	Robin Bing-Yu Chen
 */

public class GL_Render_Pixel {

	protected GL_Context glContext;

	/** Put a pixel in the Color Buffer */
	public void put_pixel_by_index (int index, int color) {
		glContext.ColorBuffer.Buffer [index] = color;
		//	if (glContext.ColorBuffer.ColorMask != 0xffffffff) {
		//	    glContext.ColorBuffer.Buffer [index] =
		//		((color & glContext.ColorBuffer.ColorMask) |
		//		 (glContext.ColorBuffer.Buffer [index] & ~glContext.ColorBuffer.ColorMask));
		//	} else glContext.ColorBuffer.Buffer [index] = color;
	}

	/** Put a pixel in the Color Buffer */
	public void put_pixel (int x, int y, int color) {
		glContext.ColorBuffer.Buffer [x+glContext.getViewport().Width*y] = color;
		//	put_pixel_by_index (x+glContext.getViewport().Width*y, color);
	}

	/** Convert the color array to call real put_pixel */
	public void put_pixel_by_index (int index, int c[]) {
		//	put_pixel_by_index (index, 0xff000000 | c[0] << 16 | c[1] << 8 | c[2]);
		//	put_pixel_by_index (index, gl_util.RGBtoI(c[0], c[1], c[2]));
		put_pixel_by_index (index, GL_Util.RGBAtoI(c[0], c[1], c[2], c[3]));
	}

	/** Convert the color array to call real put_pixel */
	public void put_pixel (int x, int y, int c[]) {
		//	put_pixel (x, y, 0xff000000 | c[0] << 16 | c[1] << 8 | c[2]);
		//	put_pixel (x, y, gl_util.RGBtoI(c[0], c[1], c[2]));
		put_pixel (x, y, GL_Util.RGBAtoI(c[0], c[1], c[2], c[3]));
	}

	/** Put a pixel in the Color Buffer, if the pixel is near View Point */
	public void put_pixel_by_index (int index, float z, int color) {
		if (glContext.DepthBuffer.Test (z, index)) {
			glContext.ColorBuffer.Buffer [index] = color;
			//	    glContext.DepthBuffer.Buffer [index] = z;
			//	    put_pixel_by_index (index, color);
			if (glContext.DepthBuffer.Mask) glContext.DepthBuffer.Buffer [index] = z;
		}
	}

	/** Put a pixel in the Color Buffer, if the pixel is near View Point */
	public void put_pixel (int x, int y, float z, int color) {
		//	put_pixel_by_index (x+glContext.getViewport().Width*y, z, color);
		int index = x+glContext.getViewport().Width*y;
		if (glContext.DepthBuffer.Test (z, index)) {
			glContext.ColorBuffer.Buffer [index] = color;
			//	    glContext.DepthBuffer.Buffer [index] = z;
			if (glContext.DepthBuffer.Mask) glContext.DepthBuffer.Buffer [index] = z;
		}
	}

	/** Convert the color array to call real put_pixel */
	public void put_pixel_by_index (int index, float z, int c[]) {
		//	put_pixel_by_index (index, z, 0xff000000 | c[0] << 16 | c[1] << 8 | c[2]);
		//	put_pixel_by_index (index, z, gl_util.RGBtoI(c[0], c[1], c[2]));
		put_pixel_by_index (index, z, GL_Util.RGBAtoI(c[0], c[1], c[2], c[3]));
	}

	/** Convert the color array to call real put_pixel */
	public void put_pixel (int x, int y, float z, int c[]) {
		//	put_pixel (x, y, z, 0xff000000 | c[0] << 16 | c[1] << 8 | c[2]);
		//	put_pixel (x, y, z, gl_util.RGBtoI(c[0], c[1], c[2]));
		put_pixel (x, y, z, GL_Util.RGBAtoI(c[0], c[1], c[2], c[3]));
	}

	/** Put a texturing pixel in the Color Buffer */
	public void put_pixel_by_index (int index, float s, float t, float r) {
		put_pixel_by_index (index, glContext.Texture.tex_vertex (s, t, r));
	}

	/** Put a texturing pixel in the Color Buffer */
	public void put_pixel (int x, int y, float s, float t, float r) {
		put_pixel_by_index (x+glContext.getViewport().Width*y, s, t, r);
		//	put_pixel (x, y, glContext.Texture.tex_vertex (s, t));
	}

	/** Put a texturing pixel in the Color Buffer with depth value */
	public void put_pixel_by_index (int index, float z, float s, float t, float r) {
		put_pixel_by_index (index, z, glContext.Texture.tex_vertex (s, t, r));
	}

	/** Put a texturing pixel in the Color Buffer with depth value */
	public void put_pixel (int x, int y, float z, float s, float t, float r) {
		put_pixel_by_index (x+glContext.getViewport().Width*y, z, s, t, r);
		//	put_pixel (x, y, z, glContext.Texture.tex_vertex (s, t));
	}

	/** Put a mip-mapped pixel in the Color Buffer */
	public void put_pixel_by_index (int  index, float w, float s, float t, float r,
			float dsdx, float dsdy,
			float dtdx, float dtdy,
			float drdx, float drdy) {
		put_pixel_by_index (index, glContext.Texture.tex_vertex (s, t, r, w, dsdx, dsdy,
				dtdx, dtdy,
				drdx, drdy));
	}

	/** Put a mip-mapped pixel in the Color Buffer */
	public void put_pixel (int x, int y, float w, float s, float t, float r,
			float dsdx, float dsdy, float dtdx, float dtdy, float drdx, float drdy) {
		put_pixel_by_index (x+glContext.getViewport().Width*y, w, s, t, r, dsdx, dsdy,
				dtdx, dtdy,
				drdx, drdy);
		//	put_pixel (x, y, glContext.Texture.tex_vertex (s, t, w, dsdx, dsdy, dtdx, dtdy));
	}

	/** Put a mip-mapped pixel in the Color Buffer with depth value */
	public void put_pixel_by_index (int  index, float z, float w, float s, float t, float r,
			float dsdx, float dsdy,
			float dtdx, float dtdy,
			float drdx, float drdy) {
		put_pixel_by_index (index, z, glContext.Texture.tex_vertex (s, t, r, w, dsdx, dsdy,
				dtdx, dtdy,
				drdx, drdy));
	}

	/** Put a mip-mapped pixel in the Color Buffer with depth value */
	public void put_pixel (int x, int y, float z, float w, float s, float t, float r,
			float dsdx, float dsdy, float dtdx, float dtdy, float drdx, float drdy) {
		put_pixel_by_index (x+glContext.getViewport().Width*y, z, w, s, t, r, dsdx, dsdy,
				dtdx, dtdy,
				drdx, drdy);
		//	put_pixel (x, y, z, glContext.Texture.tex_vertex (s, t, w, dsdx,dsdy,dtdx,dtdy));
	}

	protected int light_pixel (int t, int c) {
		return light_pixel (t, GL_Util.ItoRGB(c));
	}

	protected int light_pixel (int t, int c[]) {
		int tex [] = GL_Util.ItoRGB(t);
		tex[0] = (int)((float)tex[0] * GL_Util.ItoF(c[0]));
		tex[1] = (int)((float)tex[1] * GL_Util.ItoF(c[1]));
		tex[2] = (int)((float)tex[2] * GL_Util.ItoF(c[2]));
		return GL_Util.RGBtoI(tex[0], tex[1], tex[2]);
	}

	/** Put a lighting texturing pixel in the Color Buffer */
	public void put_pixel_by_index (int index, float s, float t, float r, int c) {
		put_pixel_by_index (index, light_pixel(glContext.Texture.tex_vertex(s, t, r),c));
	}

	/** Put a lighting texturing pixel in the Color Buffer */
	public void put_pixel (int x, int y, float s, float t, float r, int c) {
		put_pixel_by_index (x+glContext.getViewport().Width*y, s, t, r, c);
	}

	/** Put a lighting texturing pixel in the Color Buffer with depth value */
	public void put_pixel_by_index (int index, float z, float s, float t, float r,
			int c) {
		put_pixel_by_index (index, z,
				light_pixel(glContext.Texture.tex_vertex(s, t, r),c));
	}

	/** Put a texturing pixel in the Color Buffer with depth value */
	public void put_pixel (int x, int y, float z, float s, float t, float r, int c) {
		put_pixel_by_index (x+glContext.getViewport().Width*y, z, s, t, r, c);
	}

	/** Put a lighting mip-mapped pixel in the Color Buffer */
	public void put_pixel_by_index (int  index, float w, float s, float t, float r,
			float dsdx, float dsdy,
			float dtdx, float dtdy,
			float drdx, float drdy, int c) {
		put_pixel_by_index (index,
				light_pixel(
						glContext.Texture.tex_vertex(s, t, r, w, dsdx, dsdy,
								dtdx, dtdy,
								drdx, drdy),c));
	}

	/** Put a lighting mip-mapped pixel in the Color Buffer */
	public void put_pixel (int x, int y, float w, float s, float t, float r,
			float dsdx, float dsdy, float dtdx, float dtdy, float drdx, float drdy,
			int c) {
		put_pixel_by_index (x+glContext.getViewport().Width*y, w, s, t, r, dsdx, dsdy,
				dtdx, dtdy,
				drdx, drdy, c);
	}

	/** Put a lighting mip-mapped pixel in the Color Buffer with depth value */
	public void put_pixel_by_index (int  index, float z,
			float w, float s, float t, float r,
			float dsdx, float dsdy,
			float dtdx, float dtdy,
			float drdx, float drdy, int c) {
		put_pixel_by_index (index, z,
				light_pixel(
						glContext.Texture.tex_vertex(s, t, r, w, dsdx, dsdy,
								dtdx, dtdy,
								drdx, drdy),c));
	}

	/** Put a lighting mip-mapped pixel in the Color Buffer with depth value */
	public void put_pixel (int x, int y, float z, float w, float s, float t, float r,
			float dsdx, float dsdy, float dtdx, float dtdy, float drdx, float drdy,
			int c) {
		put_pixel_by_index (x+glContext.getViewport().Width*y, z, w, s, t, r, dsdx, dsdy,
				dtdx, dtdy,
				drdx, drdy, c);
	}


	/** Put a lighting texturing pixel in the Color Buffer */
	public void put_pixel_by_index (int index, float s, float t, float r, int c[]) {
		put_pixel_by_index (index, light_pixel(glContext.Texture.tex_vertex(s, t, r),c));
	}

	/** Put a lighting texturing pixel in the Color Buffer */
	public void put_pixel (int x, int y, float s, float t, float r, int c[]) {
		put_pixel_by_index (x+glContext.getViewport().Width*y, s, t, r, c);
	}

	/** Put a lighting texturing pixel in the Color Buffer with depth value */
	public void put_pixel_by_index (int index, float z, float s, float t, float r,
			int c[]) {
		put_pixel_by_index (index, z,
				light_pixel(glContext.Texture.tex_vertex(s, t, r),c));
	}

	/** Put a texturing pixel in the Color Buffer with depth value */
	public void put_pixel (int x, int y, float z, float s, float t, float r, int c[]) {
		put_pixel_by_index (x+glContext.getViewport().Width*y, z, s, t, r, c);
	}

	/** Put a lighting mip-mapped pixel in the Color Buffer */
	public void put_pixel_by_index (int  index, float w, float s, float t, float r,
			float dsdx, float dsdy,
			float dtdx, float dtdy,
			float drdx, float drdy, int c[]) {
		put_pixel_by_index (index,
				light_pixel(
						glContext.Texture.tex_vertex(s, t, r, w, dsdx, dsdy,
								dtdx, dtdy,
								drdx, drdy),c));
	}

	/** Put a lighting mip-mapped pixel in the Color Buffer */
	public void put_pixel (int x, int y, float w, float s, float t, float r,
			float dsdx, float dsdy, float dtdx, float dtdy, float drdx, float drdy,
			int c[]) {
		put_pixel_by_index (x+glContext.getViewport().Width*y, w, s, t, r, dsdx, dsdy,
				dtdx, dtdy,
				drdx, drdy, c);
	}

	/** Put a lighting mip-mapped pixel in the Color Buffer with depth value */
	public void put_pixel_by_index (int  index, float z,
			float w, float s, float t, float r,
			float dsdx, float dsdy,
			float dtdx, float dtdy,
			float drdx, float drdy, int c[]) {
		put_pixel_by_index (index, z,
				light_pixel(
						glContext.Texture.tex_vertex(s, t, r, w, dsdx, dsdy,
								dtdx, dtdy,
								drdx, drdy),c));
	}

	/** Put a lighting mip-mapped pixel in the Color Buffer with depth value */
	public void put_pixel (int x, int y, float z, float w, float s, float t, float r,
			float dsdx, float dsdy, float dtdx, float dtdy, float drdx, float drdy,
			int c[]) {
		put_pixel_by_index (x+glContext.getViewport().Width*y, z, w, s, t, r, dsdx, dsdy,
				dtdx, dtdy,
				drdx, drdy, c);
	}

	/* just for stipple_line */
	public void init (int dx, int dy) {}

	public GL_Render_Pixel (GL_Context cc) {
		glContext = cc;
	}

}
