package ui.item;

import java.awt.Color;
import java.awt.Graphics;

import domain.item.EngineEngel;

/**
 * The Class UIEngel.
 */
public class UIEngel implements IUIItem{
	
	/** The width. */
	int width;
	
	/** The height. */
	int height;
	
	/** The x. */
	int x;
	
	/** The y. */
	int y;
	
	/**
	 * Instantiates a new UI engel.
	 *
	 * @param engel the engel
	 */
	public UIEngel(EngineEngel engel) {
		x = engel.getX();
		y = engel.getY();
		width = engel.getWidth();
		height = engel.getHeight();
	}

	/* (non-Javadoc)
	 * @see ui.item.IUIItem#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g2) {
		g2.setColor(Color.CYAN);
		g2.fillRect(x, y, width, height);
		
	}
}