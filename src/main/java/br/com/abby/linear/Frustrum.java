package br.com.abby.linear;

import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import br.com.abby.util.PointToVectorAdapter;
import br.com.etyllica.core.collision.CollisionStatus;

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

	public Vector3f nTopLeft,nTopRight,nBottomLeft,nBottomRight,fTopLeft,fTopRight,fBottomLeft,fBottomRight;

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

		Vector3f position = PointToVectorAdapter.adapt(camera);
		Vector3f target = PointToVectorAdapter.adapt(camera.getTarget());
		Vector3f up = PointToVectorAdapter.adapt(camera.getNormal());

		setCamDef(position, target, up);
	}

	public void setCamDef(Vector3f position, Vector3f target, Vector3f up) {

		// compute the Z axis of camera
		// this axis points in the opposite direction from 
		// the looking direction
		Vector3f Z = Vector3f.sub(position, target, null);
		Z.normalise();

		// X axis of camera with given "up" vector and Z axis
		Vector3f X = Vector3f.cross(up, Z, null);
		X.normalise();

		// the real "up" vector is the cross product of Z and X
		Vector3f Y = Vector3f.cross(Z, X, null);

		// compute the centers of the near and far planes
		Vector3f nearZ = new Vector3f(Z);
		nearZ.scale((float)nearD);

		Vector3f farZ = new Vector3f(Z);
		farZ.scale((float)farD);

		Vector3f nc = Vector3f.sub(position, nearZ, null);
		Vector3f fc = Vector3f.sub(position, farZ, null);

		Vector3f nwX = new Vector3f(X);
		nwX.scale((float)nWidth);

		Vector3f fwX = new Vector3f(X);
		fwX.scale((float)fWidth);

		Vector3f nhY = new Vector3f(Y);
		nhY.scale((float)nHeight);

		Vector3f fhY = new Vector3f(Y);
		fhY.scale((float)fHeight);

		// compute the 4 corners of the frustum on the near plane
		nTopLeft = Vector3f.add(nc, Vector3f.sub(nhY, nwX, null), null);
		nTopRight = Vector3f.add(nc, Vector3f.add(nhY, nwX, null), null);
		nBottomLeft = Vector3f.sub(nc, Vector3f.sub(nhY, nwX, null), null);
		nBottomRight = Vector3f.sub(nc, Vector3f.add(nhY, nwX, null), null);

		// compute the 4 corners of the frustum on the far plane
		fTopLeft = Vector3f.add(fc, Vector3f.sub(fhY, fwX, null), null);
		fTopRight = Vector3f.add(fc, Vector3f.add(fhY, fwX, null), null);
		fBottomLeft = Vector3f.sub(fc, Vector3f.sub(fhY, fwX, null), null);
		fBottomRight = Vector3f.sub(fc, Vector3f.add(fhY, fwX, null), null);

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

	public CollisionStatus pointInFrustum(Vector3f p) {
		CollisionStatus result = CollisionStatus.INSIDE;
		for(int i=0; i < 6; i++) {
			if (planes[i].distance(p) < 0) {
				return CollisionStatus.OUTSIDE;
			}
		}

		return result;
	}
	
	public CollisionStatus sphereInFrustum(Vector3f p, float radius) {
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
			
				List<Vector3f> boxList = b.getVertexList();
				
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
	
	Vector3f a,b;

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
