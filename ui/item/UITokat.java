package ui.item;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import domain.gameengine.GameEngineData;
import domain.item.EngineTokat;

/**
 * The Class UITokat.
 */
public class UITokat implements Runnable, IUIItem{

	/** The x. */
	private int x;
	
	/** The y. */
	private int y;
	
	/** The l. */
	private int L;	
	
	/** The tokat oval 1 X. */
	private int tokatOval1X = x;
	
	/** The tokat oval 1 Y. */
	private int tokatOval1Y = y;	
	
	/** The state. */
	private int state;
	
	/** The tokat X rec. */
	private int tokatXRec = x;
	
	/** The tokat Y rec. */
	private int tokatYRec = y + L/4;
	
	/** The tokat oval 2 X. */
	private int tokatOval2X = x ;
	
	/** The tokat oval 2 y. */
	private int tokatOval2y = y + L + L/2;
	
	/** The tokat oval 2 X temp. */
	private int  tokatOval2XTemp;
	
	/** The tokat oval 2 Y temp. */
	private int  tokatOval2YTemp;
	
	/** The normal invoke. */
	private boolean normalInvoke = true;
	
	/** The in edit mode. */
	private boolean inEditMode = true;
	
	/** The invoke. */
	private boolean invoke = false;
	
	/** The rotation. */
	private double rotation = 0;
	
	/** The mid rectangle. */
	private Rectangle midRectangle;

	/** The sol tepe X. */
	int solTepeX;
	
	/** The sol alt X. */
	int solAltX;
	
	/** The sol tepe Y. */
	int solTepeY;
	
	/** The sol alt Y. */
	int solAltY;
	
	/** The sag tepe X. */
	int sagTepeX;
	
	/** The sag alt X. */
	int sagAltX;
	
	/** The sag alt Y. */
	int sagAltY;

	/**
	 * Instantiates a new UI tokat.
	 *
	 * @param tokat the tokat
	 */
	public UITokat(EngineTokat tokat){
		
		this.x = tokat.getX();
		this.y = tokat.getY();
		this.state = tokat.getState();
		this.L = tokat.getL();
		
		tokatOval2X =x;
		tokatOval2y = y + L + L/2;;
		tokatOval2XTemp =  tokatOval2X;
		tokatOval2YTemp =  tokatOval2y;
		
		if(state%2 == 1){

			tokatOval1X = x;
			tokatOval1Y = y;
			tokatXRec = x;
			tokatYRec = y + L/4;
			tokatOval2X = x ;
			tokatOval2y = y + L + L/2;
			solTepeX = tokatXRec;
			solAltX = tokatXRec;
			solTepeY = tokatYRec;
			solAltY = tokatYRec + (3 * L / 4);
			sagTepeX = tokatXRec + L/2;
			sagAltX = tokatXRec + L/2;
			sagAltY = tokatYRec + (3 * L / 4);

		}else if(state%2 == 0){

			tokatOval1X = x + L + L/2;
			tokatOval1Y = y;
			tokatXRec = x + L + L/2;
			tokatYRec = y + L/4;
			tokatOval2X = x + L + L/2;
			tokatOval2y = y + L + L/2;
			solTepeX = tokatXRec;
			solAltX = tokatXRec;
			solTepeY = tokatYRec;
			solAltY = tokatYRec + (3 * L / 4);
			sagTepeX = tokatXRec + L/2;
			sagAltX = tokatXRec + L/2;
			sagAltY = tokatYRec + (3 * L / 4);
		}
	}

	/* (non-Javadoc)
	 * @see ui.item.IUIItem#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g){
		if(inEditMode){
			if(state%2 == 1){
				tokatOval1X = x;
				tokatOval1Y = y;
				tokatXRec = x;
				tokatYRec = y + L/4;
				tokatOval2X = x ;
				tokatOval2y = y + L + L/2;
			}else if(state%2 == 0){
				tokatOval1X = x + L + L/2;
				tokatOval1Y = y;
				tokatXRec = x + L + L/2;
				tokatYRec = y + L/4;
				tokatOval2X = x + L + L/2;
				tokatOval2y = y + L + L/2;
			}
		}
		g.setColor(new Color(255, 195, 33));
		g.fillOval(tokatOval2X, tokatOval2y, L/2, L/2);
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setColor(new Color(255, 195, 33));
		midRectangle = new Rectangle(tokatXRec, tokatYRec, L/2, (L/2 + L/4) * 2);
		Path2D.Double path = new Path2D.Double();
		path.append(midRectangle, false);
		AffineTransform t = new AffineTransform();
		t.rotate(Math.toRadians(rotation), tokatXRec + L/4, tokatYRec);
		path.transform(t);
		g2.fill(path);
		g.fillOval(tokatOval1X, tokatOval1Y, L/2, L/2);
	}

	/**
	 * Bounding box.
	 *
	 * @return the rectangle
	 */
	public Rectangle boundingBox() {
		return new Rectangle(x, y, 2*L, 2*L);
	}

