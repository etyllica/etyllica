package br.com.tide.platform.player;

import br.com.etyllica.core.Updatable;
import br.com.tide.ActivePlayer;
import br.com.tide.PlayerState;
import br.com.tide.input.ControllerListener;

public class PlatformPlayer extends ActivePlayer implements Updatable, ControllerListener {

	protected int walkSpeed = 5;
	protected int runSpeed = 5;
	protected int jumpSpeed = 5;
	protected int jumpHeight = 64;
	
	protected int jumpStart = 0;
	
	protected PlatformPlayerListener listener;
	
	public PlatformPlayer() {
		super();
	}
	
	public PlatformPlayer(PlatformPlayerListener listener) {
		super();
		
		this.listener = listener;
	}
	
	@Override
	public void update(long now) {
		super.update(now);
		
		if(hasState(PlayerState.WALK_LEFT)) {
			x -= currentSpeed;
		} else if(hasState(PlayerState.WALK_RIGHT)) {
			x += currentSpeed;
		}
		
		if(hasState(PlayerState.JUMP)) {
			updateJump();
		}
	}
	
	protected void updateJump() {
		
		if(!hasState(PlayerState.FALL)) {
			
			if(y > jumpStart-jumpHeight) {
				y -= jumpSpeed;
			} else {
				y = jumpStart-jumpHeight;
				fall();
			}

		} else {

			//TODO Change to collision methods
			if(y < jumpStart) {
				y += jumpSpeed;
			} else {
				y = jumpStart;
				stopJump();
			}
		}
	}

	public void walkLeft() {
		
		if(!states.contains(PlayerState.TURN_LEFT)) {
			listener.onTurnLeft();
			states.add(PlayerState.TURN_LEFT);
			states.remove(PlayerState.TURN_RIGHT);
		}
				
		listener.onWalkLeft();		
		states.add(PlayerState.WALK_LEFT);
	}

	public void stopWalkLeft() {
		states.remove(PlayerState.WALK_LEFT);
		listener.onStopWalkLeft();
	}

	public void walkRight() {
		
		if(!states.contains(PlayerState.TURN_RIGHT)) {
			listener.onTurnRight();
			states.add(PlayerState.TURN_RIGHT);
			states.remove(PlayerState.TURN_LEFT);
		}
		
		listener.onWalkRight();
		states.add(PlayerState.WALK_RIGHT);
	}

	public void stopWalkRight() {
		states.remove(PlayerState.WALK_RIGHT);
		listener.onStopWalkRight();
	}

	public void lookUp() {
		if(isWalking()) {
			stopWalk();
		}
		
		listener.onLookUp();
		states.add(PlayerState.LOOK_UP);
	}

	public void stopWalkUp() {
		states.remove(PlayerState.WALK_UP);
		listener.onStopLookUp();
	}

	public void standDown() {
		listener.onStandDown();
		states.add(PlayerState.STAND_DOWN);
	}

	public void stopStandDown() {
		states.remove(PlayerState.STAND_DOWN);
		listener.onStopStandDown();
	}
	
	public void stopWalk() {
		states.remove(PlayerState.WALK_LEFT);
		states.remove(PlayerState.WALK_RIGHT);
	}
	
	public void jump() {
		jumpStart = y;
		listener.onJump();
		states.add(PlayerState.JUMP);
	}
	
	public void fall() {
		states.add(PlayerState.FALL);
	}
	
	public void stopJump() {
		states.remove(PlayerState.FALL);
		states.remove(PlayerState.JUMP);		
	}	
	
	public void run() {
		currentSpeed = runSpeed;
		listener.onRun();
	}
	
	public void stopRun() {
		currentSpeed = walkSpeed;
		listener.onStopRun();
	}
	
	public boolean isWalking() {
		return states.contains(PlayerState.WALK_RIGHT)||states.contains(PlayerState.WALK_LEFT)||states.contains(PlayerState.WALK_UP)||states.contains(PlayerState.WALK_DOWN);
	}
	
	public void onUpButtonPressed() {
		lookUp();
	}

	@Override
	public void onUpButtonReleased() {
		stopWalkUp();
	}

	@Override
	public void onDownButtonPressed() {
		standDown();
	}

	@Override
	public void onDownButtonReleased() {
		stopStandDown();
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
		run();
	}

	public void onAButtonReleased() {
		stopRun();
	}
	
	public void onBButtonPressed() {
		jump();
	}

	public void onBButtonReleased() {

	}

	public void onCButtonPressed() {
		attack();
	}

	public void onCButtonReleased() {
		stopAttack();
		stand();
	}

	@Override
	public void onXButtonPressed() {
		specialAttack();		
	}

	@Override
	public void onXButtonReleased() {
		onStopSpecialAttack();
		stand();
	}

	@Override
	public void onYButtonPressed() {
		run();
	}

	@Override
	public void onYButtonReleased() {
		stopRun();
	}

	@Override
	public void onZButtonPressed() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onZButtonReleased() {
		// TODO Auto-generated method stub
	}

}
