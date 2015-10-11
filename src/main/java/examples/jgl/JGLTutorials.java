package examples.jgl;

import br.com.etyllica.Etyllica;
import br.com.etyllica.core.context.Application;
import examples.jgl.application.BezSurf;
import examples.jgl.application.Checker;
import examples.jgl.application.Clip;
import examples.jgl.application.ColorMaterial;
import examples.jgl.application.Lines;
import examples.jgl.application.Material;
import examples.jgl.application.Surface;
import examples.jgl.application.Teapots;
import examples.jgl.application.TextureSurf;
import examples.jgl.bezcurve.BezCurve;
import examples.jgl.bezmesh.BezMesh;

public class JGLTutorials extends Etyllica {

	private static final long serialVersionUID = 1L;

	public JGLTutorials() {
		super(640, 480);
	}
	
	@Override
	public Application startApplication() {
		
		//return new BezCurve(w, h);
		//return new BezMesh(w, h);
		//return new BezSurf(w, h);
		//return new Checker(w, h);
		//return new Clip(w, h);
		//return new ColorMaterial(w, h);
		//return new Light(w, h);
		//return new Lines(w, h);
		//return new List(w, h);
		//return new Material(w, h);
		//return new MipMap(w, h);
		//return new Model(w, h);
		//return new MoveLight(w, h);
		//return new Planet(w, h);
		//return new Robot(w, h);
		//return new Smooth(w, h);
		//return new Surface(w, h);
		//return new Tea(w, h);
		//return new Teapots(w, h);
		return new TextureSurf(w, h);
		
		//Problem
		//return new Simple(w, h);
		//return new Stroke(w, h);
	}
	
}
