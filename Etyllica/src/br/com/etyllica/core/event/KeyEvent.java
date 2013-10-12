package br.com.etyllica.core.event;


import br.com.etyllica.core.input.keyboard.KeyState;

public class KeyEvent{
	
	private int id = 0;
	
	private int key;
	
	private KeyState state;
	
	private int amount = Character.getNumericValue('\0');
	
	public KeyEvent(int key, KeyState state) {
		super();
		
		this.key = key;
		this.state = state;
	}
	
	public KeyEvent(int key, int amount, KeyState state) {
		super();
		
		this.key = key;
		this.amount = amount;
		this.state = state;
	}
	
	public KeyEvent(int id, int key, int amount, KeyState state) {
		super();
		
		this.id = id;
		this.key = key;
		this.amount = amount;
		this.state = state;
	}

	public boolean isKeyDown(int keyCode){
		
		if(this.key == keyCode){
			return state==KeyState.PRESSED;
		}
		
		return false;
	}
	
	public boolean isKeyUp(int keyCode){
		
		if(this.key == keyCode){
			return state==KeyState.RELEASED;
		}
		
		return false;
	}
		
	public char getChar(){
		
		if(amount>Character.MIN_VALUE&&amount<Character.MAX_VALUE){
			return (char)amount;
		}
		
		return '\0';
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}
	
	//List of Inputs
	public static final int TSK_MENOS = java.awt.event.KeyEvent.VK_MINUS;
	public static final int TSK_MAIS = java.awt.event.KeyEvent.VK_PLUS;
	public static final int TSK_IGUAL = java.awt.event.KeyEvent.VK_EQUALS;
	
	public static final int TSK_A = java.awt.event.KeyEvent.VK_A;
	public static final int TSK_B = java.awt.event.KeyEvent.VK_B;
	public static final int TSK_C = java.awt.event.KeyEvent.VK_C;
	public static final int TSK_D = java.awt.event.KeyEvent.VK_D;
	public static final int TSK_E = java.awt.event.KeyEvent.VK_E;
	public static final int TSK_F = java.awt.event.KeyEvent.VK_F;
	public static final int TSK_G = java.awt.event.KeyEvent.VK_G;
	public static final int TSK_H = java.awt.event.KeyEvent.VK_H;
	public static final int TSK_I = java.awt.event.KeyEvent.VK_I;
	public static final int TSK_J = java.awt.event.KeyEvent.VK_J;
	public static final int TSK_K = java.awt.event.KeyEvent.VK_K;
	public static final int TSK_L = java.awt.event.KeyEvent.VK_L;
	public static final int TSK_M = java.awt.event.KeyEvent.VK_M;
	public static final int TSK_N = java.awt.event.KeyEvent.VK_N;
	public static final int TSK_O = java.awt.event.KeyEvent.VK_O;
	public static final int TSK_P = java.awt.event.KeyEvent.VK_P;
	public static final int TSK_Q = java.awt.event.KeyEvent.VK_Q;
	public static final int TSK_R = java.awt.event.KeyEvent.VK_R;
	public static final int TSK_S = java.awt.event.KeyEvent.VK_S;
	public static final int TSK_T = java.awt.event.KeyEvent.VK_T;
	public static final int TSK_U = java.awt.event.KeyEvent.VK_U;
	public static final int TSK_V = java.awt.event.KeyEvent.VK_V;		
	public static final int TSK_W = java.awt.event.KeyEvent.VK_W;
	public static final int TSK_X = java.awt.event.KeyEvent.VK_X;
	public static final int TSK_Y = java.awt.event.KeyEvent.VK_Y;
	public static final int TSK_Z = java.awt.event.KeyEvent.VK_Z;
		
	public static final int TSK_SETA_CIMA = java.awt.event.KeyEvent.VK_UP;
	public static final int TSK_SETA_BAIXO = java.awt.event.KeyEvent.VK_DOWN;
	public static final int TSK_SETA_ESQUERDA = java.awt.event.KeyEvent.VK_LEFT;
	public static final int TSK_SETA_DIREITA = java.awt.event.KeyEvent.VK_RIGHT;
	
	public static final int TSK_AT = java.awt.event.KeyEvent.VK_AT;
	public static final int TSK_ASTERISK = java.awt.event.KeyEvent.VK_ASTERISK;
		
