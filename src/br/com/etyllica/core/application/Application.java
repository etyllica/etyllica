package br.com.etyllica.core.application;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.effects.TransitionEffect;
import br.com.etyllica.gui.GUIComponent;
import br.com.etyllica.gui.Window;
import br.com.etyllica.layer.ImageLayer;

/**
 * Class to represent sessions of the Main Application like Mini-Applications.  
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public abstract class Application extends GUIComponent implements Runnable{

	/**
	 * Executor to update Application by time 
	 */
	private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
	
	/**
	 * The updateInterval between executions
	 */
	private int updateInterval = 0;
	
	/**
	 * Returned Application (next Application to show up)
	 */
	protected Application returnApplication = this;
	
	/**
	 * Transition effect while change to returned Application
	 */
	protected TransitionEffect effect = TransitionEffect.NONE;
	
	/**
	 * Load percentage unlock Application when reaches 100 
	 */
	protected int loading = 0;
	
	/**
	 * Loading phrase while loading Application 
	 */
	protected String loadingPhrase = "Loading...";
	
	/**
	 * Application title (useful with windows) 
	 */
	protected String title = "Application";
	
	/**
	 * Application icon (useful with windows) 
	 */
	protected ImageLayer icon = null;
	
	/**
	 * Clear application before every draw call  
	 */
	protected boolean clearBeforeDraw = true;
	
	/**
	 * Map shared between Applications  
	 */
	protected SessionMap sessionMap;
	
	private List<GUIEvent> guiEvents = new ArrayList<GUIEvent>();
	
	private List<Window> windows = new ArrayList<Window>();	
	
	/**
	 * Constructor
	 * 
	 * @param x coordinate to show Application (useful with multiple Applications) 
	 * @param y coordinate to show Application (useful with multiple Applications)
	 * @param w Application width
	 * @param h Application height
	 */
	public Application(int x, int y, int w, int h){
		super(x,y,w,h);
		
		this.loading = 0;
		//TODO Dictionary get "loading"+...
		this.loadingPhrase = "Carregando...";
	}
	
	/**
	 * Constructor for "fit Window" Applications
	 * 
	 * @param w Application width
	 * @param h Application height
	 */
	public Application(int w, int h){
		this(0,0,w,h);
	}

	/**
	 * Load method is the first method called before constructor. 
	 * Application gets lock while load() and unlocks when loading = 100;     
	 */
	public abstract void load();
	
	/**
	 * Draw method     
	 */
	public abstract void draw(Grafico g);
	
	/**
	 * Unload is not implemented yet but is useful when Applications needs free memory before change to next Application
	 */
	public void unload(){
		
	}
	
	/**
	 * Method to Update by GUI events
	 */
	@Override
	public void update(GUIEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean onMouse(int mx, int my) {
		return false;
	}

	
	public int getLoading(){
		return loading;
	}
	
	public String getLoadingPhrase(){
		return loadingPhrase;
	}

	public void setSessionMap(SessionMap sessionMap){
		this.sessionMap = sessionMap;
	}
	
	public SessionMap getSessionMap(){
		return sessionMap;
	}
	
	public void setVariavelSessao(String nomeVariavel,Object objeto){
		sessionMap.put(nomeVariavel, objeto);
	}
	
	public Object getVariavelSessao(String nomeVariaviel){
		
		if(sessionMap.containsKey(nomeVariaviel)){
			return sessionMap.get(nomeVariaviel);
		}

		return null;
	}
	
	public boolean isClearBeforeDraw() {
		return clearBeforeDraw;
	}

	public void setClearBeforeDraw(boolean clearBeforeDraw) {
		this.clearBeforeDraw = clearBeforeDraw;
	}

	public Application getReturnApplication() {
		return returnApplication;
	}

	public void setReturnApplication(Application returnApplication) {
		this.returnApplication = returnApplication;
	}
		
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ImageLayer getIcon() {
		return icon;
	}

	public void setIcon(ImageLayer icon) {
		this.icon = icon;
	}
			
	protected  void updateAtFixedRate(int interval){
		
		updateInterval = interval;
		
		executor.scheduleAtFixedRate(this, 0, updateInterval, TimeUnit.MILLISECONDS);
		
	}

	public void run(){
		timeUpdate();
	}
	
	protected void timeUpdate(){
		
	}
	
	protected void addGUIEvent(GUIEvent event){
		guiEvents.add(event);
	}

	public List<GUIEvent> getGuiEvents() {
		return guiEvents;
	}

	public void setGuiEvents(List<GUIEvent> guiEvents) {
		this.guiEvents = guiEvents;
	}
	
	protected void addWindow(Window window){
		this.windows.add(window);
	}
	
	public List<Window> getWindows(){
		return windows;
	}
	
}
