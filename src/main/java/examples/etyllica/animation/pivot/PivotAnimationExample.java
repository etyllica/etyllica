package examples.etyllica.animation.pivot;

import java.awt.Color;

import br.com.etyllica.animation.pivot.Part;
import br.com.etyllica.commons.context.Application;
import br.com.etyllica.commons.graphics.Graphics;

public class PivotAnimationExample extends Application {

	public PivotAnimationExample(int w, int h) {
		super(w, h);
	}
	
	private Part part;
	private Part otherPart;
	
	@Override
	public void load() {
		
		loading = 10;
				
		part = new Part(40, 80);
		otherPart = new Part(150, 90);
		
		loading = 100;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect(part);
		g.drawRect(otherPart);
	}
		
}