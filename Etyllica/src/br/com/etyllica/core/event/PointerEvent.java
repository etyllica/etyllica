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
	
	private PointerState state;
	
	private float x;
	
	private float y;
	
	private int amount;
	
	private long timestamp = System.currentTimeMillis();
	
	public PointerEvent(MouseButton key, PointerState state){
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
	
	public PointerEvent(MouseButton key, PointerState state, int x, int y){
		this.key = key;
		this.state = state;
		
		this.x = x;
		this.y = y;
		this.amount = 0;
	}
	
	public PointerEvent(MouseButton key, PointerState state, int x, int y, int amount){
		this.key = key;
		this.state = state;
		
		this.x = x;
		this.y = y;
		this.amount = amount;
	}

	public PointerState getState() {
		return state;
	}

	public void setState(PointerState state) {
		this.state = state;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
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
		return((state==PointerState.DRAGGED)&&this.key==key);
	}
	
	public boolean onButtonDown(MouseButton key){
		//TODO Make sense?
		return((state==PointerState.PRESSED||(state==PointerState.DRAGGED))&&this.key==key);
	}
	
	public boolean onButtonUp(MouseButton key){
		return((state==PointerState.RELEASED)&&this.key==key);
	}
		
}

