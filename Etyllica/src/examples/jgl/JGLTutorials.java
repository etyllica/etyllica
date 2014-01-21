package examples.jgl;

import br.com.etyllica.Etyllica;
import br.com.etyllica.context.Application;
import examples.jgl.application.Teapots;

public class JGLTutorials extends Etyllica {

	private static final long serialVersionUID = 1L;

	public JGLTutorials() {
		super(640, 480);
	}
	
	@Override
	public Application startApplication() {
		
		//setMainApplication(new BezCurve(w,h));
		//setMainApplication(new BezMesh(w,h));
		//setMainApplication(new Checker(w,h));
		//setMainApplication(new Clip(w,h));
		//setMainApplication(new ColorMaterial(w,h));
		//setMainApplication(new Light(w,h));
		//setMainApplication(new Lines(w,h));
		//setMainApplication(new List(w,h));
		//setMainApplication(new Material(w,h));
		//setMainApplication(new MipMap(w,h));
		//setMainApplication(new Model(w,h));
		//setMainApplication(new MoveLight(w,h));
		//setMainApplication(new Planet(w,h));
		//setMainApplication(new Robot(w,h));
		//setMainApplication(new Smooth(w,h));
		//setMainApplication(new Surface(w,h));
		//setMainApplication(new Tea(w,h));
		return new Teapots(w,h);
		//setMainApplication(new TextureSurf(w,h));
		
		//Problem
		//setMainApplication(new Simple(w,h));
		//setMainApplication(new Stroke(w,h));
		
		
	}
	
}
