package br.com.etyllica.core.event;

import br.com.etyllica.core.input.mouse.MouseButton;


/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class PointerEvent {

	private MouseButton key;
	
	private KeyState state;
	
	private int x;
	
	private int y;
	
	private int amount;
	
	private long timestamp = System.currentTimeMillis();
	
	public PointerEvent(MouseButton key, KeyState state){
		this.key = key;
		this.state = state;
		
		this.x = 0;
		this.y = 0;
		this.amount = 0;
		
		//Ex: (Teclado, TSK_A, PRESSED);
		//Ex: (Mouse, BUTTON_LEFT, DRAGGED)//Pressed with x or y = dragged
		
		//EX: (VOICE_SPELL, TSK_A, PRESSED);
		//EX: (JOYSTICK, TSK_SETA_DIRTEITA, PRESSED, speed, 0);
		//EX: (HEAD, TSK_SETA_DIREITA, PRESSED, speed, 0);
		
	}
	
	public PointerEvent(MouseButton key, KeyState state, int x, int y){
		this.key = key;
		this.state = state;
		
		this.x = x;
		this.y = y;
		this.amount = 0;
	}
	
	public PointerEvent(MouseButton key, KeyState state, int x, int y, int amount){
		this.key = key;
		this.state = state;
		
		this.x = x;
		this.y = y;
		this.amount = amount;
	}

	public KeyState getState() {
		return state;
	}

	public void setState(KeyState state) {
		this.state = state;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public long getTimestamp() {
		return timestamp;
	}
	
	public boolean isKey(MouseButton key){
		return this.key==key;
	}
		
	public boolean onDragButton(MouseButton key){
		return((state==KeyState.DRAGGED)&&this.key==key);
	}
	
	public boolean onButtonDown(MouseButton key){
		//TODO Make sense?
		return((state==KeyState.PRESSED||(state==KeyState.DRAGGED))&&this.key==key);
	}
	
	public boolean onButtonUp(MouseButton key){
		return((state==KeyState.RELEASED)&&this.key==key);
	}
		
}

