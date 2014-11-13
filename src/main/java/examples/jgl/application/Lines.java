package examples.jgl.application;

import org.jgl.GL;
import org.jgl.GLU;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;

public class Lines extends GLU{

	public Lines(int w, int h) {
		super(w,h);
	}

	@Override
	public void load() {
		myinit();
		loading = 100;
	}

	private void drawOneLine (float x1, float y1, float x2, float y2) {
		glBegin (GL_LINES);
		glVertex2f (x1, y1);
		glVertex2f (x2, y2);
		glEnd ();
	}

	public void draw(Graphic g){
		int i;

		glClear (GL_COLOR_BUFFER_BIT);

		/* draw all lines in white */
		glColor3f (1.0f, 1.0f, 1.0f);

		/* in 1st row, 3 lines, each with a different stipple */
		glEnable (GL_LINE_STIPPLE);
		glLineStipple (1, (short)0x0101);	/* dotted */
		drawOneLine (50.0f, 125.0f, 150.0f, 125.0f);
		glLineStipple (1, (short)0x00FF);	/* dashed */
		drawOneLine (150.0f, 125.0f, 250.0f, 125.0f);
		glLineStipple (1, (short)0x1C47);	/* dash/dot/dash */
		drawOneLine (250.0f, 125.0f, 350.0f, 125.0f);

		/* in 2nd row, 3 width lines, each with different stipple */
		glLineWidth (5.0f);
		glLineStipple (1, (short)0x0101);
		drawOneLine (50.0f, 100.0f, 150.0f, 100.0f);
		glLineStipple (1, (short)0x00FF);
		drawOneLine (150.0f, 100.0f, 250.0f, 100.0f);
		glLineStipple (1, (short)0x1C47);
		drawOneLine (250.0f, 100.0f, 350.0f, 100.0f);
		glLineWidth (1.0f);

		/* in 3rd row, 6 lines, with dash/dot/dash stipple, */
		/* as part of a single connected line strip */
		glLineStipple (1, (short)0x1C47);
		glBegin (GL_LINE_STRIP);
		for (i = 0; i < 7; i++) {
			glVertex2f (50.0f + ((float)i * 50.0f), 75.0f);
		}
		glEnd ();

		/* in 4th row, 6 independent lines, */
		/* with dash/dot/dash stipple */
		for (i = 0; i < 6; i++) {
			drawOneLine (50.0f + ((float)i * 50.0f), 50.0f,
					50.0f + ((float)(i + 1) * 50.0f), 50.0f);
		}

		/* in 5th row, 1 line, with dash/dot/dash stipple */
		/* and repeat factor of 5 */
		glLineStipple (1, (short)0x1C47);
		drawOneLine (50.0f, 25.0f, 350.0f, 25.0f);

		glFlush(g);
	}

	private void myinit () {
		/* background to be cleared to black */
		glClearColor (0.0f, 0.0f, 0.0f, 0.0f);
		glShadeModel (GL_FLAT);

		myReshape(w, h);
	}

	public void myReshape (float w, float h) {
		glViewport (0, 0, w, h);
		glMatrixMode (GL.GL_PROJECTION);
		glLoadIdentity ();
		gluOrtho2D (0.0, (double)w, 0.0, (double)h);
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
