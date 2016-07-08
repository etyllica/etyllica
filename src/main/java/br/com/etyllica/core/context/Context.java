package br.com.etyllica.core.context;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import br.com.etyllica.cinematics.Camera;
import br.com.etyllica.core.Updatable;
import br.com.etyllica.core.context.load.ApplicationLoadListener;
import br.com.etyllica.core.context.load.DefaultLoadApplication;
import br.com.etyllica.core.effect.TransitionEffect;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.MouseState;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.core.i18n.Language;
import br.com.etyllica.core.i18n.LanguageChanger;
import br.com.etyllica.core.i18n.LanguageChangerListener;
import br.com.etyllica.core.input.mouse.MouseStateChanger;
import br.com.etyllica.core.input.mouse.MouseStateListener;
import br.com.etyllica.core.ui.UIComponent;
import br.com.etyllica.core.ui.ViewContainer;
import br.com.etyllica.gui.View;
import br.com.etyllica.gui.Window;
import br.com.etyllica.layer.Layer;

/**
 * Class to represent sessions of the Main Application like Mini-Applications.  
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public abstract class Context extends Layer implements ViewContainer, UIComponent, Updatable, MouseStateChanger, LanguageChanger {

	/**
	 * The updateInterval between executions
	 */
	private int updateInterval = 0;
	
	/**
	 * Listener to be updated
	 */
	private UpdateIntervalListener updated = null;

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
	protected String loadingInfo = "Loading...";

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
	protected Session session;

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
	 * Lock on update
	 */
	private boolean locked = false;
	
	/**
	 * Lock on load
	 * Start as true
	 */
	private boolean loaded = true;

	/**
	 * Pause
	 */
	protected boolean paused = false;
	
	/**
	 * Active Center Mouse
	 */
	protected boolean activeCenterMouse = false;

	/**
     * Application to be shown during loading
     */
	private ApplicationLoadListener loadListener;
	
	private MouseStateListener mouseStateListener;
	
	private LanguageChangerListener languageChangerListener;
	
	protected Window parent;
		
	/**
	 * Returned Application (next Application to show up)
	 */
	protected Context nextApplication = this;
	
	protected DefaultLoadApplication loadApplication;
	
	protected int fps = 0;
	
	private boolean drawCursor = true;
	
	protected List<View> views = new ArrayList<View>();
	
	/**
	 * Constructor
	 * 
	 * @param x coordinate to show Application (useful with multiple Applications) 
	 * @param y coordinate to show Application (useful with multiple Applications)
	 * @param w Application width
	 * @param h Application height
	 */
	public Context(int x, int y, int w, int h) {
		super(x, y, w, h);

		this.loading = 0;
		//TODO Dictionary get "loading"+...
		this.loadingInfo = "Carregando...";
	}

	/**
	 * Constructor for "fit Window" Applications
	 * 
	 * @param w Application width
	 * @param h Application height
	 */
	public Context(int w, int h) {
		this(0, 0, w, h);
	}

	public void startLoad() {
		locked = true;
		this.loading = 0;
		load();
		locked = false;
		notifyListeners();
	}

	private void notifyListeners() {
		loadListener.onApplicationLoaded();
	}

	/**
	 * Load method is the first method called before constructor. 
	 * Application gets lock while load() and unlocks when loading = 100;     
	 */
	public abstract void load();
	
	/**
	 * Draw method
	 */
	public abstract void draw(Graphics g);

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

	public String getLoadingInfo() {
		return loadingInfo;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Session getSession() {
		return session;
	}

	public void setSessionValue(String key, Object value) {
		session.put(key, value);
	}

	public Object getSessionValue(String key) {
		return session.get(key);
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

	protected  void updateAtFixedRate(int interval, UpdateIntervalListener updated) {
		updateInterval = interval;
		this.updated = updated;
	}

	protected  void stopTimeUpdate() {
		updateInterval = 0;
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

	public void changeLanguage(Language language) {
		if(languageChangerListener != null)
			languageChangerListener.changeLanguage(language);
	}
	
	public LanguageChangerListener getLanguageChangerListener() {
		return languageChangerListener;
	}

	public void setLanguageChangerListener(
			LanguageChangerListener languageChangerListener) {
		this.languageChangerListener = languageChangerListener;
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

	public void setLoadListener(ApplicationLoadListener listener) {
		this.loadListener = listener;
	}
	
	public Context getNextApplication() {
		return nextApplication;
	}
	
	protected void setNextApplication(Context nextApplication) {
		this.nextApplication = nextApplication;
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

	public DefaultLoadApplication getLoadApplication() {
		return loadApplication;
	}

	public UpdateIntervalListener getUpdated() {
		return updated;
	}

	public boolean isDrawCursor() {
		return drawCursor;
	}
	
	public void setDrawCursor(boolean drawCursor) {
		this.drawCursor = drawCursor;
	}

	public void hideCursor() {
		this.drawCursor = false;
	}
	
	public void showCursor() {
		this.drawCursor = true;
	}

	public boolean isActiveCenterMouse() {
		return activeCenterMouse;
	}

	public void setParent(Window window) {
		this.parent = window;
		setSession(window.getSession());
		setCamera(window.getCamera());
	}

	public boolean isLoaded() {
		return loaded;
	}

	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}

	public List<View> getViews() {
		return views;
	}

	public void setViews(List<View> views) {
		this.views = views;
	}
	
	public void addView(View view) {
		this.views.add(view);
	}

	public Color getBackgroundColor() {
		return Color.WHITE;
	}
	
}
