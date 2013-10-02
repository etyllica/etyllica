package examples.etyllica.tutorial14;


import br.com.etyllica.animation.scripts.FrameAnimationScript;
import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.video.Graphic;
import br.com.etyllica.gui.ScrollerPanel;
import br.com.etyllica.gui.Select;
import br.com.etyllica.gui.list.Option;
import br.com.etyllica.gui.list.OptionPanel;
import br.com.etyllica.gui.panel.ColoredTextPanel;
import br.com.etyllica.layer.AnimatedLayer;

public class AnimationTutorial extends Application{

	public AnimationTutorial(int w, int h){
		super(w,h);
	}
	
	private AnimatedLayer fruit;
	
	@Override
	public void load() {
		
		fruit = new AnimatedLayer(20, 20, 16, 16, "fruits.png");
		fruit.setSpeed(5000);
		
		animation.add(new FrameAnimationScript(fruit));
		
		loading = 100;
	}

	@Override
	public void draw(Graphic g) {
		
		fruit.draw(g);
		
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
