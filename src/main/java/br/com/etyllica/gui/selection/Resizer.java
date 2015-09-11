package br.com.etyllica.gui.selection;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.List;

import br.com.etyllica.core.collision.CollisionDetector;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.MouseButton;
import br.com.etyllica.core.event.MouseState;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.input.mouse.MouseStateChanger;
import br.com.etyllica.gui.stroke.DashedStroke;
import br.com.etyllica.layer.GeometricLayer;
import br.com.etyllica.layer.Layer;

public class Resizer {

	private Layer selected;
	private Layer overlay = new Layer();
	
	private ResizerPoint selectedArea;
	private ResizerPoint[] points;

	private ResizerListener listener;
	private MouseStateChanger changer;
	
	private int buttonSize = 16;

	private final DashedStroke dash = new DashedStroke();
	private final BasicStroke resetStroke = new BasicStroke(1);

	private boolean dragged = false;

	private int initialX = 0;
	private int initialY = 0;
	private double initialW = 0;
	private double initialH = 0;

	private int lastIndex = 0;

	private int keyboardSpeed = 1;
	
	private List<Layer> layers;
	
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

	public void select(Layer layer) {

		if(!isSelected()) {
			deselect();
		}

		this.selected = layer;
		selectedArea.copy(layer);
		
		int inc = 0;

		//Update 8 points
		for(int b=0; b<9; b++) {

			int i = b%3;
			int j = b/3;

			if(i==1 && j==1) {
				inc = -1;
				continue;
			}
			
			int offsetX = (int)(layer.utilWidth()*(1-layer.getScaleX()))/2;
			int offsetY = (int)(layer.utilHeight()*(1-layer.getScaleY()))/2;
			
			int bx = (int)(layer.getX()+offsetX+i*(layer.utilWidth()*layer.getScaleX()/2) - buttonSize/2);
			int by = (int)(layer.getY()+offsetY+j*(layer.utilHeight()*layer.getScaleY()/2) - buttonSize/2);

			points[b+inc].setBounds(bx, by, buttonSize, buttonSize);
		}

	}

	public void draw(Graphic g) {

		drawOverlay(g);
		
		if(!isSelected())
			return;
		
		g.setColor(Color.BLACK);
		g.setStroke(dash);
		drawScaledRect(g, selected);

		for(int b=0; b < 8; b++) {
			points[b].draw(g);
		}

		g.setStroke(resetStroke);
	}

	private void drawScaledRect(Graphic g, Layer layer) {
		int sw = (int)(layer.utilWidth()*layer.getScaleX());
		int sh = (int)(layer.utilHeight()*layer.getScaleY());
				
		int offsetX = (int)(layer.utilWidth()*(1-layer.getScaleX()))/2;
		int offsetY = (int)(layer.utilHeight()*(1-layer.getScaleY()))/2;
		
		g.drawRect(layer.getX()+offsetX, layer.getY()+offsetY, sw, sh);
	}
	
	private void fillScaledRect(Graphic g, Layer layer) {
		int sw = (int)(layer.utilWidth()*layer.getScaleX());
		int sh = (int)(layer.utilHeight()*layer.getScaleY());
		
		int offsetX = (int)(layer.utilWidth()*(1-layer.getScaleX()))/2;
		int offsetY = (int)(layer.utilHeight()*(1-layer.getScaleY()))/2;
		
		g.fillRect(layer.getX()+offsetX, layer.getY()+offsetY, sw, sh);
	}
		
	private void drawOverlay(Graphic g) {

		if(overlay.isVisible() == false)
			return;

		g.setColor(Color.BLACK);
		g.setAlpha(60);
		fillScaledRect(g, overlay);
		g.resetOpacity();
	}

	private boolean changed = false;

	public void handleEvent(PointerEvent event) {

		int mx = event.getX();
		int my = event.getY();

		if(!isSelected()) {
			for(Layer component: layers) {
				if(component.onMouse(mx, my)) {
					overlay.setVisible(true);
					overlay.copy(component);
					break;
				}
			}
		}
		
		if(event.isButtonDown(MouseButton.MOUSE_BUTTON_LEFT)) {

			if(!isSelected()) {
				for(Layer component: layers) {
					if(component.onMouse(mx, my)) {
						overlay.copy(component);
						select(component);
						overlay.setVisible(false);
						
						break;
					}
				}
				
			} else if(!isDragged()) {
				deselect();
			}

		}
		
		if(selected == null)
			return;

		changed = false;

		if(!dragged) {

			for(int b = 0; b < 9; b++) {

				if(CollisionDetector.colideRectPoint(points[b], mx, my)) {
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
			//notifyListener();
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
		initialW = selected.utilWidth()*selected.getScaleX();
		initialH = selected.utilHeight()*selected.getScaleY();
	}

	private void moveSelected(PointerEvent event) {
		selected.setX(initialX+event.getAmountX());
		selected.setY(initialY+event.getAmountY());
	}

	private void resizeUp(PointerEvent event) {
		selected.setY(initialY+event.getAmountY()/2);
		double sy = initialH-event.getAmountY();
		selected.setScaleY(sy/selected.utilHeight());
	}

	private void resizeDown(PointerEvent event) {
		selected.setY(initialY+event.getAmountY()/2);
		double sy = initialH+event.getAmountY();
		selected.setScaleY(sy/selected.utilHeight());
	}

	private void resizeLeft(PointerEvent event) {
		selected.setX(initialX+event.getAmountX()/2);
		double sx = initialW-event.getAmountX();
		selected.setScaleX(sx/selected.utilWidth());
	}

	private void resizeRight(PointerEvent event) {
		selected.setX(initialX+event.getAmountX()/2);
		double sx = initialW+event.getAmountX();
		selected.setScaleX(sx/selected.utilWidth());
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

		if(event.isKeyDown(KeyEvent.VK_UP_ARROW)) {
			selected.setOffsetY(-keyboardSpeed);
			notifyListener();
			reselect();
		} else if(event.isKeyDown(KeyEvent.VK_DOWN_ARROW)) {
			selected.setOffsetY(+keyboardSpeed);
			notifyListener();
			reselect();
		}

		if(event.isKeyDown(KeyEvent.VK_LEFT_ARROW)) {
			selected.setOffsetX(-keyboardSpeed);
			notifyListener();
			reselect();
		} else if(event.isKeyDown(KeyEvent.VK_RIGHT_ARROW)) {
			selected.setOffsetX(+keyboardSpeed);
			notifyListener();
			reselect();
		}
	}
	
	private void notifyListener() {
		if(listener == null)
			return;
		
		int w = (int)(selected.utilWidth()+selected.getScaleX());
		int h = (int)(selected.utilHeight()+selected.getScaleY());
		listener.onResize(selected.getX(), selected.getY(), w, h);		
	}

	public ResizerListener getListener() {
		return listener;
	}

	public void setListener(ResizerListener listener) {
		this.listener = listener;
	}

	public List<Layer> getLayers() {
		return layers;
	}

	public void setLayers(List<Layer> layers) {
		this.layers = layers;
	}
		
}
