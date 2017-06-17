package examples.etyllica.collision.alpha;

import br.com.etyllica.Etyllica;
import br.com.etyllica.commons.context.Application;
import examples.etyllica.collision.hexagon.HexagonExample;

public class AlphaExample extends Etyllica {

    private static final long serialVersionUID = 1L;

    public AlphaExample() {
        super(320, 100);
    }

    public static void main(String[] args) {
        AlphaExample app = new AlphaExample();
        app.init();
    }

    @Override
    public Application startApplication() {
        //initialSetup("../../");
        return new AlphaCollision(w, h);
    }

}
