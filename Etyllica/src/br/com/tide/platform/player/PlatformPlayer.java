package br.com.tide.platform.player;

import br.com.etyllica.core.Updatable;
import br.com.tide.ActivePlayer;
import br.com.tide.PlayerState;
import br.com.tide.input.ControllerListener;

public class PlatformPlayer extends ActivePlayer implements Updatable, ControllerListener {

	private PlatformPlayerListener listener;
	
	public PlatformPlayer(PlatformPlayerListener listener) {
		super();
		
		this.listener = listener;
	}

	public void walkLeft() {
		x -= walkSpeed;
		
		listener.onWalkLeft();		
		states.add(PlayerState.WALK_LEFT);
	}

	public void stopWalkLeft() {
		states.remove(PlayerState.WALK_LEFT);
		listener.onStopWalkLeft();
	}

	public void walkRight() {
		x += walkSpeed;
		
		listener.onWalkRight();
		states.add(PlayerState.WALK_RIGHT);
	}

	public void stopWalkRight() {
		states.remove(PlayerState.WALK_RIGHT);
		listener.onStopWalkRight();
	}

	public void walkUp() {
		
		y -= walkSpeed;
		
		listener.onWalkUp();
		states.add(PlayerState.WALK_UP);
	}

	public void stopWalkUp() {
		states.remove(PlayerState.WALK_UP);
		listener.onStopWalkUp();
	}

	public void walkDown() {
		y += walkSpeed;
		
		listener.onWalkDown();
		states.add(PlayerState.WALK_DOWN);
	}

	public void stopWalkDown() {
		states.remove(PlayerState.WALK_DOWN);
		listener.onStopWalkDown();
	}
	
	public void stopWalk() {
		states.remove(PlayerState.WALK_UP);
		states.remove(PlayerState.WALK_DOWN);
		states.remove(PlayerState.WALK_LEFT);
		states.remove(PlayerState.WALK_RIGHT);		
	}
	
	public boolean isWalking() {
		return states.contains(PlayerState.WALK_RIGHT)||states.contains(PlayerState.WALK_LEFT)||states.contains(PlayerState.WALK_UP)||states.contains(PlayerState.WALK_DOWN);
	}
	
	public void onUpButtonPressed() {
		walkUp();
	}

	@Override
	public void onUpButtonReleased() {
		stopWalkUp();
	}

	@Override
	public void onDownButtonPressed() {
		walkDown();
	}

	@Override
	public void onDownButtonReleased() {
		stopWalkDown();
	}

	@Override
	public void onRightButtonPressed() {
		walkRight();
	}

	@Override
	public void onRightButtonReleased() {
		stopWalkRight();
	}

	@Override
	public void onLeftButtonPressed() {
		walkLeft();
	}

	@Override
	public void onLeftButtonReleased() {
		stopWalkLeft();
	}

	@Override
	public void onAButtonPressed() {
		attack();
	}

	public void onAButtonReleased() {
		stopAttack();
		stand();	
	}

	public void onBButtonPressed() {
		specialAttack();
	}

	public void onBButtonReleased() {
		onStopSpecialAttack();
		stand();	
	}

	public void onCButtonPressed() {
		// TODO Auto-generated method stub
		
	}

	public void onCButtonReleased() {
		// TODO Auto-generated method stub
		
	}

}
