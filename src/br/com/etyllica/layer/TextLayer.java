package br.com.etyllica.layer;

import java.awt.Color;
import java.awt.Font;

import br.com.etyllica.core.video.Grafico;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class TextLayer extends ImageLayer{
	
	private String text;
	
	private int style = Font.PLAIN;
	private float size = 26;
	private String fontName = "exocet.ttf";
	
	private Color corDifusa;
	private Color corBorda;

	private boolean borda = false; 

	private boolean isAntiAliased = true;
	
	//private boolean usesFractionalMetrics = false;
	
	public TextLayer(String text){
		super();
		
		//corDifusa = new Color(190,10,10);
		corDifusa = new Color(0xff,0xff,0xff);
		corBorda = new Color(0,0,0);
		

		this.text = text;
	}

	public TextLayer(int x, int y, String text){

		corDifusa = new Color(0xff,0xff,0xff);
		corBorda = new Color(0,0,0);

		this.x = x;
		this.y = y;

		this.text = text;

	}
		
	public int getStyle() {
		return style;
	}
	public void setStyle(int style) {
		this.style = style;
	}
	public float getSize() {
		return size;
	}
	public void setSize(float size) {
		this.size = size;
	}
	public String getFontName() {
		return fontName;
	}
	public void setFontName(String fontName) {
		this.fontName = fontName;
	}
	
	public void setOffsetTexto(String texto){
		setTexto(this.text+texto);
	}
	
	public void apagaUltima(){
		setTexto(this.text.substring(0,text.length()-1));
	}

	public void insereFinal(String texto){
		this.text+=texto;
	}
	
	public void setTexto(Object texto){
		setTexto(texto.toString());
	}
	
	public void setTexto(String texto){
		if(!texto.isEmpty())
			this.text = texto;
		else
			this.text = " ";
		//escreve();
	}

	@Override
	public void draw(Grafico g){
		if(visible){
			g.getFont().deriveFont(style, size);
			g.drawString(text, x, y);
		}		
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
		return text;
	}
	
	public Color getCorBorda(){
		return corBorda;
	}
	
	public Color getCorDifusa(){
		return corDifusa;
	}
	
	
}
