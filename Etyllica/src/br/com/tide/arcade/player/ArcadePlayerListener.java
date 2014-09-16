package br.com.tide.arcade.player;

public interface ArcadePlayerListener {

	public void onWalkLeft();
	
	public void onWalkRight();
	
	public void onWalkUp();
	
	public void onWalkDown();
	
	public void onStopWalkLeft();
	
	public void onStopWalkRight();
	
	public void onStopWalkUp();
	
	public void onStopWalkDown();
		
}
