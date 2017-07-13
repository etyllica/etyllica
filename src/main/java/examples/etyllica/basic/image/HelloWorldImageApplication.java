package examples.etyllica.basic.image;

import br.com.etyllica.commons.context.Application;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.layer.ImageLayer;

public class HelloWorldImageApplication extends Application {

	ImageLayer layer;

	public HelloWorldImageApplication(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {
		layer = new ImageLayer("etyllica_logo.png");
		layer.centralize(this);

		loading = 100;
	}

	@Override
	public void draw(Graphics g) {
		layer.draw(g);
	}

}