	public static final int TSK_QUOTE = java.awt.event.KeyEvent.VK_QUOTE;
	public static final int TSK_DOUBLE_QUOTE = java.awt.event.KeyEvent.VK_QUOTEDBL;
	public static final int TSK_BACK_QUOTE = java.awt.event.KeyEvent.VK_BACK_QUOTE;
		
	public static final int TSK_UP_ARROW = java.awt.event.KeyEvent.VK_UP;
	public static final int TSK_DOWN_ARROW = java.awt.event.KeyEvent.VK_DOWN;
	public static final int TSK_LEFT_ARROW = java.awt.event.KeyEvent.VK_LEFT;
	public static final int TSK_RIGHT_ARROW = java.awt.event.KeyEvent.VK_RIGHT;
		
	public static final int TSK_0 = java.awt.event.KeyEvent.VK_0;
	public static final int TSK_1 = java.awt.event.KeyEvent.VK_1;
	public static final int TSK_2 = java.awt.event.KeyEvent.VK_2;
	public static final int TSK_3 = java.awt.event.KeyEvent.VK_3;
	public static final int TSK_4 = java.awt.event.KeyEvent.VK_4;
	public static final int TSK_5 = java.awt.event.KeyEvent.VK_5;
	public static final int TSK_6 = java.awt.event.KeyEvent.VK_6;
	public static final int TSK_7 = java.awt.event.KeyEvent.VK_7;
	public static final int TSK_8 = java.awt.event.KeyEvent.VK_8;
	public static final int TSK_9 = java.awt.event.KeyEvent.VK_9;
			
	public static final int TSK_F1 = java.awt.event.KeyEvent.VK_F1;
	public static final int TSK_F2 = java.awt.event.KeyEvent.VK_F2;
	public static final int TSK_F3 = java.awt.event.KeyEvent.VK_F3;
	public static final int TSK_F4 = java.awt.event.KeyEvent.VK_F4;
	public static final int TSK_F5 = java.awt.event.KeyEvent.VK_F5;
	public static final int TSK_F6 = java.awt.event.KeyEvent.VK_F6;
	public static final int TSK_F7 = java.awt.event.KeyEvent.VK_F7;
	public static final int TSK_F8 = java.awt.event.KeyEvent.VK_F8;
	public static final int TSK_F9 = java.awt.event.KeyEvent.VK_F9;
	public static final int TSK_F10 = java.awt.event.KeyEvent.VK_F10;
	public static final int TSK_F11 = java.awt.event.KeyEvent.VK_F11;
	public static final int TSK_F12 = java.awt.event.KeyEvent.VK_F12;
	public static final int TSK_F13 = java.awt.event.KeyEvent.VK_F13;
	public static final int TSK_F14 = java.awt.event.KeyEvent.VK_F14;
	public static final int TSK_F15 = java.awt.event.KeyEvent.VK_F15;
	public static final int TSK_F16 = java.awt.event.KeyEvent.VK_F16;
	public static final int TSK_F17 = java.awt.event.KeyEvent.VK_F17;
	public static final int TSK_F18 = java.awt.event.KeyEvent.VK_F18;
	public static final int TSK_F19 = java.awt.event.KeyEvent.VK_F19;
	public static final int TSK_F20 = java.awt.event.KeyEvent.VK_F20;
	public static final int TSK_F21 = java.awt.event.KeyEvent.VK_F21;
	public static final int TSK_F22 = java.awt.event.KeyEvent.VK_F22;
	
	public static final int TSK_DEL = java.awt.event.KeyEvent.VK_DELETE;
	public static final int TSK_BACK_SPACE = java.awt.event.KeyEvent.VK_BACK_SPACE;
	public static final int TSK_TAB = java.awt.event.KeyEvent.VK_TAB;
	public static final int TSK_ESPACO = java.awt.event.KeyEvent.VK_SPACE;
	public static final int TSK_SPACE = java.awt.event.KeyEvent.VK_SPACE;
	public static final int TSK_ENTER = java.awt.event.KeyEvent.VK_ENTER;
	public static final int TSK_INSERT = java.awt.event.KeyEvent.VK_INSERT;
	public static final int TSK_HOME = java.awt.event.KeyEvent.VK_HOME;
	public static final int TSK_END = java.awt.event.KeyEvent.VK_END;
	public static final int TSK_ESC = java.awt.event.KeyEvent.VK_ESCAPE;
		
