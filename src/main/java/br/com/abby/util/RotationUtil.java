package br.com.abby.util;

import org.lwjgl.util.vector.Quaternion;

public class RotationUtil {
	
	/**
	 * Convert angles to quaternion
	 * @param yaw in radians
	 * @param pitch in radians
	 * @param roll in radians
	 * @return quaternion
	 */
	public static final Quaternion convertToQuaternion(double yaw, double pitch, double roll) {

		// Assuming the angles are in radians.
		double c1 = Math.cos(yaw);
		double s1 = Math.sin(yaw);
		double c2 = Math.cos(pitch);
		double s2 = Math.sin(pitch);
		double c3 = Math.cos(roll);
		double s3 = Math.sin(roll);

		double w = Math.sqrt(1.0 + c1 * c2 + c1*c3 - s1 * s2 * s3 + c2*c3) / 2.0;
		double w4 = (4.0 * w);
		double x = (c2 * s3 + c1 * s3 + s1 * s2 * c3) / w4 ;
		double y = (s1 * c2 + s1 * c3 + c1 * s2 * s3) / w4 ;
		double z = (-s1 * s3 + c1 * s2 * c3 +s2) / w4 ;

		Quaternion quaternion = new Quaternion((float)x, (float) y, (float) z, (float) w);
		return quaternion;
	}

}
