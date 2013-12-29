package br.com.etyllica.core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import br.com.etyllica.animation.AnimationHandler;
import br.com.etyllica.animation.AnimationScript;
import br.com.etyllica.animation.Updatable;
import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.application.Context;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.event.PointerState;
import br.com.etyllica.core.input.HIDController;
import br.com.etyllica.core.input.InputListener;
import br.com.etyllica.core.input.keyboard.Keyboard;
import br.com.etyllica.core.input.mouse.Mouse;
import br.com.etyllica.core.input.mouse.MouseButton;
import br.com.etyllica.core.loader.JoystickLoader;
import br.com.etyllica.core.video.Graphic;
import br.com.etyllica.debug.Logger;
import br.com.etyllica.effects.GlobalEffect;
import br.com.etyllica.gui.View;
import br.com.etyllica.gui.Window;
import br.com.etyllica.gui.window.MainWindow;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class EngineCore implements Core, InputListener, Updatable{

	//External Windows
	private Window activeWindow = null;

	private List<AnimationScript> globalScripts = new ArrayList<AnimationScript>();

	private List<Updatable> updatables = new ArrayList<Updatable>();

	private AnimationHandler animation = new AnimationHandler();

	private View focus;

	protected HIDController control;

	protected Mouse mouse;

	protected Keyboard keyboard;

	//Mouse Over Something
	//Usado para acessibilidade talvez
	private boolean mouseOver = false;
	private boolean mouseOverClickable = false;

	private List<GUIEvent> guiEvents;

	//private List<KeyEvent> joyEvents;

	private MainWindow mainWindow;

	protected boolean drawCursor = false;

	private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

	private final int ANIMATION_DELAY = 20;

	protected Animator animator = new Animator();

	protected boolean fullScreenEnable = false;

	public EngineCore(){
		super();

		guiEvents = new ArrayList<GUIEvent>();

		control = new HIDController(this);

		mouse = control.getMouse();

		keyboard = control.getKeyboard();

		updatables.add(animation);

	}

	public MainWindow getDesktopWindow() {
		return mainWindow;
	}

	private boolean enableFullScreen = false;
	private boolean disableFullScreen = false;

	private boolean alt = false;
	private boolean enter = false;
	private boolean esc = false;

	private GUIEvent superEvent = GUIEvent.NONE;

	public void update(long now){

		if(Configuration.getInstance().isLanguageChanged()){
			guiEvents.add(GUIEvent.LANGUAGE_CHANGED);
			Configuration.getInstance().setLanguageChanged(false);
		}

		if(Configuration.getInstance().isThemeChanged()){
			guiEvents.add(GUIEvent.THEME_CHANGED);
			Configuration.getInstance().setThemeChanged(false);
		}

		superEvent = GUIEvent.NONE;

		List<View> components = new CopyOnWriteArrayList<View>(activeWindow.getViews());
		
		Context application = activeWindow.getApplication();
		
		components.add(application);

		application.update(getTimeNow());

		updateActiveWindow();

		//updateApplication();

		updateGui(components);

		updateMouse(components);

		//updateKeyboard();

		keyboard.update(now);

		JoystickLoader.getInstance().update(now);

		if(enableFullScreen){
			enableFullScreen = false;

			superEvent = GUIEvent.ENABLE_FULL_SCREEN;
		}

		if(disableFullScreen){
			disableFullScreen = false;

			superEvent = GUIEvent.DISABLE_FULL_SCREEN;
		}

	}

	public void updateApplication(){

		Context application = activeWindow.getApplication();

		if(application!=null){

			long now = getTimeNow();
			
			if(application.getUpdateInterval()>0){

				if(now-application.getLastUpdate()>application.getUpdateInterval()){
					application.timeUpdate(now);
					application.setLastUpdate(now);
				}

			}

			//Animate
			//TODO Independent Thread
			if(!application.isLocked()){

				application.getScene().update(getTimeNow());

				//if activeWindow, receive command to change application
				if(application.getReturnApplication()!=application){

					this.changeApplication();

				}

			}

		}

	}

	private void updateActiveWindow(){

		List<Window> windows = activeWindow.getWindows(); 

		//Creating Windows
		//if application has windows
		if(!windows.isEmpty()){

			//For each new window in application.windows
			for(Window window : windows){

				//if this !windows.contains(window)
				addWindow(window);

			}

			activeWindow.getWindows().clear();
		}

		/*if(activeWindow.isClose()){

			if(windows.size()>0){
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

		activeWindow.getApplication().updateKeyboard(event);

	}

	private void updateMouse(List<View> components){

		mouseOver = false;
		mouseOverClickable = false;

		//Solving ConcurrentModification
		List<PointerEvent> events = new CopyOnWriteArrayList<PointerEvent>(mouse.getEvents());

		//System.out.println(mouseEvents.size());

		//Update components with events
		for(PointerEvent event: events){

			updateMouseEvent(event);

			//Avoid concurrency problems
			//
			//Update components in reverse order
			//Collections.reverse(components);

			for(View component: components){

				GUIEvent nextEvent = updateMouse(component, event);

				if(nextEvent!=GUIEvent.NONE){

					if(nextEvent==GUIEvent.NEXT_COMPONENT){
						//Its necessary in NEXT_COMPONENT Events

						View next = component.findNext();

						if(next!=null){
							gerenciaEvento(component.findNext(), nextEvent);	
						}

					}else{
						gerenciaEvento(component.findNext(), nextEvent);
					}

					break;
				}

				//TODO Coisas de acessibilidade
				//gerenciaTimerClick();

			}

			//TODO Melhorar isso para fechar janela com ctrl+F4 ou algo assim

			GUIEvent windowEvent = activeWindow.updateMouse(event);

			if(windowEvent!=GUIEvent.NONE){
				gerenciaEvento(activeWindow, windowEvent);
			}

			GUIEvent frameEvent = updateFrameEvents(event); 
			if(frameEvent!=GUIEvent.NONE){
				superEvent = frameEvent;
			}

		}

		mouse.clearEvents();
	}

	private void updateGui(List<View> components){

		for(GUIEvent event: guiEvents){

			for(View component: components){

				updateGuiComponent(component, event);

			}

		}

		guiEvents.clear();
	}

	private void updateGuiComponent(View component, GUIEvent event){

		component.update(event);

		//Update Childs
		for(View child: component.getViews()){

			updateGuiComponent(child, event);

		}
	}

	private GUIEvent updateMouse(View component, PointerEvent event){

		//If componente is visible
		if(component.isVisible()){

			//Verify onMouse
			if(component.onMouse(event)){

				if(!component.isMouseOver()){
					component.setMouseOver(true);
				}

			}else{

				if(component.isMouseOver()){
					component.setMouseOver(false);
				}

			}

			//Update Component
			GUIEvent result = component.updateMouse(event);

			if(result!=GUIEvent.NONE&&result!=null){

				gerenciaEvento(component, result);

				return result;
			}

			//Update Childs
			for(View child: component.getViews()){

				child.setOffset(component.getX(), component.getY());

				updateMouse(child, event);

				child.setOffset(-component.getX(), -component.getY());

			}

		}

		return GUIEvent.NONE;

	}

	private void gerenciaEvento(View componente, GUIEvent lastEvent){

		//switch (event.action) {
		switch (lastEvent) {

		case GAIN_FOCUS:

			if(focus!=null){
				focus.update(GUIEvent.LOST_FOCUS);
			}

			focus = componente;			

			break;

		case LOST_FOCUS:

			if(componente==focus){
				//TODO Mouse.loseFocus()
				//events.add(new Event(Tecla.NONE, KeyState.LOSE_FOCUS));
				//events.add(new Event(DeviceType.KEYBOARD, Tecla.NONE, KeyState.LOSE_FOCUS));

				//TODO improve it
				focus = null;
			}

			break;

		case MOUSE_OVER:
			if(!mouseOver){
				mouseOver = true;
				mouseOverClickable = true;
				//TODO componente.setMouseOver(true);
			}

			break;

			/*case MOUSE_OVER_UNCLICKABLE:
			if(!mouseOver){
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

			if(componente.isMouseOver()){
				componente.update(GUIEvent.MOUSE_OUT);
			}

			break;
		}

		componente.setLastEvent(lastEvent);

		componente.update(lastEvent);

		componente.executeAction(lastEvent);

	}

	public void draw(Graphic g){

		/*List<Window> drawWindows = new CopyOnWriteArrayList<Window>(windows);

		for(Window window : drawWindows){
			drawWindow(g, window);
		}*/

		drawWindow(g, activeWindow);

		drawEffects(g);

		if(drawCursor){
			mouse.draw(g);
		}

	}

	private void drawWindow(Graphic g, Window window){

		boolean offset = window.getX()!=0||window.getY()!=0;

		if(offset){
			g.translate(window.getX(), window.getY());
		}

		window.draw(g);

		drawContext(window.getApplication(), g);		

		List<View> components = window.getViews();

		for(View view: components){

			if(view!=null){
				drawView(view, g);
			}else{
				System.out.println("Draw Null Component");
			}

		}

		if(offset){
			g.translate(-window.getX(), -window.getY());
		}

	}

	private void drawContext(Context context, Graphic g){

		context.drawScene(g);

		for(View view: context.getViews()){
			drawView(view, g);
		}

	}

	private void drawEffects(Graphic g){

		for(Updatable updatable: updatables){
			updatable.update(getTimeNow());	
		}		

		List<AnimationScript> remove = new ArrayList<AnimationScript>();

		for(AnimationScript script: globalScripts){

			if(!script.isStopped()){
				script.getTarget().draw(g);
			}else{
				remove.add(script);
			}

		}

		for(AnimationScript script: remove){
			globalScripts.remove(script);
		}

	}

	//TODO Some kind of Subimage to textfields for example
	private void drawView(View component, Graphic g){

		if(component.isVisible()){

			//Draw Component
			component.draw(g);

			List<View> components = new CopyOnWriteArrayList<View>(component.getViews());

			for(View child: components){
				child.setOffset(component.getX(), component.getY());
				//g.setBimg(g.getBimg().getSubimage(child.getX(), child.getY(), child.getW(), child.getH()));
				drawView(child,g);
				child.setOffset(-component.getX(), -component.getY());
			}

		}


	}

	public long getTimeNow(){
		return System.currentTimeMillis();
	}

	public void translateComponents(int x, int y){
		for(View component: activeWindow.getViews()){
			translateComponent(x, y, component);
		}
	}

	private void translateComponent(int x, int y, View component){

		component.setOffset(x, y);

		for(View child: component.getViews()){
			translateComponent(x, y, child);
		}

	}

	public boolean isMouseOver() {
		return mouseOver;
	}

	public boolean isMouseOverClickable() {
		return mouseOverClickable;
	}

	public void setMouseOverClickable(boolean mouseOverClickable) {
		this.mouseOverClickable = mouseOverClickable;
	}

	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}

	/*public List<PointerEvent> getEvents(){
		return mouseEvents;
	}

	public void addEvent(PointerEvent event){
		mouseEvents.add(event);		
	}*/

	public void addEffect(GlobalEffect effect){

		animation.add(effect.getScript());
		globalScripts.add(effect.getScript());

		//TODO add animation
		//globalEffects.add(effect);

	}

	private void updateKeyboardEvents(KeyEvent event){

		if(event.isKeyDown(KeyEvent.TSK_ALT_DIREITA)||event.isKeyDown(KeyEvent.TSK_ALT_ESQUERDA)){

			alt = true;
		}
		else if(event.isKeyUp(KeyEvent.TSK_ALT_DIREITA)||event.isKeyUp(KeyEvent.TSK_ALT_ESQUERDA)){

			alt = false;
		}

		if(event.isKeyDown(KeyEvent.TSK_ENTER)){
			enter = true;
		}
		else if(event.isKeyUp(KeyEvent.TSK_ENTER)){
			enter = false;
		}

		if(event.isKeyDown(KeyEvent.TSK_ESC)){
			esc = true;
		}
		else if(event.isKeyUp(KeyEvent.TSK_ESC)){
			esc = false;
		}

		if(alt&&enter){
			alt = false;
			enter = false;
			if(!fullScreenEnable){
				enableFullScreen = true;
			}
		}

		if(esc){

			esc = false;

			if(fullScreenEnable){
				disableFullScreen = true;
			}
		}

	}

	private void updateNumpadMouse(KeyEvent event){

		if(Configuration.getInstance().isNumpadMouse()){

			int velocidade = 1;

			//Move Left/Right
			if(event.isKeyDown(KeyEvent.TSK_NUMPAD_SETA_ESQUERDA)){

				mouse.setX(mouse.getX()-velocidade);
				mouse.addEvent(new PointerEvent(MouseButton.MOUSE_NONE, PointerState.MOVE, mouse.getX(), mouse.getY()));

			}else if(event.isKeyDown(KeyEvent.TSK_NUMPAD_SETA_DIREITA)){

				mouse.setX(mouse.getX()+velocidade);
				mouse.addEvent(new PointerEvent(MouseButton.MOUSE_NONE, PointerState.MOVE, mouse.getX(), mouse.getY()));

			}

			//Move Up/Down
			if(event.isKeyDown(KeyEvent.TSK_NUMPAD_SETA_CIMA)){

				mouse.setX(mouse.getY()-velocidade);
				mouse.addEvent(new PointerEvent(MouseButton.MOUSE_NONE, PointerState.MOVE, mouse.getX(), mouse.getY()));

			}else if(event.isKeyDown(KeyEvent.TSK_NUMPAD_SETA_BAIXO)){

				mouse.setX(mouse.getY()+velocidade);
				mouse.addEvent(new PointerEvent(MouseButton.MOUSE_NONE, PointerState.MOVE, mouse.getX(), mouse.getY()));

			}

			//Mouse Left Button
			if(event.isKeyDown(KeyEvent.TSK_NUMPAD_INS)){
				mouse.getEvents().add(new PointerEvent(MouseButton.MOUSE_BUTTON_LEFT, PointerState.PRESSED));
			}else if(event.isKeyUp(KeyEvent.TSK_NUMPAD_INS)){
				mouse.getEvents().add(new PointerEvent(MouseButton.MOUSE_BUTTON_LEFT, PointerState.RELEASED));
			}/*else if(event.getKeyTyped(Tecla.TSK_NUMPAD_INS)){
				Gui.getInstance().addEvent(new Event(Tecla.MOUSE_BUTTON_LEFT, KeyState.CLICK));
			}*/

			//Mouse Right Button
			if(event.isKeyDown(KeyEvent.TSK_NUMPAD_DEL)){
				mouse.getEvents().add(new PointerEvent(MouseButton.MOUSE_BUTTON_RIGHT, PointerState.PRESSED));
			}else if(event.isKeyUp(KeyEvent.TSK_NUMPAD_DEL)){
				mouse.getEvents().add(new PointerEvent(MouseButton.MOUSE_BUTTON_RIGHT, PointerState.RELEASED));
			}/*else if(event.getKeyTyped(Tecla.TSK_NUMPAD_DEL)){
				Gui.getInstance().addEvent(new Event(Tecla.MOUSE_BUTTON_RIGHT, KeyState.CLICK));
			}*/

		}
	}

	private GUIEvent updateFrameEvents(PointerEvent event){

		if(event.getState()==PointerState.CLICK){
			return GUIEvent.REQUEST_FOCUS;
		}

		if(event.getState()==PointerState.DRAGGED){

			if(mouse.getY()<=50){
				/*Logger.log("Evento Mouse dragged");

				Logger.log("Mx = "+mouse.getDragX());
				Logger.log("My = "+mouse.getDragY());
				Logger.log("Dx = "+mouse.getDragX());
				Logger.log("Dy = "+mouse.getDragY());*/
				return GUIEvent.WINDOW_MOVE;
			}


			/*if(event.getMouseButtonDragged(Tecla.MOUSE_BUTTON_LEFT)){

				if(mouse.getY()<20)
				Logger.log("Evento Move Janela");

				return GUIEvent.WINDOW_MOVE;
			}*/
		}

		return GUIEvent.NONE;
	}

	public void addWindow(Window window){

		//Change to wID system or
		//Set<Windows>...
		if(activeWindow!=window){

			window.setClose(false);

			//windows.add(window);

			activeWindow = window;

			//Avoid unnecessary reload
			reload(window.getApplication());

		}

	}

	public void setMainApplication(Application application){

		reload(application);		

	}

	protected void changeApplication(){

		//Lock old application
		Context application = activeWindow.getApplication();

		application.setSessionMap(activeWindow.getSessionMap());

		reload(activeWindow.getApplication().getReturnApplication());

	}

	private void reload(Context application){

		activeWindow.reload(application);

	}

	private boolean click = false;

	private void updateTimerClick(){

		Configuration config = Configuration.getInstance();

		int speed = 3;

		if(mouseOverClickable){

			if(config.isTimerClick()){

				//TODO Ativar timer do mouse que incrementa sozinho
				int arc = control.getMouse().getArc();
				if(arc<360){
					mouse.setArc(arc+speed);
				}else{
					//TODO if timerMouse
					mouse.addEvent(new PointerEvent(MouseButton.MOUSE_BUTTON_LEFT, PointerState.CLICK));
					//Simula Click
					click = true;
				}
			}

		}else{
			if(config.isTimerClick()){
				mouse.setArc(0);
			}
		}				
	}

	public HIDController getControl(){
		return control;
	}

	public GUIEvent getSuperEvent(){
		return superEvent;
	}

	public void hideCursor() {
		drawCursor = false;
	}

	public void showCursor() {
		drawCursor = true;		
	}


	protected class Animator implements Runnable{

		protected void startAnimation(){
			executor.scheduleWithFixedDelay(new Animator(), ANIMATION_DELAY, ANIMATION_DELAY, TimeUnit.MILLISECONDS);
		}

		public void run() { 
			updateApplication();
		}

	}

	@Override
	public void updateKeyEvent(KeyEvent event) {

		activeWindow.updateKeyboard(event);

		//Application sempre eh gerenciada pelo teclado
		activeWindow.getApplication().updateKeyboard(event);

		//TODO Same as UpdateMouse
		//List<GUIComponent> components = new CopyOnWriteArrayList<GUIComponent>(activeWindow.getComponents());
		//Collections.reverse(components);

		//Apenas o componente quem tem foco eh gerenciado pelo teclado
		if(focus!=null){

			GUIEvent focusEvent = focus.updateKeyboard(event);

			if(focusEvent!=GUIEvent.NONE&&focusEvent!=null){
				//TODO Update NExtComponent
				Logger.log(focusEvent);

				View next = focus.findNext();

				if(next!=null){

					if(focusEvent==GUIEvent.NEXT_COMPONENT){

						gerenciaEvento(focus, focusEvent);
						gerenciaEvento(next, GUIEvent.GAIN_FOCUS);

					}else{

						gerenciaEvento(next, focusEvent);

					}

				}
			}
		}

		updateKeyboardEvents(event);

		updateNumpadMouse(event);
	}

	@Override
	public void updateMouseEvent(PointerEvent event) {

		event.setX(event.getX()-activeWindow.getX());
		event.setY(event.getY()-activeWindow.getY());

	}

}
