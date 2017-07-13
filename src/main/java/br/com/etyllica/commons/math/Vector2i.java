package br.com.etyllica.commons.math;

public class Vector2i {

    protected int x = 0;
    protected int y = 0;

    public Vector2i() {
        super();
    }

    public Vector2i(int x, int y) {
        super();

        setLocation(x, y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setLocation(int x, int y) {
        setX(x);
        setY(y);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double angle(double px, double py) {
        double deltaX = px - x;
        double deltaY = py - y;

        double angleInDegrees = Math.atan2(deltaY, deltaX) * 180 / Math.PI;
        return angleInDegrees;
    }

    public void rotate(double cx, double cy, double degreeAngle) {
        double angle = Math.toRadians(degreeAngle);
        double nx = cx + (x - cx) * Math.cos(angle) - (y - cy) * Math.sin(angle);
        double ny = cy + (x - cx) * Math.sin(angle) + (y - cy) * Math.cos(angle);

        x = (int) nx;
        y = (int) ny;
    }

    public double distance(Vector2i vector) {
        return distance(this.x, this.y, vector.x, vector.y);
    }

    public double distance(double px, double py) {
        return distance(px, py, this.x, this.y);
    }

    public static double distance(double px, double py, double qx, double qy) {
        double difX = px - qx;
        double difY = py - qy;

        double dist = Math.sqrt(Math.pow(difX, 2) + Math.pow(difY, 2));

        return dist;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vector2i other = (Vector2i) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }
}
