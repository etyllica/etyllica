package br.com.etyllica.core.loader.image;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public abstract class ImageReaderImpl implements ImageReader{

	@Override
	public List<ImageFrame> loadAnimation(URL url) throws IOException {
		
		List<ImageFrame> frames = new ArrayList<ImageFrame>();
		
		frames.add(new ImageFrame(this.loadImage(url), 0));
		
		return frames;
		
	}
	
}
