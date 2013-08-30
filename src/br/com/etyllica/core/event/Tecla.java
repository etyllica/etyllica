package br.com.etyllica.core.event;

import java.awt.event.KeyEvent;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public enum Tecla {

	TSK_MENOS (KeyEvent.VK_MINUS),
	TSK_MAIS (KeyEvent.VK_PLUS),
	TSK_IGUAL(KeyEvent.VK_EQUALS),
	
	TSK_A (KeyEvent.VK_A),
	TSK_B (KeyEvent.VK_B),
	TSK_C (KeyEvent.VK_C),
	TSK_D (KeyEvent.VK_D),
	TSK_E (KeyEvent.VK_E),
	TSK_F (KeyEvent.VK_F),
	TSK_G (KeyEvent.VK_G),
	TSK_H (KeyEvent.VK_H),
	TSK_I (KeyEvent.VK_I),
	TSK_J (KeyEvent.VK_J),
	TSK_K (KeyEvent.VK_K),
	TSK_L (KeyEvent.VK_L),
	TSK_M (KeyEvent.VK_M),
	TSK_N (KeyEvent.VK_N),
	TSK_O (KeyEvent.VK_O),
	TSK_P (KeyEvent.VK_P),
	TSK_Q (KeyEvent.VK_Q),
	TSK_R (KeyEvent.VK_R),
	TSK_S (KeyEvent.VK_S),
	TSK_T (KeyEvent.VK_T),
	TSK_U (KeyEvent.VK_U),
	TSK_V (KeyEvent.VK_V),		
	TSK_W (KeyEvent.VK_W),
	TSK_X (KeyEvent.VK_X),
	TSK_Y (KeyEvent.VK_Y),
	TSK_Z (KeyEvent.VK_Z),
	
	TSK_SETA_CIMA (KeyEvent.VK_UP),
	TSK_SETA_BAIXO (KeyEvent.VK_DOWN),
	TSK_SETA_ESQUERDA (KeyEvent.VK_LEFT),
	TSK_SETA_DIREITA (KeyEvent.VK_RIGHT),
	
	TSK_UP_ARROW (KeyEvent.VK_UP),
	TSK_DOWN_ARROW (KeyEvent.VK_DOWN),
	TSK_LEFT_ARROW (KeyEvent.VK_LEFT),
	TSK_RIGHT_ARROW (KeyEvent.VK_RIGHT),
	
	TSK_0 (KeyEvent.VK_0),
	TSK_1 (KeyEvent.VK_1),
	TSK_2 (KeyEvent.VK_2),
	TSK_3 (KeyEvent.VK_3),
	TSK_4 (KeyEvent.VK_4),
	TSK_5 (KeyEvent.VK_5),
	TSK_6 (KeyEvent.VK_6),
	TSK_7 (KeyEvent.VK_7),
	TSK_8 (KeyEvent.VK_8),
	TSK_9 (KeyEvent.VK_9),
		
	TSK_F1 (KeyEvent.VK_F1),
	TSK_F2 (KeyEvent.VK_F2),
	TSK_F3 (KeyEvent.VK_F3),
	TSK_F4 (KeyEvent.VK_F4),
	TSK_F5 (KeyEvent.VK_F5),
	TSK_F6 (KeyEvent.VK_F6),
	TSK_F7 (KeyEvent.VK_F7),
	TSK_F8 (KeyEvent.VK_F8),
	TSK_F9 (KeyEvent.VK_F9),
	TSK_F10 (KeyEvent.VK_F10),
	TSK_F11 (KeyEvent.VK_F11),
	TSK_F12 (KeyEvent.VK_F12),
	TSK_F13 (KeyEvent.VK_F13),
	TSK_F14 (KeyEvent.VK_F14),
	TSK_F15 (KeyEvent.VK_F15),
	
	TSK_DEL (KeyEvent.VK_DELETE),
	TSK_BACK_SPACE (KeyEvent.VK_BACK_SPACE),
	TSK_TAB (KeyEvent.VK_TAB),
	TSK_ESPACO (KeyEvent.VK_SPACE),
	TSK_SPACE (KeyEvent.VK_SPACE),
	TSK_ENTER (KeyEvent.VK_ENTER),
	TSK_INSERT (KeyEvent.VK_INSERT),
	TSK_HOME (KeyEvent.VK_HOME),
	TSK_END (KeyEvent.VK_END),
	TSK_ESC (KeyEvent.VK_ESCAPE),
	
	//TSK_ALT (KeyEvent.VK_ALT),
	TSK_ALT_DIREITA (KeyEvent.KEY_LOCATION_RIGHT*100+KeyEvent.VK_ALT),
	TSK_ALT_ESQUERDA (KeyEvent.KEY_LOCATION_LEFT*100+KeyEvent.VK_ALT),
	
	TSK_ALT_RIGHT (KeyEvent.KEY_LOCATION_RIGHT*100+KeyEvent.VK_ALT),
	TSK_ALT_LEFT (KeyEvent.KEY_LOCATION_LEFT*100+KeyEvent.VK_ALT),
	
	//TSK_CTRL (KeyEvent.VK_CONTROL),
	TSK_CTRL_DIREITA (KeyEvent.KEY_LOCATION_RIGHT*100+KeyEvent.VK_CONTROL),
	TSK_CTRL_ESQUERDA (KeyEvent.KEY_LOCATION_LEFT*100+KeyEvent.VK_CONTROL),
	
	TSK_CTRL_RIGHT (KeyEvent.KEY_LOCATION_RIGHT*100+KeyEvent.VK_CONTROL),
	TSK_CTRL_LEFT (KeyEvent.KEY_LOCATION_LEFT*100+KeyEvent.VK_CONTROL),
	
	//TSK_SHIFT (KeyEvent.VK_SHIFT),
	TSK_SHIFT_DIREITA (KeyEvent.KEY_LOCATION_RIGHT*100+KeyEvent.VK_SHIFT),
	TSK_SHIFT_ESQUERDA (KeyEvent.KEY_LOCATION_LEFT*100+KeyEvent.VK_SHIFT),
	
	TSK_SHIFT_RIGHT (KeyEvent.KEY_LOCATION_RIGHT*100+KeyEvent.VK_SHIFT),
	TSK_SHIFT_LEFT (KeyEvent.KEY_LOCATION_LEFT*100+KeyEvent.VK_SHIFT),
		
	TSK_NUMPAD_0 (KeyEvent.KEY_LOCATION_NUMPAD*100+TSK_0.getCode()+48),
	TSK_NUMPAD_1 (KeyEvent.KEY_LOCATION_NUMPAD*100+TSK_1.getCode()+48),
	TSK_NUMPAD_2 (KeyEvent.KEY_LOCATION_NUMPAD*100+TSK_2.getCode()+48),
	TSK_NUMPAD_3 (KeyEvent.KEY_LOCATION_NUMPAD*100+TSK_3.getCode()+48),
	TSK_NUMPAD_4 (KeyEvent.KEY_LOCATION_NUMPAD*100+TSK_4.getCode()+48),
	TSK_NUMPAD_5 (KeyEvent.KEY_LOCATION_NUMPAD*100+TSK_5.getCode()+48),
	TSK_NUMPAD_6 (KeyEvent.KEY_LOCATION_NUMPAD*100+TSK_6.getCode()+48),
	TSK_NUMPAD_7 (KeyEvent.KEY_LOCATION_NUMPAD*100+TSK_7.getCode()+48),
	TSK_NUMPAD_8 (KeyEvent.KEY_LOCATION_NUMPAD*100+TSK_8.getCode()+48),
	TSK_NUMPAD_9 (KeyEvent.KEY_LOCATION_NUMPAD*100+TSK_9.getCode()+48),
	
	TSK_NUMPAD_DEL (KeyEvent.KEY_LOCATION_NUMPAD*100+TSK_DEL.getCode()-19),
	TSK_NUMPAD_INS (KeyEvent.KEY_LOCATION_NUMPAD*100+TSK_INSERT.getCode()),
	TSK_NUMPAD_ENTER (KeyEvent.KEY_LOCATION_NUMPAD*100+TSK_ENTER.getCode()),
	
	TSK_NUMPAD_SETA_BAIXO (KeyEvent.KEY_LOCATION_NUMPAD*100+TSK_SETA_BAIXO.getCode()+185),
	TSK_NUMPAD_SETA_CIMA (KeyEvent.KEY_LOCATION_NUMPAD*100+TSK_SETA_CIMA.getCode()+186),
	TSK_NUMPAD_SETA_DIREITA (KeyEvent.KEY_LOCATION_NUMPAD*100+TSK_SETA_DIREITA.getCode()+188),
	TSK_NUMPAD_SETA_ESQUERDA (KeyEvent.KEY_LOCATION_NUMPAD*100+TSK_SETA_ESQUERDA.getCode()+189),
			
	TSK_ABRE_COLCHETES (KeyEvent.VK_OPEN_BRACKET),
	TSK_FECHA_COLCHETES (KeyEvent.VK_CLOSE_BRACKET),
	
	TSK_ABRE_CHAVES (KeyEvent.VK_BRACELEFT),
	TSK_FECHA_CHAVES (KeyEvent.VK_BRACERIGHT),
	
	TSK_ARROBA (KeyEvent.VK_AT),
	TSK_PONTO (KeyEvent.VK_PERIOD),
	TSK_VIRGULA (KeyEvent.VK_COMMA),
	TSK_DOIS_PONTOS (KeyEvent.VK_COLON),
	
		
	TSK_HIRAGANA (KeyEvent.VK_JAPANESE_HIRAGANA),
		
	JOYSTICK_UP(JoystickButtons.UP),
	JOYSTICK_DOWN(JoystickButtons.DOWN),
	JOYSTICK_LEFT(JoystickButtons.LEFT),
	JOYSTICK_RIGHT(JoystickButtons.RIGHT),

	JOYSTICK_BUTTON_1(JoystickButtons.BUTTON_1),
	JOYSTICK_BUTTON_2(JoystickButtons.BUTTON_2),
	JOYSTICK_BUTTON_3(JoystickButtons.BUTTON_3),
	JOYSTICK_BUTTON_4(JoystickButtons.BUTTON_4),
	JOYSTICK_BUTTON_5(JoystickButtons.BUTTON_5),
	JOYSTICK_BUTTON_6(JoystickButtons.BUTTON_6),
	JOYSTICK_BUTTON_7(JoystickButtons.BUTTON_7),
	JOYSTICK_BUTTON_8(JoystickButtons.BUTTON_8),
	JOYSTICK_BUTTON_9(JoystickButtons.BUTTON_9),
	JOYSTICK_BUTTON_10(JoystickButtons.BUTTON_10),
	JOYSTICK_BUTTON_11(JoystickButtons.BUTTON_11),
	JOYSTICK_BUTTON_12(JoystickButtons.BUTTON_12),
	//...
	
	NONE(0);
	
	private final int code;

	Tecla(int code){
		this.code = code;
	}
	
	public final int getCode(){ 
		return code; 
	}

}

