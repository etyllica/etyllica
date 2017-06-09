package br.com.etyllica.loader.image;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public interface ImageReader {
	public BufferedImage loadImage(URL url) throws IOException;	
}
