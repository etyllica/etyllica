/*
 * @(#)gl_pointer.java 0.3 06/11/21
 *
 * jGL 3-D graphics library for Java
 * Copyright (c) 1999-2006 Robin Bing-Yu Chen (robin@ntu.edu.tw)
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

package org.jgl.context;

import org.jgl.context.clipping.GL_CP_Clipping;
import org.jgl.context.clipping.GL_CP_Color_Clipping;
import org.jgl.context.clipping.GL_CP_Lit_Tex_Clipping;
import org.jgl.context.clipping.GL_CP_Tex_Clipping;
import org.jgl.context.clipping.GL_Clipping;
import org.jgl.context.clipping.GL_VP_Clipping;
import org.jgl.context.clipping.GL_VP_Color_Clipping;
import org.jgl.context.clipping.GL_VP_Lit_Tex_Clipping;
import org.jgl.context.clipping.GL_VP_Tex_Clipping;
import org.jgl.context.geometry.GL_Depth_Geo;
import org.jgl.context.geometry.GL_Geometry;
import org.jgl.context.geometry.GL_Lit_Tex_Geo;
import org.jgl.context.geometry.GL_Lit_Tex_Z_Geo;
import org.jgl.context.geometry.GL_Smooth_Geo;
import org.jgl.context.geometry.GL_Smooth_Z_Geo;
import org.jgl.context.geometry.GL_Tex_Geo;
import org.jgl.context.geometry.GL_Tex_Z_Geo;
import org.jgl.context.render.GL_Depth;
import org.jgl.context.render.GL_Lit_Tex;
import org.jgl.context.render.GL_Lit_Tex_Z;
import org.jgl.context.render.GL_Render;
import org.jgl.context.render.GL_Select_Render;
import org.jgl.context.render.GL_Smooth;
import org.jgl.context.render.GL_Smooth_Z;
import org.jgl.context.render.GL_Tex;
import org.jgl.context.render.GL_Tex_Z;
import org.jgl.context.render.pixel.GL_Blend_Pixel;
import org.jgl.context.render.pixel.GL_Render_Pixel;
import org.jgl.context.render.pixel.GL_Render_Point;
import org.jgl.context.render.pixel.GL_Select_Pixel;
import org.jgl.context.render.pixel.GL_Stipple_Line_Pixel;
import org.jgl.context.render.pixel.GL_Stipple_Poly_Pixel;

/**
 * gl_pointer is the class points to all current instants of jGL 2.4.
 *
 * @version 	0.3, 21 Nov 2006
 * @author 	Robin Bing-Yu Chen
 */

public class GL_Pointer {

    protected GL_Context CC;

    protected int status = 4; // default state "000100"

    /**
     *
     * Current Status
     *    The status of GL about selection, point size, texturing, smooth or
     *    flat shading, depth buffer, and clip-plane clipping.
     *
     * Status Select Point Texture Smooth Depth Clipping
     *---------------------------------------------------
     * ??00?0                 no     no            no
     * ??00?1                 no     no           yes
     * 0?000?   no            no     no     no
     * 0?001?   no            no     no    yes
     * ??01?0                 no    yes            no
     * ??01?1                 no    yes           yes
     * 0?010?   no            no    yes     no
     * 0?011?   no            no    yes    yes
     * ??10?0                yes     no            no
     * ??10?1                yes     no          yes
     * 0?100?   no           yes     no     no
     * 0?101?   no           yes     no    yes
     * ??11?0                yes    yes            no
     * ??11?1                yes    yes           yes
     * 0?110?   no           yes    yes     no
     * 0?111?   no           yes    yes    yes
     * 00????   no     no
     * 01????   no    yes
     * 1----?  yes    ---    ---    ---    ---
     *
     * where ? means independent, - means ignore.
     */

    // set to default state, no depth, smooth shading, no texturing, no clipping,
    // no selection, no stipple
    public GL_Clipping     clipping;   
    public GL_VP_Clipping  vp_clipping;

