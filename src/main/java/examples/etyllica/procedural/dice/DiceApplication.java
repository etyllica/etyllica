package examples.etyllica.procedural.dice;

import java.awt.Color;

import br.com.etyllica.commons.context.Application;
import br.com.etyllica.core.graphics.Graphics;

public class DiceApplication extends Application {

	public DiceApplication(int w, int h) {
		super(w,h);
	}

	@Override
	public void load() {
		loading = 100;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(this);
		
		g.setColor(Color.WHITE);
		
		for(int i=0;i<6;i++) {
			g.fillRect(38+70*i,38,64,64);	
		}
		
		g.setColor(Color.BLACK);
		drawNumberOne(g, 40, 40);
		drawNumberTwo(g, 40+70*1, 40);
		drawNumberThree(g, 40+70*2, 40);
		drawNumberFour(g, 40+70*3, 40);
		drawNumberFive(g, 40+70*4, 40);
		drawNumberSix(g, 40+70*5, 40);
	}

	private void drawNumberOne(Graphics g, int x, int y) {
		g.fillCircle(x+30, y+30, 8);
	}
	
	private void drawNumberTwo(Graphics g, int x, int y) {
		g.fillCircle(x+16-4, y+16-4, 8);
		g.fillCircle(x+60-8-4, y+60-8-4, 8);
	}
	
	private void drawNumberThree(Graphics g, int x, int y) {
		drawNumberOne(g, x, y);
		drawNumberTwo(g, x, y);
	}
	
	private void drawNumberFour(Graphics g, int x, int y) {
		g.fillCircle(x+16-4, y+16-4, 8);
		g.fillCircle(x+60-8-4, y+16-4, 8);
		g.fillCircle(x+16-4, y+60-8-4, 8);
		g.fillCircle(x+60-8-4, y+60-8-4, 8);
	}
	
	private void drawNumberFive(Graphics g, int x, int y) {
		drawNumberOne(g, x, y);
		drawNumberFour(g, x, y);
	}
	
	private void drawNumberSix(Graphics g, int x, int y) {
		drawNumberFour(g, x, y);
		g.fillCircle(x+16-4, y+34-4, 8);
		g.fillCircle(x+60-8-4, y+34-4, 8);
	}
	
}
