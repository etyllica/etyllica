package br.com.etyllica.gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.etyllica.core.Component;
import br.com.etyllica.core.event.PointerEvent;
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

	protected GUIComponent root = null;
	
	private Set<GUIComponent> components = new LinkedHashSet<GUIComponent>();
	
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
	
	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		return GUIEvent.NONE;
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
