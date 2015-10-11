package examples.etyllica.tutorial16;

import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.effects.particle.BasicEmitter;

public class ParticleApplication extends Application{

	private BasicEmitter emitter;
	
	public ParticleApplication(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {
		
		emitter = new BasicEmitter(50,450);
				
		scene.addEmitter(emitter);
		
		loading = 100;
	}

	@Override
	public void draw(Graphic g) {
		// TODO Auto-generated method stub
		
	}

}
