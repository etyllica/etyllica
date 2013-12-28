package br.com.tide.platform.player;

import java.util.HashSet;
import java.util.Set;

import br.com.etyllica.core.Drawable;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.video.Graphic;
import br.com.tide.input.controller.Controller;

public class Player implements Drawable{

	private Controller controller;
	
	protected Set<PlayerState> state = new HashSet<PlayerState>();
	
	protected int walkSpeed = 5;
		
	public Player(){
		super();
		
		state.add(PlayerState.STAND);
		
	}

	@Override
	public void draw(Graphic g) {
		
	}

	public void walkLeft(){
		state.add(PlayerState.WALK_LEFT);
	}
	
	public void walkRight(){
		state.add(PlayerState.WALK_RIGHT);
	}
	
	public void walkUp(){
		state.add(PlayerState.WALK_UP);
	}
	
	public void walkDown(){
		state.add(PlayerState.WALK_DOWN);
	}
	
	public void stand(){
		state.clear();
		state.add(PlayerState.STAND);
	}
	
	public void handleEvent(KeyEvent event){
				
		if(event.isKeyDown(controller.getRightButton())){
			state.add(PlayerState.WALK_RIGHT);
		}else if(event.isKeyUp(controller.getRightButton())){
			state.remove(PlayerState.WALK_RIGHT);
		}
		
		if(event.isKeyDown(controller.getLeftButton())){
			state.add(PlayerState.WALK_LEFT);
		}else if(event.isKeyUp(controller.getLeftButton())){
			state.remove(PlayerState.WALK_LEFT);
		}
		
		if(event.isKeyDown(controller.getUpButton())){
			state.add(PlayerState.WALK_UP);
		}else if(event.isKeyUp(controller.getUpButton())){
			state.remove(PlayerState.WALK_UP);
		}
		
		if(event.isKeyDown(controller.getDownButton())){
			state.add(PlayerState.WALK_DOWN);
		}else if(event.isKeyUp(controller.getDownButton())){
			state.remove(PlayerState.WALK_DOWN);
		}
		
	}
	
	public void update() {
		
	}
	
	public boolean isWalking(){
		return state.contains(PlayerState.WALK_RIGHT)||state.contains(PlayerState.WALK_LEFT)||state.contains(PlayerState.WALK_UP)||state.contains(PlayerState.WALK_DOWN);
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
		
}
