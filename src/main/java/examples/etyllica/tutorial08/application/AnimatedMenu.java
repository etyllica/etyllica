package examples.etyllica.tutorial08.application;

import br.com.etyllica.commons.animation.AnimationModule;
import br.com.etyllica.commons.animation.script.HorizontalMovementScript;
import br.com.etyllica.commons.context.Application;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.ui.UI;
import br.com.etyllica.ui.Button;
import br.com.etyllica.ui.label.TextLabel;

public class AnimatedMenu extends Application{
	
	private Button button1;
	
	private Button button2;
	
	public AnimatedMenu(int w, int h) {
		super(w, h);
	}
		
	@Override
	public void load() {
				
		createButtons();
		
		HorizontalMovementScript script = new HorizontalMovementScript(button1, 2000);
		
		script.setInterval(w, button1.getX());
				
		HorizontalMovementScript scriptButton2 = new HorizontalMovementScript(button2, 2000);
		
		scriptButton2.setInterval(w, button2.getX());
		
		script.addNext(scriptButton2);

		AnimationModule.getInstance().add(script);
				
		loading = 100;
	}
	
	private void createButtons() {
		
		final int buttonW = 200;
		
		final int buttonH = 50;
		
		final int offset = 15;
		
		button1 = new Button(w/2-buttonW/2, h/2, buttonW, buttonH);
		
		button1.setLabel(new TextLabel("Button1"));
		
		button2 = new Button(w/2-buttonW/2, button1.getY()+buttonH+offset, buttonW, buttonH);
		
		button2.setLabel(new TextLabel("Button2"));

		UI.add(button1);
		UI.add(button2);
	}

	@Override
	public void draw(Graphics g) {

	}
}