    public GL_Geometry     geometry;   
    public GL_Render       render;     
    public GL_Render       line;       

    public GL_Render_Pixel basic_pixel;
    public GL_Render_Pixel pixel;      
    public GL_Render_Pixel line_pixel; 
    public GL_Render_Pixel poly_pixel; 

    public void gl_stipple_line (boolean state) {
	if (state) { line_pixel = new GL_Stipple_Line_Pixel (CC, this);
	} else { line_pixel = basic_pixel; }
    }

    public void gl_stipple_poly (boolean state) {
	if (state) { poly_pixel = new GL_Stipple_Poly_Pixel (CC);
	} else { poly_pixel = basic_pixel; }
    }

    public void gl_clipping (boolean state) {
	if (state) {
	    if ((status & 0x00000001) == 0) {
		if ((status & 0x00000008) != 0) {
		    if (CC.Lighting.Enable && ((status & 0x00000004) != 0)) {
			clipping = new GL_CP_Lit_Tex_Clipping (CC);
		    } else {
			clipping = new GL_CP_Tex_Clipping (CC);
		    }
//		    clipping = new gl_cp_tex_clipping (CC);
		} else {
		    if ((status & 0x00000004) != 0) {
			clipping = new GL_CP_Color_Clipping (CC);
		    } else {
			clipping = new GL_CP_Clipping (CC);
		    }
		}
		status |= 0x00000001;
	    }
	} else {
	    if ((status & 0x00000001) != 0) {
/*
		if ((status & 0x00000008) != 0) {
		    clipping = new gl_nf_tex_clipping (CC);
		} else {
		    if ((status & 0x00000004) != 0) {
			clipping = new gl_nf_color_clipping (CC);
		    } else {
			clipping = new gl_nf_clipping (CC);
		    }
		}
*/
		clipping = null;
		status &= 0xfffffffe;
	    }
	}
    }

    public void gl_depth (boolean state) {
	if (state) {
	    if ((status & 0x00000002) == 0) {		// test for depth
		if ((status & 0x00000008) != 0) {	// test for texture
		    if (CC.Lighting.Enable && ((status & 0x00000004) != 0)) {
		    	geometry = new GL_Lit_Tex_Z_Geo (CC, this);
			if ((status & 0x00000020) == 0) {
			    render = new GL_Lit_Tex_Z (CC);
			}
		    } else {
			geometry = new GL_Tex_Z_Geo (CC, this);
			if ((status & 0x00000020) == 0) {
			    render = new GL_Tex_Z (CC);
			}
		    }
/*
		    geometry = new gl_tex_z_geo (CC, this);
		    if ((status & 0x00000020) == 0) {	// test for select
			render = new gl_tex_z (CC);
		    }
*/
		} else {
		    if ((status & 0x00000004) != 0) {	// test for smooth
			geometry = new GL_Smooth_Z_Geo (CC, this);
			if ((status & 0x00000020) == 0) {
			    render = new GL_Smooth_Z (CC);
			}
		    } else {
			geometry = new GL_Depth_Geo (CC, this);
			if ((status & 0x00000020) == 0) {
			    render = new GL_Depth (CC);
			}
		    }
		}
		line = new GL_Depth (CC);
		line.set_pixel (basic_pixel);
		status |= 0x00000002;
	    }
	} else {
	    if ((status & 0x00000002) != 0) {
		if ((status & 0x00000008) != 0) {
		    if (CC.Lighting.Enable && ((status & 0x00000004) != 0)) {
			geometry = new GL_Lit_Tex_Geo (CC, this);
			if ((status & 0x00000020) == 0) {
			    render = new GL_Lit_Tex (CC);
			}
		    } else {
			geometry = new GL_Tex_Geo (CC, this);
			if ((status & 0x00000020) == 0) {
			    render = new GL_Tex (CC);
			}
		    }
/*
		    geometry = new gl_tex_geo (CC, this);
		    if ((status & 0x00000020) == 0) {
			render = new gl_tex (CC);
		    }
*/
		} else {
		    if ((status & 0x00000004) != 0) {
			geometry = new GL_Smooth_Geo (CC, this);
			if ((status & 0x00000020) == 0) {
			    render = new GL_Smooth (CC);
			}
		    } else {
			geometry = new GL_Geometry (CC, this);
			if ((status & 0x00000020) == 0) {
			    render = new GL_Render (CC);
			}
		    }
		}
		line = new GL_Render (CC);
		line.set_pixel (basic_pixel);
		status &= 0xfffffffd;
	    }
	}
    }

