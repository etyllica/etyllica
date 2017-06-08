package examples.etyllica.gui.textview;

import java.awt.Color;

import br.com.etyllica.commons.context.Application;
import br.com.etyllica.commons.graphics.Graphics;
import br.com.etyllica.ui.UI;
import br.com.etyllica.ui.base.BaseTextView;

public class TextViewExample extends Application {

	public TextViewExample(int w, int h){
		super(w,h);
	}
		
	@Override
	public void load() {
		
		BaseTextView textView = new BaseTextView(20,30,120,40);
		textView.setText("Hello?");
		UI.add(textView);
		
		loading = 100;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(this);
	}

}
