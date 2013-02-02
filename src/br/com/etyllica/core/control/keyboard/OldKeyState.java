package br.com.etyllica.core.control.keyboard;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public enum OldKeyState {
	FIRST_RELEASED,
	RELEASED,
	PRESSED,  // Down, but not the first time
	ONCE,      // Down for the first time
	
	TYPED;
}
