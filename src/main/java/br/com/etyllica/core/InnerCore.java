package br.com.etyllica.core;

import java.util.ArrayList;
import java.util.List;

import br.com.etyllica.awt.AWTArrowDrawer;
import br.com.etyllica.awt.core.input.AWTController;
import br.com.etyllica.core.animation.AnimationHandler;
import br.com.etyllica.core.animation.script.AnimationScript;
import br.com.etyllica.core.animation.script.SingleIntervalAnimation;
import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.context.Context;
import br.com.etyllica.core.context.UpdateIntervalListener;
import br.com.etyllica.core.context.load.ApplicationLoader;
import br.com.etyllica.core.context.load.LoaderListener;
import br.com.etyllica.core.effect.GlobalEffect;
import br.com.etyllica.core.error.ErrorMessages;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.KeyEventListener;
import br.com.etyllica.core.event.MouseEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.event.PointerState;
import br.com.etyllica.core.graphics.ArrowDrawer;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.core.graphics.Monitor;
import br.com.etyllica.core.handler.Handler;
import br.com.etyllica.core.i18n.Language;
import br.com.etyllica.core.i18n.LanguageChangerListener;
import br.com.etyllica.core.i18n.LanguageHandler;
import br.com.etyllica.core.input.keyboard.Keyboard;
import br.com.etyllica.core.input.mouse.Mouse;
import br.com.etyllica.core.ui.UIComponent;
import br.com.etyllica.core.ui.UICore;
import br.com.etyllica.core.ui.UICoreListener;
import br.com.etyllica.gui.View;
import br.com.etyllica.gui.Window;
import br.com.etyllica.gui.theme.Theme;
import br.com.etyllica.gui.theme.ThemeManager;
import br.com.etyllica.gui.theme.listener.ThemeListener;
import br.com.etyllica.theme.etyllic.EtyllicArrowTheme;

/**
 * 
 * @author yuripourre
 *
 */

public abstract class InnerCore implements Core, KeyEventListener, Updatable, ThemeListener, LanguageChangerListener, LoaderListener, UICoreListener {

	private static final int TITLE_BAR_HEIGHT = 50;
	
	//External Windows
	private Window activeWindow = null;

	protected AWTController control;

	private Mouse mouse;

	private Keyboard keyboard;

	//private List<KeyEvent> joyEvents;

	private Window mainWindow;

	//private boolean drawCursor = true;

	private boolean fullScreenEnable = false;

	private boolean needReload = false;

	private boolean locked = false;
	
	private boolean fixEventPosition = false;

	private int fps = 0;

	//FullScreen Stuff
	private boolean enableFullScreen = false;
	private boolean disableFullScreen = false;

	private boolean alt = false;
	private boolean enter = false;
	private boolean esc = false;

	protected GUIEvent superEvent = GUIEvent.NONE;

	//Create an Arrow Drawer 
	private ArrowDrawer arrowDrawer;

	private LanguageHandler languageHandler;

	private List<SingleIntervalAnimation> globalScripts = new ArrayList<SingleIntervalAnimation>();
	
	protected List<Monitor> monitors = new ArrayList<Monitor>();
		
	protected ApplicationLoader applicationLoader;
	protected UICore uiCore;

	private List<Handler> handlers = new ArrayList<Handler>();
	
	public InnerCore(int w, int h) {
		super();
		
		control = new AWTController(this);

		setMouse(control.getMouse());

		setKeyboard(control.getKeyboard());

		arrowDrawer = new AWTArrowDrawer();

		uiCore = new UICore(w, h, this);
		uiCore.setArrowDrawer(arrowDrawer);
		uiCore.setMouse(control.getMouse());
		
		languageHandler = new LanguageHandler();
		
		initTheme();

		//updatables.add(AnimationHandler.getInstance());
		
		applicationLoader = new ApplicationLoader(w, h);

		initHandlers();
	}

	//Default Handlers
	private void initHandlers() {
		handlers.add(AnimationHandler.getInstance());
	}

	private void initTheme() {

		ThemeManager.getInstance().setThemeListener(this);

		ThemeManager.getInstance().setArrowThemeListener(arrowDrawer);

		ThemeManager.getInstance().setArrowTheme(new EtyllicArrowTheme());
	}

	public Window getWindow() {
		return mainWindow;
	}

