package br.com.abby.linear;

import java.util.List;

import br.com.abby.util.PointToVectorAdapter;
import br.com.etyllica.core.collision.CollisionStatus;

import com.badlogic.gdx.math.Vector3;

/**
 * Frustrum class based on: http://cgvr.cs.uni-bremen.de/teaching/cg_literatur/lighthouse3d_view_frustum_culling/
 */
public class Frustrum {

	private static final int TOP = 0;
	private static final int BOTTOM = 1;
	private static final int LEFT = 2;
	private static final int RIGHT = 3;
	private static final int NEAR_PLANE = 4;
	private static final int FAR_PLANE = 5;

	public Plane planes[] = new Plane[6];

	public Vector3 nTopLeft,nTopRight,nBottomLeft,nBottomRight,fTopLeft,fTopRight,fBottomLeft,fBottomRight;

	public double nearD, farD, ratio, angle, tan;
	public double nWidth, nHeight, fWidth, fHeight;

	private static final float ANG2RAD = (float)Math.PI/180.0f;

	public Frustrum() {
		for(int i = 0; i < planes.length; i++) {
			planes[i] = new Plane();
		}
	}

	public void setCamInternals(double angle, double ratio, double nearDistance, double farDistance) {

		// store the information
		this.ratio = ratio;
		this.angle = angle;
		this.nearD = nearDistance;
		this.farD = farDistance;

		// compute width and height of the near and far plane sections
		//tan = (float)Math.tan(Math.toRadians(angle * 0.5));
		tan = Math.tan(ANG2RAD * angle * 0.5);
		nHeight = nearDistance * tan;
		nWidth = nHeight * ratio;
		fHeight = farDistance * tan;
		fWidth = fHeight * ratio;
	}

	public void setCamDef(Camera camera) {

		Vector3 position = PointToVectorAdapter.adapt(camera);
		Vector3 target = PointToVectorAdapter.adapt(camera.getTarget());
		Vector3 up = PointToVectorAdapter.adapt(camera.getNormal());

		setCamDef(position, target, up);
	}

	public void setCamDef(Vector3 position, Vector3 target, Vector3 up) {

		// compute the Z axis of camera
		// this axis points in the opposite direction from 
		// the looking direction
		Vector3 Z = new Vector3(position).sub(target);
		Z.nor();

		// X axis of camera with given "up" vector and Z axis
		Vector3 X = new Vector3(up).crs(Z);
		X.nor();

		// the real "up" vector is the cross product of Z and X
		Vector3 Y = new Vector3(Z).crs(X);

		// compute the centers of the near and far planes
		Vector3 nearZ = new Vector3(Z);
		nearZ.scl((float)nearD);

		Vector3 farZ = new Vector3(Z);
		farZ.scl((float)farD);

		Vector3 nc = new Vector3(position).sub(nearZ);
		Vector3 fc = new Vector3(position).sub(farZ);

		Vector3 nwX = new Vector3(X);
		nwX.scl((float)nWidth);

		Vector3 fwX = new Vector3(X);
		fwX.scl((float)fWidth);

		Vector3 nhY = new Vector3(Y);
		nhY.scl((float)nHeight);

		Vector3 fhY = new Vector3(Y);
		fhY.scl((float)fHeight);
		
		// compute the 4 corners of the frustum on the near plane
		nTopLeft = new Vector3(nc).add(nhY).sub(nwX);
		nTopRight = new Vector3(nc).add(nhY).add(nwX);
		nBottomLeft = new Vector3(nc).sub(nhY).sub(nwX);
		nBottomRight = new Vector3(nc).sub(nhY).add(nwX);

		// compute the 4 corners of the frustum on the far plane
		fTopLeft = new Vector3(fc).add(fc).sub(fwX);
		fTopRight = new Vector3(fc).add(fc).add(fwX);
		fBottomLeft = new Vector3(fc).sub(fc).sub(fwX);
		fBottomRight = new Vector3(fc).sub(fhY).add(fwX);
		
		// compute the six planes
		// the function setPoints assumes that the points
		// are given in counter clockwise order
		
		planes[TOP].setPoints(nTopRight, nTopLeft, fTopLeft);
		planes[BOTTOM].setPoints(nBottomLeft, nBottomRight, fBottomRight);
		planes[LEFT].setPoints(nTopLeft, nBottomLeft, fBottomLeft);
		planes[RIGHT].setPoints(nBottomRight, nTopRight, fBottomRight);
		planes[NEAR_PLANE].setPoints(nTopLeft, nTopRight, nBottomRight);
		planes[FAR_PLANE].setPoints(fTopRight, fTopLeft, fBottomLeft);
	}

