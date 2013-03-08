package examples.jgl.application;


import jgl.GLU;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyboardEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.video.Grafico;

public class Smooth extends GLU{
	
	public Smooth(int w, int h) {
		super(w,h);
	}

	@Override
	public void load(){

		myinit();
		
		myReshape(w, h);
				
		loading = 100;
	}

	private void myinit () {
		glShadeModel (GL_SMOOTH); /* GL_SMOOTH is the default */
	}

	private void triangle () {
		glBegin (GL_TRIANGLES);
		glColor3f (1.0f, 0.0f, 0.0f);
		glVertex2f (5.0f, 5.0f);
		glColor3f (0.0f, 1.0f, 0.0f);
		glVertex2f (25.0f, 5.0f);
		glColor3f (0.0f, 0.0f, 1.0f);
		glVertex2f (5.0f, 25.0f);
		glEnd ();
	}

	public void draw(Grafico g) {
		glClear (GL_COLOR_BUFFER_BIT);
		
		triangle();
		glFlush(g);		
	}


	private void myReshape (int w, int h) {
		glViewport (0, 0, w, h);
		glMatrixMode (GL_PROJECTION);
		glLoadIdentity ();
		if (w <= h) {
			gluOrtho2D (0.0, 30.0, 0.0, 30.0 * (float)h /(float)w);
		} else {
			gluOrtho2D (0.0, 30.0 * (float)w/(float)h, 0.0, 30.0);
		}
		glMatrixMode (GL_MODELVIEW);
	}

	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent.NONE;
	}

	@Override
	public GUIEvent updateKeyboard(KeyboardEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent.NONE;
	}

}
