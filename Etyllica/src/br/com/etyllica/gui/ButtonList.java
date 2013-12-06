package br.com.etyllica.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.video.Graphic;
import br.com.etyllica.gui.button.DefaultButton;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class ButtonList extends GUIComponent{
	
	List<DefaultButton> buttons = new ArrayList<DefaultButton>();
	
	public ButtonList(int x, int y, int w, int h){
		super(x, y, w, h);
	}

	@Override
	public GUIEvent updateMouse(PointerEvent event){
		
		for(DefaultButton button: buttons){
			button.updateMouse(event);
		}
		
		return GUIEvent.NONE;
	}

	@Override
	public void update(GUIEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	//TODO Desenha o botao ou o scroll 
	public void draw(Graphic g){
		
		//BufferedImage bimg = g.getBimg();
		
		//g.setBimg(bimg.getSubimage(0, y, w, h));
		
		for(DefaultButton button: buttons){
			if(button.getY()<h-100){
				button.draw(g);
			}else{
				break;
			}
		}
		
		//g.setBimg(bimg);
		
		drawScroll(g);
		
		
	}
	
	private void drawScroll(Graphic g){
		g.setColor(Color.GREEN);
		g.fillRect(x+w-10,y,5,h);
		
	}
	
	public void add(DefaultButton button){
		buttons.add(button);
	}
	
	public void clear(){
		buttons.clear();
	}
	
	@Override
	public void setOffset(int offsetX, int offsetY){
		for(DefaultButton button: buttons){
			button.setOffset(offsetX, offsetY);
		}
		
		this.x += offsetX;
		this.y += offsetY;
	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent	.NONE;
	}

}