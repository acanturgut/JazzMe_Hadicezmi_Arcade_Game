package domain.item;
import java.awt.Rectangle;
import domain.gameengine.GameEngineAudioHandler;
import domain.gameengine.GameEngineData;
import domain.physics.Circle;
import domain.physics.Geometry;
import domain.physics.LineSegment;
import domain.physics.Vect;
/**
 * The Class EngineEngel.
 */
public class EngineEngel implements Runnable {
	/** The x. */
	private  int x;
	
	/** The y. */
	private  int y;
	
	/** The width. */
	private  int width;
	
	/** The height. */
	private  int height;
	
	/** The left wall. */
	LineSegment leftWall;
	
	/** The right wall. */
	LineSegment rightWall;
	
	/** The top wall. */
	LineSegment topWall;
	
	/** The left top corner. */
	Circle leftTopCorner;
	
	/** The right top corner. */
	Circle rightTopCorner;
	
	/** The is engel hit. */
	private boolean isEngelAvailableForCollision = true;
	private int collisionTime;
	/**
	 * Instantiates a new engine engel.
	 *
	 * @param x the x
	 * @param y the y
	 * @param width the width
	 * @param height the height
	 */
	public EngineEngel(int x,int y,int width,int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		int radius = 1; 
		leftWall = new LineSegment(x,y+radius,x,y+height);
		rightWall = new LineSegment(x+width,y+radius,x+width,y+height);
		topWall = new LineSegment(x+radius,y,x+width-radius,y);
		leftTopCorner = new Circle(new Vect(x + radius, y + radius),radius);
		rightTopCorner = new Circle(new Vect(x+ width - radius ,y+radius), radius);
	}
	/**
	 * Engel collision.
	 *
	 * @param ball the ball
	 */
	public void engelCollision(EngineBall ball){
		Vect centerOfBall = new Vect(ball.getX() + ball.getR(), ball.getY() + ball.getR());
		Circle ballCircle = new Circle(centerOfBall, ball.getR());
		
		
		if (isEngelAvailableForCollision) {
			if(ball.boundingBox().getBounds2D().intersectsLine(getLeftWall().toLine2D())){
				ball.setVelocityVector(Geometry.reflectWall(getLeftWall(), ball.getVelocityVector()));
				notifyCollision();
				isEngelAvailableForCollision = false;
				GameEngineAudioHandler.getInstance().makeDropSound();
				GameEngineData.setTokatPoint(false);
				GameEngineData.setRoofPoint1(false);
				GameEngineData.setRoofPoint2(false);
				ball.setX(ball.getX() + 5);
				if(GameEngineData.getEngelPoint1()){
					GameEngineData.getPlayer1().setScore(GameEngineData.getPlayer1().getScore() - 0.5);
				}
				if(GameEngineData.getEngelPoint2()){
					GameEngineData.getPlayer2().setScore(GameEngineData.getPlayer2().getScore() - 0.5);
				}
				collisionTime=0;
				return;
			}else if(ball.boundingBox().getBounds2D().intersectsLine(getRightWall().toLine2D())){
				ball.setVelocityVector(Geometry.reflectWall(getRightWall(), ball.getVelocityVector()));
				notifyCollision();
				isEngelAvailableForCollision = false;
				GameEngineAudioHandler.getInstance().makeDropSound();
				GameEngineData.setTokatPoint(false);
				GameEngineData.setRoofPoint1(false);
				GameEngineData.setRoofPoint2(false);
				ball.setX(ball.getX() - 5);
				if(GameEngineData.getEngelPoint1()){
					GameEngineData.getPlayer1().setScore(GameEngineData.getPlayer1().getScore() - 0.5);
				}
				if(GameEngineData.getEngelPoint2()){
					GameEngineData.getPlayer2().setScore(GameEngineData.getPlayer2().getScore() - 0.5);
				}
				collisionTime=0;
				return;
			}else if(ball.boundingBox().getBounds2D().intersectsLine(getTopWall().toLine2D())){
				ball.setVelocityVector(Geometry.reflectWall(getTopWall(), ball.getVelocityVector()));
				notifyCollision();
				isEngelAvailableForCollision = false;
				GameEngineAudioHandler.getInstance().makeDropSound();
				GameEngineData.setTokatPoint(false);
				GameEngineData.setRoofPoint1(false);
				GameEngineData.setRoofPoint2(false);
				ball.setY(ball.getY() - 5);
				if(GameEngineData.getEngelPoint1()){
					GameEngineData.getPlayer1().setScore(GameEngineData.getPlayer1().getScore() - 0.5);
				}
				if(GameEngineData.getEngelPoint2()){
					GameEngineData.getPlayer2().setScore(GameEngineData.getPlayer2().getScore() - 0.5);
				}
				collisionTime=0;
				return;
			}else if(ball.boundingBox().getBounds2D().intersects(getLeftTopCorner().toEllipse2D().getBounds2D())){
				ball.setVelocityVector(Geometry.reflectRotatingCircle(getLeftTopCorner() ,getLeftTopCorner().getCenter(), 0, ballCircle, ball.getVelocityVector()));
				notifyCollision();
				isEngelAvailableForCollision = false;
				ball.setY(ball.getY() - 5);
				ball.setX(ball.getX() - 5);
				GameEngineAudioHandler.getInstance().makeDropSound();
				GameEngineData.setTokatPoint(false);
				GameEngineData.setRoofPoint1(false);
				GameEngineData.setRoofPoint2(false);
				if(GameEngineData.getEngelPoint1()){
					GameEngineData.getPlayer1().setScore(GameEngineData.getPlayer1().getScore() - 0.5);
				}
				if(GameEngineData.getEngelPoint2()){
					GameEngineData.getPlayer2().setScore(GameEngineData.getPlayer2().getScore() - 0.5);
				}
				collisionTime=0;
				return;
			}
			else if(ball.boundingBox().getBounds2D().intersects(getRightTopCorner().toEllipse2D().getBounds2D())){
				ball.setVelocityVector(Geometry.reflectRotatingCircle(getRightTopCorner() ,getRightTopCorner().getCenter(), 0, ballCircle, ball.getVelocityVector()));
				notifyCollision();
				isEngelAvailableForCollision = false;
				ball.setY(ball.getY() - 5);
				ball.setX(ball.getX() + 5);
				GameEngineAudioHandler.getInstance().makeDropSound();
				GameEngineData.setTokatPoint(false);
				GameEngineData.setRoofPoint1(false);
				GameEngineData.setRoofPoint2(false);
				if(GameEngineData.getEngelPoint1()){
					GameEngineData.getPlayer1().setScore(GameEngineData.getPlayer1().getScore() - 0.5);
				}
				if(GameEngineData.getEngelPoint2()){
					GameEngineData.getPlayer2().setScore(GameEngineData.getPlayer2().getScore() - 0.5);
				}
				collisionTime=0;
				return;
			}
		}
	}
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		collisionTime++;
		if(GameEngineData.getLevel() == 1){
			engelCollision(GameEngineData.getBallList().get(0));
		}else{
			engelCollision(GameEngineData.getBallList().get(0));
			engelCollision(GameEngineData.getBallList().get(1));
		}
		
