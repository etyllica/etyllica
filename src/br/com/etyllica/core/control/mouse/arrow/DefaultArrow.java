package br.com.etyllica.core.control.mouse.arrow;

import java.awt.Color;
import java.awt.Polygon;

import br.com.etyllica.core.Configuration;
import br.com.etyllica.core.Theme;
import br.com.etyllica.core.video.Grafico;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public class DefaultArrow extends Polygon implements MouseArrow{
	private static final long serialVersionUID = -5503086611953227299L;

	private int tx = 0;
	private int ty = 0;

	protected int size = 24;

	protected Color color = null;
	protected Color borderColor = null;

	public DefaultArrow(int size){

		this.size = size;

		defPoints();
	}

	public void defPoints(){
		addPoint(0, 0);
		addPoint(0, (int)(size*0.8));
		addPoint((int)(size*0.18),(int)(size*0.62));

		addPoint((int)(size*0.28),(int)(size*0.88));
		addPoint((int)(size*0.42),(int)(size*0.82));

		addPoint((int)(size*0.30),(int)(size*0.55));
		addPoint((int)(size*0.49),(int)(size*0.57));
	}

	public void move(int mx, int my){

		int difx = mx;
		int dify = my;

		this.translate(difx-tx, dify-ty);

		tx = difx;
		ty = dify;

	}

	public void draw(Grafico g){

		Theme theme = Configuration.getInstance().getTheme();

		if(color==null){
			g.setColor(theme.getMouseArrowColor());
		}else{
			g.setColor(color);
		}
		g.fillPolygon(this);

		if(borderColor==null){
			g.setColor(theme.getMouseArrowBorderColor());	
		}else{
			g.setColor(borderColor);
		}
		g.drawPolygon(this);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
}
