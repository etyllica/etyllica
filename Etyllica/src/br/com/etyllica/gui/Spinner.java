package br.com.etyllica.gui;

import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.event.Action;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.video.Graphic;
import br.com.etyllica.gui.button.DefaultButton;
import br.com.etyllica.gui.label.TextLabel;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public abstract class Spinner<T extends Number> extends View{

	private DefaultButton plus;
	private DefaultButton minus;
	private TextLabel resultLabel;
	private Panel resultPanel;
	
	protected T value;
	protected T step;
	protected T maxValue;
	protected T minValue;
	
	public Spinner(float x, float y, float w, float h){
		
		resultPanel = new Panel(x, y, w, h);
		add(resultPanel);
		
		//TODO levar em consideracao o fontSize
		resultLabel = new TextLabel(x+10,y+2+h/2,"0");
		add(resultLabel);
		
		float buttonWidth = w/6;
		int miniBorder = 1;
		
		plus = new DefaultButton(x+w-buttonWidth-miniBorder, y+miniBorder, buttonWidth, h/2-miniBorder-1);
		plus.setLabel(new TextLabel("+"));
		plus.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new Action(this, "addReload"));
		add(plus);
		
		minus = new DefaultButton(x+w-buttonWidth-miniBorder, y+h/2+miniBorder, buttonWidth, h/2-miniBorder);
		minus.setLabel(new TextLabel("-"));
		minus.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new Action(this, "subReload"));		
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
	public GUIEvent updateKeyboard(KeyEvent event) {
		return GUIEvent.NONE;
	}

	@Override
	public void update(GUIEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void draw(Graphic g) {
		// TODO Auto-generated method stub
		
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