		if(collisionTime > 21){
			isEngelAvailableForCollision = true;
		}
	}
	
	/**
	 * Bounding box.
	 *
	 * @return the rectangle
	 */
	public Rectangle boundingBox() {
		return new Rectangle(x, y, width, height);
	}
	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public  int getX() {
		return x;
	}
	/**
	 * Sets the x.
	 *
	 * @param x the new x
	 */
	public  void setX(int x) {
		this.x = x;
	}
	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public  int getY() {
		return y;
	}
	/**
	 * Sets the y.
	 *
	 * @param y the new y
	 */
	public  void setY(int y) {
		this.y = y;
	}
	/**
	 * Gets the width.
	 *
	 * @return the width
	 */
	public  int getWidth() {
		return width;
	}
	/**
	 * Sets the width.
	 *
	 * @param width the new width
	 */
	public  void setWidth(int width) {
		this.width = width;
	}
	/**
	 * Gets the height.
	 *
	 * @return the height
	 */
	public int getHeight() {
		return this.height;
	}
	/**
	 * Sets the height.
	 *
	 * @param height the new height
	 */
	public  void setHeight(int height) {
		this.height = height;
	}
	/**
	 * Gets the left wall.
	 *
	 * @return the left wall
	 */
	public LineSegment getLeftWall() {
		return leftWall;
	}
	/**
	 * Sets the left wall.
	 *
	 * @param leftWall the new left wall
	 */
	public void setLeftWall(LineSegment leftWall) {
		this.leftWall = leftWall;
	}
	/**
	 * Gets the right wall.
	 *
	 * @return the right wall
	 */
	public LineSegment getRightWall() {
		return rightWall;
	}
	/**
	 * Sets the right wall.
	 *
	 * @param rightWall the new right wall
	 */
	public void setRightWall(LineSegment rightWall) {
		this.rightWall = rightWall;
	}
	/**
	 * Gets the top wall.
	 *
	 * @return the top wall
	 */
	public LineSegment getTopWall() {
		return topWall;
	}
	/**
	 * Sets the top wall.
	 *
	 * @param topWall the new top wall
	 */
	public void setTopWall(LineSegment topWall) {
		this.topWall = topWall;
	}
	/**
	 * Gets the left top corner.
	 *
	 * @return the left top corner
	 */
	public Circle getLeftTopCorner() {
		return leftTopCorner;
	}
	/**
	 * Sets the left top corner.
	 *
	 * @param leftTopCorner the new left top corner
	 */
	public void setLeftTopCorner(Circle leftTopCorner) {
		this.leftTopCorner = leftTopCorner;
	}
	/**
	 * Gets the right top corner.
	 *
	 * @return the right top corner
	 */
	public Circle getRightTopCorner() {
		return rightTopCorner;
	}
	/**
	 * Sets the right top corner.
	 *
	 * @param rightTopCorner the new right top corner
	 */
	public void setRightTopCorner(Circle rightTopCorner) {
		this.rightTopCorner = rightTopCorner;
	}
	public void setEngelAvailableForCollision(boolean isEngelAvailableForCollision) {
		this.isEngelAvailableForCollision = isEngelAvailableForCollision;
	}
	public void notifyCollision(){
		for(int i=0;i<GameEngineData.getTriangularTakozList().size();i++){
			GameEngineData.getTriangularTakozList().get(i).setTriangleAvailableForCollision(true);
		}
		for(int i=0;i<GameEngineData.getSquareTakozList().size();i++){
			GameEngineData.getSquareTakozList().get(i).setSquareTakozAvailableForCollision(true);
		}
		for(int i=0;i<GameEngineData.getTokatList().size();i++){
			GameEngineData.getTokatList().get(i).setTokatAvailableForCollision(true);
		}
		for(int i=0;i<GameEngineData.getFirildakList().size();i++){
			GameEngineData.getFirildakList().get(i).setFirildakAvailableForCollision(true);
		}
		GameEngineData.getEngel().setEngelAvailableForCollision(true);
		EngineBorder.setBorderAvailableForCollision(true);
	}
}