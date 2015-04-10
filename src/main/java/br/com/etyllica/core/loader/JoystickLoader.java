package br.com.etyllica.core.loader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

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

public class JoystickLoader extends LoaderImpl implements Updatable, Runnable {

	private static JoystickLoader instance = null;

	public static JoystickLoader getInstance() {
		if(instance==null){
			instance = new JoystickLoader();
		}

		return instance;
	}
	
	private boolean initialized = false;
	
	private InputKeyListener listener;
	
	private int updateDelay = 5;//5 ms
	
	private int joysticks = 5;

	private List<KeyEvent> joystickEvents = new ArrayList<KeyEvent>();

	private Map<Integer, Joystick> inputStreams = new HashMap<Integer, Joystick>();
	
	private ScheduledExecutorService executor;
	
	private ScheduledFuture<?> future;
					
	public void init(int joysticks) {
		this.joysticks = joysticks;
		initLoader();
		
		executor = Executors.newSingleThreadScheduledExecutor();
		future = executor.scheduleAtFixedRate(this, updateDelay, updateDelay, TimeUnit.MILLISECONDS);
	}
	
	@Override
	public void initLoader() {

		initialized = true;
		
		int j = 0;

		for(;j<joysticks;j++) {

			try {
				this.inputStreams.put(j, new Joystick(j));
				System.out.println("Joystick "+j+ " found!");

			} catch (FileNotFoundException e) {
				System.out.println("Joystick "+j+ " not found.");								
				break;
			}
		}
		
		this.joysticks = j;
	}
	
	@Override
	public void run() {
		
		if(!initialized) {
			init(joysticks);
		}
			
		update(0);
	}

	public List<KeyEvent> getJoyEvents() {
		return joystickEvents;
	}

	public void update(long now) {
				
		for(Entry<Integer, Joystick> entry: inputStreams.entrySet()) {
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

		List<KeyEvent> eventList = inputStreams.get(id).listen();
		
		if(!eventList.isEmpty()) {
			joystickEvents.addAll(eventList);
		}
		
	}

	public InputKeyListener getListener() {
		return listener;
	}

	public void setListener(InputKeyListener listener) {
		this.listener = listener;
	}
	
	public ScheduledFuture<?> getFuture() {
		return future;
	}
	
}
