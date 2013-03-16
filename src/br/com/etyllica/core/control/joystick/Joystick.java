package br.com.etyllica.core.control.joystick;

import java.util.List;


/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public class Joystick {

	private String name;
	
	private List<JoystickEvent> events;
	
	public Joystick(){
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<JoystickEvent> getEvents() {
		return events;
	}

	public void setEvents(List<JoystickEvent> events) {
		this.events = events;
	}
	
	
	
}
