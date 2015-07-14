package br.com.etyllica.core.input.mouse;


/**
 * Reference: http://docs.oracle.com/javase/7/docs/api/java/awt/event/MouseEvent.html
 */
public enum MouseButton {

	MOUSE_BUTTON_LEFT(1),
	MOUSE_BUTTON_MIDDLE(2),
	MOUSE_BUTTON_RIGHT(3),	
	MOUSE_WHEEL_UP(507),
	MOUSE_WHEEL_DOWN(508),		
	
	MOUSE_NONE(0);	
	
	private final int code;

	MouseButton(int code) {
		this.code = code;
	}
	
	public final int getCode() { 
		return code; 
	}
	
}
