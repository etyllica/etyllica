package examples.timeline;

import br.com.etyllica.Etyllica;
import br.com.etyllica.core.context.Application;
import examples.timeline.application.TimelineApplication;

/**
 * @author yuripourre
 * @license LGPLv3
 */

public class TimelineExample extends Etyllica {

    private static final long serialVersionUID = 1L;

    public TimelineExample() {
        super(640, 480);
    }

    public static void main(String[] args) {
        TimelineExample app = new TimelineExample();
        app.init();
    }

    @Override
    public Application startApplication() {
        initialSetup("../../");

        return new TimelineApplication(w,h);
    }

}
