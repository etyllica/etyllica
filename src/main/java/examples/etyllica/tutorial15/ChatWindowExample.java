package examples.etyllica.tutorial15;

import java.awt.Color;

import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.event.Action;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
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
		button.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new Action(this, "sendMsg"));
		add(button);
		
		loading = 100;
	}

	public void sendMsg(){
		panel.addLine(username+": "+tf.getText());
		tf.clearField();
	}
	
	@Override
	public GUIEvent updateKeyboard(KeyEvent event){
		
		if(event.isKeyDown(KeyEvent.VK_1)){
			panel.addLine(friendname+": Hi");
		}
		
		if(event.isKeyDown(KeyEvent.VK_2)){
			panel.addLine(friendname+": Hello");
		}
		
		if(event.isKeyDown(KeyEvent.VK_3)){
			panel.addLine(friendname+": How are you?");
		}
		
		if(event.isKeyDown(KeyEvent.VK_4)){
			panel.addLine(friendname+": I am fine, and you?");
		}
		
		if(event.isKeyDown(KeyEvent.VK_5)){
			panel.addLine(friendname+": Thank You.");
		}
		
		if(event.isKeyDown(KeyEvent.VK_6)){
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
