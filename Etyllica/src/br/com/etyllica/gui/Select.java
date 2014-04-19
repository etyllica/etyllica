package br.com.etyllica.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import br.com.etyllica.core.event.Action;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.gui.button.DefaultButton;
import br.com.etyllica.gui.icon.DownArrow;
import br.com.etyllica.gui.list.Option;

public class Select extends View{
		
	private List<Option> options = new ArrayList<Option>();
	
	private DefaultButton button;
	
	private int selectedOption = 0;
	
	private boolean showOptions = false;
	
	public Select(int x, int y, int w, int h){
		super(x,y,w,h);
		
		int buttonSize = h;
		
		//TODO ButtonDownArrow
		button = new DefaultButton(w-buttonSize,0,buttonSize,buttonSize);
		
		DownArrow arrow = new DownArrow(x+buttonSize/4, y+buttonSize/5, buttonSize/2);
		button.setLabel(arrow);
		button.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new Action(this, "swapShowOptions"));
		
		add(button);
		
	}
	
	public void swapShowOptions(){
		showOptions = !showOptions;
	}
	
	public void addOption(Option option){
		options.add(option);
		//optionsPanel.addOption(option);
	}
	
	
	@Override
	public void update(GUIEvent event){
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphic g){
		
		g.setColor(Color.BLACK);
		g.drawRect(x, y, w, h);
		
		int fontSize = g.getFont().getSize()+1;
		int textOffset = 2;
		
		g.write(x+textOffset, y+fontSize, options.get(selectedOption).getLabel());
		
		if(showOptions){
		
			float initialY = y+h;
			float finalY = h*(options.size()+1);
			
			g.setColor(Color.WHITE);
			g.fillRect(x, initialY, w, finalY);
			
			g.setColor(Color.BLACK);
			int i=0;			
			
			for(Option option: options){
				g.write(x+textOffset, y+h*(2+i), option.getLabel());
				
				i++;
			}

			g.setColor(Color.BLACK);
			g.drawRect(x, initialY, w, finalY);
			
		}
						
	}

	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent	.NONE;
	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent	.NONE;
	}

	

}