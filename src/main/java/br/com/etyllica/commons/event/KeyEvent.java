package br.com.etyllica.commons.event;


public class KeyEvent {

	private int id = 0; 
	private int key;  
	private KeyState state;
	private int amount = Character.getNumericValue('\0');  
	private long timestamp = -1;  
	private boolean consumed = false;

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

	public KeyEvent(int id, int key, int amount, KeyState state, long timestamp) {
		super();

		this.id = id;
		this.key = key;
		this.amount = amount;
		this.state = state;
		this.timestamp = timestamp; 
	}

	public boolean isKeyDown(int keyCode) {
		if(consumed) {
			return false;
		}

		return this.key == keyCode && state == KeyState.PRESSED;   
	}

	public boolean isAnyKeyDown(int ... keyCodes) {
		if(consumed)
			return false;

		for(int keyCode : keyCodes) {
			if(isKeyDown(keyCode)) {
				return true;
			}
		}

		return false; 
	}

	public boolean isKeyUp(int keyCode) {
		if(consumed) {
			return false;
		}

		return this.key == keyCode && state == KeyState.RELEASED;   
	}

	public boolean isAnyKeyUp(int ... keyCodes) {
		if(consumed) {
			return false;
		}

		for(int keyCode : keyCodes) {
			if(isKeyUp(keyCode)) {
				return true;
			}
		}
		return false; 
	}

	public char getChar() {

		if(amount > Character.MIN_VALUE && amount < Character.MAX_VALUE) {
			return (char) amount;
		}

		return CHAR_UNDEFINED; 
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

	public KeyState getState() {
		return state; 
	} 
	
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp; 
	}

	public long getTimestamp() {

		if(timestamp < 0) {
			return System.currentTimeMillis();
		}

		return timestamp; 
	} 
	
	public void consume() {
		consumed = true; 
	}
	
	public void setConsumed(boolean consumed) {
		this.consumed = consumed; 
	}

	public boolean isConsumed() {
		return consumed;
	}

	public static final char CHAR_UNDEFINED = 65535;  //Locations
	public static final int KEY_LOCATION_LEFT = 2;
	public static final int KEY_LOCATION_NUMPAD = 4;
	public static final int KEY_LOCATION_RIGHT = 3;
	public static final int KEY_LOCATION_STANDARD = 1;
	public static final int KEY_LOCATION_UNKNOWN = 0;  //Keys
	
