package ui.painter;

import java.awt.Graphics;

import domain.gameengine.GameEngineData;
import ui.data.UIData;
import ui.item.UIBall;
import ui.item.UICezerye;
import ui.item.UICezmi;

/**
 * The Class UIPainterPlay.
 */
public class UIPainterPlay {

	/** The level. */
	int level;

	/**
	 * Instantiates a new UI painter play.
	 *
	 * @param level the level
	 */
	public UIPainterPlay(int level){
		this.level = level;
	}

	/**
	 * Paint.
	 *
	 * @param g the g
	 */
	public void paint(Graphics g){

		if(level == 1){
			UIBall ball1 = new UIBall(GameEngineData.getBallList().get(0));
			ball1.paint(g);

		}else if(level == 2){
			UIBall ball1 = new UIBall(GameEngineData.getBallList().get(0));
			ball1.paint(g);
			UIBall ball2 = new UIBall(GameEngineData.getBallList().get(1));
			ball2.paint(g);

		}else if(level == 3){
			UIBall ball1 = new UIBall(GameEngineData.getBallList().get(0));
			ball1.paint(g);
			UIBall ball2 = new UIBall(GameEngineData.getBallList().get(1));
			ball2.paint(g);
			UIBall ball3 = new UIBall(GameEngineData.getBallList().get(2));
			ball3.paint(g);
			UIBall ball4 = new UIBall(GameEngineData.getBallList().get(3));
			ball4.paint(g);
			
		}else if(level == 4){
			
		}
		
		for (int i = 0; i < UIData.getCezeryeUIList().size(); i++) {
			UIData.getCezeryeUIList().get(i).paint(g);
		}

		for (int i = 0; i < UIData.getFirildakUIList().size(); i++) {
			UIData.getFirildakUIList().get(i).paint(g);
			UIData.getFirildakUIList().get(i).run();
		}

		for (int i = 0; i < UIData.getTokatUIList().size(); i++) {
			UIData.getTokatUIList().get(i).paint(g);
			UIData.getTokatUIList().get(i).run();
		}
		
		UICezmi cezmi1 = new UICezmi(GameEngineData.getCezmi1());
		UICezmi cezmi2 = new UICezmi(GameEngineData.getCezmi2());
		
		UICezerye cezerye = new UICezerye(GameEngineData.getCezerye());
		
		cezerye.paint(g);
		cezmi1.paint(g);
		cezmi2.paint(g);
		
	}
}
