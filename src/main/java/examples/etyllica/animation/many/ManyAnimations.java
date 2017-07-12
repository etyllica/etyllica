package examples.etyllica.animation.many;

import br.com.etyllica.commons.animation.script.*;
import br.com.etyllica.context.SceneApplication;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.layer.ImageLayer;
import br.com.etyllica.commons.layer.Layer;
import br.com.etyllica.layer.TextLayer;

import java.awt.*;

/**
 * @author yuripourre
 * @license LGPLv3
 */

public class ManyAnimations extends SceneApplication {

    public ManyAnimations(int w, int h) {
        super(w, h);
    }

    private TextLayer text = new TextLayer("Text!");

    private TextLayer bouncingText = new TextLayer(0, 400, "Text2!");

    private TextLayer text3 = new TextLayer(300, 400, "Text3!");

    private ImageLayer hello;
    private ImageLayer helloFix;

    private Layer layer = new Layer(200, 440, 40, 40);
    int cx = 220, cy = 340;

    @Override
    public void load() {

        text.setBorder(true);
        text.setBorderColor(Color.BLACK);
        text.setBorderWidth(3f);

        bouncingText.setSize(26);
        bouncingText.setBorder(true);
        bouncingText.setBorderColor(Color.BLACK);
        bouncingText.setBorderWidth(3f);

        HorizontalMovementScript hscript = new HorizontalMovementScript(bouncingText, 10000);
        hscript.setInterval(400, 10);

        HorizontalMovementScript invscript = new HorizontalMovementScript(bouncingText, 10000);
        invscript.setInterval(10, 400);
        //After the hscript, execute invscript
        invscript.addNext(hscript);
        hscript.addNext(invscript);

        VerticalMovementScript vscript = new VerticalMovementScript(bouncingText, 600);
        vscript.setInterval(100, 200);

        VerticalMovementScript invVscript = new VerticalMovementScript(600);
        invVscript.setTarget(bouncingText);
        invVscript.setInterval(200, 100);
        invscript.addNext(vscript);
        vscript.addNext(invVscript);

        this.scene.addAnimation(hscript);
        this.scene.addAnimation(vscript);

        RotateAnimation rotate = new RotateAnimation(1000, 6000);
        rotate.setTarget(bouncingText);
        rotate.setInterval(0, 360);
        this.scene.addAnimation(rotate);


        OpacityAnimation opacityAnimation = new OpacityAnimation(0, 10000);
        opacityAnimation.setTarget(text3);
        opacityAnimation.setInterval(0, 255);
        this.scene.addAnimation(opacityAnimation);

        ScaleUniformAnimation scaleAnimation = new ScaleUniformAnimation(0, 10000);
        scaleAnimation.setTarget(text3);
        scaleAnimation.setInterval(1, 5);
        this.scene.addAnimation(scaleAnimation);


        OrbitAnimation orbit = new OrbitAnimation(0, 60000);
        orbit.setTarget(layer);
        orbit.setInterval(0, 1080);
        orbit.around(cx, cy);

        orbit.setRepeat(AnimationScript.REPEAT_FOREVER);

        this.scene.addAnimation(orbit);


        hello = new ImageLayer(200, 100, "hello.png");
        helloFix = new ImageLayer(200, 100, "hello.png");

        ScaleUniformAnimation scaleHello = new ScaleUniformAnimation(0, 10000);
        scaleHello.setTarget(hello);
        scaleHello.setInterval(1, 2);
        this.scene.addAnimation(scaleHello);

        RotateAnimation rotateHello = new RotateAnimation(0, 10000);
        rotateHello.setTarget(hello);
        rotateHello.setInterval(0, 360);
        this.scene.addAnimation(rotateHello);


        loading = 100;
    }

    @Override
    public void draw(Graphics g) {

        //Drawing background
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x, y, w, h);

        text.draw(g);

        bouncingText.draw(g);

        text3.draw(g);


        helloFix.draw(g);
        hello.draw(g);


        g.setColor(Color.BLACK);
        g.fillCircle(cx, cy, 10);
        g.drawCircle(cx, cy, 120);

        g.drawLine(cx, cy, layer.getX() + layer.getW() / 2, layer.getY() + layer.getH() / 2);

        g.setColor(Color.BLUE);
        g.drawRect(layer);

    }

}
