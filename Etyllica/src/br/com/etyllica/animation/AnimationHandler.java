package br.com.etyllica.animation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AnimationHandler {

	private List<AnimationScript> scripts = new ArrayList<AnimationScript>();

	private Set<AnimationScript> removeScripts = new HashSet<AnimationScript>();

	private Set<AnimationScript> nextScripts = new HashSet<AnimationScript>();

	private Map<AnimationScript, AnimationScript> endlessScripts = new HashMap<AnimationScript, AnimationScript>();

	public AnimationHandler(){
		super();
	}

	public void animate(long now){

		for(AnimationScript script: scripts){

			if(!script.isStopped()){
				script.preAnimate(now);
			}else{
				//if stopped, use child (next script)
				if(script.getNext()!=null){

					if(script.isEndless()){
						endlessScripts.put(lastScript(script),script);
					}

					nextScripts.add(script.getNext());


				}else{

					//If this script has some associated endless script
					if(endlessScripts.containsKey(script)){
						//Find and restart the endless script
						AnimationScript endless = endlessScripts.get(script);
						restartEndless(endless);

						nextScripts.add(endless);

					}else{

						//If script is endless and don't have next
						if(script.isEndless()){
							script.restart();
							nextScripts.add(script);
						}
					}
				}
				removeScripts.add(script);
			}

		}

		//Remove marked Scripts
		for(AnimationScript script: removeScripts){
			scripts.remove(script);
		}

		removeScripts.clear();

		//Add next Scripts
		for(AnimationScript script: nextScripts){
			scripts.add(script);
		}

		nextScripts.clear();		

	}

	public void add(AnimationScript script){
		scripts.add(script);
	}

	private AnimationScript restartEndless(AnimationScript script){

		script.restart();

		AnimationScript last = script;

		if(script.getNext()!=null){
			last = restartEndless(script.getNext()); 
		}

		return last;

	}

	private AnimationScript lastScript(AnimationScript script){

		AnimationScript last = script;

		if(script.getNext()!=null){
			last = lastScript(script.getNext()); 
		}

		return last;

	}

}
