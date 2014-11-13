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
	
	private final double nodeDistance = 40;
	
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
		
		//Add three child nodes
		graph.addNode(root);
		graph.addNode(firstChild);
		graph.addNode(secondChild);
		graph.addNode(thirdChild);
		
		graph.addEdge(new Edge(root, firstChild));
		graph.addEdge(new Edge(root, secondChild));
		graph.addEdge(new Edge(root, thirdChild));
		
		graph.addEdge(new Edge(firstChild, firstChildSon));
		graph.addEdge(new Edge(firstChild, new Node()));		
		
		
		moveNodes(root);
		
		loading = 100;
	}
	
	@Override
	public void draw(Graphic g) {
		
		drawNode(g, root);
	}
	
	private void drawLeaf(Graphic g, Node node) {
				
		g.fillCircle(node.getPoint(), 5);
		
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
			
			g.drawLine(edge.getOrigin().getPoint(), edge.getDestination().getPoint());
			
			drawNode(g, edge.getDestination());
		}
				
	}
	
	public void moveNodes(Node root) {
		moveChildrenNodes(root, 0);
	}
	
	private void moveChildrenNodes(Node node, double initialAngle) {
		
		List<Edge> edges = graph.getEdges(node);
						
		int size = edges.size()+1;
		
		double theta = 180 / size;
				
		int i = 0;
				
		for(Edge edge: edges) {
			
			i++;
			
			Node destination = edge.getDestination();
			
			double angle = (theta * i);
			
			if(initialAngle>90) {
				angle += initialAngle-90;
			} else {
				angle -= initialAngle;
			}
			
			double x = node.getPoint().getX() + nodeDistance * Math.cos(Math.toRadians(angle));
		    double y = node.getPoint().getY() + nodeDistance * Math.sin(Math.toRadians(angle));
		    
		    destination.setLocation(x, y);
		    		    
		    moveChildrenNodes(destination, angle);
		    
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
