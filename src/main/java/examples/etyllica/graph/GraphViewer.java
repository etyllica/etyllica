package examples.etyllica.graph;
import examples.etyllica.graph.view.MeshNavigationExample;
import examples.etyllica.graph.view.SimpleGraphExample;
import br.com.etyllica.EtyllicaFrame;
import br.com.etyllica.context.Application;

public class GraphViewer extends EtyllicaFrame {

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
		//return new SimpleGraph(w,h);
		return new MeshNavigationExample(w,h);
	}

}

