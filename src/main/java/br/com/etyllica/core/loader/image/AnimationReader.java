package br.com.etyllica.core.loader.image;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public interface AnimationReader {
	public List<ImageFrame> loadAnimation(URL url) throws IOException;
}
