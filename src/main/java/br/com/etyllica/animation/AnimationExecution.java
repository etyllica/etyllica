package br.com.etyllica.animation;

import br.com.etyllica.animation.scripts.AnimationScript;

public class AnimationExecution {

	private int repeated = 0;

	private AnimationScript script;

	public AnimationExecution(AnimationScript script) {
		super();

		this.script = script;
	}
	
	public boolean execute(long now) {
		
		if(!script.isStopped()) {

			script.preAnimate(now);
			
			return true;			
		}

		return false;		
	}
	
	public void repeat() {
		repeated++;

		script.restart();
	}

	public int getRepeated() {
		return repeated;
	}

	public void setRepeated(int repeated) {
		this.repeated = repeated;
	}

	public AnimationScript getScript() {
		return script;
	}

	public void setScript(AnimationScript script) {
		this.script = script;
	}	

}
