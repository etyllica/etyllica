package br.com.etyllica.gui;

import br.com.etyllica.core.event.Action;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.gui.base.BaseButton;
import br.com.etyllica.gui.label.TextLabel;
import br.com.etyllica.gui.listener.ValueListener;
import br.com.etyllica.gui.spinner.composer.SpinnerComposer;
import br.com.etyllica.gui.spinner.composer.VerticalComposer;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public abstract class Spinner<T extends Number> extends View {

	protected SpinnerComposer composer;
	
	protected BaseButton plus;
	protected BaseButton minus;
	protected TextLabel resultLabel;
	protected Panel panel;

	protected T value;
	protected T step;
	protected T maxValue;
	protected T minValue;

	protected ValueListener<T> listener;
	
	public Spinner(int x, int y, int w, int h) {
		super(x, y, w, h);
				
		panel = new Panel(x, y, w, h);

		//TODO change size based on fontSize
		resultLabel = new TextLabel(x+w/3,y+2+h/2,"0");

		composer = buildComposer();
		
		configureButtons();
	}
	
	protected SpinnerComposer buildComposer() {
		return new VerticalComposer(x, y, w, h);
	}
	
	private void configureButtons() {
		
		composer.setBorder(1);
		composer.setButtonWidth(w/6);
		
		plus = composer.buildPlusButton(x, y, w, h);
		plus.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new Action(this, "addReload"));
		
		minus = composer.buildMinusButton(x, y, w, h);
		minus.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new Action(this, "subReload"));		
	}

	//Should be private
	public void addReload() {
		add();		
		reload();
	}

	//Should be private
	public void subReload() {
		subtract();
		reload();
	}

	protected void reload() {
		if(listener!=null) {
			listener.onChange(value);
		}
		
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
	
	public void mouseOut() {
		super.mouseOut();
		
		plus.setMouseOver(false);
		minus.setMouseOver(false);
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
		reload();
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

	public ValueListener<T> getListener() {
		return listener;
	}

	public void setListener(ValueListener<T> listener) {
		this.listener = listener;
	}
	
}
