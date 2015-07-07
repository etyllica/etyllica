package br.com.abby.linear;

import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import br.com.abby.adapter.PointToVectorAdapter;
import br.com.etyllica.collision.CollisionStatus;

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

	public Vector3f ntl,ntr,nbl,nbr,ftl,ftr,fbl,fbr;

	public float nearD, farD, ratio, angle,tan;
	public float nWidth, nHeight, fWidth, fHeight;

	private static final float ANG2RAD = (float)Math.PI/180.0f;

	public Frustrum() {
		for(int i = 0; i < planes.length; i++) {
			planes[i] = new Plane();
		}
	}

	public void setCamInternals(float angle, float ratio, float nearDistance, float farDistance) {

		// store the information
		this.ratio = ratio;
		this.angle = angle;
		this.nearD = nearDistance;
		this.farD = farDistance;

		// compute width and height of the near and far plane sections
		//tan = (float)Math.tan(Math.toRadians(angle * 0.5));
		tan = (float)Math.tan(ANG2RAD * angle * 0.5);
		nHeight = nearDistance * tan;
		nWidth = nHeight * ratio;
		fHeight = farDistance * tan;
		fWidth = fHeight * ratio;
	}

	public void setCamDef(Camera3D camera) {

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
		nearZ.scale(nearD);

		Vector3f farZ = new Vector3f(Z);
		farZ.scale(farD);

		Vector3f nc = Vector3f.sub(position, nearZ, null);
		Vector3f fc = Vector3f.sub(position, farZ, null);

		Vector3f nwX = new Vector3f(X);
		nwX.scale(nWidth);

		Vector3f fwX = new Vector3f(X);
		fwX.scale(fWidth);

		Vector3f nhY = new Vector3f(Y);
		nhY.scale(nHeight);

		Vector3f fhY = new Vector3f(Y);
		fhY.scale(fHeight);

		// compute the 4 corners of the frustum on the near plane
		ntl = Vector3f.add(nc, Vector3f.sub(nhY, nwX, null), null);
		ntr = Vector3f.add(nc, Vector3f.add(nhY, nwX, null), null);
		nbl = Vector3f.sub(nc, Vector3f.sub(nhY, nwX, null), null);
		nbr = Vector3f.sub(nc, Vector3f.add(nhY, nwX, null), null);

		// compute the 4 corners of the frustum on the far plane
		ftl = Vector3f.add(fc, Vector3f.sub(fhY, fwX, null), null);
		ftr = Vector3f.add(fc, Vector3f.add(fhY, fwX, null), null);
		fbl = Vector3f.sub(fc, Vector3f.sub(fhY, fwX, null), null);
		fbr = Vector3f.sub(fc, Vector3f.add(fhY, fwX, null), null);

		// compute the six planes
		// the function setPoints assumes that the points
		// are given in counter clockwise order
		planes[TOP].setPoints(ntr, ntl, ftl);
		planes[BOTTOM].setPoints(nbl, nbr, fbr);
		planes[LEFT].setPoints(ntl, nbl, fbl);
		planes[RIGHT].setPoints(nbr, ntr, fbr);
		planes[NEAR_PLANE].setPoints(ntl, ntr, nbr);
		planes[FAR_PLANE].setPoints(ftr, ftl, fbl);
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
		return(result);
	}

}
