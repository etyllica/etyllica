package br.com.etyllica.core.input.mouse;

import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.List;

import javax.swing.event.MouseInputListener;

import br.com.etyllica.core.Drawable;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.event.PointerState;
import br.com.etyllica.layer.AnimatedLayer;
import br.com.etyllica.layer.ImageLayer;
import br.com.etyllica.theme.mouse.ArrowTheme;
import br.com.etyllica.theme.mouse.ArrowThemeListener;
import br.com.etyllica.theme.mouse.MouseArrow;
import br.com.etyllica.util.RingBuffer;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class MouseHandler implements MouseMotionListener, MouseInputListener, MouseWheelListener, ArrowThemeListener {

	private RingBuffer<PointerEvent> events = new RingBuffer<PointerEvent>(PointerEvent.class);
	
	protected int x = 0;
	protected int y = 0;
	protected int z = 0;

	//Arc for TimeClick
	protected int arc = 0;

	protected boolean overText = false;
	
	protected boolean overClickable = false;

	protected ArrowTheme arrowTheme;

	protected MouseArrow arrow;
	
	public MouseHandler(int x, int y) {
		super();
		
		this.x = x;
		this.y = y;
		
		events.setMinimumSlots(4);
	}
	
	public void updateArrowTheme(ArrowTheme arrowTheme) {
		this.arrowTheme = arrowTheme;
		setStateNormal();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public void setCoordenadas(int x, int y) {
		setX(x);
		setY(y);		
	}

	public void click(int botao) {
		setStateClick();		
	}

	public void pressiona(int botao) {
		setStateClick();
	}

	public void solta(int botao) {
		setStateNormal();
	}

	protected int clicks = 0;
	//TODO doubleClick[]
	protected boolean doubleClick = false;

	protected boolean dragged = false;
	
	protected int dragButton = 0;
	
	protected int dragX = 0;
	protected int dragY = 0;

	private void addEvent(int button, PointerState state) {

		addEvent(button, state, 0);

	}
	
	private void addEvent(int button, PointerState state, int amount) {

		addEvent(button, state, 0, amount);

	}
	
	private void addEvent(int button, PointerState state, int amountX, int amountY) {

		MouseButton key = MouseButton.MOUSE_NONE;

		switch (button) {
		case MouseEvent.BUTTON1:
			key = MouseButton.MOUSE_BUTTON_LEFT;
			break;
		case MouseEvent.BUTTON2:
			key = MouseButton.MOUSE_BUTTON_MIDDLE;
			break;
		case MouseEvent.BUTTON3:
			key = MouseButton.MOUSE_BUTTON_RIGHT;
			break;
		}
		
		events.getSlot().set(key, state, x, y, amountX, amountY);

	}

	@Override
	public void mouseClicked( MouseEvent me ) {

		PointerState state = PointerState.CLICK;

		if (me.getClickCount() == 2) {
			state = PointerState.DOUBLE_CLICK;
		}else if (me.getClickCount() > 2) {
			state = PointerState.MULTIPLE_CLICK;
		}

		setCoordenadas(me.getX(),me.getY());
		
		addEvent(me.getButton(),state, me.getClickCount());
		
		me.consume();
	}

	@Override
	public void mousePressed( MouseEvent me ) {

		setCoordenadas(me.getX(),me.getY());
		
		addEvent(me.getButton(),PointerState.PRESSED);
		
		pressDragButton(me.getButton());
		
		me.consume();
	}
	
	private void pressDragButton(int button) {
				
		if(dragButton == 0) {
			dragButton = button;			
		}
		
	}
	
	private void releaseDragButton(int button) {
		
		if(dragButton == button) {
			dragged = false;
			dragButton = 0;
		}
		
	}

	@Override
	public void mouseReleased( MouseEvent me ) {

		setCoordenadas(me.getX(),me.getY());
		
		addEvent(me.getButton(),PointerState.RELEASED);

		releaseDragButton(me.getButton());

		me.consume();
	}

	@Override
	public void mouseMoved( MouseEvent me ) {
		
		setCoordenadas(me.getX(),me.getY());
		
		addMouseMoveEvent(x, y);

		me.consume();
	}

	@Override
	public void mouseEntered( MouseEvent me ) {
		//mouseMoved( me );
		me.consume();
	}

	@Override
	public void mouseExited( MouseEvent me ) {
		me.consume();
	}

	@Override
	public void mouseDragged( MouseEvent me ) {
		
		if(!dragged) {
			dragX = me.getX();
			dragY = me.getY();

			dragged = true;
		}
		
		int deltaX = me.getX()-dragX;
		int deltaY = me.getY()-dragY;
		
		setCoordenadas(me.getX(), me.getY());
				
		addEvent(dragButton, PointerState.DRAGGED, deltaX, deltaY);
		
		me.consume();
		
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent mwe) {
		
		MouseButton key = MouseButton.MOUSE_WHEEL_DOWN;		
		
		if(mwe.getWheelRotation()<0) {
			key = MouseButton.MOUSE_WHEEL_UP;
		}
		
		events.getSlot().set(key, PointerState.PRESSED, x, y, mwe.getWheelRotation());
	}

	//TODO Remover colisoes e incluir em layer

	//Colisao
	public boolean sobMouseCircular(ImageLayer b) {
		
		final float radius = b.getW()/2;
		
		return sobMouseCircular(b.getX()+radius,b.getY()+radius, radius);
	}

	public boolean sobMouseCircular(float cx, float cy, float radius) {
		
		float dx = cx - x;
		float dy = cy - y;

		if ( ( dx * dx )  + ( dy * dy ) < radius * radius )	{
			return true;
		}

		return false;

	}

	public boolean sobMouseIso(ImageLayer cam) {

		float my = cam.getH()/2;
		float mx = cam.getW()/2;

		float x = this.x-cam.getX();
		float y = this.y-cam.getY();

		if(y>+my)
			y=my-(y-my);

		if((x>mx+1+(2*y))||(x<mx-1-(2*y)))
			return false;
		else
			return true;

	}

	public boolean sobMouse(float x, float y, float w, float h)	{
		
		if((this.x<x)||(this.x>x + w)) {
			
			return false;
		}
		
		if((this.y<y)||(this.y>y + h)) {
			
			return false;
		}

		return true;	
	}

	public boolean sobMouse(AnimatedLayer cam) {
		
		return sobMouse(cam.getX(), cam.getY(), cam.getTileW(), cam.getTileH());
	}

	public boolean sobMouse(Polygon poligono) {
		return poligono.contains(x, y);
	}

	public void setStateClick() {
		arrow = arrowTheme.getClickArrow();
	}
	
	public void setStateLoading() {
		arrow = arrowTheme.getLoadingArrow();
	}
	
	public void setStateNormal() {
		arrow = arrowTheme.getNormalArrow();
	}
	
	public void setStateText() {
		arrow = arrowTheme.getTextArrow();
	}

	public void resetCursor() {
		arrow = arrowTheme.getNormalArrow();
	}

	public int getDragX() {
		return dragX;
	}
	public int getDragY() {
		return dragY;
	}

	public void setArc(int arc) {
		this.arc = arc;
	}

	public int getArc() {
		return arc;
	}

	public void setTextMode(boolean textMode) {

		this.overText = textMode;

		if(textMode) {
			setStateText();
		}
		else{
			setStateNormal();
		}
	}

	public boolean isTextMode() {
		return overText;
	}

	public Drawable getArrow() {
		return arrow;
	}
	
	public List<PointerEvent> getEvents() {
				
		return events.getList();
	}
	
	public void addMouseMoveEvent(int x, int y) {

		events.getSlot().set(MouseButton.MOUSE_NONE, PointerState.MOVE, x, y);
	}
	
	public void clearEvents() {
		events.pack();
	}
	
	public void addEvent(PointerEvent event) {
		events.getSlot().copy(event);
	}

	public boolean isOverClickable() {
		return overClickable;
	}

	public void setOverClickable(boolean overClickable) {
		this.overClickable = overClickable;
	}

}
