package examples.jgl.application;

import org.jgl.GLAUX;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.video.Graphic;

public class TextGen extends GLAUX{
	
	public TextGen(int w, int h) {
		super(w,h);
	}

	private final int stripeImageWidth = 32;
	private byte stripeImage[][] = new byte [stripeImageWidth][3];

	private void makeStripeImage () {
		int j;

		for (j = 0; j < stripeImageWidth; j++) {
			if (j<=4) stripeImage[j][0] = (byte)255;
			else      stripeImage[j][0] = (byte)0;
			if (j> 4) stripeImage[j][1] = (byte)255;
			else      stripeImage[j][1] = (byte)0;
			stripeImage[j][2] = 0;
		}
	}

	/* glTexGen stuff: */
	float sgenparams[] = {1.0f, 1.0f, 1.0f, 0.0f};

	@Override
	public void load() {
		
		glClearColor (0.0f, 0.0f, 0.0f, 0.0f);

		makeStripeImage();
		
		glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
		glTexEnvf(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_MODULATE);
		glTexParameterf(GL_TEXTURE_1D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		glTexParameterf(GL_TEXTURE_1D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		glTexParameterf(GL_TEXTURE_1D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
		glTexImage1D(GL_TEXTURE_1D, 0, 3, stripeImageWidth, 0,
				GL_RGB, GL_UNSIGNED_BYTE, stripeImage);

		glTexGeni(GL_S, GL_TEXTURE_GEN_MODE, GL_OBJECT_LINEAR);
		glTexGenfv(GL_S, GL_OBJECT_PLANE, sgenparams);

		glEnable (GL_DEPTH_TEST);
		glDepthFunc (GL_LESS);
		glEnable (GL_TEXTURE_GEN_S);
		glEnable (GL_TEXTURE_1D);
		glEnable (GL_CULL_FACE);
		glEnable (GL_LIGHTING);
		glEnable (GL_LIGHT0);
		glEnable (GL_AUTO_NORMAL);
		glEnable (GL_NORMALIZE);
		glFrontFace (GL_CW);
		glCullFace (GL_BACK);
		glMaterialf (GL_FRONT, GL_SHININESS, 64.0f);

		myReshape(w,h);

		loading = 100;
	}

	@Override
	public void draw(Graphic g) {
		glClear (GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		glPushMatrix();
		glRotatef(45.0f, 0.0f, 0.0f, 1.0f);
		auxSolidTeapot(2.0);
		glPopMatrix();
		glFlush(g);
	}

	private void myReshape (int w, int h) {
		glViewport (0, 0, w, h);
		glMatrixMode (GL_PROJECTION);
		glLoadIdentity ();
		if (w <= h) {
			glOrtho (-3.5f, 3.5f,
					-3.5f*(float)h/(float)w, 
					3.5f*(float)h/(float)w, 
					-3.5f, 3.5f);
		} else {
			glOrtho (-3.5f*(float)w/(float)h,
					3.5f*(float)w/(float)h,
					-3.5f, 3.5f,
					-3.5f, 3.5f);
		}
		glMatrixMode (GL_MODELVIEW);
		glLoadIdentity ();
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
