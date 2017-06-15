package examples.etyllica.tutorial14;


import br.com.etyllica.commons.animation.AnimationModule;
import br.com.etyllica.commons.animation.script.FrameAnimation;
import br.com.etyllica.context.SceneApplication;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.layer.AnimatedLayer;

public class SpriteAnimationTutorial extends SceneApplication {

    public SpriteAnimationTutorial(int w, int h) {
        super(w, h);
    }

    private AnimatedLayer fruit;

    @Override
    public void load() {
        fruit = new AnimatedLayer(20, 60, 16, 16, "fruits.png");
        fruit.setFrames(3);
        //fruit.setOscilate(true);
        fruit.setSpeed(500);

        AnimationModule.getInstance().add(new FrameAnimation(fruit));

        session.put("hello", "my friend");

        loading = 100;
    }

    @Override
    public void draw(Graphics g) {
        fruit.draw(g);
    }

}
