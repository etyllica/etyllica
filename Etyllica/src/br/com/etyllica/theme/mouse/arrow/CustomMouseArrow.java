package br.com.etyllica.theme.mouse.arrow;

import br.com.etyllica.layer.ImageLayer;
import br.com.etyllica.theme.mouse.arrow.MouseArrow;

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
		setCoordinates(x, y);		
	}

}
