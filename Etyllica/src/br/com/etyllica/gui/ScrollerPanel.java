package br.com.etyllica.gui;

import java.awt.Color;
import java.awt.image.BufferedImage;

import br.com.etyllica.core.event.Action;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.input.mouse.MouseButton;
import br.com.etyllica.core.video.Graphic;
import br.com.etyllica.gui.button.DefaultButton;
import br.com.etyllica.gui.icon.DownArrow;
import br.com.etyllica.gui.icon.UpArrow;
import br.com.etyllica.gui.panel.ScrollBackground;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class ScrollerPanel extends View{

	private float lastComponentH = 0;
	private View component;

	private int buttonSize = 20;
	private int scrollAmount = 10;
	private float scrollFactor = 1;
	private float offset = 0;
	private float knobPosition = 0;

	private DefaultButton upButton;
	private DefaultButton downButton;
	private DefaultButton knob;
	private ScrollBackground track;

	public ScrollerPanel(int x, int y, int w, int h) {
		super(x, y, w, h);

		upButton = new DefaultButton(w-buttonSize,0,buttonSize,buttonSize);
		upButton.setLabel(new UpArrow(x+buttonSize/4, y+buttonSize/5, buttonSize/2));
		upButton.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new Action(this, "scrollUp"));
		upButton.setVisible(false);		
		
		downButton = new DefaultButton(w-buttonSize,h-buttonSize,buttonSize,buttonSize);
		downButton.setLabel(new DownArrow(x+buttonSize/4, y+buttonSize/5, buttonSize/2));
		downButton.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new Action(this, "scrollDown"));
		downButton.setVisible(false);
		
		track = new ScrollBackground(w-buttonSize, buttonSize, buttonSize, h-buttonSize*2);
		track.setVisible(false);
		
		add(track);

		add(upButton);
		add(downButton);
	}

	@Override
	public void draw(Graphic g){

		g.setColor(Color.WHITE);

		g.fillRect(x,y,w,h);

		//TODO Fix this
		BufferedImage back = g.getBimg();
		//g.setBufferedImage(back.getSubimage(x, y, w, h));
		
		if(component!=null){
			
			if(lastComponentH!=component.getH()){
				resetScroll();
				lastComponentH = component.getH();
			}
			
			//component.draw(g);
			
			
			component.draw(g);
			
		}
		
		//g.setBufferedImage(back);

	}

	@Override
	public void update(GUIEvent event){

	}

	@Override
	public GUIEvent updateMouse(PointerEvent event){

		if(mouseOver){

			if(event.onButtonDown(MouseButton.MOUSE_WHEEL_DOWN)){

				for(int i=0;i<event.getAmount();i++){
					scrollDown();
				}
			}

			if(event.onButtonDown(MouseButton.MOUSE_WHEEL_UP)){

				for(int i=event.getAmount();i<0;i++){
					scrollUp();
				}
			}


			if(knob.isMouseOver()){

				if(event.onButtonDown(MouseButton.MOUSE_BUTTON_LEFT)){
					//TODO Mouse dragged with knob move scroll
				}

			}
		}


		return GUIEvent.NONE;

	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {

		if(event.isKeyDown(KeyEvent.TSK_TAB)){

			return GUIEvent.NEXT_COMPONENT;

		}

		return GUIEvent.NONE;
	}

	public void setComponent(View component){
		this.component = component;
		lastComponentH = component.getH();

		knobPosition = buttonSize;
		
		resetScroll();
	}

	private void resetScroll(){
		
		boolean needScroll = false; 
		
		if(component.getH()>h){
			scrollFactor = (float)((float)h/(float)component.getH());
			needScroll = true;
		}

		offset = scrollAmount*scrollFactor;
		
		remove(knob);
		knob = new DefaultButton(w-buttonSize,(int)(knobPosition), buttonSize,((int)(h*scrollFactor))-buttonSize*2+1);
		knob.setVisible(false);
		add(knob);
		
		
		
		if(needScroll){
			showButtons();
		}
			
		
	}
	
	private void showButtons(){
		track.setVisible(true);
		upButton.setVisible(true);
		downButton.setVisible(true);
		knob.setVisible(true);
	}

	public void scrollDown(){

		float panelDif = h-component.getH();

		if(component.getY()-panelDif>0){

			component.setOffsetY(-scrollAmount);

			knobPosition+=offset;

			knob.setY((int)knobPosition);

		}

	}

	public void scrollUp(){

		if(component.getY()<0){

			component.setOffsetY(+scrollAmount);

			knobPosition-=offset;

			knob.setY((int)knobPosition);

		}

	}

}