package examples.jgl.application;

import org.jgl.GLAUX;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;

public class Clip extends GLAUX{

	public Clip(int w, int h) {
		super(w,h);
	}
	
	public void load(){
		myinit();
		loading = 100;
	}

	public void draw(Graphic g) {
		double eqn [] = {0.0, 1.0, 0.0, 0.0};	/* y < 0 */
		double eqn2 [] = {1.0, 0.0, 0.0, 0.0};	/* x < 0 */

		glClear (GL_COLOR_BUFFER_BIT);

		glColor3f (1.0f, 1.0f, 1.0f);
		glPushMatrix ();
		glTranslatef (0.0f, 0.0f, -5.0f);

		glClipPlane (GL_CLIP_PLANE0, eqn);
		glEnable (GL_CLIP_PLANE0);
		glClipPlane (GL_CLIP_PLANE1, eqn2);
		glEnable (GL_CLIP_PLANE1);

		glRotatef (90.0f, 1.0f, 0.0f, 0.0f);
		auxWireSphere (1.0);
		glPopMatrix ();
		glFlush(g);
	}

	private void myinit () {
		glShadeModel (GL_FLAT);
		myReshape(w,h);
	}


	private void myReshape (float w, float h) {
		glViewport (0, 0, w, h);
		glMatrixMode (GL_PROJECTION);
		glLoadIdentity ();
		gluPerspective (60.0, (float)w/(float)h, 1.0, 20.0);
		glMatrixMode (GL_MODELVIEW);
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
