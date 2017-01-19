package ui.item;

import java.awt.Color;
import java.awt.Graphics;
import domain.item.EngineBall;

/**
 * The Class UIBall.
 */
public class UIBall implements IUIItem{
	
	/** The x. */
	private int x;
	
	/** The y. */
	private int y;
	
	/** The r. */
	private int r;
	
	/** The color. */
	private Color color = new Color(210, 234, 252);
	
	/**
	 * Instantiates a new UI ball.
	 *
	 * @param b the b
	 */
	public UIBall(EngineBall b) {

		this.x = b.getX();
		this.y = b.getY();
		this.r = b.getR();	
	}

	/* (non-Javadoc)
	 * @see ui.item.IUIItem#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		
		g.setColor(color);
		g.fillOval(x, y, 2*r, 2*r);
	}
}