	public CollisionStatus pointInFrustum(Vector3 p) {
		CollisionStatus result = CollisionStatus.INSIDE;
		for(int i=0; i < 6; i++) {
			if (planes[i].distance(p) < 0) {
				return CollisionStatus.OUTSIDE;
			}
		}

		return result;
	}
	
	public CollisionStatus sphereInFrustum(Vector3 p, float radius) {
		double distance;
		CollisionStatus result = CollisionStatus.INSIDE;
		
		for(int i=0; i < 6; i++) {
			distance = planes[i].distance(p);
			if (distance < -radius)
				return CollisionStatus.OUTSIDE;
			else if (distance < radius)
				result =  CollisionStatus.INTERSECT;
		}
		
		return result;
	}
	
	public CollisionStatus boxInFrustum(BoundingBox3D b) {
		CollisionStatus result = CollisionStatus.INSIDE;
		
		int out, in;

		// for each plane do ...
		for(int i=0; i < 6; i++) {

			// reset counters for corners in and out
			out=0;in=0;
			// for each corner of the box do ...
			// get out of the cycle as soon as a box as corners
			// both inside and out of the frustum
			for (int k = 0; k < 8 && (in==0 || out==0); k++) {
			
				List<Vector3> boxList = b.getVertexList();
				
				// is the corner outside or inside
				if (planes[i].distance(boxList.get(k)) < 0) {
					out++;
				} else {
					in++;
				}
			}
			//if all corners are out
			if (in == 0) {
				return (CollisionStatus.OUTSIDE);
			}
			// if some corners are out and others are in	
			else if (out != 0) {
				result = CollisionStatus.INTERSECT;
			}
		}
		
		return result;
	}
	
	/*public void drawFrustrumNormals(Frustrum frustrum) {
	GL2 gl = getGL2();

	gl.glBegin(GL2.GL_LINES);
	
	Vector3 a,b;

		// near
		a = (ntr + ntl + nbr + nbl) * 0.25;
		b = a + pl[NEARP].normal;
		glVertex3f(a.x,a.y,a.z);
		glVertex3f(b.x,b.y,b.z);

		// far
		a = (ftr + ftl + fbr + fbl) * 0.25;
		b = a + pl[FARP].normal;
		glVertex3f(a.x,a.y,a.z);
		glVertex3f(b.x,b.y,b.z);

		// left
		a = (ftl + fbl + nbl + ntl) * 0.25;
		b = a + pl[LEFT].normal;
		glVertex3f(a.x,a.y,a.z);
		glVertex3f(b.x,b.y,b.z);
		
		// right
		a = (ftr + nbr + fbr + ntr) * 0.25;
		b = a + pl[RIGHT].normal;
		glVertex3f(a.x,a.y,a.z);
		glVertex3f(b.x,b.y,b.z);
		
		// top
		a = (ftr + ftl + ntr + ntl) * 0.25;
		b = a + pl[TOP].normal;
		glVertex3f(a.x,a.y,a.z);
		glVertex3f(b.x,b.y,b.z);
		
		// bottom
		a = (fbr + fbl + nbr + nbl) * 0.25;
		b = a + pl[BOTTOM].normal;
		glVertex3f(a.x,a.y,a.z);
		glVertex3f(b.x,b.y,b.z);

	glEnd();

}*/
	
}
