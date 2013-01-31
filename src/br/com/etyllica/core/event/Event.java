package br.com.etyllica.core.event;


/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public class Event {

	private DeviceType deviceType;
	private Integer key;
	private KeyState state;
	
	private int x;
	private int y;
	private int amount;
	
	public Event(DeviceType device, Tecla key, KeyState state){
		this.deviceType = device;
		this.key = key.getCodigo();
		this.state = state;
		
		this.x = -100;
		this.y = -100;
		this.amount = 0;
		
		//Ex: (Teclado, TSK_A, PRESSED);
		//Ex: (Mouse, BUTTON_LEFT, DRAGGED)//Pressed with x or y = dragged
		
		//EX: (VOICE_SPELL, TSK_A, PRESSED);
		//EX: (JOYSTICK, TSK_SETA_DIRTEITA, PRESSED, speed, 0);
		//EX: (HEAD, TSK_SETA_DIREITA, PRESSED, speed, 0);
		
	}
	
	public Event(DeviceType device, Tecla key, KeyState state, int x, int y){
		this.deviceType = device;
		this.key = key.getCodigo();
		this.state = state;
		
		this.x = x;
		this.y = y;
		this.amount = 0;
	}
	
	public Event(DeviceType device, Tecla key, KeyState state, int x, int y, int amount){
		this.deviceType = device;
		this.key = key.getCodigo();
		this.state = state;
		
		this.x = x;
		this.y = y;
		this.amount = amount;
	}
	
	public DeviceType getDevice() {
		return deviceType;
	}

	public void setDevice(DeviceType device) {
		this.deviceType = device;
	}

	/*public Tecla getKey() {
		return key;
	}

	public void setKey(Tecla key) {
		this.key = key;
	}*/

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
	
	public boolean isKey(Tecla key){
		return this.key==key.getCodigo();
	}

	public boolean getKeyPressed(Tecla key){
		return getPressed(DeviceType.KEYBOARD, key);
	}
	
	public boolean getKeyReleased(Tecla key){
		return getReleased(DeviceType.KEYBOARD, key);
	}
	
	public boolean getKeyTyped(Tecla key){
		return getTyped(DeviceType.KEYBOARD, key);
	}
	
	public boolean getMouseButtonPressed(Tecla key){
		return getPressed(DeviceType.MOUSE, key);
	}
	
	public boolean getMouseButtonDragged(Tecla key){
		return((deviceType==DeviceType.MOUSE)&&(state==KeyState.DRAGGED)&&this.key==key.getCodigo());
	}
	
	public boolean getPressed(DeviceType type, Tecla key){
		return((deviceType==type)&&(state==KeyState.PRESSED)&&this.key==key.getCodigo());
	}
	
	public boolean getReleased(DeviceType type, Tecla key){
		return((deviceType==type)&&(state==KeyState.RELEASED)&&this.key==key.getCodigo());
	}
	
	public boolean getTyped(DeviceType type, Tecla key){
		return((deviceType==type)&&(state==KeyState.TYPED)&&this.key==key.getCodigo());
	}
	
}

