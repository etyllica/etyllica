package br.com.etyllica.layer;

import br.com.etyllica.commons.Drawable;
import br.com.etyllica.commons.collision.CollisionDetector;
import br.com.etyllica.commons.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.linear.Point2D;

import java.awt.geom.AffineTransform;

/**
 * @author yuripourre
 */

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
     * if layer is visible
     */
    protected boolean visible = true;

    public Layer() {
        super();
    }

    public Layer(int x, int y) {
        super();

        this.x = x;
        this.y = y;
    }

    public Layer(int x, int y, int w, int h) {
        super(x, y, w, h);
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
        this.originX = (float) utilWidth() / 2;
        this.originY = (float) utilHeight() / 2;
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
                return CollisionDetector.colideRectPoint(getX(), getY(), utilWidth(), utilHeight(), mx, my);
            } else {
                int rw = (int) (utilWidth() * getScaleX());
                int rh = (int) (utilHeight() * getScaleY());
                return CollisionDetector.colideRectPoint(getX() - rw / 4, getY() - rh / 4, rw, rh, mx, my);
            }
        } else {
            if (scaleX == 1 && scaleY == 1) {
                return CollisionDetector.colideRectPoint(getX(), getY(), utilWidth() / 2, utilHeight() / 2, getAngle(), mx, my);
            } else {
                int rw = (int) (utilWidth() * getScaleX());
                int rh = (int) (utilHeight() * getScaleY());
                return CollisionDetector.colideRectPoint(getX() - rw / 4, getY() - rh / 4, rw / 2, rh / 2, getAngle(), mx, my);
            }
        }
    }

    public AffineTransform getTransform() {
        return getTransform(0, 0);
    }

    public AffineTransform getTransform(float offsetX, float offsetY) {
        AffineTransform transform = new AffineTransform();

        float px = getX() + offsetX;
        float py = getY() + offsetY;

        transform.translate(px + originX, py + originY);

        // Scale
        if (scaleX != 1 || scaleY != 1) {
            transform.scale(scaleX, scaleY);
        }

        // Rotate
        if (angle != 0) {
            if ((scaleY > 0 && scaleX > 0)
                    || (scaleX < 0 && scaleY < 0)) {
                transform.rotate(Math.toRadians(angle));
            } else {
                transform.rotate(Math.toRadians(-angle));
            }
        }

        // Move to origin (centered)
        transform.translate(-px - originX, -py - originY);

        return transform;
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

    /**
     * Code found at: http://stackoverflow.com/questions/5650032/collision-detection-with-rotated-rectangles
     */
    public boolean colideRectPoint(double px, double py) {
        int rectWidth = utilWidth();
        int rectHeight = utilHeight();
        int offsetX = 0;
        int offsetY = 0;

        if (getScaleX() != 1) {
            rectWidth *= getScaleX();
            offsetX = (int) (utilWidth() * (1 - getScaleX())) / 2;
        }

        if (getScaleY() != 1) {
            rectHeight *= getScaleY();
            offsetY = (int) (utilHeight() * (1 - getScaleY())) / 2;
        }

        int rectCenterX = getX() + offsetX + rectWidth / 2;
        int rectCenterY = getY() + offsetY + rectHeight / 2;

        double rectRotation = Math.toRadians(-getAngle());

        return CollisionDetector.colideRectPoint(rectWidth, rectHeight, rectRotation, rectCenterX, rectCenterY, px, py);
    }

    public boolean colideRectPoint(Point2D point) {
        int rectCenterX = getX() + utilWidth() / 2;
        int rectCenterY = getY() + utilHeight() / 2;
        int rectWidth = utilWidth();
        int rectHeight = utilHeight();

        double rectRotation = Math.toRadians(-getAngle());

        return CollisionDetector.colideRectPoint(rectWidth, rectHeight, rectRotation, rectCenterX, rectCenterY, point.getX(), point.getY());
    }

    public boolean colide(Layer b) {
        if (getAngle() == 0 && b.getAngle() == 0) {
            return ((getX() + utilWidth() / 2 - b.getX() + b.utilWidth() / 2) * 2 < (utilWidth() + b.utilWidth())) &&
                    ((getY() + utilHeight() / 2 - b.getY() + b.utilHeight() / 2) * 2 < (utilHeight() + b.utilHeight()));
        } else {
            return CollisionDetector.colidePolygon(getX(), getY(), utilWidth(), utilHeight(), getAngle(),
                    b.getX(), b.getY(), b.utilWidth(), b.utilHeight(), b.getAngle());
        }
    }
}
