package examples.jgl.application;


import org.jgl.GLU;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;

public class List extends GLU{

	public List(int w, int h) {
		super(w,h);
	}

	@Override
	public void load() {
		myinit();
		loading = 100;
	}

	private int listName = 1;

	private void drawLine () {
		glBegin (GL_LINES);
		glVertex2f ( 0.0f, 0.5f);
		glVertex2f (15.0f, 0.5f);
		glEnd ();
	}

	public void draw(Graphic g) {
		int i;
		
		glLoadIdentity ();
		
		glClear (GL_COLOR_BUFFER_BIT);
		glColor3f (1.0f, 0.0f, 0.0f);
		for (i = 0; i < 10; i++) {
			glCallList (listName);
		}
		drawLine ();

		glFlush (g);
	}
	
	private void myinit () {
		glNewList (listName, GL_COMPILE);
		glColor3f (1.0f, 0.0f, 0.0f);
		
		glBegin (GL_TRIANGLES);
		glVertex2f (0.0f, 0.0f);
		glVertex2f (1.0f, 0.0f);
		glVertex2f (0.0f, 1.0f);
		glEnd ();
		
		glTranslatef (1.5f, 0.0f, 0.0f);
		glEndList ();
		
		glShadeModel (GL_FLAT);

		myReshape(w,h);
	}

	private void myReshape (float w, float h) {
		glViewport (0, 0, w, h);
		glMatrixMode (GL_PROJECTION);
		glLoadIdentity ();
		if (w <= h) {
			gluOrtho2D (0.0, 2.0, -0.5 * (float)h/(float)w, 1.5 * (float)h/(float)w);
		} else {
			gluOrtho2D (0.0, 2.0 * (float)w/(float)h, -0.5, 1.5);
		}
		glMatrixMode (GL_MODELVIEW);
		glLoadIdentity ();

	}

	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent.NONE;
	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent.NONE;
	}

}
