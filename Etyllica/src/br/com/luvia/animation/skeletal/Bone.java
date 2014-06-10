package br.com.luvia.animation.skeletal;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.jgl.GL;
import org.jgl.GLAUX;
import org.lwjgl.util.vector.Vector3f;

import br.com.luvia.GLDrawable;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Bone implements GLDrawable{

	private String name;
	
	protected Color color = Color.WHITE;

	private double size;
	private double angleX = 0;
	private double angleY = 0;
	private double angleZ = 0;

	private List<Vector3f> vectors = new ArrayList<Vector3f>();

	private Joint orign = null;
	protected Joint end = null;

	public Bone(double size){
		this.size = size;

		end = new Joint(0,0,0);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOrign(Joint orign){
		this.orign = orign;
		this.end.setX(orign.getX()+size);
		this.end.setY(orign.getY());
		this.end.setZ(orign.getZ());
	}

	private double cos(double angle){
		return Math.cos(Math.toRadians(angle));
	}

	private double sin(double angle){
		return Math.sin(Math.toRadians(angle));
	}

	public void translate(double tx, double ty, double tz){

		if(vectors!=null){

			for(Vector3f vector: vectors){

				//Initial values
				double ix = vector.getX();
				double iy = vector.getY();
				double iz = vector.getZ();

				double vx = ix+tx;
				double vy = iy+ty;
				double vz = iz+tz;

				vector.setX((float)vx);
				vector.setY((float)vy);
				vector.setZ((float)vz);

			}

		}

		if(end!=null){

			end.translate(tx,ty,tz);

		}
	}

	public void rotateXOver(double angle){

		if(orign!=null){

			double px = orign.getX();
			double py = orign.getY();
			double pz = orign.getZ();

			rotateXOver(angle, px, py, pz);
		}
	}
	
	public void rotateXUnsigned(double angle){

		if(orign!=null){

			double px = orign.getX();
			double py = orign.getY();
			double pz = orign.getZ();

			rotateXUnsigned(angle, px, py, pz);
		}
	}

	public void rotateYOver(double angle){

		if(orign!=null){

			double px = orign.getX();
			double py = orign.getY();
			double pz = orign.getZ();

			rotateYOver(angle, px, py, pz);
		}
	}
	
	public void rotateYUnsigned(double angle){

		if(orign!=null){

			double px = orign.getX();
			double py = orign.getY();
			double pz = orign.getZ();

			rotateYUnsigned(angle, px, py, pz);
		}
	}

	public void rotateZOver(double angle){

		if(orign!=null){

			double px = orign.getX();
			double py = orign.getY();
			double pz = orign.getZ();

			rotateZOver(angle, px, py, pz);
		}
	}
	
	public void rotateZUnsigned(double angle){

		if(orign!=null){

			double px = orign.getX();
			double py = orign.getY();
			double pz = orign.getZ();

			rotateZUnsigned(angle, px, py, pz);
		}
	}

	private double[][] rotationMatrixX(double angle, double px, double py, double pz){

		double m[][] = new double[4][4];

		m[0][0] = 1;
		m[0][1] = 0;
		m[0][2] = 0;
		m[0][3] = px - m[0][0]*px - m[0][1]*py - m[0][2]*pz;

		m[1][0] = 0;
		m[1][1] = cos(angle);
		m[1][2] = -sin(angle);
		m[1][3] = py - m[1][0]*px - m[1][1]*py - m[1][2]*pz;

		m[2][0] = 0;
		m[2][1] = sin(angle);
		m[2][2] = cos(angle);
		m[2][3] = pz - m[2][0]*px - m[2][1]*py - m[2][2]*pz;

		m[3][0] = 0;
		m[3][1] = 0;
		m[3][2] = 0;
		m[3][3] = 1;

		return m;
	}

	private double[][] rotationMatrixY(double angle, double px, double py, double pz){

		double m[][] = new double[4][4];

		m[0][0] = cos(angle);
		m[0][1] = 0;
		m[0][2] = sin(angle);
		m[0][3] = px - m[0][0]*px - m[0][1]*py - m[0][2]*pz;

		m[1][0] = 0;
		m[1][1] = 1;
		m[1][2] = 0;
		m[1][3] = py - m[1][0]*px - m[1][1]*py - m[1][2]*pz;

		m[2][0] = -sin(angle);
		m[2][1] = 0;
		m[2][2] = cos(angle);
		m[2][3] = pz - m[2][0]*px - m[2][1]*py - m[2][2]*pz;

		m[3][0] = 0;
		m[3][1] = 0;
		m[3][2] = 0;
		m[3][3] = 1;

		return m;
	}

	private double[][] rotationMatrixZ(double angle, double px, double py, double pz){

		double m[][] = new double[4][4];

		m[0][0] = cos(angle);
		m[0][1] = -sin(angle);
		m[0][2] = 0;
		m[0][3] = px - m[0][0]*px - m[0][1]*py - m[0][2]*pz;

		m[1][0] = sin(angle);
		m[1][1] = cos(angle);
		m[1][2] = 0;
		m[1][3] = py - m[1][0]*px - m[1][1]*py - m[1][2]*pz;

		m[2][0] = 0;
		m[2][1] = 0;
		m[2][2] = 1;
		m[2][3] = pz - m[2][0]*px - m[2][1]*py - m[2][2]*pz;

		m[3][0] = 0;
		m[3][1] = 0;
		m[3][2] = 0;
		m[3][3] = 1;

		return m;
	}

	public void rotateXOver(double angle, double px, double py, double pz){

		this.angleX += angle;
		
		rotateOver(angle, px, py, pz, rotationMatrixX(angle, px, py, pz));

		end.rotateXOver(angle,px,py,pz);

	}
	
	public void rotateXUnsigned(double angle, double px, double py, double pz){

		this.angleX += angle;

		rotateUnsigned(angle, px, py, pz, rotationMatrixX(angle, px, py, pz));

		end.rotateXUnsigned(angle,px,py,pz);

	}

	public void rotateYOver(double angle, double px, double py, double pz){

		this.angleY += angle;

		rotateOver(angle, px, py, pz, rotationMatrixY(angle, px, py, pz));

		end.rotateYOver(angle,px,py,pz);

	}
	
	public void rotateYUnsigned(double angle, double px, double py, double pz){

		this.angleY += angle;

		rotateUnsigned(angle, px, py, pz, rotationMatrixY(angle, px, py, pz));

		end.rotateYUnsigned(angle,px,py,pz);

	}

	public void rotateZOver(double angle, double px, double py, double pz){

		this.angleZ += angle;

		rotateOver(angle, px, py, pz, rotationMatrixZ(angle, px, py, pz));

		end.rotateZOver(angle,px,py,pz);

	}
	
	public void rotateZUnsigned(double angle, double px, double py, double pz){

		this.angleZ += angle;

		rotateUnsigned(angle, px, py, pz, rotationMatrixZ(angle, px, py, pz));

		end.rotateZUnsigned(angle,px,py,pz);

	}

	public void rotateOver(double angle, double px, double py, double pz, double m[][]){

		if(vectors!=null){

			for(Vector3f vector: vectors){

				//Initial values
				double ix = vector.getX();
				double iy = vector.getY();
				double iz = vector.getZ();

				double vx = ix*m[0][0]+iy*m[0][1]+iz*m[0][2]+m[0][3];
				double vy = ix*m[1][0]+iy*m[1][1]+iz*m[1][2]+m[1][3];
				double vz = ix*m[2][0]+iy*m[2][1]+iz*m[2][2]+m[2][3];

				vector.setX((float)(vx));
				vector.setY((float)(vy));
				vector.setZ((float)(vz));

			}

		}

		//if(end!=null){

		double ix = end.getX();
		double iy = end.getY();
		double iz = end.getZ();

		double vx = ix*m[0][0]+iy*m[0][1]+iz*m[0][2]+1*m[0][3];
		double vy = ix*m[1][0]+iy*m[1][1]+iz*m[1][2]+1*m[1][3];
		double vz = ix*m[2][0]+iy*m[2][1]+iz*m[2][2]+1*m[2][3];

		end.setX(vx);
		end.setY(vy);
		end.setZ(vz);

		//}

	}

	public void rotateUnsigned(double angle, double px, double py, double pz, double m[][]){

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

	public List<Vector3f> getVectors(){
		return vectors;
	}

	public void addVectors(List<Vector3f> vectors){
		this.vectors.addAll(vectors);
	}

	@Override
	public void draw(GLAUX gl){

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
	
	public void drawVertexes(GLAUX gl){
		
		double vsize = 0.005;
		
		for(Vector3f vector: vectors){

			gl.glPushMatrix();
			gl.glTranslated(vector.getX(), vector.getY(), vector.getZ());
			gl.auxSolidCube(vsize);
			gl.glPopMatrix();
			
		}
		
	}

	public Joint getEnd(){
		return end;
	}

	public Color getColor(){
		return color;
	}

	public void setColor(Color color){
		this.color = color;
	}

	public double getAngleX(){
		return angleX;
	}

	public double getAngleY(){
		return angleY;
	}

	public void setAngleZ(double angleZ){
		this.angleZ = angleZ;
	}

	public double getAngleZ(){
		return angleZ;
	}

	public double getSize() {
		return size;
	}
	
}
