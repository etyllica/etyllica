package examples.physics;

import java.awt.Color;
import java.awt.geom.AffineTransform;

import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Convex;

import br.com.etyllica.core.video.Grafico;


/**
 * Custom Body class to add drawing functionality.
 * @author William Bittle
 * @version 3.0.2
 * @since 3.0.0
 */
public class GameObject extends Body {
		
	/** The color of the object */
	protected Color color;

	/**
	 * Default constructor.
	 */
	public GameObject() {
		// randomly generate the color
		this.color = new Color(
				(float)Math.random() * 0.5f + 0.5f,
				(float)Math.random() * 0.5f + 0.5f,
				(float)Math.random() * 0.5f + 0.5f);
	}

	/**
	 * Draws the body.
	 * <p>
	 * Only coded for polygons and circles.
	 * @param g the graphics object to render to
	 */
	public void render(Grafico g) {
		// save the original transform
		AffineTransform resetTransform = g.getTransform();

		// transform the coordinate system from world coordinates to local coordinates
		AffineTransform lt = new AffineTransform();
		lt.translate(this.transform.getTranslationX(), this.transform.getTranslationY());
		lt.rotate(this.transform.getRotation());

		// apply the transform
		g.transform(lt);

		// loop over all the body fixtures for this body
		for (BodyFixture fixture : this.fixtures) {
			// get the shape on the fixture
			Convex convex = fixture.getShape();
			Graphics2DRenderer.render(g.getGraphics(),convex,1.0, Color.BLUE);
		}

		// set the original transform
		g.setTransform(resetTransform);
	}
}
