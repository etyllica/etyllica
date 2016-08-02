package br.com.etyllica.loader.image;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.etyllica.layer.StaticLayer;
import br.com.etyllica.loader.LoaderImpl;
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

	private String getFullPath(String path, boolean absolute) {

		StringBuilder sb = new StringBuilder();

		if(!absolute) {
			sb.append(url.getPath());
			sb.append(folder);
		}

		sb.append(path);

		return sb.toString();
	}

	public BufferedImage getImage(String path, boolean absolute) {

		String diretorio = getFullPath(path, absolute);

		if(images.containsKey(diretorio)) {
			return images.get(diretorio);
		} else {

			BufferedImage img = null;

			URL dir = null;

			if(!absolute) {
				try {
					dir = new URL(url, diretorio);
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}	
			} else {
				
				if(!diretorio.startsWith(IOHelper.FILE_PREFIX)) {
					diretorio = IOHelper.FILE_PREFIX+diretorio;	
				}
				
				try {
					dir = new URL(diretorio);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			String ext = StringUtils.fileExtension(diretorio);

			ImageReader reader = loaders.get(ext);

			if(reader == null) {
				System.out.println("Etyllica can't load "+ext+" files.");
			} else {
				try {
					img = reader.loadImage(dir);
					images.put(diretorio, img);
				} catch (IOException e) {
					System.err.println("Image "+diretorio+" not found.");
				}
			}

			return img;
		}

	}

	public List<ImageFrame> getAnimation(String path) {

		return getAnimation(path, false);

	}

	public List<ImageFrame> getAnimation(String path, boolean absolute) {

		String diretorio = getFullPath(path, absolute);

		if(animations.containsKey(diretorio)) {
			return animations.get(diretorio);

		}else{

			List<ImageFrame> list = null;

			URL dir = null;

			try {
				dir = new URL(url, diretorio);
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			}

			String ext = StringUtils.fileExtension(diretorio);

			if(ext.equals("gif")) {

				try {
					list = GIFReader.getInstance().loadAnimation(dir);
					animations.put(diretorio, list);

				} catch (IOException e) {

					System.err.println("Image "+diretorio+" not found.");

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

}
