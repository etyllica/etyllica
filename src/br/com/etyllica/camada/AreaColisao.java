package br.com.etyllica.camada;

import java.util.ArrayList;
import java.util.List;

import br.com.etyllica.linear.Retangulo;

public class AreaColisao {

	List<Retangulo> area;
	
	public AreaColisao (){
		area = new ArrayList<Retangulo>();
	}

	public List<Retangulo> getArea() {
		return area;
	}

	public void setArea(List<Retangulo> area) {
		this.area = area;
	}
		
}
