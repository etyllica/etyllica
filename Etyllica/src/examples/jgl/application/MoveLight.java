package examples.jgl.application;

import org.jgl.GLAUX;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.input.mouse.MouseButton;
import br.com.etyllica.core.video.Graphic;

public class MoveLight extends GLAUX{

	public MoveLight(int w, int h) {
		super(w,h);
	}

	@Override
	public void load(){
		myinit();
		loading = 100;
	}	
	
	private int spin = 0;

	private void movelight () {
		spin = (spin + 30) % 360;
	}
	
	@Override
	public GUIEvent updateMouse(PointerEvent event){

		if(event.onButtonDown(MouseButton.MOUSE_BUTTON_LEFT)){
			movelight();
		}

		return GUIEvent	.NONE;
	}

	private void myinit () {
		glEnable (GL_LIGHTING);
		glEnable (GL_LIGHT0);

		glDepthFunc (GL_LESS);
		glEnable (GL_DEPTH_TEST);

		myReshape(w, h);
	}

	public void draw(Graphic g) {
		float position [] = {0.0f, 0.0f, 1.5f, 1.0f};

		glClear (GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glPushMatrix ();
		glTranslatef (0.0f, 0.0f, -5.0f);

		glPushMatrix ();
		glRotated ((double)spin, 1.0, 0.0, 0.0);
		glRotated (0.0, 1.0, 0.0, 0.0);
		glLightfv (GL_LIGHT0, GL_POSITION, position);

		glTranslated (0.0, 0.0, 1.5);
		glDisable (GL_LIGHTING);
		glColor3f (0.0f, 1.0f, 1.0f);
		auxWireCube (0.1);
		glEnable (GL_LIGHTING);
		glPopMatrix ();

		auxSolidTorus (0.275, 0.85);
		glPopMatrix ();
		glFlush(g);
	}

	private void myReshape (float w, float h) {
		glViewport (0, 0, w, h);
		glMatrixMode (GL_PROJECTION);
		glLoadIdentity ();
		gluPerspective (40.0, (float)w/(float)h, 1.0, 20.0);
		glMatrixMode (GL_MODELVIEW);
	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent.NONE;
	}
}
