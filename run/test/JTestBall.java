package run.test;

import static org.junit.Assert.*;

import org.junit.Test;

import domain.item.EngineBall;
import domain.physics.Vect;

public class JTestBall {

	EngineBall testBall = new EngineBall(50,50,2);
	double xVelocity = testBall.getVelocityVector().x();
	double yVelocity = testBall.getVelocityVector().y();
	Vect velocityVector = new Vect(xVelocity, yVelocity);
	
	
	@Test
	public void testMoveX() {
		testBall.moveX();
		assertEquals(50 + xVelocity, testBall.getX(), 0.0);
	}
	
	@Test
	public void testMoveY() {
		testBall.moveY();
		assertEquals(50 + yVelocity, testBall.getY(), 0.0);
	}

	@Test
	public void testGetR() {
		int radius = testBall.getR();
		assertEquals(radius, 2, 0.0);
	}
	
	@Test
	public void testGetVelocityVector() {
		Vect testVect = new Vect(testBall.getVelocityVector().x(), testBall.getVelocityVector().y());
		assertEquals(testVect.x(), velocityVector.x(), 0.0);
		assertEquals(testVect.y(), velocityVector.y(), 0.0);
	}
	
	@Test
	public void testRun(){
		testBall.run();
		assertEquals(50 + xVelocity, testBall.getX(), 0.0);
		assertEquals(50 + yVelocity, testBall.getY(), 0.0);
	}
	
	
	// Extreme points - to the borders
	
	EngineBall testBall2 = new EngineBall(0,0,2);
	Vect velocity = new Vect(-10, -10);
	
	@Test
	public void testMoveExtremeX() {
		testBall2.setVelocityVector(velocity);
		testBall2.run();
		assertEquals(10, testBall2.getX(), 0.0);
	}
	
	@Test
	public void testMoveExtremeY() {
		testBall2.setVelocityVector(velocity);
		testBall2.run();
		assertEquals(10, testBall2.getY(), 0.0);
	}
	
	
}
