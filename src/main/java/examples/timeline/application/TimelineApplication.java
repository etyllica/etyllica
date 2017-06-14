package examples.timeline.application;

import java.awt.Color;

import br.com.etyllica.commons.context.Application;
import br.com.etyllica.commons.event.MouseEvent;
import br.com.etyllica.commons.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.layer.Layer;

public class TimelineApplication extends Application {

	Color background = new Color(0x72, 0x72, 0x72);
	Color details = new Color(0x36, 0x36, 0x36);
	Color middle = new Color(0x6b, 0x6b, 0x6b);
	Color mark = new Color(0x5b, 0x5b, 0x5b);
	
	Color cursorColor = new Color(0x60, 0xc0, 0x40);
		
	int cursor = 100;
	int cursorPadding = 2;
	
	int totalTime = 8000;
	int currentTime = 0;
	
	Layer timeline;
	
	public TimelineApplication(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {
		
		int tx = 20;
		int ty = 160;
		int tw = 600;
		int th = 80;
		
		timeline = new Layer(tx, ty, tw, th);
		
		loading = 100;
	}

	@Override
	public void updateMouse(PointerEvent event) {
				
		if (timeline.onMouse(event)) {
			if (event.isButtonDown(MouseEvent.MOUSE_BUTTON_LEFT)) {	
				cursor = event.getX()-timeline.getX();
			}
		}
		
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(background);
		g.fillRect(timeline);

		g.setColor(details);
		//Draw marks
		for(int i=0;i<timeline.getW()/20;i++) {
			if (i%2 == 0) {
				g.setColor(middle);
			} else {
				g.setColor(mark);
			}
			g.drawLine(timeline.getX()+i*20, timeline.getY(), timeline.getX()+i*20, timeline.getY()+timeline.getH());
		}
		
		//Draw timeline border
		g.setColor(details);
		g.drawRect(timeline);
		
		//Draw cursor
		g.setLineWidth(2f);
		g.setColor(cursorColor);
		g.drawLine(timeline.getX()+cursor, timeline.getY()+cursorPadding, timeline.getX()+cursor, timeline.getY()+timeline.getH()-cursorPadding);
		g.setLineWidth(1f);
	}
}

