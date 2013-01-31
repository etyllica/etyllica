package br.com.etyllica.core.control.mouse.arrow;

import br.com.etyllica.core.video.Grafico;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public interface MouseArrow {

	public void defPoints();
	
	public void draw(Grafico g);
	
	public void move(int mx, int my);
	
}
