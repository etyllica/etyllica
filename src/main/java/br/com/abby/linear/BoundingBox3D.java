package br.com.abby.linear;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import br.com.abby.adapter.PointToVectorAdapter;
import br.com.etyllica.linear.Point3D;

/**
 * AABB Bounding Box
 */
public class BoundingBox3D {

	private Point3D minPoint;
	private Point3D maxPoint;

	public BoundingBox3D() {
		super();
		this.minPoint = new Point3D(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
		this.maxPoint = new Point3D(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
	}
	
	public BoundingBox3D(Point3D minPoint, Point3D maxPoint) {
		super();
		this.minPoint = minPoint;
		this.maxPoint = maxPoint;
	}
	
	public BoundingBox3D(BoundingBox3D box) {
		super();
		this.minPoint = new Point3D(box.minPoint);
		this.maxPoint = new Point3D(box.maxPoint);
	}

	public void add(Vector3f vertex) {
		addPoint(PointToVectorAdapter.adapt(vertex));
	}
	
	public void addPoint(Point3D point) {
		double px = point.getX();
		double py = point.getY();
		double pz = point.getZ();

		if(px < minPoint.getX()) {
			minPoint.setX(px);
		} else if(px > maxPoint.getX()) {
			maxPoint.setX(px);
		}

		if(py < minPoint.getY()) {
			minPoint.setY(py);
		} else if(py > maxPoint.getY()) {
			maxPoint.setY(py);
		}

		if(pz < minPoint.getZ()) {
			minPoint.setZ(pz);
		} else if(pz > maxPoint.getZ()) {
			maxPoint.setZ(pz);
		}
	}

	/**
	 * Test if another BoundingBox intersects
	 * Based on http://forum.devmaster.net/t/aabb-collision/12934/5
	 * @param box
	 * @return boolean
	 */
	public boolean intersects(BoundingBox3D box) {
		boolean intersectX = minPoint.getX() > box.minPoint.getX() || maxPoint.getX() < box.maxPoint.getX();
		boolean intersectY = minPoint.getY() > box.minPoint.getY() || maxPoint.getY() < box.maxPoint.getY();
		boolean intersectZ = minPoint.getZ() > box.minPoint.getZ() || maxPoint.getZ() < box.maxPoint.getZ();
		
		return intersectX || intersectY || intersectZ;
	}

	/**
	 * Test if another BoundingBox is inside
	 * Based on http://forum.devmaster.net/t/aabb-collision/12934/5
	 * @param box
	 * @return boolean
	 */
	public boolean contains(BoundingBox3D box) {
		boolean containsX = minPoint.getX() <= box.minPoint.getX() && maxPoint.getX() >= box.maxPoint.getX();
		boolean containsY = minPoint.getY() <= box.minPoint.getY() && maxPoint.getY() >= box.maxPoint.getY();
		boolean containsZ = minPoint.getZ() <= box.minPoint.getZ() && maxPoint.getZ() >= box.maxPoint.getZ();

		return containsX && containsY && containsZ;
	}

	public boolean contains(Point3D point) {
		if(point.getX() >= minPoint.getX() && point.getX() <= maxPoint.getX() &&
				point.getY() >= minPoint.getY() && point.getY() <= maxPoint.getY() &&
				point.getZ() >= minPoint.getZ() && point.getZ() <= maxPoint.getZ()) {
			return true;
		}

		return false;
	}
	
	public Point3D getMinPoint() {
		return minPoint;
	}

	public Point3D getMaxPoint() {
		return maxPoint;
	}

	public double getVolume() {
		double dx = (maxPoint.getX()-minPoint.getX())/2;
		double dy = (maxPoint.getY()-minPoint.getY())/2;
		double dz = (maxPoint.getZ()-minPoint.getZ())/2;

		return dx*dy*dz;
	}
	
	public Point3D getCenter() {
		double cx = (maxPoint.getX()+minPoint.getX())/2;
		double cy = (maxPoint.getY()+minPoint.getY())/2;
		double cz = (maxPoint.getZ()+minPoint.getZ())/2;
		
		return new Point3D(cx, cy, cz); 
	}

	public List<Vector3f> getVertexList() {
		
		List<Vector3f> vertexList = new ArrayList<Vector3f>();
		
		vertexList.add(PointToVectorAdapter.adapt(minPoint));
		vertexList.add(new Vector3f((float)maxPoint.getX(), (float)minPoint.getY(), (float)minPoint.getZ()));
		vertexList.add(new Vector3f((float)minPoint.getX(), (float)maxPoint.getY(), (float)minPoint.getZ()));
		vertexList.add(new Vector3f((float)minPoint.getX(), (float)maxPoint.getY(), (float)maxPoint.getZ()));
		vertexList.add(new Vector3f((float)maxPoint.getX(), (float)maxPoint.getY(), (float)minPoint.getZ()));
		vertexList.add(new Vector3f((float)maxPoint.getX(), (float)minPoint.getY(), (float)maxPoint.getZ()));
		vertexList.add(new Vector3f((float)minPoint.getX(), (float)minPoint.getY(), (float)maxPoint.getZ()));
		vertexList.add(PointToVectorAdapter.adapt(maxPoint));
		
		return vertexList;
	}
}
