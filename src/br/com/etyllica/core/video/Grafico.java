package br.com.etyllica.core.video;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import br.com.etyllica.core.loader.FontLoader;
import br.com.etyllica.layer.ImageLayer;
import br.com.etyllica.layer.TextLayer;
import br.com.etyllica.linear.Ponto2D;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public class Grafico{

	private BufferedImage bimg;
	protected Graphics2D screen;

	private int width;
	private int height;

	public Grafico(){
		super();
	}
	
	public Grafico(int width, int height){
		super();
		
		this.width = width;
		this.height = height;
	}
	
	public void setBufferedImage(BufferedImage bimg){
		this.width = bimg.getWidth();
		this.height = bimg.getHeight();
		
		this.bimg = bimg;
		this.screen = (Graphics2D)bimg.getGraphics();
		this.screen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
	}

	public int getLargura() {
		return width;
	}

	public int getAltura() {
		return height;
	}
	
	public void escreveCamada(String texto, ImageLayer destino) {
		FontMetrics fm = screen.getFontMetrics();

		int msg_width = fm.stringWidth(texto);

		int ascent = fm.getMaxAscent();
		int descent= fm.getMaxDescent();

		int x = destino.getX()+destino.getW()/2 - msg_width/2;
		int y = destino.getY()+destino.getH()/2 - descent/2 + ascent/2;

		screen.drawString(texto, x, y);
	}
	
	public void escreveLabel(int x, int y, int w, int h, String texto) {
		FontMetrics fm = screen.getFontMetrics();

		int msg_width = fm.stringWidth(texto);

		int ascent = fm.getMaxAscent();
		int descent= fm.getMaxDescent();

		int dx = x+w/2 - msg_width/2;
		int dy = y+h/2 - descent/2 + ascent/2;

		screen.drawString(texto, dx, dy);
	}
	
	public void escreveLabelSombra(int x, int y, int w, int h, String texto, Color shadowColor) {
		FontMetrics fm = screen.getFontMetrics();

		int msg_width = fm.stringWidth(texto);

		int ascent = fm.getMaxAscent();
		int descent= fm.getMaxDescent();

		int dx = x+w/2 - msg_width/2;
		int dy = y+h/2 - descent/2 + ascent/2;

		escreveSombra(dx, dy, texto, shadowColor);
	}

	public void escreveX(int offsetX, int y, String frase, boolean borda){

		if((frase!=null)&&(!frase.isEmpty())){

			FontMetrics fm = screen.getFontMetrics();
			Font f = getFont();

			int x = (width/2)-(fm.stringWidth(frase)/2)+offsetX;
			int fy = y+fm.getHeight();

			if(!borda){
				screen.drawString(frase,x,fy);
			}			
			else{

				FontRenderContext frc = screen.getFontRenderContext();

				TextLayout tl = new TextLayout(frase, f, frc);

				Shape sha = tl.getOutline(AffineTransform.getTranslateInstance(x,y));        

				screen.setStroke(new BasicStroke(2.666f));
				screen.setColor(Color.BLACK);
				screen.draw(sha);
				
				screen.setColor(Color.WHITE);
				screen.fill(sha);
				
			}

		}
	}

	
	public void escreveSombra(int x, int y, String frase){
				
		escreveSombra(x, y, frase,Color.BLACK);
	}
	
	public void escreveSombra(int x, int y, String frase, Color shadowColor){
		
		if((frase!=null)&&(!frase.isEmpty())){
			
			Color lastColor = screen.getColor();
			
			screen.setColor(shadowColor);
			screen.drawString(frase,x+1,y+1);
			screen.setColor(lastColor);
			screen.drawString(frase,x,y);
		}
	}
	

	public void escreveSombraX(int y, String frase){
		if((frase!=null)&&(!frase.isEmpty())){

			FontMetrics fm = screen.getFontMetrics();

			int x = (width/2)-(fm.stringWidth(frase)/2);
			int fy = y+fm.getHeight();

			escreveSombra(x, fy, frase);
				
		}
	}
	
	public void escreve(int x, int y, String frase){
		if((frase!=null)&&(!frase.isEmpty())){
			screen.drawString(frase,x,y);
		}
	}

	public void escreveX(int y, String frase) {

		escreveX(0,y,frase, false);
	}
	
	public void escreveX(int offsetX, int y, String frase) {

		escreveX(offsetX, y,frase, false);
	}
	
	public void escreveX(int y, String frase, boolean borda) {
		escreveX(0,y,frase, borda);
	}
	
	
	//Fun��es de Escrita
	public void escreveXCustom(int offsetX, int y, String frase) {


		//Font f = carregaFonte("exocet.ttf");
		//getGraphics().setFont(f);

		screen.drawString(frase, 200, y);



		/*
		FontRenderContext frc = screen.getFontRenderContext();
		Font f = carregaFonte("font/exocet.ttf");

		FontMetrics fm = screen.getFontMetrics (f);

		int msg_width = fm.stringWidth(frase);

		int x = largura/2 - msg_width/2;
		x += offsetX;

		TextLayout tl = new TextLayout(frase, f, frc);

		Shape sha = tl.getOutline(AffineTransform.getTranslateInstance(x,y));        

		screen.setStroke(new BasicStroke(2.666f));
		screen.setColor(Color.black);
		screen.draw(sha);
		Color c = new Color(190,10,10); 
		screen.setColor(c);
		screen.fill(sha);
		 */

	}

	public void drawImage( Image img, int dx1, int dy1,int dx2, int dy2, int sx1 , int sy1, int sx2, int sy2, ImageObserver observer ){
		screen.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer);
	}
	
	public void desenha(TextLayer texto){

		if(texto.isVisible()){

			Font f = FontLoader.getInstancia().carregaFonte(texto.getNomeFonte());
			f = f.deriveFont(texto.getEstilo(), texto.getTamanho());

			screen.setFont(f);

			screen.setColor(texto.getCorDifusa());

			FontMetrics fm = screen.getFontMetrics(f);

			int msg_width = fm.stringWidth(texto.getTexto());

			int x = texto.getX() - msg_width/2;
			int y = texto.getY() + texto.getTamanho()/3;

			if(texto.getBorda()==false){
				screen.drawString(texto.getTexto(), x, y);
			}

		}

		/*
		if(t.getAparecendo()){
			//escreve
			//screen.drawImage( t.getImagem(), t.getX(), t.getY(), null);
			FontRenderContext frc = new FontRenderContext(null, t.isAntialiased(), false);
			TextLayout layout = new TextLayout(t.getTexto(), t.getFonte(), frc);

			Rectangle2D bounds = layout.getBounds();

			Graphics2D g = getGraphics();

			g.setFont(t.getFonte());
			if(t.getBorda()==true){
				Shape sha = layout.getOutline(AffineTransform.getTranslateInstance(0,t.getTexto().length()-14));

				//g.setStroke(new BasicStroke(3f));
				g.setStroke(new BasicStroke(4f));

				g.setColor(t.getCorBorda());
				g.draw(sha);

				g.setColor(t.getCorDifusa());
				g.fill(sha);
			}
			else{
				g.setColor(t.getCorDifusa());
				g.drawString(t.getTexto(),t.getX(),t.getY());
			}

			g.dispose();
		}
		 */
	}

	public Graphics2D getGraphics(){
		return screen;
	}

	public void drawRect(ImageLayer camada){
		screen.drawRect(camada.getX(), camada.getY(), camada.getW(), camada.getH());
	}
	public void fillOval(ImageLayer camada){
		screen.fillOval(camada.getX(), camada.getY(), camada.getW(), camada.getH());
	}
	public void fillArc(ImageLayer camada, int anguloInicial, int anguloArco){
		screen.fillArc(camada.getX(), camada.getY(), camada.getW(), camada.getH(), anguloInicial, anguloArco);
	}
	
	/** Funções Delegadas */
	public void setBasicStroke(float width){
		screen.setStroke(new BasicStroke(width));	
	}
	
	public AffineTransform getTransform(){
		return screen.getTransform();
	}
	public void setTransform(AffineTransform tx){
		//Evitar Bug do Java
		if(screen!=null)
			screen.setTransform(tx);
	}
	/*public void resetTransform(){
		//Evitar Bug do Java
		if(screen!=null)
			screen.setTransform(resetTransform);
	}*/
	
	public void setFont(Font fonte){
		screen.setFont(fonte);
	}
	public Font getFont(){
		return screen.getFont();		
	}
	
	public FontRenderContext getFontRenderContext(){
		return screen.getFontRenderContext();
	}
	
	public void setColor(int color){
		screen.setColor(new Color(color));
	}	
	public void setColor(Color c){
		screen.setColor(c);
	}
	
	public void setAlpha(int porcento){

		float a = (float)porcento/100;

		screen.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, a));
	}

	public void drawImage(Image img, int x, int y){
		screen.drawImage(img, x, y, null);
	}
	
	public void drawArc(int x, int y, int w, int h, int anguloInicial, int anguloArco){
		screen.drawArc(x, y, w, h, anguloInicial, anguloArco);
	}
	
	public void drawLine(int x1,int y1,int x2,int y2){
		screen.drawLine(x1, y1, x2, y2);
	}
	
	public void drawLine(Ponto2D p, Ponto2D q){
		screen.drawLine((int)p.getX(), (int)p.getY(), (int)q.getX(), (int)q.getY());
	}
		
	public void drawPolygon(Polygon p){
		screen.drawPolygon(p);
	}
	
	public void fillPolygon(Polygon p){
		screen.fillPolygon(p);
	}
	
	public void fillRect(ImageLayer camada) {
		screen.fillRect(camada.getX(),camada.getY(),camada.getW(),camada.getH());
	}
	
	public void fillRect(int x, int y, int w, int h) {
		screen.fillRect(x,y,w,h);
	}
	
	public void fillArc(int x, int y, int w, int h, int anguloInicial, int anguloArco){
		screen.fillArc(x, y, w, h, anguloInicial, anguloArco);
	}

	public void drawRect(int x, int y, int w, int h) {
		screen.drawRect(x,y,w,h);
	}

	public void drawRect(double x, double y, double w, double h) {
		screen.drawRect((int)x,(int)y,(int)w,(int)h);
	}

	public void drawOval(int x, int y, int w, int h ){
		screen.drawOval(x,y,w,h);
	}
	
	public void drawCircle(int cx, int cy, int raio ){
		screen.drawOval(cx-raio, cy-raio, raio*2, raio*2);
	}
	
	public void drawOval(Ponto2D p, int raio ){
		screen.drawOval((int)p.getX()-raio, (int)p.getY()-raio, raio*2, raio*2);
	}

	public void fillCircle(int cx, int cy, int raio ){
		screen.fillOval(cx-raio, cy-raio, raio*2, raio*2);
	}
	
	public void fillOval(int x, int y, int w, int h ){
		screen.fillOval(x, y, w, h);
	}

	/*public void setGraphics(GLGraphics2D graphics){		
		this.screen = graphics;
		this.screen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}*/
	
	public BufferedImage getBimg() {
		return bimg;
	}

	public void setBimg(BufferedImage bimg) {
		this.bimg = bimg;
	}
		
}
