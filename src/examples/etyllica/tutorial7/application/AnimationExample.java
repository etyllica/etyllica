package examples.etyllica.tutorial7.application;

import java.awt.Color;

import br.com.etyllica.animation.scripts.HorizontalAnimationScript;
import br.com.etyllica.animation.scripts.VerticalAnimationScript;
import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyboardEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.layer.TextLayer;

public class AnimationExample extends Application{

	public AnimationExample(int w, int h) {
		super(w, h);
	}
	
	private TextLayer text2 = new TextLayer(0,400,"Text2!");
	
	private TextLayer text = new TextLayer("Text!");

	@Override
	public void load() {
		
		text.setBorder(true);
		text.setBorderColor(Color.BLACK);
		text.setBorderWidth(3f);
		
		text2.setBorder(true);
		text2.setBorderColor(Color.BLACK);
		text2.setBorderWidth(3f);
		
		HorizontalAnimationScript hscript = new HorizontalAnimationScript(10000, text2);
		hscript.setInterval(400, 10);
		
		HorizontalAnimationScript invscript = new HorizontalAnimationScript(10000, text2);
		invscript.setInterval(0, 400);
		//After the hscript, execute invscript
		hscript.setNext(invscript);
		
		VerticalAnimationScript vscript = new VerticalAnimationScript(20000);
		vscript.setTarget(text2);
		vscript.setInterval(100, 200);
		
		this.animation.add(hscript);
		this.animation.add(vscript);
		
		
		loading = 100;
	}
	
	@Override
	public GUIEvent updateKeyboard(KeyboardEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent.NONE;
	}

	@Override
	public GUIEvent updateMouse(PointerEvent event) {
				
		text.setCoordinates(event.getX(), event.getY());
		
		return GUIEvent.NONE;
	}
	
	@Override
	public void draw(Grafico g) {
				
		//Drawing background
		
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x,y,w,h);
		
		text.draw(g);
		
		text2.draw(g);
	}

	
}