    public void gl_smooth (boolean state) {
	if (state) {
	    if ((status & 0x00000004) == 0) {
		if ((status & 0x00000008) == 0) {
		    if ((status & 0x00000002) != 0) {
			if ((status & 0x00000020) == 0) {
			    render = new GL_Smooth_Z (CC);
			}
			geometry = new GL_Smooth_Z_Geo (CC, this);
		    } else {
			if ((status & 0x00000020) == 0) {
			    render = new GL_Smooth (CC);
			}
			geometry = new GL_Smooth_Geo (CC, this);
		    }
		    if ((status & 0x00000001) != 0) {
			clipping = new GL_CP_Color_Clipping (CC);
		    }
/*
 else {
			clipping = new gl_nf_color_clipping (CC);
		    }
*/
		    vp_clipping = new GL_VP_Color_Clipping (CC);
		} else if (CC.Lighting.Enable) {
		    if ((status & 0x00000002) != 0) {
			if ((status & 0x00000020) == 0) {
			    render = new GL_Lit_Tex_Z (CC);
			}
			geometry = new GL_Lit_Tex_Z_Geo (CC, this);
		    } else {
			if ((status & 0x00000020) == 0) {
			    render = new GL_Lit_Tex (CC);
			}
			geometry = new GL_Lit_Tex_Geo (CC, this);
		    }
		    if ((status & 0x00000001) != 0) {
			clipping = new GL_CP_Lit_Tex_Clipping (CC);
		    }
		    vp_clipping = new GL_VP_Lit_Tex_Clipping (CC);
		}
		status |= 0x00000004;
	    }
	} else {
	    if ((status & 0x00000004) != 0) {
		if ((status & 0x00000008) == 0) {
		    if ((status & 0x00000002) != 0) {
			if ((status & 0x00000020) == 0) {
			    render = new GL_Depth (CC);
			}
			geometry = new GL_Depth_Geo (CC, this);
		    } else {
			if ((status & 0x00000020) == 0) {
			    render = new GL_Render (CC);
			}
			geometry = new GL_Geometry (CC, this);
		    }
		    if ((status & 0x00000001) != 0) {
			clipping = new GL_CP_Clipping (CC);
		    }
/*
else {
			clipping = new gl_nf_clipping (CC);
		    }
*/
		    vp_clipping = new GL_VP_Clipping (CC);
		} else {
		    if ((status & 0x00000002) != 0) {
			if ((status & 0x00000020) == 0) {
			    render = new GL_Tex_Z (CC);
			}
			geometry = new GL_Tex_Z_Geo (CC, this);
		    } else {
			if ((status & 0x00000020) == 0) {
			    render = new GL_Tex (CC);
			}
			geometry = new GL_Tex_Geo (CC, this);
		    }
		    if ((status & 0x00000001) != 0) {
			clipping = new GL_CP_Tex_Clipping (CC);
		    }
		    vp_clipping = new GL_VP_Tex_Clipping (CC);
		}
		status &= 0xfffffffb;
	    }
	}
    }

