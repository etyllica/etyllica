package br.com.etyllica.core;

import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public interface Component extends Drawable{
	
	/**
	 * 
	 * @param event
	 * @return
	 */
	public GUIEvent updateMouse(PointerEvent event);
	
	/**
	 * 
	 * @param event
	 * @return
	 */
	public GUIEvent updateKeyboard(KeyEvent event);
	
	/**
	 * 
	 * @param event
	 */
	public void update(GUIEvent event);
		
	/**
	 * 
	 * @param mx
	 * @param my
	 * @return
	 */
	public boolean onMouse(int mx, int my);

}
