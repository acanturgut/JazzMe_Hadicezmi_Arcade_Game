package ui.item;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import domain.item.EngineTriangularTakoz;

/**
 * The Class UITriangularTakoz.
 */
public class UITriangularTakoz implements IUIItem{

	/** The x. */
	int x;
	
	/** The y. */
	int y;
	
	/** The l. */
	int L;
	
	/** The state. */
	int state;
	
	/**
	 * Instantiates a new UI triangular takoz.
	 *
	 * @param tTakoz the t takoz
	 */
	public UITriangularTakoz(EngineTriangularTakoz tTakoz) {
		
		x = tTakoz.getX();
		y = tTakoz.getY();
		L = tTakoz.getL();
		state = tTakoz.getState();
	}

	/* (non-Javadoc)
	 * @see ui.item.IUIItem#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g){

		int x1TriAngle = x; 
		int x2TriAngle = x;
		int x3TriAngle = x + L;

		int y1TriAngle = y;
		int y2TriAngle = y + L;
		int y3TriAngle = y + L;

		Polygon tri = new Polygon();

		if(state%4 == 0){

			x1TriAngle = x; 
			x2TriAngle = x;
			x3TriAngle = x + L;

			y1TriAngle = y;
			y2TriAngle = y + L;
			y3TriAngle = y;

		}else if(state%4 == 1){

			x1TriAngle = x; 
			x2TriAngle = x;
			x3TriAngle = x + L;

			y1TriAngle = y;
			y2TriAngle = y + L;
			y3TriAngle = y + L;	

		}else if(state%4 == 2){

			x1TriAngle = x; 
			x2TriAngle = x + L;
			x3TriAngle = x + L;

			y1TriAngle = y;
			y2TriAngle = y;
			y3TriAngle = y + L;
			
		}else if(state%4 == 3){

			x1TriAngle = x + L; 
			x2TriAngle = x;
			x3TriAngle = x + L;

			y1TriAngle = y;
			y2TriAngle = y + L;
			y3TriAngle = y + L;
		}
		tri.addPoint(x1TriAngle, y1TriAngle);
		tri.addPoint(x2TriAngle, y2TriAngle);
		tri.addPoint(x3TriAngle, y3TriAngle);

		g.setColor(new Color(204, 59, 2));
		g.fillPolygon(tri);
	}		
}