	private static final int TSK_ALT = java.awt.event.KeyEvent.VK_ALT;	
	public static final int TSK_ALT_DIREITA = java.awt.event.KeyEvent.KEY_LOCATION_RIGHT*100+TSK_ALT;
	public static final int TSK_ALT_ESQUERDA = java.awt.event.KeyEvent.KEY_LOCATION_LEFT*100+TSK_ALT;
	
	public static final int TSK_ALT_GRAPH = java.awt.event.KeyEvent.VK_ALT_GRAPH;
	public static final int TSK_ALT_RIGHT = java.awt.event.KeyEvent.KEY_LOCATION_RIGHT*100+TSK_ALT;
	public static final int TSK_ALT_LEFT = java.awt.event.KeyEvent.KEY_LOCATION_LEFT*100+TSK_ALT;
	
	private static final int TSK_CTRL = java.awt.event.KeyEvent.VK_CONTROL;
	public static final int TSK_CTRL_DIREITA = java.awt.event.KeyEvent.KEY_LOCATION_RIGHT*100+TSK_CTRL;
	public static final int TSK_CTRL_ESQUERDA = java.awt.event.KeyEvent.KEY_LOCATION_LEFT*100+TSK_CTRL;
	
	public static final int TSK_CTRL_RIGHT = java.awt.event.KeyEvent.KEY_LOCATION_RIGHT*100+TSK_CTRL;
	public static final int TSK_CTRL_LEFT = java.awt.event.KeyEvent.KEY_LOCATION_LEFT*100+TSK_CTRL;
	
	private static final int TSK_SHIFT = java.awt.event.KeyEvent.VK_SHIFT;
	public static final int TSK_SHIFT_DIREITA = java.awt.event.KeyEvent.KEY_LOCATION_RIGHT*100+TSK_SHIFT;
	public static final int TSK_SHIFT_ESQUERDA = java.awt.event.KeyEvent.KEY_LOCATION_LEFT*100+TSK_SHIFT;
	
	public static final int TSK_SHIFT_RIGHT = java.awt.event.KeyEvent.KEY_LOCATION_RIGHT*100+TSK_SHIFT;
	public static final int TSK_SHIFT_LEFT = java.awt.event.KeyEvent.KEY_LOCATION_LEFT*100+TSK_SHIFT;
		
	public static final int TSK_NUMPAD_0 = java.awt.event.KeyEvent.KEY_LOCATION_NUMPAD*100+TSK_0+48;
	public static final int TSK_NUMPAD_1 = java.awt.event.KeyEvent.KEY_LOCATION_NUMPAD*100+TSK_1+48;
	public static final int TSK_NUMPAD_2 = java.awt.event.KeyEvent.KEY_LOCATION_NUMPAD*100+TSK_2+48;
	public static final int TSK_NUMPAD_3 = java.awt.event.KeyEvent.KEY_LOCATION_NUMPAD*100+TSK_3+48;
	public static final int TSK_NUMPAD_4 = java.awt.event.KeyEvent.KEY_LOCATION_NUMPAD*100+TSK_4+48;
	public static final int TSK_NUMPAD_5 = java.awt.event.KeyEvent.KEY_LOCATION_NUMPAD*100+TSK_5+48;
	public static final int TSK_NUMPAD_6 = java.awt.event.KeyEvent.KEY_LOCATION_NUMPAD*100+TSK_6+48;
	public static final int TSK_NUMPAD_7 = java.awt.event.KeyEvent.KEY_LOCATION_NUMPAD*100+TSK_7+48;
	public static final int TSK_NUMPAD_8 = java.awt.event.KeyEvent.KEY_LOCATION_NUMPAD*100+TSK_8+48;
	public static final int TSK_NUMPAD_9 = java.awt.event.KeyEvent.KEY_LOCATION_NUMPAD*100+TSK_9+48;
	
	public static final int TSK_NUMPAD_DEL = java.awt.event.KeyEvent.KEY_LOCATION_NUMPAD*100+TSK_DEL-19;
	public static final int TSK_NUMPAD_INS = java.awt.event.KeyEvent.KEY_LOCATION_NUMPAD*100+TSK_INSERT;
	public static final int TSK_NUMPAD_ENTER = java.awt.event.KeyEvent.KEY_LOCATION_NUMPAD*100+TSK_ENTER;
	
