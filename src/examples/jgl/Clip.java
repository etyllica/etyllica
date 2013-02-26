package examples.jgl;

import jgl.GLAUX;
import br.com.etyllica.core.video.Grafico;

public class Clip extends GLAUX{

	public Clip() {
		super();
	}
	public void load(){
		myinit();
		loading = 100;
	}

	public void draw(Grafico g) {
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


	private void myReshape (int w, int h) {
		glViewport (0, 0, w, h);
		glMatrixMode (GL_PROJECTION);
		glLoadIdentity ();
		gluPerspective (60.0, (float)w/(float)h, 1.0, 20.0);
		glMatrixMode (GL_MODELVIEW);
	}
	
}
