package run.test;

import static org.junit.Assert.*;

import org.junit.Test;

import domain.gameengine.GameEngineData;
import domain.item.EngineBall;
import domain.item.EngineGizmo;
import domain.item.EngineTriangularTakoz;

public class JTestGameEngineData {

	@Test
	public void testAddBall() {
		EngineBall testBall = new EngineBall(50,50,2);
		GameEngineData.addBall(testBall);
		assertEquals(1,GameEngineData.getBallList().size());
	}
	
	@Test
	public void testAddGizmo() {
		EngineTriangularTakoz testGizmo = new EngineTriangularTakoz(50,50,20,0,1);
		GameEngineData.addGizmo(testGizmo);
		assertEquals(1,GameEngineData.getTriangularTakozList().size());
	}

}
