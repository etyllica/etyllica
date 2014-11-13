package br.com.etyllica.linear.graph;

public class WeightEdge extends Edge {
	
	private int weight = 0;
	
	public WeightEdge(Node origin, Node destination) {
		super(origin, destination);		
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
		
}
