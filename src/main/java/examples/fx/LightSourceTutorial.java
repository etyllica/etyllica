package examples.fx;

import br.com.etyllica.Etyllica;
import br.com.etyllica.commons.context.Application;
import examples.fx.application.LightSourceApplication;

public class LightSourceTutorial extends Etyllica {

    private static final long serialVersionUID = 1L;

    public LightSourceTutorial() {
        super(768, 417);//Don't ask
    }

    public static void main(String[] args) {
        LightSourceTutorial app = new LightSourceTutorial();
        app.init();
    }

    @Override
    public Application startApplication() {
        //initialSetup("../../");
        return new LightSourceApplication(w, h);
    }

}
