package br.com.etyllica.core.event;

/**
 * 
 * @author yuripourre
 *
 */

public enum KeyState {
	FIRST_RELEASED,
	RELEASED,
	PRESSED,  // Down, but not the first time
	ONCE,      // Down for the first time
	
	TYPED;
}
