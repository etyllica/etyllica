package examples.etyllica.animation.sprite;

import br.com.etyllica.Etyllica;
import br.com.etyllica.commons.context.Application;

public class SpriteAnimationExample extends Etyllica {

    private static final long serialVersionUID = 1L;

    public SpriteAnimationExample() {
        super(100, 100);
    }

    public static void main(String[] args) {
        SpriteAnimationExample app = new SpriteAnimationExample();
        app.init();
    }

    @Override
    public Application startApplication() {
        initialSetup("");
        return new SpriteAnimationTutorial(w, h);
    }

}
