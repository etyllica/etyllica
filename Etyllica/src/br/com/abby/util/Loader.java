package br.com.abby.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import br.com.abby.linear.Face3D;
import br.com.abby.linear.Point3D;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Loader {

	public Loader(){
		super();
	}

	public static Vector<Face3D> carregaOBJ(String caminho){
		
		caminho = caminho.substring(6);
		
		Vector<Point3D> vertices = new Vector<Point3D>();
		Vector<Face3D> faces = new Vector<Face3D>();

		try {
			FileReader fr = new FileReader(caminho);
			BufferedReader br = new BufferedReader(fr);

			String eachLine = br.readLine();

			while (eachLine != null) {
				//System.out.println(eachLine);
				eachLine = eachLine.replace("  ", " ");
				
				if(eachLine.startsWith("#")){}
				else if(eachLine.startsWith("v")){
					
					double x = Double.parseDouble(eachLine.split(" ")[1]);
					double y = Double.parseDouble(eachLine.split(" ")[2]);
					double z = Double.parseDouble(eachLine.split(" ")[3]);					
					
					vertices.add(new Point3D(x,y,z));
				}
				else if(eachLine.startsWith("f")){
					
					String[] poliLine = eachLine.split(" ");
					
					Face3D pol = new Face3D();
					for(int i = 1;i<poliLine.length;i++){						
						
						pol.novoPonto(vertices.get(Integer.parseInt(poliLine[i])-1));
						
					}
					
					faces.add(pol);
				}
				else{
					//System.out.println(eachLine);
				}
				
				eachLine = br.readLine();
			}
			
			br.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return faces;

	}
	
	public static float[][][] carregaCtrlPoints(String caminho){
		return convertOBJ(carregaOBJ(caminho));
	}
	
	public static float[][][] convertOBJ(Vector<Face3D> faces){
		
		float[][][] cpoints = new float[faces.size()][][];
		
		int j;
		
		int npontos = 4;
		
		for (int i=0;i<faces.size();i++){
			
			//npontos = faces.get(i).getPontos().size();
			cpoints[i] = new float[npontos][3];
			
				for (j=0;j<npontos;j++){
					
				   cpoints[i][j][0] = (float) faces.get(i).getPontos().get(j).getX();
				   cpoints[i][j][1] = (float) faces.get(i).getPontos().get(j).getY();
				   cpoints[i][j][2] = (float) faces.get(i).getPontos().get(j).getZ();
				   //System.out.println("["+i+"]["+j+"] - existe");
				   
			   }
				
		}
		
		
		
		
		return cpoints;
	}



}
