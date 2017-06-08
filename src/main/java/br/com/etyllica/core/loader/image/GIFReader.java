package br.com.etyllica.core.loader.image;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import br.com.etyllica.core.loader.image.gif.GifDecoder;

public class GIFReader implements ImageReader, AnimationReader {

	private GifDecoder decoder;
	
	private static GIFReader instance = null;
	
	public static GIFReader getInstance() {
		if(instance==null) {
			instance = new GIFReader();
		}

		return instance;
	}
	
	public GIFReader() {
		super();
	}
	
	@Override
	public BufferedImage loadImage(URL url) throws IOException {
		decoder = new GifDecoder();
		decoder.read(url.getPath());
		return decoder.getImage();
	}
	
	@Override
	public List<ImageFrame> loadAnimation(URL url) throws IOException {
		decoder.read(url.getPath());
		return decoder.getFrames();
	}
	
}
