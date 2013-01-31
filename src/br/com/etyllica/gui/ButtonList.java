package br.com.etyllica.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import br.com.etyllica.core.control.HIDController;
import br.com.etyllica.core.event.Event;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyboardEvent;
import br.com.etyllica.core.video.Grafico;

public class ButtonList extends GUIComponent{
	
	List<Button> buttons = new ArrayList<Button>();
	
	public ButtonList(int x, int y, int w, int h){
		super(x, y, w, h);
	}

	@Override
	public GUIEvent updateMouse(Event event){
		
		for(Button button: buttons){
			button.updateMouse(event);
		}
		
		return GUIEvent.NONE;
	}

	@Override
	public void update(GUIEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	//TODO Desenha o botao ou o scroll 
	public void draw(Grafico g){
		
		//BufferedImage bimg = g.getBimg();
		
		//g.setBimg(bimg.getSubimage(0, y, w, h));
		
		for(Button button: buttons){
			if(button.getY()<h-100){
				button.draw(g);
			}else{
				break;
			}
		}
		
		//g.setBimg(bimg);
		
		drawScroll(g);
		
		
	}
	
	private void drawScroll(Grafico g){
		g.setColor(Color.GREEN);
		g.fillRect(x+w-10,y,5,h);
		
	}
	
	public void add(Button button){
		buttons.add(button);
	}
	
	public void clear(){
		buttons.clear();
	}
	
	@Override
	public void setOffset(int offsetX, int offsetY){
		for(Button button: buttons){
			button.setOffset(offsetX, offsetY);
		}
		
		this.x += offsetX;
		this.y += offsetY;
	}

}
