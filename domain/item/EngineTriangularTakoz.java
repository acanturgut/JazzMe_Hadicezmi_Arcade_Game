package domain.item;
import java.awt.Rectangle;
import domain.gameengine.GameEngineAudioHandler;
import domain.gameengine.GameEngineData;
import domain.physics.Circle;
import domain.physics.Geometry;
import domain.physics.LineSegment;
import domain.physics.Vect;
/**
 * The Class EngineTriangularTakoz.
 */
public class EngineTriangularTakoz extends EngineGizmo implements Runnable{
	/** The top circle. */
	private Circle topCircle;
	/** The left bottom circle. */
	private Circle leftBottomCircle;
	/** The right bottom circle. */
	private Circle rightBottomCircle;
	/** The center of top circle. */
	private Vect centerOfTopCircle;
	/** The center of left bottom circle. */
	private Vect centerOfLeftBottomCircle;
	/** The center of right bottom circle. */
	private Vect centerOfRightBottomCircle;
	/** The hypotenuse. */
	private LineSegment hypotenuse;
	/** The left line. */
	private LineSegment leftLine;
	/** The bottom line. */
	private LineSegment bottomLine;
	/** The is triangle available for collision. */
	private boolean isTriangleAvailableForCollision=true;
	/** The collision time. */
	private long collisionTime = 0;
	/**
	 * Instantiates a new engine triangular takoz.
	 *
	 * @param x the x
	 * @param y the y
	 * @param l the l
	 * @param state the state
	 * @param player the player
	 */
	public EngineTriangularTakoz(int x, int y, int l, int state, int player) {
		super(x, y, l, state, player);
		super.type = "triangularTakoz";
	}
	/**
	 * Sets the.
	 */
	public void set(){
		int radius = 1;
		if(state%4 == 0){
			topCircle = new Circle(x+radius,y+radius,radius);
			setCenterOfTopCircle(new Vect(x+radius,y+radius));
			leftBottomCircle = new Circle(x+radius,y+L-radius,radius);
			setCenterOfLeftBottomCircle(new Vect(x+radius,y+L-radius));
			rightBottomCircle = new Circle(x+L-radius,y+radius,radius);
			setCenterOfRightBottomCircle(new Vect(x+L-radius,y+radius)); 
			hypotenuse = new LineSegment(x+L,y,x,y+L);
			leftLine = new LineSegment(x,y+2*radius,x,y+L-2*radius);
			bottomLine = new LineSegment(x+2*radius,y,x+L-2*radius,y);;
		}else if(state%4 == 1){
			topCircle = new Circle(x+radius,y+radius,radius);
			setCenterOfTopCircle(new Vect(x+radius,y+radius));
			leftBottomCircle = new Circle(x+radius,y+L-radius,radius);
			setCenterOfLeftBottomCircle(new Vect(x+radius,y+L-radius));
			rightBottomCircle = new Circle(x+L-radius,y+L-radius,radius);
			setCenterOfRightBottomCircle(new Vect(x+L-radius,y+L-radius)); 
			hypotenuse = new LineSegment(x, y, x + L , y + L);
			leftLine = new LineSegment(x,y+2*radius,x,y+L-2*radius);
			bottomLine = new LineSegment(x+2*radius,y+L,x+L-2*radius,y+L);
		}else if(state%4 == 2){
			topCircle = new Circle(x+radius,y+radius,radius);
			setCenterOfTopCircle(new Vect(x+radius,y+radius));
			leftBottomCircle = new Circle(x+L-radius,y+radius,radius);
			setCenterOfLeftBottomCircle(new Vect(x+L-radius,y+radius));
			rightBottomCircle = new Circle(x+L-radius,y+L-radius,radius);
			setCenterOfRightBottomCircle(new Vect(x+L-radius,y+L-radius)); 
			hypotenuse = new LineSegment(x,y,x+L,y+L);
			leftLine = new LineSegment(x+2*radius,y,x+L-2*radius,y);
			bottomLine = new LineSegment(x+L,y+2*radius,x+L,y+L-2*radius);
		}else if(state%4 == 3){
			topCircle = new Circle(x+L-radius,y+radius,radius);
			setCenterOfTopCircle(new Vect(x+L-radius,y+radius));
			leftBottomCircle = new Circle(x+radius,y+L-radius,radius);
			setCenterOfLeftBottomCircle(new Vect(x+radius,y+L-radius));
			rightBottomCircle = new Circle(x+L-radius,y+L-radius,radius);
			setCenterOfRightBottomCircle(new Vect(x+L-radius,y+L-radius)); 
			hypotenuse = new LineSegment(x+L, y, x , y + L);
			leftLine = new LineSegment(x+L,y+2*radius,x+L,y+L-2*radius);
			bottomLine = new LineSegment(x+2*radius,y+L,x+L-2*radius,y+L);
		}
	}
	/**
	 * Triangular takoz collision.
	 *
	 * @param ball the ball
	 */
	public void triangularTakozCollision(EngineBall ball) {
		if(isTriangleAvailableForCollision){
			int ballX=ball.getX();
			int ballY=ball.getY();
			Circle ballA=new Circle(ballX,ballY,ball.getR());
			Vect ballVelocity = ball.getVelocityVector();
			if(ball.boundingBox().getBounds2D().intersects(topCircle.toEllipse2D().getBounds2D())){
				GameEngineAudioHandler.getInstance().makeDropSound();
				ballVelocity=Geometry.reflectRotatingCircle(topCircle ,centerOfTopCircle, 0, ballA, ballVelocity);
				ball.setVelocityVector(ballVelocity);
				notifyCollision();
				isTriangleAvailableForCollision = false;
				GameEngineData.setEngelPoint1(false);
				GameEngineData.setEngelPoint2(false);
				GameEngineData.setTokatPoint(false);
				GameEngineData.setRoofPoint1(false);
				GameEngineData.setRoofPoint2(false);
				collisionTime = 0;
			}else if(ball.boundingBox().getBounds2D().intersects(leftBottomCircle.toEllipse2D().getBounds2D())){
				GameEngineAudioHandler.getInstance().makeDropSound();
				ballVelocity=Geometry.reflectRotatingCircle(leftBottomCircle ,centerOfLeftBottomCircle, 0, ballA, ballVelocity);
				ball.setVelocityVector(ballVelocity);
				notifyCollision();
				isTriangleAvailableForCollision = false;
				GameEngineData.setEngelPoint1(false);
				GameEngineData.setEngelPoint2(false);
				GameEngineData.setTokatPoint(false);
				GameEngineData.setRoofPoint1(false);
				GameEngineData.setRoofPoint2(false);
				collisionTime = 0;
			}else if(ball.boundingBox().getBounds2D().intersects(rightBottomCircle.toEllipse2D().getBounds2D())){
				GameEngineAudioHandler.getInstance().makeDropSound();
				ballVelocity=Geometry.reflectRotatingCircle(rightBottomCircle ,centerOfRightBottomCircle, 0, ballA, ballVelocity);
				ball.setVelocityVector(ballVelocity);
				notifyCollision();
				isTriangleAvailableForCollision = false;
				GameEngineData.setEngelPoint1(false);
				GameEngineData.setEngelPoint2(false);
				GameEngineData.setTokatPoint(false);
				GameEngineData.setRoofPoint1(false);
				GameEngineData.setRoofPoint2(false);
				collisionTime = 0;
			}else if(ball.boundingBox().getBounds2D().intersectsLine(hypotenuse.toLine2D())){
				GameEngineAudioHandler.getInstance().makeDropSound();
				ballVelocity=Geometry.reflectWall(hypotenuse, ballVelocity);
				ball.setVelocityVector(ballVelocity);
				notifyCollision();
				isTriangleAvailableForCollision = false;
				GameEngineData.setEngelPoint1(false);
				GameEngineData.setEngelPoint2(false);
				GameEngineData.setTokatPoint(false);
				GameEngineData.setRoofPoint1(false);
				GameEngineData.setRoofPoint2(false);
				collisionTime = 0;
			}else if(ball.boundingBox().getBounds2D().intersectsLine(leftLine.toLine2D())){
				GameEngineAudioHandler.getInstance().makeDropSound();
				ballVelocity=Geometry.reflectWall(leftLine, ballVelocity);
				ball.setVelocityVector(ballVelocity);
				notifyCollision();
				isTriangleAvailableForCollision = false;
				GameEngineData.setEngelPoint1(false);
				GameEngineData.setEngelPoint2(false);
				GameEngineData.setTokatPoint(false);
				GameEngineData.setRoofPoint1(false);
				GameEngineData.setRoofPoint2(false);
				collisionTime = 0;
			}else if(ball.boundingBox().getBounds2D().intersectsLine(bottomLine.toLine2D())){
				GameEngineAudioHandler.getInstance().makeDropSound();
				ballVelocity=Geometry.reflectWall(bottomLine, ballVelocity);
				ball.setVelocityVector(ballVelocity);
				notifyCollision();
				isTriangleAvailableForCollision = false;
				GameEngineData.setEngelPoint1(false);
				GameEngineData.setEngelPoint2(false);
				GameEngineData.setTokatPoint(false);
				GameEngineData.setRoofPoint1(false);
				GameEngineData.setRoofPoint2(false);
				collisionTime = 0;
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
			triangularTakozCollision(GameEngineData.getBallList().get(0));
		}else{
			triangularTakozCollision(GameEngineData.getBallList().get(0));
			triangularTakozCollision(GameEngineData.getBallList().get(1));
		}
		if(collisionTime > 21){
			isTriangleAvailableForCollision=true;
		}
	}
	/**
	 * Checks if is triangle available for collision.
	 *
	 * @return true, if is triangle available for collision
	 */
	public boolean isTriangleAvailableForCollision() {
		return isTriangleAvailableForCollision;
	}
	/**
	 * Sets the triangle available for collision.
	 *
	 * @param isTriangleAvailableForCollision the new triangle available for collision
	 */
	public void setTriangleAvailableForCollision(boolean isTriangleAvailableForCollision) {
		this.isTriangleAvailableForCollision = isTriangleAvailableForCollision;
	}
	/* (non-Javadoc)
	 * @see domain.item.EngineGizmo#boundingBox()
	 */
	public Rectangle boundingBox() {
		return new Rectangle(x, y, L, L);
	}
	/**
	 * Sets the top circle.
	 *
	 * @param topCircle the new top circle
	 */
	public void setTopCircle(Circle topCircle) {
		this.topCircle = topCircle;
	}
	/**
	 * Sets the right bottom circle.
	 *
	 * @param rightBottomCircle the new right bottom circle
	 */
	public void setRightBottomCircle(Circle rightBottomCircle) {
		this.rightBottomCircle = rightBottomCircle;
	}
	/**
	 * Gets the left line.
	 *
	 * @return the left line
	 */
	public LineSegment getLeftLine() {
		return leftLine;
	}
	/**
	 * Sets the left line.
	 *
	 * @param leftLine the new left line
	 */
	public void setLeftLine(LineSegment leftLine) {
		this.leftLine = leftLine;
	}
	/**
	 * Sets the bottom line.
	 *
	 * @param bottomLine the new bottom line
	 */
	public void setBottomLine(LineSegment bottomLine) {
		this.bottomLine = bottomLine;
	}
	/**
	 * Sets the center of top circle.
	 *
	 * @param centerOfTopCircle the new center of top circle
	 */
	public void setCenterOfTopCircle(Vect centerOfTopCircle) {
		this.centerOfTopCircle = centerOfTopCircle;
	}
	/**
	 * Gets the center of left bottom circle.
	 *
	 * @return the center of left bottom circle
	 */
	public Vect getCenterOfLeftBottomCircle() {
		return centerOfLeftBottomCircle;
	}
	/**
	 * Sets the center of left bottom circle.
	 *
	 * @param centerOfLeftBottomCircle the new center of left bottom circle
	 */
	public void setCenterOfLeftBottomCircle(Vect centerOfLeftBottomCircle) {
		this.centerOfLeftBottomCircle = centerOfLeftBottomCircle;
	}
	/**
	 * Sets the center of right bottom circle.
	 *
	 * @param centerOfRightBottomCircle the new center of right bottom circle
	 */
	public void setCenterOfRightBottomCircle(Vect centerOfRightBottomCircle) {
		this.centerOfRightBottomCircle = centerOfRightBottomCircle;
	}
	/**
	 * Gets the left bottom circle.
	 *
	 * @return the left bottom circle
	 */
	public Circle getLeftBottomCircle() {
		return leftBottomCircle;
	}
	/**
	 * Sets the left bottom circle.
	 *
	 * @param leftBottomCircle the new left bottom circle
	 */
	public void setLeftBottomCircle(Circle leftBottomCircle) {
		this.leftBottomCircle = leftBottomCircle;
	}
	/**
	 * Gets the top circle.
	 *
	 * @return the top circle
	 */
	public Circle getTopCircle() {
		return topCircle;
	}
	/**
	 * Gets the right bottom circle.
	 *
	 * @return the right bottom circle
	 */
	public Circle getRightBottomCircle() {
		return rightBottomCircle;
	}
	/**
	 * Gets the hypotenuse.
	 *
	 * @return the hypotenuse
	 */
	public LineSegment getHypotenuse() {
		return hypotenuse;
	}
	/**
	 * Sets the hypotenuse.
	 *
	 * @param hypotenuse the new hypotenuse
	 */
	public void setHypotenuse(LineSegment hypotenuse) {
		this.hypotenuse = hypotenuse;
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