	public void update(long now) {
		
		if(!currentContext().isLoaded()) {
			return;
		} else if (needReload) {
			fastReload();
		}

		superEvent = GUIEvent.NONE;

		updateActiveWindow(now);

		updateHandlers(now);

		Context application = currentContext();

		updateApplication(application, now);
		
		if (checkApplicationChange(application)) {
			return;
		}

		updateInput(application, now);

		//Update in another thread
		//Joystick locks the application
		//JoystickLoader.getInstance().update(now);

		handleFullScreen();

	}

	private void updateInput(Context application, long now) {
		//Update All components
		//List<View> components = new CopyOnWriteArrayList<View>(application.getViews());

		for (Handler handler : handlers) {
			handler.update(now);
		}

		List<PointerEvent> events = getMouse().lock();
		updatePointerEvents(events, application, application.getViews());
		getMouse().unlock();

		//updateKeyboard();

		getKeyboard().update(now);
	}
	
	private boolean checkApplicationChange(Context application) {
		//if activeWindow, receive command to change application
		if(application.getNextApplication() != application) {

			this.changeApplication();
			return true;
		}
		
		return false;
	}

	private void handleFullScreen() {

		if(enableFullScreen) {
			enableFullScreen = false;

			superEvent = GUIEvent.ENABLE_FULL_SCREEN;
		}

		if(disableFullScreen) {
			disableFullScreen = false;

			superEvent = GUIEvent.DISABLE_FULL_SCREEN;
		}

	}

	public void resizeApplication(int w, int h) {

		Context application = currentContext();

		application.resize(w, h);

		application.setW(w);
		application.setH(h);
	}

	public boolean updateApplication(Context context, long now) {
		
		if(context.isLocked()) {
			return false;
		}

		if (context.getUpdateInterval() == 0) {

			context.update(now);

			context.setLastUpdate(now);

			//Update Components
			for (UIComponent component:context.getComponents()) {
				component.update(now);
			}

		}else if(now-context.getLastUpdate() >= context.getUpdateInterval()) {

			UpdateIntervalListener updated = context.getUpdated();
			
			if(updated==null) {
				return false;
			}
			
			updated.timeUpdate(now);

			context.setLastUpdate(now);
			
			for (UIComponent component:context.getComponents()) {
				component.update(now);
			}
		}

		return true;
	}

	private void updateActiveWindow(long now) {

		List<Window> windows = activeWindow.getWindows(); 

		//Creating Windows
		//if application has windows
		if(!windows.isEmpty()) {

			//For each new window in application.windows
			for(Window window : windows) {
				//if this !windows.contains(window)
				replaceWindow(window);
			}

			activeWindow.getWindows().clear();
		}

		/*if(activeWindow.isClose()) {

			if(windows.size()>0) {
				windows.remove(activeWindow);
				activeWindow = windows.get(windows.size()-1);
			}else{
				System.exit(0);
			}

	    }*/

	}

	@Override
	public void updateJoystickEvent(KeyEvent event) {

		Context context = currentContext();
		
		//Debug Joystick Commands
		//System.out.println("UpdateJoystick "+event.getKey());

		handleApplicationKeyEvents(context, event);
	}
	
	public void updatePointerEvents(List<PointerEvent> events, Context context, List<View> components) {

		int eventSize = events.size();
		
		for(int i=0; i < eventSize; i++) {

			PointerEvent event = events.get(i);
			context.updateMouse(event);

			//Update Handlers
			updatePointerEvent(event);
		}
	}

	public void updatePointerEvent(PointerEvent event) {

		if(fixEventPosition) {
			fixEventPosition(event);
		}

		for (Handler handler: handlers) {
			handler.updateMouse(event);
		}

		updateWindowEvent(event, activeWindow);		
	}

	

	private void updateWindowEvent(PointerEvent event, Window window) {

		GUIEvent frameEvent = updateFrameEvents(event);

		if(frameEvent != GUIEvent.NONE) {
			superEvent = frameEvent;
		}

	}

	public void draw(Graphics g) {

		if(!canDraw())
			return;

		drawContext(currentContext(), g);

		//Draw Handlers
		for(Handler handler: handlers) {
			handler.draw(g);
		}

		//Draw Global Effects
		drawGlobalEffects(g);
	}

	protected boolean canDraw() {
		return !locked && !needReload;
	}

	private void drawContext(Context context, Graphics g) {
		if (context.isClearBeforeDraw()) {
			g.setColor(context.getBackgroundColor());
			g.fillRect(0, 0, context.getW(), context.getH());
		}

		context.draw(g);
	}

