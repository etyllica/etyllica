package br.com.etyllica.gui.bar;

import br.com.etyllica.core.Configuration;
import br.com.etyllica.core.control.mouse.Mouse;
import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.layer.ImageLayer;
import br.com.etyllica.theme.Theme;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class LeftBar extends Bar{
	
	public LeftBar(int x, int y, int w, int h){
		super(x,y,w,h);
		
		reset();
	}

	public void reset(){

		int chanfer = 12;
		int raio = 32;

		iconColision = new ImageLayer(x+w-raio/3-chanfer,y+h/2-raio,raio*2,raio*2);

		if(icon!=null){
			icon.centraliza(iconColision);
			icon.setOffsetX(raio/2);
		}
				
		p.reset();
		p.addPoint(x, y);
		p.addPoint(x+w, y);
		p.addPoint(x+w+chanfer, y+chanfer);
		p.addPoint(x+w+chanfer, y+h-chanfer);
		p.addPoint(x+w,y+h);
		p.addPoint(x,y+h);
	}

	//TODO Subir para Bar
	@Override
	public void draw(Grafico g) {

		Theme theme = Configuration.getInstance().getTheme();

		if(mouseOver){
			g.setColor(theme.getBarOnMouseColor());
		}else{
			g.setColor(theme.getBarColor());
		}

		g.fillPolygon(p);

		g.fillArc(iconColision.getX(), iconColision.getY(), iconColision.getW(), iconColision.getH(), 270, 180);

		if(icon!=null){
			icon.draw(g);
		}

	}

	@Override
	public boolean onMouse(Mouse mouse){


		if((iconColision.colideCircularPonto(mouse.getX(), mouse.getY()))
				//if((iconColision.colideRetangular(mouse.getX(), mouse.getY(), 1, 1))
				//||(p.contains(mouse.getX(), mouse.getY()))){
				){
			return true;
		}

		return false;

	}


	public void open(){

		//TODO Substituir por timer

		if(x<startX+application.getW()){
			x+=velocidade;
			reset();
			
		}else{
			openning = false;
			opened = true;
		}

	}

	public void close(){

		if(x>startX){

			x -= velocidade;
			reset();

		}else{
			closing = false;
			closed = true;
			//visible = false;
		}

	}

}