	//Virtual Keys
	public static final int VK_0 = 48;
	public static final int VK_1 = 49;
	public static final int VK_2 = 50;
	public static final int VK_3 = 51;
	public static final int VK_4 = 52;
	public static final int VK_5 = 53;
	public static final int VK_6 = 54;
	public static final int VK_7 = 55;
	public static final int VK_8 = 56;
	public static final int VK_9 = 57;
	public static final int VK_A = 65;
	public static final int VK_ACCEPT = 30;
	public static final int VK_ADD = 107;
	public static final int VK_AGAIN = 65481;
	public static final int VK_ALL_CANDIDATES = 256;
	public static final int VK_ALPHANUMERIC = 240;
	public static final int VK_ALT = 18;
	public static final int VK_ALT_GRAPH = 65406;
	public static final int VK_AMPERSAND = 150;
	public static final int VK_ASTERISK = 151;
	public static final int VK_AT = 512;
	public static final int VK_B = 66;
	public static final int VK_BACK_QUOTE = 192;
	public static final int VK_BACK_SLASH = 92;
	public static final int VK_BACK_SPACE = 8;
	public static final int VK_BEGIN = 65368;
	public static final int VK_BRACELEFT = 161;
	public static final int VK_BRACERIGHT = 162;
	public static final int VK_C = 67;
	public static final int VK_CANCEL = 3;
	public static final int VK_CAPS_LOCK = 20;
	public static final int VK_CIRCUMFLEX = 514;
	public static final int VK_CLEAR = 12;
	public static final int VK_CLOSE_BRACKET = 93;
	public static final int VK_CODE_INPUT = 258;
	public static final int VK_COLON = 513;
	public static final int VK_COMMA = 44;
	public static final int VK_COMPOSE = 65312;
	public static final int VK_CONTEXT_MENU = 525;
	public static final int VK_CONTROL = 17;
	public static final int VK_CONVERT = 28;
	public static final int VK_COPY = 65485;
	public static final int VK_CUT = 65489;
	public static final int VK_D = 68;
	public static final int VK_DEAD_ABOVEDOT = 134;
	public static final int VK_DEAD_ABOVERING = 136;
	public static final int VK_DEAD_ACUTE = 129;
	public static final int VK_DEAD_BREVE = 133;
	public static final int VK_DEAD_CARON = 138;
	public static final int VK_DEAD_CEDILLA = 139;
	public static final int VK_DEAD_CIRCUMFLEX = 130;
	public static final int VK_DEAD_DIAERESIS = 135;
	public static final int VK_DEAD_DOUBLEACUTE = 137;
	public static final int VK_DEAD_GRAVE = 128;
	public static final int VK_DEAD_IOTA = 141;
	public static final int VK_DEAD_MACRON = 132;
	public static final int VK_DEAD_OGONEK = 140;
	public static final int VK_DEAD_SEMIVOICED_SOUND = 143;
	public static final int VK_DEAD_TILDE = 131;
	public static final int VK_DEAD_VOICED_SOUND = 142;
	public static final int VK_DECIMAL = 110;
	public static final int VK_DELETE = 127;
	public static final int VK_DIVIDE = 111;
	public static final int VK_DOLLAR = 515;
	public static final int VK_DOWN = 40;
	public static final int VK_E = 69;
	public static final int VK_END = 35;
	public static final int VK_ENTER = 10;
	public static final int VK_EQUALS = 61;
	public static final int VK_ESCAPE = 27;
	public static final int VK_EURO_SIGN = 516;
	public static final int VK_EXCLAMATION_MARK = 517;
	public static final int VK_F = 70;
	public static final int VK_F1 = 112;
	public static final int VK_F10 = 121;
	public static final int VK_F11 = 122;
	public static final int VK_F12 = 123;
	public static final int VK_F13 = 61440;
	public static final int VK_F14 = 61441;
	public static final int VK_F15 = 61442;
	public static final int VK_F16 = 61443;
	public static final int VK_F17 = 61444;
	public static final int VK_F18 = 61445;
	public static final int VK_F19 = 61446;
	public static final int VK_F2 = 113;
	public static final int VK_F20 = 61447;
	public static final int VK_F21 = 61448;
	public static final int VK_F22 = 61449;
	public static final int VK_F23 = 61450;
	public static final int VK_F24 = 61451;
	public static final int VK_F3 = 114;
	public static final int VK_F4 = 115;
	public static final int VK_F5 = 116;
	public static final int VK_F6 = 117;
	public static final int VK_F7 = 118;
	public static final int VK_F8 = 119;
	public static final int VK_F9 = 120;
	public static final int VK_FINAL = 24;
	public static final int VK_FIND = 65488;
	public static final int VK_FULL_WIDTH = 243;
	public static final int VK_G = 71;
	public static final int VK_GREATER = 160;
	public static final int VK_H = 72;
	public static final int VK_HALF_WIDTH = 244;
	public static final int VK_HELP = 156;
	public static final int VK_HIRAGANA = 242;
	public static final int VK_HOME = 36;
	public static final int VK_I = 73;
	public static final int VK_INPUT_METHOD_ON_OFF = 263;
	public static final int VK_INSERT = 155;
	public static final int VK_INVERTED_EXCLAMATION_MARK = 518;
	public static final int VK_J = 74;
	public static final int VK_JAPANESE_HIRAGANA = 260;
	public static final int VK_JAPANESE_KATAKANA = 259;
	public static final int VK_JAPANESE_ROMAN = 261;
	public static final int VK_K = 75;
	public static final int VK_KANA = 21;
	public static final int VK_KANA_LOCK = 262;
	public static final int VK_KANJI = 25;
	public static final int VK_KATAKANA = 241;
	public static final int VK_KP_DOWN = 225;
	public static final int VK_KP_LEFT = 226;
	public static final int VK_KP_RIGHT = 227;
	public static final int VK_KP_UP = 224;
	public static final int VK_L = 76;
	public static final int VK_LEFT = 37;
	public static final int VK_LEFT_PARENTHESIS = 519;
	public static final int VK_LESS = 153;
	public static final int VK_M = 77;
	public static final int VK_META = 157;
	public static final int VK_MINUS = 45;
	public static final int VK_MODECHANGE = 31;
	public static final int VK_MULTIPLY = 106;
	public static final int VK_N = 78;
	public static final int VK_NONCONVERT = 29;
	public static final int VK_NUM_LOCK = 144;
	public static final int VK_NUMBER_SIGN = 520;
	public static final int VK_NUMPAD0 = 96;
	public static final int VK_NUMPAD1 = 97;
	public static final int VK_NUMPAD2 = 98;
	public static final int VK_NUMPAD3 = 99;
	public static final int VK_NUMPAD4 = 100;
	public static final int VK_NUMPAD5 = 101;
	public static final int VK_NUMPAD6 = 102;
	public static final int VK_NUMPAD7 = 103;
	public static final int VK_NUMPAD8 = 104;
	public static final int VK_NUMPAD9 = 105;
	public static final int VK_O = 79;
	public static final int VK_OPEN_BRACKET = 91;
	public static final int VK_P = 80;
	public static final int VK_PAGE_DOWN = 34;
	public static final int VK_PAGE_UP = 33;
	public static final int VK_PASTE = 65487;
	public static final int VK_PAUSE = 19;
	public static final int VK_PERIOD = 46;
	public static final int VK_PLUS = 521;
	public static final int VK_PREVIOUS_CANDIDATE = 257;
	public static final int VK_PRINTSCREEN = 154;
	public static final int VK_PROPS = 65482;
	public static final int VK_Q = 81;
	public static final int VK_QUOTE = 222;
	public static final int VK_QUOTEDBL = 152;
	public static final int VK_DOUBLE_QUOTE = VK_QUOTEDBL;
	public static final int VK_R = 82;
	public static final int VK_RIGHT = 39;
	public static final int VK_RIGHT_PARENTHESIS = 522;
	public static final int VK_ROMAN_CHARACTERS = 245;
	public static final int VK_S = 83;
	public static final int VK_SCROLL_LOCK = 145;
	public static final int VK_SEMICOLON = 59;
	public static final int VK_SEPARATER = 108;
	public static final int VK_SEPARATOR = 108;
	public static final int VK_SHIFT = 16;
	public static final int VK_SLASH = 47;
	public static final int VK_SPACE = 32;
	public static final int VK_STOP = 65480;
	public static final int VK_SUBTRACT = 109;
	public static final int VK_T = 84;
	public static final int VK_TAB = 9;
	public static final int VK_U = 85;
	public static final int VK_UNDEFINED = 0;
	public static final int VK_UNDERSCORE = 523;
	public static final int VK_UNDO = 65483;
	public static final int VK_UP = 38;
	public static final int VK_V = 86;
	public static final int VK_W = 87;
	public static final int VK_WINDOWS = 524;
	public static final int VK_X = 88;
	public static final int VK_Y = 89;
	public static final int VK_Z = 90;
	
