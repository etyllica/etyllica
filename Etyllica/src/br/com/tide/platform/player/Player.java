package br.com.tide.platform.player;

import java.util.HashSet;
import java.util.Set;

import br.com.etyllica.animation.Updatable;
import br.com.etyllica.core.event.KeyEvent;
import br.com.tide.input.controller.Controller;

public class Player implements Updatable, PlayerListener {

	protected float health = 100;

	protected String name = "Player";

	protected int walkSpeed = 5;

	private Controller controller;

	protected Set<PlayerState> state = new HashSet<PlayerState>();

	protected long attackDelay = 600;
	
	protected long hitDelay = 200;

	protected long wasHit = 0;

	public Player() {
		super();

		state.add(PlayerState.STAND);
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
		state.add(PlayerState.WALK_LEFT);
		onWalkLeft();
	}

	public void stopWalkLeft() {
		state.remove(PlayerState.WALK_LEFT);
		onStopWalkLeft();
	}

	public void walkRight() {
		state.add(PlayerState.WALK_RIGHT);
		onWalkRight();
	}

	public void stopWalkRight() {
		state.remove(PlayerState.WALK_RIGHT);
		onStopWalkRight();
	}

	public void walkUp() {
		state.add(PlayerState.WALK_UP);
		onWalkUp();
	}

	public void stopWalkUp() {
		state.remove(PlayerState.WALK_UP);
		onStopWalkUp();
	}

	public void walkDown() {
		state.add(PlayerState.WALK_DOWN);
		onWalkDown();
	}

	public void stopWalkDown() {
		state.remove(PlayerState.WALK_DOWN);
		onStopWalkDown();
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

	public void handleEvent(KeyEvent event) {

		if(event.isKeyDown(controller.getRightButton())) {
			walkRight();
		}else if(event.isKeyUp(controller.getRightButton())) {
			stopWalkRight();
		}

		if(event.isKeyDown(controller.getLeftButton())) {
			walkLeft();
		}else if(event.isKeyUp(controller.getLeftButton())) {
			stopWalkLeft();
		}

		if(event.isKeyDown(controller.getUpButton())) {
			walkUp();
		}else if(event.isKeyUp(controller.getUpButton())) {
			stopWalkUp();
		}

		if(event.isKeyDown(controller.getDownButton())) {
			walkDown();
		}else if(event.isKeyUp(controller.getDownButton())) {
			stopWalkDown();
		}

		if(event.isKeyDown(controller.getButtonA())) {
			attack();
		}else if(event.isKeyUp(controller.getButtonA())) {
			onStopAttack();
			stand();
		}
		
		if(event.isKeyDown(controller.getButtonB())) {
			specialAttack();
		}else if(event.isKeyUp(controller.getButtonB())) {
			onStopSpecialAttack();
			stand();
		}

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

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void onWalkLeft() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onWalkRight() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onWalkUp() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onWalkDown() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStopWalkLeft() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStopWalkRight() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStopWalkUp() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStopWalkDown() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAttack() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStopAttack() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void onSpecialAttack() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStopSpecialAttack() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStand() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onBeignHit(Player attacker) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDie() {
		// TODO Auto-generated method stub
		
	}

}
