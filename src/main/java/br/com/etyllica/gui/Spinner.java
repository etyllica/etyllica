package br.com.etyllica.gui;

import br.com.etyllica.core.event.Action;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.gui.factory.DefaultButton;
import br.com.etyllica.gui.label.TextLabel;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public abstract class Spinner<T extends Number> extends View {

	protected DefaultButton plus;
	protected DefaultButton minus;
	protected TextLabel resultLabel;
	protected Panel panel;

	protected T value;
	protected T step;
	protected T maxValue;
	protected T minValue;

	public Spinner(int x, int y, int w, int h) {
		super(x, y, w, h);
		panel = new Panel(x, y, w, h);

		//TODO change size based on fontSize
		resultLabel = new TextLabel(x+10,y+2+h/2,"0");		

		configureButtons();
	}

	protected void configureButtons() {

		int buttonWidth = w/6;
		int miniBorder = 1;

		plus = new DefaultButton(x+w-buttonWidth-miniBorder, y+miniBorder, buttonWidth, h/2-miniBorder-1);
		plus.setLabel(new TextLabel("+"));
		plus.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new Action(this, "addReload"));

		minus = new DefaultButton(x+w-buttonWidth-miniBorder, y+h/2+miniBorder, buttonWidth, h/2-miniBorder);
		minus.setLabel(new TextLabel("-"));
		minus.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new Action(this, "subReload"));		
	}

	@Override
	public boolean onMouse(PointerEvent event) {
		return panel.onMouse(event);
	}

	//Should be private
	public void addReload(){
		add();		
		reload();
	}

	//Should be private
	public void subReload(){
		subtract();
		reload();
	}

	protected void reload() {
		String result = value.toString();
		resultLabel.setText(result);
	}

	public abstract void add();
	public abstract void subtract();

	@Override
	public GUIEvent updateMouse(PointerEvent event) {

		plus.safeUpdateMouse(event);
		minus.safeUpdateMouse(event);			

		return GUIEvent.NONE;
	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		return GUIEvent.NONE;
	}

	@Override
	public void update(GUIEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void draw(Graphic g) {
		drawPanel(g);
		drawResult(g);
		drawButtons(g);
	}

	protected void drawPanel(Graphic g) {
		panel.draw(g);
	}

	protected void drawResult(Graphic g) {
		resultLabel.draw(g);		
	}

	protected void drawButtons(Graphic g) {
		plus.draw(g);
		minus.draw(g);
	}

	public void setValue(T value){
		this.value = value;
		resultLabel.setText(value.toString());
	}

	public T getValue() {
		return this.value;
	}

	public T getStep() {
		return step;
	}

	public void setStep(T step) {
		this.step = step;
	}

	public T getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(T maxValue) {
		this.maxValue = maxValue;
	}

	public T getMinValue() {
		return minValue;
	}

	public void setMinValue(T minValue) {
		this.minValue = minValue;
	}

}
