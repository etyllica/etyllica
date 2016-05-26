package br.com.etyllica.awt;

import java.awt.BasicStroke;
import java.awt.Color;

import br.com.etyllica.core.event.MouseState;
import br.com.etyllica.core.graphics.ArrowDrawer;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.theme.mouse.ArrowTheme;
import br.com.etyllica.theme.mouse.arrow.MouseArrow;

public class AWTArrowDrawer implements ArrowDrawer {

	private int x = 0;

	private int y = 0;

	private static final BasicStroke strokeOne = new BasicStroke(1F);

	private static final BasicStroke strokeThree = new BasicStroke(3F);

	private static final BasicStroke strokeFive = new BasicStroke(5F);

	protected ArrowTheme arrowTheme;

	protected MouseArrow arrow;

	protected boolean overText = false;

	public void setCoordinates(int x, int y) {

		this.x = x;
		this.y = y;

		arrow.setCoordinates(x, y);
	}

	public void draw(Graphics g) {
		g.setStroke(strokeOne);
		arrow.draw(g);
	}	

	public void drawTimerArc(Graphics g, int arc) {

		g.setColor(Color.WHITE);
		g.setStroke(strokeFive);  // set stroke width of 5

		int radius = 26;

		g.drawArc(x-radius+2, y-radius+2, radius*2, radius*2, 0, 360);

		g.setColor(Color.BLUE);

		//Only if component was Clickable
		//if(overClickable){
		g.getGraphics().setStroke(strokeThree);  // set stroke width of 3
		g.drawArc(x-radius+2, y-radius+2, radius*2, radius*2, 0, arc);
		g.getGraphics().setStroke(strokeOne);  // set stroke width of 1
		//}

		g.getGraphics().setStroke(strokeOne);

	}
	
	@Override
	public void changeState(MouseState state) {
		switch(state) {
			
			case ARROW_HORIZONTAL:
				arrow = arrowTheme.getHorizontalArrow();
				break;
			case ARROW_VERTICAL:
				arrow = arrowTheme.getVerticalArrow();
				break;
			case ARROW_NE_SW:
				arrow = arrowTheme.getDiagonalArrow();
				break;
			case ARROW_NW_SE:
				arrow = arrowTheme.getInvertedDiagonalArrow();
				break;
			case CLICK:
				arrow = arrowTheme.getClickArrow();
				break;
			case LINK:
				arrow = arrowTheme.getLinkArrow();
				break;	
			case HELP:
				arrow = arrowTheme.getHelpArrow();
				break;
			case MOVE:
				arrow = arrowTheme.getMoveArrow();
				break;
			case TEXT:
				arrow = arrowTheme.getTextArrow();
				break;
			case WAIT:
				arrow = arrowTheme.getWaitArrow();
				break;
				
			default:
			case NORMAL:
				arrow = arrowTheme.getNormalArrow();
				break;
				
		}
	}
		
	public void updateArrowTheme(ArrowTheme arrowTheme) {
		this.arrowTheme = arrowTheme;
		changeState(MouseState.NORMAL);
	}
	
}