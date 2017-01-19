package domain.item;

import java.awt.Color;
import java.awt.Rectangle;
import domain.gameengine.GameEngineAudioHandler;
import domain.gameengine.GameEngineData;
import domain.physics.Circle;
import domain.physics.Geometry;
import domain.physics.Vect;

/**
 * The Class EngineCezmi.
 */
public class EngineCezmi extends AEngineItem{

	/** The radius. */
	private int radius;
	
	/** The y. */
	private int x,y;
	
	/** The color cezmi. */
	private Color colorCezmi;
	
	/** The is available for collision. */
	private boolean isAvailableForCollision = true;
	
	/** The collision counter. */
	private int collisionCounter = 0;
	
	/** The player. */
	private int player;
	
	/** The cezmi step. */
	private int cezmiStep = 1;
	
	/**
	 * Instantiates a new engine cezmi.
	 *
	 * @param c the c
	 * @param player the player
	 */
	public EngineCezmi(Color c, int player){
		this.colorCezmi = c;
		this.player = player;
	}

	/**
	 * Cezmi collision.
	 *
	 * @param ball the ball
	 */
	public void cezmiCollision(EngineBall ball){
		
		Vect centerOfCezmi = new Vect(getCenterX(),getCenterY());
		Vect centerOfBall = new Vect(ball.getX() + ball.getR(), ball.getY() + ball.getR());		
		Circle cezmi1 = new Circle(centerOfCezmi, getRadius());
		Circle ballCircle = new Circle(centerOfBall, ball.getR());
		int cezmi1CenterX = getCenterX();
		int cezmi1CenterY = getCenterY();
		int cezmi1Radius = getRadius();	
		Vect velocityVector = ball.getVelocityVector();
		
		double distanceDiffCezmi1 = Math.sqrt((double)(Math.pow(ball.getX() + ball.getR() - cezmi1CenterX, 2) + Math.pow(ball.getY() + ball.getR() - cezmi1CenterY, 2)));
		
		if ((int)distanceDiffCezmi1 < ball.getR() + cezmi1Radius + 10){

			if(isAvailableForCollision){
				 ball.setVelocityVector(Geometry.reflectRotatingCircle(cezmi1 ,centerOfCezmi, 0, ballCircle, velocityVector));
				isAvailableForCollision = false;
				collisionCounter = 0;
				GameEngineAudioHandler.getInstance().makeDropSound();
				ball.setPlayer(player);
				if(ball.getPlayer() == 1){
					GameEngineData.setNoWallPoint1(true);
					GameEngineData.setEngelPoint1(true);
					GameEngineData.setRoofPoint1(true);
				}
				else{
					GameEngineData.setNoWallPoint2(true);
					GameEngineData.setEngelPoint2(true);
					GameEngineData.setRoofPoint2(true);
				}
			}
		}
	}

	/**
	 * Run.
	 */
	public void run() {
		collisionCounter++;
		
		
		if(GameEngineData.getLevel() == 1){
			cezmiCollision(GameEngineData.getBallList().get(0));
		}else{
			cezmiCollision(GameEngineData.getBallList().get(0));
			cezmiCollision(GameEngineData.getBallList().get(1));
		}
		if(collisionCounter > 300){
			isAvailableForCollision = true;
		}	
	}
	
	/**
	 * Gets the center X.
	 *
	 * @return the center X
	 */
	public int getCenterX(){
		return x + radius;
	}

	/**
	 * Gets the center Y.
	 *
	 * @return the center Y
	 */
	public int getCenterY(){
		return y + radius;
	}

	/**
	 * Move left.
	 */
	public void moveLeft() {
		x-=cezmiStep;
	}

	/**
	 * Move right.
	 */
	public void moveRight() {		
		x+=cezmiStep;
	}

	/**
	 * Move up.
	 */
	public void moveUp(){
		y-=cezmiStep;
	}

	/**
	 * Move down.
	 */
	public void moveDown(){
		y+=cezmiStep;
	}

	/**
	 * Gets the radius.
	 *
	 * @return the radius
	 */
	public int getRadius() {
		return radius;
	}

	/**
	 * Sets the l.
	 *
	 * @param windowSize the new l
	 */
	public void setL(int windowSize) {
		radius = (windowSize/25);
	}

	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets the x.
	 *
	 * @param x the new x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the y.
	 *
	 * @param y the new y
	 */
	public void setY(int y) {
		this.y = y;
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
	 * Gets the color.
	 *
	 * @return the color
	 */
	public Color getColor() {
		return colorCezmi;
	}

	/**
	 * Gets the player.
	 *
	 * @return the player
	 */
	public int getPlayer() {
		return player;
	}

	/**
	 * Sets the player.
	 *
	 * @param player the new player
	 */
	public void setPlayer(int player) {
		this.player = player;
	}

	/**
	 * Gets the cezmi step.
	 *
	 * @return the cezmi step
	 */
	public int getCezmiStep() {
		return cezmiStep;
	}

	/**
	 * Sets the cezmi step.
	 *
	 * @param cezmiStep the new cezmi step
	 */
	public void setCezmiStep(int cezmiStep) {
		this.cezmiStep = cezmiStep;
	}
}
