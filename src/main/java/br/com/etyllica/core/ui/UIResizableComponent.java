package br.com.etyllica.core.ui;


import br.com.etyllica.core.handler.Handler;

/**
 * 
 * @author yuripourre
 *
 */

public interface UIResizableComponent extends UIComponent {
	
	/**
	 * 
	 * @param width - new window width
	 * @param height - new window height
	 */
	void resize(int width, int height);

}
