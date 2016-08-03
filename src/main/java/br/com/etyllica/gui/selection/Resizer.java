package br.com.etyllica.gui.selection;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import br.com.etyllica.awt.stroke.DashedStroke;
import br.com.etyllica.core.collision.CollisionDetector;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.MouseButton;
import br.com.etyllica.core.event.MouseState;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.core.input.mouse.MouseStateChanger;
import br.com.etyllica.layer.GeometricLayer;
import br.com.etyllica.layer.Layer;

public class Resizer<T extends Layer> {

	ResizerEvent lastEvent = null;
	Layer copy = new Layer();
	
	public static final int UNKNOWN = -1;
	
	private int count = 0;
	protected Map<Integer, T> layers = new HashMap<Integer, T>();
	
	protected int selectedIndex = UNKNOWN;
	private Layer selected;
	protected Layer overlay = new Layer();
	
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

	private static final int SHIFT_SPEED = 10;
	private static final int NORMAL_SPEED = 1;
	
	private int keyboardSpeed = 1;
	private int speedFactor = NORMAL_SPEED;

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

	public void refresh() {
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

	public void draw(Graphics g) {

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

	private void drawScaledRect(Graphics g, Layer layer) {
		int sw = (int)(layer.utilWidth()*layer.getScaleX());
		int sh = (int)(layer.utilHeight()*layer.getScaleY());
				
		int offsetX = (int)(layer.utilWidth()*(1-layer.getScaleX()))/2;
		int offsetY = (int)(layer.utilHeight()*(1-layer.getScaleY()))/2;
		
		g.drawRect(layer.getX()+offsetX, layer.getY()+offsetY, sw, sh);
	}
	
	private void fillScaledRect(Graphics g, Layer layer) {
		int sw = (int)(layer.utilWidth()*layer.getScaleX());
		int sh = (int)(layer.utilHeight()*layer.getScaleY());
		
		int offsetX = (int)(layer.utilWidth()*(1-layer.getScaleX()))/2;
		int offsetY = (int)(layer.utilHeight()*(1-layer.getScaleY()))/2;
		
		g.fillRect(layer.getX()+offsetX, layer.getY()+offsetY, sw, sh);
	}
		
	private void drawOverlay(Graphics g) {

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
			checkMouseOver(mx, my);
		}
		
		if(event.isButtonDown(MouseButton.MOUSE_BUTTON_LEFT)) {
			if(!isSelected()) {
				checkSelection(mx, my);
			} else if(!isDragged()) {
				deselect();
			}
		}
		
		if (selected == null)
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

		if (event.isButtonUp(MouseButton.MOUSE_BUTTON_LEFT)) {
			dragged = false;
			if(lastEvent != null) {
				notifyListener(lastEvent);
				lastEvent = null;	
			}
		} else if (dragged && event.isDraggedButton(MouseButton.MOUSE_BUTTON_LEFT)) {
			resizeEvent(lastIndex, event);
			//notifyListener();
			refresh();
		}
		
		if (!changed && event.isClicked(MouseButton.MOUSE_BUTTON_LEFT)) {
			deselect();
		}

		if (!changed) {
			changer.changeMouseState(MouseState.NORMAL);
		}

	}

	protected void checkMouseOver(int mx, int my) {
		for(Layer component: layers.values()) {
			if(component.onMouse(mx, my)) {
				overlay.setVisible(true);
				overlay.copy(component);
				break;
			}
		}
	}

	protected void checkSelection(int mx, int my) {
		selectedIndex = UNKNOWN;
		for (Entry<Integer, T> entry: layers.entrySet()) {
			T component = entry.getValue();
			if(component.onMouse(mx, my)) {
				overlay.copy(component);
				selectedIndex = entry.getKey();
				select(component);
				overlay.setVisible(false);
				
				break;
			}
		}
	}

	private void resizeEvent(int index, PointerEvent event) {
		
		lastEvent = ResizerEvent.SCALE;
		
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
			lastEvent = ResizerEvent.MOVE;
			break;
		}
	}

	private void handleDragEvent(PointerEvent event) {

		if (!dragged && event.isDraggedButton(MouseButton.MOUSE_BUTTON_LEFT)) {
			setInitialValues();
			dragged = true;
			copy.copy(selected);
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

		if(event.isAnyKeyDown(KeyEvent.VK_SHIFT_LEFT, KeyEvent.VK_SHIFT_RIGHT)) {
			speedFactor = SHIFT_SPEED;
		} else if(event.isAnyKeyUp(KeyEvent.VK_SHIFT_LEFT, KeyEvent.VK_SHIFT_RIGHT)) {
			speedFactor = NORMAL_SPEED;
		}
		
		if(event.isKeyDown(KeyEvent.VK_UP_ARROW)) {
			selected.setOffsetY(-speed());
			notifyListener(ResizerEvent.MOVE);
			refresh();
		} else if(event.isKeyDown(KeyEvent.VK_DOWN_ARROW)) {
			selected.setOffsetY(+speed());
			notifyListener(ResizerEvent.MOVE);
			refresh();
		}

		if(event.isKeyDown(KeyEvent.VK_LEFT_ARROW)) {
			selected.setOffsetX(-speed());
			notifyListener(ResizerEvent.MOVE);
			refresh();
		} else if(event.isKeyDown(KeyEvent.VK_RIGHT_ARROW)) {
			selected.setOffsetX(+speed());
			notifyListener(ResizerEvent.MOVE);
			refresh();
		}
	}

	protected int speed() {
		return keyboardSpeed*speedFactor;
	}
	
	private void notifyListener(ResizerEvent event) {
		if(listener == null)
			return;
		
		listener.onResize(event, selectedIndex, selected, copy);		
	}

	public ResizerListener getListener() {
		return listener;
	}

	public void setListener(ResizerListener listener) {
		this.listener = listener;
	}

	public Collection<T> getLayers() {
		return layers.values();
	}

	public void setLayers(List<T> layers) {
		for(T layer:layers) {
			addLayer(layer);
		}
	}

	private Integer generateId() {
		count++;
		return count;
	}
	
	public int getId(T layer) {
		for(Entry<Integer, T> entry:layers.entrySet()) {
			if(entry.getValue().equals(layer)) {
				return entry.getKey();
			}
		}
		return UNKNOWN;
	}

	public int addLayer(T layer) {
		int id = generateId();
		layers.put(id, layer);
		
		return id;
	}

	public int getSelectedIndex() {
		return selectedIndex;
	}

	public void removeLayer(int index) {
		if (selectedIndex == index) {
			deselect();
		}
		layers.remove(index);
	}
}
