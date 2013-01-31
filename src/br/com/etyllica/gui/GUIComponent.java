package br.com.etyllica.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.etyllica.core.Component;
import br.com.etyllica.core.event.GUIAction;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyboardEvent;
import br.com.etyllica.layer.Layer;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public abstract class GUIComponent extends Layer implements Component{
	
	protected GUIEvent lastEvent = GUIEvent.NONE;
	
	protected boolean onFocus = false;
	protected boolean mouseOver = false;
	
	protected int roundness;

	protected List<GUIComponent> components = new ArrayList<GUIComponent>();
	protected List<GUIAction> actions = new ArrayList<GUIAction>();
	
	//TODO Remover lista e trocar por mapa
	protected Map<GUIEvent,GUIAction> mapa = new HashMap<GUIEvent, GUIAction>();
	
	public GUIComponent(int x, int y) {
		super(x,y,1,1);
	}
	
	public GUIComponent(int x, int y, int w, int h){
		super(x,y,w,h);
	}
	
	public GUIComponent(){
		this(0,0);
	}
	
	public GUIEvent getLastEvent() {
		return lastEvent;
	}

	public void setLastEvent(GUIEvent lastEvent) {
		this.lastEvent = lastEvent;
	}

	public boolean isMouseOver() {
		return mouseOver;
	}

	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}

	public boolean isOnFocus() {
		return onFocus;
	}

	public void setOnFocus(boolean focus) {
		this.onFocus = focus;
	}
	

	public int getRoundness() {
		return roundness;
	}

	public void setRoundness(int roundness) {
		this.roundness = roundness;
	}

	public List<GUIAction> getActions() {
		return actions;
	}

	public void setActions(List<GUIAction> actions) {
		this.actions = actions;
	}

	public List<GUIComponent> getComponents() {
		return components;
	}

	//Always add component at first
	public void addComponent(GUIComponent component) {
		this.components.add(0,component);
	}
	
	public void translateComponents(int x, int y){
		for(GUIComponent component: components){
			translateComponent(x, y, component);
		}
	}
	
	private void translateComponent(int x, int y, GUIComponent component){
		
		component.setOffset(x, y);
		
		for(GUIComponent child: component.components){
			translateComponent(x, y, child);
		}
		
	}	


	/**
	 * Method to execute component's associated actions
	 * 
	 * @param event
	 */	
	public void executeAction(GUIEvent event){

		if(mapa.containsKey(event)){
			mapa.get(event).executeAction();
		}
		
	}
	
	public void addAction(GUIEvent event, GUIAction action){
		mapa.put(event, action);
	}
	

	@Override
	public GUIEvent updateKeyboard(KeyboardEvent event) {
		return GUIEvent.NONE;
	}	
	
}
