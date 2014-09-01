package br.com.tide;


public interface ActivePlayerListener {

	public void onAttack();
	
	public void onStopAttack();
	
	public void onSpecialAttack();
	
	public void onStopSpecialAttack();
	
	public void onBeignHit(ActivePlayer attacker);
	
	public void onStand();
	
	public void onDie();
	
}
