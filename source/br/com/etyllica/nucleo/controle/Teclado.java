package br.com.etyllica.nucleo.controle;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Teclado implements KeyListener {

	public static final int KEY_COUNT = 256;
	private String texto;
	private boolean textMode;

	public static enum KeyState {
		FIRST_RELEASED,
		RELEASED,
		PRESSED,  // Down, but not the first time
		ONCE      // Down for the first time
	}

	// Current state of the keyboard
	private boolean[] keys = null;
	//private boolean[] controlKeys = null;

	// Polled keyboard state
	private KeyState[] keyStates = null;

	public Teclado() {
		keys = new boolean[ KEY_COUNT ];
		//controlKeys = new boolean[ KEY_COUNT ];
		keyStates = new KeyState[ KEY_COUNT ];
		resetTeclado();
	}
	
	public void resetTeclado(){
		for( int i = 0; i < KEY_COUNT; ++i ) {
			keyStates[ i ] = KeyState.RELEASED;
			//controlKeys[ i ] = false;
		}
	}

	public synchronized void poll() {
		for( int i = 0; i < KEY_COUNT; ++i ) {
			// Set the key state 
			if( keys[ i ] ) {
				
				if( keyStates[ i ] == KeyState.RELEASED ){
					keyStates[ i ] = KeyState.ONCE;
				}
				else{
					keyStates[ i ] = KeyState.PRESSED;
				}
				
			} else {

				if(( keyStates[ i ] == KeyState.ONCE )||( keyStates[ i ] == KeyState.PRESSED )){
					keyStates[ i ] = KeyState.FIRST_RELEASED;
				}
				else{
					
					keyStates[ i ] = KeyState.RELEASED;
				}

			}
		}
	}

	public boolean keyDown( int keyCode ) {
		return keyStates[ keyCode ] != KeyState.RELEASED;
	}

	public boolean keyDownOnce( int keyCode ) {
		return keyStates[ keyCode ] == KeyState.ONCE;
	}

	public boolean keyUp( int keyCode ) {
		return keyStates[ keyCode ] == KeyState.FIRST_RELEASED;
	}

	public synchronized void keyPressed( KeyEvent e ) {
		int keyCode = e.getKeyCode();
		if( keyCode >= 0 && keyCode < KEY_COUNT ) {
			keys[ keyCode ] = true;
		}
		
	}

	public synchronized void keyReleased( KeyEvent e ) {
		int keyCode = e.getKeyCode();
		if( keyCode >= 0 && keyCode < KEY_COUNT ) {
			keys[ keyCode ] = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent ke) {

		if(textMode){
			char c = ke.getKeyChar();
			if ( c != KeyEvent.CHAR_UNDEFINED ) {
				texto += c;
			}
		}

	}

	public void despressionaTecla( Tecla tecla ) {
		keyStates[ tecla.codigo() ] = KeyState.RELEASED;
	}
	
	public void setTextMode(boolean textMode) {
		this.textMode = textMode;

	}
	public String getTexto(){
		return texto;
	}

	//TECLADO
	public boolean getTecla(Tecla ptecla){
		return keyDown(ptecla.codigo());			
	}

	public boolean getTeclaOnce(Tecla ptecla){
		return keyDownOnce(ptecla.codigo());
	}
	
	public boolean getTeclaSolta(Tecla ptecla){
		return keyUp(ptecla.codigo());
	}

	public boolean[] getKeys() {
		return keys;
	}

	public KeyState[] getKeyStates() {
		return keyStates;
	}
	
}
