package br.com.etyllica.core.event;

import br.com.etyllica.core.control.keyboard.OldKeyState;

public class KeyboardEvent{
	
	private Integer key;
	private OldKeyState state;
	
	private Character character = '\0';
	
	public KeyboardEvent(Integer key, OldKeyState state) {
		
		this.key = key;
		this.state = state;
	}
	
	public KeyboardEvent(Integer key, Character c, OldKeyState state) {
		
		this.key = key;
		this.character = c;
		this.state = state;
	}

	public boolean getPressed(Tecla key){
		
		if(this.key == key.getCode()){
			return state==OldKeyState.PRESSED;
		}
		
		return false;
	}
	
	public boolean getReleased(Tecla key){
		
		if(this.key == key.getCode()){
			return state==OldKeyState.RELEASED;
		}
		
		return false;
	}
		
	public char getChar(){
		return character;
	}
	
}
