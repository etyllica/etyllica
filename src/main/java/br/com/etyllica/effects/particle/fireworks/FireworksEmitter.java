package br.com.etyllica.effects.particle.fireworks;

import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.effects.particle.BasicEmitter;

import java.awt.*;

public class FireworksEmitter extends BasicEmitter {

    public FireworksEmitter(int x, int y) {
        super(x, y, 20);
        variance = 360;
    }

    @Override
    public void drawEmitter(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, radius, radius);
    }

}