	public static final int VK_ALT_RIGHT = KEY_LOCATION_RIGHT*100+VK_ALT;
	public static final int VK_ALT_LEFT = KEY_LOCATION_LEFT*100+VK_ALT;
	
	public static final int VK_CTRL = VK_CONTROL;
	public static final int VK_CTRL_RIGHT = KEY_LOCATION_RIGHT*100+VK_CTRL;
	public static final int VK_CTRL_LEFT = KEY_LOCATION_LEFT*100+VK_CTRL;  
		
	public static final int VK_SHIFT_RIGHT = KEY_LOCATION_RIGHT*100+VK_SHIFT;
	public static final int VK_SHIFT_LEFT = KEY_LOCATION_LEFT*100+VK_SHIFT;

	public static final int VK_NUMPAD_0 = KEY_LOCATION_NUMPAD*100+VK_0+48;
	public static final int VK_NUMPAD_1 = KEY_LOCATION_NUMPAD*100+VK_1+48;
	public static final int VK_NUMPAD_2 = KEY_LOCATION_NUMPAD*100+VK_2+48;
	public static final int VK_NUMPAD_3 = KEY_LOCATION_NUMPAD*100+VK_3+48;
	public static final int VK_NUMPAD_4 = KEY_LOCATION_NUMPAD*100+VK_4+48;
	public static final int VK_NUMPAD_5 = KEY_LOCATION_NUMPAD*100+VK_5+48;
	public static final int VK_NUMPAD_6 = KEY_LOCATION_NUMPAD*100+VK_6+48;
	public static final int VK_NUMPAD_7 = KEY_LOCATION_NUMPAD*100+VK_7+48;
	public static final int VK_NUMPAD_8 = KEY_LOCATION_NUMPAD*100+VK_8+48;
	public static final int VK_NUMPAD_9 = KEY_LOCATION_NUMPAD*100+VK_9+48; 
	public static final int VK_NUMPAD_DEL = KEY_LOCATION_NUMPAD*100+VK_DELETE-19;
	public static final int VK_NUMPAD_INS = KEY_LOCATION_NUMPAD*100+VK_INSERT;
	public static final int VK_NUMPAD_ENTER = KEY_LOCATION_NUMPAD*100+VK_ENTER; 
	public static final int VK_NUMPAD_DOWN_ARROW = KEY_LOCATION_NUMPAD*100+VK_DOWN+185;
	public static final int VK_NUMPAD_UP_ARROW = KEY_LOCATION_NUMPAD*100+VK_UP+186;
	public static final int VK_NUMPAD_RIGHT_ARROW = KEY_LOCATION_NUMPAD*100+VK_RIGHT+188;
	public static final int VK_NUMPAD_LEFT_ARROW = KEY_LOCATION_NUMPAD*100+VK_LEFT+189;
	
