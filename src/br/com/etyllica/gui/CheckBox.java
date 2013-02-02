package br.com.etyllica.gui;

import br.com.etyllica.core.Configuration;
import br.com.etyllica.core.Theme;
import br.com.etyllica.core.control.mouse.MouseButton;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyState;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.video.Grafico;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public class CheckBox extends Button{

	protected boolean checked = false;

	public CheckBox(int x, int y) {
		super(x, y, 22, 22);
	}

	@Override
	public GUIEvent updateMouse(PointerEvent event){

		GUIEvent retorno = GUIEvent.NONE;

		if(onMouse(event)){

			mouseOver = true;

			if(event.getState()==KeyState.PRESSED){

				if(event.isKey(MouseButton.MOUSE_BUTTON_LEFT)){

					swapChecked();

				}
				
			}
			
			retorno = GUIEvent.MOUSE_OVER;

		}else{

			mouseOver = false;
			retorno = GUIEvent.NONE;

		}

		return retorno;

	}
	
	private void swapChecked(){
		if(!checked){
			checked = true;
		}else{
			checked = false;
		}
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
