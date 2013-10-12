package br.com.luvia.animation.skeletal;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.jgl.GLAUX;

import br.com.luvia.GLDrawable;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Joint implements GLDrawable{
	
	private Color color = Color.WHITE;

	protected double x;
	protected double y;
	protected double z;

	private List<Bone> bones = new ArrayList<Bone>();

	public Joint(double x, double y, double z){
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void addBone(Bone bone){
		bones.add(bone);
		bone.setOrign(this);
	}

	public void translate(double tx, double ty, double tz){

		this.x += tx;
		this.y += ty;
		this.z += tz;
		
		for(Bone bone: bones){
			bone.translate(tx, ty, tz);
		}
	}

	public void rotateXOver(double angle, double px, double py, double pz){

		for(Bone bone: bones){
			bone.rotateXOver(angle, px, py, pz);
		}
	}
	
	public void rotateXUnsigned(double angle, double px, double py, double pz){

		for(Bone bone: bones){
			bone.rotateXUnsigned(angle, px, py, pz);
		}
	}
	
	public void rotateYOver(double angle, double px, double py, double pz){

		for(Bone bone: bones){
			bone.rotateYOver(angle, px, py, pz);
		}
	}
	
	public void rotateYUnsigned(double angle, double px, double py, double pz){

		for(Bone bone: bones){
			bone.rotateYUnsigned(angle, px, py, pz);
		}
	}
	
	public void rotateZOver(double angle, double px, double py, double pz){

		for(Bone bone: bones){
			bone.rotateZOver(angle, px, py, pz);
		}
	}
	
	public void rotateZUnsigned(double angle, double px, double py, double pz){

		for(Bone bone: bones){
			bone.rotateZUnsigned(angle, px, py, pz);
		}
	}

	@Override
	public void draw(GLAUX gl) {
		gl.glColor3i(color.getRed(), color.getGreen(), color.getBlue());

		//gl.glPushMatrix();
		gl.glTranslated(x, y, z);

		gl.auxWireSphere(0.02);
		gl.glTranslated(-x, -y, -z);
		//gl.glPopMatrix();

		for(Bone bone: bones){
			bone.draw(gl);
		}

	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}


}
