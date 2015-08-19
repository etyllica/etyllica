package examples.etyllica.tutorial08.application;

import br.com.etyllica.core.animation.AnimationHandler;
import br.com.etyllica.core.animation.script.HorizontalMovementScript;
import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.gui.Button;
import br.com.etyllica.gui.label.TextLabel;

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
		
		AnimationHandler.getInstance().add(script);
				
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
		

		this.add(button1);
		
		this.add(button2);
		
	}

	@Override
	public void draw(Graphic g) {

	}
		
	@Override
	public void update(long now){
				
	}
	
	@Override
	public GUIEvent updateMouse(PointerEvent event) {
						
		return GUIEvent.NONE;
	}
	
	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {

		return GUIEvent.NONE;
	}

}
