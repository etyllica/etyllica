package examples.etyllica.graph.view;

import java.util.List;

import br.com.etyllica.awt.SVGColor;
import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.core.linear.Point2D;
import br.com.etyllica.linear.graph.Node;
import br.com.etyllica.linear.graph.common.IntegerEdge;
import br.com.etyllica.linear.graph.common.IntegerGraph;

public class SimpleGraphExample extends Application{

	public SimpleGraphExample(int w, int h) {
		super(w, h);
	}

	private Node<Integer> root;
	
	private Node<Integer> firstChild;
	
	private IntegerGraph graph;
	
	private final double nodeDistance = 40;
	
	@Override
	public void load() {
		
		loading = 10;
		
		graph = new IntegerGraph();
		
		root = new Node<Integer>(0);
		root.setLocation(80, 190);
		
		firstChild = new Node<Integer>(1);		
		Node<Integer> secondChild = new Node<Integer>(2);
		Node<Integer> thirdChild = new Node<Integer>(3);
		
		Node<Integer> firstChildSon = new Node<Integer>(4);
		
		//Add three child nodes
		graph.addNode(root);
		graph.addNode(firstChild);
		graph.addNode(secondChild);
		graph.addNode(thirdChild);
		
		graph.addEdge(new IntegerEdge(root, firstChild));
		graph.addEdge(new IntegerEdge(root, secondChild));
		graph.addEdge(new IntegerEdge(root, thirdChild));
		
		graph.addEdge(new IntegerEdge(firstChild, firstChildSon));
		graph.addEdge(new IntegerEdge(firstChild, new Node<Integer>(84)));		
		
		
		moveNodes(root);
		
		loading = 100;
	}
	
	@Override
	public void draw(Graphics g) {
		
		drawNode(g, root);
	}
	
	private void drawLeaf(Graphics g, Node<Integer> node) {
		Point2D point = node.getPoint();
		
		int radius = 12;
		
		g.setColor(SVGColor.BLACK);
		g.fillCircle(point, radius);
		g.setColor(SVGColor.WHITE);
		
		int x = (int)point.getX()-radius;
		int y = (int)point.getY()-radius;
		int w = radius*2;
		int h = radius*2;
		
		g.drawStringBorder(Integer.toString(node.getData()), x, y, w, h);
		g.setColor(SVGColor.BLACK);
	}
	
	private void drawNode(Graphics g, Node<Integer> node) {
						
		//Draw Children
		drawEdges(g, node);
		
		//Draw Node itself
		drawLeaf(g, node);
	}
		
	private void drawEdges(Graphics g, Node<Integer> node) {
		
		List<IntegerEdge> edges = graph.getEdges(node);
		
		g.setColor(SVGColor.RED);
		
		for(IntegerEdge edge: edges) {
			
			g.drawLine(edge.getOrigin().getPoint(), edge.getDestination().getPoint());
			
			drawNode(g, edge.getDestination());
		}
				
	}
	
	public void moveNodes(Node<Integer> root) {
		moveChildrenNodes(root, 0);
	}
	
	private void moveChildrenNodes(Node<Integer> node, double initialAngle) {
		
		List<IntegerEdge> edges = graph.getEdges(node);
						
		int size = edges.size()+1;
		
		double theta = 180 / size;
				
		int i = 0;
				
		for(IntegerEdge edge: edges) {
			
			i++;
			
			Node<Integer> destination = edge.getDestination();
			
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

}
