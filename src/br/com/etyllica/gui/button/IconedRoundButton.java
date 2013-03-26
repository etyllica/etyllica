package br.com.etyllica.gui.button;

import br.com.etyllica.core.Configuration;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.gui.Button;
import br.com.etyllica.layer.ImageLayer;
import br.com.etyllica.theme.Theme;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class IconedRoundButton extends Button{

	protected ImageLayer icon;
	
	public IconedRoundButton(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	public IconedRoundButton(int x, int y, String iconPath) {
		super(x, y, 0,0);
		
		this.icon = new ImageLayer(iconPath);
		this.setW(icon.getW());
		this.setH(icon.getH());
		
		this.icon.centraliza(x, y, w, h);
	}

	public void setIcon(ImageLayer icon){
		this.icon = icon;
		
		int offsetX = icon.getX();
		int offsetY = icon.getY();
		
		this.icon.centraliza(x, y, w, h);
		this.icon.setOffset(offsetX, offsetY);
	}

	@Override
	public void draw(Grafico g){

		Theme theme = Configuration.getInstance().getTheme();

		if(!mouseOver){

			if(onFocus){
				
				g.setColor(theme.getButtonOnFocus());
				
			}else{
				
				g.setColor(theme.getButtonColor());
				
			}
			
		}else{
			
			if(lastEvent == GUIEvent.MOUSE_LEFT_BUTTON_DOWN){

				g.setColor(theme.getButtonOnClick());

			}else{

				g.setColor(theme.getButtonOnMouse());

			}
			
		}
		
		g.fillCircle(x+w/2,y+w/2,w/2);

		drawIcon(g);

	}


	protected void drawIcon(Grafico g){
		if(icon!=null){
			icon.draw(g);
		}
	}
	
	@Override
	public void setOffsetX(int offsetX){
		this.x += offsetX;
		
		if(icon!=null){
			icon.setOffsetX(offsetX);
		}
	}
	
	@Override
	public void setOffsetY(int offsetY){
		this.y += offsetY;
		
		if(icon!=null){
			icon.setOffsetY(offsetY);
		}
	}

}
