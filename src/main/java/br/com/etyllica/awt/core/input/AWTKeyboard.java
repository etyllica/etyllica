package br.com.etyllica.awt.core.input;

import java.awt.event.KeyListener;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import br.com.etyllica.core.event.KeyEventListener;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.KeyState;
import br.com.etyllica.core.input.keyboard.Keyboard;
import br.com.etyllica.util.concurrency.ConcurrentSet;

/**
 *
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class AWTKeyboard implements KeyListener, Keyboard {

	private KeyEventListener listener;
	
	private List<KeyEvent> keyEvents = new ArrayList<KeyEvent>();

	private Map<Integer,Boolean> keys = new HashMap<Integer,Boolean>();
	private Map<Integer,KeyState> keyStates = new HashMap<Integer,KeyState>();

	private ConcurrentSet<Integer> changed = new ConcurrentSet<Integer>();

	public AWTKeyboard(KeyEventListener listener) {
		super();
		this.listener = listener;
	}

	public void update(long now) {
		
		Set<Integer> changedCopy = changed.lock();

		for(Integer key: changedCopy) {

			KeyState keyState = getState(key);

			if ( keys.get(key) ) {

				if ( keyState == KeyState.RELEASED ) {

					keyStates.put(key,KeyState.ONCE);
					keyEvents.add(new KeyEvent(key, KeyState.PRESSED));

				} else if (keyState != KeyState.PRESSED) {

					keyStates.put(key,KeyState.PRESSED);

				}

			} else {

				if (( keyState == KeyState.ONCE )||( keyState == KeyState.PRESSED )) {

					keyStates.put(key,KeyState.FIRST_RELEASED);

				} else if (keyState == KeyState.FIRST_RELEASED) {

					keyStates.put(key, KeyState.RELEASED);
					keyEvents.add(new KeyEvent(key, KeyState.RELEASED));

					changed.remove(key);

				}
			}
		}

		poll(listener);
		
		changed.unlock();
	}

	private KeyState getState(Integer key) {
		KeyState state = keyStates.get(key);
		if (state == null) {
			state = KeyState.RELEASED;
		}
			
		return state;
	}

	public void poll(KeyEventListener listener) {

		List<KeyEvent> events = new CopyOnWriteArrayList<KeyEvent>(keyEvents);
		
		for (KeyEvent event: events) {
			listener.updateKeyEvent(event);
		}

		keyEvents.clear();
	}

	public void keyPressed( java.awt.event.KeyEvent ke ) {

		int code = getKeyFromEvent(ke);

		keys.put(code, true);

		changed.add(code);

		ke.consume();
	}

	public void keyReleased( java.awt.event.KeyEvent ke ) {

		int code = getKeyFromEvent(ke);

		keys.put(code, false);

		changed.add(code);

		ke.consume();
	}

	@Override
	public void keyTyped( java.awt.event.KeyEvent ke) {

		int code = getKeyFromEvent(ke);

		char c = ke.getKeyChar();

		//TODO Ajeitar o typed
		if ( c != KeyEvent.CHAR_UNDEFINED ) {
			keyEvents.add(new KeyEvent(code, c, KeyState.TYPED));
		}

		ke.consume();
	}

	private int getKeyFromEvent(java.awt.event.KeyEvent ke) {
		int code = ke.getKeyCode();

		if (ke.getKeyLocation() != java.awt.event.KeyEvent.KEY_LOCATION_STANDARD) {
			code+=ke.getKeyLocation()*100;
		}

		return code;
	}

	/*public List<KeyEvent> getEvents() {
		return keyEvents;
	}
	 */

	public void init() {
		/*Field[] fields = KeyEvent.class.getDeclaredFields();

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
		}*/
	}

	public boolean hasPendingEvent() {
		return changed.getSet().size() > 0;
	}

}