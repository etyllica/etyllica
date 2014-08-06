package br.com.etyllica.animation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import br.com.etyllica.animation.scripts.AnimationScript;
import br.com.etyllica.core.Updatable;

public class AnimationHandler implements Updatable {

	private static AnimationHandler instance;

	private Set<AnimationScript> scripts = new HashSet<AnimationScript>();

	private Set<AnimationScript> removeScripts = new HashSet<AnimationScript>();

	private Set<AnimationScript> nextScripts = new HashSet<AnimationScript>();

	private Map<AnimationScript, AnimationScript> endlessScripts = new HashMap<AnimationScript, AnimationScript>();

	private int repeated = 0;

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

		for(AnimationScript script: scripts) {

			if(!script.isStopped()) {

				script.preAnimate(now);

			} else {

				repeatLogic(script);
			}
		}

		//Remove marked Scripts
		for(AnimationScript script: removeScripts) {
			scripts.remove(script);
		}

		removeScripts.clear();

		//Add next Scripts
		for(AnimationScript script: nextScripts) {
			scripts.add(script);
		}

		nextScripts.clear();		

	}

	private void repeatLogic(AnimationScript script) {

		if(script.getRepeat() == AnimationScript.REPEAT_FOREVER) {

			endlessScripts.put(lastScript(script),script);

		} else if(script.getRepeat() < repeated) {

			repeated++;

			script.restart();

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

		repeated = 0;
		
		removeScripts.add(script);	

	}

	public void add(AnimationScript script) {
		scripts.add(script);
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
