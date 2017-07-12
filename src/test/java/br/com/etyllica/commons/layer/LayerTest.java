package br.com.etyllica.commons.layer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LayerTest {

    private Layer layer;

    @Before
    public void setUp() {
        layer = new Layer(0, 1, 31, 32);
        layer.setOpacity(22);
        layer.setOrigin(33,34);
        layer.setVisible(false);
    }

    @Test
    public void testGetSrcMeasures() {
        Assert.assertEquals(31, layer.getW());
        Assert.assertEquals(32, layer.getH());

        layer.setW(200);
        Assert.assertEquals(200, layer.getW());
    }

    @Test
    public void testSwapVisible() {
        layer.setVisible(false);
        layer.swapVisible();

        Assert.assertTrue(layer.isVisible());
    }

    @Test
    public void testCopyLayer() {
        Layer l = new Layer();
        l.copy(layer);

        Assert.assertEquals(0, l.getX());
        Assert.assertEquals(1, l.getY());
        Assert.assertEquals(31, l.getW());
        Assert.assertEquals(32, l.getH());
        Assert.assertEquals(33, l.getOriginX(), 0);
        Assert.assertEquals(34, l.getOriginY(), 0);
        Assert.assertEquals(22, l.getOpacity());

    }

}
