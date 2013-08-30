package br.com.etyllica.core.input.joystick;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import br.com.etyllica.core.event.KeyboardEvent;
import br.com.etyllica.core.event.Tecla;
import br.com.etyllica.core.input.keyboard.KeyState;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Joystick{

	private List<KeyboardEvent> joyEvents = new ArrayList<KeyboardEvent>();
	
	private Map<Integer, FileInputStream> iss = new HashMap<Integer, FileInputStream>();

	//Define constants
	private static final int JS_EVENT_BUTTON = 0x01;    /* button pressed/released */
	private static final int JS_EVENT_AXIS = 0x02;   /* joystick moved */
	private static final int JS_EVENT_INIT = 0x80;    /* initial state of device */

	private final int MAX_AXIS_MOVEMENT = 32767;
	private final int MIN_AXIS_MOVEMENT = -32767;

	private final String JOYSTICK_DIRECTORY = "/dev/input/js";

	public Joystick(int joysticks) {
		super();
		
		/*int j=0;
		
		for(;j<joysticks;j++){
			
			try {

				this.iss.put(j, new FileInputStream(JOYSTICK_DIRECTORY+j));
				System.out.println("Joystick "+j+ "found.");
		
			} catch (FileNotFoundException e) {

				System.err.println("Joystick "+j+" not found.");
				//e.printStackTrace();
			}
			
		}*/
		
		/*if(j==1){
			System.out.println("Found "+j+" Joystick.");	
		}else if(j>1){
			System.out.println("Found "+j+" Joysticks.");	
		}*/
		
		
		
	}
	
/*	public Joystick(String joystickDirectory) {
		super();
		
		try {

			this.iss.put(0, new FileInputStream(joystickDirectory));
	
		} catch (FileNotFoundException e) {

			System.err.println("Joystick not found: "+joystickDirectory);
			//e.printStackTrace();
		}


	}*/
	
	public List<KeyboardEvent> getJoyEvents() {
		return joyEvents;
	}

	public void setJoyEvents(List<KeyboardEvent> joyEvents) {
		this.joyEvents = joyEvents;
	}


	public void poll(){

		for(Entry<Integer, FileInputStream> entry: iss.entrySet()){
			listen(entry.getKey(), entry.getValue());
		}		
		
	}

	private void listen(Integer id, FileInputStream is){

		byte[] buf = new byte[8];

		int n = 0;

		try {
			n = is.read(buf, 0, 8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (n == 8) {
			long time = buf[3] << 24
					| (buf[2] & 0xff) << 16
					| (buf[1] & 0xff) << 8 | (buf[0] & 0xff);
			int value = buf[5] << 8 | (buf[4] & 0xff);
			int type = buf[6] & 0xff;
			int channel = buf[7] & 0xff;

			//Lógica

			if (type == JS_EVENT_AXIS) {

				switch (channel) {

				case 0:
					joyEvents.add(new KeyboardEvent(Tecla.JOYSTICK_LEFT.getCode(), value, KeyState.PRESSED));
					System.out.println("Left?");
				case 2:
					joyEvents.add(new KeyboardEvent(Tecla.JOYSTICK_RIGHT.getCode(), value,  KeyState.PRESSED));
					System.out.println("Right?");
					
					break;
				case 1:
					joyEvents.add(new KeyboardEvent(Tecla.JOYSTICK_UP.getCode(), value, KeyState.PRESSED));
					System.out.println("Up?");
				case 3:
					joyEvents.add(new KeyboardEvent(Tecla.JOYSTICK_DOWN.getCode(), value, KeyState.PRESSED));
					System.out.println("Down?");

					break;				
				default:
					break;
				}

			} else if (type == JS_EVENT_BUTTON) {

				if(value==1){
					joyEvents.add(new KeyboardEvent(channel, KeyState.PRESSED));
					System.out.println("Button "+channel+" Pressed.");
				}else{
					joyEvents.add(new KeyboardEvent(channel, KeyState.RELEASED));
					System.out.println("Button "+channel+" Released.");
				}

			}


		} else {
			System.err.println("only " + n + " of 8 bytes read");
		}


	}
}
