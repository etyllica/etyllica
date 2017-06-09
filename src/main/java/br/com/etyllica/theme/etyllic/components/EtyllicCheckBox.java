package br.com.etyllica.theme.etyllic.components;

import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.ui.base.BaseCheckBox;

/**
 * 
 * @author yuripourre
 *
 */

public class EtyllicCheckBox extends BaseCheckBox {

	public EtyllicCheckBox(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		
		if (checked) {
			checker.draw(g);
		}
	}

}