    public void gl_texture (boolean state) {
	if (state) {
	    if ((status & 0x00000008) == 0) {
		if (CC.Lighting.Enable && ((status & 0x00000004) != 0)) {
		    if ((status & 0x00000002) != 0) {
			if ((status & 0x00000020) == 0) {
			    render = new GL_Lit_Tex_Z (CC);
			}
			geometry = new GL_Lit_Tex_Z_Geo (CC, this);
		    } else {
			if ((status & 0x00000020) == 0) {
			    render = new GL_Lit_Tex (CC);
			}
			geometry = new GL_Lit_Tex_Geo (CC, this);
		    }
		    if ((status & 0x00000001) != 0) {
			clipping = new GL_CP_Lit_Tex_Clipping (CC);
		    }
		    vp_clipping = new GL_VP_Lit_Tex_Clipping (CC);
		} else {
		    if ((status & 0x00000002) != 0) {
			if ((status & 0x00000020) == 0) {
			    render = new GL_Tex_Z (CC);
			}
			geometry = new GL_Tex_Z_Geo (CC, this);
		    } else {
			if ((status & 0x00000020) == 0) {
			    render = new GL_Tex (CC);
			}
			geometry = new GL_Tex_Geo (CC, this);
		    }
		    if ((status & 0x00000001) != 0) {
			clipping = new GL_CP_Tex_Clipping (CC);
		    }
		    vp_clipping = new GL_VP_Tex_Clipping (CC);
		}
/*
		if ((status & 0x00000002) != 0) {
		    if ((status & 0x00000020) == 0) {
			render = new gl_tex_z (CC);
		    }
		    geometry = new gl_tex_z_geo (CC, this);
		} else {
		    if ((status & 0x00000020) == 0) {
			render = new gl_tex (CC);
		    }
		    geometry = new gl_tex_geo (CC, this);
		}
		if ((status & 0x00000001) != 0) {
		    clipping = new gl_cp_tex_clipping (CC);
		}
*/
/*
else {
		    clipping = new gl_nf_tex_clipping (CC);
		}
		vp_clipping = new gl_vp_tex_clipping (CC);
*/
		status |= 0x00000008;
	    }
	} else {
	    if ((status & 0x00000008) != 0) {
		if ((status & 0x00000004) != 0) {
		    if ((status & 0x00000002) != 0) {
			if ((status & 0x00000020) == 0) {
			    render = new GL_Smooth_Z (CC);
			}
			geometry = new GL_Smooth_Z_Geo (CC, this);
		    } else {
			if ((status & 0x00000020) == 0) {
			    render = new GL_Smooth (CC);
			}
			geometry = new GL_Smooth_Geo (CC, this);
		    }
		    if ((status & 0x00000001) != 0) {
			clipping = new GL_CP_Color_Clipping (CC);
		    }
/*
else {
			clipping = new gl_nf_color_clipping (CC);
		    }
*/
		    vp_clipping = new GL_VP_Color_Clipping (CC);
		} else {
		    if ((status & 0x00000002) != 0) {
			if ((status & 0x00000020) == 0) {
			    render = new GL_Depth (CC);
			}
			geometry = new GL_Depth_Geo (CC, this);
		    } else {
			if ((status & 0x00000020) == 0) {
			    render = new GL_Render (CC);
			}
			geometry = new GL_Geometry (CC, this);
		    }
		    if ((status & 0x00000001) != 0) {
			clipping = new GL_CP_Clipping (CC);
		    }
/*
else {
			clipping = new gl_nf_clipping (CC);
		    }
*/
		    vp_clipping = new GL_VP_Clipping (CC);
		}
		status &= 0xfffffff7;
	    }
	}
    }

    public void gl_point_size (boolean state) {
	if (state) {
	    if ((status & 0x00000010) == 0) {
		if ((status & 0x00000020) == 0) {
		    pixel = new GL_Render_Point (CC);
		}
		status |= 0x00000010;
	    }
	} else {
	    if ((status & 0x00000010) != 0) {
		if ((status & 0x00000020) == 0) {
		    pixel = basic_pixel;
		}
		status &= 0xffffffef;
	    }
	}
    }

