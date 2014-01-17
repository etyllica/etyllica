package br.com.etyllica.core.loader.image;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public interface ImageReader {
	
	public BufferedImage loadImage(URL url) throws IOException;
	
	public List<ImageFrame> loadAnimation(URL url) throws IOException;
	
}
