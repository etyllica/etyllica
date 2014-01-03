package br.com.etyllica.gui;

import br.com.etyllica.core.Drawable;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public interface GUIComponent extends Drawable{
	
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
	 * @param mx mouse coordinate x
	 * @param my mouse coordinate y
	 * @return
	 */
	public boolean onMouse(int mx, int my);

}
