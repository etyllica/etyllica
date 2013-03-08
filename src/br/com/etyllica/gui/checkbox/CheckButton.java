package br.com.etyllica.gui.checkbox;

import br.com.etyllica.core.Configuration;
import br.com.etyllica.core.Theme;
import br.com.etyllica.core.control.mouse.MouseButton;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyState;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.gui.Button;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public class CheckButton extends Button{

	protected boolean checked = false;

	public CheckButton(int x, int y) {
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
		
		checked = !checked;
		
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

		if(checked){
			g.setColor(theme.getButtonOnClick());
		}
		
		g.fillRect(x,y,w,h);

	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}


}
