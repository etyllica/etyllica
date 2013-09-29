package examples.jgl.application;

import jgl.GL;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.video.Graphic;

public class BezMesh extends GL{

	public BezMesh(int w, int h) {
		super(w,h);
	}

	public void load() {

		loading = 99;

		myinit ();

		loading = 100;

	}

	private static final float ctrlpoints [][][] = {
		/*
		{{-1.5f, -1.5f,  4.0f},
			{-0.5f, -1.5f,  2.0f},
			{ 0.5f, -1.5f, -1.0f},
			{ 1.5f, -1.5f,  2.0f}},
			{{-1.5f, -0.5f,  1.0f},
				{-0.5f, -0.5f,  3.0f},
				{ 0.5f, -0.5f,  0.0f},
				{ 1.5f, -0.5f, -1.0f}},
				{{-1.5f,  0.5f,  4.0f},
					{-0.5f,  0.5f,  0.0f},
					{ 0.5f,  0.5f,  3.0f},
					{ 1.5f,  0.5f,  4.0f}},
					{{-1.5f,  1.5f, -2.0f},
						{-0.5f,  1.5f, -2.0f},
						{ 0.5f,  1.5f,  0.0f},
						{ 1.5f,  1.5f, -1.0f}}};
		 */
		//Pontos
		{{0.0f, 0.0f,  0.0f},
			{0.0f, 0.0f,  1.0f},
			{ 0.0f, -1f, 0f},
			{ 1f, 0f,  0f}},
			{{0f, 0f,  1.0f},
				{1f, 0f,  1f},
				{ 0f, 1f,  1f},
				{ 1f, 1f, 1f}},
				{{-1.5f,  0.5f,  4.0f},
					{-0.5f,  0.5f,  0.0f},
					{ 0.5f,  0.5f,  3.0f},
					{ 1.5f,  0.5f,  4.0f}},
					{{-1.5f,  1.5f, -2.0f},
						{-0.5f,  1.5f, -2.0f},
						{ 0.5f,  1.5f,  0.0f},
						{ 1.5f,  1.5f, -1.0f}}};



	private void initlights () {
		float ambient[] = {0.2f, 0.2f, 0.2f, 1.0f};
		float position[] = {0.0f, 0.0f, 2.0f, 1.0f};
		float mat_diffuse[] = {0.6f, 0.6f, 0.6f, 1.0f};
		float mat_specular[] = {1.0f, 1.0f, 1.0f, 1.0f};
		float mat_shininess[] = {50.0f};

		glEnable (GL_LIGHTING);
		glEnable (GL_LIGHT0);
		glLightfv (GL_LIGHT0, GL_AMBIENT, ambient);
		glLightfv (GL_LIGHT0, GL_POSITION, position);

		glMaterialfv (GL_FRONT, GL_DIFFUSE, mat_diffuse);
		glMaterialfv (GL_FRONT, GL_SPECULAR, mat_specular);
		glMaterialfv (GL_FRONT, GL_SHININESS, mat_shininess);
	}

	private void myinit () {
		glClearColor (0.0f, 0.0f, 0.0f, 1.0f);
		glMap2f (GL_MAP2_VERTEX_3, 0.0f, 1.0f, 3, 4,
				0.0f, 1.0f, 12, 4, ctrlpoints);
		glEnable (GL_MAP2_VERTEX_3);
		glEnable (GL_AUTO_NORMAL);
		glEnable (GL_NORMALIZE);
		glMapGrid2f (20, 0.0f, 1.0f, 20, 0.0f, 1.0f);
		initlights ();		/* for lighted version only */

		myReshape (w, h);

		System.out.println(ctrlpoints.length);
		System.out.println(ctrlpoints[0].length);
		System.out.println(ctrlpoints[0][0].length);
	}

	public void draw(Graphic g) {
		glClear (GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glPushMatrix ();
		glRotatef (85.0f, 1.0f, 1.0f, 1.0f);
		glEvalMesh2 (GL_FILL, 0, 20, 0, 20);
		glPopMatrix ();
		glFlush (g);
	}

	private void myReshape (int w, int h) {
		glViewport (0, 0, w, h);
		glMatrixMode (GL_PROJECTION);
		glLoadIdentity ();
		if (w <= h) {
			glOrtho (-4.0f, 4.0f,
					-4.0f *(float)h/(float)w,
					4.0f *(float)h/(float)w,
					-4.0f, 4.0f);
		} else {
			glOrtho (-4.0f *(float)w/(float)h,
					4.0f *(float)w/(float)h,
					-4.0f, 4.0f,
					-4.0f, 4.0f);
		}
		glMatrixMode (GL_MODELVIEW);
		glLoadIdentity ();
	}

	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent	.NONE;
	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent	.NONE;
	}

}
