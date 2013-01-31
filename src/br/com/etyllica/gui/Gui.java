package br.com.etyllica.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import br.com.etyllica.core.Configuration;
import br.com.etyllica.core.control.HIDController;
import br.com.etyllica.core.control.keyboard.Keyboard;
import br.com.etyllica.core.control.mouse.Mouse;
import br.com.etyllica.core.event.DeviceType;
import br.com.etyllica.core.event.Event;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyState;
import br.com.etyllica.core.event.KeyboardEvent;
import br.com.etyllica.core.event.Tecla;
import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.effects.GlobalEffect;
import br.com.etyllica.gui.window.DesktopWindow;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public class Gui {

	private static Gui instance = null;

	public static Gui getInstance() {
		if(instance==null){
			instance = new Gui();
		}

		return instance;
	}


	//Janelas externas
	private Window activeWindow;
	private List<Window> windows = new ArrayList<Window>();

	private List<GlobalEffect> globalEffects = new ArrayList<GlobalEffect>();

	private GUIComponent focus;

	private HIDController controle;
	private Mouse mouse;
	private Keyboard teclado;

	//Mouse Over Something
	//Usado para acessibilidade talvez
	private boolean mouseOver = false;
	private boolean mouseOverClickable = false;


	private Set<Window> requestCloseSet = new LinkedHashSet<Window>();

	//TODO Separar eventos Mouse, teclado, joystick,...
	private List<Event> mouseEvents = new ArrayList<Event>();

	private List<KeyboardEvent> keyEvents;

	private List<GUIEvent> guiEvents = new ArrayList<GUIEvent>();

	private DesktopWindow desktopWindow;


	private Grafico g = new Grafico();

	private Gui(){
		super();

		controle = new HIDController();

		mouse = controle.getMouse();
		mouseEvents = mouse.getEvents();

		teclado = controle.getTeclado();

		keyEvents = teclado.getEvents();

	}

	public void setDesktopWindow(DesktopWindow desktopWindow){
		this.desktopWindow = desktopWindow;
		add(desktopWindow);
	}

	public DesktopWindow getDesktopWindow() {
		return desktopWindow;
	}

	//TODO Find next
	public GUIComponent findNext(GUIComponent component){
		return null;
	}

	public void add(Window window){

		//Change to wID system or
		//Set<Windows>...
		if(activeWindow!=window){

			windows.add(window);
			activeWindow = window;
		}

	}

	//Change to close
	public void requestClose(Window window){

		requestCloseSet.add(window);

	}

	private void reallyClose(){

		boolean activeWindowSetted = false;

		for(Window close: requestCloseSet){
			close.update(GUIEvent.WINDOW_CLOSE);

			if(close.stillWantClose){
				windows.remove(close);
			}else{
				activeWindow = close;
				activeWindowSetted = true;
			}

		}

		if(!activeWindowSetted){
			activeWindow = windows.get(windows.size()-1);
		}

		requestCloseSet.clear();
	}

	private boolean enableFullScreen = false;
	private boolean disableFullScreen = false;

	private boolean alt = false;
	private boolean enter = false;
	private boolean esc = false;

	private GUIEvent superEvent = GUIEvent.NONE;
	
	public void gerencia(){

		superEvent = GUIEvent.NONE;
		
		updateKeyboard();

		updateCloseRequests(requestCloseSet);

		updateForcedEvents(guiEvents);

		mouseOver = false;
		mouseOverClickable = false;


		GUIEvent lastEvent = GUIEvent.NONE;

		//If window isnt locked
		//if(!activeWindow.locked){

			//Solving ConcurrentModification
			List<Event> events = new CopyOnWriteArrayList<Event>(mouseEvents);

			//Update components with events
			for(Event event: events){

				//if activeWindow, receive command to change application
				if(activeWindow.getApplication().getReturnApplication()!=activeWindow.getApplication()){
					activeWindow.changeApplication(activeWindow.getApplication().getReturnApplication());
				}

				List<GUIComponent> components = new CopyOnWriteArrayList<GUIComponent>(activeWindow.getComponents());
				for(GUIComponent component: components){
					if(component!=null){
						updateComponent(component, event);
					}

					//TODO Coisas de acessibilidade
					//gerenciaTimerClick();

				}

				//TODO Melhorar isso para fechar janela com ctrl+F4 ou algo assim
				if(lastEvent!=GUIEvent.GAIN_FOCUS){
					lastEvent = activeWindow.updateMouse(event);
				}

				lastEvent = gerenciaEvento(activeWindow, lastEvent);

				GUIEvent customEvent = updateCustomEvents(event); 
				if(customEvent!=GUIEvent.NONE){
					superEvent = customEvent;
				}

			}

			mouseEvents.clear();

			if(enableFullScreen){
				enableFullScreen = false;

				superEvent = GUIEvent.ENABLE_FULL_SCREEN;
			}

			if(disableFullScreen){
				disableFullScreen = false;

				superEvent = GUIEvent.DISABLE_FULL_SCREEN;
			}

		//} locked
	}


	private void updateCloseRequests(Set<Window> closeRequests){

		if(!requestCloseSet.isEmpty()){
			reallyClose();
		}

	}
	
	private void updateKeyboard(){
		
		List<KeyboardEvent> keyboardEvents = new CopyOnWriteArrayList<KeyboardEvent>(keyEvents);
		
		for(KeyboardEvent keyboardEvent: keyboardEvents){

			//Application sempre eh gerenciada pelo teclado
			activeWindow.getApplication().updateKeyboard(keyboardEvent);			
			
			//Apenas o componente quem tem foco eh gerenciado pelo teclado
			if(focus!=null){
				focus.updateKeyboard(keyboardEvent);
			}
			
			updateKeyboardEvents(keyboardEvent);
			
			updateNumpadMouse(keyboardEvent);
		}
				
		keyEvents.clear();
	}

	private GUIEvent updateCustomEvents(Event event){
		//updateKeyboardEvents(event);

		//updateNumpadMouse(event);

		return updateFrameEvents(event);		
	}

	private void updateForcedEvents(List<GUIEvent> guiEvents){
		for(GUIEvent event: guiEvents){
			updateForcedEvent(event);
		}
	}

	private void updateForcedEvent(GUIEvent event){
		for(Window window: windows){
			updateForcedEventComponent(window, event);
		}
	}

	private void updateForcedEventComponent(GUIComponent component, GUIEvent event){

		component.update(event);

		for(GUIComponent child: component.components){
			updateForcedEventComponent(child, event);
		}

	}

	private void updateComponent(GUIComponent component, Event event){

		//If componente is visible
		if(component.isVisible()){

			//Update Childs
			for(GUIComponent child: component.components){

				child.setOffset(component.getX(), component.getY());

				updateComponent(child, event);

				child.setOffset(-component.getX(), -component.getY());

			}

			//Update Component
			GUIEvent result = component.updateMouse(event);

			if(result!=GUIEvent.NONE){
				gerenciaEvento(component, result);
			}

		}

	}
	
	private GUIEvent gerenciaEvento(GUIComponent componente, GUIEvent lastEvent){

		//switch (event.action) {
		switch (lastEvent) {

		case GAIN_FOCUS:

			if(focus!=null){
				focus.update(GUIEvent.LOST_FOCUS);
			}
			focus = componente;
			componente.update(lastEvent);

			return GUIEvent.NONE;

		case LOST_FOCUS:

			if(componente==focus){
				//TODO Mouse.loseFocus()
				//events.add(new Event(DeviceType.MOUSE, Tecla.NONE, KeyState.LOSE_FOCUS));
				//events.add(new Event(DeviceType.KEYBOARD, Tecla.NONE, KeyState.LOSE_FOCUS));
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

			//controle.getTeclado().loseFocus();
			//events.add(new Event(DeviceType.KEYBOARD, Tecla.NONE, KeyState.))

			componente.update(GUIEvent.LOST_FOCUS);

			return GUIEvent.GAIN_FOCUS;

		case WINDOW_CLOSE:

			requestClose((Window)componente);

			break;

			/*case ONCE:
			//this.event (param)
			event.setState(KeyState.PRESSED);

			//Prevent consume
			events.add(event);
			break;
			 */

		case APPLICATION_CHANGED:
			activeWindow.changeApplication(activeWindow.getApplication().getReturnApplication());
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

		return lastEvent;
	}

	public void draw(){

		//BufferedImage screen =  g.getBimg();

		//for(UndecoratedWindow window:windows){
		for(int i=0;i<windows.size();i++){
			Window window = windows.get(i);

			window.draw(g);

			List<GUIComponent> components = new CopyOnWriteArrayList<GUIComponent>(window.components);
			
			//if(!window.isLocked()){
			for(GUIComponent componente: components){

				drawComponent(componente, g);

			}
			//}

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

		drawMouse();

	}

	private BasicStroke strokeOne = new BasicStroke(1F);
	private BasicStroke strokeThree = new BasicStroke(3F);
	private BasicStroke strokeFive = new BasicStroke(5F);

	private void drawMouse(){

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

			for(GUIComponent child: component.components){
				child.setOffset(component.getX(), component.getY());
				//g.setBimg(g.getBimg().getSubimage(child.getX(), child.getY(), child.getW(), child.getH()));
				drawComponent(child,g);
				child.setOffset(-component.getX(), -component.getY());
			}
		}

	}

	public void translateComponents(int x, int y){
		for(GUIComponent component: activeWindow.components){
			translateComponent(x, y, component);
		}
	}

	private void translateComponent(int x, int y, GUIComponent component){

		component.setOffset(x, y);

		for(GUIComponent child: component.components){
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

	public List<Event> getEvents(){
		return mouseEvents;
	}

	public void addEvent(Event event){
		mouseEvents.add(event);		
	}

	public void addGUIEvent(GUIEvent event){
		guiEvents.add(event);		
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
				mouseEvents.add(new Event(DeviceType.MOUSE, Tecla.NONE, KeyState.MOVE, mouse.getX(), mouse.getY()));

			}else if(event.getPressed(Tecla.TSK_NUMPAD_SETA_DIREITA)){

				mouse.setX(mouse.getX()+velocidade);
				mouseEvents.add(new Event(DeviceType.MOUSE, Tecla.NONE, KeyState.MOVE, mouse.getX(), mouse.getY()));

			}

			//Move Up/Down
			if(event.getPressed(Tecla.TSK_NUMPAD_SETA_CIMA)){

				mouse.setX(mouse.getY()-velocidade);
				mouseEvents.add(new Event(DeviceType.MOUSE, Tecla.NONE, KeyState.MOVE, mouse.getX(), mouse.getY()));

			}else if(event.getPressed(Tecla.TSK_NUMPAD_SETA_BAIXO)){

				mouse.setX(mouse.getY()+velocidade);
				mouseEvents.add(new Event(DeviceType.MOUSE, Tecla.NONE, KeyState.MOVE, mouse.getX(), mouse.getY()));

			}

			//Mouse Left Button
			if(event.getPressed(Tecla.TSK_NUMPAD_INS)){
				mouse.getEvents().add(new Event(DeviceType.MOUSE, Tecla.MOUSE_BUTTON_LEFT, KeyState.PRESSED));
			}else if(event.getReleased(Tecla.TSK_NUMPAD_INS)){
				mouse.getEvents().add(new Event(DeviceType.MOUSE, Tecla.MOUSE_BUTTON_LEFT, KeyState.RELEASED));
			}/*else if(event.getKeyTyped(Tecla.TSK_NUMPAD_INS)){
				Gui.getInstance().addEvent(new Event(DeviceType.MOUSE, Tecla.MOUSE_BUTTON_LEFT, KeyState.CLICK));
			}*/

			//Mouse Right Button
			if(event.getPressed(Tecla.TSK_NUMPAD_DEL)){
				mouse.getEvents().add(new Event(DeviceType.MOUSE, Tecla.MOUSE_BUTTON_RIGHT, KeyState.PRESSED));
			}else if(event.getReleased(Tecla.TSK_NUMPAD_DEL)){
				mouse.getEvents().add(new Event(DeviceType.MOUSE, Tecla.MOUSE_BUTTON_RIGHT, KeyState.RELEASED));
			}/*else if(event.getKeyTyped(Tecla.TSK_NUMPAD_DEL)){
				Gui.getInstance().addEvent(new Event(DeviceType.MOUSE, Tecla.MOUSE_BUTTON_RIGHT, KeyState.CLICK));
			}*/

		}
	}

	private GUIEvent updateFrameEvents(Event event){
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


	private boolean click = false;

	private void gerenciaTimerClick(){

		Configuration config = Configuration.getInstance();

		int speed = 3;

		if(mouseOverClickable){

			if(config.isTimerClick()){

				//TODO Ativar timer do mouse que incrementa sozinho
				int arc = controle.getMouse().getArc();
				if(arc<360){
					mouse.setArc(arc+speed);
				}else{
					//TODO if timerMouse
					mouseEvents.add(new Event(DeviceType.MOUSE, Tecla.MOUSE_BUTTON_LEFT, KeyState.CLICK));
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
		return controle;
	}

	public Grafico getGrafico(){
		return g;
	}

	public GUIEvent getSuperEvent(){
		return superEvent;
	}

}
