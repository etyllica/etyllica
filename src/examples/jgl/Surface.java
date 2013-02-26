package examples.jgl;

import jgl.GLU;
import jgl.glu.GLUnurbsObj;
import br.com.etyllica.core.video.Grafico;

public class Surface extends GLU{
	
	public Surface() {
		super();
	}

	private float ctlpoints [][][] = new float [4][4][3];
	private GLUnurbsObj theNurb;

	private void init_surface () {
		int u, v;
		for (u = 0; u < 4; u++) {
			for (v = 0; v < 4; v++) {
				ctlpoints [u][v][0] = 2.0f * ((float)u - 1.5f);
				ctlpoints [u][v][1] = 2.0f * ((float)v - 1.5f);

				if (((u == 1) || (u == 2)) && ((v == 1) || (v == 2))) {
					ctlpoints [u][v][2] = 3.0f;
				} else {
					ctlpoints [u][v][2] = -3.0f;
				}
			}
		}
	}

	public void load() {
		
		float mat_diffuse[] = { 0.7f, 0.7f, 0.7f, 1.0f };
		float mat_specular[] = { 1.0f, 1.0f, 1.0f, 1.0f };
		float mat_shininess[] = { 100.0f };

		glClearColor (0.0f, 0.0f, 0.0f, 1.0f);
		glMaterialfv (GL_FRONT, GL_DIFFUSE, mat_diffuse);
		glMaterialfv (GL_FRONT, GL_SPECULAR, mat_specular);
		glMaterialfv (GL_FRONT, GL_SHININESS, mat_shininess);

		glEnable (GL_LIGHTING);
		glEnable (GL_LIGHT0);
		glDepthFunc (GL_LEQUAL);
		glEnable (GL_DEPTH_TEST);
		glEnable (GL_AUTO_NORMAL);
		glEnable (GL_NORMALIZE);

		init_surface ();

		theNurb = gluNewNurbsRenderer ();
		gluNurbsProperty (theNurb,GLU_SAMPLING_TOLERANCE, 25.0f);
		gluNurbsProperty (theNurb,GLU_DISPLAY_MODE,GLU_FILL);

		myReshape(w,h);
		loading = 100;
	}

	public void draw(Grafico g) {
		float knots [] = { 0.0f, 0.0f, 0.0f, 0.0f,
				1.0f, 1.0f, 1.0f, 1.0f };

		glClear (GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		glPushMatrix ();
		glRotatef (330.0f, 1.0f, 0.0f, 0.0f);
		glScalef (0.5f, 0.5f, 0.5f);

		gluBeginSurface (theNurb);
		gluNurbsSurface (theNurb,
				8, knots,
				8, knots,
				4 * 3,
				3,
				ctlpoints,
				4, 4,
				GL_MAP2_VERTEX_3);
		gluEndSurface (theNurb);

		glPopMatrix ();
		glFlush (g);
	}


	private void myReshape (int w, int h) {
		glViewport (0, 0, w, h);
		glMatrixMode (GL_PROJECTION);
		glLoadIdentity ();
		gluPerspective (45.0, (double)w/(double)h, 3.0, 8.0);
		glMatrixMode (GL_MODELVIEW);
		glLoadIdentity ();
		glTranslatef (0.0f, 0.0f, -5.0f);
	}

}
