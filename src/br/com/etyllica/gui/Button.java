package br.com.etyllica.gui;

import br.com.etyllica.core.Configuration;
import br.com.etyllica.core.Theme;
import br.com.etyllica.core.control.mouse.MouseButton;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyState;
import br.com.etyllica.core.event.KeyboardEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.event.Tecla;
import br.com.etyllica.core.video.Grafico;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public class Button extends RoundGUIComponent{

	protected Label label;

	public Button(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public void draw(Grafico g){

		Theme theme = Configuration.getInstance().getTheme();

		if(!mouseOver){

			g.setColor(theme.getButtonColor());

		}else{

			if(lastEvent == GUIEvent.MOUSE_LEFT_BUTTON_DOWN){

				g.setColor(theme.getButtonOnClick());

			}else{

				g.setColor(theme.getButtonOnMouse());

			}

			//g.escreveSombra(x, y, label.getAlt());
		}

		g.fillRect(x,y,w,h);
		
		drawLabel(g);

	}

	protected void drawLabel(Grafico g){
		
		if(label!=null){
			label.draw(g);
		}
		
	}

	public void update(GUIEvent event){

		switch (event) {
		case MOUSE_LEFT_BUTTON_DOWN:
			leftClick();
			break;

		case MOUSE_LEFT_BUTTON_UP:
			leftUp();
			break;

		case MOUSE_RIGHT_BUTTON_DOWN:
			rightClick();
			break;

		case MOUSE_MIDDLE_BUTTON_DOWN:
			middleClick();
			break;

		case MOUSE_OVER:
			justOnMouse();
			break;

		case MOUSE_OUT:
			mouseOut();
			break;

		case GAIN_FOCUS:
			onFocus = true;
			break;
			
		case LOST_FOCUS:
			onFocus = false;
			break;

		case NONE:
			break;

		default:

			//Labels is button responsability
			if(label!=null){
				label.update(event);
			}

			break;
		}


	}

	protected void leftClick(){
		//igualaImagem(click);
	}

	protected void leftUp(){

	}

	protected void middleClick(){
		//new Voicer().say(rotulo);
	}

	protected void rightClick(){

	}

	protected void justOnMouse(){
		//igualaImagem(sobMouse);
	}

	protected void mouseOut(){
		//igualaImagem(sobMouse);
	}

	public GUIEvent updateMouse(PointerEvent event){

		GUIEvent retorno = GUIEvent.NONE;

		if(mouseOver){

			if(event.getState()==KeyState.PRESSED){

				if(event.isKey(MouseButton.MOUSE_BUTTON_LEFT)){

					retorno = GUIEvent.MOUSE_LEFT_BUTTON_DOWN;

				}else if(event.isKey(MouseButton.MOUSE_BUTTON_RIGHT)){

					retorno = GUIEvent.MOUSE_RIGHT_BUTTON_DOWN;

				}else if(event.isKey(MouseButton.MOUSE_BUTTON_MIDDLE)){

					retorno = GUIEvent.MOUSE_MIDDLE_BUTTON_DOWN;
				}
			}
			else if(event.getState()==KeyState.RELEASED){

				if(event.isKey(MouseButton.MOUSE_BUTTON_LEFT)){
					
					retorno = GUIEvent.MOUSE_LEFT_BUTTON_UP;

				}else if(event.isKey(MouseButton.MOUSE_BUTTON_RIGHT)){
					
					retorno = GUIEvent.MOUSE_RIGHT_BUTTON_UP;

				}else if(event.isKey(MouseButton.MOUSE_BUTTON_MIDDLE)){
					
					retorno = GUIEvent.MOUSE_MIDDLE_BUTTON_UP;
					
				}else{
					
					retorno = GUIEvent.MOUSE_OVER;
					
				}

			}else if(event.getState()==KeyState.DOUBLE_CLICK){
			
				if(event.isKey(MouseButton.MOUSE_BUTTON_LEFT)){
					
					retorno = GUIEvent.MOUSE_LEFT_BUTTON_DOUBLE_CLICK;
					
				}else if(event.isKey(MouseButton.MOUSE_BUTTON_RIGHT)){
					
					retorno = GUIEvent.MOUSE_RIGHT_BUTTON_DOUBLE_CLICK;
					
				}else if(event.isKey(MouseButton.MOUSE_BUTTON_MIDDLE)){
										
					retorno = GUIEvent.MOUSE_MIDDLE_BUTTON_DOUBLE_CLICK;
					
				}

			}

		}else{
			
			if(event.getPressed(MouseButton.MOUSE_BUTTON_LEFT)){
				onFocus = false;
			}

			retorno = GUIEvent.NONE;

		}

		return retorno;

	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;

		int offsetX = label.getX();
		int offsetY = label.getY();

		label.setX(x+offsetX);
		label.setY(y+offsetY);
		
		label.setContentBounds(x, y, w, h);

	}
	
	public void setCenterLabel(Label label) {
		this.label = label;

		label.setX(x+w/2-label.getW()/2);
		label.setY(y+h/2-label.getH()/2);
		
		label.setContentBounds(x, y, w, h);

	}

	@Override
	public GUIEvent updateKeyboard(KeyboardEvent event) {

		if(event.getPressed(Tecla.TSK_TAB)){

			return GUIEvent.NEXT_COMPONENT;
			
		}
		
		/*if(event.getPressed(Tecla.TSK_ENTER)){

			return GUIEvent.MOUSE_LEFT_BUTTON_DOWN;

		}else if(event.getReleased(Tecla.TSK_ENTER)){

			return GUIEvent.MOUSE_LEFT_BUTTON_UP;

		}*/

		return GUIEvent.NONE;
	}

	

}
