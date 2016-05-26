package br.com.etyllica.gui;

import java.util.Collection;
import java.util.List;

import br.com.etyllica.core.event.Action;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.gui.base.BaseCheckBox;
import br.com.etyllica.theme.ThemeManager;

public class CheckBox extends View {
	
	private BaseCheckBox checkbox;
	
	public CheckBox(int x, int y, int w, int h) {
		super();
		
		this.checkbox = ThemeManager.getInstance().getTheme().createCheckBox(x, y, w, h);
	}

	public int getRoundness() {
		return checkbox.getRoundness();
	}

	@Override
	public int getX() {
		return checkbox.getX();
	}

	@Override
	public int getY() {
		return checkbox.getY();
	}

	@Override
	public int getW() {
		return checkbox.getW();
	}

	@Override
	public int getH() {
		return checkbox.getH();
	}
	
	@Override
	public void draw(Graphics g) {
		checkbox.draw(g);
	}

	@Override
	public GUIEvent getLastEvent() {
		return checkbox.getLastEvent();
	}

	@Override
	public boolean isMouseOver() {
		return checkbox.isMouseOver();
	}

	@Override
	public boolean isVisible() {
		return checkbox.isVisible();
	}

	public boolean isOnFocus() {
		return checkbox.isOnFocus();
	}
	
	public boolean isDisabled() {
		return checkbox.isDisabled();
	}

	public void setDisabled(boolean disabled) {
		checkbox.setDisabled(disabled);
	}

	@Override
	public List<Action> getActions() {
		return checkbox.getActions();
	}

	@Override
	public void hide() {
		checkbox.hide();
	}

	@Override
	public List<View> getViews() {
		return checkbox.getViews();
	}

	@Override
	public boolean colideRect(int bx, int by, int bw, int bh) {
		return checkbox.colideRect(bx, by, bw, bh);
	}

	@Override
	public void clearComponents() {
		checkbox.clearComponents();
	}

	@Override
	public boolean colideCircleCircle(int bx, int by, int bw, int bh) {
		return checkbox.colideCircleCircle(bx, by, bw, bh);
	}

	@Override
	public void add(View component) {
		checkbox.add(component);
	}

	@Override
	public void addAll(Collection<? extends View> components) {
		checkbox.addAll(components);
	}

	@Override
	public boolean colideCirclePoint(int px, int py) {
		return checkbox.colideCirclePoint(px, py);
	}

	@Override
	public boolean onMouse(PointerEvent event) {
		return checkbox.onMouse(event);
	}

	@Override
	public boolean onMouse(int mx, int my) {
		return checkbox.onMouse(mx, my);
	}

	@Override
	public void executeAction(GUIEvent event) {
		checkbox.executeAction(event);
	}

	@Override
	public void addAction(GUIEvent event, Action action) {
		checkbox.addAction(event, action);
	}

	@Override
	public boolean equals(Object obj) {
		return checkbox.equals(obj);
	}

	@Override
	public View findNext() {
		return checkbox.findNext();
	}

	public String getAlt() {
		return checkbox.getAlt();
	}

	public Label getLabel() {
		return checkbox.getLabel();
	}

	@Override
	public int hashCode() {
		return checkbox.hashCode();
	}

	public void setRoundness(int roundness) {
		checkbox.setRoundness(roundness);
	}

	@Override
	public void setX(int x) {
		checkbox.setX(x);
	}

	@Override
	public void setY(int y) {
		checkbox.setY(y);
	}

	@Override
	public void setW(int w) {
		checkbox.setW(w);
	}

	@Override
	public void setH(int h) {
		checkbox.setH(h);
	}

	@Override
	public void setBounds(int x, int y, int w, int h) {
		checkbox.setBounds(x, y, w, h);
	}

	@Override
	public void setCoordinates(int x, int y) {
		checkbox.setCoordinates(x, y);
	}

	@Override
	public void setOffsetX(int offsetX) {
		checkbox.setOffsetX(offsetX);
	}

	@Override
	public void setOffsetY(int offsetY) {
		checkbox.setOffsetY(offsetY);
	}

	@Override
	public void setLastEvent(GUIEvent lastEvent) {
		checkbox.setLastEvent(lastEvent);
	}

	@Override
	public void update(GUIEvent event) {
		checkbox.update(event);
	}

	@Override
	public void setLocation(int offsetX, int offsetY) {
		checkbox.setLocation(offsetX, offsetY);
	}

	@Override
	public void setVisible(boolean visible) {
		checkbox.setVisible(visible);
	}

	@Override
	public void setMouseOver(boolean mouseOver) {
		checkbox.setMouseOver(mouseOver);
	}

	@Override
	public void show() {
		checkbox.show();
	}

	@Override
	public void setOnFocus(boolean focus) {
		checkbox.setOnFocus(focus);
	}

	@Override
	public void setActions(List<Action> actions) {
		checkbox.setActions(actions);
	}

	@Override
	public void swapVisible() {
		checkbox.swapVisible();
	}

	@Override
	public void remove(View component) {
		checkbox.remove(component);
	}

	@Override
	public void removeAll(Collection<? extends View> components) {
		checkbox.removeAll(components);
	}

	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		return checkbox.updateMouse(event);
	}

	@Override
	public void translateComponents(int x, int y) {
		checkbox.translateComponents(x, y);
	}
	
	public void setAlt(String alt) {
		checkbox.setAlt(alt);
	}

	public void setLabel(Label label) {
		checkbox.setLabel(label);
	}

	public void setCenterLabel(Label label) {
		checkbox.setCenterLabel(label);
	}

	@Override
	public String toString() {
		return checkbox.toString();
	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		return checkbox.updateKeyboard(event);
	}

	public void setChecked(boolean checked) {
		checkbox.setChecked(checked);
	}	

}
