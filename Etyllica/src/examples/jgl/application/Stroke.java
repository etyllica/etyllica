package examples.jgl.application;

import org.jgl.GL;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;

public class Stroke extends GL {
	
	public Stroke(int w, int h) {
		super(w,h);
	}

	private class CP {
		public float x[] = new float[2];
		public int type;

		public CP(float a, float b, int c) {
			x[0] = a;
			x[1] = b;
			type = c;
		}
	}

	public final int PT = 1;
	public final int STROKE = 2;
	public final int END = 3;

	private CP Adata[] = { new CP(0, 0, PT), new CP(0, 9, PT),
			new CP(1, 10, PT), new CP(4, 10, PT), new CP(5, 9, PT),
			new CP(5, 0, STROKE), new CP(0, 5, PT), new CP(5, 5, END) };

	private CP Edata[] = { new CP(5, 0, PT), new CP(0, 0, PT),
			new CP(0, 10, PT), new CP(5, 10, STROKE), new CP(0, 5, PT),
			new CP(4, 5, END) };

	private CP Pdata[] = { new CP(0, 0, PT), new CP(0, 10, PT),
			new CP(4, 10, PT), new CP(5, 9, PT), new CP(5, 6, PT),
			new CP(4, 5, PT), new CP(0, 5, END) };

	private CP Rdata[] = { new CP(0, 0, PT), new CP(0, 10, PT),
			new CP(4, 10, PT), new CP(5, 9, PT), new CP(5, 6, PT),
			new CP(4, 5, PT), new CP(0, 5, STROKE), new CP(3, 5, PT),
			new CP(5, 0, END) };

	private CP Sdata[] = { new CP(0, 1, PT), new CP(1, 0, PT),
			new CP(4, 0, PT), new CP(5, 1, PT), new CP(5, 4, PT),
			new CP(4, 5, PT), new CP(1, 5, PT), new CP(0, 6, PT),
			new CP(0, 9, PT), new CP(1, 10, PT), new CP(4, 10, PT),
			new CP(5, 9, END) };

	private void drawLetter(CP l[]) {
		int i = 0;
		glBegin(GL_LINE_STRIP);
		while (i < l.length) {
			switch (l[i].type) {
			case PT:
				glVertex2fv(l[i].x);
				break;
			case STROKE:
				glVertex2fv(l[i].x);
				glEnd();
				glBegin(GL_LINE_STRIP);
				break;
			case END:
				glVertex2fv(l[i].x);
				glEnd();
				glTranslatef(8.0f, 0.0f, 0.0f);
				return;
			}
			i++;
		}
	}

	@Override
	public void load() {
		int base;

		glShadeModel(GL_FLAT);

		base = glGenLists(128);
		glListBase(base);
		glNewList(base + 'A', GL_COMPILE);
		drawLetter(Adata);
		glEndList();
		glNewList(base + 'E', GL_COMPILE);
		drawLetter(Edata);
		glEndList();
		glNewList(base + 'P', GL_COMPILE);
		drawLetter(Pdata);
		glEndList();
		glNewList(base + 'R', GL_COMPILE);
		drawLetter(Rdata);
		glEndList();
		glNewList(base + 'S', GL_COMPILE);
		drawLetter(Sdata);
		glEndList();
		glNewList(base + ' ', GL_COMPILE);
		glTranslatef(8.0f, 0.0f, 0.0f);
		glEndList();

		loading = 100;
	}

	private String test1 = "A SPARE SERAPE APPEARS AS";
	private String test2 = "APES PREPARE RARE PEPPERS";

	private void printStrokedString(String s) {
		int len = s.length();
		glCallLists(len, GL_BYTE, s.getBytes());
	}

	public void draw(Graphic g) {
		glClear(GL_COLOR_BUFFER_BIT);
		glColor3f(1.0f, 1.0f, 1.0f);
		glPushMatrix();
		glScalef(2.0f, 2.0f, 2.0f);
		glTranslatef(10.0f, 30.0f, 0.0f);
		printStrokedString(test1);
		glPopMatrix();
		glPushMatrix();
		glScalef(2.0f, 2.0f, 2.0f);
		glTranslatef(10.0f, 13.0f, 0.0f);
		printStrokedString(test2);
		glPopMatrix();
		glFlush(g);
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
