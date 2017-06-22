package examples.etyllica.dnd;

import br.com.etyllica.commons.context.Application;
import br.com.etyllica.commons.graphics.Color;
import br.com.etyllica.core.graphics.Graphics;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DragAndDropApplication extends Application {

	private List<String> paths = new ArrayList<String>();
	private boolean dragAction = false;

	public DragAndDropApplication(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {

	}

	@Override
	public void draw(Graphics g) {
		if(dragAction) {
			int width = 4;
			g.setColor(Color.AQUA);
			g.setLineWidth(width);
			g.drawRect(x+width, y+width, w-width*2, h-width*2);
			g.setLineWidth(1f);
		}

		if(!paths.isEmpty()) {
			g.setFontSize(16f);
			g.setColor(Color.BLACK);
			int count = 0;
			for(String path : paths) {				
				g.drawString(path, 20, 60+count*20);
				count++;
			}
		} else {
			g.setFontSize(40f);
			g.drawStringShadowX("Drop something here!", h/2);
		}
	}

	@Override
	public void dragEnter() {
		dragAction = true;
	}

	@Override
	public void dragExit() {
		dragAction = false;
	}
		
	@Override
	public void dropFiles(int x, int y, List<File> files) {
		paths.clear();
		for(File f: files) {
			paths.add(f.getAbsolutePath());
		}
	}

}
