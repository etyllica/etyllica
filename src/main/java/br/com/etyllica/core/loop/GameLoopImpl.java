package br.com.etyllica.core.loop;

import br.com.etyllica.core.Core;

public abstract class GameLoopImpl implements GameLoop {

	protected Core core;
	
	public GameLoopImpl(Core core) {
		super();
		
		this.core = core;		
	}
	
}
