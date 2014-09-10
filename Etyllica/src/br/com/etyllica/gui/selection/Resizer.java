package br.com.etyllica.gui.selection;

import java.awt.BasicStroke;
import java.awt.Color;

import br.com.etyllica.collision.ColisionDetector;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.input.mouse.MouseButton;
import br.com.etyllica.core.input.mouse.MouseState;
import br.com.etyllica.core.input.mouse.MouseStateChanger;
import br.com.etyllica.gui.stroke.DashedStroke;
import br.com.etyllica.layer.GeometricLayer;

public class Resizer {

	private GeometricLayer selected;

	private ResizerPoint selectedArea;

	private ResizerPoint[] points;

	private MouseStateChanger changer;
	
	private ResizerListener listener;

	private int buttonSize = 30;

	private final DashedStroke dash = new DashedStroke();

	private final BasicStroke resetStroke = new BasicStroke(1);

	private boolean dragged = false;

	private int initialX = 0;
	private int initialY = 0;
	private int initialW = 0;
	private int initialH = 0;

	private int lastIndex = 0;

	private int keyboardSpeed = 1;

	public Resizer(MouseStateChanger context) {
		super();

		changer = context;

		points = new ResizerPoint[9];
		for(int i=0;i<8; i++)
			points[i] = new ResizerPoint(0, 0, 1, 1);

		selectedArea = new ResizerPoint(0, 0, 1, 1);
		selectedArea.setState(MouseState.MOVE);

		points[8] = selectedArea;

		points[0].setState(MouseState.ARROW_NW_SE);
		points[1].setState(MouseState.ARROW_VERTICAL);
		points[2].setState(MouseState.ARROW_NE_SW);
		points[3].setState(MouseState.ARROW_HORIZONTAL);
		points[4].setState(MouseState.ARROW_HORIZONTAL);
		points[5].setState(MouseState.ARROW_NE_SW);
		points[6].setState(MouseState.ARROW_VERTICAL);
		points[7].setState(MouseState.ARROW_NW_SE);
		points[8].setState(MouseState.MOVE);
	}

	public void reselect() {
		select(selected);
	}

	public void deselect() {
		this.selected = null;

		changer.changeMouseState(MouseState.NORMAL);
	}

	public void select(GeometricLayer selected) {

		if(!isSelected()) {
			deselect();
		}

		this.selected = selected;

		selectedArea.copy(selected);

		int inc = 0;

		//Update 8 points
		for(int b=0; b<9; b++) {

			int i = b%3;
			int j = b/3;

			if(i==1 && j==1) {
				inc = -1;
				continue;
			}

			int bx = selected.getX()+i*(selected.getW()/2) - buttonSize/2;
			int by = selected.getY()+j*(selected.getH()/2) - buttonSize/2;

			points[b+inc].setBounds(bx, by, buttonSize, buttonSize);
		}

	}

	public void draw(Graphic g) {

		if(!isSelected())
			return;

		g.setColor(Color.BLACK);

		g.setStroke(dash);

		g.drawRect(selected);

		for(int b=0; b < 8; b++) {

			points[b].draw(g);
		}

		g.setStroke(resetStroke);
	}

	private boolean changed = false;

	public void handleEvent(PointerEvent event) {

		if(selected == null)
			return;

		int mx = event.getX();
		int my = event.getY();

		changed = false;

		if(!dragged) {

			for(int b = 0; b < 9; b++) {

				if(ColisionDetector.colideRectPoint(points[b], mx, my)) {

					lastIndex = b;

					changer.changeMouseState(points[b].getState());
					changed = true;

					handleDragEvent(event);

					break;
				}
			}
		}


		if(event.isButtonUp(MouseButton.MOUSE_BUTTON_LEFT)) {
			dragged = false;
		} else if(dragged && event.isDraggedButton(MouseButton.MOUSE_BUTTON_LEFT)) {
			resizeEvent(lastIndex, event);
			notifyListener();
			reselect();		
		}
		
		if(!changed && event.isClicked(MouseButton.MOUSE_BUTTON_LEFT)) {
			deselect();
		}

		if(!changed) {
			changer.changeMouseState(MouseState.NORMAL);
		}

	}

	private void resizeEvent(int index, PointerEvent event) {
		switch (index) {
		case 0:
			resizeUp(event);
			resizeLeft(event);
			break;

		case 1:
			resizeUp(event);
			break;

		case 2:
			resizeUp(event);
			resizeRight(event);
			break;

		case 3:
			resizeLeft(event);
			break;

		case 4:
			resizeRight(event);
			break;

		case 5:
			resizeDown(event);
			resizeLeft(event);
			break;

		case 6:
			resizeDown(event);
			break;

		case 7:
			resizeDown(event);
			resizeRight(event);
			break;

		default:
			moveSelected(event);
			break;
		}
	}

	private void handleDragEvent(PointerEvent event) {

		if(!dragged && event.isDraggedButton(MouseButton.MOUSE_BUTTON_LEFT)) {

			setInitialValues();

			dragged = true;
		}

		if(event.isButtonUp(MouseButton.MOUSE_BUTTON_LEFT)) {
			dragged = false;
		}

	}

	private void setInitialValues() {
		initialX = selected.getX();
		initialY = selected.getY();
		initialW = selected.getW();
		initialH = selected.getH();
	}

	private void moveSelected(PointerEvent event) {
		selected.setX(initialX+event.getAmountX());
		selected.setY(initialY+event.getAmountY());
	}

	private void resizeUp(PointerEvent event) {
		selected.setY(initialY+event.getAmountY());
		selected.setH(initialH-event.getAmountY());		
	}

	private void resizeDown(PointerEvent event) {
		selected.setH(initialH+event.getAmountY());
	}

	private void resizeLeft(PointerEvent event) {
		selected.setX(initialX+event.getAmountX());
		selected.setW(initialW-event.getAmountX());
	}

	private void resizeRight(PointerEvent event) {
		selected.setW(initialW+event.getAmountX());
	}

	public boolean isDragged() {
		return dragged||changed;
	}

	public boolean isSelected() {
		return selected != null;
	}

	public GeometricLayer getSelectedLayer() {
		return selected;
	}

	public void handleKeyEvent(KeyEvent event) {

		if(event.isKeyDown(KeyEvent.TSK_UP_ARROW)) {
			selected.setOffsetY(-keyboardSpeed);
			notifyListener();
			reselect();
		} else if(event.isKeyDown(KeyEvent.TSK_DOWN_ARROW)) {
			selected.setOffsetY(+keyboardSpeed);
			notifyListener();
			reselect();
		}

		if(event.isKeyDown(KeyEvent.TSK_LEFT_ARROW)) {
			selected.setOffsetX(-keyboardSpeed);
			notifyListener();
			reselect();
		} else if(event.isKeyDown(KeyEvent.TSK_RIGHT_ARROW)) {
			selected.setOffsetX(+keyboardSpeed);
			notifyListener();
			reselect();
		}

	}
	

	private void notifyListener() {
		
		if(listener == null)
			return;
		
		listener.onResize(selected.getX(), selected.getY(), selected.getW(), selected.getH());		
	}

	public ResizerListener getListener() {
		return listener;
	}

	public void setListener(ResizerListener listener) {
		this.listener = listener;
	}	

}
