package br.com.etyllica.core.input.keyboard;

import java.awt.event.KeyListener;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.etyllica.core.event.KeyEvent;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Keyboard implements KeyListener {
	
	private List<KeyEvent> keyEvents = new ArrayList<KeyEvent>();

	private Map<Integer,KeyState> keyStates = new HashMap<Integer,KeyState>(0);
	private Map<Integer,Boolean> keys = new HashMap<Integer,Boolean>();

	public Keyboard(){
		super();
				
	}
	
	public void reset(){
		
		Field[] fields = KeyEvent.class.getDeclaredFields();
		
		for (Field f : fields) {
		    if (Modifier.isPublic(f.getModifiers()) && Modifier.isStatic(f.getModifiers()) && f.getName().startsWith("TSK_")) {
		    	try {
					keys.put(f.getInt(null),false);
					keyStates.put(f.getInt(null),KeyState.RELEASED);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
		    } 
		}
	}

	public void poll(){

		for(Integer key: keys.keySet()){
			if( keys.get(key) ) {

				if( keyStates.get(key) == KeyState.RELEASED ){
					keyStates.put(key,KeyState.ONCE);
					
					keyEvents.add(new KeyEvent(key, KeyState.PRESSED));
				}
				else{
					keyStates.put(key,KeyState.PRESSED);
				}

			}else{

				if(( keyStates.get(key) == KeyState.ONCE )||( keyStates.get(key) == KeyState.PRESSED )){
					keyStates.put(key,KeyState.FIRST_RELEASED);

					keyEvents.add(new KeyEvent(key, KeyState.RELEASED));
				}
				else{
					keyStates.put(key,KeyState.RELEASED);
				}
			}
		}
	}

	public boolean keyDown( int keyCode ) {
		return keyStates.get(keyCode) != KeyState.RELEASED;
	}

	public boolean keyDownOnce( int keyCode ) {
		return keyStates.get(keyCode) == KeyState.ONCE;
	}

	public boolean keyUp( int keyCode ) {

		return keyStates.get(keyCode) == KeyState.FIRST_RELEASED;		
	}

	public synchronized void keyPressed( java.awt.event.KeyEvent ke ) {

		int code = getKeyFromEvent(ke);

		keys.put(code, true);

	}

	public synchronized void keyReleased( java.awt.event.KeyEvent ke ) {

		int code = getKeyFromEvent(ke);

		keys.put(code, false);

	}

	@Override
	public void keyTyped( java.awt.event.KeyEvent ke) {

		int code = getKeyFromEvent(ke);
		
		char c = ke.getKeyChar();

		//TODO Ajeitar o typed
		if ( c != java.awt.event.KeyEvent.CHAR_UNDEFINED ) {
			keyEvents.add(new KeyEvent(code, c, KeyState.TYPED));
		}
		
		ke.consume();

	}
	
	private int getKeyFromEvent(java.awt.event.KeyEvent ke){
		int code = ke.getKeyCode();

		if(ke.getKeyLocation()!=java.awt.event.KeyEvent.KEY_LOCATION_STANDARD){
			code+=ke.getKeyLocation()*100;
		}
		
		return code;
	}

	public List<KeyEvent> getEvents(){
		return keyEvents;
	}

}