	public static final int TSK_NUMPAD_SETA_BAIXO = java.awt.event.KeyEvent.KEY_LOCATION_NUMPAD*100+TSK_SETA_BAIXO+185;
	public static final int TSK_NUMPAD_SETA_CIMA = java.awt.event.KeyEvent.KEY_LOCATION_NUMPAD*100+TSK_SETA_CIMA+186;
	public static final int TSK_NUMPAD_SETA_DIREITA = java.awt.event.KeyEvent.KEY_LOCATION_NUMPAD*100+TSK_SETA_DIREITA+188;
	public static final int TSK_NUMPAD_SETA_ESQUERDA = java.awt.event.KeyEvent.KEY_LOCATION_NUMPAD*100+TSK_SETA_ESQUERDA+189;
			
	public static final int TSK_ABRE_COLCHETES = java.awt.event.KeyEvent.VK_OPEN_BRACKET;
	public static final int TSK_FECHA_COLCHETES = java.awt.event.KeyEvent.VK_CLOSE_BRACKET;
	
	public static final int TSK_ABRE_CHAVES = java.awt.event.KeyEvent.VK_BRACELEFT;
	public static final int TSK_FECHA_CHAVES = java.awt.event.KeyEvent.VK_BRACERIGHT;
	
	public static final int TSK_ARROBA = java.awt.event.KeyEvent.VK_AT;
	public static final int TSK_PONTO = java.awt.event.KeyEvent.VK_PERIOD;
	public static final int TSK_VIRGULA = java.awt.event.KeyEvent.VK_COMMA;
	public static final int TSK_DOIS_PONTOS = java.awt.event.KeyEvent.VK_COLON;
	
	public static final int TSK_HIRAGANA = java.awt.event.KeyEvent.VK_JAPANESE_HIRAGANA;
	public static final int TSK_KATAKANA = java.awt.event.KeyEvent.VK_JAPANESE_KATAKANA;
	public static final int TSK_ROMAJI = java.awt.event.KeyEvent.VK_JAPANESE_ROMAN;
	public static final int TSK_KANJI = java.awt.event.KeyEvent.VK_KANJI;
	
	public static final int TSK_NUM_LOCK = java.awt.event.KeyEvent.VK_NUM_LOCK;
	
	public static final int TSK_SUPER = java.awt.event.KeyEvent.VK_WINDOWS;
		
	public static final int TSK_JOYSTICK_UP = JoystickEvent.UP;
	public static final int TSK_JOYSTICK_DOWN = JoystickEvent.DOWN;
	public static final int TSK_JOYSTICK_CENTER_Y = JoystickEvent.CENTER_Y;
	
	public static final int TSK_JOYSTICK_LEFT = JoystickEvent.LEFT;
	public static final int TSK_JOYSTICK_RIGHT = JoystickEvent.RIGHT;
	public static final int TSK_JOYSTICK_CENTER_X = JoystickEvent.CENTER_X;

	public static final int TSK_JOYSTICK_BUTTON_1 = JoystickEvent.BUTTON_1;
	public static final int TSK_JOYSTICK_BUTTON_2 = JoystickEvent.BUTTON_2;
	public static final int TSK_JOYSTICK_BUTTON_3 = JoystickEvent.BUTTON_3;
	public static final int TSK_JOYSTICK_BUTTON_4 = JoystickEvent.BUTTON_4;
	public static final int TSK_JOYSTICK_BUTTON_5 = JoystickEvent.BUTTON_5;
	public static final int TSK_JOYSTICK_BUTTON_6 = JoystickEvent.BUTTON_6;
	public static final int TSK_JOYSTICK_BUTTON_7 = JoystickEvent.BUTTON_7;
	public static final int TSK_JOYSTICK_BUTTON_8 = JoystickEvent.BUTTON_8;
	public static final int TSK_JOYSTICK_BUTTON_9 = JoystickEvent.BUTTON_9;
	public static final int TSK_JOYSTICK_BUTTON_10 = JoystickEvent.BUTTON_10;
	public static final int TSK_JOYSTICK_BUTTON_11 = JoystickEvent.BUTTON_11;
	public static final int TSK_JOYSTICK_BUTTON_12 = JoystickEvent.BUTTON_12;
	
	public static final int TSK_NONE = 0;

}
