package br.com.etyllica.storage.octree;

import java.util.Set;

import br.com.abby.linear.BoundingBox3D;
import br.com.etyllica.linear.Point3D;

public interface Octree<T> {
	
	public static final int BELOW_LEFT_LOWER = 0;
	public static final int BELOW_LEFT_UPPER = 1;
	public static final int BELOW_RIGHT_LOWER = 2;
	public static final int BELOW_RIGHT_UPPER = 3;
	public static final int ABOVE_LEFT_LOWER = 4;
	public static final int ABOVE_LEFT_UPPER = 5;
	public static final int ABOVE_RIGHT_LOWER = 6;
	public static final int ABOVE_RIGHT_UPPER = 7;
	
	public void add(Point3D point, T data);
	
	public OctreeNode<T> getRoot();
	
	public Set<T> getData(BoundingBox3D box);
	
	public Set<OctreeNode<T>> getNodes(BoundingBox3D box);
	
}
