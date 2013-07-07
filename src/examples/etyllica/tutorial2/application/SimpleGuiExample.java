package examples.etyllica.tutorial2.application;

import java.awt.Color;

import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.event.GUIAction;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyboardEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.gui.Button;
import br.com.etyllica.gui.button.RoundCornerButton;
import br.com.etyllica.gui.label.TextLabel;
import br.com.etyllica.util.SVGColor;

public class SimpleGuiExample extends Application{

	public SimpleGuiExample(int w, int h){
		super(w,h);
	}
	
	/**
	 * Background Color
	 */
	private Color color = Color.WHITE;
	
	@Override
	public void load() {
		
		Button buttonWhite = new Button(20,30,120,40);
		buttonWhite.setLabel(new TextLabel("WHITE!"));
		buttonWhite.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new GUIAction(this, "turnWhite"));
		add(buttonWhite);		
		
		Button buttonBlue = new Button(20,80,120,40);
		buttonBlue.setLabel(new TextLabel("BLUE!"));
		buttonBlue.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new GUIAction(this, "turnBlue"));
		add(buttonBlue);
		
		Button buttonSeaGreen = new Button(20,130,120,40);
		buttonSeaGreen.setLabel(new TextLabel("SEA GREEN!"));
		buttonSeaGreen.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new GUIAction(this, "turnSeaGreen"));
		add(buttonSeaGreen);
		
		RoundCornerButton buttonOrchid = new RoundCornerButton(20,180,120,40);
		buttonOrchid.setRoundness(10);
		buttonOrchid.setLabel(new TextLabel("ORCHID!"));
		buttonOrchid.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new GUIAction(this, "turnOrchid"));
		add(buttonOrchid);
		
		RoundCornerButton buttonOrange = new RoundCornerButton(20,230,120,40);
		buttonOrange.setRoundness(10);
		buttonOrange.setLabel(new TextLabel("ORANGE!"));
		buttonOrange.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new GUIAction(this, "turnOrange"));
		add(buttonOrange);
		
		RoundCornerButton buttonCrimson = new RoundCornerButton(20,280,120,40);
		buttonCrimson.setRoundness(10);
		buttonCrimson.setLabel(new TextLabel("CRIMSON!"));
		buttonCrimson.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new GUIAction(this, "turnCrimson"));
		add(buttonCrimson);
		
		loading = 100;
	}
	
	public void turnWhite(){
		color = Color.WHITE;
	}
	
	public void turnBlue(){
		color = Color.BLUE;
	}
	
	public void turnSeaGreen(){
		color = SVGColor.LIGHT_SEA_GREEN;
	}
	
	public void turnOrchid(){
		color = SVGColor.ORCHID;
	}
	
	public void turnOrange(){
		color = SVGColor.ORANGE;
	}
	
	public void turnCrimson(){
		color = SVGColor.CRIMSON;
	}

	@Override
	public void draw(Grafico g) {
		
		g.setColor(color);
		g.fillRect(x, y, w, h);
		
	}

	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent	.NONE;
	}

	@Override
	public GUIEvent updateKeyboard(KeyboardEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent	.NONE;
	}

}
