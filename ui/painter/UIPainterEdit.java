package ui.painter;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import domain.gameengine.GameEngineData;
import ui.item.UIEngel;
import ui.item.UIFirildak;
import ui.item.UISquareTakoz;
import ui.item.UITokat;
import ui.item.UITriangularTakoz;

/**
 * The Class UIPainterEdit.
 */
public class UIPainterEdit {

	/** The window size. */
	private int windowSize = 500;
	
	/** The window size part. */
	private int windowSizePart = 500;

	/**
	 * Instantiates a new UI painter edit.
	 *
	 * @param windowSize the window size
	 */
	public UIPainterEdit(int windowSize){
		this.windowSize = windowSize;
		this.windowSizePart = windowSize/25;
	}
	
	/**
	 * Paint lines.
	 *
	 * @param g the g
	 */
	public void paintLines(Graphics g){
		
		Graphics2D g2 = (Graphics2D) g;
		
		ArrayList<Line2D> listOfLinesX = new ArrayList<Line2D>(); 
		ArrayList<Line2D> listOfLinesY = new ArrayList<Line2D>();

		for (int i = 0; i < 24; i++) { // LINES ON THE SCREEN
			g2.setColor(new Color(76, 76, 76));
			listOfLinesX.add(new Line2D.Float(windowSizePart+i*windowSizePart,0,windowSizePart+i*windowSizePart,windowSize-(6* (windowSize/25))));
			
			g2.draw(listOfLinesX.get(i));

		}

		for (int i = 0; i < 19; i++) { // LINES ON THE SCREEN 
			g2.setColor(new Color(76, 76, 76));
			listOfLinesY.add(new Line2D.Float(0,windowSizePart+i*windowSizePart,windowSize,windowSizePart+i*windowSizePart));
			
			g2.draw(listOfLinesY.get(i));

		}
	}

	/**
	 * Paint engel.
	 *
	 * @param g2 the g 2
	 */
	public void paintEngel(Graphics g2){
		UIEngel engel = new UIEngel(GameEngineData.getEngel());
		engel.paint(g2);
	}
	
	/**
	 * Paint ghost gizmos.
	 *
	 * @param g3 the g 3
	 */
	public void paintGhostGizmos(Graphics g3){
		g3.setColor(new Color(11, 73, 127));
		g3.fillOval(windowSize/4 - windowSize/25, windowSize - windowSize/25, (windowSize/25)*2, (windowSize/25)*2);
		g3.setColor(new Color(57, 102, 65));
		g3.fillOval(windowSize/4 + windowSize/2  - windowSize/25, windowSize - windowSize/25, (windowSize/25)*2, (windowSize/25)*2);		
	}
	
	/**
	 * Paint.
	 *
	 * @param g the g
	 */
	public void paint(Graphics g){
		for (int i = 0; i < GameEngineData.getSquareTakozList().size(); i++) {
			UISquareTakoz sq = new UISquareTakoz(GameEngineData.getSquareTakozList().get(i));
			sq.paint(g);
		}

		for (int i = 0; i < GameEngineData.getTriangularTakozList().size(); i++) {
			UITriangularTakoz tt = new UITriangularTakoz(GameEngineData.getTriangularTakozList().get(i));
			tt.paint(g);
		}
	}
	
	/**
	 * Firildak paint.
	 *
	 * @param g the g
	 */
	public void firildakPaint(Graphics g){
		for (int i = 0; i < GameEngineData.getFirildakList().size(); i++) {
			UIFirildak tt = new UIFirildak(GameEngineData.getFirildakList().get(i));
			tt.paint(g);
		}
	}
	
	/**
	 * Tokat paint.
	 *
	 * @param g the g
	 */
	public void tokatPaint(Graphics g){
		for (int i = 0; i < GameEngineData.getTokatList().size(); i++) {
			UITokat tt = new UITokat(GameEngineData.getTokatList().get(i));
			tt.paint(g);
		}

	}
}
