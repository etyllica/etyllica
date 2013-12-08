package br.com.etyllica.core.input.joystick;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.KeyState;

public class Joystick {

	private static final int JS_EVENT_BUTTON = 0x01;
	private static final int JS_EVENT_AXIS = 0x02;
	private static final int JS_EVENT_INIT = 0x80;

	public static final int MAX_AXIS_MOVEMENT = 32767;
	public static final int MIN_AXIS_MOVEMENT = -32767;

	private final String JOYSTICK_DIRECTORY = "/dev/input/js";

	private FileInputStream inputStream;

	private int id;

	public Joystick(int id) throws FileNotFoundException{
		super();

		this.id = id;

		inputStream = new FileInputStream(JOYSTICK_DIRECTORY+id);

	}

	public FileInputStream getInputStream() {
		return inputStream;
	}

	public KeyEvent listen(){

		byte[] buf = new byte[8];

		int n = 0;

		try {
			n = inputStream.read(buf, 0, 8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (n == 8) {
			long time = buf[3] << 24 | (buf[2] & 0xff) << 16 | (buf[1] & 0xff) << 8 | (buf[0] & 0xff);
			int value = buf[5] << 8 | (buf[4] & 0xff);
			int type = buf[6] & 0xff;
			int channel = buf[7] & 0xff;

			if (type == JS_EVENT_AXIS) {

				switch (channel) {

				case 0:
				case 2:

					if(value>0){
						return new KeyEvent(id, KeyEvent.TSK_JOYSTICK_RIGHT, value, KeyState.PRESSED);
					}else if(value<0){					
						return new KeyEvent(id, KeyEvent.TSK_JOYSTICK_LEFT, value,  KeyState.PRESSED);
					}else{
						return new KeyEvent(id, KeyEvent.TSK_JOYSTICK_CENTER_X, value,  KeyState.PRESSED);
					}

				case 1:
				case 3:

					if(value>0){
						return new KeyEvent(id, KeyEvent.TSK_JOYSTICK_DOWN, value, KeyState.PRESSED);
					}else if(value<0){					
						return new KeyEvent(id,KeyEvent.TSK_JOYSTICK_UP, value,  KeyState.PRESSED);
					}else{
						return new KeyEvent(id, KeyEvent.TSK_JOYSTICK_CENTER_Y, value,  KeyState.PRESSED);
					}

				default:
					break;
				}

			} else if (type == JS_EVENT_BUTTON) {

				int buttonCode = (KeyEvent.TSK_JOYSTICK_BUTTON_1)+channel;

				if(value==1){
					return new KeyEvent(id, buttonCode, 0,  KeyState.PRESSED);
				}else{
					return new KeyEvent(id, buttonCode, 0, KeyState.RELEASED);
				}

			}			

		} else {
			System.err.println("only " + n + " of 8 bytes read");
		}
		
		return null;

	}

}
