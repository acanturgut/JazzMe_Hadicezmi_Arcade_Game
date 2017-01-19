package run.test;

import static org.junit.Assert.*;

import java.awt.Color;
import org.junit.Test;

import domain.item.EngineBall;
import domain.item.EngineCezmi;

public class JTestCezmi {

	EngineCezmi testCezmi = new EngineCezmi(Color.BLACK,1);
	
	@Test
	public void testMoveLeft() {
		int x = testCezmi.getCenterX();
		testCezmi.moveLeft();
		assertEquals(testCezmi.getCenterX(), x - 1, 0.0);
	}
	
	@Test
	public void testMoveRight() {
		int x = testCezmi.getCenterX();
		testCezmi.moveRight();
		assertEquals(testCezmi.getCenterX(), x + 1, 0.0);
	}
	
	@Test
	public void testMoveUp() {
		int y = testCezmi.getCenterY();
		testCezmi.moveUp();
		assertEquals(testCezmi.getCenterY(), y - 1, 0.0);
	}
	
	
//	@Test
//	public void testRadius() {
//		int radius = testCezmi.getRadius();
//		int radius2 = 
//		testCezmi.moveRight();
//		assertEquals(testCezmi.getCenterX(), x + 1, 0.0);
//	}

}
