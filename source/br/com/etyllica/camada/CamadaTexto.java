package br.com.etyllica.camada;

import java.awt.Color;
import java.awt.Font;

public class CamadaTexto extends Camada{
	
	private String texto;
	
	private int estilo = Font.PLAIN;
	private int tamanho = 22;
	private String nomeFonte = "exocet.ttf";
	
	private Color corDifusa;
	private Color corBorda;

	private boolean borda = false; 

	private boolean isAntiAliased = true;
	//private boolean usesFractionalMetrics = false;
	
	public int getEstilo(){
		return estilo;
	}
	
	public void setEstilo(int estilo){
		this.estilo = estilo;
	}
	
	public int getTamanho(){
		return tamanho;
	}
	
	public void setTamanho(int tamanho){
		this.tamanho = tamanho;
	}
	
	public String getNomeFonte(){
		return nomeFonte;
	}
	public void setNomeFonte(String nomeFonte){
		this.nomeFonte = nomeFonte;
	}

	public void setTamanhoFonte(int tamanho){
		this.tamanho = tamanho;
	}

	public void setOffsetTexto(String texto){
		setTexto(this.texto+texto);
	}
	public void apagaUltima(){
		setTexto(this.texto.substring(0,texto.length()-1));
	}

	public void insereFinal(String texto){
		this.texto+=texto;
	}
	
	public void setTexto(Object texto){
		setTexto(texto.toString());
	}
	
	public void setTexto(String texto){
		if(!texto.isEmpty())
			this.texto = texto;
		else
			this.texto = " ";
		//escreve();
	}

	public CamadaTexto(String texto){
		//corDifusa = new Color(190,10,10);
		corDifusa = new Color(0xff,0xff,0xff);
		corBorda = new Color(0,0,0);

		setTexto(texto);
	}

	public CamadaTexto(int x, int y, String texto){

		corDifusa = new Color(0xff,0xff,0xff);
		corBorda = new Color(0,0,0);

		this.x = x;
		this.y = y;

		this.texto = texto;

	}

	/*
	protected void escreve(){

		FontRenderContext frc = new FontRenderContext(null, isAntiAliased, usesFractionalMetrics);
		TextLayout layout = new TextLayout(texto, font, frc);

		Rectangle2D bounds = layout.getBounds();

		//int w = (int) Math.ceil(bounds.getWidth()+3);
		int width = (int) Math.ceil(bounds.getWidth()+tamanhoFonte/2);
		int height = (int) Math.ceil(bounds.getHeight()+tamanhoFonte);		

		BufferedImage imagemBuffer = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g = imagemBuffer.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.setFont(font);
		if(borda){
			Shape sha = layout.getOutline(AffineTransform.getTranslateInstance(0,height-14));

			//g.setStroke(new BasicStroke(3f));
			g.setStroke(new BasicStroke(4f));

			g.setColor(corBorda);
			g.draw(sha);

			g.setColor(corDifusa);
			g.fill(sha);
		}
		else{
			g.setColor(corDifusa);
			g.drawString(texto,0,0);
		}

		g.dispose();

		//Para poder centralizar


		int w = imagemBuffer.getWidth();
		int h = imagemBuffer.getHeight();
		imgDefinida = imagemBuffer.getScaledInstance(w, h, BufferedImage.TYPE_INT_ARGB);
		xLimite = imgDefinida.getWidth(null);
		yLimite = imgDefinida.getHeight(null);

	}
	*/
	
	public void setCorDifusa(int r, int g, int b){
		corDifusa = new Color(r%256,g%256,b%256);
	}
	public void setCorDifusa(Color c){
		corDifusa = c;
	}
	
	public boolean isAntialiased(){
		return isAntiAliased;
	}
	
	public boolean getBorda(){
		return borda;
	}
	
	public String getTexto(){
		return texto;
	}
	
	public Color getCorBorda(){
		return corBorda;
	}
	
	public Color getCorDifusa(){
		return corDifusa;
	}
	
	
}
