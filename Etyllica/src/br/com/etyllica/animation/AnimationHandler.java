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
			}else{
				//if stopped, use child (next script)
				if(script.getNext()!=null) {

					if(script.getRepeat() == AnimationScript.REPEAT_FOREVER) {
						endlessScripts.put(lastScript(script),script);
					}

					nextScripts.add(script.getNext());


				}else{

					//If this script has some associated endless script
					if(endlessScripts.containsKey(script)) {
						//Find and restart the endless script
						AnimationScript endless = endlessScripts.get(script);
						restartEndless(endless);

						nextScripts.add(endless);

					}else{

						//If script is endless and don't have next
						if(script.getRepeat() == AnimationScript.REPEAT_FOREVER) {
							script.restart();
							nextScripts.add(script);
						}
					}
				}
				removeScripts.add(script);
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

	public void add(AnimationScript script) {
		scripts.add(script);
	}

	private AnimationScript restartEndless(AnimationScript script) {

		script.restart();

		AnimationScript last = script;

		if(script.getNext()!=null) {
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
