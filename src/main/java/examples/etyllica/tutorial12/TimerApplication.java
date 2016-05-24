package examples.etyllica.tutorial12;

import java.awt.Color;

import br.com.etyllica.animation.timer.Timer;
import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.graphics.Graphic;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class TimerApplication extends Application {

	private static final int RECT_SIZE = 20;
	
	private Timer timer;
	private int x;
	private int speed = 3;

	public TimerApplication(int w, int h) {
		super(w,h);
	}
	
	public void load() {
		timer = new Timer();
		x = 0;
	}

	public void update(long now) {
		if(timer.schedule(now, 10)) {
			if (x >= w-RECT_SIZE) {
				speed = -speed;
			} else if (x < 0) {
				speed = -speed;
			}
			x += speed;
		}
	}

	@Override
	public void draw(Graphic g) {
		g.setColor(Color.BLACK);
		g.fillRect(x, 100, RECT_SIZE, RECT_SIZE);
	}
}