package br.com.etyllica.gui.bar;

import br.com.etyllica.core.Configuration;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.theme.Theme;
import br.com.etyllica.core.video.Grafico;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class DownBar extends Bar{

	public DownBar(int x, int y, int w, int h){
		super(x,y,w,h);				
	}

	public void reset(){
		p.reset();

		p.addPoint(x+h, y);
		p.addPoint(x+w-h, y);
		p.addPoint(x+w, y+h);
		p.addPoint(x,y+h);
	}

	@Override
	public GUIEvent updateMouse(PointerEvent event){

		return GUIEvent.NONE;
	}

	@Override
	public void draw(Grafico g) {

		Theme theme = Configuration.getInstance().getTheme();

		if(mouseOver){
			g.setColor(theme.getBarOnMouseColor());
		}else{
			g.setColor(theme.getBarColor());
		}

		g.fillPolygon(p);

		/*if(application!=null){
			application.draw(g);
			application.getGui().draw(g);
		}*/

	}

	@Override
	public void open() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	protected void translateGui() {
		// TODO Auto-generated method stub
	}

}
