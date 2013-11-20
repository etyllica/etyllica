package br.com.etyllica.core.loader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.input.joystick.Joystick;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class JoystickLoader extends LoaderImpl{

	private static JoystickLoader instance = null;

	public static JoystickLoader getInstance() {
		if(instance==null){
			instance = new JoystickLoader();
		}

		return instance;
	}
	
	private int joysticks = 10;
	
	private boolean started = false;

	private List<KeyEvent> joystickEvents = new ArrayList<KeyEvent>();

	private Map<Integer, Joystick> inputStreams = new HashMap<Integer, Joystick>();
	
	private ScheduledExecutorService executor;
	
	private final int UPDATE_DELAY = 20;
	
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
		executor = Executors.newScheduledThreadPool(this.joysticks);
		
		for(int i=0;i<joysticks;i++){
			System.out.println("Create Handler");
			executor.scheduleAtFixedRate(new JoystickHandler(i), 0, UPDATE_DELAY, TimeUnit.MILLISECONDS);
		}
		
		started = true;
		
	}
	
	private class JoystickHandler implements Runnable{
		
		private int id;
		
		public JoystickHandler(int id){
			super();
			this.id = id;
		}
		
		public void run() {
			listen(id);
		}
		
	}

	public List<KeyEvent> getJoyEvents() {
		return joystickEvents;
	}

	public void poll(){
		
		for(Entry<Integer, Joystick> entry: inputStreams.entrySet()){
			listen(entry.getKey());
		}

	}

	private void listen(Integer id){

		KeyEvent event = inputStreams.get(id).listen();
		
		if(event!=null){
			joystickEvents.add(event);
		}		
		
	}

	public boolean isStarted() {
		return started;
	}
	
}