    public void gl_select (boolean state) {
	if (state) {
	    if ((status & 0x00000020) == 0) {
		render = new GL_Select_Render (CC);
		pixel = new GL_Select_Pixel (CC);
		status |= 0x00000020;
	    }
	} else {
	    if ((status & 0x00000020) != 0) {
		if ((status & 0x00000008) != 0) {
		    if ((status & 0x00000002) != 0) {
			if (CC.Lighting.Enable && ((status & 0x00000004) != 0)) {
			    render = new GL_Lit_Tex_Z (CC);
			} else {
			    render = new GL_Tex_Z (CC);
			}
		    } else {
			if (CC.Lighting.Enable && ((status & 0x00000004) != 0)) {
			    render = new GL_Lit_Tex (CC);
			} else {
			    render = new GL_Tex (CC);
			}
		    }
/*
		    if ((status & 0x00000002) != 0) {
			render = new gl_tex_z (CC);
		    } else {
			render = new gl_tex (CC);
		    }
*/
		} else {
		    if ((status & 0x00000002) != 0) {
			if ((status & 0x00000004) != 0) {
			    render = new GL_Smooth_Z (CC);
			} else {
			    render = new GL_Depth (CC);
			}
		    } else {
			if ((status & 0x00000004) != 0) {
			    render = new GL_Smooth (CC);
			} else {
			    render = new GL_Render (CC);
			}
		    }
		}
		if ((status & 0x00000010) != 0) {
		    pixel = new GL_Render_Point (CC);
		} else {
		    pixel = basic_pixel;
		}
		status &= 0xffffffdf;
	    }
	}
    }
    
    public void gl_lighting (boolean state) {
	if (state) {
	    if ((status & 0x00000008) != 0) {
		if ((status & 0x00000004) != 0) {
		    if ((status & 0x00000002) != 0) {
			if ((status & 0x00000020) == 0) {
			    render = new GL_Lit_Tex_Z (CC);
			}
			geometry = new GL_Lit_Tex_Z_Geo (CC, this);
		    } else {
			if ((status & 0x00000020) == 0) {
			    render = new GL_Lit_Tex (CC);
			}
			geometry = new GL_Lit_Tex_Geo (CC, this);
		    }
		    if ((status & 0x00000001) != 0) {
			clipping = new GL_CP_Lit_Tex_Clipping (CC);
		    }
		    vp_clipping = new GL_VP_Lit_Tex_Clipping (CC);
		}
	    }
	} else {
	    if ((status & 0x00000008) != 0) {
		if ((status & 0x00000002) != 0) {
		    if ((status & 0x00000020) == 0) {
			render = new GL_Tex_Z (CC);
		    }
		    geometry = new GL_Tex_Z_Geo (CC, this);
		} else {
		    if ((status & 0x00000020) == 0) {
			render = new GL_Tex (CC);
		    }
		    geometry = new GL_Tex_Geo (CC, this);
		}
		if ((status & 0x00000001) != 0) {
		    clipping = new GL_CP_Tex_Clipping (CC);
		}
		vp_clipping = new GL_VP_Tex_Clipping (CC);
	    }
	}
    }

    public void gl_blending (boolean state) {
	if (state) {
	    line_pixel = new GL_Blend_Pixel (CC);
	    poly_pixel = line_pixel;
	    line.set_pixel (line_pixel);
	} else {
	    line_pixel = basic_pixel;
	    poly_pixel = basic_pixel;
	    line.set_pixel (basic_pixel);
	}
    }
    
    public GL_Pointer (GL_Context cc) {
	CC = cc;
//	clipping    = new gl_nf_color_clipping (CC);
	clipping    = null;
	vp_clipping = new GL_VP_Color_Clipping (CC);
	geometry    = new GL_Smooth_Geo (CC, this);
	render      = new GL_Smooth (CC);
	line        = new GL_Render (CC);
	basic_pixel = new GL_Render_Pixel (CC);
	pixel       = basic_pixel;
	line_pixel  = basic_pixel;
	poly_pixel  = basic_pixel;
	line.set_pixel (basic_pixel);
    }

}
