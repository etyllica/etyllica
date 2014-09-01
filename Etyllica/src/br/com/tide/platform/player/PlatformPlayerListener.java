package br.com.tide.platform.player;

public interface PlatformPlayerListener {

	public void onWalkLeft();
	
	public void onWalkRight();
	
	public void onWalkUp();
	
	public void onWalkDown();
	
	public void onStopWalkLeft();
	
	public void onStopWalkRight();
	
	public void onStopWalkUp();
	
	public void onStopWalkDown();
		
}
