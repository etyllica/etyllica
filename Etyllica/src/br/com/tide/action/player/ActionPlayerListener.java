package br.com.tide.action.player;

public interface ActionPlayerListener {

	public void onTurnLeft();
	
	public void onTurnRight();
	
	public void onWalkForward();
	
	public void onWalkBackward();
	
	public void onStopTurnLeft();
	
	public void onStopTurnRight();
	
	public void onStopWalkForward();
	
	public void onStopWalkBackward();
		
}
