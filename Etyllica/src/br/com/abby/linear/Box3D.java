package br.com.abby.linear;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Box3D extends Polygon3D{
	
	private double altura;
	private double largura;
	private double profundidade;
	
	public Box3D(){
		this(0,0,0);
		
	}
	public Box3D(double x, double y, double z){
		super(x,y,z);
		
		//N�o preciso desenhar v�rtices.
		//Posso chamar direto o auxWireBox
		altura = 2;
		largura = 0.4f;
		profundidade = 1;
		
		/*
		novoVertice(1.0, 1.0,-1.0);					// Top Right Of The Quad (Top)
		novoVertice(-1.0f, 1.0f,-1.0f);					// Top Left Of The Quad (Top)
		novoVertice(-1.0f, 1.0f, 1.0f);					// Bottom Left Of The Quad (Top)
		novoVertice( 1.0f, 1.0f, 1.0f);					// Bottom Right Of The Quad (Top)
		//glColor3f(1.0f,0.5f,0.0f);						// Set The Color To Orange
		novoVertice( x+largura,y, z);					// Top Right Of The Quad (Bottom)
		novoVertice(x,y,z);					// Top Left Of The Quad (Bottom)
		novoVertice(x,y-altura,z);					// Bottom Left Of The Quad (Bottom)
		novoVertice( x+largura,y-altura,z);					// Bottom Right Of The Quad (Bottom)
		//glColor3f(1.0f,0.0f,0.0f);						// Set The Color To Red
		novoVertice( x+largura,y+altura, z+profundidade);		// Top Right Of The Quad (Front)
		novoVertice(-1.0f, 1.0f, 1.0f);					// Top Left Of The Quad (Front)
		novoVertice(-1.0f,-1.0f, 1.0f);					// Bottom Left Of The Quad (Front)
		novoVertice( 1.0f,-1.0f, 1.0f);					// Bottom Right Of The Quad (Front)
		glColor3f(1.0f,1.0f,0.0f);						// Set The Color To Yellow
		novoVertice( 1.0f,-1.0f,-1.0f);					// Top Right Of The Quad (Back)
		novoVertice(-1.0f,-1.0f,-1.0f);					// Top Left Of The Quad (Back)
		novoVertice(-1.0f, 1.0f,-1.0f);					// Bottom Left Of The Quad (Back)
		novoVertice( 1.0f, 1.0f,-1.0f);					// Bottom Right Of The Quad (Back)
		glColor3f(0.0f,0.0f,1.0f);						// Set The Color To Blue
		novoVertice(-1.0f, 1.0f, 1.0f);					// Top Right Of The Quad (Left)
		novoVertice(-1.0f, 1.0f,-1.0f);					// Top Left Of The Quad (Left)
		novoVertice(-1.0f,-1.0f,-1.0f);					// Bottom Left Of The Quad (Left)
		novoVertice(-1.0f,-1.0f, 1.0f);					// Bottom Right Of The Quad (Left)
		glColor3f(1.0f,0.0f,1.0f);						// Set The Color To Violet
		novoVertice( 1.0f, 1.0f,-1.0f);					// Top Right Of The Quad (Right)
		novoVertice( 1.0f, 1.0f, 1.0f);					// Top Left Of The Quad (Right)
		novoVertice( 1.0f,-1.0f, 1.0f);					// Bottom Left Of The Quad (Right)
		novoVertice( 1.0f,-1.0f,-1.0f);					// Bottom Right Of The Quad (Right)
		//adiciona V�rtices no ver
		 *
		 */
	}

	public void setTamanho(double largura,double altura, double profundidade){
		this.largura = largura;
		this.altura = altura;
		this.profundidade = profundidade;
	}
	
	public double getAltura() {
		return altura;
	}

	public void setAltura(float altura) {
		this.altura = altura;
	}

	public double getLargura() {
		return largura;
	}

	public void setLargura(float largura) {
		this.largura = largura;
	}

	public double getProfundidade() {
		return profundidade;
	}

	public void setProfundidade(float profundidade) {
		this.profundidade = profundidade;
	}

}
