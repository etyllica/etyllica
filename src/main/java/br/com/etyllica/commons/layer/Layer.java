package br.com.etyllica.commons.layer;

import br.com.etyllica.commons.Drawable;
import br.com.etyllica.commons.collision.CollisionDetector;
import br.com.etyllica.commons.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphics;

public class Layer extends GeometricLayer implements Drawable {

    /**
     * Opacity
     */
    protected int opacity = 255;

    /**
     * Angle in degrees
     */
    protected double angle = 0;

    /**
     * Reference point to scale and rotation
     */
    protected float originX, originY;

    /**
     * Scale factors
     */
    protected double scaleX = 1, scaleY = 1;

    /**
     * If layer is visible
     */
    protected boolean visible = true;

    public Layer() {
        super();
    }

    public Layer(int x, int y) {
        super(x, y);
    }

    public Layer(int x, int y, int w, int h) {
        super(x, y, w, h);
        originX = w / 2;
        originY = h / 2;
    }

    public int getOpacity() {
        return opacity;
    }

    /**
     * @param opacity
     */
    public void setOpacity(int opacity) {
        this.opacity = opacity;
    }

    public double getAngle() {
        return angle;
    }

    /**
     * @param angle
     */
    public void setAngle(double angle) {
        this.angle = angle;
    }

    public float getOriginX() {
        return originX;
    }

    public float getOriginY() {
        return originY;
    }

    /**
     * @param originX
     * @param originY
     */
    public void setOrigin(float originX, float originY) {
        this.originX = originX;
        this.originY = originY;
    }

    public void setOriginCenter() {
        this.originX = (float) getW() / 2;
        this.originY = (float) getH() / 2;
    }

    /**
     * @param offset
     */
    public void setOffsetAngle(double offset) {
        setAngle(angle + offset);
    }

    public double getScaleX() {
        return scaleX;
    }

    public double getScaleY() {
        return scaleY;
    }

    /**
     * @param scaleX
     */
    public void setScaleX(double scaleX) {
        this.scaleX = scaleX;
    }

    /**
     * @param scaleY
     */
    public void setScaleY(double scaleY) {
        this.scaleY = scaleY;
    }

    public void setScale(double scale) {
        this.scaleX = scale;
        this.scaleY = scale;
    }

    /**
     * @param visible
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }

    /**
     * Method to turn a Layer visible
     */
    public void show() {
        visible = true;
    }

    /**
     * Method to turn a Layer invisible
     */
    public void hide() {
        visible = false;
    }

    public void swapVisible() {
        visible = !visible;
    }

    /**
     * @param event
     * @return
     */
    public boolean onMouse(PointerEvent event) {
        return onMouse(event.getX(), event.getY());
    }

    /**
     * @param mx
     * @param my
     * @return
     */
    public boolean onMouse(int mx, int my) {
        if (angle == 0) {
            if (scaleX == 1 && scaleY == 1) {
                return CollisionDetector.colideRectPoint(getX(), getY(), getW(), getH(), mx, my);
            } else {
                int rw = (int) (getW() * getScaleX());
                int rh = (int) (getH() * getScaleY());
                return CollisionDetector.colideRectPoint(getX() - rw / 4, getY() - rh / 4, rw, rh, mx, my);
            }
        } else {
            if (scaleX == 1 && scaleY == 1) {
                return CollisionDetector.colideRectPoint(getX(), getY(), getW() / 2, getH() / 2, getAngle(), mx, my);
            } else {
                int rw = (int) (getW() * getScaleX());
                int rh = (int) (getH() * getScaleY());
                return CollisionDetector.colideRectPoint(getX() - rw / 4, getY() - rh / 4, rw / 2, rh / 2, getAngle(), mx, my);
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        // TODO Auto-generated method stub
    }

    public void simpleDraw(Graphics g) {
        // TODO Auto-generated method stub
    }

    public void copy(Layer layer) {
        super.copy(layer);
        setOrigin(layer.getOriginX(), layer.getOriginY());
        setScaleX(layer.getScaleX());
        setScaleY(layer.getScaleY());
        setAngle(layer.getAngle());
        setOpacity(layer.getOpacity());
    }

    @Override
    public boolean colideRectPoint(int px, int py) {
        int rectWidth = (int) (getW() * getScaleX());
        int rectHeight = (int) (getH() * getScaleY());

        return CollisionDetector.colideRectPoint(getX(), getY(), rectWidth, rectHeight, angle, px, py);
    }

    public boolean colide(Layer b) {
        if (getAngle() == 0 && b.getAngle() == 0) {
            return CollisionDetector.colideRectRect(getX(), getY(), getW(), getH(),
                    b.getX(), b.getY(), b.getW(), b.getH());
        } else {
            return CollisionDetector.colidePolygon(getX(), getY(), getW(), getH(), getAngle(),
                    b.getX(), b.getY(), b.getW(), b.getH(), b.getAngle());
        }
    }
}
