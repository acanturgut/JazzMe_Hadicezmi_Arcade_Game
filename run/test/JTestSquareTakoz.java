package run.test;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import domain.item.EngineSquareTakoz;

public class JTestSquareTakoz {

	EngineSquareTakoz testSquareTakoz = new EngineSquareTakoz(10,10, 5, 1);
	
	@Test
	public void testBoundingBoxX() {
		int x = testSquareTakoz.boundingBox().x;
		assertEquals(10, x, 0.0);
	}
	
	@Test
	public void testBoundingBoxY() {
		int y = testSquareTakoz.boundingBox().y;
		assertEquals(10, y, 0.0);
	}
	
	@Test
	public void testLength(){
		int L = testSquareTakoz.boundingBox().width;
		assertEquals(5, L, 0.0);
	}

	@Test
	public void testSetSquareAvailable(){
		testSquareTakoz.setSquareAvailable(false);
		boolean testHit = testSquareTakoz.isAllHitSquareTakoz();
		assertEquals(testHit, true);
	}
	
	
}
