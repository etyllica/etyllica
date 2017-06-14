package br.com.etyllica.commons.ui;


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
