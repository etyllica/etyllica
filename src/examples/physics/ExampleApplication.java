package examples.physics;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.dyn4j.RigidBody;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.Mass;

import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.video.Graphic;
import br.com.etyllica.layer.ImageLayer;
import br.com.etyllica.layer.Layer;

public class ExampleApplication extends Application {
		
	public ExampleApplication(int w, int h) {
		super(w, h);
	}
	
	private RigidBody floor;
	
	private List<RigidBody> crates;	
	
	protected World world;

	@Override
	public void load() {

		this.world = new World();
				
		floor = new RigidBody(new Layer(0,500,w,20));
		floor.setMass(Mass.Type.INFINITE);
		
		this.world.addBody(floor);
		
		crates = new ArrayList<RigidBody>();
		loading = 10;
		
		for(int i=0;i<6;i++){
			
			RigidBody crate = new RigidBody(new ImageLayer(20+50*i, 10+70*i, "crate.jpg"));
			crate.getLinearVelocity().set(5, 0.0);
						
			this.world.addBody(crate);
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
	public GUIEvent updateKeyboard(KeyEvent event) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public void timeUpdate(){
				
		updateWorld();
	}

	@Override
	public void draw(Graphic g) {

		g.setColor(Color.BLACK);
		g.drawRect(floor.getLayer());
		
		for(RigidBody crate: crates){
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
