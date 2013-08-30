package br.com.etyllica.core.event;

import br.com.etyllica.core.input.keyboard.KeyState;

public class KeyboardEvent{
	
	private int id = 0;
	
	private int key;
	private KeyState state;
	
	private int amount = Character.getNumericValue('\0');
	
	public KeyboardEvent(int key, KeyState state) {
		super();
		
		this.key = key;
		this.state = state;
	}
	
	public KeyboardEvent(int key, int amount, KeyState state) {
		super();
		
		this.key = key;
		this.amount = amount;
		this.state = state;
	}
	
	public KeyboardEvent(int id, int key, int amount, KeyState state) {
		super();
		
		this.id = id;
		this.key = key;
		this.amount = amount;
		this.state = state;
	}

	public boolean getPressed(Tecla key){
		
		if(this.key == key.getCode()){
			return state==KeyState.PRESSED;
		}
		
		return false;
	}
	
	public boolean getReleased(Tecla key){
		
		if(this.key == key.getCode()){
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
			
}
