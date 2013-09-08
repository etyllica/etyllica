package examples.jgl.application;


import jgl.GLU;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyboardEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.video.Graphic;

public class MipMap extends GLU{
	
	public MipMap(int w, int h) {
		super(w,h);
	}

	@Override
	public void load(){
		myinit();
		loading = 100;
	}

	private byte mipmapImage32 [][][] = new byte [32][32][3];
	private byte mipmapImage16 [][][] = new byte [16][16][3];
	private byte mipmapImage8 [][][] = new byte [8][8][3];
	private byte mipmapImage4 [][][] = new byte [4][4][3];
	private byte mipmapImage2 [][][] = new byte [2][2][3];
	private byte mipmapImage1 [][][] = new byte [1][1][3];

	private void loadImage () {
		int i, j;

		for (i = 0; i < 32; i++) {
			for (j = 0; j < 32; j++) {
				mipmapImage32 [i][j][0] = (byte)255;
				mipmapImage32 [i][j][1] = (byte)255;
				mipmapImage32 [i][j][2] = (byte)0;
			}
		}
		for (i = 0; i < 16; i++) {
			for (j = 0; j < 16; j++) {
				mipmapImage16 [i][j][0] = (byte)255;
				mipmapImage16 [i][j][1] = (byte)0;
				mipmapImage16 [i][j][2] = (byte)255;
			}
		}
		for (i = 0; i < 8; i++) {
			for (j = 0; j < 8; j++) {
				mipmapImage8 [i][j][0] = (byte)255;
				mipmapImage8 [i][j][1] = (byte)0;
				mipmapImage8 [i][j][2] = (byte)0;
			}
		}
		for (i = 0; i < 4; i++) {
			for (j = 0; j < 4; j++) {
				mipmapImage4 [i][j][0] = (byte)0;
				mipmapImage4 [i][j][1] = (byte)255;
				mipmapImage4 [i][j][2] = (byte)0;
			}
		}
		for (i = 0; i < 2; i++) {
			for (j = 0; j < 2; j++) {
				mipmapImage2 [i][j][0] = (byte)0;
				mipmapImage2 [i][j][1] = (byte)0;
				mipmapImage2 [i][j][2] = (byte)255;
			}
		}
		mipmapImage1 [0][0][0] = (byte)255;
		mipmapImage1 [0][0][1] = (byte)255;
		mipmapImage1 [0][0][2] = (byte)255;
	}

	private void myinit () {
		glEnable (GL_DEPTH_TEST);
		glDepthFunc (GL_LEQUAL);
		glShadeModel (GL_FLAT);
		glTranslatef (0.0f, 0.0f, -3.6f);
		loadImage ();
		glPixelStorei (GL_UNPACK_ALIGNMENT, 1);
		glTexImage2D (GL_TEXTURE_2D, 0, 3, 32, 32, 0,
				GL_RGB, GL_UNSIGNED_BYTE, mipmapImage32);
		glTexImage2D (GL_TEXTURE_2D, 1, 3, 16, 16, 0,
				GL_RGB, GL_UNSIGNED_BYTE, mipmapImage16);
		glTexImage2D (GL_TEXTURE_2D, 2, 3, 8, 8, 0,
				GL_RGB, GL_UNSIGNED_BYTE, mipmapImage8);
		glTexImage2D (GL_TEXTURE_2D, 3, 3, 4, 4, 0,
				GL_RGB, GL_UNSIGNED_BYTE, mipmapImage4);
		glTexImage2D (GL_TEXTURE_2D, 4, 3, 2, 2, 0,
				GL_RGB, GL_UNSIGNED_BYTE, mipmapImage2);
		glTexImage2D (GL_TEXTURE_2D, 5, 3, 1, 1, 0,
				GL_RGB, GL_UNSIGNED_BYTE, mipmapImage1);
		glTexParameterf (GL_TEXTURE_2D, GL_TEXTURE_WRAP_S,
				GL_REPEAT);
		glTexParameterf (GL_TEXTURE_2D, GL_TEXTURE_WRAP_T,
				GL_REPEAT);
		glTexParameterf (GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER,
				GL_NEAREST);
		glTexParameterf (GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER,
				GL_NEAREST_MIPMAP_NEAREST);
		glTexEnvf (GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_DECAL);
		glEnable (GL_TEXTURE_2D);

		myReshape(w, h);
	}

	public void draw(Graphic g) {
		glClear (GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glBegin (GL_QUADS);
		glTexCoord2f (0.0f, 0.0f);
		glVertex3f (-2.0f, -1.0f, 0.0f);
		glTexCoord2f (0.0f, 8.0f);
		glVertex3f (-2.0f, 1.0f, 0.0f);
		glTexCoord2f (8.0f, 8.0f);
		glVertex3f (2000.0f, 1.0f, -6000.0f);
		glTexCoord2f (8.0f, 0.0f);
		glVertex3f (2000.0f, -1.0f, -6000.0f);
		glEnd ();
		glFlush (g);
	}


	private void myReshape (int w, int h) {
		glViewport (0, 0, w, h);
		glMatrixMode (GL_PROJECTION);
		glLoadIdentity ();
		gluPerspective (60.0, 1.0 * (double)w/(double)h, 1.0, 30000.0);
		glMatrixMode (GL_MODELVIEW);
		glLoadIdentity ();
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
