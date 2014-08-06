package br.com.etyllica.animation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.etyllica.animation.scripts.AnimationScript;
import br.com.etyllica.core.Updatable;

public class AnimationHandler implements Updatable {

	private static AnimationHandler instance;

	private List<AnimationExecution> scripts = new ArrayList<AnimationExecution>();

	private List<AnimationScript> nextScripts = new ArrayList<AnimationScript>();

	private Map<AnimationScript, AnimationScript> endlessScripts = new HashMap<AnimationScript, AnimationScript>();

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
				repeatLogic(execution);
			}			
		}

		//Add next Scripts
		for(AnimationScript script: nextScripts) {
			scripts.add(new AnimationExecution(script));
		}

		nextScripts.clear();

	}

	private void repeatLogic(AnimationExecution execution) {

		AnimationScript script = execution.getScript();
		
		if(script.getRepeat() == AnimationScript.REPEAT_FOREVER) {

			endlessScripts.put(lastScript(script),script);

		} else if(script.getRepeat()-1 > execution.getRepeated()) {
			
			execution.repeat();
			
			return;
		}

		if(script.getNext()!=null) {

			nextScripts.add(script.getNext());

			//Else If this script has some associated endless script
		} else if(endlessScripts.containsKey(script)) {

			//Find and restart the endless script
			AnimationScript endless = script;
			restartEndless(endless);

			nextScripts.add(endless);

		} else {

			//If script is endless and don't have next
			if(script.getRepeat() == AnimationScript.REPEAT_FOREVER) {
				script.restart();
				nextScripts.add(script);
			}
		}
		
		scripts.remove(script);

	}

	public void add(AnimationScript script) {
		scripts.add(new AnimationExecution(script));
	}

	private AnimationScript restartEndless(AnimationScript script) {

		script.restart();

		AnimationScript last = script;

		if(script.getNext() != null) {
			last = restartEndless(script.getNext()); 
		}

		return last;

	}
	
	private AnimationScript lastScript(AnimationScript script) {

		AnimationScript last = script;

		if(script.getNext()!=null) {
			last = lastScript(script.getNext()); 
		}

		return last;

	}

}
