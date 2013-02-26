package examples.jgl;

import jgl.GLAUX;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyboardEvent;
import br.com.etyllica.core.event.Tecla;
import br.com.etyllica.core.video.Grafico;

public class Planet extends GLAUX{
	
	public Planet() {
		super();
	}
	
	@Override
	public void load(){
		
		myReshape(w, h);
		
		updateAtFixedRate(50);
		
		loading = 100;
		
	}

	private static int month = 0, day = 0;

	private void dayAdd(){ day = (day + 10) % 360; }
	private void daySubtract(){ day = (day - 10) % 360; }
	private void monthAdd(){ month = (month + 5) % 360; }
	private void monthSubtract(){ month = (month - 5) % 360; }

	@Override
	public void timeUpdate(){
		monthAdd();
		dayAdd();
	}
	
	@Override
	public GUIEvent updateKeyboard(KeyboardEvent event) {

		if(event.getPressed(Tecla.TSK_SETA_ESQUERDA)){
			monthSubtract();
		}
		else if(event.getPressed(Tecla.TSK_SETA_DIREITA)){
			monthAdd();
		}
		else if(event.getPressed(Tecla.TSK_SETA_CIMA)){
			dayAdd();
		}
		else if(event.getPressed(Tecla.TSK_SETA_BAIXO)){
			daySubtract();
		}

		return GUIEvent.NONE;

	}

	@Override
	public void draw(Grafico g) {
		glClear (GL_COLOR_BUFFER_BIT);

		glColor3f (1.0f, 1.0f, 1.0f);
		glPushMatrix ();
		auxWireSphere (1.0);		/* draw sun */
		glRotatef ((float)month, 0.0f, 1.0f, 0.0f);
		glTranslatef (2.0f, 0.0f, 0.0f);
		glRotatef ((float)day, 0.0f, 1.0f, 0.0f);
		auxWireSphere (0.2);		/* draw smaller planet */
		glPopMatrix ();
		glFlush(g);
	}

	private void myReshape (int w, int h) {
		glShadeModel (GL_FLAT);
		glViewport (0, 0, w, h);
		glMatrixMode (GL_PROJECTION);
		glLoadIdentity ();
		gluPerspective (60.0, (double)w/(double)h,1.0, 20.0);
		glMatrixMode (GL_MODELVIEW);
		glLoadIdentity ();
		glTranslatef (0.0f, 0.0f, -5.0f);
	}
}
