package examples.jgl.application;

import org.jgl.GLAUX;

import br.com.etyllica.core.context.UpdateIntervalListener;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.graphics.Graphic;

public class Planet extends GLAUX implements UpdateIntervalListener {
	
	public Planet(int w, int h) {
		super(w,h);
	}
	
	@Override
	public void load() {
		myReshape(w, h);
		
		updateAtFixedRate(50, this);
		
		loading = 100;
	}

	private static int month = 0, day = 0;

	private void dayAdd(){ day = (day + 10) % 360; }
	private void daySubtract(){ day = (day - 10) % 360; }
	private void monthAdd(){ month = (month + 5) % 360; }
	private void monthSubtract(){ month = (month - 5) % 360; }

	@Override
	public void timeUpdate(long now){
		monthAdd();
		dayAdd();
	}
	
	@Override
	public void updateKeyboard(KeyEvent event) {

		if(event.isKeyDown(KeyEvent.VK_LEFT)) {
			monthSubtract();
		}
		else if(event.isKeyDown(KeyEvent.VK_RIGHT)) {
			monthAdd();
		}
		else if(event.isKeyDown(KeyEvent.VK_UP)) {
			dayAdd();
		}
		else if(event.isKeyDown(KeyEvent.VK_DOWN)) {
			daySubtract();
		}
	}

	@Override
	public void draw(Graphic g) {
		glClear (GL_COLOR_BUFFER_BIT);

		glColor3f (1.0f, 1.0f, 1.0f);
		glPushMatrix ();
		auxWireSphere (1.0);		/* draw sun */
		glRotatef ((float)month, 0.0f, 1.0f, 0.0f);
		glTranslatef (2.0f, 0.0f, 0.0f);
		glRotatef ((float)day, 0.0f, 1.0f, 0.0f);
		auxWireSphere (0.2);		/* draw smaller planet */
		glPopMatrix ();
		glFlush(g);
	}

	private void myReshape (float w, float h) {
		glShadeModel (GL_FLAT);
		glViewport (0, 0, w, h);
		glMatrixMode (GL_PROJECTION);
		glLoadIdentity ();
		gluPerspective (60.0, (double)w/(double)h,1.0, 20.0);
		glMatrixMode (GL_MODELVIEW);
		glLoadIdentity ();
		glTranslatef (0.0f, 0.0f, -5.0f);
	}

}
