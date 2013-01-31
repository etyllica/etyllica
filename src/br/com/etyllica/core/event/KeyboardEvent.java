package br.com.etyllica.core.event;

public class KeyboardEvent{
	
	private Integer key;
	private KeyState state;
	
	private Character character = '\0';
	
	public KeyboardEvent(Integer key, KeyState state) {
		
		this.key = key;
		this.state = state;
	}
	
	public KeyboardEvent(Integer key, Character c, KeyState state) {
		
		this.key = key;
		this.character = c;
		this.state = state;
	}

	public boolean getPressed(Tecla key){
		
		if(this.key == key.getCodigo()){
			return state==KeyState.PRESSED;
		}
		
		return false;
	}
	
	public boolean getReleased(Tecla key){
		
		if(this.key == key.getCodigo()){
			return state==KeyState.RELEASED;
		}
		
		return false;
	}
		
	public char getChar(){
		return character;
	}
	
}
