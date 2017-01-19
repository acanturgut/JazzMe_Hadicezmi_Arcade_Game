package run.test;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Rectangle;

import org.junit.Test;

import domain.item.EngineTokat;
import domain.physics.Circle;
import domain.physics.LineSegment;
import domain.physics.Vect;
import domain.playerdata.ADataPlayer;

public class JTestTokat {
	
	EngineTokat testTokat1 = new EngineTokat(50,50,20,1,1);
	
	@Test
	public void rotateTest(){
			int x1=testTokat1.getX();
			int y1=testTokat1.getY();
			int L1=testTokat1.getL();
			boolean invoke=false;
			boolean normalInvoke=true;
			int heightSquare = (3*L1/2) * (3*L1/2);
			double fast=10;
			double rotation=0;
			rotation = rotation - fast;
			double rad = rotation * 0.0174533;
			testTokat1.rotate();
			assertEquals((int) (testTokat1.getTokatBottomOvalXTemp() + (Math.sqrt((2 * heightSquare) - (2 * heightSquare) * Math.cos(rad))) * Math.sin(90*0.0174533 - rad/2)),testTokat1.getTokatBottomOvalX(),0.0);
			assertEquals((int) (testTokat1.getTokatBottomOvalYTemp() + (Math.sqrt((2 * heightSquare) - (2 * heightSquare) * Math.cos(rad))) * Math.sin(rad/2)) + 1,testTokat1.getTokatBottomOvalY(),0.0);
			assertEquals(new Circle(testTokat1.getTokatBottomOvalX() + L1 / 4, testTokat1.getTokatBottomOvalY()  + L1 / 4, L1 / 4 ),testTokat1.getBottomCircle());
			assertEquals(false,invoke);
			assertEquals(true,normalInvoke);
		}
	}

