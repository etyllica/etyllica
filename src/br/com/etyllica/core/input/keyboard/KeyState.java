package br.com.etyllica.core.input.keyboard;

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
