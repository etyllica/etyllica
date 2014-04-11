package br.com.tide.platform.player;

import java.util.HashSet;
import java.util.Set;

import br.com.etyllica.core.Updatable;
import br.com.tide.input.ControllerListener;

public class Player implements Updatable, PlayerListener, ControllerListener {

	protected float health = 100;

	protected String name = "Player";

	protected int walkSpeed = 5;

	protected Set<PlayerState> state = new HashSet<PlayerState>();

	protected long attackDelay = 600;
	
	protected long hitDelay = 200;

	protected long wasHit = 0;

	public Player() {
		super();

		state.add(PlayerState.STAND);
	}

	public boolean hasState(PlayerState ... playerStates) {
		
		for(PlayerState playerState: playerStates) {
			
			if(state.contains(playerState)) {
				return true;
			}
			
		}
		
		return false;
	}

	public void update(long now) {

		if(isBeignHit()) {
			if(now-wasHit>hitDelay) {
				this.stand();
			}
		}

	}
	
	public void die() {
		state.clear();
		state.add(PlayerState.DEAD);
		onDie();
	}

	public void walkLeft() {
		onWalkLeft();
		state.add(PlayerState.WALK_LEFT);
	}

	public void stopWalkLeft() {
		state.remove(PlayerState.WALK_LEFT);
		onStopWalkLeft();
	}

	public void walkRight() {
		onWalkRight();
		state.add(PlayerState.WALK_RIGHT);
	}

	public void stopWalkRight() {
		state.remove(PlayerState.WALK_RIGHT);
		onStopWalkRight();
	}

	public void walkUp() {
		onWalkUp();
		state.add(PlayerState.WALK_UP);
	}

	public void stopWalkUp() {
		state.remove(PlayerState.WALK_UP);
		onStopWalkUp();
	}

	public void walkDown() {
		onWalkDown();
		state.add(PlayerState.WALK_DOWN);
	}

	public void stopWalkDown() {
		state.remove(PlayerState.WALK_DOWN);
		onStopWalkDown();
	}
	
	public void stopWalk() {
		state.remove(PlayerState.WALK_UP);
		state.remove(PlayerState.WALK_DOWN);
		state.remove(PlayerState.WALK_LEFT);
		state.remove(PlayerState.WALK_RIGHT);		
	}

	public void stand() {
		state.clear();
		state.add(PlayerState.STAND);
		onStand();
	}

	public void attack() {
		state.clear();
		state.add(PlayerState.ATTACK);
		onAttack();
	}
	
	public void specialAttack() {
		state.clear();
		state.add(PlayerState.SPECIAL_ATTACK);
		onSpecialAttack();
	}

	public void beignHit(Player who, long when) {
		state.clear();
		state.add(PlayerState.BEING_HIT);
		onBeignHit(who);

		wasHit = when;
	}

	public void stopAttack() {
		stand();
		onStopAttack();
	}

	public boolean isWalking() {
		return state.contains(PlayerState.WALK_RIGHT)||state.contains(PlayerState.WALK_LEFT)||state.contains(PlayerState.WALK_UP)||state.contains(PlayerState.WALK_DOWN);
	}

	public boolean isAttacking() {
		return state.contains(PlayerState.ATTACK);
	}

	public boolean isStanding() {
		return state.contains(PlayerState.STAND);
	}
	
	public boolean isDead() {
		return state.contains(PlayerState.DEAD);
	}

	public boolean isBeignHit() {
		return state.contains(PlayerState.BEING_HIT);
	}

	public void onWalkLeft() {
		// TODO Auto-generated method stub
	}

	public void onWalkRight() {
		// TODO Auto-generated method stub
	}

	public void onWalkUp() {
		// TODO Auto-generated method stub
	}

	public void onWalkDown() {
		// TODO Auto-generated method stub
	}

	public void onStopWalkLeft() {
		// TODO Auto-generated method stub
	}

	public void onStopWalkRight() {
		// TODO Auto-generated method stub
	}

	public void onStopWalkUp() {
		// TODO Auto-generated method stub
	}

	public void onStopWalkDown() {
		// TODO Auto-generated method stub
	}
	
	public void onAttack() {
		// TODO Auto-generated method stub
	}

	public void onStopAttack() {
		// TODO Auto-generated method stub
	}
	
	public void onSpecialAttack() {
		// TODO Auto-generated method stub
	}

	public void onStopSpecialAttack() {
		// TODO Auto-generated method stub
	}

	public void onStand() {
		// TODO Auto-generated method stub
	}

	public void onBeignHit(Player attacker) {
		// TODO Auto-generated method stub
	}

	public void onDie() {
		// TODO Auto-generated method stub
	}

	public void onUpButtonPressed() {
		walkUp();
	}

	@Override
	public void onUpButtonReleased() {
		stopWalkUp();
	}

	@Override
	public void onDownButtonPressed() {
		walkDown();
	}

	@Override
	public void onDownButtonReleased() {
		stopWalkDown();
	}

	@Override
	public void onRightButtonPressed() {
		walkRight();
	}

	@Override
	public void onRightButtonReleased() {
		stopWalkRight();
	}

	@Override
	public void onLeftButtonPressed() {
		walkLeft();
	}

	@Override
	public void onLeftButtonReleased() {
		stopWalkLeft();
	}

	@Override
	public void onAButtonPressed() {
		attack();
	}

	public void onAButtonReleased() {
		stopAttack();
		stand();	
	}

	public void onBButtonPressed() {
		specialAttack();
	}

	public void onBButtonReleased() {
		onStopSpecialAttack();
		stand();	
	}

	public void onCButtonPressed() {
		// TODO Auto-generated method stub
		
	}

	public void onCButtonReleased() {
		// TODO Auto-generated method stub
		
	}

}
