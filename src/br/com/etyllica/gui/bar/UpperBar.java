package br.com.etyllica.gui.bar;

import br.com.etyllica.core.Configuration;
import br.com.etyllica.core.input.mouse.Mouse;
import br.com.etyllica.core.theme.Theme;
import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.layer.ImageLayer;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class UpperBar extends Bar{

	public UpperBar(int x, int y, int w, int h){
		super(x,y,w,h);

	}

	public void reset(){

		int chanfer = 12;
		int raio = 32;		

		iconColision = new ImageLayer(x+w/2-(raio/2),y+h-raio,raio*2,raio*2);

		if(icon!=null){
			icon.centraliza(iconColision);
			icon.setOffsetY(chanfer);
		}
		
		p.reset();
		p.addPoint(x, y);
		p.addPoint(x+w, y);
		p.addPoint(x+w, y+h-chanfer);
		p.addPoint(x+w-chanfer, y+h);		
		p.addPoint(x+chanfer, y+h);
		p.addPoint(x, y+h-chanfer);
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

		g.fillArc(iconColision.getX(), iconColision.getY(), iconColision.getW(), iconColision.getH(), 180, 180);

		if(icon!=null){
			icon.draw(g);
		}
				
	}
	
	public boolean onMouse(Mouse mouse){


		if((iconColision.colideCircularPonto(mouse.getX(), mouse.getY()))
				//if((iconColision.colideRetangular(mouse.getX(), mouse.getY(), 1, 1))
				||(p.contains(mouse.getX(), mouse.getY()))){
			return true;
		}

		return false;
	}

	@Override
	public void open(){
		//TODO Substituir por timer

		if(y<startY+application.getH()){
			y+=velocidade;
			reset();

		}else{
			openning = false;
			opened = true;
		}

	}

	@Override
	public void close(){

		if(y>startY){
			y -= velocidade;
			reset();
		}else{
			closing = false;
			closed = true;
			//visible = false;
		}

	}

}
