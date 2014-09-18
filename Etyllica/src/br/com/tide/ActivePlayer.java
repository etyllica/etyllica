package br.com.tide;

import br.com.etyllica.core.Updatable;

public class ActivePlayer extends Player implements Updatable, ActivePlayerListener {
	
	protected float health = 100;

	protected int currentSpeed = 5;

	protected long wasHit = 0;
		
	protected long hitDelay = 200;
	
	protected long attackDelay = 600;
	
	public void update(long now) {

		if(isBeignHit()) {
			if(now-wasHit>hitDelay) {
				this.stand();
			}
		}
	}
	
	public void stand() {
		states.clear();
		states.add(PlayerState.STAND);
		onStand();
	}
	
	public void attack() {
		states.clear();
		states.add(PlayerState.ATTACK);
		onAttack();
	}
	
	public void specialAttack() {
		states.clear();
		states.add(PlayerState.SPECIAL_ATTACK);
		onSpecialAttack();
	}

	public void beignHit(ActivePlayer who, long when) {
		states.clear();
		states.add(PlayerState.BEING_HIT);
		onBeignHit(who);

		wasHit = when;
	}

	public void stopAttack() {
		this.stand();
		onStopAttack();
	}
	
	public void die() {
		states.clear();
		states.add(PlayerState.DEAD);
		onDie();
	}
	
	public boolean isAttacking() {
		return states.contains(PlayerState.ATTACK);
	}

	public boolean isStanding() {
		return states.contains(PlayerState.STAND);
	}
	
	public boolean isDead() {
		return states.contains(PlayerState.DEAD);
	}

	public boolean isBeignHit() {
		return states.contains(PlayerState.BEING_HIT);
	}
	
	//Listener Methods
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

	public void onBeignHit(ActivePlayer attacker) {
		// TODO Auto-generated method stub
	}

	public void onDie() {
		// TODO Auto-generated method stub
	}
	
}
