package br.com.luvia.material;

import org.lwjgl.util.vector.Vector3f;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Material {

	private String name = "";
	
	//Ambient Color
	private Vector3f Ka;
	private String map_Ka = "";
	
	//Diffuse Color
	private Vector3f Kd;
	private String map_Kd = "";
	
	//Specular Color
	private Vector3f Ks;
	private String map_Ks = "";
	
	//Specular Highlight
	private float Ns = 0f;
	private String map_Ns = "";
	
	private float d;
	private String map_d = "";
	
	
	private int illum = 0;
	
	
	public Material() {
		super();
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Vector3f getKa() {
		return Ka;
	}


	public void setKa(Vector3f ka) {
		Ka = ka;
	}


	public String getMap_Ka() {
		return map_Ka;
	}


	public void setMap_Ka(String map_Ka) {
		this.map_Ka = map_Ka;
	}


	public Vector3f getKd() {
		return Kd;
	}


	public void setKd(Vector3f kd) {
		Kd = kd;
	}


	public String getMap_Kd() {
		return map_Kd;
	}


	public void setMap_Kd(String map_Kd) {
		this.map_Kd = map_Kd;
	}


	public Vector3f getKs() {
		return Ks;
	}


	public void setKs(Vector3f ks) {
		Ks = ks;
	}


	public String getMap_Ks() {
		return map_Ks;
	}


	public void setMap_Ks(String map_Ks) {
		this.map_Ks = map_Ks;
	}


	public float getNs() {
		return Ns;
	}


	public void setNs(float ns) {
		Ns = ns;
	}


	public String getMap_Ns() {
		return map_Ns;
	}


	public void setMap_Ns(String map_Ns) {
		this.map_Ns = map_Ns;
	}


	public float getD() {
		return d;
	}


	public void setD(float d) {
		this.d = d;
	}


	public String getMap_d() {
		return map_d;
	}


	public void setMap_d(String map_d) {
		this.map_d = map_d;
	}


	public int getIllum() {
		return illum;
	}


	public void setIllum(int illum) {
		this.illum = illum;
	}
		
}
