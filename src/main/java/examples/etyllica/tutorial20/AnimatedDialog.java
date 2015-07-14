package examples.etyllica.tutorial20;

import br.com.etyllica.core.animation.AnimationHandler;
import br.com.etyllica.core.animation.script.text.DialogScript;
import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.input.mouse.MouseButton;
import br.com.etyllica.layer.TextLayer;

public class AnimatedDialog extends Application{
	
	private TextLayer layer;
	
	private DialogScript script;
		
	public AnimatedDialog(int w, int h) {
		super(w, h);
	}
		
	@Override
	public void load() {
				
		layer = new TextLayer(200, 140, "Hello my friend, stay awhile and listen.");
						
		script = new DialogScript(layer, 2000);
		
		AnimationHandler.getInstance().add(script);
				
		loading = 100;
	}
	
	@Override
	public void draw(Graphic g) {

		layer.draw(g);
	}
		
	@Override
	public void update(long now){
				
	}
	
	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		
		if(event.isButtonDown(MouseButton.MOUSE_BUTTON_LEFT)) {
			AnimationHandler.getInstance().add(script);
			script.restart();
		}
		
		return GUIEvent.NONE;
	}
	
	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {

		return GUIEvent.NONE;
	}

}
