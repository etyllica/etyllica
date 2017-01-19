package br.com.etyllica.core.ui;


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
	public void resize(int width, int height);

}
