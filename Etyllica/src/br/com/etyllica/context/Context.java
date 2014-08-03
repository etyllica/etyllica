package br.com.etyllica.context;

import br.com.etyllica.cinematics.Camera;
import br.com.etyllica.context.load.LoadListener;
import br.com.etyllica.core.Updatable;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.input.mouse.MouseState;
import br.com.etyllica.core.input.mouse.MouseStateChanger;
import br.com.etyllica.core.input.mouse.MouseStateListener;
import br.com.etyllica.effects.TransitionEffect;
import br.com.etyllica.gui.View;

/**
 * Class to represent sessions of the Main Application like Mini-Applications.  
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public abstract class Context extends View implements Updatable, MouseStateChanger {

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
	 * Scene camera  
	 */
	protected Camera camera;
	
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
	
	private MouseStateListener mouseStateListener;

	/**
	 * Returned Application (next Application to show up)
	 */
	protected Context returnApplication = this;
	
	protected int fps = 0;
	
	/**
	 * Constructor
	 * 
	 * @param x coordinate to show Application (useful with multiple Applications) 
	 * @param y coordinate to show Application (useful with multiple Applications)
	 * @param w Application width
	 * @param h Application height
	 */
	public Context(int x, int y, int w, int h) {
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
	public Context(int w, int h) {
		this(0,0,w,h);
	}

	public void startLoad() {

		locked = true;
		this.loading = 0;
		load();
		locked = false;
		notifyListeners();

	}

	private void notifyListeners() {

		//for(LoadListener listener: loadListeners) {
		loadListener.loaded();
		//}

	}

	/**
	 * Load method is the first method called before constructor. 
	 * Application gets lock while load() and unlocks when loading = 100;     
	 */
	public abstract void load();

	public void drawScene(Graphic g) {

		this.draw(g);
		
		scene.draw(g);
		
	}
	
	/**
	 * Draw method
	 */
	public abstract void draw(Graphic g);

	/**
	 * Unload is not implemented yet but is useful when Applications needs free memory before change to next Application
	 */
	public void unload() {

	}

	/**
	 * Method to Update by GUI events
	 */
	public void update(GUIEvent event) {
		// TODO Auto-generated method stub
	}
	
	public void update(long now) {
		
	}

	@Override
	public boolean onMouse(int mx, int my) {
		return false;
	}


	public float getLoading() {
		return loading;
	}

	public String getLoadingPhrase() {
		return loadingPhrase;
	}

	public void setSessionMap(SessionMap sessionMap) {
		this.sessionMap = sessionMap;
	}

	public SessionMap getSessionMap() {
		return sessionMap;
	}

	public void setVariavelSessao(String nomeVariavel,Object objeto) {
		sessionMap.put(nomeVariavel, objeto);
	}

	public Object getVariavelSessao(String nomeVariaviel) {

		if(sessionMap.containsKey(nomeVariaviel)) {
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

	protected  void updateAtFixedRate(int interval) {
		updateInterval = interval;
	}

	protected  void stopTimeUpdate() {
		updateInterval = 0;
	}

	public void timeUpdate(long now) {

	}
	
	public void changeMouseState(MouseState state) {
		if(mouseStateListener != null)
			mouseStateListener.changeState(state);
	}
	
	public MouseStateListener getMouseStateListener() {
		return mouseStateListener;
	}

	public void setMouseStateListener(MouseStateListener mouseStateListener) {
		this.mouseStateListener = mouseStateListener;
	}

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

	public void setLoadListener(LoadListener listener) {
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
	
	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public int getFps() {
		return fps;
	}

	public void setFps(int fps) {
		this.fps = fps;
	}
		
}
