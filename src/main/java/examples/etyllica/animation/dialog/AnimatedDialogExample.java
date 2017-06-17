package examples.etyllica.animation.dialog;

import br.com.etyllica.Etyllica;
import br.com.etyllica.commons.context.Application;

public class AnimatedDialogExample extends Etyllica {

    private static final long serialVersionUID = 1L;

    public AnimatedDialogExample() {
        super(800, 600);
    }

    public static void main(String[] args) {
        AnimatedDialogExample app = new AnimatedDialogExample();
        app.init();
    }

    @Override
    public Application startApplication() {
        return new AnimatedDialog(w, h);
    }

}
