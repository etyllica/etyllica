package br.com.etyllica.core.loader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import br.com.etyllica.core.Updatable;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.input.InputKeyListener;
import br.com.etyllica.core.input.joystick.Joystick;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class JoystickLoader extends LoaderImpl implements Updatable{

	private static JoystickLoader instance = null;

	public static JoystickLoader getInstance() {
		if(instance==null){
			instance = new JoystickLoader();
		}

		return instance;
	}
	
	private InputKeyListener listener;
	
	private int joysticks = 10;

	private List<KeyEvent> joystickEvents = new ArrayList<KeyEvent>();

	private Map<Integer, Joystick> inputStreams = new HashMap<Integer, Joystick>();
	
	public void start(){

		int j=0;

		for(;j<joysticks;j++){

			try {
				this.inputStreams.put(j, new Joystick(j));
				System.out.println("Joystick "+j+ " found!");

			} catch (FileNotFoundException e) {

				System.out.println("Joystick "+j+ " not found.");
				
				//e.printStackTrace();
				
				return;
				
			}
	

		}
		
		this.joysticks = j;
	
	}

	public List<KeyEvent> getJoyEvents() {
		return joystickEvents;
	}

	public void update(long now){
		
		for(Entry<Integer, Joystick> entry: inputStreams.entrySet()){
			listen(entry.getKey());
		}
		
		notifyListener();

	}
	
	private void notifyListener(){

		for(KeyEvent event: joystickEvents){
			listener.updateJoystickEvent(event);
		}

		joystickEvents.clear();

	}

	private void listen(Integer id){

		KeyEvent event = inputStreams.get(id).listen();
		
		if(event!=null){
			joystickEvents.add(event);
		}		
		
	}

	public InputKeyListener getListener() {
		return listener;
	}

	public void setListener(InputKeyListener listener) {
		this.listener = listener;
	}
	
}
