package br.com.etyllica.core.loop;

import br.com.etyllica.core.GameCore;

public abstract class GameLoopImpl implements GameLoop {

	protected GameCore core;
	
	public GameLoopImpl(GameCore core) {
		super();
		
		this.core = core;		
	}
	
}
