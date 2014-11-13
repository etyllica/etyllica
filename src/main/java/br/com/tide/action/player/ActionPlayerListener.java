package br.com.tide.action.player;


public interface ActionPlayerListener<T extends ActionPlayer> {

	public void onTurnLeft(T player);
	
	public void onTurnRight(T player);
	
	public void onWalkForward(T player);
	
	public void onWalkBackward(T player);
	
	public void onStopTurnLeft(T player);
	
	public void onStopTurnRight(T player);
	
	public void onStopWalkForward(T player);
	
	public void onStopWalkBackward(T player);
		
}
