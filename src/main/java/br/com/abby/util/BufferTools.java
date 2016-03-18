package br.com.abby.util;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

import com.badlogic.gdx.math.Matrix4;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class BufferTools {
	
    public static String bufferToString(FloatBuffer buffer, int elements) {
        StringBuilder bufferString = new StringBuilder();
        for (int i = 0; i < elements; i++) {
            bufferString.append(" ").append(buffer.get(i));
        }
        return bufferString.toString();
    }

    public static FloatBuffer asFloatBuffer(Matrix4 matrix4f) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
        matrix4f.set(buffer.array());
        return buffer;
    }

    public static FloatBuffer asFlippedFloatBuffer(Matrix4 matrix4f) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
        matrix4f.set(buffer.array());
        buffer.flip();
        return buffer;
    }

    public static FloatBuffer asFloatBuffer(float... values) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(values.length);
        buffer.put(values);
        return buffer;
    }

    public static FloatBuffer reserveData(int amountOfIndices) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(amountOfIndices);
        return buffer;
    }

    public static FloatBuffer asFlippedFloatBuffer(float... values) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(values.length);
        buffer.put(values);
        buffer.flip();
        return buffer;
    }
}
