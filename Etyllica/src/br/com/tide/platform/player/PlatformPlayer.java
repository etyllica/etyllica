package br.com.tide.platform.player;

import br.com.tide.ActivePlayer;
import br.com.tide.PlayerState;
import br.com.tide.input.ControllerListener;

public class PlatformPlayer extends ActivePlayer implements ControllerListener {

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
		
		if(hasState(PlayerState.JUMP, PlayerState.FALL)) {
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
			y += jumpSpeed;
			//finishJump();
		}
	}
	
	/*public void finishJump() {
		//TODO Change to collision methods
		if(y < jumpStart) {
			y += jumpSpeed;
		} else {
			y = jumpStart;
			stopJump();
		}		
	}*/

	public void walkLeft() {
		
		if(!states.contains(PlayerState.TURN_LEFT)) {
			listener.onTurnLeft(this);
			states.add(PlayerState.TURN_LEFT);
			states.remove(PlayerState.TURN_RIGHT);
		}
				
		listener.onWalkLeft(this);		
		states.add(PlayerState.WALK_LEFT);
	}

	public void stopWalkLeft() {
		states.remove(PlayerState.WALK_LEFT);
		listener.onStopWalkLeft(this);
	}

	public void walkRight() {
		
		if(!states.contains(PlayerState.TURN_RIGHT)) {
			listener.onTurnRight(this);
			states.add(PlayerState.TURN_RIGHT);
			states.remove(PlayerState.TURN_LEFT);
		}
		
		listener.onWalkRight(this);
		states.add(PlayerState.WALK_RIGHT);
	}

	public void stopWalkRight() {
		states.remove(PlayerState.WALK_RIGHT);
		listener.onStopWalkRight(this);
	}

	public void lookUp() {
		if(isWalking()) {
			stopWalk();
		}
		
		listener.onLookUp(this);
		states.add(PlayerState.LOOK_UP);
	}
	
	public void turnLeft() {
		
	}
	
	public void turnRight() {
		
	}

	public void stopWalkUp() {
		states.remove(PlayerState.WALK_UP);
		listener.onStopLookUp(this);
	}

	public void standDown() {
		listener.onStandDown(this);
		states.add(PlayerState.STAND_DOWN);
	}

	public void stopStandDown() {
		states.remove(PlayerState.STAND_DOWN);
		listener.onStopStandDown(this);
	}
	
	public void stopWalk() {
		states.remove(PlayerState.WALK_LEFT);
		states.remove(PlayerState.WALK_RIGHT);
	}
	
	public void jump() {
		jumpStart = y;
		listener.onJump(this);
		states.add(PlayerState.JUMP);
	}
	
	public void fall() {
		states.add(PlayerState.FALL);
	}
	
	public void fall(int groundPosition) {
		states.add(PlayerState.FALL);
	}
	
	public void stopJump() {
		states.remove(PlayerState.FALL);
		states.remove(PlayerState.JUMP);		
	}	
	
	public void run() {
		currentSpeed = runSpeed;
		listener.onRun(this);
	}
	
	public void stopRun() {
		currentSpeed = walkSpeed;
		listener.onStopRun(this);
	}
	
	public boolean isWalking() {
		return hasState(PlayerState.WALK_RIGHT, PlayerState.WALK_LEFT, PlayerState.WALK_UP, PlayerState.WALK_DOWN);
	}
	
	public boolean isJumping() {
		return states.contains(PlayerState.JUMP);
	}
	
	public boolean isFalling() {
		return states.contains(PlayerState.FALL);
	}
	
	//Plataform specific actions
	public void lookUpAction() {
		
	}
	
	public void standDownAction() {
		
	}
	
	//Controller Oriented Actions
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
