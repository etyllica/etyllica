package br.com.etyllica.commons.event;


public class GestureEvent {
    int pointer;
    int x;
    int y;

    public GestureEvent() {
        this(0, 0, 0);
    }

    public GestureEvent(int pointer, int x, int y) {
        this.pointer = pointer;
        this.x = x;
        this.y = y;
    }

    public double distance(int bx, int by) {
        float dx = bx - x;
        float dy = by - y;
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    public double distance(GestureEvent b) {
        return distance(b.x, b.y);
    }
}
