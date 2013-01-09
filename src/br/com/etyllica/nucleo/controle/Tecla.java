package br.com.etyllica.nucleo.controle;

import java.awt.event.KeyEvent;

public enum Tecla {

	//public class Tecla {public final int TSK_0  = KeyEvent.VK_0;
	
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
	
	TSK_SETA_CIMA (KeyEvent.VK_UP),
	TSK_SETA_BAIXO (KeyEvent.VK_DOWN),
	TSK_SETA_ESQUERDA (KeyEvent.VK_LEFT),
	TSK_SETA_DIREITA (KeyEvent.VK_RIGHT),

	TSK_CTRL (KeyEvent.VK_CONTROL),
	TSK_DEL (KeyEvent.VK_DELETE),
	
	TSK_ESPACO (KeyEvent.VK_SPACE),
	TSK_ENTER (KeyEvent.VK_ENTER),
	TSK_ESC (KeyEvent.VK_ESCAPE),
	TSK_ALT (KeyEvent.VK_ALT),
	TSK_SHIFT (KeyEvent.VK_SHIFT),
	
	TSK_HIRAGANA (KeyEvent.VK_JAPANESE_HIRAGANA);

	private final int codigo;

	Tecla(int codigo){
		this.codigo = codigo;
	}
	public int codigo(){ return codigo; }

}
