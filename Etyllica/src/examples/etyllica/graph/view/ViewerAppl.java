package examples.etyllica.graph.view;

import java.util.List;

import br.com.etyllica.context.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.graphics.SVGColor;
import br.com.etyllica.linear.graph.Edge;
import br.com.etyllica.linear.graph.Node;
import examples.etyllica.graph.model.ComplexGraph;

public class ViewerAppl extends Application{

	public ViewerAppl(int w, int h) {
		super(w, h);
	}

	private Node root;
	
	private Node firstChild;
	
	private ComplexGraph graph;
	
	@Override
	public void load() {
		
		loading = 10;
		
		graph = new ComplexGraph();
		
		root = new Node();
		root.setLocation(80, 190);
		
		firstChild = new Node();		
		Node secondChild = new Node();
		Node thirdChild = new Node();
		
		Node firstChildSon = new Node();		
		
		loading = 20;
		
		//Add three child nodes
		graph.addNode(root);
		graph.addNode(firstChild);
		graph.addNode(secondChild);
		graph.addNode(thirdChild);
		graph.addNode(firstChildSon);
		
		loading = 30;
		graph.addEdge(new Edge(root, firstChild));
		graph.addEdge(new Edge(root, secondChild));
		graph.addEdge(new Edge(root, thirdChild));		
		
		loading = 35;
		graph.addEdge(new Edge(firstChild, new Node()));
		graph.addEdge(new Edge(firstChild, new Node()));
		
		loading = 40;
		moveChildrenNodes(root);
		
		loading = 100;
	}

	@Override
	public void draw(Graphic g) {
		
		drawNode(g, root);
	}
	
	private void drawLeaf(Graphic g, Node node) {
				
		g.fillCircle(node, 5);
		
	}
	
	private void drawNode(Graphic g, Node node) {
						
		//Draw Children
		drawEdges(g, node);
		
		//Draw Node itself
		g.setColor(SVGColor.BLACK);
		drawLeaf(g, node);
	}
		
	private void drawEdges(Graphic g, Node node) {
		
		List<Edge> edges = graph.getEdges(node);
		
		g.setColor(SVGColor.RED);
		
		for(Edge edge: edges) {
			
			g.drawLine(edge.getOrigin(), edge.getDestination());
			
			drawNode(g, edge.getDestination());
		}
				
	}
	
	private void moveChildrenNodes(Node node) {
		
		List<Edge> edges = graph.getEdges(node);
						
		int size = edges.size()+1;
		
		double theta = Math.PI / size;
		
		double distance = 40;
		
		int i = 0;
				
		for(Edge edge: edges) {
			
			i++;
			
			Node destination = edge.getDestination();
			
			double x = node.getX() + distance * Math.cos(theta * i);
		    double y = node.getY() + distance * Math.sin(theta * i);
		    
		    destination.setLocation(x, y);
		    
		    moveChildrenNodes(destination);
		}
		
	}
		
	@Override
	public void update(long now) {
		
	}
	
	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		// TODO Auto-generated method stub
		return null;
	}
		
	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		
		// TODO Auto-generated method stub
		return null;
	}
	

}
