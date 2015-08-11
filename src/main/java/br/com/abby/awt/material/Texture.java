package br.com.abby.awt.material;

import java.awt.Color;
import java.awt.image.BufferedImage;

import br.com.etyllica.layer.BufferedLayer;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Texture extends BufferedLayer{

	public Texture(BufferedImage imagem) {
		super(imagem);
		
		flipHorizontal();
	}
		
	public byte[][][] getAlphaBytes(){
		
		int width = (int)w;
		
		int height = (int)h;
		
		byte imagem2D[][][] = new byte [width][height][4];

		Color c;

		for(int i=0;i<h;i++){
			for(int j=0;j<w;j++){

				c = new Color(buffer.getRGB(j,i));

				imagem2D[j][i][0] = (byte)c.getRed();
				imagem2D[j][i][1] = (byte)c.getGreen();
				imagem2D[j][i][2] = (byte)c.getBlue();
				imagem2D[j][i][3] = (byte)c.getAlpha();
			}
		}

		return imagem2D;		
	}

}
