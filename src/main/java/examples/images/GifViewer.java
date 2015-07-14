package examples.images;

import br.com.etyllica.Etyllica;
import br.com.etyllica.core.context.Application;

public class GifViewer extends Etyllica {

	private static final long serialVersionUID = 1L;

	public GifViewer() {
		super(800, 600);
	}
	
	@Override
	public Application startApplication() {
				
		//Etyllica tries to find the resources as your Application should be
		//To avoid this you should put your /assets/images in /Project/src/bin/etyllica/
		
		//Upping four directories we have /Project/bin/assets/images
		
		initialSetup("../../");
		
		return new GifAnimation(w,h);
		
	}
	
}
