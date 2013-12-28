package br.com.etyllica.core.input.mouse;

import java.awt.BasicStroke;

import br.com.etyllica.core.Drawable;
import br.com.etyllica.core.video.Graphic;

public class Mouse extends MouseHandler implements Drawable{

	public Mouse(int x, int y) {
		super(x, y);
	}

	private final BasicStroke strokeOne = new BasicStroke(1F);
	
	private final BasicStroke strokeThree = new BasicStroke(3F);
	
	private final BasicStroke strokeFive = new BasicStroke(5F);

	public void draw(Graphic g){

		g.getGraphics().setStroke(strokeOne);
		
		arrow.move(x, y);
		
		arrow.draw(g);

		/*
		if(Configuration.getInstance().isTimerClick()){

			g.setColor(Color.WHITE);
			g.getGraphics().setStroke(strokeFive);  // set stroke width of 5

			int raio = 26;

			g.drawArc(x-raio+2, y-raio+2, raio*2, raio*2, 0, 360);

			g.setColor(Color.BLUE);

			//Only if component was Clickable
			if(mouseOverClickable){
				g.getGraphics().setStroke(strokeThree);  // set stroke width of 3
				g.drawArc(x-raio+2, y-raio+2, raio*2, raio*2, 0, mouse.getArc());
				g.getGraphics().setStroke(strokeOne);  // set stroke width of 1
			}
		}
		*/

	}

	
	
}
