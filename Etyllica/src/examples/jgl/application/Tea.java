package examples.jgl.application;

import org.jgl.GLAUX;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.video.Graphic;

public class Tea extends GLAUX{

	public Tea(int w, int h) {
		super(w,h);
	}

	@Override
	public void load(){
		myinit();
		
		loading = 100;
	}

	private void myinit () {
		float light_ambient[] = { 0.0f, 0.0f, 0.0f, 1.0f };
		float light_diffuse[] = { 1.0f, 1.0f, 1.0f, 1.0f };
		float light_specular[] = { 1.0f, 1.0f, 1.0f, 1.0f };
		/*  light_position is NOT default value  */
		float light_position[] = { 1.0f, 1.0f, 1.0f, 0.0f };

		glLightfv (GL_LIGHT0, GL_AMBIENT, light_ambient);
		glLightfv (GL_LIGHT0, GL_DIFFUSE, light_diffuse);
		glLightfv (GL_LIGHT0, GL_SPECULAR, light_specular);
		glLightfv (GL_LIGHT0, GL_POSITION, light_position);

		glFrontFace (GL_CW);
		glEnable (GL_LIGHTING);
		glEnable (GL_LIGHT0);
		glEnable (GL_AUTO_NORMAL);
		glEnable (GL_NORMALIZE);
		glDepthFunc (GL_LESS);
		glEnable (GL_DEPTH_TEST);

		myReshape(w,h);
	}

	@Override
	public void draw(Graphic g) {
		
		double eqn[] = { 1.0, 0.0, -1.0, 1.0 };
		
		float mat_diffuse[] = { 0.8f, 0.8f, 0.8f, 1.0f };
		float back_diffuse[] = { 0.8f, 0.2f, 0.8f, 1.0f };

		glClear (GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		glPushMatrix ();
		glClipPlane (GL_CLIP_PLANE0, eqn);  /*  slice objects   */
		glEnable (GL_CLIP_PLANE0); 

		glPushMatrix ();
		glTranslatef (0.0f, 2.0f, 0.0f);
		auxSolidTeapot (1.0);		/*  one-sided lighting  */
		glPopMatrix ();

		/*  two-sided lighting, but same material  */
		glLightModeli (GL_LIGHT_MODEL_TWO_SIDE, GL_TRUE);
		glMaterialfv (GL_FRONT_AND_BACK, GL_DIFFUSE, mat_diffuse);
		glPushMatrix ();
		glTranslatef (0.0f, 0.0f, 0.0f);
		auxSolidTeapot (1.0);
		glPopMatrix ();

		/*  two-sided lighting, two different materials	*/
		glMaterialfv (GL_FRONT, GL_DIFFUSE, mat_diffuse);
		glMaterialfv (GL_BACK, GL_DIFFUSE, back_diffuse);
		glPushMatrix ();
		glTranslatef (0.0f, -2.0f, 0.0f);
		auxSolidTeapot (1.0);
		glPopMatrix ();

		glLightModeli (GL_LIGHT_MODEL_TWO_SIDE, GL_FALSE);
		glDisable (GL_CLIP_PLANE0);
		glPopMatrix ();
		glFlush(g);
	}

	private void myReshape (int w, int h) {
		glViewport (0, 0, w, h);
		glMatrixMode (GL_PROJECTION);
		glLoadIdentity ();
		if (w <= h) {
			glOrtho (-4.0f, 4.0f,
					-4.0f*(float)h/(float)w, 
					4.0f*(float)h/(float)w, 
					-10.0f, 10.0f);
		} else {
			glOrtho (-4.0f*(float)w/(float)h, 
					4.0f*(float)w/(float)h, 
					-4.0f,  4.0f,
					-10.0f, 10.0f);
		}
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
