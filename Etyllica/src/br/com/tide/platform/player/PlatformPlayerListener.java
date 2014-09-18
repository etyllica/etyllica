package br.com.tide.platform.player;

public interface PlatformPlayerListener {

	public void onTurnLeft();
	
	public void onTurnRight();
	
	public void onWalkLeft();
		
	public void onWalkRight();
		
	public void onLookUp();
	
	public void onStandDown();
	
	public void onJump();
	
	public void onFall();
	
	public void onRun();
	
	public void onStopWalkLeft();
	
	public void onStopWalkRight();
	
	public void onStopLookUp();
	
	public void onStopStandDown();
	
	public void onStopJump();
	
	public void onStopRun();
	
}
