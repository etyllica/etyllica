package br.com.tide.action.player;

import br.com.tide.ActivePlayer;
import br.com.tide.PlayerState;

public class ActionPlayer extends ActivePlayer {

	private double startAngle = 90;
	
	private double angle = 0;
	
	private double turnSpeed = 5;
		
	private double backWalkSpeed = -1.5;
	
	private ActionPlayerListener listener;
	
	public ActionPlayer(ActionPlayerListener listener) {
		super();
		
		this.listener = listener;
	}
	
	public void turnLeft() {
		angle -= turnSpeed;
		
		listener.onTurnLeft();
		states.add(PlayerState.WALK_LEFT);
	}

	public void stopTurnLeft() {
		states.remove(PlayerState.WALK_LEFT);
		listener.onStopTurnLeft();
	}

	public void turnRight() {
		
		angle += turnSpeed;
		
		listener.onTurnRight();
		states.add(PlayerState.TURN_RIGHT);
	}

	public void stopTurnRight() {
		states.remove(PlayerState.TURN_RIGHT);
		listener.onStopTurnRight();
	}

	public void walkForward() {
		
		walk(walkSpeed);
		
		listener.onWalkForward();
		states.add(PlayerState.WALK_FORWARD);
	}

	public void stopWalkForward() {
		states.remove(PlayerState.WALK_FORWARD);
		listener.onStopWalkForward();
	}

	public void walkBackward() {
		
		walk(backWalkSpeed);
				
		listener.onStopWalkBackward();
		states.add(PlayerState.WALK_BACKWARD);
	}
	
	public void stopWalkBackward() {
		states.remove(PlayerState.WALK_BACKWARD);
		listener.onStopWalkBackward();
	}

	private void walk(double speed) {
		x = ((int)(x + Math.sin(Math.toRadians(angle+startAngle)) * speed));
		y = ((int)(y - Math.cos(Math.toRadians(angle+startAngle)) * speed));
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
	
}
