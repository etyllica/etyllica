package br.com.etyllica.core.animation;

import java.util.ArrayList;
import java.util.List;

import br.com.etyllica.core.Updatable;
import br.com.etyllica.core.animation.script.AnimationScript;

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
		//Next Scripts
		List<AnimationScript> nextScript = script.getNext();

		if(nextScript != null) {
			for(AnimationScript s: nextScript) {
				nextScripts.add(s);	
				s.restart();
			}
		}
	}
	
	private void notifyFinish(AnimationScript script, long now) {
		script.finish(now);		
	}

	public void add(AnimationScript script) {
		scripts.add(new AnimationExecution(script));
	}
	
	public void remove(AnimationScript script) {
		for(AnimationExecution execution: scripts) {
			if(execution.getScript() == script) {
				scripts.remove(script);
				break;
			}
		}
	}
	
	public void clearAll() {
		scripts.clear();
		nextScripts.clear();
	}

}
