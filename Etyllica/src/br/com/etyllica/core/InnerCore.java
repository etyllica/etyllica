package br.com.etyllica.core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import br.com.etyllica.animation.AnimationHandler;
import br.com.etyllica.animation.scripts.AnimationScript;
import br.com.etyllica.animation.scripts.SingleIntervalAnimation;
import br.com.etyllica.context.Application;
import br.com.etyllica.context.Context;
import br.com.etyllica.core.drawer.ArrowDrawer;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.event.PointerState;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.input.HIDController;
import br.com.etyllica.core.input.InputKeyListener;
import br.com.etyllica.core.input.keyboard.Keyboard;
import br.com.etyllica.core.input.mouse.Mouse;
import br.com.etyllica.core.input.mouse.MouseButton;
import br.com.etyllica.core.loader.JoystickLoader;
import br.com.etyllica.debug.Logger;
import br.com.etyllica.effects.GlobalEffect;
import br.com.etyllica.gui.View;
import br.com.etyllica.gui.Window;
import br.com.etyllica.gui.theme.Theme;
import br.com.etyllica.gui.window.MainWindow;
import br.com.etyllica.theme.ThemeManager;
import br.com.etyllica.theme.dalt.DaltArrowTheme;
import br.com.etyllica.theme.mouse.ThemeListener;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class InnerCore implements Core, InputKeyListener, Updatable, ThemeListener {

	//External Windows
	private Window activeWindow = null;

	private List<SingleIntervalAnimation> globalScripts = new ArrayList<SingleIntervalAnimation>();

	private List<Updatable> updatables = new ArrayList<Updatable>();

	private View focus;

	protected HIDController control;

	protected Mouse mouse;

	protected Keyboard keyboard;

	//Mouse Over View
	private View mouseOver = null;

	private List<GUIEvent> guiEvents;

	//private List<KeyEvent> joyEvents;

	private MainWindow mainWindow;

	private boolean drawCursor = true;

	protected boolean fullScreenEnable = false;

	private boolean needReload = false;

	private boolean locked = false;

	private Configuration configuration = Configuration.getInstance();

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

	public InnerCore() {
		super();

		guiEvents = new ArrayList<GUIEvent>();

		control = new HIDController(this);

		mouse = control.getMouse();

		keyboard = control.getKeyboard();

		arrowDrawer = new ArrowDrawer();
		
		initTheme();

		updatables.add(AnimationHandler.getInstance());

	}

	private void initTheme() {

		ThemeManager.getInstance().setThemeListener(this);

		ThemeManager.getInstance().setArrowThemeListener(arrowDrawer);

		ThemeManager.getInstance().setArrowTheme(new DaltArrowTheme());

	}

	public MainWindow getDesktopWindow() {
		return mainWindow;
	}

	public void update(long now) {

		if(!activeWindow.isLoaded()) {
			return;

		} else if (needReload) {

			fastReload();

		}

		if(Configuration.getInstance().isLanguageChanged()) {
			guiEvents.add(GUIEvent.LANGUAGE_CHANGED);
			Configuration.getInstance().setLanguageChanged(false);
		}

		if(Configuration.getInstance().isThemeChanged()) {
			guiEvents.add(GUIEvent.THEME_CHANGED);
			Configuration.getInstance().setThemeChanged(false);
		}

		superEvent = GUIEvent.NONE;

		List<View> components = new CopyOnWriteArrayList<View>(activeWindow.getViews());

		Context application = activeWindow.getApplication();

		components.add(application);

		updateEffects(now);

		updateApplication(application, now);		

		updateActiveWindow(now);

		updateGui(components, guiEvents);

		updateMouse(components);

		//updateKeyboard();

		keyboard.update(now);

		JoystickLoader.getInstance().update(now);

		if(enableFullScreen) {
			enableFullScreen = false;

			superEvent = GUIEvent.ENABLE_FULL_SCREEN;
		}

		if(disableFullScreen) {
			disableFullScreen = false;

			superEvent = GUIEvent.DISABLE_FULL_SCREEN;
		}

	}

	public void updateApplication(Context context, long now) {

		if(!context.isLocked()) {

			if(context.getUpdateInterval() == 0) {

				context.update(now);

				context.setLastUpdate(now);

				context.getScene().update(now);

			}else if(now-context.getLastUpdate() >= context.getUpdateInterval()) {

				context.timeUpdate(now);

				context.setLastUpdate(now);

				context.getScene().update(now);

			}

			//if activeWindow, receive command to change application
			if(context.getReturnApplication()!=context) {

				this.changeApplication();

			}

		}

	}

	private void updateActiveWindow(long now) {

		List<Window> windows = activeWindow.getWindows(); 

		//Creating Windows
		//if application has windows
		if(!windows.isEmpty()) {

			//For each new window in application.windows
			for(Window window : windows) {

				//if this !windows.contains(window)
				addWindow(window);

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

		System.out.println("UpdateJoystick "+event.getKey());

		//activeWindow.getApplication().updateKeyboard(event);
		//updateKeyEvent(event);

		activeWindow.updateKeyboard(event);

		//Application sempre eh gerenciada pelo teclado
		activeWindow.getApplication().updateKeyboard(event);

	}

	private void updateMouse(List<View> components) {

		//Solving ConcurrentModification
		List<PointerEvent> events = new CopyOnWriteArrayList<PointerEvent>(mouse.getEvents());

		//System.out.println(mouseEvents.size());

		//Update components with events
		for(PointerEvent event: events) {

			fixEventPosition(event);

			//Avoid concurrency problems
			//
			//Update components in reverse order
			//Collections.reverse(components);

			for(View component: components) {

				GUIEvent nextEvent = updateMouse(component, event);

				if(nextEvent!=GUIEvent.NONE) {

					if(nextEvent==GUIEvent.NEXT_COMPONENT) {
						//Its necessary in NEXT_COMPONENT Events

						View next = component.findNext();

						if(next!=null) {
							updateEvent(component.findNext(), nextEvent);	
						}

					}else{

						View next = component.findNext();

						if(next!=null) {
							//if overMouse
							updateEvent(component.findNext(), nextEvent);
						}

					}

					break;
				}

			}

			//TODO Melhorar isso para fechar janela com ctrl+F4 ou algo assim

			GUIEvent windowEvent = activeWindow.updateMouse(event);

			if(windowEvent!=GUIEvent.NONE) {
				updateEvent(activeWindow, windowEvent);
			}

			GUIEvent frameEvent = updateFrameEvents(event); 
			if(frameEvent!=GUIEvent.NONE) {
				superEvent = frameEvent;
			}

			//Helper UI Methods
			updateTimerClick();

		}

		mouse.clearEvents();
	}

	private void updateGui(List<View> components, List<GUIEvent> guiEvents) {

		for(GUIEvent event: guiEvents) {

			for(View component: components) {

				updateGuiComponent(component, event);

			}

		}

		guiEvents.clear();
	}

	private void updateGuiComponent(View component, GUIEvent event) {

		component.update(event);

		//Update Childs
		for(View child: component.getViews()) {

			updateGuiComponent(child, event);

		}
	}

	private GUIEvent updateMouse(View component, PointerEvent event) {

		if(!component.isVisible()) {
			return GUIEvent.NONE;
		}

		//Verify onMouse
		if(component.isMouseOver()) {

			if(component != mouseOver) {
				setMouseOver(component);	
			}			

		} else if (component == mouseOver) {

			resetMouseOver();

		}

		//Update Component
		GUIEvent result = component.updateMouse(event);

		if(result!=GUIEvent.NONE&&result!=null) {

			updateEvent(component, result);

			return result;
		}

		//Update Childs
		for(View child: component.getViews()) {

			child.setOffset(component.getX(), component.getY());

			updateMouse(child, event);

			child.setOffset(-component.getX(), -component.getY());

		}

		return GUIEvent.NONE;

	}

	private void updateEvent(View componente, GUIEvent lastEvent) {

		//switch (event.action) {
		switch (lastEvent) {

		case GAIN_FOCUS:

			if(focus!=null) {
				focus.update(GUIEvent.LOST_FOCUS);
			}

			focus = componente;			

			break;

		case LOST_FOCUS:

			if(componente==focus) {
				//TODO Mouse.loseFocus()
				//events.add(new Event(Tecla.NONE, KeyState.LOSE_FOCUS));
				//events.add(new Event(DeviceType.KEYBOARD, Tecla.NONE, KeyState.LOSE_FOCUS));

				//TODO improve it
				focus = null;
			}

			break;

			/*case MOUSE_OVER:
			if(!mouseOver) {
				mouseOver = true;
				mouseOverClickable = true;
				//TODO componente.setMouseOver(true);
			}

			break;*/

			/*case MOUSE_OVER_UNCLICKABLE:
			if(!mouseOver) {
				mouseOver = true;
				mouseOverClickable = false;
			}			
			break;*/

		case MOUSE_OVER_WITH_FOCUS:

			//lastOver = componente;
			break;

		case NEXT_COMPONENT:

			Logger.log("LostFocus");

			//controle.getTeclado().loseFocus();
			//events.add(new Event(DeviceType.KEYBOARD, Tecla.NONE, KeyState.))

			componente.update(GUIEvent.LOST_FOCUS);

			break;

		case WINDOW_CLOSE:

			//TODO
			//((Window)componente.setClose(true));

			break;

			/*case ONCE:
			//this.event (param)
			event.setState(KeyState.PRESSED);

			//Prevent consume
			events.add(event);
			break;
			 */

		case UPDATE_MOUSE:
			updateMouse(componente, new PointerEvent(MouseButton.MOUSE_NONE, PointerState.MOVE, mouse.getX(), mouse.getY()));
			break;

		case APPLICATION_CHANGED:
			this.changeApplication();
			break;

		default:

			if(componente.isMouseOver()) {
				componente.update(GUIEvent.MOUSE_OUT);
			}

			break;
		}

		componente.setLastEvent(lastEvent);

		componente.update(lastEvent);

		componente.executeAction(lastEvent);

	}

	public void draw(Graphic g) {

		if(locked||needReload)
			return;

		drawWindow(g, activeWindow);

		drawGlobalEffects(g);

		if(drawCursor) {
			arrowDrawer.setCoordinates(mouse.getX(), mouse.getY());
			arrowDrawer.draw(g);
		}

	}

	private void drawWindow(Graphic g, Window window) {

		boolean offset = window.getX()!=0||window.getY()!=0;

		if(offset) {
			g.translate(window.getX(), window.getY());
		}

		window.draw(g);

		drawContext(window.getApplication(), g);


		List<View> components = new CopyOnWriteArrayList<View>(window.getViews());

		for(View view: components) {

			if(view!=null) {
				drawView(view, g);
			}else{
				System.out.println(this.getClass().getSimpleName()+" - Draw Null Component");
			}

		}

		if(offset) {
			g.translate(-window.getX(), -window.getY());
		}

	}

	private void drawContext(Context context, Graphic g) {

		context.drawScene(g);

		for(View view: context.getViews()) {
			drawView(view, g);
		}

	}

	private void updateEffects(long now) {

		for(Updatable updatable: updatables) {

			updatable.update(now);	
		}

	}

	private void drawGlobalEffects(Graphic g) {

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

	//TODO Some kind of Subimage to textfields for example
	private void drawView(View component, Graphic g) {

		//Draw Component
		component.draw(g);

		if(!component.getViews().isEmpty()) {

			List<View> components = new CopyOnWriteArrayList<View>(component.getViews());

			for(View child: components) {
				child.setOffset(component.getX(), component.getY());
				//g.setBimg(g.getBimg().getSubimage(child.getX(), child.getY(), child.getW(), child.getH()));
				drawView(child,g);
				
				child.setOffset(-component.getX(), -component.getY());
			}

		}

	}

	public void translateComponents(int x, int y) {
		for(View component: activeWindow.getViews()) {
			translateComponent(x, y, component);
		}
	}

	private void translateComponent(int x, int y, View component) {

		component.setOffset(x, y);

		for(View child: component.getViews()) {
			translateComponent(x, y, child);
		}

	}

	public boolean isMouseOver() {
		return mouseOver!=null;
	}

	public void addEffect(GlobalEffect effect) {

		AnimationHandler.getInstance().add(effect.getScript());
		globalScripts.add(effect.getScript());

		//TODO add animation
		//globalEffects.add(effect);

	}

	private void updateKeyboardEvents(KeyEvent event) {

		if(event.isKeyDown(KeyEvent.TSK_ALT_DIREITA)||event.isKeyDown(KeyEvent.TSK_ALT_ESQUERDA)) {

			alt = true;
		}
		else if(event.isKeyUp(KeyEvent.TSK_ALT_DIREITA)||event.isKeyUp(KeyEvent.TSK_ALT_ESQUERDA)) {

			alt = false;
		}

		if(event.isKeyDown(KeyEvent.TSK_ENTER)) {
			enter = true;
		}
		else if(event.isKeyUp(KeyEvent.TSK_ENTER)) {
			enter = false;
		}

		if(event.isKeyDown(KeyEvent.TSK_ESC)) {
			esc = true;
		}
		else if(event.isKeyUp(KeyEvent.TSK_ESC)) {
			esc = false;
		}

		if(alt&&enter) {
			alt = false;
			enter = false;
			if(!fullScreenEnable) {
				enableFullScreen = true;
			}
		}

		if(esc) {

			esc = false;
			if(fullScreenEnable) {
				disableFullScreen = true;
			}
		}

	}

	private void updateNumpadMouse(KeyEvent event) {

		if(Configuration.getInstance().isNumpadMouse()) {

			int velocidade = 1;

			//Move Left/Right
			if(event.isKeyDown(KeyEvent.TSK_NUMPAD_SETA_ESQUERDA)) {

				mouse.setX(mouse.getX()-velocidade);
				mouse.addEvent(new PointerEvent(MouseButton.MOUSE_NONE, PointerState.MOVE, mouse.getX(), mouse.getY()));

			}else if(event.isKeyDown(KeyEvent.TSK_NUMPAD_SETA_DIREITA)) {

				mouse.setX(mouse.getX()+velocidade);
				mouse.addEvent(new PointerEvent(MouseButton.MOUSE_NONE, PointerState.MOVE, mouse.getX(), mouse.getY()));

			}

			//Move Up/Down
			if(event.isKeyDown(KeyEvent.TSK_NUMPAD_SETA_CIMA)) {

				mouse.setX(mouse.getY()-velocidade);
				mouse.addEvent(new PointerEvent(MouseButton.MOUSE_NONE, PointerState.MOVE, mouse.getX(), mouse.getY()));

			}else if(event.isKeyDown(KeyEvent.TSK_NUMPAD_SETA_BAIXO)) {

				mouse.setX(mouse.getY()+velocidade);
				mouse.addEvent(new PointerEvent(MouseButton.MOUSE_NONE, PointerState.MOVE, mouse.getX(), mouse.getY()));

			}

			//Mouse Left Button
			if(event.isKeyDown(KeyEvent.TSK_NUMPAD_INS)) {
				mouse.getEvents().add(new PointerEvent(MouseButton.MOUSE_BUTTON_LEFT, PointerState.PRESSED));
			}else if(event.isKeyUp(KeyEvent.TSK_NUMPAD_INS)) {
				mouse.getEvents().add(new PointerEvent(MouseButton.MOUSE_BUTTON_LEFT, PointerState.RELEASED));
			}/*else if(event.getKeyTyped(Tecla.TSK_NUMPAD_INS)) {
				Gui.getInstance().addEvent(new Event(Tecla.MOUSE_BUTTON_LEFT, KeyState.CLICK));
			}*/

			//Mouse Right Button
			if(event.isKeyDown(KeyEvent.TSK_NUMPAD_DEL)) {
				mouse.getEvents().add(new PointerEvent(MouseButton.MOUSE_BUTTON_RIGHT, PointerState.PRESSED));
			}else if(event.isKeyUp(KeyEvent.TSK_NUMPAD_DEL)) {
				mouse.getEvents().add(new PointerEvent(MouseButton.MOUSE_BUTTON_RIGHT, PointerState.RELEASED));
			}/*else if(event.getKeyTyped(Tecla.TSK_NUMPAD_DEL)) {
				Gui.getInstance().addEvent(new Event(Tecla.MOUSE_BUTTON_RIGHT, KeyState.CLICK));
			}*/

		}
	}

	private GUIEvent updateFrameEvents(PointerEvent event) {

		if(event.getState()==PointerState.CLICK) {
			return GUIEvent.REQUEST_FOCUS;
		}

		if(event.getState()==PointerState.DRAGGED) {

			if(mouse.getY()<=50) {
				/*Logger.log("Evento Mouse dragged");

				Logger.log("Mx = "+mouse.getDragX());
				Logger.log("My = "+mouse.getDragY());
				Logger.log("Dx = "+mouse.getDragX());
				Logger.log("Dy = "+mouse.getDragY());*/
				return GUIEvent.WINDOW_MOVE;
			}


			/*if(event.getMouseButtonDragged(Tecla.MOUSE_BUTTON_LEFT)) {

				if(mouse.getY()<20)
				Logger.log("Evento Move Janela");

				return GUIEvent.WINDOW_MOVE;
			}*/
		}

		return GUIEvent.NONE;
	}

	public void addWindow(Window window) {

		//Change to wID system or
		//Set<Windows>...
		if(activeWindow!=window) {

			window.setClose(false);

			//windows.add(window);

			activeWindow = window;

			//Avoid unnecessary reload
			reload(window.getApplication());

		}

	}

	public void setMainApplication(Application application) {

		reload(application);		

	}

	protected void changeApplication() {

		//Lock old application
		//Context application = activeWindow.getApplication();

		reload(activeWindow.getApplication().getReturnApplication());

	}

	private void reload(Context application) {

		if(application==null) {
			System.err.println("Application cannot be null.");
		}

		activeWindow.reload(application);

		application.setSessionMap(activeWindow.getSessionMap());
		
		application.setCamera(activeWindow.getCamera());
		
		application.setMouseStateListener(arrowDrawer);

	}

	private void fastReload() {

		locked = true;

		activeWindow.getApplication().clearComponents();

		activeWindow.getApplication().load();

		needReload = false;

		locked = false;
	}

	private void updateTimerClick() {

		int speed = 3;

		if(mouseOver!=null) {

			if(configuration.isTimerClick()) {

				//TODO Ativar timer do mouse que incrementa sozinho
				int arc = arrowDrawer.getArc();
				if(arc<360) {
					arrowDrawer.setArc(arc+speed);
				}else{

					updateEvent(mouseOver, GUIEvent.MOUSE_LEFT_BUTTON_DOWN);
					updateEvent(mouseOver, GUIEvent.MOUSE_LEFT_BUTTON_UP);

					resetMouseOver();

				}
			}

		}else{

			if(configuration.isTimerClick()) {
				arrowDrawer.setArc(0);
			}

		}
	}

	@Override
	public void updateKeyEvent(KeyEvent event) {

		activeWindow.updateKeyboard(event);

		//Application sempre eh gerenciada pelo teclado
		activeWindow.getApplication().updateKeyboard(event);

		//Apenas o componente quem tem foco eh gerenciado pelo teclado
		if(focus!=null) {

			GUIEvent focusEvent = focus.updateKeyboard(event);

			if(focusEvent!=GUIEvent.NONE&&focusEvent!=null) {
				//TODO Update NExtComponent
				Logger.log(focusEvent);

				View next = focus.findNext();

				if(next!=null) {

					if(focusEvent==GUIEvent.NEXT_COMPONENT) {

						updateEvent(focus, focusEvent);
						updateEvent(next, GUIEvent.GAIN_FOCUS);

					}else{

						updateEvent(next, focusEvent);

					}

				}
			}
		}

		updateKeyboardEvents(event);

		updateNumpadMouse(event);
	}

	public void fixEventPosition(PointerEvent event) {

		event.setX(event.getX()-activeWindow.getX());
		event.setY(event.getY()-activeWindow.getY());

	}

	private void setMouseOver(View component) {

		mouseOver = component;

		arrowDrawer.setOverClickable(true);		
	}

	private void resetMouseOver() {

		mouseOver = null;

		arrowDrawer.setOverClickable(false);
	}

	public HIDController getControl() {
		return control;
	}

	public GUIEvent getSuperEvent() {
		return superEvent;
	}

	public void hideCursor() {
		drawCursor = false;
	}

	public void showCursor() {
		drawCursor = true;		
	}

	public int getFps() {
		return fps;
	}

	public void setFps(int fps) {
		this.fps = fps;
		this.activeWindow.getApplication().setFps(fps);
	}

	@Override
	public void updateTheme(Theme theme) {

		needReload = true;
	}

}
