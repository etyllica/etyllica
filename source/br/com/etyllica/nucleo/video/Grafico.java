package br.com.etyllica.nucleo.video;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import br.com.etyllica.camada.Camada;
import br.com.etyllica.camada.CamadaAnimacao;
import br.com.etyllica.camada.CamadaBuffer;
import br.com.etyllica.camada.CamadaEstatica;
import br.com.etyllica.camada.CamadaPivot;
import br.com.etyllica.camada.CamadaTexto;
import br.com.etyllica.camada.EfeitoSonoro;
import br.com.etyllica.gui.Janela;

public class Grafico{

	private Map<String, BufferedImage>camadanova = new HashMap<String, BufferedImage>();

	private Map<String, Font>fonte = new HashMap<String, Font>();

	private Graphics2D screen;

	private int largura;
	private int altura;

	//private String pasta = "http://www.etyllica.com.br/mystic/imagens/";
	private String pasta = "imagens/";

	private VolatileImage vImagem;

	private URL url;

	public Grafico(int largura, int altura){

		this.largura = largura;
		this.altura = altura;

	}

	public int getLargura() {
		return largura;
	}

	public int getAltura() {
		return altura;
	}

	public void setPasta(String pasta){
		this.pasta = pasta;
	}

	public void setGraphics(VolatileImage vImagem){
		this.vImagem = vImagem;
		setGraphics((Graphics2D)vImagem.getGraphics());
	}

	public void setGraphics(Graphics screen){
		setGraphics((Graphics2D)screen);
	}


