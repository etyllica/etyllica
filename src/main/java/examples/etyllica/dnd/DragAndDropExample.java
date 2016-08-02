package examples.etyllica.dnd;

import br.com.etyllica.Etyllica;
import br.com.etyllica.core.context.Application;

public class DragAndDropExample extends Etyllica {

	private static final long serialVersionUID = 1L;

	public DragAndDropExample() {
		super(800, 600);
	}

	public static void main(String[] args){
		DragAndDropExample app = new DragAndDropExample();
		app.setTitle("Drag and Drop Example");
		app.init();
	}	
	
	@Override
	public Application startApplication() {
		initialSetup("../../");
		return new DragAndDropApplication(w,h);
	}

}
