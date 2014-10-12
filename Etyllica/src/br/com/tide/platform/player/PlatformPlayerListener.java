package br.com.tide.platform.player;

public interface PlatformPlayerListener<T extends PlatformPlayer> {

	public void onTurnLeft(T player);
	
	public void onTurnRight(T player);
	
	public void onWalkLeft(T player);
		
	public void onWalkRight(T player);
		
	public void onLookUp(T player);
	
	public void onStandDown(T player);
	
	public void onJump(T player);
	
	public void onFall(T player);
	
	public void onRun(T player);
	
	public void onStopWalkLeft(T player);
	
	public void onStopWalkRight(T player);
	
	public void onStopLookUp(T player);
	
	public void onStopStandDown(T player);
	
	public void onStopJump(T player);
	
	public void onStopRun(T player);
	
}
