package examples.jgl.application;

import org.jgl.GL;

import br.com.etyllica.core.graphics.Graphic;

public class Simple extends GL{

	public Simple(int w, int h) {
		super(w,h);
	}

	@Override
	public void load() {
		loading = 100;
	}

	@Override
	public void draw(Graphic g) {
		
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		glClear(GL_COLOR_BUFFER_BIT);
		glColor3f(1.0f, 1.0f, 1.0f);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(-1.0f, 1.0f, -1.0f, 1.0f, -1.0f, 1.0f);
		glBegin(GL_POLYGON);
		glVertex2f(-0.5f, -0.5f);
		glVertex2f(-0.5f,  0.5f);
		glVertex2f( 0.5f,  0.5f);
		glVertex2f( 0.5f, -0.5f);
		glEnd();
		
		glFlush(g);
	}

}
