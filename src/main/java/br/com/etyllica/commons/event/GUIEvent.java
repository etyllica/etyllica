package br.com.etyllica.commons.event;

/**
 * 
 * @author yuripourre
 *
 */

public enum GUIEvent {
	
	BLOCK_CLICK, //When pointer is over something already
	
	GAIN_FOCUS,
	LOST_FOCUS,
	//TAB
	NEXT_COMPONENT,
	
	MOUSE_OVER,
	//MOUSE_OVER_UNCLICKABLE,
	
	//Clickable Components
	MOUSE_OVER_WITH_FOCUS,
	MOUSE_OUT,
	MOUSE_IN,
	
	MOUSE_LEFT_BUTTON_DOWN,
	MOUSE_LEFT_BUTTON_UP,
	MOUSE_LEFT_BUTTON_DOUBLE_CLICK,
	
	MOUSE_RIGHT_BUTTON_DOWN,
	MOUSE_RIGHT_BUTTON_UP,
	MOUSE_RIGHT_BUTTON_DOUBLE_CLICK,
	
	MOUSE_MIDDLE_BUTTON_DOWN,
	MOUSE_MIDDLE_BUTTON_UP,
	MOUSE_MIDDLE_BUTTON_DOUBLE_CLICK,
	
	COMPONENT_CHANGED,
	
	//Application Events
	ENABLE_FULL_SCREEN,
	DISABLE_FULL_SCREEN,
	
	APPLICATION_CHANGED,
	ZOOM_APPLICATION,
	PAUSE_APPLICATION,
	
	CLOSE_APPLICATION,
	
	WINDOW_CLOSE,
	WINDOW_MOVE,
	
	LANGUAGE_CHANGED,
	THEME_CHANGED,
	
	REQUEST_FOCUS,
	UPDATE_MOUSE,
	
	NONE;
	
}
