package br.com.etyllica.core.event;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public enum KeyState {
	FIRST_RELEASED,
	RELEASED,
	PRESSED,  // Down, but not the first time
	ONCE,      // Down for the first time
	
	TYPED;
}
