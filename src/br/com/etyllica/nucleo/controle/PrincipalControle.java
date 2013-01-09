package br.com.etyllica.nucleo.controle;

import java.util.ArrayList;

public class PrincipalControle {

	private ArrayList<Mouse> mouses;
	//protected ArrayList<Teclado> teclados;
	private ArrayList<Teclado> teclados = new ArrayList<Teclado>();
	//protected ArrayList<Pad> teclados;

	public PrincipalControle(){

		mouses = new ArrayList<Mouse>();
		mouses.add(new Mouse(0,0));

		teclados.add(new Teclado());
	}

	public Mouse getMouse() {
		return mouses.get(0);
	}
	
	public Teclado getTeclado() {
		return teclados.get(0);
	}
	
	public ArrayList<Mouse> getMouses() {
		return mouses;
	}

	public ArrayList<Teclado> getTeclados() {
		return teclados;
	}
		

}
