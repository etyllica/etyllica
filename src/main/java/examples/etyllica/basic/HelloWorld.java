package examples.etyllica.basic;

import br.com.etyllica.Etyllica;
import br.com.etyllica.core.context.Application;

public class HelloWorld extends Etyllica {

	private static final long serialVersionUID = 1L;

	public HelloWorld() {
		super(800, 600);
	}

	public static void main(String[] args){
		HelloWorld app = new HelloWorld();
		app.setTitle("Hello World Example");
		app.setIcon("particle.png");
		app.init();
	}	
	
	@Override
	public Application startApplication() {
		//This line is needed because our main class is inside /src folder
		//See the current folder with:
		//System.out.println(getPath());
		initialSetup("../");
		
		return new HelloWorldApplication(w, h);
	}

}
