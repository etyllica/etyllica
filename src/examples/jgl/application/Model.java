package examples.jgl.application;


import jgl.GL;
import br.com.etyllica.core.video.Grafico;

public class Model extends GL{
	
	public Model() {
		super();
	}

	@Override
	public void load(){
		myinit();
		loading = 100;
	}

	private void draw_triangle () {
		glBegin (GL_LINE_LOOP);
		glVertex2f (  0.0f,  25.0f);
		glVertex2f ( 25.0f, -25.0f);
		glVertex2f (-25.0f, -25.0f);
		glEnd ();
	}

	public void draw(Grafico g) {
		glClearColor (0.0f, 0.0f, 0.0f, 1.0f);
		glClear (GL_COLOR_BUFFER_BIT);

		glLoadIdentity ();
		glColor3f (1.0f, 1.0f, 1.0f);
		draw_triangle ();			/* solid lines */

		glEnable (GL_LINE_STIPPLE);	/* dashed lines */
		glLineStipple (1, (short)0xF0F0);
		glLoadIdentity ();
		glTranslatef (-20.0f, 0.0f, 0.0f);
		draw_triangle ();

		glLineStipple (1, (short)0xF00F);	/* long dashed lines */
		glLoadIdentity ();
		glScalef (1.5f, 0.5f, 1.0f);
		draw_triangle ();

		glLineStipple (1, (short)0x8888);	/* dotted lines */
		glLoadIdentity ();
		glRotatef (90.0f, 0.0f, 0.0f, 1.0f);
		draw_triangle ();
		glDisable (GL_LINE_STIPPLE);

		glFlush (g);
	}

	private void myinit () {
		glShadeModel (GL_FLAT);
		myReshape(w, h);
	}

	private void myReshape (int w, int h) {
		glViewport (0, 0, w, h);
		glMatrixMode (GL_PROJECTION);
		glLoadIdentity ();
		if (w <= h) {
			glOrtho (-50.0f, 50.0f,
					-50.0f*(float)h/(float)w, 
					50.0f*(float)h/(float)w, 
					-1.0f, 1.0f);
		} else {
			glOrtho (-50.0f*(float)w/(float)h, 
					50.0f*(float)w/(float)h, 
					-50.0f, 50.0f,
					-1.0f,  1.0f);
		}
		glMatrixMode (GL_MODELVIEW);
	}

}
