package examples.etyllica.basic.hello;

import java.awt.Color;

import br.com.etyllica.commons.context.Application;
import br.com.etyllica.core.graphics.Graphics;

public class HelloWorldApplication extends Application {

	public HelloWorldApplication(int w, int h) {
		super(w, h);
	}

	@Override
	public void draw(Graphics g) {
		//Paint the background
		g.setColor(Color.BLUE);
		//this = this dice
		g.fillRect(this);
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
	}
	
}
