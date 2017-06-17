package examples.etyllica.time.update;

import br.com.etyllica.Etyllica;
import br.com.etyllica.commons.context.Application;

public class UpdateExample extends Etyllica {

    private static final long serialVersionUID = 1L;

    public UpdateExample() {
        super(800, 600);
    }

    public static void main(String[] args) {
        UpdateExample app = new UpdateExample();
        app.init();
    }

    @Override
    public Application startApplication() {
        //initialSetup("../../");
        return new TimedApplication(w, h);
    }

}
