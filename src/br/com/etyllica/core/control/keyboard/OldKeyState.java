package br.com.etyllica.core.control.keyboard;

public enum OldKeyState {
	FIRST_RELEASED,
	RELEASED,
	PRESSED,  // Down, but not the first time
	ONCE      // Down for the first time
}
