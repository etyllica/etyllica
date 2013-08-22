package br.com.etyllica.layer;

import java.awt.Color;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import br.com.etyllica.core.loader.ImageLoader;
import br.com.etyllica.core.video.Grafico;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class BufferedLayer extends ImageLayer{

	private BufferedImage originalBuffer;
	protected BufferedImage imagemBuffer;

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public BufferedLayer(int x, int y) {
		super(x,y);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param path
	 */
	public BufferedLayer(int x, int y, String path) {
		super(x,y,path);
		igualaImagem(ImageLoader.getInstance().getImage(path));
	}

	/**
	 * 
	 * @param path
	 */
	public BufferedLayer(String path) {
		this(0,0,path);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param buffer
	 */
	public BufferedLayer(int x, int y, BufferedImage buffer) {
		this(x,y);
		igualaImagem(buffer);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public BufferedLayer(int x, int y, int w, int h) {

		this(x,y);

		this.w = w;
		this.h = h;

		originalBuffer = new BufferedImage(w, h,BufferedImage.TYPE_INT_ARGB);
		originalBuffer.getGraphics().fillRect(0,0,w,h);

		resetImage();
	}

	/**
	 * 
	 * @param buffer
	 */
	public BufferedLayer(BufferedImage buffer) {
		this(0,0,buffer);
	}

	/**
	 * 
	 * @param buffer
	 */
	public void igualaImagem(Image buffer){

		if(buffer==null)
			return;

		int w = buffer.getWidth(null);
		int h = buffer.getHeight(null);

		this.w = w;
		this.h = h;

		originalBuffer = new BufferedImage(w, h,BufferedImage.TYPE_INT_ARGB);
		originalBuffer.getGraphics().drawImage(buffer,0,0,null);

		resetImage();

	}

	/**
	 * 
	 * @param buffer
	 */
	public void igualaImagem(BufferedImage buffer){

		w = buffer.getWidth();
		h = buffer.getHeight();

		originalBuffer = new BufferedImage(w, h,BufferedImage.TYPE_INT_ARGB);
		originalBuffer.getGraphics().drawImage(buffer,0,0,null);

		resetImage();

	}

	/**
	 * ImagemBuffer volta ao estado original
	 */
	public void resetImage(){
		imagemBuffer = new BufferedImage(w, h,BufferedImage.TYPE_INT_ARGB);
		imagemBuffer.getGraphics().drawImage(originalBuffer,0,0,null);
	}

	/**
	 * Offset all pixels to the selected value
	 * Image receive color 
	 *
	 * @param red
	 * @param green
	 * @param blue
	 */
	public void offsetRGB(int red, int green, int blue){

		resetImage();

		offsetPixels(red,green,blue);

	}

	private void offsetPixels(int offsetRed, int offsetGreen, int offsetBlue){

		for(int j=0;j<h;j++){

			for(int i=0;i<w;i++){

				//Pego o rgb do pixel selecionado
				int rgb = originalBuffer.getRGB(i,j);

				int a = (rgb>>24) & 0xff;

				int r = ((rgb & 0x00ff0000) >> 16);
				int g = ((rgb & 0x0000ff00) >> 8);
				int b = ((rgb & 0x000000ff) >> 0);

				//O valor de offset é somado nos valores de rgb, logo 
				//se o offset for positivo, as cores escuras tornam-se claras(coloridas) e o branco nao se altera.
				//se o offset for negativo, as cores claras(recebem a cor negativa desejada) e o preto nao se altera.
				r += offsetRed;
				g += offsetGreen;
				b += offsetBlue;

				//Verifico se os valores de vermelho ainda estao no range 0~255
				if(r>255){
					r = 255;
				}else if(r<0){
					r = 0;
				}

				//Verifico se os valores de verde ainda estao no range 0~255
				if(g>255){
					g = 255;
				}else if(g<0){
					g = 0;
				}

				//Verifico se os valores de azul ainda estao no range 0~255
				if(b>255){
					b = 255;
				}else if(b<0){
					b = 0;
				}

				//Nao sei porque isso ocorre mas as vezes alpha eh negativo e 
				//me parece que este eh o jeito certo de consertar
				//if(a<0){
				//	a += 255;
				//}

				imagemBuffer.setRGB(i, j, new Color(r,g,b,a).getRGB());

			}
		}

	}

	//Manipulação de Imagens
	public void espelharVertical(){
		AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
		tx.translate(0, -h);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		imagemBuffer = op.filter(imagemBuffer, null);
	}
	public void espelharHorizontal(){
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-w, 0);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		imagemBuffer = op.filter(imagemBuffer, null);
	}

	public void girar180(){
		AffineTransform tx = AffineTransform.getScaleInstance(-1, -1);
		tx.translate(-w, -h);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		imagemBuffer = op.filter(imagemBuffer, null);
	}

	public byte[][][] getBytes(){

		DataBufferInt db = (DataBufferInt)imagemBuffer.getRaster().getDataBuffer();

		int[] by = db.getData(); 

		byte imagem2D[][][] = new byte[w][h][3];

		for(int j=0;j<h;j++){
			for(int i=0;i<w;i++){

				int rgb = by[i+j*w];

				byte r = (byte) ((rgb & 0x00ff0000) >> 16);
				byte g = (byte) ((rgb & 0x0000ff00) >> 8);
				byte b = (byte) (rgb & 0x000000ff);

				imagem2D[i][j][0] = r;
				imagem2D[i][j][1] = g;
				imagem2D[i][j][2] = b;

			}
		}

		return imagem2D;

	}

	public BufferedImage getImagemBuffer(){
		return imagemBuffer;
	}
	
	@Override
	public void draw(Grafico g, AffineTransform transform) {
		g.transform(transform);

		g.drawImage( imagemBuffer, x, y, x+w,y+h,
				xImage,yImage,xImage+w,yImage+h, null );		
	}

}
