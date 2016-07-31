package br.com.etyllica.core.ui;

import java.util.ArrayList;
import java.util.List;

import br.com.etyllica.core.Configuration;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.MouseButton;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.event.PointerState;
import br.com.etyllica.core.graphics.ArrowDrawer;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.core.input.mouse.Mouse;
import br.com.etyllica.gui.View;

public class UICore {

	int w, h;
	//Timer click arc
	private int arc = 0;

	ArrowDrawer arrowDrawer;

	private View focus;
	Mouse mouse;

	//View above Mouse
	public View mouseOver = null;
	protected View focusComponent = null;

	private boolean overClickable = false;

	private UICoreListener listener;

	public List<GUIEvent> guiEvents = new ArrayList<GUIEvent>();

	public UICore(int w, int h, UICoreListener listener) {
		super();
		this.w = w;
		this.h = h;
		this.listener = listener;
	}

	public void updateGui(List<View> components) {
		for(GUIEvent event: guiEvents) {
			updateGuiEvent(components, event);
		}

		guiEvents.clear();
	}

	public void updateGuiEvent(List<View> components, GUIEvent event) {
		for(View component: components) {
			updateGuiComponent(component, event);
		}
	}

	private void updateGuiComponent(View component, GUIEvent event) {
		component.update(event);

		for(View child: component.getViews()) {
			updateGuiComponent(child, event);
		}
	}

	public void updateMouseViews(PointerEvent event, List<View> views) {

		for(View component: views) {

			//Update View
			updateEvent(component, component.updateMouse(event));
			
			//Update Children
			updateMouseViews(event, component.getViews());
		}
	}

	public void updateKeyboard(KeyEvent event) {
		//Only the focused component handles the keyboard
		if (focus != null) {
			GUIEvent focusEvent = focus.updateKeyboard(event);

			if (focusEvent != GUIEvent.NONE && focusEvent != null) {
				//TODO Update NExtComponent

				if (focusEvent == GUIEvent.NEXT_COMPONENT) {
					View next = focus.findNext();

					if (next != null) {
						updateEvent(focus, focusEvent);
						updateEvent(next, GUIEvent.GAIN_FOCUS);
					}
				} else {
					updateEvent(focus, focusEvent);
				}
			}
		}
	}

	public GUIEvent updateMouse(View component, PointerEvent event) {

		if(!component.isVisible()) {
			return GUIEvent.NONE;
		}

		GUIEvent result = component.updateMouse(event);

		if(GUIEvent.MOUSE_IN == result) {
			setMouseOver(component);
		} else if (GUIEvent.MOUSE_OUT == result) {
			resetMouseOver();
		} else if(GUIEvent.GAIN_FOCUS == result) {
			setFocus(component);
		} else if(GUIEvent.LOST_FOCUS == result) {
			removeFocus(component);
		}else if(result != GUIEvent.NONE && result != null) {
			updateEvent(component, result);
		}

		return GUIEvent.NONE;
	}

	private GUIEvent updateEvent(View view, GUIEvent lastEvent) {

		switch (lastEvent) {

		case GAIN_FOCUS:

			//Remove focus from last
			if(focus != null) {
				focus.update(GUIEvent.LOST_FOCUS);
			}

			view.setOnFocus(true);

			focus = view;

			break;

		case LOST_FOCUS:

			if(view == focus) {
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

			System.out.println("LostFocus");

			//controle.getTeclado().loseFocus();
			//events.add(new Event(DeviceType.KEYBOARD, Tecla.NONE, KeyState.))

			view.update(GUIEvent.LOST_FOCUS);

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
			updateMouse(view, new PointerEvent(MouseButton.MOUSE_NONE, PointerState.MOVE, mouse.getX(), mouse.getY()));				
			break;

		case APPLICATION_CHANGED:
			listener.changeApplication();
			break;

		default:

			/*if(view.isMouseOver()) {
				view.update(GUIEvent.MOUSE_OUT);
			}*/

			break;
		}

		//view.setLastEvent(lastEvent);
		//view.update(lastEvent);
		//view.executeAction(lastEvent);

		return GUIEvent.NONE;
	}

	public void setMouseOver(View view) {
		if (mouseOver != null) {
			removeMouseOver(mouseOver);
		}

		mouseOver = view;
		mouseOver.mouseIn();

		overClickable = true;
	}

	public void resetMouseOver() {
		removeMouseOver(mouseOver);
		mouseOver = null;
		overClickable = false;
	}

	private void setFocus(View component) {
		if (focus != null) {
			removeFocus(focus);
		}
		focus = component;
		component.setOnFocus(true);
		component.update(GUIEvent.GAIN_FOCUS);
	}

	private void removeFocus(View component) {
		if (component == focus) {
			component.setOnFocus(false);
			component.update(GUIEvent.LOST_FOCUS);
			focus = null;			
		}
	}

	private void removeMouseOver(View view) {
		if (view == null)
			return;
		view.setMouseOver(false);
		view.update(GUIEvent.MOUSE_OUT);
	}

	public void drawCursor(Graphics g) {

		arrowDrawer.setCoordinates(mouse.getX(), mouse.getY());
		arrowDrawer.draw(g);
		//Draw Accessible Cursor
		if(Configuration.getInstance().isTimerClick()&&overClickable) {
			arrowDrawer.drawTimerArc(g, arc);
		}
	}

	//Move to ArrowDrawer
	public void updateTimerClick(long now) {

		final int speed = 3;

		if(mouseOver!=null) {

			if(Configuration.getInstance().isTimerClick()) {

				if(arc<360) {
					arc += speed;
				}else{

					updateEvent(mouseOver, GUIEvent.MOUSE_LEFT_BUTTON_DOWN);
					updateEvent(mouseOver, GUIEvent.MOUSE_LEFT_BUTTON_UP);

					resetMouseOver();
				}
			}

		} else {

			if(Configuration.getInstance().isTimerClick()) {
				arc = 0;
			}

		}
	}

	public ArrowDrawer getArrowDrawer() {
		return arrowDrawer;
	}

	public void setArrowDrawer(ArrowDrawer arrowDrawer) {
		this.arrowDrawer = arrowDrawer;
	}

	public void setMouse(Mouse mouse) {
		this.mouse = mouse;
	}

	public void drawUIViews(Graphics g, ViewContainer context) {
		for (View child: context.getViews()) {
			child.drawWithChildren(g);
		}
	}

}
