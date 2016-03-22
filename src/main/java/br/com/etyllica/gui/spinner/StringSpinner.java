package br.com.etyllica.gui.spinner;

import java.util.ArrayList;
import java.util.List;

import br.com.etyllica.core.event.Action;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.gui.Panel;
import br.com.etyllica.gui.View;
import br.com.etyllica.gui.base.BaseButton;
import br.com.etyllica.gui.label.TextLabel;
import br.com.etyllica.gui.spinner.composer.SpinnerComposer;
import br.com.etyllica.gui.spinner.composer.StringHorizontalComposer;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class StringSpinner extends View {

	protected SpinnerComposer composer;
	
	protected BaseButton next;
	protected BaseButton previous;
	protected TextLabel resultLabel;
	protected Panel panel;
	
	protected List<String> options = new ArrayList<String>();
	
	private int currentItem = 0;

	public StringSpinner(int x, int y, int w, int h) {
		super(x, y, w, h);
		
		panel = new Panel(x, y, w, h);

		//TODO change size based on fontSize
		resultLabel = new TextLabel(x,y+2+h/2,w);
		

		composer = buildComposer();
		
		configureButtons();
		
		//reload();
	}
	
	protected SpinnerComposer buildComposer() {
		return new StringHorizontalComposer(x, y, w, h);
	}
	
	private void configureButtons() {
		
		composer.setBorder(1);
		composer.setButtonWidth(w/6);
		
		next = composer.buildPlusButton(x, y, w, h);
		next.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new Action(this, "nextItem"));
		
		previous = composer.buildMinusButton(x, y, w, h);
		previous.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new Action(this, "previousItem"));		
	}
	
	public void nextItem() {
		if(options.isEmpty())
			return;
		
		currentItem++;
		currentItem%=options.size();
		reload();
	}

	//Should be private
	public void previousItem() {
		if(options.isEmpty())
			return;
		
		currentItem+=options.size()-1;
		currentItem%=options.size();
		reload();
	}
	
	protected void reload() {
		if(options.isEmpty())
			return;
		
		String result = options.get(currentItem);
		resultLabel.setText(result);
	}
	
	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		next.safeUpdateMouse(event);

		previous.safeUpdateMouse(event);	

		return GUIEvent.NONE;
	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		return GUIEvent.NONE;
	}
	
	public void mouseOut() {
		super.mouseOut();
		
		next.setMouseOver(false);
		previous.setMouseOver(false);
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
		next.draw(g);
		previous.draw(g);
	}

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
		reload();
	}
	
}

