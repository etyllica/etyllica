package examples.etyllica.swing;

import br.com.etyllica.Etyllica;
import br.com.etyllica.commons.context.Application;

public class SwingIntegration extends Etyllica {

    private static final long serialVersionUID = 1L;

    public SwingIntegration() {
        super(800, 600);
    }

    public static void main(String[] args) {
        SwingIntegration app = new SwingIntegration();
        app.init();
    }

    @Override
    public Application startApplication() {
        initialSetup("../../");
        return new FileExample(w, h);
    }

}
