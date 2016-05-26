package br.com.etyllica.awt;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.VolatileImage;

import br.com.etyllica.cinematics.Camera;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.core.linear.Point2D;
import br.com.etyllica.core.linear.PointInt2D;
import br.com.etyllica.layer.GeometricLayer;
import br.com.etyllica.layer.Layer;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class AWTGraphics implements Graphics {

	private VolatileImage vimg;

	protected Graphics2D screen;

	private int width;
	private int height;

	private Color shadowColor = Color.BLACK;

	//Identity matrix
	private static final AffineTransform RESET_TRANSFORM = AffineTransform.getScaleInstance(1, 1);

	public AWTGraphics(int width, int height) {
		super();

		this.width = width;
		this.height = height;
	}

	public AWTGraphics(BufferedImage image) {
		super();
		setImage(image);
	}

	public void setFastImage(BufferedImage image) {
		this.screen = (Graphics2D) image.getGraphics();

		if(screen == null)
			screen = image.createGraphics();

		this.width = image.getWidth();
		this.height = image.getHeight();
	}

	public void setImage(BufferedImage image) {
		setFastImage(image);

		this.screen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		this.screen.setColor(shadowColor);
	}

	public void setVolatileImage(VolatileImage vimg) {

		this.vimg = vimg;
		this.width = vimg.getWidth();
		this.height = vimg.getHeight();

		this.screen = vimg.createGraphics();
		this.screen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		this.screen.setColor(shadowColor);
	}

	public void resetImage() {
		if(vimg == null)
			return;

		this.screen = vimg.createGraphics();
		this.screen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		this.screen.setColor(shadowColor);
	}

	/*public void setBufferedImage(BufferedImage bimg) {
		this.width = bimg.getWidth();
		this.height = bimg.getHeight();

		this.bimg = bimg;
		this.screen = (Graphics2D)bimg.getGraphics();
		this.screen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		this.screen.setColor(Color.BLACK);

	}*/

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param text
	 */
	public void drawString(int x, int y, int w, int h, String text) {

		int dx = centralizeTextX(text, x, w);
		int dy = centralizeTextY(text, y, h);
		
		screen.drawString(text, dx, dy);
	}
	
	public void drawString(GeometricLayer layer, String text) {
		drawString(layer.getX(), layer.getY(), layer.utilWidth(), layer.utilHeight(), text);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param text
	 */
	public void drawString(float x, float y, float w, float h, String text) {
		this.drawString((int) x, (int) y, (int) w, (int) h, text);
	}

	/**
	 * @param x
	 * @param y
	 * @param text
	 * @param exponent
	 */
	public void drawStringExponent(String text, String exponent, int x, int y) {
		this.drawString(text, x, y);

		FontMetrics fm = screen.getFontMetrics();

		float lastSize = fm.getFont().getSize2D();

		float h = lastSize*0.7f;

		int w = fm.stringWidth(text);

		this.setFontSize(h);
		this.drawString(exponent, x+w, (int)(y-h*0.5f));

		this.setFontSize(lastSize);

	}

	public void drawStringExponentShadow(String text, String exponent, int x, int y) {
		this.drawShadow(x, y, text);

		FontMetrics fm = screen.getFontMetrics();

		float lastSize = fm.getFont().getSize2D();

		float h = lastSize*0.7f;

		int w = fm.stringWidth(text);

		this.setFontSize(h);
		this.drawShadow(x+w, (int)(y-h*0.5f), exponent);

		this.setFontSize(lastSize);

	}

	public void drawStringShadow(int x, int y, int w, int h, String text) {
		drawStringShadow(x, y, w, h, text, shadowColor);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param text
	 * @param shadowColor
	 */
	public void drawStringShadow(int x, int y, int w, int h, String text, Color shadowColor) {

		int dx = centralizeTextX(text, x, w);
		
		int dy = centralizeTextY(text, y, h);

		drawShadow(dx, dy, text, shadowColor);
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param text
	 * @param shadowColor
	 */
	public void drawStringShadow(float x, float y, float w, float h, String text, Color shadowColor) {
		this.drawStringShadow((int)x, (int)y, (int)w, (int)h, text, shadowColor);
	}

	/**
	 * 
	 * @param offsetX
	 * @param y
	 * @param text
	 * @param border
	 */
	public void writeX(float offsetX, float y, String text, boolean border) {

		if((text==null)||(text.isEmpty())) {
			return;
		}

		FontMetrics fm = screen.getFontMetrics();
		Font f = getFont();

		float x = centralizeTextX(text)+offsetX;
		float fy = y+fm.getHeight();

		if(!border) {
			screen.drawString(text,x,fy);
		} else {

			FontRenderContext frc = screen.getFontRenderContext();

			TextLayout tl = new TextLayout(text, f, frc);

			Shape sha = tl.getOutline(AffineTransform.getTranslateInstance(x,y));        

			screen.setStroke(new BasicStroke(2.666f));
			screen.setColor(shadowColor);
			screen.draw(sha);

			screen.setColor(Color.WHITE);
			screen.fill(sha);

		}
	}

	public void drawStringBorder(String text, int x, int y, int w, int h) {
		
		int dx = centralizeTextX(text, x, w);
		int dy = centralizeTextY(text, y, h);
		
		drawStringBorder(text, dx, dy);		
	}
	
	/**
	 * 
	 * @param offsetX
	 * @param y
	 * @param text
	 * @param border
	 */
	public void drawStringBorder(String text, float x, float y) {

		if((text==null)||(text.isEmpty())) {
			return;
		}

		Font f = getFont();

		FontRenderContext frc = screen.getFontRenderContext();

		TextLayout tl = new TextLayout(text, f, frc);

		Shape sha = tl.getOutline(AffineTransform.getTranslateInstance(x,y));        

		Color standardColor = screen.getColor();
		
		screen.setColor(shadowColor);
		screen.draw(sha);

		screen.setColor(standardColor);
		screen.fill(sha);

	}
	
	/**
	 * 
	 * @param offsetX
	 * @param y
	 * @param text
	 * @param border
	 */
	public void drawStringBorderX(String text, float y) {

		int x = centralizeTextX(text);
		
		drawStringBorder(text, x, y);		

	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param text
	 */
	public void drawShadow(int x, int y, String text) {
		drawShadow(x, y, text, shadowColor);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param text
	 */
	public void drawShadow(float x, float y, String text) {
		this.drawShadow(x, y, text, shadowColor);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param text
	 * @param shadowColor
	 */
	public void drawShadow(int x, int y, String text, Color shadowColor) {

		if((text!=null)&&(!text.isEmpty())) {

			Color lastColor = screen.getColor();

			screen.setColor(shadowColor);
			screen.drawString(text,x+1,y+1);
			screen.setColor(lastColor);
			screen.drawString(text,x,y);
		}
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param text
	 * @param shadowColor
	 */
	public void drawShadow(float x, float y, String text, Color shadowColor) {
		this.drawShadow((int)x, (int)y, text, shadowColor);
	}

	/**
	 * 
	 * @param y
	 * @param text
	 */
	public void drawStringShadowX(int y, String text) {
		if((text!=null)&&(!text.isEmpty())) {

			FontMetrics fm = screen.getFontMetrics();

			int x = centralizeTextX(text);
			int fy = y+fm.getHeight();

			drawShadow(x, fy, text);

		}
	}
	
	private int centralizeTextX(String text) {
		return centralizeTextX(text, 0, width);
	}
	
	private int centralizeTextX(String text, int x, int w) {
		FontMetrics fm = screen.getFontMetrics();

		int textWidth = fm.stringWidth(text);
		
		int dx = x+w/2 - textWidth/2;
		
		return dx;
	}
	
	private int centralizeTextY(String text) {
		return centralizeTextY(text, 0, height);
	}
	
	private int centralizeTextY(String text, int y, int h) {
		FontMetrics fm = screen.getFontMetrics();

		int ascent = fm.getMaxAscent();
		int descent= fm.getMaxDescent();
		
		int dy = y+h/2 - descent/2 + ascent/2;
		
		return dy;
	}
	

	/**
	 * 
	 * @param y
	 * @param text
	 */
	public void drawStringShadowX(float y, String text) {
		this.drawStringShadowX((int)y, text);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param text
	 */
	public void write(float x, float y, String text) {
		if((text!=null)&&(!text.isEmpty())) {
			screen.drawString(text,x,y);
		}
	}

	public void write(String text, GeometricLayer layer) {
		if((text!=null)&&(!text.isEmpty())) {
			drawString(layer.getX(), layer.getY(), layer.getW(), layer.getH(), text);
		}
	}

	public void writeShadow(String text, GeometricLayer layer) {
		if((text!=null)&&(!text.isEmpty())) {
			drawStringShadow(layer.getX(), layer.getY(), layer.getW(), layer.getH(), text);
		}
	}



	/**
	 * 
	 * @param y
	 * @param text
	 */
	public void escreveX(int y, String text) {

		writeX(0,y,text, false);
	}

	/**
	 * 
	 * @param offsetX
	 * @param y
	 * @param text
	 */
	public void escreveX(int offsetX, int y, String text) {

		writeX(offsetX, y,text, false);
	}

	/**
	 * 
	 * @param y
	 * @param text
	 * @param borda
	 */
	public void writeX(float y, String text, boolean borda) {
		writeX(0,y,text, borda);
	}

	public void writeX(float y, String text) {
		writeX(0,y,text, false);
	}

	/*
	 * Write Methods
	 */

	/**
	 * 
	 * @param offsetX
	 * @param y
	 * @param text
	 */
	public void escreveXCustom(int offsetX, int y, String text) {

		screen.drawString(text, 200, y);

		/*
		FontMetrics fm = screen.getFontMetrics (f);

		int msg_width = fm.stringWidth(text);

		int x = largura/2 - msg_width/2;
		x += offsetX;

		TextLayout tl = new TextLayout(text, f, frc);

		Shape sha = tl.getOutline(AffineTransform.getTranslateInstance(x,y));        

		screen.setStroke(new BasicStroke(2.666f));
		screen.setColor(Color.black);
		screen.draw(sha);
		Color c = new Color(190,10,10); 
		screen.setColor(c);
		screen.fill(sha);
		 */

	}

	/**
	 * 
	 * @param img
	 * @param dx1
	 * @param dy1
	 * @param dx2
	 * @param dy2
	 * @param sx1
	 * @param sy1
	 * @param sx2
	 * @param sy2
	 * @param observer
	 */
	public void drawImage( Image img, int dx1, int dy1,int dx2, int dy2, int sx1 , int sy1, int sx2, int sy2, ImageObserver observer ) {
		screen.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer);
	}

	public void drawImage( Image img, float dx1, float dy1, float dx2, float dy2, float sx1 , float sy1, float sx2, float sy2, ImageObserver observer ) {
		drawImage(img, (int)dx1, (int)dy1, (int)dx2, (int)dy2, (int)sx1, (int)sy1, (int)sx2, (int)sy2, observer);
	}


	public Graphics2D getGraphics() {
		return screen;
	}

	/**
	 * 
	 * @param layer
	 */
	public void drawRect(GeometricLayer layer) {
		drawRect(layer.getX(), layer.getY(), layer.getW(), layer.getH());
	}

	/**
	 * 
	 * @param layer
	 */
	public void drawRect(Layer layer) {

		AffineTransform transform = layer.getTransform();

		if(transform == null)
			drawRect(layer.getX(),layer.getY(),layer.getW(),layer.getH());
		else {
			setTransform(transform);
			drawRect(layer.getX(),layer.getY(),layer.getW(),layer.getH());
			resetTransform();
		}
	}

	/**
	 * 
	 * @param layer
	 */
	public void fillOval(GeometricLayer layer) {
		screen.fillOval((int)layer.getX(), (int)layer.getY(), (int)layer.getW(), (int)layer.getH());
	}

	/**
	 * 
	 * @param layer
	 * @param startAngle
	 * @param arcAngle
	 */
	public void fillArc(GeometricLayer layer, int startAngle, int arcAngle) {
		screen.fillArc((int)layer.getX(), (int)layer.getY(), (int)layer.getW(), (int)layer.getH(), startAngle, arcAngle);
	}

	/** Funções Delegadas */
	/**
	 * 
	 * @param width
	 */
	public void setLineWidth(float width) {
		screen.setStroke(new BasicStroke(width));
	}

	public AffineTransform getTransform() {
		return screen.getTransform();
	}

	/**
	 * 
	 * @param tx
	 */
	public void setTransform(AffineTransform tx) {
		//Avoid Java Bug
		if(screen != null) {
			screen.setTransform(tx);
		}
	}

	/**
	 * 
	 * @param tx
	 */
	public void transform(AffineTransform tx) {
		//Avoid Java Bug
		if(screen!=null) {
			screen.transform(tx);
		}
	}

	public void resetTransform() {
		setTransform(RESET_TRANSFORM);	
	}

	/**
	 * Set basic stroke with width 1f 
	 */
	public void resetStroke() {
		screen.setStroke(new BasicStroke(1f));
	}

	/**
	 * 
	 * @param stroke
	 */
	public void setStroke(Stroke stroke) {
		screen.setStroke(stroke);
	}

	/**
	 * 
	 * @param font
	 */
	public void setFont(Font font) {
		screen.setFont(font);
	}

	public Font getFont() {
		return screen.getFont();
	}

	public FontRenderContext getFontRenderContext() {
		return screen.getFontRenderContext();
	}

	/**
	 * 
	 * @param color
	 */
	public void setColor(int color) {
		screen.setColor(new Color(color));
	}

	/**
	 * 
	 * @param color
	 */
	public void setColor(Color color) {
		screen.setColor(color);
	}

	public void setFontSize(float size) {
		screen.setFont(screen.getFont().deriveFont(size));
	}

	/**
	 * 
	 * @param percent
	 */
	public void setAlpha(int percent) {
		float alpha = (float)percent/100;
		screen.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
	}

	public void setComposite(AlphaComposite composite) {
		screen.setComposite(composite);
	}

	public void setClearAlpha(int percent) {
		float alpha = (float)percent/100;
		screen.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR, alpha));
	}	

	/**
	 * 
	 * @param opacity
	 */
	public void setOpacity(int opacity) {

		float a = (float)opacity/255;

		screen.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, a));

	}

	public void resetOpacity() {

		float a = 1f;

		screen.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, a));

	}

	/**
	 * 
	 * @param img
	 * @param x
	 * @param y
	 */
	public void drawImage(Image img, int x, int y) {
		screen.drawImage(img, x, y, null);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param startAngle
	 * @param arcAngle
	 */
	public void drawArc(int x, int y, int w, int h, int startAngle, int arcAngle) {
		screen.drawArc(x, y, w, h, startAngle, arcAngle);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param startAngle
	 * @param arcAngle
	 */
	public void drawArc(float x, float y, float w, float h, int startAngle, int arcAngle) {
		this.drawArc((int)x, (int)y, (int)w, (int)h, startAngle, arcAngle);
	}

	/**
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public void drawLine(int x1,int y1,int x2,int y2) {
		screen.drawLine(x1, y1, x2, y2);
	}

	/**
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public void drawLine(float x1,float y1,float x2,float y2) {
		this.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
	}

	/**
	 * 
	 * @param p
	 * @param q
	 */
	public void drawLine(Point2D p, Point2D q) {
		screen.drawLine((int)p.getX(), (int)p.getY(), (int)q.getX(), (int)q.getY());
	}

	/**
	 * 
	 * @param polygon
	 */
	public void drawPolygon(Polygon polygon) {
		screen.drawPolygon(polygon);
	}

	/**
	 * 
	 * @param polygon
	 */
	public void fillPolygon(Polygon polygon) {
		screen.fillPolygon(polygon);
	}

	/**
	 * 
	 * @param layer
	 */
	public void fillRect(GeometricLayer layer) {
		fillRect(layer.getX(),layer.getY(),layer.getW(),layer.getH());
	}

	public void fillRect(Layer layer) {
		
		AffineTransform transform = layer.getTransform();

		if(transform == null)
			fillRect(layer.getX(),layer.getY(),layer.getW(),layer.getH());
		else {
			setTransform(transform);
			fillRect(layer.getX(),layer.getY(),layer.getW(),layer.getH());
			resetTransform();
		}
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void fillRect(int x, int y, int w, int h) {
		screen.fillRect(x,y,w,h);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void fillRect(float x, float y, float w, float h) {
		screen.fillRect((int)x,(int)y,(int)w,(int)h);
	}


	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param raised
	 */
	public void fill3DRect(int x, int y, int w, int h, boolean raised) {
		screen.fill3DRect(x, y, w, h, raised);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param raised
	 */
	public void fill3DRect(float x, float y, float w, float h, boolean raised) {
		this.fill3DRect((int)x, (int)y, (int)w, (int)h, raised);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param startAngle
	 * @param arcAngle
	 */
	public void fillArc(int x, int y, int w, int h, int startAngle, int arcAngle) {
		screen.fillArc(x, y, w, h, startAngle, arcAngle);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param startAngle
	 * @param arcAngle
	 */
	public void fillArc(float x, float y, float w, float h, int startAngle, int arcAngle) {
		this.fillArc((int)x, (int)y, (int)w, (int)h, startAngle, arcAngle);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void drawRect(int x, int y, int w, int h) {
		screen.drawRect(x,y,w,h);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void drawRect(double x, double y, double w, double h) {
		screen.drawRect((int)x,(int)y,(int)w,(int)h);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param arcWidth
	 * @param arcHeight
	 */
	public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
		screen.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param arcWidth
	 * @param arcHeight
	 */
	public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
		screen.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void drawOval(int x, int y, int w, int h ) {
		screen.drawOval(x,y,w,h);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void drawOval(float x, float y, float w, float h ) {
		this.drawOval((int)x,(int)y,(int)w,(int)h);
	}

	/**
	 * 
	 * @param cx
	 * @param cy
	 * @param radius
	 */
	public void drawCircle(int cx, int cy, int radius ) {
		screen.drawOval(cx-radius, cy-radius, radius*2, radius*2);
	}

	/**
	 * 
	 * @param cx
	 * @param cy
	 * @param radius
	 */
	public void drawCircle(float cx, float cy, float radius ) {
		this.drawCircle((int)cx, (int)cy, (int)radius);
	}

	/**
	 * 
	 * @param cx
	 * @param cy
	 * @param radius
	 */
	public void drawCircle(double cx, double cy, double radius ) {
		this.drawCircle((int)cx, (int)cy, (int)radius);
	}

	/**
	 * 
	 * @param point
	 * @param radius
	 */
	public void drawCircle(Point2D point, int radius ) {
		screen.drawOval((int)point.getX()-radius, (int)point.getY()-radius, radius*2, radius*2);
	}

	/**
	 * 
	 * @param cx
	 * @param cy
	 * @param radius
	 */
	public void fillCircle(int cx, int cy, int radius ) {
		screen.fillOval(cx-radius, cy-radius, radius*2, radius*2);
	}

	/**
	 * 
	 * @param cx
	 * @param cy
	 * @param radius
	 */
	public void fillCircle(float cx, float cy, float radius ) {
		screen.fillOval((int)(cx-radius), (int)(cy-radius), (int)(radius*2), (int)(radius*2));
	}

	/**
	 * 
	 * @param cx
	 * @param cy
	 * @param radius
	 */
	public void fillCircle(double cx, double cy, double radius ) {
		screen.fillOval((int)(cx-radius), (int)(cy-radius), (int)(radius*2), (int)(radius*2));
	}

	/**
	 * 
	 * @param point
	 * @param radius
	 */
	public void fillCircle(Point2D point, int radius ) {
		screen.fillOval((int)point.getX()-radius, (int)point.getY()-radius, radius*2, radius*2);
	}
	
	/**
	 * 
	 * @param point
	 * @param radius
	 */
	public void fillCircle(PointInt2D point, int radius) {
		screen.fillOval(point.getX()-radius, point.getY()-radius, radius*2, radius*2);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void fillOval(int x, int y, int w, int h ) {
		screen.fillOval(x, y, w, h);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void fillOval(float x, float y, float w, float h ) {
		this.fillOval((int)x, (int)y, (int)w, (int)h);
	}

	/**
	 * 
	 * @param text
	 * @param x
	 * @param y
	 */
	public void drawString(String text, int x, int y) {
		screen.drawString(text, x, y);
	}

	/**
	 * 
	 * @param shape
	 */
	public void draw(Shape shape) {
		screen.draw(shape);
	}

	/**
	 * 
	 * @param shape
	 */
	public void fill(Shape shape) {
		screen.fill(shape);
	}

	public FontMetrics getFontMetrics() {
		return screen.getFontMetrics();
	}

	/*public void setGraphics(GLGraphics2D graphics) {		
		this.screen = graphics;
		this.screen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}*/

	public BufferedImage getBimg() {
		return vimg.getSnapshot();
	}

	public VolatileImage getVimg() {
		return vimg;
	}

	/**
	 * 
	 * @param startX
	 * @param startY
	 * @param w
	 * @param h
	 * @param rgbArray
	 * @param offset
	 * @param scansize
	 */
	/*public void setRGB(int startX, int startY, int w, int h, int[] rgbArray, int offset, int scansize) {

		vimg..setRGB(startX, startY, w, h, rgbArray, offset, scansize);
	}*/

	public void drawImage(BufferedImage image, int x, int y) {
		screen.drawImage(image, x, y, null);
	}

	public void drawImage(BufferedImage image, float x, float y) {
		screen.drawImage(image, (int)x, (int)y, null);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public void translate(int x, int y) {
		screen.translate(x, y);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public void translate(double x, double y) {
		screen.translate(x, y);
	}

	public void rotate(double angle) {
		screen.rotate(angle);
	}

	public void setBackground(Color color) {
		screen.setBackground(color);
	}

	public void clearRect(int x, int y, int width, int height) {
		screen.clearRect(x, y, width, height);
	}

	public void setPaint(Paint paint) {
		screen.setPaint(paint);
	}

	public void resetPaint() {
		screen.setPaint(screen.getColor());
	}
	
	public void setShadowColor(Color shadowColor) {
		this.shadowColor = shadowColor;
	}
	
	public void dispose() {
		screen.dispose();
	}

	public void setCamera(Camera camera) {
		camera.resetImage();
		setImage(camera.getBuffer());		
	}

	public void resetCamera(Camera camera) {
		resetImage();
	}

	public void drawArrow(Point2D p, Point2D q, int arrowSize) {
		double pq = p.distance(q);
		
		int arrowAngle = 30;
		
		Point2D p1 = p.distantPoint(q, pq+arrowSize);
		Point2D p2 = new Point2D(p1.getX(), p1.getY());
		
		p1.rotate(q, 180-arrowAngle);
		p2.rotate(q, 180+arrowAngle);
		
		drawLine(p, q);
		drawLine(q, p1);
		drawLine(q, p2);
	}
	
	public void drawArrow(Point2D p, Point2D q) {
		drawArrow(p, q, 25);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
		
}