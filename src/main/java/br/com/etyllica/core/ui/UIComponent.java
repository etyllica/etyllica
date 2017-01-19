package br.com.etyllica.core.ui;

import br.com.etyllica.core.Drawable;
import br.com.etyllica.core.Updatable;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;

/**
 * 
 * @author yuripourre
 *
 */

public interface UIComponent extends Drawable, Updatable {
	
	/**
	 * 
	 * @param event
	 * @return
	 */
	public void updateMouse(PointerEvent event);
	
	/**
	 * 
	 * @param event
	 * @return
	 */
	public void updateKeyboard(KeyEvent event);
	
}
