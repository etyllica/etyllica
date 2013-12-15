package br.com.etyllica.core.application;

import java.util.List;

import br.com.etyllica.core.application.load.LoadListener;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.video.Graphic;
import br.com.etyllica.effects.TransitionEffect;
import br.com.etyllica.gui.View;
import br.com.etyllica.layer.Layer;
import br.com.etyllica.scene.Scene;

/**
 * Class to represent sessions of the Main Application like Mini-Applications.  
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public abstract class Context extends View{

	/**
	 * The updateInterval between executions
	 */
	private int updateInterval = 0;

	/**
	 * Transition effect while change to returned Application
	 */
	protected TransitionEffect effect = TransitionEffect.NONE;

	/**
	 * Load percentage unlock Application when reaches 100 
	 */
	protected float loading = 0;

	/**
	 * Loading phrase while loading Application 
	 */
	protected String loadingPhrase = "Loading...";

	/**
	 * Application title (useful with windows) 
	 */
	protected String title = "Application";

	/**
	 * Clear application before every draw call  
	 */
	protected boolean clearBeforeDraw = true;

	/**
	 * Map shared between Applications  
	 */
	protected SessionMap sessionMap;

	/**
	 * Scene Graph Windows
	 */
	protected Scene scene = new Scene();

	/**
	 * Last time updated
	 */

	protected long lastUpdate = 0;

	/**
	 * Lock
	 */
	private boolean locked = false;

	/**
	 * Pause
	 */
	protected boolean paused = false;

	private LoadListener loadListener;

	/**
	 * Returned Application (next Application to show up)
	 */
	protected Context returnApplication = this;
	
	/**
	 * Constructor
	 * 
	 * @param x coordinate to show Application (useful with multiple Applications) 
	 * @param y coordinate to show Application (useful with multiple Applications)
	 * @param w Application width
	 * @param h Application height
	 */
	public Context(float x, float y, float w, float h){
		super(x,y,w,h);

		this.loading = 0;
		//TODO Dictionary get "loading"+...
		this.loadingPhrase = "Carregando...";

		//loadListeners = new ArrayList<LoadListener>();
	}

	/**
	 * Constructor for "fit Window" Applications
	 * 
	 * @param w Application width
	 * @param h Application height
	 */
	public Context(float w, float h){
		this(0,0,w,h);
	}

	public void startLoad(){

		locked = true;
		this.loading = 0;
		load();
		locked = false;
		notifyListeners();

	}

	private void notifyListeners(){

		//for(LoadListener listener: loadListeners){
		loadListener.loaded();
		//}

	}

	/**
	 * Load method is the first method called before constructor. 
	 * Application gets lock while load() and unlocks when loading = 100;     
	 */
	public abstract void load();

	/**
	 * Draw Scene method with recurrency
	 */
	public void drawScene(Graphic g){

		this.draw(g);
		
		for(Layer layer: scene.getGraph()){
			drawLayer(layer, g);
		}

	}

	private void drawLayer(Layer layer, Graphic g){

		layer.draw(g);
		
		List<Layer> children = layer.getChildren();

		if(children==null||children.isEmpty()){
			
			return;
			
		}else{
			
			for(Layer child: layer.getChildren()){
				drawLayer(child,g);
			}
			
		}
	}

	/**
	 * Draw method
	 */
	public abstract void draw(Graphic g);

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
	public boolean onMouse(float mx, float my) {
		return false;
	}


	public float getLoading(){
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	protected  void updateAtFixedRate(int interval){
		updateInterval = interval;
	}

	protected  void stopTimeUpdate(){
		updateInterval = 0;
	}

	public void timeUpdate(){

	}

	/*public AnimationHandler getAnimation() {
		return animation;
	}

	public void setAnimation(AnimationHandler animation) {
		this.animation = animation;
	}*/

	public int getUpdateInterval() {
		return updateInterval;
	}

	public long getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(long lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLoadListener(LoadListener listener){
		this.loadListener = listener;
	}
	
	public Context getReturnApplication() {
		return returnApplication;
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}
	
}
