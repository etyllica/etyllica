package br.com.tide.platform.player;

public interface PlatformPlayerListener {

	public void onWalkLeft();
	
	public void onWalkRight();
	
	public void onLookUp();
	
	public void onStandDown();
	
	public void onStopWalkLeft();
	
	public void onStopWalkRight();
	
	public void onStopLookUp();
	
	public void onStopStandDown();
		
}
