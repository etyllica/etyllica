package br.com.etyllica.core.event;

/**
 * 
 * @author yuripourre
 *
 */

public enum PointerState {

	RELEASED,
	PRESSED,  // Pressed but not for the first time 
	DRAGGED,
	
	CLICK,
	DOUBLE_CLICK,
	MULTIPLE_CLICK,
	MOVE,
	
	ENTER,
	
}
