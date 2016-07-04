package examples.etyllica.gui.list;


import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.graphics.Graphics;
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
				
		Table table = new Table(0,0,0,80);
		table.style.margin.top = 2;
		panel.add(table);
		table.setHeaders("Name", "Description");
		
		loading = 20;
		table.add(createRow("A", "descA"));
		table.addRow("B", "descB");
		table.addRow("C", "descC");
		table.addRow("D", "descD");
		table.addRow("E", "descE");
		
		Table anotherTable = new Table(0,0,0,60);
		anotherTable.style.margin.top = 2;
		panel.add(anotherTable);
		anotherTable.setHeaders("Name", "Description");
		
		anotherTable.addRow("F", "descF");
		
		addView(panel);
				
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
