package run.test;

import static org.junit.Assert.*;

import org.junit.Test;

import domain.item.EngineBall;
import domain.item.EngineBorder;
import domain.physics.Vect;

public class JTestBorder {

	EngineBorder testBorder = new EngineBorder(700);
	EngineBall testBall = new EngineBall(10,10,5);
	
	@Test
	public void testBorderCollision() {
		Vect newVelocity = new Vect(-5, -5);
		testBall.setVelocityVector(newVelocity);
		testBall.run();
		testBorder.borderCollision(testBall);
		testBall.run();
		testBorder.borderCollision(testBall);
		testBall.run();
		testBorder.borderCollision(testBall);
		int x = testBall.getX();
		assertEquals(x, 5, 0.0);
	}
	
	
	
}
