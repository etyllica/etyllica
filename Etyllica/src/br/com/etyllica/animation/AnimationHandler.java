package br.com.etyllica.animation;

import java.util.ArrayList;
import java.util.List;

import br.com.etyllica.animation.scripts.AnimationScript;
import br.com.etyllica.core.Updatable;

public class AnimationHandler implements Updatable {

	private static AnimationHandler instance;

	private List<AnimationExecution> scripts = new ArrayList<AnimationExecution>();

	private List<AnimationScript> nextScripts = new ArrayList<AnimationScript>();

	private AnimationHandler() {
		super();
	}

	public static AnimationHandler getInstance() {

		if (instance == null) {
			instance = new AnimationHandler();
		}

		return instance;
	}

	public void update(long now) {

		for(int i = scripts.size()-1; i>=0; i--) {

			AnimationExecution execution = scripts.get(i);

			if(!execution.execute(now)) {
				if(repeatLogic(execution, now)) {
					scripts.remove(execution);
				}
			}
		}

		//Add next Scripts
		for(AnimationScript script: nextScripts) {
			scripts.add(new AnimationExecution(script));
		}

		nextScripts.clear();
	}

	private boolean repeatLogic(AnimationExecution execution, long now) {

		AnimationScript script = execution.getScript();

		if(script.getRepeat() == AnimationScript.REPEAT_FOREVER) {
			
			//Repeat Forever
			script.restart();
			nextScripts.add(script);
			
		} else if(script.getRepeat()-1 > execution.getRepeated()) {

			//Repeat cycle
			//script.calculate(1);
			execution.repeat();
			script.preAnimate(now);
			
			return false;

		} else {
			//Animation is over
			notifyFinish(script, now);
			
			//Next Script
			appendNextScript(script);
		}

		return true;
	}
	
	private void appendNextScript(AnimationScript script) {
		//Next Script
		AnimationScript nextScript = script.getNext();

		if(nextScript != null) {
			nextScripts.add(nextScript);
			nextScript.restart();
		}
	}
	
	private void notifyFinish(AnimationScript script, long now) {
		if(script.getListener()==null)
			return;
		
		script.getListener().onAnimationFinish(now);		
	}

	public void add(AnimationScript script) {
		scripts.add(new AnimationExecution(script));
	}

}
