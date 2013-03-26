package br.com.etyllica.core;

import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyboardEvent;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public interface Component extends DrawableComponent{
	
	//TODO Incuir tambem o nome(id) de quem gerou o evento!
		
	public GUIEvent updateMouse(PointerEvent event);
	public GUIEvent updateKeyboard(KeyboardEvent event);
	
	public void update(GUIEvent event);
	
	public boolean onMouse(int mx, int my);

}
