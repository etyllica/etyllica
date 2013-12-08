package br.com.etyllica.core.event;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public enum PointerState {

	RELEASED,
	PRESSED,  // Pressed but not for the first time 
	DRAGGED,
	
	CLICK,
	DOUBLE_CLICK,
	MOVE,
	
	ENTER,
	
}
