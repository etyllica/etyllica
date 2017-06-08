package br.com.etyllica.core.loader.image;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.etyllica.layer.StaticLayer;
import br.com.etyllica.core.loader.LoaderImpl;
import br.com.etyllica.util.StringUtils;
import br.com.etyllica.util.io.IOHelper;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class ImageLoader extends LoaderImpl {

	private static ImageLoader instance = null;

	private Map<String, ImageReader> loaders = new HashMap<String, ImageReader>();

	private Map<String, BufferedImage> images = new HashMap<String, BufferedImage>();

	private Map<String, List<ImageFrame>> animations = new HashMap<String, List<ImageFrame>>();

	private final static String BMP = "bmp";
	private final static String GIF = "gif";
	private final static String ICO = "ico";
	private final static String JPG = "jpg";
	private final static String JPEG = "jpeg";
	private final static String PCX = "pcx";
	private final static String PNG = "png";
	private final static String TIF = "tif";
	private final static String TIFF = "tiff";
	private final static String TGA = "tga";

	private ImageLoader() {
		super();

		folder = "assets/images/";

		AWTReader awtReader = new AWTReader();

		loaders.put(BMP, awtReader);
		loaders.put(JPG, awtReader);
		loaders.put(JPEG, awtReader);
		loaders.put(PNG, awtReader);
		loaders.put(TIF, awtReader);
		loaders.put(TIFF, awtReader);
		loaders.put(ICO, new ICOReader());
		loaders.put(GIF, new GIFReader());
		loaders.put(TGA, new TGAReader());
		loaders.put(PCX, new PCXReader());
	}

	public static ImageLoader getInstance() {
		if(instance==null) {
			instance = new ImageLoader();
		}

		return instance;
	}

	public StaticLayer loadImage(String path) {
		return loadImage(path, false);
	}
	
	public StaticLayer loadImage(String path, boolean absolute) {

		BufferedImage img = getImage(path, absolute);

		StaticLayer cam = new StaticLayer();
		cam.setSize(img.getWidth(), img.getHeight());
		
		if(absolute) {
			cam.cloneLayer(IOHelper.FILE_PREFIX+path);
		} else {
			cam.cloneLayer(path);
		}

		return cam;
	}

	public BufferedImage getImage(String path) {
		
		boolean absolute = false;
		
		if(path.startsWith(IOHelper.FILE_PREFIX)) {
			absolute = true;
		}
		
		return getImage(path, absolute);
	}

	public BufferedImage getImage(String path, boolean absolute) {

		String fullPath = fullPath(path, absolute);

		if (images.containsKey(fullPath)) {
			return images.get(fullPath);
		} else {
			URL dir = null;

			if (!absolute) {
				try {
					dir = new URL(url, fullPath);
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}	
			} else {
				
				if (!fullPath.startsWith(IOHelper.FILE_PREFIX)) {
					fullPath = IOHelper.FILE_PREFIX + fullPath;	
				}
				
				try {
					dir = new URL(fullPath);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			String ext = StringUtils.fileExtension(fullPath);
			ImageReader reader = loaders.get(ext);

			BufferedImage img = null;

			if(reader == null) {
				System.out.println("Etyllica can't load "+ext+" files.");
			} else {
				try {
					img = reader.loadImage(dir);
					images.put(fullPath, img);
					if (img == null) {
						System.err.println("Image "+fullPath+" not found.");	
					}
				} catch (IOException e) {
					System.err.println("Image "+fullPath+" not found.");
				}
			}

			return img;
		}

	}

	public List<ImageFrame> getAnimation(String path) {

		return getAnimation(path, false);

	}

	public List<ImageFrame> getAnimation(String path, boolean absolute) {

		String fullPath = fullPath(path, absolute);

		if(animations.containsKey(fullPath)) {
			return animations.get(fullPath);

		}else{

			List<ImageFrame> list = null;

			URL dir = null;

			try {
				dir = new URL(url, fullPath);
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			}

			String ext = StringUtils.fileExtension(fullPath);

			if(ext.equals("gif")) {

				try {
					list = GIFReader.getInstance().loadAnimation(dir);
					animations.put(fullPath, list);

				} catch (IOException e) {

					System.err.println("Image "+fullPath+" not found.");

					e.printStackTrace();
				}
			}

			return list;
		}
	}
	
	public Set<String> supportedExtensions() {
		return loaders.keySet();
	}
	
	public void addLoader(String extension, ImageReader loader) {
		loaders.put(extension, loader);
	}
	
	public void disposeImage(String path) {
		images.remove(path);
	}
	
}
