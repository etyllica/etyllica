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
	
	private int x = 0;
	
	private int y = 0;
	
	private int amountX = 0;
	
	private int amountY = 0;
	
	private long timestamp = System.currentTimeMillis();
	
	public PointerEvent() {
		super();
	}
	
	public PointerEvent(MouseButton key, PointerState state) {
		super();
		
		set(key, state);
		
	}
		
	public PointerEvent(MouseButton key, PointerState state, int x, int y) {
		super();
		
		this.key = key;
		this.state = state;
		
		this.x = x;
		this.y = y;
		this.amountY = 0;
	}
	
	public PointerEvent(MouseButton key, PointerState state, int x, int y, int amount) {
		super();
		
		this.set(key, state, x, y, 0, amount);
	}
	
	public PointerEvent(MouseButton key, PointerState state, int x, int y, int amountX, int amountY) {
		super();

		this.set(key, state, x, y, amountX, amountY);
	}
	
	public void set(MouseButton key, PointerState state) {
		
		this.set(key, state, 0, 0);
	}
	
	public void set(MouseButton key, PointerState state, int x, int y) {

		this.set(key, state, x, y, 0, 0);
		
	}
	
	public void set(MouseButton key, PointerState state, int x, int y, int amount) {
		this.set(key, state, x, y, 0, amount);
	}
	
	public void set(MouseButton key, PointerState state, int x, int y, int amountX, int amountY) {

		this.key = key;
		this.state = state;

		this.x = x;
		this.y = y;
		
		this.amountX = amountX;
		this.amountY = amountY;
		
	}
	
	public void copy(PointerEvent event) {
		
		this.set(event.key, event.state, event.x, event.y, event.amountX, event.amountY);
		
	}
	
	public MouseButton getKey() {
		return key;
	}

	public PointerState getState() {
		return state;
	}

	public void setState(PointerState state) {
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
		return amountY;
	}
	
	public int getAmountX() {
		return amountX;
	}

	public int getAmountY() {
		return amountY;
	}

	public long getTimestamp() {
		return timestamp;
	}
	
	public boolean isKey(MouseButton key) {
		return this.key == key;
	}
		
	public boolean isDraggedButton(MouseButton key) {
		return((state == PointerState.DRAGGED) && this.key == key);
	}
	
	public boolean isButtonDown(MouseButton key) {
		return((state == PointerState.PRESSED || (state == PointerState.DRAGGED)) && this.key == key);
	}
	
	public boolean isClicked(MouseButton key) {
		return(state == PointerState.CLICK && this.key == key);
	}
	
	public boolean isButtonUp(MouseButton key) {
		return((state == PointerState.RELEASED) && this.key == key);
	}

}

