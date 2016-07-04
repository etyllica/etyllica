package examples.etyllica.gui.textview;

import java.awt.Color;

import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.gui.base.BaseTextView;

public class TextViewExample extends Application {

	public TextViewExample(int w, int h){
		super(w,h);
	}
		
	@Override
	public void load() {
		
		BaseTextView textView = new BaseTextView(20,30,120,40);
		textView.setText("Hello?");
		this.addView(textView);
		
		loading = 100;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(this);
	}

}