	private void updateHandlers(long now) {
		for(Updatable updatable: handlers) {
			updatable.update(now);	
		}
	}

	private void drawGlobalEffects(Graphics g) {

		List<AnimationScript> remove = new ArrayList<AnimationScript>();

		for(SingleIntervalAnimation script: globalScripts) {

			if(!script.isStopped()) {
				script.getTarget().draw(g);
			} else {
				remove.add(script);
			}
		}

		for(AnimationScript script: remove) {
			globalScripts.remove(script);
		}
	}
			
	public boolean isMouseOver() {
		return uiCore.mouseOver != null;
	}

	public View getMouseOver() {
		return uiCore.mouseOver;
	}

	public void addEffect(GlobalEffect effect) {
		AnimationHandler.getInstance().add(effect.getScript());
		globalScripts.add(effect.getScript());

		//TODO add animation
		//globalEffects.add(effect); 
	}

	private void updateKeyboardEvents(KeyEvent event) {

		if(event.isKeyDown(KeyEvent.VK_ALT_RIGHT)||event.isKeyDown(KeyEvent.VK_ALT_LEFT)) {

			alt = true;
		}
		else if(event.isKeyUp(KeyEvent.VK_ALT_RIGHT)||event.isKeyUp(KeyEvent.VK_ALT_LEFT)) {

			alt = false;
		}

		if(event.isKeyDown(KeyEvent.VK_ENTER)) {
			enter = true;
		}
		else if(event.isKeyUp(KeyEvent.VK_ENTER)) {
			enter = false;
		}

		if(event.isKeyDown(KeyEvent.VK_ESC)) {
			esc = true;
		}
		else if(event.isKeyUp(KeyEvent.VK_ESC)) {
			esc = false;
		}

		if(alt&&enter) {
			alt = false;
			enter = false;
			if(!isFullScreenEnable()) {
				enableFullScreen = true;
			}
		}

		if(esc) {

			esc = false;
			if(isFullScreenEnable()) {
				disableFullScreen = true;
			}
		}

	}

	private void updateNumpadMouse(KeyEvent event) {

		if(Configuration.getInstance().isNumpadMouse()) {

			int speed = 1;

			//Move Left/Right
			if(event.isKeyDown(KeyEvent.VK_NUMPAD_LEFT_ARROW)) {

				getMouse().setX(getMouse().getX()-speed);
				getMouse().addEvent(new PointerEvent(MouseEvent.MOUSE_NONE, PointerState.MOVE, getMouse().getX(), getMouse().getY()));

			}else if(event.isKeyDown(KeyEvent.VK_NUMPAD_RIGHT_ARROW)) {

				getMouse().setX(getMouse().getX()+speed);
				getMouse().addEvent(new PointerEvent(MouseEvent.MOUSE_NONE, PointerState.MOVE, getMouse().getX(), getMouse().getY()));

			}

			//Move Up/Down
			if(event.isKeyDown(KeyEvent.VK_NUMPAD_UP_ARROW)) {

				getMouse().setX(getMouse().getY()-speed);
				getMouse().addEvent(new PointerEvent(MouseEvent.MOUSE_NONE, PointerState.MOVE, getMouse().getX(), getMouse().getY()));

			}else if(event.isKeyDown(KeyEvent.VK_NUMPAD_DOWN_ARROW)) {

				getMouse().setX(getMouse().getY()+speed);
				getMouse().addEvent(new PointerEvent(MouseEvent.MOUSE_NONE, PointerState.MOVE, getMouse().getX(), getMouse().getY()));

			}

			//Mouse Left Button
			if(event.isKeyDown(KeyEvent.VK_NUMPAD_INS)) {
				getMouse().addEvent(new PointerEvent(MouseEvent.MOUSE_BUTTON_LEFT, PointerState.PRESSED));
			}else if(event.isKeyUp(KeyEvent.VK_NUMPAD_INS)) {
				getMouse().addEvent(new PointerEvent(MouseEvent.MOUSE_BUTTON_LEFT, PointerState.RELEASED));
			}/*else if(event.getKeyTyped(Tecla.VK_NUMPAD_INS)) {
				Gui.getInstance().addEvent(new Event(Tecla.MOUSE_BUTTON_LEFT, KeyState.CLICK));
			}*/

			//Mouse Right Button
			if(event.isKeyDown(KeyEvent.VK_NUMPAD_DEL)) {
				getMouse().addEvent(new PointerEvent(MouseEvent.MOUSE_BUTTON_RIGHT, PointerState.PRESSED));
			}else if(event.isKeyUp(KeyEvent.VK_NUMPAD_DEL)) {
				getMouse().addEvent(new PointerEvent(MouseEvent.MOUSE_BUTTON_RIGHT, PointerState.RELEASED));
			}/*else if(event.getKeyTyped(Tecla.VK_NUMPAD_DEL)) {
				getMouse().addEvent(new PointerEvent(MouseButton.MOUSE_BUTTON_RIGHT, KeyState.CLICK));
			}*/

		}
	}

