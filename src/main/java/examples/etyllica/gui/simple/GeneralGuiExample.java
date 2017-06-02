package examples.etyllica.gui.simple;

import java.awt.Color;

import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.event.Action;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.core.ui.UI;
import br.com.etyllica.gui.Button;
import br.com.etyllica.gui.CheckBox;
import br.com.etyllica.gui.RadioButton;
import br.com.etyllica.gui.RadioGroup;
import br.com.etyllica.gui.TextField;
import br.com.etyllica.gui.label.TextLabel;

public class GeneralGuiExample extends Application{

	public GeneralGuiExample(int w, int h){
		super(w,h);
	}
	
	/**
	 * Background Color
	 */
	private Color color = Color.WHITE;
	
	@Override
	public void load() {
		
		Button buttonWhite = new Button(20,30,120,40);
		buttonWhite.setLabel(new TextLabel("WHITE"));
		buttonWhite.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new Action(this, "changeColor", Color.WHITE));
		UI.add(buttonWhite);
		
		Button buttonBlue = new Button(20,80,120,40);
		buttonBlue.setLabel(new TextLabel("BLUE"));
		buttonBlue.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new Action(this, "changeColor", Color.BLUE));
		UI.add(buttonBlue);
		
		TextField field = new TextField(100, 150, 120, 40);
		UI.add(field);
		
		CheckBox checkbox = new CheckBox(280, 150, 60, 40);
		checkbox.setChecked(true);
		UI.add(checkbox);
		
		RadioGroup group = new RadioGroup();
		RadioButton radio1 = new RadioButton(200, 50, 40, 40);
		radio1.setGroup(group);
		RadioButton radio2 = new RadioButton(280, 50, 40, 40);
		radio2.setGroup(group);
		radio1.check();

		UI.add(radio1);
		UI.add(radio2);
		
		loading = 100;
	}
	
	public void changeColor(Color color){
		this.color = color;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, w, h);
	}

}
