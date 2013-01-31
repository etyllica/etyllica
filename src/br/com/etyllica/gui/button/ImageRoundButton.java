package br.com.etyllica.gui.button;

import br.com.etyllica.layer.StaticLayer;
/**
 * 
 * @author mscythe
 * @license GPLv3
 *
 */

public class ImageRoundButton extends ImageButton{

	public ImageRoundButton(int x, int y, StaticLayer normal,
			StaticLayer sobMouse) {
		super(x, y, normal, sobMouse);
	}

	public ImageRoundButton(int x, int y, int w, int h, String label) {
		super(x,y,w,h,label);
	}

	@Override
	public boolean onMouse(int mx, int my) {

		return colideCircleCircle(mx-w/2, my-w/2, 1, 1);
	}

}
