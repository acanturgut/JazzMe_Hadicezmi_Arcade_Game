package run.test;

import static org.junit.Assert.*;

import org.junit.Test;

import domain.item.EngineBall;
import domain.item.EngineEngel;
import domain.physics.Vect;

public class JTestEngel {

	EngineEngel testEngel = new EngineEngel(50, 0, 1, 50);
	EngineBall testBall = new EngineBall(40,10,5);
	
	@Test
	public void testEngelCollision() {
		Vect newVelocity = new Vect(5, 0);
		testBall.setVelocityVector(newVelocity);
		testBall.run();
		testEngel.engelCollision(testBall);
		testBall.run();
		testEngel.engelCollision(testBall);
		testBall.run();
		testEngel.engelCollision(testBall);
		int x = testBall.getX();
		assertEquals(x, 35, 0.0);
	}

}
