package run.test;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import domain.item.EngineTriangularTakoz;
import domain.physics.Circle;

public class JTestTriangularTakoz {

	EngineTriangularTakoz triTest = new EngineTriangularTakoz(10, 10, 5, 1, 1);

	
	@Test
	public void testBoundingBoxX() {
		int x = triTest.getX();
		assertEquals(x, triTest.boundingBox().x, 0.0);
	}	
	
	@Test
	public void testBoundingBoxY() {
		int y = triTest.getY();
		assertEquals(y, triTest.boundingBox().y, 0.0);
	}
	
	@Test
	public void testLeftBottomCircleX(){
		triTest.set();
		Circle testCircle = new Circle(10, 10, 3);
		assertEquals(13, triTest.getLeftBottomCircle().getCenter().x(), 0.0);
	}
	
	@Test
	public void testLeftBottomCircleRadius(){
		triTest.set();
		Circle testCircle = new Circle(10, 10, 3);
		assertEquals(3, triTest.getLeftBottomCircle().getRadius(), 0.0);
	}
	
	@Test
	public void testSetHypotenuse1(){
		triTest.set();
		int x = (int) triTest.getHypotenuse().p1().x();
		assertEquals(10, x, 0.0);
	}
	
	@Test
	public void testSetHypotenuse2(){
		triTest.set();
		int x = (int) triTest.getHypotenuse().p2().x();
		assertEquals(15, x, 0.0);
	}
	
	@Test
	public void testState(){
		int state = triTest.getState();
		assertEquals(1, state, 0.0);
	}



}
