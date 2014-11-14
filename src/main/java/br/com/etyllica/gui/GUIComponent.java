package br.com.etyllica.gui;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public interface GUIComponent {
	
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
	
	/**
	 * 
	 * @param new window width 
	 * @param new window height
	 */
	public void resize(int width, int height);	

}