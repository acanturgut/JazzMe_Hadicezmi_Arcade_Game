package ui.item;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import domain.item.EngineFirildak;

/**
 * The Class UIFirildak.
 */
public class UIFirildak implements Runnable, IUIItem{

	/** The x. */
	int x;
	
	/** The y. */
	int y;
	
	/** The l. */
	int L;
	
	/** The player. */
	int player;
	
	/** The rotation. */
	double rotation = 0.0;

	/**
	 * Instantiates a new UI firildak.
	 *
	 * @param f the f
	 */
	public UIFirildak(EngineFirildak f){
		x = f.getX();
		y = f.getY();
		L = f.getL();	
		player = f.getPlayer();
	}

	/* (non-Javadoc)
	 * @see ui.item.IUIItem#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setColor(new Color(91, 186, 173));
		Rectangle rect2 = new Rectangle(x, y, L, L);
		Path2D.Double path = new Path2D.Double();
		path.append(rect2, false);
		AffineTransform t = new AffineTransform();
		t.rotate(rotation, x+L/2, y+L/2);
		path.transform(t);
		g2.fill(path);
	}

	/**
	 * Bounding box.
	 *
	 * @return the rectangle
	 */
	public Rectangle boundingBox() {
		return new Rectangle(x, y, L, L);
	}

	/**
	 * Rotate.
	 */
	public void rotate(){
		if(player == 1){
			Rectangle rect2 = new Rectangle(x, y, L, L);
			rotation = rotation + 0.05;
			Path2D.Double path = new Path2D.Double();
			path.append(rect2, false);
			AffineTransform t = new AffineTransform();
			t.rotate(rotation, x+L/2, y+L/2);
			path.transform(t);
		}else{
			Rectangle rect2 = new Rectangle(x, y, L, L);
			rotation = rotation - 0.05;	
			Path2D.Double path = new Path2D.Double();
			path.append(rect2, false);
			AffineTransform t = new AffineTransform();
			t.rotate(rotation, x+L/2, y+L/2);
			path.transform(t);
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run(){
		rotate();
	}
}

