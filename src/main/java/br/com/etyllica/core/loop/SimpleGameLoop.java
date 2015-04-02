package br.com.etyllica.core.loop;

import br.com.etyllica.core.GameCore;

public class SimpleGameLoop extends GameLoopImpl {
	
	private static final int UPDATE_DELAY = 1000/60;
	
	public SimpleGameLoop(GameCore core) {
		super(core);
	}

	@Override
	public boolean loop() {
		
		long previous = System.currentTimeMillis();
		
		long countTime = previous;
		
		int fps = 0;
		
		double lag = 0.0;
		
		while (core.isRunning()) {
			
		  long now = System.currentTimeMillis();
		  long elapsed = now - previous;
		  long delta = (long) (elapsed/UPDATE_DELAY);
		  
		  previous = now;
		  
		  lag += elapsed;

		  while (lag >= UPDATE_DELAY) {
			core.update(delta);
		    lag -= UPDATE_DELAY;
		  }

		  core.render();
		  fps++;
		  
		  if(now - countTime >= 1000) {
				countTime += 1000;

				core.setFps(fps);

				fps = 0;
			}
		  
		}
		return true;		
	}
	
}
