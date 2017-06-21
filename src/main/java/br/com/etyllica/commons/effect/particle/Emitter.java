package br.com.etyllica.commons.effect.particle;

import br.com.etyllica.commons.Drawable;
import br.com.etyllica.commons.Updatable;
import br.com.etyllica.core.graphics.Graphics;

import java.util.ArrayList;
import java.util.List;

public abstract class Emitter<T extends Particle> implements Updatable, Drawable {

    protected int x, y;

    private long lastUpdate = 0;
    private long lastParticleUpdate = 0;

    protected float gravity = 0.1f;
    protected long speed = 40;
    protected int maxParticles = 15;
    protected long particleRate = 1500;

    protected int maxDistance = 200;

    protected List<T> particles = new ArrayList<T>();

    public Emitter(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void update(long now) {
        long diff = now - lastUpdate;

        if (now - lastParticleUpdate >= particleRate) {
            if (particles.size() >= maxParticles) {
                particles.get(0).setActive(false);
            }

            particles.add(createParticle(now));

            lastParticleUpdate = now;
        }

        if (diff >= speed) {
            for (Particle particle : particles) {
                particle.addVelocity(0, gravity);
                particle.update(now);
                if (stopParticle(particle)) {
                    particle.setActive(false);
                }
            }

            lastUpdate = now;
        }
    }

    protected abstract T createParticle(long now);

    @Override
    public void draw(Graphics g) {
        drawEmitter(g);
        drawParticles(g);
    }

    public void drawParticles(Graphics g) {
        for (int i = particles.size() - 1; i >= 0; i--) {
            T particle = particles.get(i);
            if (!particle.isActive()) {
                particles.remove(i);
                continue;
            }
            drawParticle(particle, g);
        }
    }

    public void setSpeed(int speed) {
        this.speed = 1000 / speed;
    }

    public boolean stopParticle(Particle particle) {
        float dx = x - particle.x;
        float dy = y - particle.y;

        return maxDistance * maxDistance < dx * dx + dy * dy;
    }

    protected abstract void drawParticle(T particle, Graphics g);

    public abstract void drawEmitter(Graphics g);

}
