package br.com.tide.arcade.player;

public interface ArcadePlayerListener<T extends ArcadePlayer> {

	public void onWalkLeft(T player);
	
	public void onWalkRight(T player);
	
	public void onWalkUp(T player);
	
	public void onWalkDown(T player);
	
	public void onStopWalkLeft(T player);
	
	public void onStopWalkRight(T player);
	
	public void onStopWalkUp(T player);
	
	public void onStopWalkDown(T player);
	
	public void onAttack(T player);
		
}
