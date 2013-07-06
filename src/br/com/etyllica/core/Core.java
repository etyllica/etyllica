package br.com.etyllica.core;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.application.InternalApplication;
import br.com.etyllica.core.control.HIDController;
import br.com.etyllica.core.control.keyboard.Keyboard;
import br.com.etyllica.core.control.mouse.Mouse;
import br.com.etyllica.core.control.mouse.MouseButton;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyState;
import br.com.etyllica.core.event.KeyboardEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.event.Tecla;
import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.effects.GlobalEffect;
import br.com.etyllica.gui.GUIComponent;
import br.com.etyllica.gui.Window;
import br.com.etyllica.gui.window.DesktopWindow;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Core {

	//External Windows
	private Window activeWindow = null;

//	private SessionMap sessionMap = new SessionMap();

	private List<Window> windows = new ArrayList<Window>();

	private List<GlobalEffect> globalEffects = new ArrayList<GlobalEffect>();

	private GUIComponent focus;

	protected HIDController control;

	protected Mouse mouse;

	protected Keyboard keyboard;

	//Mouse Over Something
	//Usado para acessibilidade talvez
	private boolean mouseOver = false;
	private boolean mouseOverClickable = false;

	private List<GUIEvent> guiEvents;

	private List<PointerEvent> mouseEvents;

	private List<KeyboardEvent> keyEvents;

	//private List<JoystickEvent> joyEvents;

	private DesktopWindow desktopWindow;

	public Core(){
		super();

		guiEvents = new ArrayList<GUIEvent>();

		control = new HIDController();

		mouse = control.getMouse();
		mouseEvents = mouse.getEvents();

		keyboard = control.getTeclado();

		keyEvents = keyboard.getEvents();

	}

	public DesktopWindow getDesktopWindow() {
		return desktopWindow;
	}

	private boolean enableFullScreen = false;
	private boolean disableFullScreen = false;

	private boolean alt = false;
	private boolean enter = false;
	private boolean esc = false;

	private GUIEvent superEvent = GUIEvent.NONE;

	public void gerencia(){

		if(Configuration.getInstance().isLanguageChanged()){
			guiEvents.add(GUIEvent.LANGUAGE_CHANGED);
			Configuration.getInstance().setLanguageChanged(false);
		}

		superEvent = GUIEvent.NONE;

		List<GUIComponent> components = new CopyOnWriteArrayList<GUIComponent>(activeWindow.getComponents());

		updateActiveWindow();

		updateApplication();

		updateGui(components);

		updateMouse(components);

		updateKeyboard();


		if(enableFullScreen){
			enableFullScreen = false;

			superEvent = GUIEvent.ENABLE_FULL_SCREEN;
		}

		if(disableFullScreen){
			disableFullScreen = false;

			superEvent = GUIEvent.DISABLE_FULL_SCREEN;
		}

	}

	private void updateActiveWindow(){

		if(activeWindow.isClose()){

			if(windows.size()>0){
				windows.remove(activeWindow);
				activeWindow = windows.get(windows.size()-1);
			}else{
				System.exit(0);
			}

		}
	}

	private void updateApplication(){

		Application application = activeWindow.getApplication();

		if(application!=null){
			//if activeWindow, receive command to change application
			if(application.getReturnApplication()!=application){
				this.changeApplication();
			}

			//Creating Windows
			//if application has windows
			if(!application.getWindows().isEmpty()){

				//For each new window in application.windows
				for(Window window:application.getWindows()){

					//if this !windows.contains(window)
					addWindow(window);


				}

				application.getWindows().clear();
			}

		}

	}

	private void updateKeyboard(){

		keyboard.poll();

		List<KeyboardEvent> keyboardEvents = new CopyOnWriteArrayList<KeyboardEvent>(keyEvents);

		for(KeyboardEvent keyboardEvent: keyboardEvents){

			activeWindow.updateKeyboard(keyboardEvent);

			//Application sempre eh gerenciada pelo teclado
			activeWindow.getApplication().updateKeyboard(keyboardEvent);

			//TODO Same as UpdateMouse
			//List<GUIComponent> components = new CopyOnWriteArrayList<GUIComponent>(activeWindow.getComponents());
			//Collections.reverse(components);

			//Apenas o componente quem tem foco eh gerenciado pelo teclado
			if(focus!=null){

				GUIEvent focusEvent = focus.updateKeyboard(keyboardEvent);

				if(focusEvent!=GUIEvent.NONE){
					//TODO Update NExtComponent
					System.out.println(focusEvent);

					GUIComponent next = focus.findNext();

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

			updateKeyboardEvents(keyboardEvent);

			updateNumpadMouse(keyboardEvent);
		}

		keyEvents.clear();
	}

	private void updateMouse(List<GUIComponent> components){

		mouseOver = false;
		mouseOverClickable = false;

		//Solving ConcurrentModification
		List<PointerEvent> events = new CopyOnWriteArrayList<PointerEvent>(mouseEvents);

		//Update components with events
		for(PointerEvent event: events){

			event.setX(event.getX()-activeWindow.getX());
			event.setY(event.getY()-activeWindow.getY());

			//Avoid concurrency problems
			//
			//Update components in reverse order
			//Collections.reverse(components);

			for(GUIComponent component: components){

				GUIEvent nextEvent = updateComponent(component, event);

				if(nextEvent!=GUIEvent.NONE){

					if(nextEvent==GUIEvent.NEXT_COMPONENT){
						//Its necessary in NEXT_COMPONENT Events

						GUIComponent next = component.findNext();

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

		mouseEvents.clear();
	}

	private void updateGui(List<GUIComponent> components){

		for(GUIEvent event: guiEvents){

			for(GUIComponent component: components){

				updateGuiComponent(component, event);

			}
			
		}

		guiEvents.clear();
	}
	
	private void updateGuiComponent(GUIComponent component, GUIEvent event){
		
		component.update(event);
		
		//Update Childs
		for(GUIComponent child: component.getComponents()){
		
			updateGuiComponent(child, event);
		
		}
	}

	private GUIEvent updateComponent(GUIComponent component, PointerEvent event){

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

			if(result!=GUIEvent.NONE){

				gerenciaEvento(component, result);

				return result;
			}

			//Update Childs
			for(GUIComponent child: component.getComponents()){

				child.setOffset(component.getX(), component.getY());

				updateComponent(child, event);

				child.setOffset(-component.getX(), -component.getY());

			}

		}

		return GUIEvent.NONE;

	}

	private void gerenciaEvento(GUIComponent componente, GUIEvent lastEvent){

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

			System.out.println("LostFocus");

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

	public void draw(Grafico g){

		List<Window> drawWindows = new CopyOnWriteArrayList<Window>(windows);

		for(Window window : drawWindows){

			boolean offset = window.getX()!=0||window.getY()!=0; 
			if(offset){
				g.translate(window.getX(), window.getY());
			}

			window.draw(g);

			List<GUIComponent> components = new CopyOnWriteArrayList<GUIComponent>(window.getComponents());

			//if(!window.isLocked()){
			for(GUIComponent componente: components){

				drawComponent(componente, g);

			}
			//}
			if(offset){
				g.translate(-window.getX(), -window.getY());
			}
		}

		int effects = globalEffects.size();

		for(int i = 0; i<effects; i++){

			if(!globalEffects.get(i).isEnd()){
				globalEffects.get(i).draw(g);
			}else{
				globalEffects.remove(i);
				effects--;
				break;
			}

		}

		drawMouse(g);

	}

	private BasicStroke strokeOne = new BasicStroke(1F);
	private BasicStroke strokeThree = new BasicStroke(3F);
	private BasicStroke strokeFive = new BasicStroke(5F);

	private void drawMouse(Grafico g){

		g.getGraphics().setStroke(strokeOne);
		mouse.getArrow().move(mouse.getX(), mouse.getY());
		mouse.getArrow().draw(g);

		if(Configuration.getInstance().isTimerClick()){

			g.setColor(Color.WHITE);
			g.getGraphics().setStroke(strokeFive);  // set stroke width of 5

			int raio = 26;

			g.drawArc(mouse.getX()-raio+2, mouse.getY()-raio+2, raio*2, raio*2, 0, 360);

			g.setColor(Color.BLUE);

			//Only if component was Clickable
			if(mouseOverClickable){
				g.getGraphics().setStroke(strokeThree);  // set stroke width of 3
				g.drawArc(mouse.getX()-raio+2, mouse.getY()-raio+2, raio*2, raio*2, 0, mouse.getArc());
				g.getGraphics().setStroke(strokeOne);  // set stroke width of 1
			}
		}

	}

	//TODO Some kind of Subimage to textfields for example
	private void drawComponent(GUIComponent component, Grafico g){

		if(component.isVisible()){
			component.draw(g);

			List<GUIComponent> components = new CopyOnWriteArrayList<GUIComponent>(component.getComponents());

			for(GUIComponent child: components){
				child.setOffset(component.getX(), component.getY());
				//g.setBimg(g.getBimg().getSubimage(child.getX(), child.getY(), child.getW(), child.getH()));
				drawComponent(child,g);
				child.setOffset(-component.getX(), -component.getY());
			}
		}

	}

	public void translateComponents(int x, int y){
		for(GUIComponent component: activeWindow.getComponents()){
			translateComponent(x, y, component);
		}
	}

	private void translateComponent(int x, int y, GUIComponent component){

		component.setOffset(x, y);

		for(GUIComponent child: component.getComponents()){
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

	public List<PointerEvent> getEvents(){
		return mouseEvents;
	}

	public void addEvent(PointerEvent event){
		mouseEvents.add(event);		
	}

	public void addEffect(GlobalEffect effect){
		globalEffects.add(effect);		
	}

	private void updateKeyboardEvents(KeyboardEvent event){

		if(event.getPressed(Tecla.TSK_ALT_DIREITA)||event.getPressed(Tecla.TSK_ALT_ESQUERDA)){

			alt = true;
		}
		else if(event.getReleased(Tecla.TSK_ALT_DIREITA)||event.getReleased(Tecla.TSK_ALT_ESQUERDA)){

			alt = false;
		}

		if(event.getPressed(Tecla.TSK_ENTER)){
			enter = true;
		}
		else if(event.getReleased(Tecla.TSK_ENTER)){
			enter = false;
		}

		if(event.getPressed(Tecla.TSK_ESC)){
			esc = true;
		}
		else if(event.getReleased(Tecla.TSK_ESC)){
			esc = false;
		}

		if(alt&&enter){
			alt = false;
			enter = false;
			enableFullScreen = true;
		}

		if(esc){
			esc = false;
			disableFullScreen = true;
		}

	}

	private void updateNumpadMouse(KeyboardEvent event){

		if(Configuration.getInstance().isNumpadMouse()){

			int velocidade = 1;

			//Move Left/Right
			if(event.getPressed(Tecla.TSK_NUMPAD_SETA_ESQUERDA)){

				mouse.setX(mouse.getX()-velocidade);
				mouseEvents.add(new PointerEvent(MouseButton.MOUSE_NONE, KeyState.MOVE, mouse.getX(), mouse.getY()));

			}else if(event.getPressed(Tecla.TSK_NUMPAD_SETA_DIREITA)){

				mouse.setX(mouse.getX()+velocidade);
				mouseEvents.add(new PointerEvent(MouseButton.MOUSE_NONE, KeyState.MOVE, mouse.getX(), mouse.getY()));

			}

			//Move Up/Down
			if(event.getPressed(Tecla.TSK_NUMPAD_SETA_CIMA)){

				mouse.setX(mouse.getY()-velocidade);
				mouseEvents.add(new PointerEvent(MouseButton.MOUSE_NONE, KeyState.MOVE, mouse.getX(), mouse.getY()));

			}else if(event.getPressed(Tecla.TSK_NUMPAD_SETA_BAIXO)){

				mouse.setX(mouse.getY()+velocidade);
				mouseEvents.add(new PointerEvent(MouseButton.MOUSE_NONE, KeyState.MOVE, mouse.getX(), mouse.getY()));

			}

			//Mouse Left Button
			if(event.getPressed(Tecla.TSK_NUMPAD_INS)){
				mouse.getEvents().add(new PointerEvent(MouseButton.MOUSE_BUTTON_LEFT, KeyState.PRESSED));
			}else if(event.getReleased(Tecla.TSK_NUMPAD_INS)){
				mouse.getEvents().add(new PointerEvent(MouseButton.MOUSE_BUTTON_LEFT, KeyState.RELEASED));
			}/*else if(event.getKeyTyped(Tecla.TSK_NUMPAD_INS)){
				Gui.getInstance().addEvent(new Event(Tecla.MOUSE_BUTTON_LEFT, KeyState.CLICK));
			}*/

			//Mouse Right Button
			if(event.getPressed(Tecla.TSK_NUMPAD_DEL)){
				mouse.getEvents().add(new PointerEvent(MouseButton.MOUSE_BUTTON_RIGHT, KeyState.PRESSED));
			}else if(event.getReleased(Tecla.TSK_NUMPAD_DEL)){
				mouse.getEvents().add(new PointerEvent(MouseButton.MOUSE_BUTTON_RIGHT, KeyState.RELEASED));
			}/*else if(event.getKeyTyped(Tecla.TSK_NUMPAD_DEL)){
				Gui.getInstance().addEvent(new Event(Tecla.MOUSE_BUTTON_RIGHT, KeyState.CLICK));
			}*/

		}
	}

	private GUIEvent updateFrameEvents(PointerEvent event){

		if(event.getState()==KeyState.CLICK){
			return GUIEvent.REQUEST_FOCUS;
		}

		if(event.getState()==KeyState.DRAGGED){

			if(mouse.getY()<=50){
				/*System.out.println("Evento Mouse dragged");

				System.out.println("Mx = "+mouse.getDragX());
				System.out.println("My = "+mouse.getDragY());
				System.out.println("Dx = "+mouse.getDragX());
				System.out.println("Dy = "+mouse.getDragY());*/
				return GUIEvent.WINDOW_MOVE;
			}


			/*if(event.getMouseButtonDragged(Tecla.MOUSE_BUTTON_LEFT)){

				if(mouse.getY()<20)
				System.out.println("Evento Move Janela");

				return GUIEvent.WINDOW_MOVE;
			}*/
		}

		return GUIEvent.NONE;
	}

	public void addWindow(Window window){

		//Change to wID system or
		//Set<Windows>...
		if(window!=activeWindow){

			//TODO Fix this
			//window.setSessionMap(sessionMap);
			//window.getApplication().setSessionMap(sessionMap);

			window.setClose(false);

			windows.add(window);

			activeWindow = window;

			//Avoid unnecessary reload
			if(window.getApplication().getLoading()!=100){
				reload(window.getApplication());
			}

		}

	}

	public void setMainApplication(Application application){

		reload(application);
	}

	protected void changeApplication(){

		reload(activeWindow.getApplication().getReturnApplication());

	}

	private void reload(Application application){

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
					mouseEvents.add(new PointerEvent(MouseButton.MOUSE_BUTTON_LEFT, KeyState.CLICK));
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

}
