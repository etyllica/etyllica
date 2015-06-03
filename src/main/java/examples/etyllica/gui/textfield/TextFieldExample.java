package examples.etyllica.gui.textfield;


import br.com.etyllica.context.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.gui.ScrollerPanel;
import br.com.etyllica.gui.Select;
import br.com.etyllica.gui.TextField;
import br.com.etyllica.gui.list.Option;
import br.com.etyllica.gui.list.OptionPanel;
import br.com.etyllica.gui.panel.ColoredTextPanel;

public class TextFieldExample extends Application {

	public TextFieldExample(int w, int h) {
		super(w,h);
	}
	
	@Override
	public void load() {

		TextField field = new TextField(120, 120, 100, 32);
		add(field);
		
		loading = 100;
	}

	@Override
	public void draw(Graphic g) {
		/*g.setColor(Color.RED);
		g.fillRect(x, y, w, h);*/
	}

}
