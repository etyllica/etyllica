package examples.etyllica.procedural.color;

import br.com.etyllica.Etyllica;
import br.com.etyllica.commons.context.Application;

/**
 * @author yuripourre
 * @license LGPLv3
 */

public class ColorChangeExample extends Etyllica {

    private static final long serialVersionUID = 1L;

    public ColorChangeExample() {
        super(640, 480);
    }

    public static void main(String[] args) {
        ColorChangeExample app = new ColorChangeExample();
        app.init();
    }

    @Override
    public Application startApplication() {
        initialSetup("../../");
        hideCursor();

        return new ProceduralColorChange(w, h);
    }

}
