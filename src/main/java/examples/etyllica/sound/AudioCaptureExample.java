package examples.etyllica.sound;

import br.com.etyllica.Etyllica;
import br.com.etyllica.commons.context.Application;

/**
 * @author yuripourre
 * @license LGPLv3
 */

public class AudioCaptureExample extends Etyllica {

    private static final long serialVersionUID = 1L;

    public AudioCaptureExample() {
        super(640, 480);
    }

    public static void main(String[] args) {
        AudioCaptureExample app = new AudioCaptureExample();
        app.init();
    }

    @Override
    public Application startApplication() {
        initialSetup("../../");
        return new AudioCaptureApplication(w, h);
    }

}
