package examples.etyllica.tutorial13;


import br.com.etyllica.context.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.video.Graphic;
import br.com.etyllica.gui.ScrollerPanel;
import br.com.etyllica.gui.Select;
import br.com.etyllica.gui.list.Option;
import br.com.etyllica.gui.list.OptionPanel;
import br.com.etyllica.gui.panel.ColoredTextPanel;

public class ComponentsList extends Application{

	public ComponentsList(int w, int h){
		super(w,h);
	}
	
	@Override
	public void load() {

		Select select = new Select(20,20,100,16);
		select.addOption(new Option("pt-br","Portuguese"));
		select.addOption(new Option("en-us","English"));
		select.addOption(new Option("es-es","Spanish" ));
		add(select);
		
		ScrollerPanel panel = new ScrollerPanel(200,40,200,200);
		panel.setComponent(new ColoredTextPanel(0, 0, 200, 300));
		add(panel);
		
		OptionPanel optionPanel = new OptionPanel(400,40,200,200);
		
		optionPanel.addOption(new Option("pt-BR", "Portuguese"));
		optionPanel.addOption(new Option("en-us","English"));
		optionPanel.addOption(new Option("es-es","Spanish" ));

		add(optionPanel);		
		
		loading = 100;
	}

	@Override
	public void draw(Graphic g) {
		/*g.setColor(Color.RED);
		g.fillRect(x, y, w, h);*/
	}

	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent.NONE;
	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent.NONE;
	}

	

}
