package examples.jgl.application;

import org.jgl.GL;

import br.com.etyllica.core.graphics.Graphic;

public class BezSurf extends GL {

	public BezSurf(int w, int h) {
		super(w,h);
	}

	public void load() {

		loading = 99;

		myinit ();

		loading = 100;
	}

	private static final float ctrlpoints [][][] = {
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

	private void myinit () {
		glClearColor (0.0f, 0.0f, 0.0f, 1.0f);
		glMap2f (GL_MAP2_VERTEX_3, 0.0f, 1.0f, 3, 4,
				0.0f, 1.0f, 12, 4, ctrlpoints);
		glEnable (GL_MAP2_VERTEX_3);
		glEnable (GL_DEPTH_TEST);
		glShadeModel (GL_FLAT);

		myReshape (w, h);
	}

	public void draw(Graphic g) {
		int i, j;

		glClear (GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glColor3f (1.0f, 1.0f, 1.0f);
		glPushMatrix ();
		glRotatef (85.0f, 1.0f, 1.0f, 1.0f);
		for (j = 0; j <= 8; j++) {
			glBegin (GL_LINE_STRIP);
			for (i = 0; i <= 30; i++) {
				glEvalCoord2f ((float)i/30.0f, (float)j/8.0f);
			}
			glEnd ();
			glBegin (GL_LINE_STRIP);
			for (i = 0; i <= 30; i++) {
				glEvalCoord2f ((float)j/8.0f, (float)i/30.0f);
			}
			glEnd ();
		}
		
		glPopMatrix();
		
		glFlush(g);
	}


	private void myReshape (float w, float h) {
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

}
