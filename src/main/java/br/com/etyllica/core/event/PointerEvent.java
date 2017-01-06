package br.com.etyllica.core.event;

/**
 * 
 * @author yuripourre
 *
 */

public class PointerEvent {

	private int pointer = 0;
	
	private MouseEvent key;
	private PointerState state;
	
	private int x = 0;
	private int y = 0;
	
	private int amountX = 0;
	private int amountY = 0;
	
	private boolean consumed = false;
	private long timestamp = System.currentTimeMillis();
	
	public PointerEvent() {
		super();
	}
	
	public PointerEvent(MouseEvent key, PointerState state) {
		super();
		
		set(key, state);
	}
		
	public PointerEvent(MouseEvent key, PointerState state, int x, int y) {
		super();
		
		this.key = key;
		this.state = state;
		
		this.x = x;
		this.y = y;
		this.amountY = 0;
	}
	
	public PointerEvent(MouseEvent key, PointerState state, int x, int y, int amount) {
		super();
		
		this.set(key, state, x, y, 0, amount);
	}
	
	public PointerEvent(MouseEvent key, PointerState state, int x, int y, int amountX, int amountY) {
		super();

		this.set(key, state, x, y, amountX, amountY);
	}
	
	public void set(MouseEvent key, PointerState state) {
		
		this.set(key, state, 0, 0);
	}
	
	public void set(MouseEvent key, PointerState state, int x, int y) {

		this.set(key, state, x, y, 0, 0);
		
	}
	
	public void set(MouseEvent key, PointerState state, int x, int y, int amount) {
		this.set(key, state, x, y, 0, amount);
	}
	
	public void set(MouseEvent key, PointerState state, int x, int y, int amountX, int amountY) {

		this.key = key;
		this.state = state;

		this.x = x;
		this.y = y;
		
		this.amountX = amountX;
		this.amountY = amountY;
		
		consumed = false;
	}
	
	public void copy(PointerEvent event) {
		
		this.set(event.key, event.state, event.x, event.y, event.amountX, event.amountY);
		
	}
	
	public MouseEvent getKey() {
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
	
	public int getPointer() {
		return pointer;
	}

	public void setPointer(int pointer) {
		this.pointer = pointer;
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
	
    public void resetTimestamp() {
        this.timestamp = System.currentTimeMillis();
    }
		
	public void consume() {
		this.consumed = true;
	}

	public boolean isKey(MouseEvent key) {
		return this.key == key;
	}
		
	public boolean isDraggedButton(MouseEvent key) {
		
		if(consumed)
			return false;
		
		return((state == PointerState.DRAGGED) && this.key == key);
	}
	
	public boolean isButtonDown(MouseEvent key) {
		
		if(consumed)
			return false;
		
		return((state == PointerState.PRESSED || (state == PointerState.DRAGGED)) && this.key == key);
	}
	
	public boolean isClicked(MouseEvent key) {
		
		if(consumed)
			return false;
		
		return(state == PointerState.CLICK && this.key == key);
	}
	
	public boolean isClicked() {
		if(consumed)
			return false;
		
		return(state == PointerState.CLICK);
	}
	
	public boolean isButtonUp(MouseEvent key) {
		
		if(consumed)
			return false;
			
		return((state == PointerState.RELEASED) && this.key == key);		
	}	

}

