package examples.etyllica.gui.list;


import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.gui.Orientation;
import br.com.etyllica.gui.Panel;
import br.com.etyllica.gui.ScrollView;
import br.com.etyllica.gui.Select;
import br.com.etyllica.gui.list.Option;
import br.com.etyllica.gui.panel.ColoredTextPanel;

public class ComponentsList extends Application {

    public ComponentsList(int w, int h) {
        super(w, h);
    }

    @Override
    public void load() {
        Select select = new Select(10,80,160,26);
		select.addOption(new Option("pt-br","Portuguese"));
		select.addOption(new Option("en-us","English"));
		select.addOption(new Option("es-es","Spanish" ));
		addView(select);

        Panel panel = new Panel(0,0, 400, 400);
        panel.setOrientation(Orientation.VERTICAL);
        panel.add(new ColoredTextPanel(0, 0, 0, 0));
		
		ScrollView scrollView = new ScrollView(180, 180, 400, 200);
        scrollView.setComponent(panel);
        addView(scrollView);
		
		/*OptionPanel optionPanel = new OptionPanel(400,40,200,200);
		
		optionPanel.addOption(new Option("pt-BR", "Portuguese"));
		optionPanel.addOption(new Option("en-us","English"));
		optionPanel.addOption(new Option("es-es","Spanish" ));

		addView(optionPanel);*/

        loading = 100;
    }

    @Override
    public void draw(Graphics g) {
		/*g.setColor(Color.RED);
		g.fillRect(x, y, w, h);*/
    }
}
