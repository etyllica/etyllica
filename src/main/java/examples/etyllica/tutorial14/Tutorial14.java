package examples.etyllica.tutorial14;

import br.com.etyllica.Etyllica;
import br.com.etyllica.commons.context.Application;

public class Tutorial14 extends Etyllica {

    private static final long serialVersionUID = 1L;

    public Tutorial14() {
        super(100, 100);
    }

    public static void main(String[] args) {
        Tutorial14 app = new Tutorial14();
        app.init();
    }

    @Override
    public Application startApplication() {
        initialSetup("");
        return new SpriteAnimationTutorial(w, h);
    }

}
