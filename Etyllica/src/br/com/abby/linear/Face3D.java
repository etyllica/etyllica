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
	
	protected Color color;
	
	protected Vector<Point3D> points;

	public Face3D() {
		
		color = Color.BLACK;
		
		points = new Vector<Point3D>();
	}

	public void novoPonto(int x, int y) {
		points.add(new Point3D(x,y,0));
	}
	public void novoPonto(int x, int y, int z) {
		points.add(new Point3D(x,y,z));
	}
	public void novoPonto(Point3D p) {
		points.add(p);
	}

	public double[] getPontosX() {

		int tamanho = points.size();

		double[] px = new double[tamanho];

		for(int i=0;i<tamanho;i++) {

			px[i] = points.get(i).getX();
		}

		return px;
	}

	public double[] getPontosY() {

		int tamanho = points.size();

		double[] py = new double[tamanho];

		for(int i=0;i<tamanho;i++) {

			py[i] = points.get(i).getY();
		}

		return py;
	}
	public int numPontos() {
		return points.size();
	}
	public Color getCor() {
		return color;
	}
	public void setCor(int r, int g, int b) {
		color = new Color(r,g,b);		
	}
	
	public void offset(int offsetX, int offsetY) {
		for(int i=0;i<points.size();i++) {
			points.get(i).setX(points.get(i).getX()+offsetX);
			points.get(i).setY(points.get(i).getY()+offsetY);
		}
	}

	public void girar(int angulo) {

		//http://ca.answers.yahoo.com/question/index?qid=20100403151916AAbJHxV
		
		double cos = Math.cos(angulo);
		double sen = Math.sin(angulo);
	
		for(int i=0;i<points.size();i++) {
			
			double rx = points.get(i).getX();
			double ry = points.get(i).getY();	
			
			points.get(i).setX((int) Math.round(rx*cos+ry*sen));
			points.get(i).setY((int) Math.round(-rx*sen+ry*cos));
			
		}
		
	}
	
	public Vector<Point3D> getPontos() {
		return points;
	}
	
	public String toString() {
		Point3D ponto;
		
		String retorno = "";
		
		for(int p = 0;p<points.size();p++) {
			
			ponto = points.get(p);
			retorno +="Ponto "+Integer.toString(p)+" = "+ponto;
			retorno += "\n";
		}
		
		return retorno;
	}

}
