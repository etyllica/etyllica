package examples.etyllica.tutorial03.application;

import java.awt.Color;

import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.event.GUIAction;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.event.Key;
import br.com.etyllica.core.video.Graphic;
import br.com.etyllica.gui.Button;
import br.com.etyllica.gui.ScrollerPanel;
import br.com.etyllica.gui.TextField;
import br.com.etyllica.gui.label.TextLabel;
import br.com.etyllica.gui.panel.TextPanel;

public class ChatWindowExample extends Application{

	public ChatWindowExample(int w, int h){
		super(w, h);
	}

	private TextPanel panel;
	
	private TextField tf;
	
	private String username = "Person";
	private String friendname = "Friend";
	
	@Override
	public void load(){
		
		ScrollerPanel rollerPanel = new ScrollerPanel(20,20,300,240);
		
		panel = new TextPanel(300,240);
		rollerPanel.setComponent(panel);
		
		add(rollerPanel);
		
		tf = new TextField(20, 280, 200, 20);
		add(tf);
		
		Button button = new Button(tf.getX()+tf.getW()+10, tf.getY(), 90, tf.getH());
		button.setLabel(new TextLabel("Send!"));
		button.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new GUIAction(this, "sendMsg"));
		add(button);
		
		loading = 100;
	}

	public void sendMsg(){
		panel.addLine(username+": "+tf.getText());
		tf.clearField();
	}
	
	@Override
	public GUIEvent updateKeyboard(KeyEvent event){
		
		if(event.onKeyDown(Key.TSK_1)){
			panel.addLine(friendname+": Hi");
		}
		
		if(event.onKeyDown(Key.TSK_2)){
			panel.addLine(friendname+": Hello");
		}
		
		if(event.onKeyDown(Key.TSK_3)){
			panel.addLine(friendname+": How are you?");
		}
		
		if(event.onKeyDown(Key.TSK_4)){
			panel.addLine(friendname+": I am fine, and you?");
		}
		
		if(event.onKeyDown(Key.TSK_5)){
			panel.addLine(friendname+": Thank You.");
		}
		
		if(event.onKeyDown(Key.TSK_6)){
			panel.addLine(friendname+": LOL :D");
		}
		
		return GUIEvent.NONE;
		
	}
	
	@Override
	public void draw(Graphic g){
		
		//Drawing background
		
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x,y,w,h);
	}

	@Override
	public GUIEvent updateMouse(PointerEvent event){
		// TODO Auto-generated method stub
		return GUIEvent	.NONE;
	}
	
}
