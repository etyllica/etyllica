package examples.etyllica.tutorial16;

import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.video.Graphic;
import br.com.etyllica.particle.BasicEmitter;

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
	
	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

}
