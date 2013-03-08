package br.com.etyllica.gui;

import java.awt.Color;
import java.awt.image.BufferedImage;

import br.com.etyllica.core.control.mouse.MouseButton;
import br.com.etyllica.core.event.GUIAction;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyboardEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.event.Tecla;
import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.gui.icon.DownArrow;
import br.com.etyllica.gui.icon.UpArrow;
import br.com.etyllica.gui.panel.ScrollBackground;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public class ScrollerPanel extends GUIComponent{

	private int lastComponentH = 0;
	private GUIComponent component;

	private int buttonSize = 20;
	private int scrollAmount = 10;
	private float scrollFactor = 1;
	private float offset = 0;
	private float knobPosition = 0;

	private Button upButton;
	private Button downButton;
	private Button knob;
	private ScrollBackground track;

	public ScrollerPanel(int x, int y, int w, int h) {
		super(x, y, w, h);

		upButton = new Button(w-buttonSize,0,buttonSize,buttonSize);
		upButton.setLabel(new UpArrow(x+buttonSize/4, y+buttonSize/5, buttonSize/2));
		upButton.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new GUIAction(this, "scrollUp"));
		upButton.setVisible(false);		
		
		downButton = new Button(w-buttonSize,h-buttonSize,buttonSize,buttonSize);
		downButton.setLabel(new DownArrow(x+buttonSize/4, y+buttonSize/5, buttonSize/2));
		downButton.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new GUIAction(this, "scrollDown"));
		downButton.setVisible(false);
		
		track = new ScrollBackground(w-buttonSize, buttonSize, buttonSize, h-buttonSize*2);
		track.setVisible(false);
		
		add(track);

		add(upButton);
		add(downButton);
	}

	@Override
	public void draw(Grafico g){

		g.setColor(Color.WHITE);

		g.fillRect(x,y,w,h);

		BufferedImage back = g.getBimg();
		g.setBufferedImage(back.getSubimage(x, y, w, h));
		
		if(component!=null){
			
			if(lastComponentH!=component.getH()){
				resetScroll();
				lastComponentH = component.getH();
			}
			
			component.draw(g);
		}
		
		g.setBufferedImage(back);

	}

	@Override
	public void update(GUIEvent event){

	}

	@Override
	public GUIEvent updateMouse(PointerEvent event){

		if(onMouse(event)){

			if(event.getPressed(MouseButton.MOUSE_WHEEL_DOWN)){

				for(int i=0;i<event.getAmount();i++){
					scrollDown();
				}
			}

			if(event.getPressed(MouseButton.MOUSE_WHEEL_UP)){

				for(int i=event.getAmount();i<0;i++){
					scrollUp();
				}
			}


			if(knob.isMouseOver()){

				if(event.getPressed(MouseButton.MOUSE_BUTTON_LEFT)){
					//TODO Mouse dragged with knob move scroll
				}

			}
		}


		return GUIEvent.NONE;

	}

	@Override
	public GUIEvent updateKeyboard(KeyboardEvent event) {

		if(event.getPressed(Tecla.TSK_TAB)){

			return GUIEvent.NEXT_COMPONENT;

		}

		return GUIEvent.NONE;
	}

	public void setComponent(GUIComponent component){
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

		remove(knob);
		knob = new Button(w-buttonSize,(int)knobPosition, buttonSize,((int)(h*scrollFactor))-buttonSize*2+1);
		knob.setVisible(false);
		add(knob);
		
		offset = scrollAmount*scrollFactor;
		
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

		int panelDif = h-component.getH();

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