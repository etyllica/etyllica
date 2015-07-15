package examples.etyllica.graph;
import br.com.etyllica.EtyllicaFrame;
import br.com.etyllica.core.context.Application;
import examples.etyllica.graph.view.SimpleGraphExample;

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
		return new SimpleGraphExample(w,h);
	}

}

