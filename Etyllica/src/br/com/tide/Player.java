package br.com.tide;

import java.util.HashSet;
import java.util.Set;

public class Player {
	
	protected int x = 0;
	
	protected int y = 0;
	
	protected String name = "Player";

	protected Set<PlayerState> states = new HashSet<PlayerState>();
	
	public Player() {
		super();

		states.add(PlayerState.STAND);
	}
	
	public boolean hasState(PlayerState ... playerStates) {
		
		for(PlayerState playerState: playerStates) {
			
			if(states.contains(playerState)) {
				return true;
			}
			
		}
		
		return false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
}
