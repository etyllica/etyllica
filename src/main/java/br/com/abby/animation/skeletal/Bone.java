package br.com.abby.animation.skeletal;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.jgl.GL;
import org.jgl.GLAUX;

import br.com.abby.GLDrawable;
import br.com.abby.linear.AimPoint;

import com.badlogic.gdx.math.Vector3;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Bone implements GLDrawable {

	private String name;

	protected Color color = Color.WHITE;

	private double size;

	private double angleX = 0;
	private double angleY = 0;
	private double angleZ = 0;

	private List<Vector3> vectors = new ArrayList<Vector3>();

	private Joint orign = null;

	protected Joint end = null;

	public Bone(String name) {
		super();
	}
	
	public Bone(double size) {
		super();
		
		setSize(size);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOrign(Joint orign) {
		this.orign = orign;
		
		this.end.translate(orign.getX(), orign.getY(), orign.getZ());
	}

	public void translate(double tx, double ty, double tz) {

		if(vectors!=null) {

			for(Vector3 vector: vectors) {

				//Initial values
				double ix = vector.x;
				double iy = vector.y;
				double iz = vector.z;

				double vx = ix+tx;
				double vy = iy+ty;
				double vz = iz+tz;

				vector.x = ((float)vx);
				vector.y = ((float)vy);
				vector.z = ((float)vz);
			}

		}

		if(end!=null) {

			end.translate(tx,ty,tz);

		}
	}

	public void rotateX(double angle) {

		double px = 0;
		double py = 0;
		double pz = 0;

		if(orign != null) {

			px = orign.getX();
			py = orign.getY();
			pz = orign.getZ();

		}
		
		rotateX(angle, px, py, pz);
		
	}

	public void rotateY(double angle) {

		double px = 0;
		double py = 0;
		double pz = 0;

		if(orign != null) {

			px = orign.getX();
			py = orign.getY();
			pz = orign.getZ();

		}

		rotateY(angle, px, py, pz);

	}

	public void rotateZ(double angle) {

		double px = 0;
		double py = 0;
		double pz = 0;

		if(orign != null) {

			px = orign.getX();
			py = orign.getY();
			pz = orign.getZ();

		}

		rotateZ(angle, px, py, pz);

	}

	public void rotateX(double angle, double px, double py, double pz) {

		this.angleX += angle;

		rotateOver(angle, px, py, pz, AimPoint.rotationMatrixX(angle, px, py, pz));

		end.rotateXOver(angle,px,py,pz);

	}

	public void rotateY(double angle, double px, double py, double pz) {

		this.angleY += angle;

		rotateOver(angle, px, py, pz, AimPoint.rotationMatrixY(angle, px, py, pz));

		end.rotateYOver(angle,px,py,pz);

	}

	public void rotateZ(double angle, double px, double py, double pz) {

		this.angleZ += angle;

		rotateOver(angle, px, py, pz, AimPoint.rotationMatrixZ(angle, px, py, pz));

		end.rotateZOver(angle,px,py,pz);

	}

	private void rotateOver(double angle, double px, double py, double pz, double m[][]) {

		if(vectors!=null) {

			for(Vector3 vector: vectors) {

				//Initial values
				double ix = vector.x;
				double iy = vector.y;
				double iz = vector.z;

				double vx = ix*m[0][0]+iy*m[0][1]+iz*m[0][2]+m[0][3];
				double vy = ix*m[1][0]+iy*m[1][1]+iz*m[1][2]+m[1][3];
				double vz = ix*m[2][0]+iy*m[2][1]+iz*m[2][2]+m[2][3];

				vector.x = ((float)(vx));
				vector.y = ((float)(vy));
				vector.z = ((float)(vz));

			}

		}

		rotateEndJoint(angle, px, py, pz, m);

	}

	private void rotateEndJoint(double angle, double px, double py, double pz, double m[][]) {

		if(end == null) {
			return;
		}

		double ix = end.getX();
		double iy = end.getY();
		double iz = end.getZ();

		double vx = ix*m[0][0]+iy*m[0][1]+iz*m[0][2]+1*m[0][3];
		double vy = ix*m[1][0]+iy*m[1][1]+iz*m[1][2]+1*m[1][3];
		double vz = ix*m[2][0]+iy*m[2][1]+iz*m[2][2]+1*m[2][3];

		end.setX(vx);
		end.setY(vy);
		end.setZ(vz);

	}

	public List<Vector3> getVectors() {
		return vectors;
	}

	public void addVectors(List<Vector3> vectors) {
		this.vectors.addAll(vectors);
	}

	@Override
	public void draw(GLAUX gl) {

		//gl.glPushMatrix();

		gl.glColor3i(color.getRed(), color.getGreen(), color.getBlue());

		gl.glBegin(GL.GL_LINES);
		gl.glVertex3d(orign.getX(), orign.getY(), orign.getZ());
		gl.glVertex3d(end.getX(), end.getY(), end.getZ());
		gl.glEnd();

		//gl.glPopMatrix();
		//drawVertexes(gl);

		end.draw(gl);

	}

	public void drawVertexes(GLAUX gl) {

		double vsize = 0.005;

		for(Vector3 vector: vectors) {

			gl.glPushMatrix();
			gl.glTranslated(vector.x, vector.y, vector.z);
			gl.auxSolidCube(vsize);
			gl.glPopMatrix();

		}

	}

	public Joint getEnd() {
		return end;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public double getAngleX() {
		return angleX;
	}

	public double getAngleY() {
		return angleY;
	}

	public void setAngleZ(double angleZ) {
		this.angleZ = angleZ;
	}

	public double getAngleZ() {
		return angleZ;
	}

	public void setSize(double size) {
		this.size = size;
		
		end = new Joint(size, 0, 0);
	}
	
	public double getSize() {
		return size;
	}
	
	public void append(Bone bone) {
		end.addBone(bone);
	}

}
