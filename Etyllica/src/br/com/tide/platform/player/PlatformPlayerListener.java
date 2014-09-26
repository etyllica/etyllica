package br.com.tide.platform.player;

public interface PlatformPlayerListener {

	public void onTurnLeft(PlatformPlayer player);
	
	public void onTurnRight(PlatformPlayer player);
	
	public void onWalkLeft(PlatformPlayer player);
		
	public void onWalkRight(PlatformPlayer player);
		
	public void onLookUp(PlatformPlayer player);
	
	public void onStandDown(PlatformPlayer player);
	
	public void onJump(PlatformPlayer player);
	
	public void onFall(PlatformPlayer player);
	
	public void onRun(PlatformPlayer player);
	
	public void onStopWalkLeft(PlatformPlayer player);
	
	public void onStopWalkRight(PlatformPlayer player);
	
	public void onStopLookUp(PlatformPlayer player);
	
	public void onStopStandDown(PlatformPlayer player);
	
	public void onStopJump(PlatformPlayer player);
	
	public void onStopRun(PlatformPlayer player);
	
}
