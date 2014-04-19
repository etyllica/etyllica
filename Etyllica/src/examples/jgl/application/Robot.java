package examples.jgl.application;

import org.jgl.GLAUX;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;

public class Robot extends GLAUX{

	public Robot(int w, int h) {
		super(w,h);
	}

	private static int shoulder = 0, elbow = 0;

	private void elbowAdd() { elbow =(elbow + 5) % 360; }
	private void elbowSubtract() { elbow = (elbow - 5) % 360; }
	private void shoulderAdd() { shoulder = (shoulder + 5) % 360; }
	private void shoulderSubtract() { shoulder = (shoulder - 5) % 360; }

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		
		if(event.isKeyDown(KeyEvent.TSK_CTRL_ESQUERDA)||event.isKeyDown(KeyEvent.TSK_CTRL_DIREITA)){
			
			if(event.isKeyDown(KeyEvent.TSK_SETA_DIREITA)){
				shoulderAdd();
			}
			else if(event.isKeyDown(KeyEvent.TSK_SETA_ESQUERDA)){
				shoulderSubtract();
			}
			else if(event.isKeyDown(KeyEvent.TSK_SETA_CIMA)){
				elbowAdd();
			}
			else if(event.isKeyDown(KeyEvent.TSK_SETA_BAIXO)){
				elbowSubtract();
			}
			
		}else{
			
			if(event.isKeyDown(KeyEvent.TSK_SETA_DIREITA)){
				shoulderAdd();
			}
			else if(event.isKeyDown(KeyEvent.TSK_SETA_ESQUERDA)){
				shoulderSubtract();
			}
			else if(event.isKeyDown(KeyEvent.TSK_SETA_CIMA)){
				elbowAdd();
			}
			else if(event.isKeyDown(KeyEvent.TSK_SETA_BAIXO)){
				elbowSubtract();
			}
			
		}

		return GUIEvent.NONE;
	}

	@Override
	public void draw(Graphic g) {
		glClear(GL_COLOR_BUFFER_BIT);
		glColor3f(1.0f, 1.0f, 1.0f);
		glPushMatrix();

		glTranslatef(-1.0f, 0.0f, 0.0f);
		glRotatef((float)shoulder, 0.0f, 0.0f, 1.0f);
		glTranslatef(1.0f, 0.0f, 0.0f);
		auxWireBox(2.0, 0.4, 1.0);	/* shoulder */

		glTranslatef(1.0f, 0.0f, 0.0f);
		glRotatef((float)elbow, 0.0f, 0.0f, 1.0f);
		glTranslatef(1.0f, 0.0f, 0.0f);
		auxWireBox(2.0, 0.4, 1.0);	/* elbow */

		glPopMatrix();
		glFlush(g);
	}

	private void myinit() {
		glShadeModel(GL_FLAT);
		myReshape(w, h);
	}

	public void load(){
		myinit();
		loading = 100;
	}

	private void myReshape(float w, float h) {
		glViewport(0, 0, w, h);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective(60.0,(double)w/(double)h, 1.0, 20.0);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glTranslatef(0.0f, 0.0f, -5.0f);
	}
	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent.NONE;
	}

}
