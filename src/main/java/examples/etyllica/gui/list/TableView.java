package examples.etyllica.gui.list;


import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.core.ui.UI;
import br.com.etyllica.gui.Panel;
import br.com.etyllica.gui.Table;
import br.com.etyllica.gui.table.Row;

public class TableView extends Application {

	public TableView(int w, int h) {
		super(w,h);
	}
	
	@Override
	public void load() {

		loading = 10;
		
		Panel panel = new Panel(20,60,260,360);
		panel.style.padding.left = 2;
		panel.style.padding.right = 2;
		
		Table table = new Table();
		table.style.padding.top = 4;
		table.style.padding.left = 4;
		table.style.margin.top = 2;
		
		table.setHeaders("Name", "Description");
		
		loading = 20;
		table.add(createRow("A", "descA"));
		table.addRow("B", "descB");
		table.addRow("C", "descC");
		table.addRow("D", "descD");
		table.addRow("E", "descE");
		table.addRow("F", "descF");
		table.addRow("G", "descG");
		table.addRow("H", "descH");
		table.addRow("I", "descI");
		table.addRow("J", "descJ");
		table.addRow("K", "descK");
		table.addRow("L", "descL");
		table.addRow("M", "descM");
		panel.add(table);
		
		Table anotherTable = new Table();
		anotherTable.style.padding.top = 4;
		anotherTable.style.padding.left = 4;
		anotherTable.style.margin.top = 2;
		anotherTable.style.margin.bottom = 2;
		
		anotherTable.setHeaders("Name", "Description");
		anotherTable.addRow("F", "descF");
		
		panel.add(anotherTable);

		UI.add(panel);
		
		loading = 100;
	}

	private Row createRow(String name, String description) {
		return new Row(
				new String[]{"Name", name},
				new String[]{"Description", description});
	}

	@Override
	public void draw(Graphics g) {
		/*g.setColor(Color.RED);
		g.fillRect(x, y, w, h);*/
	}

}