	public static final int VK_ABRE_COLCHETES = VK_OPEN_BRACKET;
	public static final int VK_FECHA_COLCHETES = VK_CLOSE_BRACKET; 
	public static final int VK_ABRE_CHAVES = VK_BRACELEFT;
	public static final int VK_FECHA_CHAVES = VK_BRACERIGHT; 
	public static final int VK_ARROBA = VK_AT;
	public static final int VK_PONTO = VK_PERIOD;
	public static final int VK_VIRGULA = VK_COMMA;
	public static final int VK_DOIS_PONTOS = VK_COLON;
	
	//Japanese Keys
	public static final int VK_ROMAJI = VK_JAPANESE_ROMAN;
	
	//Custom Keys
	
	//Shortcuts
	public static final int VK_UP_ARROW = VK_UP;
	public static final int VK_DOWN_ARROW = VK_DOWN;
	public static final int VK_LEFT_ARROW = VK_LEFT;
	public static final int VK_RIGHT_ARROW = VK_RIGHT;
	public static final int VK_DEL = VK_DELETE;
	public static final int VK_ESC = VK_ESCAPE;
	public static final int VK_SUPER = VK_WINDOWS;
	
	//Mobile Keys
	public static final int VK_BACK = VK_ESC;
	public static final int VK_HOME_SCREEN = VK_HOME;
	public static final int VK_RECENT_APPS = 599;
	
	//Joystick Keys
	public static final int VK_JOYSTICK_UP = JoystickEvent.UP;
	public static final int VK_JOYSTICK_DOWN = JoystickEvent.DOWN;
	public static final int VK_JOYSTICK_CENTER_Y = JoystickEvent.CENTER_Y; 
	public static final int VK_JOYSTICK_LEFT = JoystickEvent.LEFT;
	public static final int VK_JOYSTICK_RIGHT = JoystickEvent.RIGHT;
	public static final int VK_JOYSTICK_CENTER_X = JoystickEvent.CENTER_X;

	public static final int VK_JOYSTICK_BUTTON_1 = JoystickEvent.BUTTON_1;
	public static final int VK_JOYSTICK_BUTTON_2 = JoystickEvent.BUTTON_2;
	public static final int VK_JOYSTICK_BUTTON_3 = JoystickEvent.BUTTON_3;
	public static final int VK_JOYSTICK_BUTTON_4 = JoystickEvent.BUTTON_4;
	public static final int VK_JOYSTICK_BUTTON_5 = JoystickEvent.BUTTON_5;
	public static final int VK_JOYSTICK_BUTTON_6 = JoystickEvent.BUTTON_6;
	public static final int VK_JOYSTICK_BUTTON_7 = JoystickEvent.BUTTON_7;
	public static final int VK_JOYSTICK_BUTTON_8 = JoystickEvent.BUTTON_8;
	public static final int VK_JOYSTICK_BUTTON_9 = JoystickEvent.BUTTON_9;
	public static final int VK_JOYSTICK_BUTTON_10 = JoystickEvent.BUTTON_10;
	public static final int VK_JOYSTICK_BUTTON_11 = JoystickEvent.BUTTON_11;
	public static final int VK_JOYSTICK_BUTTON_12 = JoystickEvent.BUTTON_12;

	public static final int VK_NONE = 0;
}
