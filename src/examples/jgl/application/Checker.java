package examples.jgl.application;

import jgl.GLU;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyboardEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.video.Grafico;

public class Checker extends GLU{

	public Checker(int w, int h) {
		super(w,h);
	}
	

	@Override
	public void load() {
		myinit();
		loading = 100;
	}

    private static final int checkImageWidth = 64;
    private static final int checkImageHeight = 64;
    private byte checkImage [][][] =
    		   new byte [checkImageWidth][checkImageHeight][3];

	private void makeCheckImage () {
		int i, j;
		byte c;

		for (i = 0; i < checkImageWidth; i++) {
			for (j = 0; j < checkImageHeight; j++) {
				if ((((i & 0x8) == 0) ^ ((j & 0x8)) == 0)) { c = (byte)255;
				} else { c = (byte)0; }
				checkImage [i][j][0] = c;
				checkImage [i][j][1] = c;
				checkImage [i][j][2] = c;
			}
		}
	}

	private void myinit () {
		glClearColor (0.0f, 0.0f, 0.0f, 0.0f);
		glEnable (GL_DEPTH_TEST);
		glDepthFunc (GL_LEQUAL);
		makeCheckImage ();
		glPixelStorei (GL_UNPACK_ALIGNMENT, 1);
		glTexImage2D (GL_TEXTURE_2D, 0, 3, checkImageWidth,
				checkImageHeight, 0, GL_RGB, GL_UNSIGNED_BYTE, checkImage);
		glTexParameterf (GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP);
		glTexParameterf (GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
		glTexParameterf (GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexParameterf (GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexEnvf (GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_DECAL);
		glEnable (GL_TEXTURE_2D);
		glShadeModel (GL_FLAT);

		myReshape(w, h);
	}

	public void draw(Grafico g){
		glClear (GL_COLOR_BUFFER_BIT);
		glBegin (GL_QUADS);
		glTexCoord2f (0.0f, 0.0f);
		glVertex3f (-2.0f, -1.0f, 0.0f);
		glTexCoord2f (0.0f, 1.0f);
		glVertex3f (-2.0f, 1.0f, 0.0f);
		glTexCoord2f (1.0f, 1.0f);
		glVertex3f (0.0f, 1.0f, 0.0f);
		glTexCoord2f (1.0f, 0.0f);
		glVertex3f (0.0f, -1.0f, 0.0f);

		glTexCoord2f (0.0f, 0.0f);
		glVertex3f (1.0f, -1.0f, 0.0f);
		glTexCoord2f (0.0f, 1.0f);
		glVertex3f (1.0f, 1.0f, 0.0f);
		glTexCoord2f (1.0f, 1.0f);
		glVertex3f (2.41421f, 1.0f, -1.41421f);
		glTexCoord2f (1.0f, 0.0f);
		glVertex3f (2.41421f, -1.0f, -1.41421f);
		glEnd ();
		glFlush (g);
	}

	private void myReshape (int w, int h) {
		glViewport (0, 0, w, h);
		glMatrixMode (GL_PROJECTION);
		glLoadIdentity ();
		gluPerspective (60.0, 1.0 * (double)w/(double)h, 1.0, 30.0);
		glMatrixMode (GL_MODELVIEW);
		glLoadIdentity ();
		glTranslatef (0.0f, 0.0f, -3.6f);
	}


	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent.NONE;
	}


	@Override
	public GUIEvent updateKeyboard(KeyboardEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent.NONE;
	}

}
