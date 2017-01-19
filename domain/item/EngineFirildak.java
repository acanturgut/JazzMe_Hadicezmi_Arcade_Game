package domain.item;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import domain.gameengine.GameEngineAudioHandler;
import domain.gameengine.GameEngineData;
import domain.physics.Circle;
import domain.physics.Geometry;
import domain.physics.LineSegment;
import domain.physics.Vect;
/**
 * The Class EngineFirildak.
 */
public class EngineFirildak extends EngineGizmo implements Runnable {
	/** The top line. */
	private LineSegment topLine;
	
	/** The right line. */
	private LineSegment rightLine;
	
	/** The left line. */
	private LineSegment leftLine;
	
	/** The bottom line. */
	private LineSegment bottomLine;
	
	/** The left top circle. */
	private Circle leftTopCircle;
	
	/** The right top circle. */
	private Circle rightTopCircle;
	
	/** The left bottom circle. */
	private Circle leftBottomCircle;
	
	/** The right bottom circle. */
	private Circle rightBottomCircle;
	
	/** The rotation. */
	private double rotation = 0.0;
	
	/** The collision time. */
	private long collisionTime = 0;
	/** The is firildak available for collision. */
	private boolean isFirildakAvailableForCollision = true;
	/**
	 * Instantiates a new engine firildak.
	 *
	 * @param x the x
	 * @param y the y
	 * @param L the l
	 * @param player the player
	 */
	public EngineFirildak(int x, int y, int L, int player){
		super(x, y, L, player);
		super.type = "firildak";
		setBounds();
		
	}
	
	public void setBounds(){
		topLine = new LineSegment(x,y, x + L, y);
		rightLine = new LineSegment(x + L, y, x + L, y + L);
		leftLine = new LineSegment(x, y, x, y + L);
		bottomLine = new LineSegment(x , y + L, x + L, y + L);
		int radius = 3;
		leftTopCircle = new Circle(x + radius, y + radius, radius);
		rightTopCircle = new Circle(x + L - radius, y + radius, radius);
		leftBottomCircle = new Circle(x + radius, y + L - radius, radius);
		rightBottomCircle = new Circle(x + L - radius, y + L - radius, radius);
	}
	
	/* (non-Javadoc)
	 * @see domain.item.EngineGizmo#boundingBox()
	 */
	public Rectangle boundingBox() {
		return new Rectangle(x, y, L, L);
	}
	
