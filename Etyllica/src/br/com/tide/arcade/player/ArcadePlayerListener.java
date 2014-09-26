package br.com.tide.arcade.player;

public interface ArcadePlayerListener {

	public void onWalkLeft(ArcadePlayer player);
	
	public void onWalkRight(ArcadePlayer player);
	
	public void onWalkUp(ArcadePlayer player);
	
	public void onWalkDown(ArcadePlayer player);
	
	public void onStopWalkLeft(ArcadePlayer player);
	
	public void onStopWalkRight(ArcadePlayer player);
	
	public void onStopWalkUp(ArcadePlayer player);
	
	public void onStopWalkDown(ArcadePlayer player);
		
}
