package br.com.etyllica.animation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Animation {

	private List<AnimationScript> scripts = new ArrayList<AnimationScript>();

	private Set<AnimationScript> removeScripts = new HashSet<AnimationScript>();
	
	private Set<AnimationScript> nextScripts = new HashSet<AnimationScript>();

	public Animation(){
		super();
	}

	public void animate(long now){

		for(AnimationScript script: scripts){

			if(!script.isStopped()){
				script.preAnimate(now);
			}else{
				//if stopped, use child (next script)
				if(script.getNext()!=null){
					nextScripts.add(script.getNext());
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

}
