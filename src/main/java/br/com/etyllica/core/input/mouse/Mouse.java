package br.com.etyllica.core.input.mouse;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.List;

import javax.swing.event.MouseInputListener;

import br.com.etyllica.core.event.MouseEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.event.PointerState;
import br.com.etyllica.util.concurrency.ConcurrentList;

/**
 * 
 * @author yuripourre
 *
 */

public class Mouse implements MouseMotionListener, MouseInputListener, MouseWheelListener {

	protected int x = 0;
	protected int y = 0;
	protected int z = 0;

	protected int clicks = 0;
	//TODO doubleClick[]
	protected boolean doubleClick = false;

	protected boolean dragged = false;

	protected int dragButton = 0;

	protected int dragX = 0;
	protected int dragY = 0;

	private ConcurrentList<PointerEvent> events = new ConcurrentList<PointerEvent>(4);
	
	public Mouse(int x, int y) {
		super();

		this.x = x;
		this.y = y;
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

	public void setCoordinates(int x, int y) {
		setX(x);
		setY(y);
	}

	private void addEvent(int button, PointerState state) {
		addEvent(button, state, 0);
	}

	private void addEvent(int button, PointerState state, int amount) {
		addEvent(button, state, 0, amount);
	}

	private void addEvent(int button, PointerState state, int amountX, int amountY) {

		MouseEvent key = MouseEvent.MOUSE_NONE;

		switch (button) {
		case MouseEvent.BUTTON1:
			key = MouseEvent.MOUSE_BUTTON_LEFT;
			break;
		case MouseEvent.BUTTON2:
			key = MouseEvent.MOUSE_BUTTON_MIDDLE;
			break;
		case MouseEvent.BUTTON3:
			key = MouseEvent.MOUSE_BUTTON_RIGHT;
			break;
		}

		events.add(new PointerEvent(key, state, x, y, amountX, amountY));
	}

	@Override
	public void mouseClicked( MouseEvent me ) {

		PointerState state = PointerState.CLICK;

		if (me.getClickCount() == 2) {
			state = PointerState.DOUBLE_CLICK;
		}else if (me.getClickCount() > 2) {
			state = PointerState.MULTIPLE_CLICK;
		}

		setCoordinates(me.getX(),me.getY());

		addEvent(me.getButton(),state, me.getClickCount());

		me.consume();
	}

	@Override
	public void mousePressed( MouseEvent me ) {

		setCoordinates(me.getX(),me.getY());

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

		setCoordinates(me.getX(),me.getY());

		addEvent(me.getButton(),PointerState.RELEASED);

		releaseDragButton(me.getButton());

		me.consume();
	}

	@Override
	public void mouseMoved( MouseEvent me ) {

		setCoordinates(me.getX(),me.getY());

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

		setCoordinates(me.getX(), me.getY());

		addEvent(dragButton, PointerState.DRAGGED, deltaX, deltaY);

		me.consume();		
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent mwe) {

		MouseEvent key = MouseEvent.MOUSE_WHEEL_DOWN;		

		if(mwe.getWheelRotation()<0) {
			key = MouseEvent.MOUSE_WHEEL_UP;
		}

		events.add(new PointerEvent(key, PointerState.PRESSED, x, y, mwe.getWheelRotation()));
	}
	
	public void addMouseMoveEvent(int x, int y) {
		events.add(new PointerEvent(MouseEvent.MOUSE_NONE, PointerState.MOVE, x, y));
	}

	public void addEvent(PointerEvent event) {
		events.add(event);
	}

	public List<PointerEvent> lock() {
		return events.lock();
	}

	public void unlock() {
		events.unlock();
	}

	/*private PointerEvent getSlot() {

		if(!locked) {

			return events.getSlot();

		} else {

			PointerEvent event = new PointerEvent();
			alternativeList.add(event);

			return event;
		}

	}

	public boolean isLocked() {
		return locked;
	}

	public void lock() {
		this.locked = true;
	}

	public void unlock() {
		this.locked = false;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}*/

}