	private GUIEvent updateFrameEvents(PointerEvent event) {

		if(event.getState() == PointerState.CLICK) {
			return GUIEvent.REQUEST_FOCUS;
		}

		if(event.getState() == PointerState.DRAGGED) {

			if(getMouse().getY() <= TITLE_BAR_HEIGHT) {
				
				return GUIEvent.WINDOW_MOVE;
			}
		}

		return GUIEvent.NONE;
	}

	public void replaceWindow(Window window) {

		if(activeWindow != window) {

			window.setClose(false);

			activeWindow = window;

			//Avoid unnecessary reload
			reload(window.getContext());
		}

	}

	public void setMainApplication(Application application) {
		reload(application);
	}

	public void changeApplication() {
		Context currentApplication = currentContext();
		//Remove Handlers
		removeHandlers(currentApplication);
		currentApplication.dispose();

		Context nextApplication = currentApplication.getNextApplication();
		nextApplication.setDrawCursor(currentApplication.isDrawCursor());

		reload(nextApplication);
	}

	public Context currentContext() {
		return activeWindow.getContext();
	}

	private void reload(Context application) {

		if (application == null) {
			System.err.println(ErrorMessages.APPLICATION_NULL);
			return;
		}

		application.setParent(activeWindow);
		application.setMouseStateListener(arrowDrawer);
		application.setLanguageChangerListener(this);
		addHandlers(application);

		if (application.isLoaded()) {
			Application nextApplication = applicationLoader.reloadApplication(this, application);
			activeWindow.setApplication(nextApplication);
		}
	}

	private void fastReload() {
		locked = true;

		currentContext().getViews().clear();
		currentContext().load();

		needReload = false;

		locked = false;
	}

	@Override
	public void updateKeyEvent(KeyEvent event) {

		Context context = currentContext();
		
		handleApplicationKeyEvents(context, event);

		for (Handler handler : handlers) {
			handler.updateKeyboard(event);
		}

		updateKeyboardEvents(event);

		updateNumpadMouse(event);
	}

	protected void handleApplicationKeyEvents(Context context, KeyEvent event) {
		//Handle Application commands
		context.updateKeyboard(event);
	}

	private void fixEventPosition(PointerEvent event) {
		event.setX(event.getX()-activeWindow.getX());
		event.setY(event.getY()-activeWindow.getY());
	}

	public AWTController getControl() {
		return control;
	}

	public GUIEvent getSuperEvent() {
		return superEvent;
	}

	public int getFps() {
		return fps;
	}

	public void setFps(int fps) {
		this.fps = fps;
		this.activeWindow.getContext().setFps(fps);
	}

	@Override
	public void updateTheme(Theme theme) {
		needReload = true;
	}

	@Override
	public void changeLanguage(Language language) {

		languageHandler.changeLanguage(language);

		for(Handler handler:handlers) {
			handler.updateGuiEvent(GUIEvent.LANGUAGE_CHANGED);
		}
	}
	
	@Override
	public void onLoad(Context context) {		
		activeWindow.setApplication(context);
		context.setLoaded(true);
	}

	private void addHandlers(Context application) {
		for (Handler handler: handlers) {
			handler.init(application);
		}
	}

	private void removeHandlers(Context application) {
		for (Handler handler: handlers) {
			handler.dispose(application);
		}
	}
		
	public List<Monitor> getMonitors() {
		return monitors;
	}

	public boolean isFullScreenEnable() {
		return fullScreenEnable;
	}

	public void setFullScreenEnable(boolean fullScreenEnable) {
		this.fullScreenEnable = fullScreenEnable;
	}

	public Mouse getMouse() {
		return mouse;
	}

	public void setMouse(Mouse mouse) {
		this.mouse = mouse;
	}

	public Keyboard getKeyboard() {
		return keyboard;
	}

	public void setKeyboard(Keyboard keyboard) {
		this.keyboard = keyboard;
	}

}
