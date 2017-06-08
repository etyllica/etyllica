package examples.etyllica.graph;
import br.com.etyllica.Etyllica;
import br.com.etyllica.commons.context.Application;
import examples.etyllica.graph.view.SimpleGraphExample;

public class GraphViewer extends Etyllica {

	private static final long serialVersionUID = 1L;

	public GraphViewer() {
		super(800, 600);
	}

	public static void main(String[] args){
		
		GraphViewer viewer = new GraphViewer();
		
		viewer.init();
	}	
	
	@Override
	public Application startApplication() {
		initialSetup("../../../../../");
		return new SimpleGraphExample(w,h);
	}

}

