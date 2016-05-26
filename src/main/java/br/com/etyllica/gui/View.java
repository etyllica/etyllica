package br.com.etyllica.gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import br.com.etyllica.core.Drawable;
import br.com.etyllica.core.event.Action;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.ui.ViewGroup;
import br.com.etyllica.layer.Layer;
import br.com.etyllica.theme.Theme;
import br.com.etyllica.theme.ThemeManager;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public abstract class View extends Layer implements GUIComponent, Drawable, ViewGroup {
	
	protected GUIEvent lastEvent = GUIEvent.NONE;
	
	protected boolean onFocus = false;
	protected boolean mouseOver = false;

	protected View root = null;
	
	private List<View> views = new ArrayList<View>();
	
	protected List<Action> actions = new ArrayList<Action>();
	
	//GUIAction's Map
	protected Map<GUIEvent,Action> actionMap = new HashMap<GUIEvent, Action>();
		
	public View(int x, int y) {
		super(x,y,1,1);
	}
	
	public View(int x, int y, int w, int h) {
		super(x,y,w,h);
	}
	
	public View() {
		super(0, 0);
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

	public synchronized List<View> getViews() {
		return views;
	}

	public void clearComponents(){
		this.views.clear();
	}
	
	/**
	 * 
	 * @param component
	 */
	public void remove(View component){
		this.views.remove(component);
	}
	
	/**
	 * 
	 * @param components
	 */
	public void removeAll(Collection<? extends View> components){
		this.views.removeAll(components);
	}
	
	/**
	 * @param component
	 */
	public void add(View component) {
		component.setRoot(this);
		this.views.add(component);
	}
	
	/**
	 * 
	 * @param components
	 */
	public void addAll(Collection<? extends View> components) {
		
		for(View component: components)	{
			add(component);
		}
		
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public void translateComponents(int x, int y){
		for(View component: views){
			translateComponent(x, y, component);
		}
	}
	
	private void translateComponent(int x, int y, View component){
		
		component.setLocation(x, y);
		
		for(View child: component.views){
			translateComponent(x, y, child);
		}
		
	}	


	/**
	 * Method to execute component's associated actions
	 * 
	 * @param event
	 */	
	public void executeAction(GUIEvent event) {

		if(actionMap.containsKey(event)) {
			actionMap.get(event).executeAction();
		}
		
	}
	
	/**
	 * 
	 * @param event
	 * @param action
	 */
	public void addAction(GUIEvent event, Action action){
		actionMap.put(event, action);
	}
	
	protected void setRoot(View root){
		this.root = root;
	}
	
	public View findNext(){
						
		if(root!=null){
			
			Iterator<View> it = root.getViews().iterator();

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
	
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
	}
	
	public GUIEvent safeUpdateMouse(PointerEvent event) {
		if(!isMouseOver()) {
			if(onMouse(event)) {
				mouseIn();
				return GUIEvent.MOUSE_IN;
			}
			
		} else {
			if(!onMouse(event)) {
				mouseOut();
				return GUIEvent.MOUSE_OUT;
			}
		}
		
		//return GUIEvent.NONE;
		return updateMouse(event);
	}
	
	public void mouseIn() {
		setMouseOver(true);
		update(GUIEvent.MOUSE_IN);
	}	
	
	public void mouseOut() {
		setMouseOver(false);
		update(GUIEvent.MOUSE_OUT);
	}

	public Theme getTheme() {
		return ThemeManager.getInstance().getTheme();
	}
}
