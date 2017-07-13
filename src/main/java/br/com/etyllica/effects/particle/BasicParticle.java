package br.com.etyllica.effects.particle;

import br.com.etyllica.commons.particle.Particle;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.layer.ImageLayer;


public class BasicParticle extends Particle {

    private ImageLayer layer;

    public BasicParticle(int x, int y) {
        super(x, y);

        layer = new ImageLayer(x, y, "particle.png");
    }

    public void draw(Graphics g) {
        layer.draw(g);
    }

    public void setX(int x) {
        this.x = x;
        layer.setX(x);
    }

    public void setY(int y) {
        this.y = y;
        layer.setY(y);
    }

    @Override
    public void update(long now) {
        super.update(now);
        layer.setLocation((int)x, (int)y);
    }

    public ImageLayer getLayer() {
        return layer;
    }
}
