package br.com.etyllica.gui.mouse.arrow;

import br.com.etyllica.gui.mouse.arrow.MouseArrow;
import br.com.etyllica.layer.ImageLayer;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class CustomMouseArrow extends ImageLayer implements MouseArrow{

	public CustomMouseArrow(String path){
		super(path);
	}
	
	@Override
	public void move(int x, int y) {
		this.x = x;
		this.y = y;		
	}

}
