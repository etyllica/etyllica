package br.com.etyllica.core.control.keyboard;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.etyllica.core.event.KeyboardEvent;
import br.com.etyllica.core.event.Tecla;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Keyboard implements KeyListener {
	
	private List<KeyboardEvent> keyEvents = new ArrayList<KeyboardEvent>();

	private Map<Integer,OldKeyState> keyStates = new HashMap<Integer,OldKeyState>(0);
	private Map<Integer,Boolean> keys = new HashMap<Integer,Boolean>();

	public Keyboard() {
		super();
		
		//Reset Keyboard to avoid delay at first press
		for (Tecla key: Tecla.values()) {
			keys.put(key.getCode(),false);
			keyStates.put(key.getCode(),OldKeyState.RELEASED);
		}

	}

	public void poll(){

		for(Integer key: keys.keySet()){
			if( keys.get(key) ) {

				if( keyStates.get(key) == OldKeyState.RELEASED ){
					keyStates.put(key,OldKeyState.ONCE);
					
					keyEvents.add(new KeyboardEvent(key, OldKeyState.PRESSED));
				}
				else{
					keyStates.put(key,OldKeyState.PRESSED);
				}

			}else{

				if(( keyStates.get(key) == OldKeyState.ONCE )||( keyStates.get(key) == OldKeyState.PRESSED )){
					keyStates.put(key,OldKeyState.FIRST_RELEASED);

					keyEvents.add(new KeyboardEvent(key, OldKeyState.RELEASED));
				}
				else{
					keyStates.put(key,OldKeyState.RELEASED);
				}
			}
		}
	}

	public boolean keyDown( int keyCode ) {
		return keyStates.get(keyCode) != OldKeyState.RELEASED;
	}

	public boolean keyDownOnce( int keyCode ) {
		return keyStates.get(keyCode) == OldKeyState.ONCE;
	}

	public boolean keyUp( int keyCode ) {

		return keyStates.get(keyCode) == OldKeyState.FIRST_RELEASED;		
	}

	public synchronized void keyPressed( KeyEvent ke ) {

		int code = getKeyFromEvent(ke);

		keys.put(code, true);

	}

	public synchronized void keyReleased( KeyEvent ke ) {

		int code = getKeyFromEvent(ke);

		keys.put(code, false);

	}

	@Override
	public void keyTyped(KeyEvent ke) {

		int code = getKeyFromEvent(ke);
		
		char c = ke.getKeyChar();

		//TODO Ajeitar o typed
		if ( c != KeyEvent.CHAR_UNDEFINED ) {
			keyEvents.add(new KeyboardEvent(code, c, OldKeyState.TYPED));
		}
		
		ke.consume();

	}
	
	private int getKeyFromEvent(KeyEvent ke){
		int code = ke.getKeyCode();

		if(ke.getKeyLocation()!=KeyEvent.KEY_LOCATION_STANDARD){
			code+=ke.getKeyLocation()*100;
		}
		
		return code;
	}

	public List<KeyboardEvent> getEvents(){
		return keyEvents;
	}

}