	public void setUrl(String s){
		try {
			url = new URL(s);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	} 

	public URL getUrl(){
		return url;
	}


	public void setGraphics(Graphics2D screen){

		this.screen = screen;
		this.screen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	}

	public BufferedImage getSnapShot(){
		return vImagem.getSnapshot();
	}


	public Image carregaImagemCaminho(String diretorio){

		if((pasta).equals(diretorio)){
			System.err.println("Caminho vazio.");
			return null;
		}


		if(camadanova.get(diretorio)!=null){
			return camadanova.get(diretorio);
		}

		return carregaCaminho(diretorio);

	}

	public Image carregaImagem(String caminho){

		String diretorio = pasta+caminho;

		return carregaImagemCaminho(diretorio);		

	}

	private Image carregaCaminho(String diretorio){
		BufferedImage img = null;

		String[] points = diretorio.split("\\.");

		String ext = points[points.length-1];

		URL dir = null;
		try {
			dir = new URL(url, diretorio);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}

		if(!ext.equalsIgnoreCase("tga")){			

			try {

				img = ImageIO.read(dir);

				if(img==null){
					System.err.println("Imagem "+diretorio+" não encontrada.");
					return null;
				}

			} catch (IOException e) {
				System.err.println("Imagem "+diretorio+" não encontrada.");
			}


			camadanova.put(diretorio,img);
		}else{

			try {
				img = TGAReader.getImage(dir.toString());
				camadanova.put(diretorio,img);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return img;
	}

	public void escreveCamada(String texto, Camada destino) {
		FontMetrics fm = screen.getFontMetrics();

		int msg_width = fm.stringWidth(texto);

		int ascent = fm.getMaxAscent();
		int descent= fm.getMaxDescent();

		int x = destino.getX()+destino.getXLimite()/2 - msg_width/2;
		int y = destino.getY()+destino.getYLimite()/2 - descent/2 + ascent/2;

		screen.drawString(texto, x, y);
	}


	public void escreveX(int offsetX, int y, String frase){

		if((frase!=null)&&(!frase.isEmpty())){

			FontMetrics fm = screen.getFontMetrics();

			int x = (largura/2)-(fm.stringWidth(frase)/2);
			int fy = y+fm.getHeight();

			screen.drawString(frase,x,fy);

		}
	}

	public void escreve(int x, int y, String frase){
		if((frase!=null)&&(!frase.isEmpty())){
			screen.drawString(frase,x,y);
		}
	}

	public void escreveX(int y, String frase) {

		escreveX(0,y,frase);
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

	public Image getImagem(CamadaAnimacao ncam){

		String nomeImagem = ncam.getCaminho();//.toLowerCase();

		Image imagem = carregaImagem(nomeImagem);

		if(imagem==null){
			return null;
		}

		return imagem;
	}


	private Image gImagem(String nomeImagem){

		if(pasta.equals(nomeImagem)){
			return null;
		}

		Image imagem = carregaImagem(nomeImagem);

		return imagem;
	}

	public void desenha(CamadaAnimacao[] imagens) {

		for(int i=0;i<imagens.length;i++){
			desenha(imagens[i]);
		}

	}

	public void desenha(Camada[] imagens) {
		for(Camada imagem: imagens){
			desenha(imagem);
		}

	}

	public void desenha(Camada cam){

		Image imagem = getImagem(cam);

		//if(!("").equals(cam.getCaminho())){

		if(cam.getAparecendo()){

			int x = cam.getX();
			int y = cam.getY();

			screen.drawImage( imagem, x, y, x+cam.getXLimite(),y+cam.getYLimite(),
					cam.getXImagem(),cam.getYImagem(),cam.getXImagem()+cam.getXLimite(),cam.getYImagem()+cam.getYLimite(), null );

		}

		//}
	}

	public void desenha(EfeitoSonoro efeito){
		if(efeito.isTocando()){
			//midia.tocaSom(efeito.getSom());
			efeito.setTocando(false);
		}
		desenha((CamadaAnimacao) efeito);
	}

	public void desenha(CamadaAnimacao cam)
	{
		if(("").equals(cam.getCaminho()))
			return;		
		Image imagem = getImagem(cam);

		if(cam.getAparecendo())
		{ 
			int x = cam.getX();
			int y = cam.getY();

			int xTile = cam.getXTile();
			int yTile = cam.getYTile();
			int xImagem = cam.getXImagem();
			int yImagem = cam.getYImagem();

			screen.drawImage( imagem, x, y, x+xTile,y+yTile,
					xImagem,yImagem,xImagem+xTile,yImagem+yTile, null );
		}
	}


	public void desenha(CamadaBuffer cam){

		Image imagem = cam.getImagemBuffer();		

		if(cam.getAparecendo()){

			int x = cam.getX();
			int y = cam.getY();

			screen.drawImage( imagem, x, y, x+cam.getXLimite(),y+cam.getYLimite(),
					cam.getXImagem(),cam.getYImagem(),cam.getXImagem()+cam.getXLimite(),cam.getYImagem()+cam.getYLimite(), null );

		}

	}

	public void draw(int x, int y, BufferedImage imagem){
		screen.drawImage( imagem, x, y, x+imagem.getWidth(),y+imagem.getHeight(),
				0,0,imagem.getWidth(),imagem.getHeight(), null );
	}

	public void desenha(CamadaPivot camP){

		Image imagem = getImagem(camP);

		if(("").equals(camP.getCaminho()))
			return;

		if(camP.getAparecendo()){

			int x = camP.getX();
			int y = camP.getY();

			screen.drawImage( imagem, x, y, x+camP.getXLimite(),y+camP.getYLimite(),
					camP.getXImagem(),camP.getYImagem(),camP.getXImagem()+camP.getXLimite(),camP.getYImagem()+camP.getYLimite(), null );

		}		
	}

	public void desenha(Janela janela){

		Image imagem = janela.getTela();	

		if(janela.getAparecendo()){

			int x = janela.getX();
			int y = janela.getY();

			screen.drawImage( imagem, x, y, x+janela.getXLimite(),y+janela.getYLimite(),
					0,0,janela.getXLimite(),janela.getYLimite(), null );

		}

	}

	//TODO Colocar fontes nos resources
	public Font carregaFonte(String nomeFonte, int estilo, int tamanho){
		Font f = new Font(nomeFonte, estilo, tamanho);
		return f;
	}

	public Font carregaFonte(String nomeFonte){

		return carregaFonte(nomeFonte,16f);
	}

	public Font carregaFonte(String nomeFonte, float tamanho){

		Font font = null;

		if(!fonte.containsKey(nomeFonte)){

			try {
				font = Font.createFont( Font.TRUETYPE_FONT, new FileInputStream("fontes/"+nomeFonte) );
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (FontFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			font = font.deriveFont(tamanho);
		}

		else{
			font = fonte.get(nomeFonte);
		}

		return font;

	}

	public void desenha(CamadaTexto texto){

		if(texto.getAparecendo()){

			//TODO if font nao foi definida no gerenciador...
			Font f = carregaFonte(texto.getNomeFonte(), texto.getEstilo(), texto.getTamanho());

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

	//Gerenciamento das CamadasNovas
	public Image getImagem(Camada ncam){

		String nomeImagem = ncam.getCaminho();//.toLowerCase();

		Image imagem = gImagem(nomeImagem);

		if(imagem==null){
			return null;
		}

		ncam.setCoordLimite(imagem.getWidth(null), imagem.getHeight(null));

		return imagem;
	}


	public Image getImagem(String caminho) {
		return carregaImagem(caminho);
	}
	public Image getImagem(CamadaEstatica camEst) {
		return carregaImagem(camEst.getCaminho());
	}
	public void centralizaX(Camada c){
		if(c.getXLimite()==0)
			c.igualaImagem(getImagem(c));

		c.centralizaX(0,largura);
	}
	public void centralizaY(Camada c){
		if(c.getYLimite()==0)
			c.igualaImagem(getImagem(c));

		c.centralizaY(0,altura);
	}

	public String carregaCamada(String caminho){
		carregaImagem(caminho);
		return caminho;
	}

	public CamadaEstatica novaCamadaEstatica(String caminho){

		String path = pasta+caminho;

		carregaImagem(caminho);

		CamadaEstatica cam = new CamadaEstatica(caminho);

		cam.setCoordLimite(camadanova.get(path).getWidth(null), camadanova.get(path).getHeight(null));

		return cam;
	}

	public Camada novaCamada(String caminho){

		String path = pasta+caminho;

		carregaImagem(caminho);

		Camada cam = new Camada(caminho);
		cam.setCoordLimite(camadanova.get(path).getWidth(null), camadanova.get(path).getHeight(null));

		return cam;
	}

	public Graphics2D getGraphics(){
		return screen;
	}

	public void drawRect(Camada camada){
		screen.drawRect(camada.getX(), camada.getY(), camada.getXLimite(), camada.getYLimite());
	}
	/** Funções Delegadas */
	public void setFont(Font fonte){
		screen.setFont(fonte);
	}
	public Font getFont(){
		return screen.getFont();		
	}
	public void setColor(Color c){
		screen.setColor(c);
	}
	public void setColor(Cor c){
		screen.setColor(c.getColor());
	}

	public void setAlpha(int porcento){

		float a = (float)porcento/100;

		screen.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, a));
	}

	public void fillRect(int x, int y, int largura, int altura) {
		screen.fillRect(x,y,largura,altura);
	}

	public void drawRect(int x, int y, int largura, int altura) {
		screen.drawRect(x,y,largura,altura);
	}

	public void drawRect(double x, double y, double largura, double altura) {
		screen.drawRect((int)x,(int)y,(int)largura,(int)altura);
	}

	public void drawOval(int cx, int cy, int raio ){
		screen.drawOval(cx-raio/2, cy-raio/2, raio, raio);
	}

	public void fillOval(int cx, int cy, int raio ){
		screen.fillOval(cx-raio/2, cy-raio/2, raio, raio);
	}


}
