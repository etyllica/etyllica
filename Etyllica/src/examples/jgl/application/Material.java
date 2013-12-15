package examples.jgl.application;

import org.jgl.GLAUX;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.video.Graphic;

public class Material extends GLAUX{

	public Material(int w, int h) {
		super(w,h);
	}

	@Override
	public void load() {
		myinit();
		loading = 100;
	}

	private void myinit () {
		float ambient [] = {0.0f,0.0f,0.0f,1.0f};
		float diffuse [] = {1.0f,1.0f,1.0f,1.0f};
		//float specular [] = {1.0f,1.0f,1.0f,1.0f};
		float position [] = {0.0f,3.0f,2.0f,0.0f};
		float lmodel_ambient [] = {0.4f,0.4f,0.4f,1.0f};
		float local_view [] = {0.0f};

		glEnable (GL_DEPTH_TEST);
		glDepthFunc (GL_LESS);

		glLightfv (GL_LIGHT0, GL_AMBIENT, ambient);
		glLightfv (GL_LIGHT0, GL_DIFFUSE, diffuse);
		glLightfv (GL_LIGHT0, GL_POSITION, position);
		glLightModelfv (GL_LIGHT_MODEL_AMBIENT, lmodel_ambient);
		glLightModelfv (GL_LIGHT_MODEL_LOCAL_VIEWER, local_view);

		glEnable (GL_LIGHTING);
		glEnable (GL_LIGHT0);

		glClearColor (0.0f, 0.1f, 0.1f, 0.0f);
		
		myReshape(w, h);
	}

