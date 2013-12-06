package br.com.etyllica.gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import br.com.etyllica.core.Component;
import br.com.etyllica.core.event.Action;
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
	
	private List<GUIComponent> components = new ArrayList<GUIComponent>();
	
	protected List<Action> actions = new ArrayList<Action>();
	
	//GUIAction's Map
	protected Map<GUIEvent,Action> map = new HashMap<GUIEvent, Action>();
				
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

	/**
	 * 
	 * @param lastEvent
	 */
	public void setLastEvent(GUIEvent lastEvent) {
		this.lastEvent = lastEvent;
	}

	public boolean isMouseOver() {
		return mouseOver;
	}
	
	/**
	 * 
	 * @param mouseOver
	 */
	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}

	public boolean isOnFocus() {
		return onFocus;
	}

	/**
	 * 
	 * @param focus
	 */
	public void setOnFocus(boolean focus) {
		this.onFocus = focus;
	}

	public List<Action> getActions() {
		return actions;
	}

	/**
	 * 
	 * @param actions
	 */
	public void setActions(List<Action> actions) {
		this.actions = actions;
	}

	public List<GUIComponent> getComponents() {
		return components;
	}

	public void clearComponents(){
		this.components.clear();
	}
	
	/**
	 * 
	 * @param component
	 */
	public void remove(GUIComponent component){
		this.components.remove(component);
	}
	
	/**
	 * 
	 * @param components
	 */
	public void removeAll(Collection<? extends GUIComponent> components){
		this.components.removeAll(components);
	}
	
	/**
	 * @param component
	 */
	public void add(GUIComponent component) {
		component.setRoot(this);
		this.components.add(component);
	}
	
	/**
	 * 
	 * @param components
	 */
	public void addAll(Collection<? extends GUIComponent> components) {
		
		for(GUIComponent component: components)	{
			add(component);
		}
		
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
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
	
	/**
	 * 
	 * @param event
	 * @param action
	 */
	public void addAction(GUIEvent event, Action action){
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
	
}
