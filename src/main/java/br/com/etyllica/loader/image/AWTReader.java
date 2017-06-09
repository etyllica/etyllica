package br.com.etyllica.loader.image;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class AWTReader implements ImageReader {

	@Override
	public BufferedImage loadImage(URL url) throws IOException {
		return ImageIO.read(url);
	}

}