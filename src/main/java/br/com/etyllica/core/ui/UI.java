package br.com.etyllica.core.ui;

import br.com.etyllica.awt.AWTArrowDrawer;
import br.com.etyllica.core.Module;
import br.com.etyllica.core.context.Context;
import br.com.etyllica.core.event.*;
import br.com.etyllica.core.graphics.ArrowDrawer;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.core.input.mouse.Mouse;
import br.com.etyllica.gui.View;
import br.com.etyllica.gui.theme.Theme;
import br.com.etyllica.gui.theme.ThemeManager;
import br.com.etyllica.gui.theme.cursor.ArrowTheme;
import br.com.etyllica.gui.theme.listener.ThemeListener;
import br.com.etyllica.theme.etyllic.EtyllicArrowTheme;

import java.util.ArrayList;
import java.util.List;

public class UI implements Module, ThemeListener {

    private static UI instance;

    private Context context;

    public static int w, h;
    public static UICoreListener listener;
    public static Mouse mouse;
    public static boolean timerClick = false;
    public ArrowDrawer arrowDrawer = new AWTArrowDrawer();
    public ArrowTheme arrowTheme = new EtyllicArrowTheme();

    //Timer click arc
    private int arc = 0;

    private View focus;

    //View above Mouse
    public View mouseOver = null;
    protected View focusComponent = null;

    private boolean overClickable = false;

    public boolean needReload = false;

    private boolean locked = false;

    public List<GUIEvent> guiEvents = new ArrayList<GUIEvent>();
    private static List<View> views = new ArrayList<View>();

    private UI() {
        super();
    }

    public static UI getInstance() {
        if (instance == null) {
            instance = new UI();

            ThemeManager.getInstance().setArrowThemeListener(instance.arrowDrawer);
            ThemeManager.getInstance().setArrowTheme(instance.arrowTheme);
            ThemeManager.getInstance().setThemeListener(instance);
        }

        return instance;
    }

    public void updateGui(List<View> components) {
        for (GUIEvent event : guiEvents) {
            for (View component : components) {
                updateGuiComponent(component, event);
            }
        }

        guiEvents.clear();
    }

    private void updateGuiComponent(View component, GUIEvent event) {
        component.updateEvent(event);

        for (View child : views) {
            updateGuiComponent(child, event);
        }
    }

    public void updateMouseViews(PointerEvent event, List<View> views) {
        for (View view : views) {
            //Update View
            updateEvent(view, view.updateMouse(event));

            //Update Children
            updateMouseViews(event, view.getViews());
        }
    }

    public GUIEvent updateMouse(View view, PointerEvent event) {

        if (!view.isVisible()) {
            return GUIEvent.NONE;
        }

        GUIEvent result = view.updateMouse(event);

        if (GUIEvent.MOUSE_IN == result) {
            setMouseOver(view);
        } else if (GUIEvent.MOUSE_OUT == result) {
            resetMouseOver();
        } else if (GUIEvent.GAIN_FOCUS == result) {
            setFocus(view);
        } else if (GUIEvent.LOST_FOCUS == result) {
            removeFocus(view);
        } else if (result != GUIEvent.NONE && result != null) {
            updateEvent(view, result);
        }

        return GUIEvent.NONE;
    }

    private GUIEvent updateEvent(View view, GUIEvent lastEvent) {

        switch (lastEvent) {

            case GAIN_FOCUS:

                //Remove focus from last
                if (focus != null) {
                    focus.updateEvent(GUIEvent.LOST_FOCUS);
                }

                view.setOnFocus(true);

                focus = view;

                break;

            case LOST_FOCUS:

                if (view == focus) {
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

                view.updateEvent(GUIEvent.LOST_FOCUS);

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
                updateMouse(view, new PointerEvent(MouseEvent.MOUSE_NONE, PointerState.MOVE, mouse.getX(), mouse.getY()));
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

    private void setFocus(View view) {
        if (focus != null) {
            removeFocus(focus);
        }
        focus = view;
        view.setOnFocus(true);
        view.updateEvent(GUIEvent.GAIN_FOCUS);
    }

    private void removeFocus(View view) {
        if (view == focus) {
            view.setOnFocus(false);
            view.updateEvent(GUIEvent.LOST_FOCUS);
            focus = null;
        }
    }

    private void removeMouseOver(View view) {
        if (view == null)
            return;
        view.setMouseOver(false);
        view.updateEvent(GUIEvent.MOUSE_OUT);
    }

    public void drawCursor(Graphics g) {
        arrowDrawer.setCoordinates(mouse.getX(), mouse.getY());
        arrowDrawer.draw(g);
        //Draw Accessible Cursor
        if (timerClick && overClickable) {
            arrowDrawer.drawTimerArc(g, arc);
        }
    }

    //Move to ArrowDrawer
    public void updateTimerClick(long now) {
        final int speed = 3;

        if (mouseOver != null) {

            if (timerClick) {

                if (arc < 360) {
                    arc += speed;
                } else {

                    updateEvent(mouseOver, GUIEvent.MOUSE_LEFT_BUTTON_DOWN);
                    updateEvent(mouseOver, GUIEvent.MOUSE_LEFT_BUTTON_UP);

                    resetMouseOver();
                }
            }

        } else {

            if (timerClick) {
                arc = 0;
            }

        }
    }

    public void drawUIViews(Graphics g) {
        for (View child : views) {
            child.drawWithChildren(g);
        }
    }

    @Override
    public void updateMouse(PointerEvent event) {
        updateMouseViews(event, views);
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

    @Override
    public void updateGuiEvent(GUIEvent event) {
        for (View component : views) {
            updateGuiComponent(component, event);
        }
    }

    @Override
    public void init(Context context) {
        this.context = context;
        context.setMouseStateListener(arrowDrawer);
    }

    @Override
    public void dispose(Context context) {

    }

    @Override
    public void draw(Graphics g) {
        if (locked) {
            return;
        }
        //Draw Components
        for (UIComponent component : context.getComponents()) {
            component.draw(g);
        }
        drawUIViews(g);

        //Draw Cursor
        if (context.isDrawCursor()) {
            drawCursor(g);
        }
    }

    @Override
    public void update(long now) {
        if (needReload) {
            fastReload();
            needReload = false;
        }

        updateTimerClick(now);
        updateGui(views);
    }

    private void fastReload() {
        locked = true;

        //Just Rebuild UI Components
        for (View view : views) {
            view.rebuild();
        }

        locked = false;
    }

    @Override
    public void updateTheme(Theme theme) {
        needReload = true;
    }

    public List<View> getViews() {
        return views;
    }

    public void setViews(List<View> views) {
        this.views = views;
    }

    public static void add(View view) {
        UI.views.add(view);
    }

    public static void addAll(List<View> views) {
        UI.views.addAll(views);
    }
}
