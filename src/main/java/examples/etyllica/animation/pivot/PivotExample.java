package examples.etyllica.animation.pivot;

import java.awt.Color;
import java.util.Set;

import br.com.etyllica.core.animation.pivot.Part;
import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.linear.Point2D;

public class PivotExample extends Application {

	public PivotExample(int w, int h) {
		super(w, h);
	}

	private Part root;
	private Part head;
	private Part arm;
	private Part torso;

	private double angle = 0;

	@Override
	public void load() {

		loading = 10;

		createChar();
		root = head;

		//Transformations
		head.moveTo(300, 260);
		torso.rotate(10);
		
		loading = 100;
	}

	@Override
	public void draw(Graphic g) {
		drawPart(g, root);
	}

	@Override
	public void update(long now) {
		angle--;
		torso.rotate(angle);
	}

	@Override
	public GUIEvent updateMouse(PointerEvent event) {

		if(event.isClicked()) {
			System.out.println("x: "+event.getX());
			System.out.println("y: "+event.getY());
		}

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {

		// TODO Auto-generated method stub
		return null;
	}	

	public void drawPart(Graphic g, Part part) {
		part.draw(g);
		drawPoints(g, part);

		for(Set<Part> ps : part.getParts()) {
			for(Part subPart : ps) {
				drawPart(g, subPart);
			}
		}
	}

	public void drawPoints(Graphic g, Part part) {
		for(Point2D point: part.getPoints()) {
			g.setColor(Color.BLACK);
			g.fillCircle(part.getX()+point.getX(), part.getY()+point.getY(), 5);
		}
	}

	private void createChar() {
		head = new Part(100,60, "parts/head.png");
		head.setAnchor(head.getW()/2, 5);

		Point2D neck = new Point2D(16,30);
		head.addPoint(neck);		

		torso = new Part(200,60, "parts/torso.png");
		torso.setAnchor(torso.getW()/2, 1);

		head.attach(neck, torso);

		//Torso joints
		Point2D leftShoulder = new Point2D(5, 10);
		torso.addPoint(leftShoulder);

		Point2D rightShoulder = new Point2D(39, 11);
		torso.addPoint(rightShoulder);

		Point2D waist = new Point2D(20, 52);
		torso.addPoint(waist);

		Part armLeft = new Part("parts/larm.png");
		armLeft.setAnchor(15,7);
		Point2D elbowLeft = new Point2D(10,32);
		armLeft.addPoint(elbowLeft);
		
		torso.attach(leftShoulder, armLeft);

		//Left ForeArm
		Part foreArmLeft = new Part("parts/lforearm.png");
		foreArmLeft.setAnchor(6, 20);
		armLeft.attach(elbowLeft, foreArmLeft);

		Point2D fistLeft = new Point2D(20,5);
		foreArmLeft.addPoint(fistLeft);

		//Left Hand
		Part handLeft = new Part("parts/lhand.png");
		handLeft.setAnchor(2,18);
		foreArmLeft.attach(fistLeft, handLeft);

		//Right Arm
		Part armRight = new Part("parts/rarm.png");
		armRight.setAnchor(3, 7);

		Point2D elbowRight = new Point2D(14,36);
		armRight.addPoint(elbowRight);

		torso.attach(rightShoulder, armRight);

		//Right Forearm
		Part foreArmRight = new Part("parts/rforearm.png");
		foreArmRight.setAnchor(7, 25);

		Point2D fistRight = new Point2D(14,7);
		foreArmRight.addPoint(fistRight);

		armRight.attach(elbowRight, foreArmRight);

		//Right Hand
		Part handRight = new Part("parts/rhand.png");
		handRight.setAnchor(8,22);
		foreArmRight.attach(fistRight, handRight);

		//Hip
		Part pelvis = new Part("parts/hip.png");
		pelvis.setAnchor(19,5);

		Point2D hipLeft = new Point2D(9,13);
		pelvis.addPoint(hipLeft);
		Point2D hipRight = new Point2D(27,13);
		pelvis.addPoint(hipRight);

		torso.attach(waist, pelvis);

		//Left Thigh
		Part thighLeft = new Part("parts/lthigh.png");
		thighLeft.setAnchor(10,7);

		Point2D kneeLeft = new Point2D(14,51);
		thighLeft.addPoint(kneeLeft);

		pelvis.attach(pelvis.getPoints().get(1), thighLeft);

		//Left Leg
		Part legLeft = new Part("parts/lleg.png");
		legLeft.setAnchor(16, 6);

		Point2D ankleLeft = new Point2D(7,43);
		legLeft.addPoint(ankleLeft);

		//thighLeft.getPoints().get(0).addPart(legLeft);
		thighLeft.attach(kneeLeft, legLeft);

		//Left Foot
		Part footLeft = new Part("parts/lfoot.png");
		footLeft.setAnchor(12,4);

		legLeft.attach(ankleLeft, footLeft);

		//Right Thigh
		Part thighRight = new Part("parts/rthigh.png");
		thighRight.setAnchor(10,4);

		Point2D kneeRight = new Point2D(17,45);
		thighRight.addPoint(kneeRight);

		pelvis.attach(hipRight, thighRight);

		//Right Leg
		Part legRight = new Part("parts/rleg.png");
		legRight.setAnchor(10,4);
		thighRight.attach(kneeRight, legRight);
		
		Point2D ankleRight = new Point2D(11,41);
		legRight.addPoint(ankleRight);
		
		//Right Foot
		Part footRight = new Part("parts/rfoot.png");
		footRight.setAnchor(10,4);
		legRight.attach(ankleRight, footRight);
		
		arm = armLeft;
	}

}