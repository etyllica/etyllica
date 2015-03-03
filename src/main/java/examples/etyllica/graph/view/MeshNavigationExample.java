package examples.etyllica.graph.view;

import java.util.List;

import br.com.etyllica.context.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.graphics.SVGColor;
import br.com.etyllica.linear.graph.GenericEdge;
import br.com.tide.ai.pathfind.NavigationMesh;
import br.com.tide.ai.pathfind.PolygonalNode;

public class MeshNavigationExample extends Application {

	public MeshNavigationExample(int w, int h) {
		super(w, h);
	}
	
	private PolygonalNode root;
	
	private PolygonalNode firstChild;
	
	private NavigationMesh graph;
	
	private final double nodeDistance = 40;
	
	@Override
	public void load() {
		
		loading = 10;
		
		graph = new NavigationMesh();
		
		root = new PolygonalNode();
		root.setLocation(80, 80);
		root.addPoint(60, 45);
		root.addPoint(90, 55);
		root.addPoint(100, 90);
		root.addPoint(70, 110);
		
		firstChild = new PolygonalNode();
		PolygonalNode secondChild = new PolygonalNode();
		
		PolygonalNode firstChildSon = new PolygonalNode();
		
		//Add three child nodes
		graph.addNode(root);
		graph.addNode(firstChild);
		graph.addNode(secondChild);
				
		graph.addEdge(new GenericEdge<PolygonalNode>(root, firstChild));
		graph.addEdge(new GenericEdge<PolygonalNode>(root, secondChild));
		graph.addEdge(new GenericEdge<PolygonalNode>(firstChild, secondChild));
		graph.hasEdge(firstChild, secondChild);
				
		graph.addEdge(new GenericEdge<PolygonalNode>(firstChild, firstChildSon));
		graph.addEdge(new GenericEdge<PolygonalNode>(firstChild, new PolygonalNode()));
						
		moveNodes(root);
		
		loading = 100;
	}
	
	@Override
	public void draw(Graphic g) {
		
		drawNode(g, root);
	}
	
	private void drawLeaf(Graphic g, PolygonalNode node) {
				
		g.fillCircle(node.getPoint(), 5);
		
	}
	
	private void drawNode(Graphic g, PolygonalNode node) {
						
		//Draw Children
		drawEdges(g, node);
		
		//Draw Node itself
		g.setColor(SVGColor.BLACK);
		drawLeaf(g, node);
		
		node.getPolygon().draw(g);
	}
		
	private void drawEdges(Graphic g, PolygonalNode node) {
		
		List<GenericEdge<PolygonalNode>> edges = graph.getEdges(node);
		
		g.setColor(SVGColor.RED);
		
		for(GenericEdge<PolygonalNode> edge: edges) {
			
			g.drawLine(edge.getOrigin().getPoint(), edge.getDestination().getPoint());
			
			drawNode(g, edge.getDestination());
		}
				
	}
	
	public void moveNodes(PolygonalNode root) {
		moveChildrenNodes(root, 0);
	}
	
	private void moveChildrenNodes(PolygonalNode node, double initialAngle) {
		
		List<GenericEdge<PolygonalNode>> edges = graph.getEdges(node);
						
		int size = edges.size()+1;
		
		double theta = 180 / size;
				
		int i = 0;
				
		for(GenericEdge<PolygonalNode> edge: edges) {
			
			i++;
			
			PolygonalNode destination = edge.getDestination();
			
			//Avoid rotate brother nodes
			if(edge.getOrigin().getParent() == destination.getParent()) {
				continue;
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
