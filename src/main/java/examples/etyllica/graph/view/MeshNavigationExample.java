package examples.etyllica.graph.view;

import java.util.List;

import br.com.etyllica.awt.SVGColor;
import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.linear.graph.GenericEdge;
import br.com.etyllica.linear.graph.Node;
import br.com.tide.ai.pathfind.NavigationMesh;
import br.com.tide.ai.pathfind.PolygonalData;

public class MeshNavigationExample extends Application {

	public MeshNavigationExample(int w, int h) {
		super(w, h);
	}
	
	private Node<PolygonalData> root;
	
	private Node<PolygonalData> firstChild;
	
	private NavigationMesh<PolygonalData> graph;
	
	private final double nodeDistance = 40;
	
	@Override
	public void load() {
		
		loading = 10;
		
		graph = new NavigationMesh<PolygonalData>();
		
		root = new Node<PolygonalData>(new PolygonalData("Root"));
		root.setLocation(280, 80);
		
		root.getData().addPoint(260, 45);
		root.getData().addPoint(290, 55);
		root.getData().addPoint(300, 90);
		root.getData().addPoint(270, 110);
		
		firstChild = new Node<PolygonalData>(new PolygonalData("1st Child"));
		Node<PolygonalData> secondChild = new Node<PolygonalData>(new PolygonalData("2nd Child"));
		Node<PolygonalData> firstChildSon = new Node<PolygonalData>(new PolygonalData("1st, First Child"));
		
		//Add three child nodes
		graph.addNode(root);
		graph.addNode(firstChild);
		graph.addNode(secondChild);
				
		graph.addEdge(new GenericEdge<PolygonalData>(root, firstChild));
		graph.addEdge(new GenericEdge<PolygonalData>(root, secondChild));
		graph.addEdge(new GenericEdge<PolygonalData>(firstChild, secondChild));
		graph.hasEdge(firstChild, secondChild);
				
		graph.addEdge(new GenericEdge<PolygonalData>(firstChild, firstChildSon));
		graph.addEdge(new GenericEdge<PolygonalData>(firstChild, new Node<PolygonalData>(new PolygonalData(("Another Node")))));
						
		moveNodes(root);
		
		loading = 100;
	}
	
	@Override
	public void draw(Graphic g) {
		drawNode(g, root);
	}
	
	private void drawLeaf(Graphic g, Node<PolygonalData> node) {
		g.fillCircle(node.getPoint(), 5);
	}
	
	private void drawNode(Graphic g, Node<PolygonalData> node) {
						
		//Draw Children
		drawEdges(g, node);
		
		//Draw Node itself
		g.setColor(SVGColor.BLACK);
		drawLeaf(g, node);
		
		node.getData().getPolygon().draw(g);
	}
		
	private void drawEdges(Graphic g, Node<PolygonalData> node) {
		
		List<GenericEdge<PolygonalData>> edges = graph.getEdges(node);
		
		g.setColor(SVGColor.RED);
		
		for(GenericEdge<PolygonalData> edge: edges) {
			
			g.drawLine(edge.getOrigin().getPoint(), edge.getDestination().getPoint());
			
			drawNode(g, edge.getDestination());
		}

	}
	
	public void moveNodes(Node<PolygonalData> root) {
		moveChildrenNodes(root, 0);
	}
	
	private void moveChildrenNodes(Node<PolygonalData> node, double initialAngle) {
		
		List<GenericEdge<PolygonalData>> edges = graph.getEdges(node);
						
		int size = edges.size()+1;
		
		double theta = 180 / size;
				
		int i = 0;
					
		for(GenericEdge<PolygonalData> edge: edges) {
			
			i++;
			
			Node<PolygonalData> destination = edge.getDestination();
			
			System.out.println(edge.getOrigin().getData().getName()+" -- "+edge.getDestination().getData().getName());
			System.out.println("Parent: "+edge.getOrigin().getParent().getData().getName());
			
			//Avoid rotate brother nodes
			if(edge.getOrigin().getParent() == destination.getParent()) {
				
				//if(edge.getOrigin().equals())
								
				System.out.println("Avoid !!");
				System.out.println(edge.getOrigin().getData().getName()+" -- "+edge.getDestination().getData().getName());
				//continue;
			}
			
			double angle = (theta * i);
			
			if(initialAngle > 90) {
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
