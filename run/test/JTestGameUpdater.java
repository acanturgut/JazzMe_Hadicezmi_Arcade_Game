package run.test;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Test;

import domain.gameengine.GameEngineData;
import domain.gameengine.GameEngineUpdater;
import domain.item.EngineBall;
import domain.item.EngineCezmi;
import domain.item.EngineSquareTakoz;
import domain.physics.Vect;

public class JTestGameUpdater {
	
	EngineBall testBall = new EngineBall(10,10,5);
	Vect newVelocity = new Vect(-5, -5);
	
	@Test
	public void testBallMove() {
		GameEngineData.addBall(testBall);
		EngineCezmi test1Cezmi = new EngineCezmi(Color.BLACK,1);
		EngineCezmi test2Cezmi = new EngineCezmi(Color.BLACK,2);
		GameEngineData.setCezmi1(test2Cezmi);
		GameEngineData.setCezmi2(test2Cezmi);

		testBall.setVelocityVector(newVelocity);

		EngineSquareTakoz testSqTakoz1 = new EngineSquareTakoz(50, 50, 5, 5);
		EngineSquareTakoz testSqTakoz2 = new EngineSquareTakoz(100, 100, 5, 5);
		EngineSquareTakoz testSqTakoz3 = new EngineSquareTakoz(150, 150, 5, 5);
		GameEngineData.addGizmo(testSqTakoz1);
		GameEngineData.addGizmo(testSqTakoz2);
		GameEngineData.addGizmo(testSqTakoz3);
		
		//Test for level one, small size window 
		GameEngineUpdater testGameUpdater = new GameEngineUpdater(500,1);
		testGameUpdater.ballMovement();
		int xBall = testBall.getX();
		assertEquals(5, xBall, 0.0);
	}
	
	@Test
	public void testIsAvailablePosition() {
		GameEngineData.addBall(testBall);
		EngineCezmi test1Cezmi = new EngineCezmi(Color.BLACK,1);
		EngineCezmi test2Cezmi = new EngineCezmi(Color.BLACK,2);
		GameEngineData.setCezmi1(test2Cezmi);
		GameEngineData.setCezmi2(test2Cezmi);

		testBall.setVelocityVector(newVelocity);

		EngineSquareTakoz testSqTakoz1 = new EngineSquareTakoz(50, 50, 5, 5);
		EngineSquareTakoz testSqTakoz2 = new EngineSquareTakoz(100, 100, 5, 5);
		EngineSquareTakoz testSqTakoz3 = new EngineSquareTakoz(150, 150, 5, 5);
		GameEngineData.addGizmo(testSqTakoz1);
		GameEngineData.addGizmo(testSqTakoz2);
		GameEngineData.addGizmo(testSqTakoz3);
		
		//Test for level one, small size window 
		GameEngineUpdater testGameUpdater = new GameEngineUpdater(500,1);
		boolean available = testGameUpdater.isPositionAvailable(10, 10);

		assertEquals(true, available);
	}
}