	/**
	 * Gets the rotation.
	 *
	 * @return the rotation
	 */
	public double getRotation() {
		return rotation;
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
			AffineTransform affineTransform = new AffineTransform();
			affineTransform.rotate(rotation, x+L/2, y+L/2);
			path.transform(affineTransform);
			int radius = 3;
			topLine = new LineSegment(path.getBounds().getMinX() , path.getBounds().getMinY(), path.getBounds().getMaxX(),path.getBounds().getMinY());
			rightLine = new LineSegment(path.getBounds().getMaxX() , path.getBounds().getMinY(), path.getBounds().getMaxX(),path.getBounds().getMaxY());
			leftLine = new LineSegment(path.getBounds().getMaxX() , path.getBounds().getMinY(), path.getBounds().getMaxX(),path.getBounds().getMaxY());
			bottomLine = new LineSegment(path.getBounds().getMinX() , path.getBounds().getMaxY(), path.getBounds().getMaxX(),path.getBounds().getMaxY());
			leftTopCircle = new Circle(x + radius, y + radius, radius);
			rightTopCircle = new Circle(x + L - radius, y + radius, radius);
			leftBottomCircle = new Circle(x + radius, y + L - radius, radius);
			rightBottomCircle = new Circle(x + L - radius, y + L - radius, radius);
		}else{
			Rectangle rectangle = new Rectangle(x, y, L, L);
			rotation = rotation - 0.05;	
			Path2D.Double path = new Path2D.Double();
			path.append(rectangle, false);
			AffineTransform t = new AffineTransform();
			t.rotate(rotation, x+L/2, y+L/2);
			path.transform(t);
			int radius = 3;
			topLine = new LineSegment(path.getBounds().getMinX() , path.getBounds().getMinY(), path.getBounds().getMaxX(),path.getBounds().getMinY());
			rightLine = new LineSegment(path.getBounds().getMaxX() , path.getBounds().getMinY(), path.getBounds().getMaxX(),path.getBounds().getMaxY());
			leftLine = new LineSegment(path.getBounds().getMaxX() , path.getBounds().getMinY(), path.getBounds().getMaxX(),path.getBounds().getMaxY());
			bottomLine = new LineSegment(path.getBounds().getMinX() , path.getBounds().getMaxY(), path.getBounds().getMaxX(),path.getBounds().getMaxY());
			leftTopCircle = new Circle(path.getBounds().getMinX() + radius, path.getBounds().getMinY() + radius, radius);
			rightTopCircle = new Circle(path.getBounds().getMaxX() - radius, path.getBounds().getMinY() + radius, radius);
			leftBottomCircle = new Circle(path.getBounds().getMinX() + radius, path.getBounds().getMaxY() - radius, radius);
			rightBottomCircle = new Circle(path.getBounds().getMaxX() - radius, path.getBounds().getMaxY() - radius, radius);
		}
	}
	/**
	 * Gets the top line.
	 *
	 * @return the top line
	 */
	public LineSegment getTopLine() {
		return topLine;
	}
	/**
	 * Sets the top line.
	 *
	 * @param topLine the new top line
	 */
	public void setTopLine(LineSegment topLine) {
		this.topLine = topLine;
	}
	/**
	 * Gets the right line.
	 *
	 * @return the right line
	 */
	public LineSegment getRightLine() {
		return rightLine;
	}
	/**
	 * Sets the right line.
	 *
	 * @param rightLine the new right line
	 */
	public void setRightLine(LineSegment rightLine) {
		this.rightLine = rightLine;
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
	 * Gets the bottom line.
	 *
	 * @return the bottom line
	 */
	public LineSegment getBottomLine() {
		return bottomLine;
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
	 * Gets the left top circle.
	 *
	 * @return the left top circle
	 */
	public Circle getLeftTopCircle() {
		return leftTopCircle;
	}
	/**
	 * Sets the left top circle.
	 *
	 * @param leftTopCircle the new left top circle
	 */
	public void setLeftTopCircle(Circle leftTopCircle) {
		this.leftTopCircle = leftTopCircle;
	}
	/**
	 * Gets the right top circle.
	 *
	 * @return the right top circle
	 */
	public Circle getRightTopCircle() {
		return rightTopCircle;
	}
	/**
	 * Sets the right top circle.
	 *
	 * @param rightTopCircle the new right top circle
	 */
	public void setRightTopCircle(Circle rightTopCircle) {
		this.rightTopCircle = rightTopCircle;
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
	 * Gets the right bottom circle.
	 *
	 * @return the right bottom circle
	 */
	public Circle getRightBottomCircle() {
		return rightBottomCircle;
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
	 * Firildak collision.
	 *
	 * @param ball the ball
	 */
	public void firildakCollision(EngineBall ball) {
		Vect ballVelocity = ball.getVelocityVector();
		Circle ballCircle = new Circle(new Vect(ball.getX() - ball.getR(), ball.getY()- ball.getR()), ball.getR());
		
		if(isFirildakAvailableForCollision){
			
			if(ball.boundingBox().getBounds2D().intersectsLine(leftLine.toLine2D())){
				ballVelocity=Geometry.reflectWall(leftLine, ballVelocity);
				ball.setVelocityVector(ballVelocity);
				GameEngineAudioHandler.getInstance().makeDropSound();
				notifyCollision();
				isFirildakAvailableForCollision = false;
				GameEngineData.setTokatPoint(false);
				GameEngineData.setRoofPoint1(false);
				GameEngineData.setRoofPoint2(false);
				GameEngineData.setEngelPoint1(false);
				GameEngineData.setEngelPoint2(false);
				collisionTime = 0;
			}else if(ball.boundingBox().getBounds2D().intersectsLine(topLine.toLine2D())){
				ballVelocity=Geometry.reflectWall(topLine, ballVelocity);
				ball.setVelocityVector(ballVelocity);
				GameEngineAudioHandler.getInstance().makeDropSound();
				notifyCollision();
				isFirildakAvailableForCollision = false;
				GameEngineData.setTokatPoint(false);
				GameEngineData.setRoofPoint1(false);
				GameEngineData.setRoofPoint2(false);
				GameEngineData.setEngelPoint1(false);
				GameEngineData.setEngelPoint2(false);
				collisionTime = 0;
			}else if(ball.boundingBox().getBounds2D().intersectsLine(rightLine.toLine2D())){
				ballVelocity=Geometry.reflectWall(rightLine, ballVelocity);
				ball.setVelocityVector(ballVelocity);
				GameEngineAudioHandler.getInstance().makeDropSound();
				notifyCollision();
				isFirildakAvailableForCollision = false;
				GameEngineData.setTokatPoint(false);
				GameEngineData.setRoofPoint1(false);
				GameEngineData.setRoofPoint2(false);
				GameEngineData.setEngelPoint1(false);
				GameEngineData.setEngelPoint2(false);
				collisionTime = 0;
			}else if(ball.boundingBox().getBounds2D().intersectsLine(bottomLine.toLine2D())){
				ballVelocity=Geometry.reflectWall(bottomLine, ballVelocity);
				ball.setVelocityVector(ballVelocity);
				GameEngineAudioHandler.getInstance().makeDropSound();
				notifyCollision();
				isFirildakAvailableForCollision = false;
				GameEngineData.setTokatPoint(false);
				GameEngineData.setRoofPoint1(false);
				GameEngineData.setRoofPoint2(false);
				GameEngineData.setEngelPoint1(false);
				GameEngineData.setEngelPoint2(false);
				collisionTime = 0;
			}else if(ball.boundingBox().getBounds2D().intersects(rightTopCircle.toEllipse2D().getBounds2D())){
				ballVelocity=Geometry.reflectRotatingCircle(rightTopCircle, rightTopCircle.getCenter(), 0.05, ballCircle, ball.getVelocityVector());
				ball.setVelocityVector(ballVelocity);
				GameEngineAudioHandler.getInstance().makeDropSound();
				notifyCollision();
				isFirildakAvailableForCollision = false;
				GameEngineData.setTokatPoint(false);
				GameEngineData.setRoofPoint1(false);
				GameEngineData.setRoofPoint2(false);
				GameEngineData.setEngelPoint1(false);
				GameEngineData.setEngelPoint2(false);
				collisionTime = 0;
			}else if(ball.boundingBox().getBounds2D().intersects(leftTopCircle.toEllipse2D().getBounds2D())){
				ballVelocity=Geometry.reflectRotatingCircle(leftTopCircle, leftTopCircle.getCenter(), 0.05, ballCircle, ball.getVelocityVector());
				ball.setVelocityVector(ballVelocity);
				GameEngineAudioHandler.getInstance().makeDropSound();
				notifyCollision();
				isFirildakAvailableForCollision = false;
				GameEngineData.setTokatPoint(false);
				GameEngineData.setRoofPoint1(false);
				GameEngineData.setRoofPoint2(false);
				GameEngineData.setEngelPoint1(false);
				GameEngineData.setEngelPoint2(false);
				collisionTime = 0;
			}else if(ball.boundingBox().getBounds2D().intersects(leftBottomCircle.toEllipse2D().getBounds2D())){
				ballVelocity=Geometry.reflectRotatingCircle(leftBottomCircle, leftBottomCircle.getCenter(), 0.05, ballCircle, ball.getVelocityVector());
				ball.setVelocityVector(ballVelocity);
				GameEngineAudioHandler.getInstance().makeDropSound();
				notifyCollision();
				isFirildakAvailableForCollision = false;
				GameEngineData.setTokatPoint(false);
				GameEngineData.setRoofPoint1(false);
				GameEngineData.setRoofPoint2(false);
				GameEngineData.setEngelPoint1(false);
				GameEngineData.setEngelPoint2(false);
				collisionTime = 0;
			}else if(ball.boundingBox().getBounds2D().intersects(rightBottomCircle.toEllipse2D().getBounds2D())){
				ballVelocity=Geometry.reflectRotatingCircle(rightBottomCircle, rightBottomCircle.getCenter(), 0.05, ballCircle, ball.getVelocityVector());
				ball.setVelocityVector(ballVelocity);
				GameEngineAudioHandler.getInstance().makeDropSound();
				notifyCollision();
				isFirildakAvailableForCollision = false;
				GameEngineData.setTokatPoint(false);
				GameEngineData.setRoofPoint1(false);
				GameEngineData.setRoofPoint2(false);
				GameEngineData.setEngelPoint1(false);
				GameEngineData.setEngelPoint2(false);
				collisionTime = 0;
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		rotate();
		collisionTime++;
		
		if(collisionTime > 21){
			isFirildakAvailableForCollision = true;
		}
		
		if(GameEngineData.getLevel() == 1){
			firildakCollision(GameEngineData.getBallList().get(0));
		}else{
			firildakCollision(GameEngineData.getBallList().get(0));
			firildakCollision(GameEngineData.getBallList().get(1));
		}
	}
	public void setFirildakAvailableForCollision(boolean isFirildakAvailableForCollision) {
		this.isFirildakAvailableForCollision = isFirildakAvailableForCollision;
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