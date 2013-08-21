package examples.physics;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.Mass;

import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyboardEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.layer.ImageLayer;
import br.com.etyllica.layer.Layer;

public class ExampleApplication extends Application {
		
	public ExampleApplication(int w, int h) {
		super(w, h);
	}
	
	private Layer floor;
	private List<ImageLayer> crates;	
	
	protected World world;

	@Override
	public void load() {

		this.world = new World();
				
		floor = new Layer(0,500,w,20);
		floor.createRectangularBody();
		floor.getBody().setMass(Mass.Type.INFINITE);
		this.world.addBody(floor.getBody());
		
		crates = new ArrayList<ImageLayer>();
		loading = 10;
		
		for(int i=0;i<6;i++){
			
			ImageLayer crate = new ImageLayer(20+50*i, 10+70*i, "crate.jpg");
			crate.createRectangularBody();
			crate.getBody().getLinearVelocity().set(5, 0.0);
						
			this.world.addBody(crate.getBody());
						
			crates.add(crate);
						
		}
		
		
		this.last = System.nanoTime();		
		//TODO Change to update Physics
		updateAtFixedRate(10);
		
		loading = 100;
	}

	/** The conversion factor from nano to base */
	final double NANO_TO_BASE = 1.0e9;

	@Override
	public GUIEvent updateMouse(PointerEvent event) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GUIEvent updateKeyboard(KeyboardEvent event) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public void timeUpdate(){
				
		updateWorld();
	}

	@Override
	public void draw(Grafico g) {

		g.setColor(Color.BLACK);
		g.drawRect(floor);
		
		for(ImageLayer crate: crates){
			crate.draw(g);
		}
		
	}

	/** The time stamp for the last iteration */
	protected long last;

	private void updateWorld(){

		long time = System.nanoTime();
		// get the elapsed time from the last iteration
		long diff = time - this.last;
		// set the last time
		this.last = time;
		// convert from nanoseconds to seconds
		double elapsedTime = (double)diff / NANO_TO_BASE;
		// update the world with the elapsed time
		this.world.update(elapsedTime);
	}

}
