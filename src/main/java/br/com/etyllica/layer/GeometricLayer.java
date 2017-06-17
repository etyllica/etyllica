package br.com.etyllica.layer;

import br.com.etyllica.commons.Movable;
import br.com.etyllica.commons.collision.CollisionDetector;
import br.com.etyllica.linear.PointInt2D;

public class GeometricLayer extends PointInt2D implements Movable {

    /**
     * Layer's width
     */
    protected int w = 0;

    /**
     * Layer's height
     */
    protected int h = 0;

    public GeometricLayer() {
        super();
    }

    public GeometricLayer(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        //setLocation(x, y);
    }

    public GeometricLayer(int x, int y, int w, int h) {
        super();
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        //setBounds(x, y, w, h);
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public void setBounds(int x, int y, int w, int h) {
        setLocation(x, y);
        setSize(w, h);
    }

    /**
     * @param x
     * @param y
     */
    public void setCoordinates(int x, int y) {
        setLocation(x, y);
    }

    /**
     * @param offsetX
     */
    public void offsetX(int offsetX) {
        setX(this.x + offsetX);
    }

    /**
     * @param offsetY
     */
    public void offsetY(int offsetY) {
        setY(this.y + offsetY);
    }

    /**
     * @param offsetX
     * @param offsetY
     */
    public void offset(int offsetX, int offsetY) {
        offsetX(offsetX);
        offsetY(offsetY);
    }

    /**
     * @param w
     * @param h
     */
    public void setSize(int w, int h) {
        setW(w);
        setH(h);
    }

	/*
	 * Centralization Methods
	 */

    /**
     * @param x
     * @param y
     * @param w
     * @param h
     */
    public void centralize(int x, int y, int w, int h) {
        centralizeX(x, x + w);
        centralizeY(y, y + h);
    }

    /**
     * @param layer
     */
    public void centralize(GeometricLayer layer) {
        centralizeX(layer);
        centralizeY(layer);
    }

    /**
     * @param layer
     */
    public void centralizeX(GeometricLayer layer) {
        centralizeX(layer.getX(), layer.getX() + layer.utilWidth());
    }

    /**
     * @param startX
     * @param endX
     * @return
     */
    public int centralizeX(int startX, int endX) {
        int x = (((startX + endX) / 2) - (utilWidth() / 2));
        setX(x);

        return x;
    }

    /**
     * @param layer
     */
    public void centralizeY(GeometricLayer layer) {
        centralizeY(layer.getY(), layer.getY() + layer.utilHeight());
    }

    /**
     * @param startY
     * @param endY
     * @return
     */
    public int centralizeY(int startY, int endY) {
        int y = (((startY + endY) / 2) - (utilHeight() / 2));
        setY(y);

        return y;
    }

    public void copy(GeometricLayer layer) {
        setX(layer.getX());
        setY(layer.getY());
        setW(layer.getW());
        setH(layer.getH());
    }
		
	/*
	 * Colision Methods
	 */

    public boolean colideRectRect(GeometricLayer b) {
        return colideRectRect(b.getX(), b.getY(), b.utilWidth(), b.utilHeight());
    }

    /**
     * @param bx
     * @param by
     * @param bw
     * @param bh
     * @return
     */
    public boolean colideRectRect(int bx, int by, int bw, int bh) {
        return CollisionDetector.colideRectRect(getX(), getY(), utilWidth(), utilHeight(), bx, by, bw, bh);
    }

    public boolean colideRectPoint(int bx, int by) {
        return CollisionDetector.colideRectPoint(getX(), getY(), utilWidth(), utilHeight(), bx, by);
    }

    /**
     * @param bx
     * @param by
     * @param bw
     * @param bh
     * @return
     */
    public boolean colideCircleCircle(int bx, int by, int bw, int bh) {
        return CollisionDetector.colideCircleCircle(getX(), getY(), utilWidth(), utilHeight(), bx, by, bw, bh);
    }

    public boolean colideCircleCircle(GeometricLayer layer) {
        return colideCircleCircle(layer.getX(), layer.getY(), layer.utilWidth(), layer.utilHeight());
    }

    /**
     * @param px
     * @param py
     * @return
     */
    public boolean colideCirclePoint(int px, int py) {
        int radius = utilWidth() / 2;
        int cx = getX() + radius;
        int cy = getY() + radius;
        return CollisionDetector.colideCirclePoint(cx, cy, radius, px, py);
    }

    public int utilWidth() {
        return getW();
    }

    public int utilHeight() {
        return getH();
    }

}