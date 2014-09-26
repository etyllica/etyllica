package br.com.tide.action.player;

public interface ActionPlayerListener {

	public void onTurnLeft(ActionPlayer player);
	
	public void onTurnRight(ActionPlayer player);
	
	public void onWalkForward(ActionPlayer player);
	
	public void onWalkBackward(ActionPlayer player);
	
	public void onStopTurnLeft(ActionPlayer player);
	
	public void onStopTurnRight(ActionPlayer player);
	
	public void onStopWalkForward(ActionPlayer player);
	
	public void onStopWalkBackward(ActionPlayer player);
		
}
