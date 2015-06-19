package br.com.etyllica.core.loader.image;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import br.com.etyllica.core.loader.LoaderImpl;
import br.com.etyllica.layer.StaticLayer;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class ImageLoader extends LoaderImpl {

	private static ImageLoader instance = null;

	private Map<String, ImageReader> readers = new HashMap<String, ImageReader>();

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

		readers.put(BMP, awtReader);
		readers.put(JPG, awtReader);
		readers.put(JPEG, awtReader);
		readers.put(PNG, awtReader);
		readers.put(TIF, awtReader);
		readers.put(TIFF, awtReader);
		readers.put(ICO, new ICOReader());
		readers.put(GIF, new GIFReader());
		readers.put(TGA, new TGAReader());
		readers.put(PCX, new PCXReader());
	}

	public static ImageLoader getInstance() {
		if(instance==null) {
			instance = new ImageLoader();
		}

		return instance;
	}

	public StaticLayer loadImage(String path) {

		BufferedImage img = getImage(path);

		StaticLayer cam = new StaticLayer();
		cam.setSize(img.getWidth(), img.getHeight());
		cam.cloneLayer(path);

		return cam;
	}

	public BufferedImage getImage(String path) {
		return getImage(path, false);
	}

	private String getDiretorio(String path, boolean absolute) {

		StringBuilder sb = new StringBuilder();

		if(!absolute) {
			sb.append(folder);
		}

		sb.append(path);

		return sb.toString();
	}

	public BufferedImage getImage(String path, boolean absolute) {

		String diretorio = getDiretorio(path, absolute);

		if(images.containsKey(diretorio)) {

			return images.get(diretorio);

		} else {

			BufferedImage img = null;

			URL dir = null;

			try {
				dir = new URL(url, diretorio);
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			}

			int mid = diretorio.lastIndexOf(".");
			String ext = diretorio.substring(mid+1,diretorio.length()).toLowerCase();

			ImageReader reader = readers.get(ext);

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

		String diretorio = getDiretorio(path, absolute);

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

			int mid= diretorio.lastIndexOf(".");
			String ext=diretorio.substring(mid+1,diretorio.length()).toLowerCase();

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


}
