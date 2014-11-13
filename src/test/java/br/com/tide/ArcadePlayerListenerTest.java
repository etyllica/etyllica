package br.com.tide;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.tide.arcade.player.ArcadePlayer;
import br.com.tide.arcade.player.ArcadePlayerListener;

public class ArcadePlayerListenerTest {

	private TestPlayer player;
	
	private TestPlayerListener listener;
	
	@Before
	public void setUp() {
		
		listener = new TestPlayerListener();
		
		player = new TestPlayer(listener);
		
		player.walkLeft();
	}
	
	@Test
	public void testListenerCall() {
		Assert.assertTrue(listener.left);
	}
	
	class TestPlayer extends ArcadePlayer {

		public String name = "player";
		
		public TestPlayer(TestPlayerListener listener) {
			super(listener);
		}

	}
	
	class TestPlayerListener implements ArcadePlayerListener<TestPlayer> {

		public boolean left = false;
		
		@Override
		public void onWalkLeft(TestPlayer player) {
			left = true;
		}

		@Override
		public void onWalkRight(TestPlayer player) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onWalkUp(TestPlayer player) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onWalkDown(TestPlayer player) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStopWalkLeft(TestPlayer player) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStopWalkRight(TestPlayer player) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStopWalkUp(TestPlayer player) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStopWalkDown(TestPlayer player) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onAttack(TestPlayer player) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
