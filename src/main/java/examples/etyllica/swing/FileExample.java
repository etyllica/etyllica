package examples.etyllica.swing;

import java.util.ArrayList;
import java.util.List;

import br.com.etyllica.awt.components.chooser.FileChooser;
import br.com.etyllica.awt.components.chooser.SelectFileListener;
import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.MouseButton;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.layer.ImageLayer;
import br.com.etyllica.layer.StaticLayer;
import br.com.etyllica.loader.image.ImageLoader;
import br.com.etyllica.util.PathHelper;

public class FileExample extends Application implements SelectFileListener {

	private FileChooser fc;
	
	public FileExample(int w, int h) {
		super(w, h);
	}

	private int px, py;
	
	private ImageLayer hello;
	
	private List<ImageLayer> layers = new ArrayList<ImageLayer>();

	@Override
	public void load() {
		
		loading = 10;
		hello = new ImageLayer(200,100,"hello.png");		
		loading = 100;
		
		fc = new FileChooser(this.parent.getComponent(), PathHelper.currentDirectory());
		fc.setListener(this);
	}	

	@Override
	public void draw(Graphic g) {
		hello.draw(g);
		
		for(ImageLayer layer: layers) {
			layer.draw(g);
		}
	}
	
	@Override
	public void update(long now) {
		
		if(right) {
			hello.setOffsetX(1);
		}
		if(left) {
			hello.setOffsetX(-1);
		}
		
		if(down) {
			hello.setOffsetY(1);
		}
		if(up) {
			hello.setOffsetY(-1);
		}
		
	}
	
	private boolean up = false;
	private boolean down = false;
	
	private boolean right = false;
	private boolean left = false;
	
	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		
		if(event.isKeyDown(KeyEvent.VK_RIGHT_ARROW)) {
			right = true;
		}
		if(event.isKeyUp(KeyEvent.VK_RIGHT_ARROW)) {
			right = false;
		}
		
		if(event.isKeyDown(KeyEvent.VK_LEFT_ARROW)) {
			left = true;
		}
		if(event.isKeyUp(KeyEvent.VK_LEFT_ARROW)) {
			left = false;
		}
		
		if(event.isKeyDown(KeyEvent.VK_UP_ARROW)) {
			up = true;
		}
		if(event.isKeyUp(KeyEvent.VK_UP_ARROW)) {
			up = false;
		}
		
		if(event.isKeyDown(KeyEvent.VK_DOWN_ARROW)) {
			down = true;
		}
	
		if(event.isKeyUp(KeyEvent.VK_DOWN_ARROW)) {
			down = false;
		}
		
		return null;
	}
	
	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		
		if(event.isButtonDown(MouseButton.MOUSE_BUTTON_LEFT)) {
			px = event.getX();
			py = event.getY();
			fc.openDialog();
		}
		
		return null;
	}

	@Override
	public void onSelectFile(String path) {
		System.out.println("Selected: "+path);
		
		StaticLayer image = ImageLoader.getInstance().loadImage(path, true);
		
		ImageLayer layer = new ImageLayer(px, py);
		layer.cloneLayer(image);
		
		layers.add(layer);
	}
	
}
