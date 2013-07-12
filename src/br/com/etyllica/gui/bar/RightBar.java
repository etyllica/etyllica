package br.com.etyllica.gui.bar;

import java.awt.Color;

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

public class RightBar extends Bar{
	
	public RightBar(int x, int y, int w, int h){
		super(x,y,w,h);
		
		reset();
	}
	
	private int counter = 0;

	public void reset(){

		int chanfer = 12;
		int raio = 32;

		iconColision = new ImageLayer(x-chanfer-raio,h/2-raio,raio*2,raio*2);
		
		if(icon!=null){
			icon.centraliza(iconColision);
			icon.setOffsetX(-chanfer);
		}

		p.reset();

		p.addPoint(x, y);
		p.addPoint(x+w, y);
		p.addPoint(x+w, y+h);
		p.addPoint(x, y+h);
		//Chanfer
		p.addPoint(x-chanfer, y+h-chanfer);
		p.addPoint(x-chanfer, y+chanfer);

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

		g.fillArc(iconColision.getX(), iconColision.getY(), iconColision.getW(), iconColision.getH(), 90, 180);

		if(icon!=null){
			icon.draw(g);
		}
		
		if(closed){
		
			if(counter>0){
				
				int circX = x-32;
				int circY = h/2+22;
				int circRadius = 12;
				
				g.setColor(Color.RED);
				g.fillCircle(circX,circY,circRadius);
				
				g.setColor(Color.WHITE);
				String number = Integer.toString(counter);
				
				g.escreveSombra(circX-(circRadius/2)*number.length(), circY+circRadius/2, number);
			}
			
		}else{
		
			if(counter>0){
				counter = 0;
			}
			
		}

	}

	@Override
	public boolean onMouse(Mouse mouse){


		if((iconColision.colideCircularPonto(mouse.getX(), mouse.getY()))
				//if((iconColision.colideRetangular(mouse.getX(), mouse.getY(), 1, 1))
				||(p.contains(mouse.getX(), mouse.getY()))){
			return true;
		}

		return false;
	}
	
	public void open(){

		//TODO Substituir por timer
		
		if(x>startX-application.getW()){

			x -= velocidade;
			reset();
			
		}else{
			openning = false;
			opened = true;
		}

	}
	
	public void close(){

		if(x<startX){

			x += velocidade;
			reset();

		}else{
			closing = false;
			closed = true;
			//visible = false;
		}

	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

}
