package domain.item;
import java.awt.geom.Ellipse2D;
import java.util.Random;
import domain.physics.Vect;

/**
 * The Class EngineBall.
 */
public class EngineBall extends AEngineItem implements Runnable {

	/** The x. */
	private int x;
	
	/** The y. */
	private int y;
	
	/** The velocity vector. */
	private Vect velocityVector;
	
	/** The r. */
	private int r;
	
	/** The x step. */
	private int xStep;
	
	/** The y step. */
	private int yStep;
	
	/** The player. */
	private int player;

	/**
	 * Instantiates a new engine ball.
	 *
	 * @param x the x
	 * @param y the y
	 * @param r the r
	 */
	public EngineBall(int x, int y, int r) {
		Random k = new Random();
		int i = k.nextInt(100)+1;
		
		Random xStepRandomNumber = new Random();
		Random yStepRandomNumber = new Random();
		
		int intStepX = xStepRandomNumber.nextInt(5)+1;
		int intStepY = yStepRandomNumber.nextInt(5)+1;
		
		if(i > 50){
			xStep = intStepX;
			yStep = intStepY;
		}else{
			xStep = -intStepX;
			yStep = intStepY;
		}
		this.x = x;
		this.y = y;
		this.r = r;
		velocityVector = new Vect(xStep,yStep);
	}

	/**
	 * Bounding box.
	 *
	 * @return the ellipse 2 D
	 */
	public Ellipse2D boundingBox() {
		return new Ellipse2D.Double(x, y, 2*r, 2*r);
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		setVelocityVector(new Vect(velocityVector.x(), velocityVector.y() + 0.1));
		moveX();
		moveY();
	}

	/**
	 * Move X.
	 */
	public void moveX(){
		xStep = (int) velocityVector.x();
		int i = 0;
		if(xStep> 0){	
			while(true){
				if(Math.abs(xStep) == i){
					break;
				}
				i++;
				x++;
			}
		}else{
			while(true){
				if(Math.abs(xStep) == i){
					break;
				}
				i++;
				x--;
			}
		}
	}

	/**
	 * Move Y.
	 */
	public void moveY(){
		yStep = (int) velocityVector.y();
		int i = 0;
		if(yStep> 0){			
			while(true){
				if(Math.abs(yStep) == i){	
					break;
				}
				i++;
				y++;
			}
		}else{
			while(true){
				if(Math.abs(yStep) == i){
					break;
				}
				i++;
				y--;
			}
		}
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
	 * Gets the r.
	 *
	 * @return the r
	 */
	public int getR() {
		return r;
	}

	/**
	 * Sets the r.
	 *
	 * @param r the new r
	 */
	public void setR(int r) {
		this.r = r;
	}

	/**
	 * Gets the velocity vector.
	 *
	 * @return the velocity vector
	 */
	public Vect getVelocityVector() {
		return velocityVector;
	}
	
	/**
	 * Sets the velocity vector.
	 *
	 * @param velocityVector the new velocity vector
	 */
	public void setVelocityVector(Vect velocityVector) {
		xStep = (int) velocityVector.x();
		yStep = (int) velocityVector.y();
		this.velocityVector = velocityVector;
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
}