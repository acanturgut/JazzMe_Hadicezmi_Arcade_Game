package ui.item;

import java.awt.Color;
import java.awt.Graphics;

import domain.item.EngineCezmi;

/**
 * The Class UICezmi.
 */
public class UICezmi implements IUIItem{

	/** The x. */
	int x;
	
	/** The y. */
	int y;
	
	/** The radius. */
	int radius;
	
	/** The color. */
	Color color;

	/**
	 * Instantiates a new UI cezmi.
	 *
	 * @param cezmi the cezmi
	 */
	public UICezmi(EngineCezmi cezmi){
		
		this.x = cezmi.getX();
		this.y = cezmi.getY();
		this.radius = cezmi.getRadius();
		this.color = cezmi.getColor();
		
	}
	
	/* (non-Javadoc)
	 * @see ui.item.IUIItem#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		g.setColor(color);
		g.fillOval(x, y, 2*radius, 2*radius);

	}
}
