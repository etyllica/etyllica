package examples.etyllica.particle.basic;

import br.com.etyllica.Etyllica;
import br.com.etyllica.commons.context.Application;

public class ParticleExample extends Etyllica {

    private static final long serialVersionUID = 1L;

    public ParticleExample() {
        super(800, 600);
    }

    public static void main(String[] args) {
        ParticleExample app = new ParticleExample();
        app.init();
    }

    @Override
    public Application startApplication() {
        //initialSetup("../../");
        return new ParticleApplication(w, h);
    }

}
