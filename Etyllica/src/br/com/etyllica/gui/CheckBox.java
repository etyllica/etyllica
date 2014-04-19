package br.com.etyllica.gui;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.event.PointerState;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.input.mouse.MouseButton;
import br.com.etyllica.gui.button.DefaultButton;
import br.com.etyllica.gui.theme.Theme;
import br.com.etyllica.theme.ThemeManager;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class CheckBox extends DefaultButton{

	protected boolean checked = false;

	public CheckBox(int x, int y) {
		super(x, y, 22, 22);
	}
	
	public CheckBox(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public GUIEvent updateMouse(PointerEvent event){

		GUIEvent retorno = GUIEvent.NONE;

		if(mouseOver){

			if(event.getState()==PointerState.PRESSED){

				if(event.isKey(MouseButton.MOUSE_BUTTON_LEFT)){

					swapChecked();
					retorno = GUIEvent.MOUSE_LEFT_BUTTON_DOWN;

				}else if(event.isKey(MouseButton.MOUSE_BUTTON_RIGHT)){

					retorno = GUIEvent.MOUSE_RIGHT_BUTTON_DOWN;

				}else if(event.isKey(MouseButton.MOUSE_BUTTON_MIDDLE)){

					retorno = GUIEvent.MOUSE_MIDDLE_BUTTON_DOWN;
				}
				
			}else if(event.getState()==PointerState.RELEASED){

				if(event.isKey(MouseButton.MOUSE_BUTTON_LEFT)){
					
					retorno = GUIEvent.MOUSE_LEFT_BUTTON_UP;

				}else if(event.isKey(MouseButton.MOUSE_BUTTON_RIGHT)){
					
					retorno = GUIEvent.MOUSE_RIGHT_BUTTON_UP;

				}else if(event.isKey(MouseButton.MOUSE_BUTTON_MIDDLE)){
					
					retorno = GUIEvent.MOUSE_MIDDLE_BUTTON_UP;
					
				}else{
					
					retorno = GUIEvent.MOUSE_OVER;
					
				}

			}
			
		}else{

			retorno = GUIEvent.NONE;

		}

		return retorno;

	}
	
	private void swapChecked(){
		
		checked = !checked;
		
	}
	
	@Override
	public void draw(Graphic g){

		Theme theme = ThemeManager.getInstance().getTheme();

		if(!mouseOver){

			g.setColor(theme.getButtonColor());

		}else{

			if(lastEvent == GUIEvent.MOUSE_LEFT_BUTTON_DOWN){

				g.setColor(theme.getButtonOnClick());

			}else{

				g.setColor(theme.getButtonOnMouse());

			}
		}

		g.fillRect(x,y,w,h);



		if(checked){

			//DrawShadow
			if(theme.isShadow()){

				g.setColor(theme.getShadowColor());
				
				g.drawLine(x+3,y+3,x+w/2+1, y+h/2+1);
				g.drawLine(x+w/2+1, y+h/2+1,x+w+5+1,y-5+1);
			}

			g.setColor(theme.getTextColor());

			g.drawLine(x+2,y+2,x+w/2, y+h/2);
			g.drawLine(x+w/2, y+h/2,x+w+5,y-5);

		}

	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}


}
