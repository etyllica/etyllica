package br.com.etyllica.gui;

import java.util.Collection;
import java.util.List;

import br.com.etyllica.core.event.Action;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.gui.base.BaseButton;
import br.com.etyllica.theme.ThemeManager;

public class Button extends View {
	
	private BaseButton button;
	
	public Button(int x, int y, int w, int h) {
		super();
		
		this.button = ThemeManager.getInstance().getTheme().createButton(x, y, w, h);
	}

	public int getRoundness() {
		return button.getRoundness();
	}

	@Override
	public int getX() {
		return button.getX();
	}

	@Override
	public int getY() {
		return button.getY();
	}

	@Override
	public int getW() {
		return button.getW();
	}

	@Override
	public int getH() {
		return button.getH();
	}
	
	@Override
	public void draw(Graphic g) {
		button.draw(g);
	}

	@Override
	public GUIEvent getLastEvent() {
		return button.getLastEvent();
	}

	@Override
	public boolean isMouseOver() {
		return button.isMouseOver();
	}

	@Override
	public boolean isVisible() {
		return button.isVisible();
	}

	public boolean isOnFocus() {
		return button.isOnFocus();
	}
	
	public boolean isDisabled() {
		return button.isDisabled();
	}

	public void setDisabled(boolean disabled) {
		button.setDisabled(disabled);
	}

	@Override
	public List<Action> getActions() {
		return button.getActions();
	}

	@Override
	public void hide() {
		button.hide();
	}

	@Override
	public List<View> getViews() {
		return button.getViews();
	}

	@Override
	public boolean colideRect(int bx, int by, int bw, int bh) {
		return button.colideRect(bx, by, bw, bh);
	}

	@Override
	public void clearComponents() {
		button.clearComponents();
	}

	@Override
	public boolean colideCircleCircle(int bx, int by, int bw, int bh) {
		return button.colideCircleCircle(bx, by, bw, bh);
	}

	@Override
	public void add(View component) {
		button.add(component);
	}

	@Override
	public void addAll(Collection<? extends View> components) {
		button.addAll(components);
	}

	@Override
	public boolean colideCirclePoint(int px, int py) {
		return button.colideCirclePoint(px, py);
	}

	@Override
	public boolean onMouse(PointerEvent event) {
		return button.onMouse(event);
	}

	@Override
	public boolean onMouse(int mx, int my) {
		return button.onMouse(mx, my);
	}

	@Override
	public void executeAction(GUIEvent event) {
		button.executeAction(event);
	}

	@Override
	public void addAction(GUIEvent event, Action action) {
		button.addAction(event, action);
	}

	@Override
	public boolean equals(Object obj) {
		return button.equals(obj);
	}

	@Override
	public View findNext() {
		return button.findNext();
	}

	public String getAlt() {
		return button.getAlt();
	}

	public Label getLabel() {
		return button.getLabel();
	}

	@Override
	public int hashCode() {
		return button.hashCode();
	}

	public void setRoundness(int roundness) {
		button.setRoundness(roundness);
	}

	@Override
	public void setX(int x) {
		button.setX(x);
	}

	@Override
	public void setY(int y) {
		button.setY(y);
	}

	@Override
	public void setW(int w) {
		button.setW(w);
	}

	@Override
	public void setH(int h) {
		button.setH(h);
	}

	@Override
	public void setBounds(int x, int y, int w, int h) {
		button.setBounds(x, y, w, h);
	}

	@Override
	public void setCoordinates(int x, int y) {
		button.setCoordinates(x, y);
	}

	@Override
	public void setOffsetX(int offsetX) {
		button.setOffsetX(offsetX);
	}

	@Override
	public void setOffsetY(int offsetY) {
		button.setOffsetY(offsetY);
	}

	@Override
	public void setLastEvent(GUIEvent lastEvent) {
		button.setLastEvent(lastEvent);
	}

	@Override
	public void update(GUIEvent event) {
		button.update(event);
	}

	@Override
	public void setLocation(int offsetX, int offsetY) {
		button.setLocation(offsetX, offsetY);
	}

	@Override
	public void setVisible(boolean visible) {
		button.setVisible(visible);
	}

	@Override
	public void setMouseOver(boolean mouseOver) {
		button.setMouseOver(mouseOver);
	}

	@Override
	public void show() {
		button.show();
	}

	@Override
	public void setOnFocus(boolean focus) {
		button.setOnFocus(focus);
	}

	@Override
	public void setActions(List<Action> actions) {
		button.setActions(actions);
	}

	@Override
	public void swapVisible() {
		button.swapVisible();
	}

	@Override
	public void remove(View component) {
		button.remove(component);
	}

	@Override
	public void removeAll(Collection<? extends View> components) {
		button.removeAll(components);
	}

	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		return button.updateMouse(event);
	}

	@Override
	public void translateComponents(int x, int y) {
		button.translateComponents(x, y);
	}
	
	public void setAlt(String alt) {
		button.setAlt(alt);
	}

	public void setLabel(Label label) {
		button.setLabel(label);
	}

	public void setCenterLabel(Label label) {
		button.setCenterLabel(label);
	}

	@Override
	public String toString() {
		return button.toString();
	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		return button.updateKeyboard(event);
	}	

}
