package br.com.tide.action.player;

import br.com.tide.ActivePlayer;
import br.com.tide.PlayerState;
import br.com.tide.input.ControllerListener;

public class ActionPlayer extends ActivePlayer implements ControllerListener {

	protected double dx = 0;
	protected double dy = 0;
	
	protected double angle = 0;
	
	protected double startAngle = 90;
		
	protected double turnSpeed = 5;
		
	protected double backWalkSpeed = 1.5;
	
	protected ActionPlayerListener listener;
	
	public ActionPlayer(int x, int y) {
		super();

		this.x = x;
		this.y = y;
		this.dx = x;
		this.dy = y;		
	}
	
	public ActionPlayer(int x, int y, ActionPlayerListener listener) {
		this(x, y);
		
		this.listener = listener;		
	}
	
	@Override
	public void update(long now) {
		super.update(now);
		
		if(hasState(PlayerState.TURN_LEFT)) {
			angle-=turnSpeed;
		}
		
		if(hasState(PlayerState.TURN_RIGHT)) {
			angle+=turnSpeed;
		}
		
		if(hasState(PlayerState.WALK_FORWARD)) {
			moveForward();
		}
		
		if(hasState(PlayerState.WALK_BACKWARD)) {
			moveBackward();
		}
		
	}
	
	public void turnLeft() {
		listener.onTurnLeft(this);
		states.add(PlayerState.TURN_LEFT);
	}

	public void stopTurnLeft() {
		states.remove(PlayerState.TURN_LEFT);
		listener.onStopTurnLeft(this);
	}

	public void turnRight() {
		listener.onTurnRight(this);
		states.add(PlayerState.TURN_RIGHT);
	}

	public void stopTurnRight() {
		states.remove(PlayerState.TURN_RIGHT);
		listener.onStopTurnRight(this);
	}

	public void walkForward() {
		listener.onWalkForward(this);
		states.add(PlayerState.WALK_FORWARD);
	}

	public void stopWalkForward() {
		states.remove(PlayerState.WALK_FORWARD);
		listener.onStopWalkForward(this);
	}

	public void walkBackward() {
		listener.onStopWalkBackward(this);
		states.add(PlayerState.WALK_BACKWARD);
	}
	
	public void stopWalkBackward() {
		states.remove(PlayerState.WALK_BACKWARD);
		listener.onStopWalkBackward(this);
	}

	private void move(double ang, double speed) {
		dx = (dx + Math.sin(Math.toRadians(ang)) * speed);
		dy = (dy - Math.cos(Math.toRadians(ang)) * speed);
		
		x = (int)dx;
		y = (int)dy;
	}
	
	private void moveForward() {
		move(angle+startAngle, currentSpeed);
	}
	
	public void undoMoveForward() {
		move(angle+startAngle, -currentSpeed);
	}
	
	private void moveBackward() {
		double ang = 180+angle+startAngle;
		move(ang, backWalkSpeed);		
	}
	
	public void undoMoveBackward() {
		double ang = 180+angle+startAngle;
		move(ang, -backWalkSpeed);
	}
	
	public void stopWalk() {
		states.remove(PlayerState.WALK_FORWARD);
		states.remove(PlayerState.WALK_BACKWARD);
	}
	
	public boolean isWalking() {
		return states.contains(PlayerState.WALK_FORWARD)||states.contains(PlayerState.WALK_BACKWARD);
	}
	
	public boolean isTurning() {
		return states.contains(PlayerState.TURN_LEFT)||states.contains(PlayerState.TURN_RIGHT);
	}

	@Override
	public void onUpButtonPressed() {
		walkForward();
	}

	@Override
	public void onUpButtonReleased() {
		stopWalkForward();
	}

	@Override
	public void onDownButtonPressed() {
		walkBackward();
	}

	@Override
	public void onDownButtonReleased() {
		stopWalkBackward();
	}

	@Override
	public void onRightButtonPressed() {
		turnRight();
	}

	@Override
	public void onRightButtonReleased() {
		stopTurnRight();
	}

	@Override
	public void onLeftButtonPressed() {
		turnLeft();
	}

	@Override
	public void onLeftButtonReleased() {
		stopTurnLeft();
	}

	@Override
	public void onAButtonPressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAButtonReleased() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBButtonPressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBButtonReleased() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCButtonPressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCButtonReleased() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onXButtonPressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onXButtonReleased() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onYButtonPressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onYButtonReleased() {
		// TODO Auto-generated method stub
		
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
