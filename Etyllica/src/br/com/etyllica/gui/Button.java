package br.com.etyllica.gui;

import java.util.Collection;
import java.util.List;

import br.com.etyllica.core.Configuration;
import br.com.etyllica.core.event.GUIAction;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.video.Graphic;
import br.com.etyllica.gui.button.DefaultButton;

public class Button extends GUIComponent{
	
	private DefaultButton button;
	
	public Button(int x, int y, int w, int h){
		button = Configuration.getInstance().getTheme().createButton(x, y, w, h);
	}

	public int getRoundness() {
		return button.getRoundness();
	}

	public int getX() {
		return button.getX();
	}

	public int getY() {
		return button.getY();
	}

	public int getW() {
		return button.getW();
	}

	public int getH() {
		return button.getH();
	}
	
	public void draw(Graphic g) {
		button.draw(g);
	}

	public GUIEvent getLastEvent() {
		return button.getLastEvent();
	}

	public boolean isMouseOver() {
		return button.isMouseOver();
	}

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

	public List<GUIAction> getActions() {
		return button.getActions();
	}

	public void hide() {
		button.hide();
	}

	public List<GUIComponent> getComponents() {
		return button.getComponents();
	}

	public boolean colideRect(int bx, int by, int bw, int bh) {
		return button.colideRect(bx, by, bw, bh);
	}

	public void clearComponents() {
		button.clearComponents();
	}

	public boolean colideCircleCircle(int bx, int by, int bw, int bh) {
		return button.colideCircleCircle(bx, by, bw, bh);
	}

	public void add(GUIComponent component) {
		button.add(component);
	}

	public void addAll(Collection<? extends GUIComponent> components) {
		button.addAll(components);
	}

	public boolean colideCirclePoint(int px, int py) {
		return button.colideCirclePoint(px, py);
	}

	public boolean onMouse(PointerEvent event) {
		return button.onMouse(event);
	}

	public boolean onMouse(int mx, int my) {
		return button.onMouse(mx, my);
	}

	public void executeAction(GUIEvent event) {
		button.executeAction(event);
	}

	public void addAction(GUIEvent event, GUIAction action) {
		button.addAction(event, action);
	}

	public boolean equals(Object obj) {
		return button.equals(obj);
	}

	public GUIComponent findNext() {
		return button.findNext();
	}

	public String getAlt() {
		return button.getAlt();
	}

	public Label getLabel() {
		return button.getLabel();
	}

	public int hashCode() {
		return button.hashCode();
	}

	public void setRoundness(int roundness) {
		button.setRoundness(roundness);
	}

	public void setX(int x) {
		button.setX(x);
	}

	public void setY(int y) {
		button.setY(y);
	}

	public void setW(int w) {
		button.setW(w);
	}

	public void setH(int h) {
		button.setH(h);
	}

	public void setBounds(int x, int y, int w, int h) {
		button.setBounds(x, y, w, h);
	}

	public void setCoordinates(int x, int y) {
		button.setCoordinates(x, y);
	}

	public void setOffsetX(int offsetX) {
		button.setOffsetX(offsetX);
	}

	public void setOffsetY(int offsetY) {
		button.setOffsetY(offsetY);
	}

	public void setLastEvent(GUIEvent lastEvent) {
		button.setLastEvent(lastEvent);
	}

	public void update(GUIEvent event) {
		button.update(event);
	}

	public void setOffset(int offsetX, int offsetY) {
		button.setOffset(offsetX, offsetY);
	}

	public void setVisible(boolean visible) {
		button.setVisible(visible);
	}

	public void setMouseOver(boolean mouseOver) {
		button.setMouseOver(mouseOver);
	}

	public void show() {
		button.show();
	}

	public void setOnFocus(boolean focus) {
		button.setOnFocus(focus);
	}

	public void setActions(List<GUIAction> actions) {
		button.setActions(actions);
	}

	public void swapVisible() {
		button.swapVisible();
	}

	public void remove(GUIComponent component) {
		button.remove(component);
	}

	public void removeAll(Collection<? extends GUIComponent> components) {
		button.removeAll(components);
	}

	public GUIEvent updateMouse(PointerEvent event) {
		return button.updateMouse(event);
	}

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

	public String toString() {
		return button.toString();
	}

	public GUIEvent updateKeyboard(KeyEvent event) {
		return button.updateKeyboard(event);
	}	

}
