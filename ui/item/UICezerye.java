package ui.item;

import java.awt.Color;
import java.awt.Graphics;
import domain.item.EngineCezerye;

/**
 * The Class UICezerye.
 */
public class UICezerye implements IUIItem {
	
	/** The x. */
	int x;
	
	/** The y. */
	int y;
	
	/** The l. */
	int L;
	
	/**
	 * Instantiates a new UI cezerye.
	 *
	 * @param c the c
	 */
	public UICezerye(EngineCezerye c){
		x = c.getX();
		y = c.getY();
		L = c.getL();
	}
	
	/* (non-Javadoc)
	 * @see ui.item.IUIItem#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g){
		g.setColor(new Color(179, 111, 214));
		g.fillRect(x, y, L, L);
	}
}