	public void draw(Graphic g) {
		float no_mat[]={0.0f,0.0f,0.0f,1.0f};
		float mat_ambient[]={0.7f,0.7f,0.7f,1.0f};
		float mat_ambient_color[]={0.8f,0.8f,0.2f,1.0f};
		float mat_diffuse[]={0.1f,0.5f,0.8f,1.0f};
		float mat_specular[]={1.0f,1.0f,1.0f,1.0f};
		float no_shininess[]={0.0f};
		float low_shininess[]={5.0f};
		float high_shininess[]={100.0f};
		float mat_emission[]={0.3f,0.2f,0.2f,0.0f};

		glClear (GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		/*  draw sphere in first row, first column
		 *  diffuse reflection only; no ambient or specular
		 */
		glPushMatrix ();
		glTranslatef (-3.75f, 3.0f, 0.0f);
		glMaterialfv (GL_FRONT, GL_AMBIENT, no_mat);
		glMaterialfv (GL_FRONT, GL_DIFFUSE, mat_diffuse);
		glMaterialfv (GL_FRONT, GL_SPECULAR, no_mat);
		glMaterialfv (GL_FRONT, GL_SHININESS, no_shininess);
		glMaterialfv (GL_FRONT, GL_EMISSION, no_mat);
		auxSolidSphere (1.0);
		glPopMatrix ();

		/*  draw sphere in first row, second column
		 *  diffuse and specular reflection; low shininess; no ambient
		 */
		glPushMatrix ();
		glTranslatef (-1.25f, 3.0f, 0.0f);
		glMaterialfv (GL_FRONT, GL_AMBIENT, no_mat);
		glMaterialfv (GL_FRONT, GL_DIFFUSE, mat_diffuse);
		glMaterialfv (GL_FRONT, GL_SPECULAR, mat_specular);
		glMaterialfv (GL_FRONT, GL_SHININESS, low_shininess);
		glMaterialfv (GL_FRONT, GL_EMISSION, no_mat);
		auxSolidSphere (1.0);
		glPopMatrix ();

		/*  draw sphere in first row, third column
		 *  diffuse and specular reflection; high shininess; no ambient
		 */
		glPushMatrix ();
		glTranslatef (1.25f, 3.0f, 0.0f);
		glMaterialfv (GL_FRONT, GL_AMBIENT, no_mat);
		glMaterialfv (GL_FRONT, GL_DIFFUSE, mat_diffuse);
		glMaterialfv (GL_FRONT, GL_SPECULAR, mat_specular);
		glMaterialfv (GL_FRONT, GL_SHININESS, high_shininess);
		glMaterialfv (GL_FRONT, GL_EMISSION, no_mat);
		auxSolidSphere (1.0);
		glPopMatrix ();

		/*  draw sphere in first row, fourth column
		 *  diffuse reflection; emission; no ambient or specular reflection
		 */
		glPushMatrix ();
		glTranslatef (3.75f, 3.0f, 0.0f);
		glMaterialfv (GL_FRONT, GL_AMBIENT, no_mat);
		glMaterialfv (GL_FRONT, GL_DIFFUSE, mat_diffuse);
		glMaterialfv (GL_FRONT, GL_SPECULAR, no_mat);
		glMaterialfv (GL_FRONT, GL_SHININESS, no_shininess);
		glMaterialfv (GL_FRONT, GL_EMISSION, mat_emission);
		auxSolidSphere (1.0);
		glPopMatrix ();

		/*  draw sphere in second row, first column
		 *  ambient and diffuse reflection; no specular
		 */
		glPushMatrix ();
		glTranslatef (-3.75f, 0.0f, 0.0f);
		glMaterialfv (GL_FRONT, GL_AMBIENT, mat_ambient);
		glMaterialfv (GL_FRONT, GL_DIFFUSE, mat_diffuse);
		glMaterialfv (GL_FRONT, GL_SPECULAR, no_mat);
		glMaterialfv (GL_FRONT, GL_SHININESS, no_shininess);
		glMaterialfv (GL_FRONT, GL_EMISSION, no_mat);
		auxSolidSphere (1.0);
		glPopMatrix ();

		/*  draw sphere in second row, second column
		 *  ambient, diffuse and specular reflection; low shininess
		 */
		glPushMatrix ();
		glTranslatef (-1.25f, 0.0f, 0.0f);
		glMaterialfv (GL_FRONT, GL_AMBIENT, mat_ambient);
		glMaterialfv (GL_FRONT, GL_DIFFUSE, mat_diffuse);
		glMaterialfv (GL_FRONT, GL_SPECULAR, mat_specular);
		glMaterialfv (GL_FRONT, GL_SHININESS, low_shininess);
		glMaterialfv (GL_FRONT, GL_EMISSION, no_mat);
		auxSolidSphere (1.0);
		glPopMatrix ();

		/*  draw sphere in second row, third column
		 *  ambient, diffuse and specular reflection; high shininess
		 */
		glPushMatrix ();
		glTranslatef (1.25f, 0.0f, 0.0f);
		glMaterialfv (GL_FRONT, GL_AMBIENT, mat_ambient);
		glMaterialfv (GL_FRONT, GL_DIFFUSE, mat_diffuse);
		glMaterialfv (GL_FRONT, GL_SPECULAR, mat_specular);
		glMaterialfv (GL_FRONT, GL_SHININESS, high_shininess);
		glMaterialfv (GL_FRONT, GL_EMISSION, no_mat);
		auxSolidSphere (1.0);
		glPopMatrix ();

		/*  draw sphere in second row, fourth column
		 *  ambient and diffuse reflection; emission; no specular
		 */
		glPushMatrix (); 
		glTranslatef (3.75f, 0.0f, 0.0f);
		glMaterialfv (GL_FRONT, GL_AMBIENT, mat_ambient);
		glMaterialfv (GL_FRONT, GL_DIFFUSE, mat_diffuse);
		glMaterialfv (GL_FRONT, GL_SPECULAR, no_mat);
		glMaterialfv (GL_FRONT, GL_SHININESS, no_shininess);
		glMaterialfv (GL_FRONT, GL_EMISSION, mat_emission);
		auxSolidSphere (1.0);
		glPopMatrix ();

		/*  draw sphere in third row, first column
		 *  colored ambient and diffuse reflection; no specular
		 */
		glPushMatrix ();
		glTranslatef (-3.75f, -3.0f, 0.0f);
		glMaterialfv (GL_FRONT, GL_AMBIENT, mat_ambient_color);
		glMaterialfv (GL_FRONT, GL_DIFFUSE, mat_diffuse);
		glMaterialfv (GL_FRONT, GL_SPECULAR, no_mat);
		glMaterialfv (GL_FRONT, GL_SHININESS, no_shininess);
		glMaterialfv (GL_FRONT, GL_EMISSION, no_mat);
		auxSolidSphere (1.0);
		glPopMatrix ();

		/*  draw sphere in third row, second column
		 *  colored ambient, diffuse and specular reflection; low shininess
		 */
		glPushMatrix ();
		glTranslatef (-1.25f, -3.0f, 0.0f);
		glMaterialfv (GL_FRONT, GL_AMBIENT, mat_ambient_color);
		glMaterialfv (GL_FRONT, GL_DIFFUSE, mat_diffuse);
		glMaterialfv (GL_FRONT, GL_SPECULAR, mat_specular);
		glMaterialfv (GL_FRONT, GL_SHININESS, low_shininess);
		glMaterialfv (GL_FRONT, GL_EMISSION, no_mat);
		auxSolidSphere (1.0);
		glPopMatrix ();

		/*  draw sphere in third row, third column
		 *  colored ambient, diffuse and specular reflection; high shininess
		 */
		glPushMatrix ();
		glTranslatef (1.25f, -3.0f, 0.0f);
		glMaterialfv (GL_FRONT, GL_AMBIENT, mat_ambient_color);
		glMaterialfv (GL_FRONT, GL_DIFFUSE, mat_diffuse);
		glMaterialfv (GL_FRONT, GL_SPECULAR, mat_specular);
		glMaterialfv (GL_FRONT, GL_SHININESS, high_shininess);
		glMaterialfv (GL_FRONT, GL_EMISSION, no_mat);
		auxSolidSphere (1.0);
		glPopMatrix ();

		/*  draw sphere in third row, fourth column
		 *  colored ambient and diffuse reflection; emission; no specular
		 */
		glPushMatrix ();
		glTranslatef (3.75f, -3.0f, 0.0f);
		glMaterialfv (GL_FRONT, GL_AMBIENT, mat_ambient_color);
		glMaterialfv (GL_FRONT, GL_DIFFUSE, mat_diffuse);
		glMaterialfv (GL_FRONT, GL_SPECULAR, no_mat);
		glMaterialfv (GL_FRONT, GL_SHININESS, no_shininess);
		glMaterialfv (GL_FRONT, GL_EMISSION, mat_emission);
		auxSolidSphere (1.0);
		glPopMatrix ();

		glFlush(g);

	}


	private void myReshape (float w, float h) {
		glViewport (0, 0, w, h);
		glMatrixMode (GL_PROJECTION);
		glLoadIdentity ();
		if (w <= (h * 2)) {
			glOrtho (-6.0f,  6.0f,
					-3.0f * ((float)h * 2) / (float)w,
					3.0f * ((float)h * 2) / (float)w,
					-10.0f, 10.0f);
		} else {
			glOrtho (-6.0f * (float)w / ((float)h * 2),
					6.0f * (float)w / ((float)h * 2),
					-3.0f,  3.0f,
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
