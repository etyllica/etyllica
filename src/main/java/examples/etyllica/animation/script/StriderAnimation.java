package examples.etyllica.animation.script;

import br.com.etyllica.core.animation.Animation;
import br.com.etyllica.core.animation.script.AnimationScript;
import br.com.etyllica.commons.context.Application;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.layer.ImageLayer;

public class StriderAnimation extends Application {

	public StriderAnimation(int w, int h) {
		super(w, h);
	}
	
	private ImageLayer background;
	private ImageLayer machine;
	private ImageLayer anotherMachine;

	@Override
	public void load() {
		
		loadingInfo = "Loading images...";
		
		background = new ImageLayer("scene.png");
		machine = new ImageLayer(180,270,"machine.png");
		anotherMachine = new ImageLayer(180,270,"machine.png");
		
		createAnimationScripts(machine, 0);
		createAnimationScripts(anotherMachine, 180);
				
		loading = 50;
		loadingInfo = "Loading animations...";		
				
	}
	
	private void createAnimationScripts(ImageLayer machine, int offsetAngle) {
		Animation.animate(machine).orbit().during(5000)
		.around(machine.getX(), machine.getY()+70).from(offsetAngle).to(360+offsetAngle)
		.loop(AnimationScript.REPEAT_FOREVER).and()
		.rotate(5000).from(offsetAngle).to(360+offsetAngle)
		.loop(AnimationScript.REPEAT_FOREVER).start();
	}

	@Override
	public void draw(Graphics g) {
		background.draw(g);
		machine.draw(g);
		anotherMachine.draw(g);
	}
}
