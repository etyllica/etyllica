package examples.etyllica.gui.textfield;


import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.gui.TextField;

public class TextFieldExample extends Application {

	public TextFieldExample(int w, int h) {
		super(w,h);
	}
	
	@Override
	public void load() {
		TextField field = new TextField(120, 120, 100, 32);
		addView(field);
		
		loading = 100;
	}

	@Override
	public void draw(Graphics g) {
		/*g.setColor(Color.RED);
		g.fillRect(x, y, w, h);*/
	}

}
