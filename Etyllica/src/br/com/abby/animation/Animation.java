package br.com.abby.animation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.abby.animation.skeletal.Bone;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Animation {

	private List<Bone> bones;

	private Map<Bone, List<KeyFrame>> frames;

	private int frame = 0;

	private int maxFrame = 4;


	public Animation(){
		this(4);
	}

	public Animation(int frameNumber){
		super();

		bones = new ArrayList<Bone>();

		frames = new HashMap<Bone, List<KeyFrame>>();

		this.maxFrame = frameNumber;

	}

	public void addBone(Bone bone){

		bones.add(bone);

		List<KeyFrame> boneFrame = new ArrayList<KeyFrame>();

		for(int i=0;i<maxFrame;i++){

			boneFrame.add(new KeyFrame(bone.getAngleX(), bone.getAngleY(), bone.getAngleZ()));

		}

		frames.put(bone, boneFrame);
	}

	public List<Bone> getBones(){

		return bones;

	}

	public void setBones(List<Bone> bones){
		this.bones = bones;
	}

	public KeyFrame getCurrentBoneFrame(Bone bone){
		return frames.get(bone).get(frame);
	}

	private KeyFrame getFirstBoneFrame(Bone bone){
		return frames.get(bone).get(0);
	}

	public KeyFrame getBoneFrame(Bone bone, int frame){
		return frames.get(bone).get(frame);
	}

	public void nextFrame(){

		resetBones();

		frame+=1;

		if(frame==maxFrame){

			//frame = 0;
			frame = 1;

		}

		int nextFrame = frame;

		updateBones(nextFrame);

	}

	public void previusFrame(){

		resetBones();

		frame-=1;

		if(frame<0){

			frame = maxFrame-1;

		}

		int nextFrame = frame;

		updateBones(nextFrame);

	}
	
	public void refreshFrame(){

		resetBones();

		updateBones(frame);

	}

	private void updateBones(int nextFrame){

		//For each bone
		for(Bone bone:bones){

			//Rotate by KeyFrame angles
			List<KeyFrame> list = frames.get(bone);

			bone.rotateXOver(list.get(nextFrame).getX()-list.get(0).getX());
			bone.rotateYOver(list.get(nextFrame).getY()-list.get(0).getY());
			bone.rotateZOver(list.get(nextFrame).getZ()-list.get(0).getZ());

		}

	}

	public void resetBones(){

		List<Bone> blist = new ArrayList<Bone>();

		blist.addAll(bones);

		Collections.reverse(blist);

		//For each bone
		for(Bone bone:blist){

			KeyFrame currentFrame = getCurrentBoneFrame(bone);
			KeyFrame firstFrame = getFirstBoneFrame(bone);

			//Undo rotations in reverse order			
			double difZ = currentFrame.getZ()-firstFrame.getZ();
			bone.rotateZOver(-difZ);

			double difY = currentFrame.getY()-firstFrame.getY();	
			bone.rotateYOver(-difY);

			double difX = currentFrame.getX()-firstFrame.getX();
			bone.rotateXOver(-difX);

		}

	}

	public void saveFrame(){

		System.out.println("Save Frame");

		//For each bone
		for(Bone bone:bones){

			KeyFrame currentBoneFrame = getCurrentBoneFrame(bone);
			KeyFrame firstBoneFrame = getFirstBoneFrame(bone);

			if(currentBoneFrame.getX()!=firstBoneFrame.getX()){
				System.out.println(bone.getName()+".setX("+currentBoneFrame.getX()+");");
			}
			if(currentBoneFrame.getY()!=firstBoneFrame.getY()){
				System.out.println(bone.getName()+".setY("+currentBoneFrame.getY()+");");
			}
			if(currentBoneFrame.getZ()!=firstBoneFrame.getZ()){
				System.out.println(bone.getName()+".setZ("+currentBoneFrame.getZ()+");");
			}

		}

	}

	public void exportAnimation(){

		System.out.println("Export Animation to .anim");

		//For each frame
		for(int i=0;i<maxFrame;i++){

			//For each bone
			for(Bone bone:bones){	
				//Save bone
				getBoneFrame(bone, i);
			}

		}

	}

	public int getMaxFrame() {
		return maxFrame;
	}

	public void setMaxFrame(int maxFrame) {
		this.maxFrame = maxFrame;
	}

	public int getFrame() {
		return frame;
	}

}