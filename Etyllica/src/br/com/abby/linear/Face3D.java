package br.com.abby.linear;

import java.awt.Color;
import java.util.Vector;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Face3D {

	protected Vector<Point3D> pontos;
	protected Color cor;

	public Face3D(){
		cor = new Color(0,0,0);
		pontos = new Vector<Point3D>();
	}

	public void novoPonto(int x, int y){
		pontos.add(new Point3D(x,y,0));
	}
	public void novoPonto(int x, int y, int z){
		pontos.add(new Point3D(x,y,z));
	}
	public void novoPonto(Point3D p){
		pontos.add(p);
	}

	public double[] getPontosX(){

		int tamanho = pontos.size();

		double[] px = new double[tamanho];

		for(int i=0;i<tamanho;i++){

			px[i] = pontos.get(i).getX();
		}

		return px;
	}

	public double[] getPontosY(){

		int tamanho = pontos.size();

		double[] py = new double[tamanho];

		for(int i=0;i<tamanho;i++){

			py[i] = pontos.get(i).getY();
		}

		return py;
	}
	public int numPontos(){
		return pontos.size();
	}
	public Color getCor(){
		return cor;
	}
	public void setCor(int r, int g, int b){
		cor = new Color(r,g,b);		
	}
	
	public void offset(int offsetX, int offsetY){
		for(int i=0;i<pontos.size();i++){
			pontos.get(i).setX(pontos.get(i).getX()+offsetX);
			pontos.get(i).setY(pontos.get(i).getY()+offsetY);
		}
	}

	public void girar(int angulo){

		//http://ca.answers.yahoo.com/question/index?qid=20100403151916AAbJHxV
		
		double cos = Math.cos(angulo);
		double sen = Math.sin(angulo);
	
		for(int i=0;i<pontos.size();i++){
			
			double rx = pontos.get(i).getX();
			double ry = pontos.get(i).getY();	
			
			pontos.get(i).setX((int) Math.round(rx*cos+ry*sen));
			pontos.get(i).setY((int) Math.round(-rx*sen+ry*cos));
			
		}
		
	}
	
	public Vector<Point3D> getPontos(){
		return pontos;
	}
	
	public String toString(){
		Point3D ponto;
		
		String retorno = "";
		
		for(int p = 0;p<pontos.size();p++){
			
			ponto = pontos.get(p);
			retorno +="Ponto "+Integer.toString(p)+" = "+ponto;
			retorno += "\n";
		}
		
		return retorno;
	}

}
