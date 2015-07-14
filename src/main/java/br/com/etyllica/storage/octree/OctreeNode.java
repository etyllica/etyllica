package br.com.etyllica.storage.octree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.abby.linear.BoundingBox3D;
import br.com.etyllica.core.linear.Point3D;

public class OctreeNode<T> {

	protected BoundingBox3D box;

	protected Map<Integer, OctreeNode<T>> children;
	
	protected Set<T> dataSet = new HashSet<T>();

	protected List<Point3D> geometry = new ArrayList<Point3D>();

	public OctreeNode(BoundingBox3D box) {
		super();
		this.box = box;

		children = new HashMap<Integer, OctreeNode<T>>(8);
	}

	protected Map<Integer, OctreeNode<T>> getChildren() {
		return children;
	}
	
	public Collection<OctreeNode<T>> getChildrenNodes() {
		return children.values();
	}

	public BoundingBox3D getBox() {
		return box;
	}
		
}

