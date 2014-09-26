package br.com.tide;

import java.util.HashSet;
import java.util.Set;

import br.com.etyllica.linear.PointInt2D;

public class Player extends PointInt2D {
	
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

}
