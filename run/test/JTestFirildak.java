package run.test;

import static org.junit.Assert.*;

import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

import org.junit.Test;

import domain.item.EngineBall;
import domain.item.EngineFirildak;
import domain.physics.Circle;
import domain.physics.Geometry;
import domain.physics.LineSegment;
import domain.physics.Vect;

public class JTestFirildak {
	
	EngineFirildak testFirildak = new EngineFirildak(50,50,20,1);

	@Test
	public void testRotate() {
		Rectangle rect2 = new Rectangle(testFirildak.getX(), testFirildak.getY(), testFirildak.getL(), testFirildak.getL());
		double rotation = testFirildak.getRotation() + 0.05;
		Path2D.Double path = new Path2D.Double();
		path.append(rect2, false);
		AffineTransform affineTransform = new AffineTransform();
		affineTransform.rotate(rotation, testFirildak.getX()+testFirildak.getL()/2, testFirildak.getY()+testFirildak.getL()/2);
		path.transform(affineTransform);
		int radius = 3;
		testFirildak.rotate();
		assertEquals(new LineSegment(path.getBounds().getMinX() , path.getBounds().getMinY(), path.getBounds().getMaxX(),path.getBounds().getMinY()),testFirildak.getTopLine());
		assertEquals(new LineSegment(path.getBounds().getMaxX() , path.getBounds().getMinY(), path.getBounds().getMaxX(),path.getBounds().getMaxY()),testFirildak.getRightLine());
		assertEquals(new LineSegment(path.getBounds().getMaxX() , path.getBounds().getMinY(), path.getBounds().getMaxX(),path.getBounds().getMaxY()),testFirildak.getLeftLine());
		assertEquals(new LineSegment(path.getBounds().getMinX() , path.getBounds().getMaxY(), path.getBounds().getMaxX(),path.getBounds().getMaxY()),testFirildak.getBottomLine());
		assertEquals(new Circle(testFirildak.getX() + radius, testFirildak.getY() + radius, radius),testFirildak.getLeftTopCircle());
		assertEquals(new Circle(testFirildak.getX() + testFirildak.getL() - radius, testFirildak.getY() + radius, radius),testFirildak.getRightTopCircle());
		assertEquals(new Circle(testFirildak.getX() + radius, testFirildak.getY() + testFirildak.getL() - radius, radius),testFirildak.getLeftBottomCircle());
		assertEquals(new Circle(testFirildak.getX() + testFirildak.getL() - radius, testFirildak.getY() + testFirildak.getL() - radius, radius),testFirildak.getRightBottomCircle());
	}
	
	@Test
	public void testFirildakCollision() {
		EngineBall testBall = new EngineBall(40,40,10);
		Vect ballVelocity=testBall.getVelocityVector();
		testFirildak.firildakCollision(testBall);
		assertEquals(Geometry.reflectWall(testFirildak.getLeftLine(), ballVelocity),testBall.getVelocityVector());
	}

}
