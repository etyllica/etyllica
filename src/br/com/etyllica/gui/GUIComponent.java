package br.com.etyllica.gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.etyllica.animation.Animation;
import br.com.etyllica.core.Component;
import br.com.etyllica.core.event.GUIAction;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.layer.Layer;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public abstract class GUIComponent extends Layer implements Component{
	
	protected GUIEvent lastEvent = GUIEvent.NONE;
	
	protected boolean onFocus = false;
	protected boolean mouseOver = false;

	protected GUIComponent root = null;
	
	private Set<GUIComponent> components = new LinkedHashSet<GUIComponent>();
	
	protected List<GUIAction> actions = new ArrayList<GUIAction>();
	
	//GUIAction's Map
	protected Map<GUIEvent,GUIAction> map = new HashMap<GUIEvent, GUIAction>();
		
	protected String alt = "";
			
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

	public List<GUIAction> getActions() {
		return actions;
	}

	public void setActions(List<GUIAction> actions) {
		this.actions = actions;
	}

	public Set<GUIComponent> getComponents() {
		return components;
	}

	public void clearComponents(){
		this.components.clear();
	}
	
	public void remove(GUIComponent component){
		this.components.remove(component);
	}
	
	public void removeAll(Collection<? extends GUIComponent> components){
		this.components.removeAll(components);
	}
	
	//Always add component at first
	public void add(GUIComponent component) {
		component.setRoot(this);
		this.components.add(component);
	}
	
	public void addAll(Collection<? extends GUIComponent> components) {
		
		for(GUIComponent component: components)	{
			add(component);
		}
		
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

		if(map.containsKey(event)){
			map.get(event).executeAction();
		}
		
	}
	
	public void addAction(GUIEvent event, GUIAction action){
		map.put(event, action);
	}
		
	protected void setRoot(GUIComponent root){
		this.root = root;
	}
	
	public GUIComponent findNext(){
						
		if(root!=null){
			
			Iterator<GUIComponent> it = root.getComponents().iterator();

	        while(it.hasNext()){
	        	
	        	if(this.equals(it.next())){
	        		
	        		if(it.hasNext()){
	        			return it.next();
	        		}
	        	}
	        	
	        }
			
		}
		
		return null;
		
	}
	
	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}
	
}
