package examples.jgl;

import br.com.etyllica.Etyllica;

public class JGLTutorials extends Etyllica {

	private static final long serialVersionUID = 1L;

	public JGLTutorials() {
		super(640, 480);
	}
	
	@Override
	public void startGame() {
		
		//setMainApplication(new BezCurve());
		//setMainApplication(new BezMesh());
		//setMainApplication(new Checker());
		//setMainApplication(new Clip());
		//setMainApplication(new ColorMaterial());
		//setMainApplication(new Light());
		//setMainApplication(new Lines());
		//setMainApplication(new List());
		//setMainApplication(new Material());
		//setMainApplication(new MipMap());
		//setMainApplication(new Model());
		//setMainApplication(new MoveLight());
		//setMainApplication(new Planet());
		//setMainApplication(new Robot());
		//setMainApplication(new Smooth());
		//setMainApplication(new Surface());
		//setMainApplication(new Tea());
		//setMainApplication(new Teapots());
		setMainApplication(new TextureSurf());
		
		//Problem
		//setMainApplication(new Simple());
		//setMainApplication(new Stroke());
		
		
	}
	
}
