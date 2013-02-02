package br.com.etyllica.gui;

import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.event.GUIAction;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyboardEvent;
import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.gui.label.TextLabel;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public abstract class Spinner<T extends Number> extends GUIComponent{

	private Button plus;
	private Button minus;
	private Label resultLabel;
	private Panel resultPanel;
	
	protected T value;
	protected T step;
	protected T maxValue;
	protected T minValue;
	
	public Spinner(int x, int y, int w, int h){
		
		resultPanel = new Panel(x, y, w, h);
		add(resultPanel);
		
		//TODO levar em consideracao o fontSize
		resultLabel = new TextLabel(x+2,y+2+h/2,"0");
		add(resultLabel);
		
		int buttonWidth = w/6;
		int miniBorder = 1;
		
		plus = new Button(x+w-buttonWidth-miniBorder, y+miniBorder, buttonWidth, h/2-miniBorder-1);
		plus.setLabel(new TextLabel("+"));
		plus.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new GUIAction(this, "addReload"));
		add(plus);
		
		minus = new Button(x+w-buttonWidth-miniBorder, y+h/2+miniBorder, buttonWidth, h/2-miniBorder);
		minus.setLabel(new TextLabel("-"));
		minus.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new GUIAction(this, "subReload"));
		
		add(minus);
				
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
	
	private void reload(){
		resultLabel.setText(value.toString());
	}
	
	public abstract void add();
	public abstract void subtract();

	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		return GUIEvent.NONE;
	}
	
	@Override
	public GUIEvent updateKeyboard(KeyboardEvent event) {
		return GUIEvent.NONE;
	}

	@Override
	public void update(GUIEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void draw(Grafico g) {
		// TODO Auto-generated method stub
		
	}
	
	public void setValue(T value){
		this.value = value;
		resultLabel.setText(value.toString());
	}
	
	public T getValue() {
		return value;
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
