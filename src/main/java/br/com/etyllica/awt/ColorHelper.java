package br.com.etyllica.awt;

import java.awt.Color;

public class ColorHelper {

	public static Color darker(Color color, int intensity) {
		int a = color.getAlpha();
		int r = darker(color.getRed(), intensity);
		int g = darker(color.getGreen(), intensity);
		int b = darker(color.getBlue(), intensity);

		Color darker = new Color(r, g, b, a);
		return darker;
	}

	private static int darker(int channel, int intensity) {
		int color = channel-intensity;
		if (color < 0) {
			color = 0;
		}
		return color;
	}

}
