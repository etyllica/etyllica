package br.com.etyllica.commons.effect.particle;

import br.com.etyllica.commons.Updatable;

public abstract class Particle implements Updatable {

    protected float x;
    protected float y;
    protected float xVelocity;
    protected float yVelocity;

    protected boolean active = true;

    public Particle(float x, float y) {
        this.x = x;
        this.y = y;

        this.active = true;
    }

    public Particle(float x, float y, float xVelocity, float yVelocity) {
        this.x = x;
        this.y = y;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;

        this.active = true;
    }

    public void addVelocity(float xAcceleration, float yAcceleration) {
        this.xVelocity += xAcceleration;
        this.yVelocity += yAcceleration;
    }

    public void update(long now) {
        x += xVelocity;
        y += yVelocity;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(float xVelocity) {
        this.xVelocity = xVelocity;
    }

    public float getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(float yVelocity) {
        this.yVelocity = yVelocity;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
