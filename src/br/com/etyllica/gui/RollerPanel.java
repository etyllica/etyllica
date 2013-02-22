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
import br.com.etyllica.gui.panel.ScrollBackground;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public class RollerPanel extends GUIComponent{

	private GUIComponent component;
	
	private int buttonSize = 20;
	private int scrollAmount = 10;
	private float scrollFactor = 1;
	private float offset = 0;
	private float knobPosition = 0;
	
	private Button upArrow;
	private Button downArrow;
	private Button knob;
	private ScrollBackground track;
	
	public RollerPanel(int x, int y, int w, int h) {
		super(x, y, w, h);
		
		upArrow = new Button(w-buttonSize,0,buttonSize,buttonSize);
		upArrow.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new GUIAction(this, "scrollUp"));
		
		downArrow = new Button(w-buttonSize,h-buttonSize,buttonSize,buttonSize);
		downArrow.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new GUIAction(this, "scrollDown"));
		
		track = new ScrollBackground(w-buttonSize, buttonSize, buttonSize, h-buttonSize*2);
		add(track);
		
		add(upArrow);
		add(downArrow);
	}

	@Override
	public void draw(Grafico g){

		g.setColor(Color.WHITE);
		
		g.fillRect(x,y,w,h);
		
		BufferedImage back = g.getBimg();
		g.setBufferedImage(back.getSubimage(x, y, w, h));
		
		if(component!=null)
			component.draw(g);
		
		g.setBufferedImage(back);

	}
	
	public void update(GUIEvent event){

	}

	@Override
	public GUIEvent updateMouse(PointerEvent event){
		
		//TODO MouseWheelUP/MouseWheelDOWN
		/*if(event.getPressed(MouseButton.MOUSE_WHEEL_UP)){
			for(int i=0;i<event.getAmount();i++){
				scrollUp();
			}
		}*/
		
		if(knob.isMouseOver()){
			if(event.getPressed(MouseButton.MOUSE_BUTTON_LEFT)){
				//Mouse Dragged também move o scroll
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
		
		if(component.getH()>h){
			scrollFactor = (float)((float)h/(float)component.getH());
		}
		
		knob = new Button(w-buttonSize, buttonSize, buttonSize,((int)(h*scrollFactor))-buttonSize*2+1);
		add(knob);
		
		offset = scrollAmount*scrollFactor;
		knobPosition = knob.getY();
		
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
