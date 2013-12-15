package examples.jgl.application;

import org.jgl.GLAUX;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.input.mouse.MouseButton;
import br.com.etyllica.core.video.Graphic;

public class ColorMaterial extends GLAUX{

	public ColorMaterial(int w, int h) {
		super(w,h);
	}

	private float diffuseMaterial [] = {0.5f, 0.5f, 0.5f, 1.0f};

	private void myinit() {
		float mat_specular[] = {1.0f, 1.0f, 1.0f, 1.0f};
		float light_position[] = {1.0f, 1.0f, 1.0f, 0.0f};

		glMaterialfv(GL_FRONT, GL_DIFFUSE, diffuseMaterial);
		glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular);
		glMaterialf(GL_FRONT, GL_SHININESS, 25.0f);
		glLightfv(GL_LIGHT0, GL_POSITION, light_position);

		glEnable(GL_LIGHTING);
		glEnable(GL_LIGHT0);
		glDepthFunc(GL_LEQUAL);
		glEnable(GL_DEPTH_TEST);

		glColorMaterial(GL_FRONT, GL_DIFFUSE);
		glEnable(GL_COLOR_MATERIAL);

		myReshape(w,h);
	}

	private void changeRedDiffuse() {
		diffuseMaterial [0] += 0.1f;
		if(diffuseMaterial [0] > 1.0f) {
			diffuseMaterial [0] = 0.0f;
		}
		glColor4fv(diffuseMaterial);
	}

	private void changeGreenDiffuse() {
		diffuseMaterial [1] += 0.1f;
		if(diffuseMaterial [1] > 1.0f) {
			diffuseMaterial [1] = 0.0f;
		}
		glColor4fv(diffuseMaterial);
	}

	private void changeBlueDiffuse() {
		diffuseMaterial [2] += 0.1f;
		if(diffuseMaterial [2] > 1.0f) {
			diffuseMaterial [2] = 0.0f;
		}
		glColor4fv(diffuseMaterial);
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
			glOrtho(-1.5f,  1.5f, 
					-1.5f*(float)h/(float)w, 
					1.5f*(float)h/(float)w,
					-10.0f, 10.0f);
		} else {
			glOrtho(-1.5f*(float)w/(float)h,
					1.5f*(float)w/(float)h,
					-1.5f,  1.5f,
					-10.0f, 10.0f);
		}
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
	}

	@Override
	public void load() {
		myinit();
		loading = 100;
	}

	@Override
	public GUIEvent updateMouse(PointerEvent event){

		if(event.onButtonDown(MouseButton.MOUSE_BUTTON_LEFT)){
			changeRedDiffuse();
		}
		
		if(event.onButtonDown(MouseButton.MOUSE_BUTTON_MIDDLE)){
			changeBlueDiffuse();
		}
		
		if(event.onButtonDown(MouseButton.MOUSE_BUTTON_RIGHT)){
			changeGreenDiffuse();
		}
		

		return GUIEvent.NONE;
	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent.NONE;
	}


}
