package examples.etyllica.camera;

import br.com.etyllica.Etyllica;
import br.com.etyllica.commons.context.Application;
import examples.etyllica.camera.application.CameraExample;

public class CinematicsExample extends Etyllica {

    private static final long serialVersionUID = 1L;

    public CinematicsExample() {
        super(640, 480);
    }

    public static void main(String[] args) {
        CinematicsExample app = new CinematicsExample();
        app.init();
    }

    @Override
    public Application startApplication() {
        //initialSetup("../../../../../");
        return new CameraExample(w, h);
    }

}