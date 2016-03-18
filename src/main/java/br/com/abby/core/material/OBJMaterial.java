package br.com.abby.core.material;

import com.badlogic.gdx.math.Vector3;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class OBJMaterial {

	protected String name = "";
	
	//Ambient Color
	protected Vector3 ka;
	protected String mapKa = "";
	
	//Diffuse Color
	protected Vector3 kd;
	protected String mapKd = "";
	
	//Specular Color
	protected Vector3 ks;
	protected String mapKs = "";
	
	//Specular Highlight
	protected float ns = 0f;
	protected String mapNs = "";
	
	protected float d;
	protected String mapD = "";
	
	protected int illum = 0;
		
	public OBJMaterial() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Vector3 getKa() {
		return ka;
	}

	public void setKa(Vector3 ka) {
		this.ka = ka;
	}

	public String getMapKa() {
		return mapKa;
	}

	public void setMapKa(String mapKa) {
		this.mapKa = mapKa;
	}

	public Vector3 getKd() {
		return kd;
	}

	public void setKd(Vector3 kd) {
		this.kd = kd;
	}

	public String getMapKd() {
		return mapKd;
	}

	public void setMapKd(String mapKd) {
		this.mapKd = mapKd;
	}

	public Vector3 getKs() {
		return ks;
	}

	public void setKs(Vector3 ks) {
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
