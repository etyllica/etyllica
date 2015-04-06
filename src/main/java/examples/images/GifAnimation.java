package examples.images;

import java.util.List;

import br.com.etyllica.context.Application;
import br.com.etyllica.context.UpdateIntervalListener;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.loader.image.ImageFrame;
import br.com.etyllica.core.loader.image.ImageLoader;

public class GifAnimation extends Application implements UpdateIntervalListener {

	public GifAnimation(int w, int h) {
		super(w, h);
	}

	private List<ImageFrame> frames;

	@Override
	public void load() {
		
		loading = 10;
				
		frames = ImageLoader.getInstance().getAnimation("shark.gif");

		updateAtFixedRate(frames.get(0).getDelay(), this);
		
		loading = 100;

	}

	private int count = 0;
	
	public void timeUpdate(long now){
		count++;
		count%=frames.size();
	}
	
	@Override
	public void draw(Graphic g) {
				
		g.drawImage(frames.get(count).getImage(), 0, 0);

	}
	
	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
