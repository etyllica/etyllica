package br.com.etyllica.core.loader;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import br.com.etyllica.core.loader.image.ICOReader;
import br.com.etyllica.core.loader.image.PCXReader;
import br.com.etyllica.core.loader.image.TGAReader;
import br.com.etyllica.layer.StaticLayer;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class ImageLoader extends LoaderImpl{

	private static ImageLoader instance = null;

	private Map<String, BufferedImage> camadas = new HashMap<String, BufferedImage>();

	private ImageLoader(){
		super();
		
		folder = "assets/images/";
	}

	public static ImageLoader getInstance() {
		if(instance==null){
			instance = new ImageLoader();
		}

		return instance;
	}

	public StaticLayer loadImage(String path){

		BufferedImage img = getImage(path);

		StaticLayer cam = new StaticLayer();
		cam.setSize(img.getWidth(), img.getHeight());
		cam.cloneLayer(path);

		return cam;
	}

	public BufferedImage getImage(String path){
		
		return getImage(path, false);
		
	}
	
	public BufferedImage getImage(String path, boolean absolute){

		StringBuilder sb = new StringBuilder();
				
		if(!absolute){
			sb.append(folder);
		}
		
		sb.append(path);
		
		String diretorio = sb.toString();

		if(camadas.containsKey(diretorio)){

			return camadas.get(diretorio);

		}else{

			BufferedImage img = null;

			URL dir = null;
			
			try {
				dir = new URL(url, diretorio);
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			}

			int mid= diretorio.lastIndexOf(".");
			String ext=diretorio.substring(mid+1,diretorio.length()).toLowerCase();
						
			if(ext.equals("png")||ext.equals("jpg")||ext.equals("jpeg")||ext.equals("bmp")){

				try {

					img = ImageIO.read(dir);

					if(img==null){
						System.err.println("Image "+diretorio+" not found.");
						return null;
					}

				} catch (IOException e) {
					System.err.println("Image "+diretorio+" not found.");
				}

				camadas.put(diretorio,img);

			}else if(ext.equals("tga")){

				try {

					img = TGAReader.getInstance().loadImage(dir);

					camadas.put(diretorio,img);
				} catch (IOException e) {
					e.printStackTrace();
				}

			}else if(ext.equals("pcx")){

				try {
					img = PCXReader.getInstance().loadImage(dir);

					camadas.put(diretorio,img);
				} catch (IOException e) {
					e.printStackTrace();
				}

			}else if(ext.equals("ico")){

				try {
					img = ICOReader.getInstance().loadImage(dir);

					camadas.put(diretorio,img);
				} catch (IOException e) {
					e.printStackTrace();
				}

			}


			return img;
		}
	}

}
