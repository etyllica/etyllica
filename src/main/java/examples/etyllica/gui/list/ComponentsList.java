package examples.etyllica.gui.list;


import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.gui.ScrollerPanel;
import br.com.etyllica.gui.Select;
import br.com.etyllica.gui.list.Option;
import br.com.etyllica.gui.list.OptionPanel;
import br.com.etyllica.gui.panel.ColoredTextPanel;

public class ComponentsList extends Application {

	public ComponentsList(int w, int h) {
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
}
