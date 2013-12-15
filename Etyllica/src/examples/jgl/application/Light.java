package examples.jgl.application;

import org.jgl.GLAUX;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.video.Graphic;

public class Light extends GLAUX{

	public Light(int w, int h) {
		super(w,h);
	}

	public void load() {
		myinit();
		loading = 100;
	}

	private void myinit() {
		float mat_specular[] = { 1.0f, 1.0f, 1.0f, 1.0f };
		float mat_shininess[] = { 50.0f };
		float light_position[] = { 1.0f, 1.0f, 1.0f, 0.0f };

		glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular);
		glMaterialfv(GL_FRONT, GL_SHININESS, mat_shininess);
		glLightfv(GL_LIGHT0, GL_POSITION, light_position);

		glEnable(GL_LIGHTING);
		glEnable(GL_LIGHT0);
		glDepthFunc(GL_LEQUAL);
		glEnable(GL_DEPTH_TEST);

		myReshape(w, h);

	}

	public void draw(Graphic g) {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		auxSolidSphere(1.0);
		glFlush(g);
	}

	private void myReshape(float w, float h) {
		glViewport(0, 0, w, h);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		if(w <= h) {
			glOrtho(-1.5f,   1.5f, 
					-1.5f*(float)h/(float)w, 
					1.5f*(float)h/(float)w,
					-10.0f, 10.0f);
		} else {
			glOrtho( -1.5f*(float)w/(float)h,
					1.5f*(float)w/(float)h,
					-1.5f,  1.5f,
					-10.0f, 10.0f);
		}
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
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
