package br.com.abby.material;

import org.lwjgl.util.vector.Vector3f;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class DiffuseMaterial {

	protected String name = "";
	
	//Ambient Color
	protected Vector3f ka;
	protected String mapKa = "";
	
	//Diffuse Color
	protected Vector3f kd;
	protected String mapKd = "";
	
	//Specular Color
	protected Vector3f ks;
	protected String mapKs = "";
	
	//Specular Highlight
	protected float ns = 0f;
	protected String mapNs = "";
	
	protected float d;
	protected String mapD = "";
	
	protected int illum = 0;
		
	public DiffuseMaterial() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Vector3f getKa() {
		return ka;
	}

	public void setKa(Vector3f ka) {
		this.ka = ka;
	}

	public String getMapKa() {
		return mapKa;
	}

	public void setMapKa(String mapKa) {
		this.mapKa = mapKa;
	}

	public Vector3f getKd() {
		return kd;
	}

	public void setKd(Vector3f kd) {
		this.kd = kd;
	}

	public String getMapKd() {
		return mapKd;
	}

	public void setMapKd(String mapKd) {
		this.mapKd = mapKd;
	}

	public Vector3f getKs() {
		return ks;
	}

	public void setKs(Vector3f ks) {
		this.ks = ks;
	}

	public String getMapKs() {
		return mapKs;
	}

	public void setMapKs(String mapKs) {
		this.mapKs = mapKs;
	}

	public float getNs() {
		return ns;
	}

	public void setNs(float ns) {
		this.ns = ns;
	}

	public String getMapNs() {
		return mapNs;
	}

	public void setMapNs(String mapNs) {
		this.mapNs = mapNs;
	}

	public float getD() {
		return d;
	}

	public void setD(float d) {
		this.d = d;
	}

	public String getMapD() {
		return mapD;
	}

	public void setMapD(String mapD) {
		this.mapD = mapD;
	}

	public int getIllum() {
		return illum;
	}

	public void setIllum(int illum) {
		this.illum = illum;
	}
		
}
