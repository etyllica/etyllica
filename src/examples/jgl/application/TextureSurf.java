package examples.jgl.application;

import jgl.GLU;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyboardEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.video.Grafico;

public class TextureSurf extends GLU{

	public TextureSurf(int w, int h) {
		super(w,h);
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

	private static final float texpts [][][] = {
		{{ 0.0f,  0.0f},
			{ 0.0f,  1.0f}},
			{{ 1.0f,  0.0f},
				{ 1.0f,  1.0f}}};

	private static final int imageWidth = 64;
	private static final int imageHeight = 64;
	private byte image [][][] = new byte [imageHeight][imageWidth][3];

	private void loadImage () {
		int i, j;
		float ti, tj;

		for (i = 0; i < imageWidth; i++) {
			ti = (float)(2.0 * Math.PI * i / imageWidth);
			for (j = 0; j < imageHeight; j++) {
				tj = (float)(2.0 * Math.PI * j / imageHeight);
				image [j][i][0] = (byte)(127 * (1.0 + Math.sin (ti)));
				image [j][i][1] = (byte)(127 * (1.0 + Math.cos (2 * tj)));
				image [j][i][2] = (byte)(127 * (1.0 + Math.cos (ti + tj)));
			}
		}
	}
	
	@Override
	public void load() {
		glMap2f (GL_MAP2_VERTEX_3, 0.0f, 1.0f, 3, 4,
				0.0f, 1.0f, 12, 4, ctrlpoints);
		glMap2f (GL_MAP2_TEXTURE_COORD_2, 0.0f, 1.0f, 2, 2,
				0.0f, 1.0f, 4, 2, texpts);
		glEnable (GL_MAP2_TEXTURE_COORD_2);
		glEnable (GL_MAP2_VERTEX_3);
		glMapGrid2f (20, 0.0f, 1.0f, 20, 0.0f, 1.0f);
		loadImage ();
		glTexEnvf (GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_DECAL);
		glTexParameterf (GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		glTexParameterf (GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
		glTexParameterf (GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexParameterf (GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexImage2D (GL_TEXTURE_2D, 0, 3, imageWidth, imageHeight,
				0, GL_RGB, GL_UNSIGNED_BYTE, image);
		glEnable (GL_TEXTURE_2D);
		glEnable (GL_DEPTH_TEST);
		glEnable (GL_NORMALIZE);
		glShadeModel (GL_FLAT);

		myReshape(w,h);

		loading = 100;
	}

	@Override
	public void draw(Grafico g) {

		glClear (GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glColor3f (1.0f, 1.0f, 1.0f);
		glEvalMesh2 (GL_FILL, 0, 20, 0, 20);
		glFlush(g);
	}

	private void myReshape (int w, int h) {
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
		glRotatef (85.0f, 1.0f, 1.0f, 1.0f);
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
