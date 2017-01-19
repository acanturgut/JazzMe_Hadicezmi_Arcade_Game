package ui.item;

import java.awt.Color;
import java.awt.Graphics;
import domain.item.EngineSquareTakoz;

/**
 * The Class UISquareTakoz.
 */
public class UISquareTakoz implements IUIItem{	
	
	/** The x. */
	int x;
	
	/** The y. */
	int y;
	
	/** The l. */
	int L;	
	
	/**
	 * Instantiates a new UI square takoz.
	 *
	 * @param st the st
	 */
	public UISquareTakoz(EngineSquareTakoz st){
		x = st.getX();
		y = st.getY();
		L = st.getL();
	}
	
	/* (non-Javadoc)
	 * @see ui.item.IUIItem#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g){
		g.setColor(new Color(0, 135, 81));
		g.fillRect(x, y, L, L);
	}
}
