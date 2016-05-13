/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package br.com.abby.linear;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

/** Encapsulates an oriented bounding box represented by a minimum and a maximum Vector. Additionally you can query for the
 * bounding box's center, dimensions and corner points.
 *
 * @author badlogicgames@gmail.com */
public class OrientedBoundingBox extends BoundingBox {
	private static final long serialVersionUID = -563414328395949544L;
	
	public Matrix4 transform = new Matrix4();
	
	public OrientedBoundingBox(Vector3 min, Vector3 max) {
		super(min, max);
	}
	
	public OrientedBoundingBox(float size) {
		this(new Vector3(-size/2, -size/2, -size/2), new Vector3(size/2, size/2, size/2));
	}
	
	public void rotateX(float angle) {
		transform.rotate(Vector3.X.mul(transform), angle);
	}
	
	public void rotateY(float angle) {
		transform.rotate(Vector3.Y.mul(transform), angle);
	}
	
	public void rotateZ(float angle) {
		transform.rotate(Vector3.Z.mul(transform), angle);
	}
	
	public OrientedBoundingBox translate (float x, float y, float z) {
		transform.translate(x, y, z);
		return this;
	}
	
}
