package examples.etyllica.basic.input;

import br.com.etyllica.Etyllica;
import br.com.etyllica.commons.context.Application;

public class InputExample extends Etyllica {

    private static final long serialVersionUID = 1L;

    public InputExample() {
        super(800, 600);
    }

    public static void main(String[] args) {
        InputExample app = new InputExample();
        app.init();
    }

    @Override
    public Application startApplication() {
        initialSetup("../");
        return new MoveImage(w, h);
    }

}
