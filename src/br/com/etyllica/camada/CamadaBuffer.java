package br.com.etyllica.camada;

import java.awt.Color;
import java.awt.Image;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class CamadaBuffer extends Camada{

	//Original
	protected BufferedImage imagemBuffer;

	//Modificada � a imgDefinida

	private int offsetVermelho = 0;
	private int offsetVerde = 0;
	private int offsetAzul = 0;

	private int tolerancia = 255;

	public CamadaBuffer(int x, int y) {
		super(x,y);
		caminho = "etyllica";
	}
	public CamadaBuffer(int x, int y, Image buf) {
		this(x,y);
		igualaImagem(buf);
	}
	public CamadaBuffer(int x, int y, int xLimite, int yLimite) {
		
		this(x,y);
		
		this.xLimite = xLimite;
		this.yLimite = yLimite;

		imagemBuffer = new BufferedImage(xLimite, yLimite,BufferedImage.TYPE_INT_ARGB);
		imagemBuffer.getGraphics().fillRect(0,0,xLimite,yLimite);
	}

	public CamadaBuffer(Image buf) {
		this(0,0,buf);
	}
	public void igualaImagem(Image buf){

		if(buf==null)
			return;

		int w = buf.getWidth(null);
		int h = buf.getHeight(null);

		xLimite = w;
		yLimite = h;

		imagemBuffer = new BufferedImage(w, h,BufferedImage.TYPE_INT_ARGB);
		imagemBuffer.getGraphics().drawImage(buf, 0,0,null);

	}

	public void igualaImagem(BufferedImage buf){

		imagemBuffer = buf;

		xLimite = imagemBuffer.getWidth();
		yLimite = imagemBuffer.getHeight();

	}

	public BufferedImage getImagemBuffer(){
		return imagemBuffer;
	}

	//Fun��es de Pixel
	public void mudaVermelho(int r){

		resetaImagem();

		offsetVermelho = r;

		manipulaPixel();

	}

	public void mudaVerde(int g){

		resetaImagem();

		offsetVerde = g;

		manipulaPixel();

	}

	public void mudaAzul(int b){

		resetaImagem();

		offsetAzul = b;

		manipulaPixel();

	}

	public void mudaRGB(int red, int green, int blue){
		

		//sensationalReset(red, green, blue);
		
		//resetaImagem();
		
		mudaVermelho(red);
		mudaVerde(green);
		mudaAzul(blue);
		
		//manipulaPixel();
		
		//resetaImagem();
		
		
		//sensationalReset(red, green, blue);
	}
	
	protected void sensationalReset(int or, int og, int ob){
		
		BufferedImage img = imagemBuffer;

		int r,g,b;
		
		int max = 200;
		
		for(int i=0;i<yLimite;i++){
			for(int j=0;j<xLimite;j++){

				Color c = new Color(img.getRGB(j,i));

				if(c.getAlpha()>200){

					if(c.getBlue()+ob>max)
						b = max;
					else if(c.getBlue()+ob<0)
						b = 0;
					else
						b = c.getBlue()+ob;

					if(c.getGreen()+og>max)
						g = max;
					else if(c.getGreen()+og<0)
						g = 0;
					else
						g = c.getGreen()+og;

					if(c.getRed()+or>max)
						r = max;
					else if(c.getRed()+or<0)
						r = 0;
					else
						r = c.getRed()+or;
					
					Color nc = new Color(r,b,g);
					
					img.setRGB(j, i, nc.getRGB());
					
				}
				//else{
					//img.setRGB(j, i, 0x00000000);
				//}
			}
		}
	}

	private Image manipulaPixel(){

		BufferedImage img = imagemBuffer;

		int r = offsetVermelho;
		int g = offsetVerde;
		int b = offsetAzul;

		for(int i=0;i<yLimite;i++){
			for(int j=0;j<xLimite;j++){

				int rgb = img.getRGB(j,i);

				Color c = new Color(rgb);


				if(c.getBlue()+offsetAzul>255)
					b = 255;
				else if(c.getBlue()+offsetAzul<0)
					b = 0;
				else
					b = offsetAzul;

				if(c.getGreen()+offsetVerde>255)
					g = 255;
				else if(c.getGreen()+offsetVerde<0)
					g = 0;
				else
					g = offsetVerde;

				if(c.getRed()+offsetVerde>255)
					r = 255;
				else if(c.getRed()+offsetVermelho<0)
					r = 0;
				else
					r = offsetVermelho;

				if((c.getRed()<tolerancia)
						||(c.getGreen()<tolerancia)
						||(c.getBlue()<tolerancia)
						||(c.getAlpha()<255)
				)
				{

					Color nc = new Color(r,g,b); 

					rgb += nc.getRGB();

					img.setRGB(j, i, rgb);
				}
				else{
					img.setRGB(j, i, 0x00000000);
				}

			}
		}

		return img;

	}


	public void resetaImagem(){

		BufferedImage img = imagemBuffer;

		for(int i=0;i<yLimite;i++){
			for(int j=0;j<xLimite;j++){

				int rgb = img.getRGB(j,i);

				Color c = new Color(rgb);

				if(c.getAlpha()>0){

					Color oc = new Color(offsetVermelho,offsetVerde,offsetAzul); 

					rgb -= oc.getRGB();

					img.setRGB(j, i, rgb);
				}
			}
		}
	}

	//Precisa Melhorar

	/*
	public void recarregaImagem(){
		try {
			imagemBuffer = ImageIO.read(dir);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 */

	/*
	public Image getImagem(){
		int w = imagemBuffer.getWidth();
		int h = imagemBuffer.getHeight();
		imgDefinida = imagemBuffer.getScaledInstance(w, h, BufferedImage.TYPE_INT_ARGB);
		xLimite = imgDefinida.getWidth(null);
		yLimite = imgDefinida.getHeight(null);
		return imgDefinida;
	}
	 */

	public int getOffsetVermelho(){
		return offsetVermelho;
	}
	public int getOffsetVerde(){
		return offsetVerde;
	}
	public int getOffsetAzul(){
		return offsetAzul;
	}

	//Manipula��o de Imagens
	public void espelharVertical(){
		AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
		tx.translate(0, -yLimite);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		imagemBuffer = op.filter(imagemBuffer, null);
	}
	public void espelharHorizontal(){
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-xLimite, 0);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		imagemBuffer = op.filter(imagemBuffer, null);
	}

	public void girar180(){
		AffineTransform tx = AffineTransform.getScaleInstance(-1, -1);
		tx.translate(-xLimite, -yLimite);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		imagemBuffer = op.filter(imagemBuffer, null);
	}
	public void girar(double angulo){

	}

	public void offsetRGB(int red, int green, int blue){

		mudaRGB(80,80,80);
		/*
		BufferedImage img = imagemBuffer;

		for(int i=0;i<yLimite;i++){
			for(int j=0;j<xLimite;j++){

				int rgb = img.getRGB(j,i);

				Color c = new Color(rgb);

				if(c.getAlpha()>255){

					int r = c.getRed()+red;
					int g = c.getGreen()+green;
					int b = c.getBlue()+blue;

					if(r>255)
						r = 255;
					else if(r<0)
						r = 0;

					if(g>255)
						g = 255;
					else if(g<0)
						g = 0;

					if(b>255)
						b = 255;
					else if(b<0)
						b = 0;

					img.setRGB(j,i,new Color(r,g,b).getRGB());
				}				
			}
		}
		*/
		//return img;	
	}

}