	/**
	 * Rotate.
	 */
	public void rotate(){
		double fast = 10;
		midRectangle = new Rectangle(tokatXRec, tokatYRec, L/2, (L/2 + L/4) * 2);
		Path2D.Double path = new Path2D.Double();
		path.append(midRectangle, false);
		AffineTransform t = new AffineTransform();
		t.rotate(Math.toRadians(rotation), tokatXRec + L/4, tokatYRec);
		path.transform(t);
		if(state%2 == 1){
			if(normalInvoke){
				if(rotation > - 90){
					rotation = rotation - fast;
					double rad = rotation * 0.0174533;
					int heightSquare = (3*L/2) * (3*L/2);
					tokatOval2X = (int) (tokatOval2XTemp + (Math.sqrt((2 * heightSquare) - (2 * heightSquare) * Math.cos(rad))) * Math.sin(90*0.0174533 - rad/2)); 
					tokatOval2y = (int) (tokatOval2YTemp + (Math.sqrt((2 * heightSquare) - (2 * heightSquare) * Math.cos(rad))) * Math.sin(rad/2)) + 1;
				}else if(rotation == -90){
					invoke = false;
					normalInvoke = false;
				}
			}else{
				if(rotation < 0){
					rotation = rotation + fast;
					double rad = rotation * 0.0174533;
					int heightSquare = (3*L/2) * (3*L/2);
					tokatOval2X = (int) (tokatOval2XTemp + (Math.sqrt((2 * heightSquare) - (2 * heightSquare) * Math.cos(rad))) * Math.sin(90*0.0174533 - rad/2)); 
					tokatOval2y = (int) (tokatOval2YTemp + (Math.sqrt((2 * heightSquare) - (2 * heightSquare) * Math.cos(rad))) * Math.sin(rad/2)) + 1;
				}else if(rotation == 0){
					invoke = false;
					normalInvoke = true;
				}
			}

		}else if(state%2 == 0){
			if(normalInvoke){
				if(rotation <  90){
					rotation = rotation + fast;
					double rad = rotation * 0.0174533;
					int heightSquare = (3*L/2) * (3*L/2);
					tokatOval2X = (int) (tokatOval2XTemp + 3*(L/2) - (Math.sqrt((2 * heightSquare) - (2 * heightSquare) * Math.cos(rad))) * Math.sin(90*0.0174533 - rad/2)); 
					tokatOval2y = (int) (tokatOval2YTemp - (Math.sqrt((2 * heightSquare) - (2 * heightSquare) * Math.cos(rad))) * Math.sin(rad/2)) + 1;			
				}else if(rotation == 90){
					invoke = false;
					normalInvoke = false;
				}
			}else{
				if(rotation > 0){
					rotation = rotation - fast;
					double rad = rotation * 0.0174533;
					int heightSquare = (3*L/2) * (3*L/2);
					tokatOval2X = (int) (tokatOval2XTemp + 3*(L/2) - (Math.sqrt((2 * heightSquare) - (2 * heightSquare) * Math.cos(rad))) * Math.sin(90*0.0174533 - rad/2)); 
					tokatOval2y = (int) (tokatOval2YTemp - (Math.sqrt((2 * heightSquare) - (2 * heightSquare) * Math.cos(rad))) * Math.sin(rad/2)) + 1;
				}else if(rotation == 0){
					invoke = false;
					normalInvoke = true;
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		inEditMode = false;
		if(isInvoke()){
			rotate();
		}
	}

	/**
	 * Make others true.
	 */
	public void makeOthersTrue() {

		for (int i = 0; i < GameEngineData.getTriangularTakozList().size(); i++) {
			GameEngineData.getTriangularTakozList().get(i).setTriangleAvailableForCollision(true);
		}

		for (int i = 0; i < GameEngineData.getSquareTakozList().size(); i++) {
			GameEngineData.getSquareTakozList().get(i).setSquareAvailable(true);
		}
	}

	/**
	 * Checks if is invoke.
	 *
	 * @return true, if is invoke
	 */
	public boolean isInvoke() {
		return invoke;
	}

	/**
	 * Sets the invoke.
	 *
	 * @param invoke the new invoke
	 */
	public void setInvoke(boolean invoke) {
		this.invoke = invoke;
	}
}
