package br.com.etyllica.core.event;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public enum KeyState {

	//FIRST_RELEASED,
	RELEASED,
	PRESSED,  // Pressionado mas nao pela primeira vez 
	//ONCE,      // Pressionado pela primeira vez
	TYPED,
	
	DRAGGED,
	
	CLICK,
	DOUBLE_CLICK,
	MOVE,
	
	ENTER,
	
}
