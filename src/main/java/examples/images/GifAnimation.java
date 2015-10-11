package examples.images;

import java.util.List;

import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.context.UpdateIntervalListener;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.loader.image.ImageFrame;
import br.com.etyllica.loader.image.ImageLoader;

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
}
