package br.com.etyllica.core.drawer;

import java.awt.BasicStroke;
import java.awt.Color;

import br.com.etyllica.core.Configuration;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.input.mouse.MouseState;
import br.com.etyllica.core.input.mouse.MouseStateListener;
import br.com.etyllica.theme.mouse.ArrowTheme;
import br.com.etyllica.theme.mouse.ArrowThemeListener;
import br.com.etyllica.theme.mouse.MouseArrow;

public class ArrowDrawer implements MouseStateListener, ArrowThemeListener {

	private int x = 0;

	private int y = 0;

	private static final BasicStroke strokeOne = new BasicStroke(1F);

	private static final BasicStroke strokeThree = new BasicStroke(3F);

	private static final BasicStroke strokeFive = new BasicStroke(5F);

	protected ArrowTheme arrowTheme;

	protected MouseArrow arrow;

	protected int arc = 0;	

	protected boolean overText = false;

	private boolean overClickable = false;

	public boolean isOverClickable() {
		return overClickable;
	}

	public void setOverClickable(boolean overClickable) {
		this.overClickable = overClickable;
	}

	public void setCoordinates(int x, int y) {

		this.x = x;
		this.y = y;

		arrow.setCoordinates(x, y);
	}

	public void draw(Graphic g){

		g.getGraphics().setStroke(strokeOne);

		arrow.draw(g);

		if(Configuration.getInstance().isTimerClick()&&overClickable){
			drawTimerArc(g);
		}
	}	

	private void drawTimerArc(Graphic g) {

		g.setColor(Color.WHITE);
		g.getGraphics().setStroke(strokeFive);  // set stroke width of 5

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

	public int getArc() {
		return arc;
	}

	public void setArc(int arc) {
		this.arc = arc;
	}
	
}
