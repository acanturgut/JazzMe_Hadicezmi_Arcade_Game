package domain.item;
import java.awt.Rectangle;
import domain.gameengine.GameEngineAudioHandler;
import domain.gameengine.GameEngineData;
import domain.physics.Circle;
import domain.physics.Geometry;
import domain.physics.LineSegment;
import domain.physics.Vect;
/**
 * The Class EngineSquareTakoz.
 */
public class EngineSquareTakoz extends EngineGizmo implements Runnable {
	/** The left top circle. */
	private Circle leftTopCircle;
	/** The right top circle. */
	private Circle rightTopCircle;
	/** The left bottom circle. */
	private Circle leftBottomCircle;
	/** The right bottom circle. */
	private Circle rightBottomCircle;
	/** The left top vect. */
	private Vect leftTopVect;
	/** The right top vect. */
	private Vect rightTopVect;
	/** The left bottom vect. */
	private Vect leftBottomVect;
	/** The right bottom vect. */
	private Vect rightBottomVect;
	/** The top line. */
	private LineSegment topLine;
	/** The right line. */
	private LineSegment rightLine;
	/** The left line. */
	private LineSegment leftLine;
	/** The bottom line. */
	private LineSegment bottomLine;
	/** The radius. */
	private int radius;
	/** The all hit square takoz. */
	private boolean isSquareTakozAvailableForCollision = true;
	/** The collision time. */
	private long collisionTime = 0;
	/**
	 * Instantiates a new engine square takoz.
	 *
	 * @param x the x
	 * @param y the y
	 * @param L the l
	 * @param player the player
	 */
	public EngineSquareTakoz(int x, int y, int L,  int player) {
		super(x, y, L, player);
		super.type = "squareTakoz";
		setBounds();
	}
	/**
	 * Notify ball.
	 *
	 * @param balla the balla
	 */
	public void squareTakozCollision(EngineBall balla) {
		Vect velocityVector = balla.getVelocityVector();
		int r = balla.getR();
		int xBall = balla.getX();
		int yBall = balla.getY();
		Vect centerOfBall = new Vect(xBall + r, yBall + r);
		Circle ball = new Circle(centerOfBall, r);
		if(isSquareTakozAvailableForCollision){
			if(balla.boundingBox().getBounds2D().intersects(leftTopCircle.toEllipse2D().getBounds2D())){
				velocityVector = Geometry.reflectRotatingCircle(leftTopCircle ,leftTopCircle.getCenter(), 0, ball, velocityVector);
				notifyCollision();
				isSquareTakozAvailableForCollision = false;
				collisionTime = 0;
				GameEngineAudioHandler.getInstance().makeDropSound();
				GameEngineData.setTokatPoint(false);
				GameEngineData.setEngelPoint1(false);
				GameEngineData.setEngelPoint2(false);
				GameEngineData.setRoofPoint1(false);
				GameEngineData.setRoofPoint2(false);
			}
			else if(balla.boundingBox().getBounds2D().intersects(rightTopCircle.toEllipse2D().getBounds2D())){
				velocityVector = Geometry.reflectRotatingCircle(leftTopCircle ,leftTopCircle.getCenter(), 0, ball, velocityVector);
				notifyCollision();
				isSquareTakozAvailableForCollision = false;
				collisionTime = 0;
				GameEngineAudioHandler.getInstance().makeDropSound();
				GameEngineData.setTokatPoint(false);
				GameEngineData.setEngelPoint1(false);
				GameEngineData.setEngelPoint2(false);
				GameEngineData.setRoofPoint1(false);
				GameEngineData.setRoofPoint2(false);
			}
			else if(balla.boundingBox().getBounds2D().intersects(leftBottomCircle.toEllipse2D().getBounds2D())){
				velocityVector = Geometry.reflectRotatingCircle(leftBottomCircle ,leftBottomCircle.getCenter(), 0, ball, velocityVector);
				notifyCollision();
				isSquareTakozAvailableForCollision = false;
				collisionTime = 0;
				GameEngineAudioHandler.getInstance().makeDropSound();
				GameEngineData.setTokatPoint(false);
				GameEngineData.setEngelPoint1(false);
				GameEngineData.setEngelPoint2(false);
				GameEngineData.setRoofPoint1(false);
				GameEngineData.setRoofPoint2(false);
			}	
			else if(balla.boundingBox().getBounds2D().intersects(rightBottomCircle.toEllipse2D().getBounds2D())){
				velocityVector = Geometry.reflectRotatingCircle(rightBottomCircle ,rightBottomCircle.getCenter(), 0, ball, velocityVector);
				notifyCollision();
				isSquareTakozAvailableForCollision = false;
				collisionTime = 0;
				GameEngineAudioHandler.getInstance().makeDropSound();
				GameEngineData.setTokatPoint(false);
				GameEngineData.setEngelPoint1(false);
				GameEngineData.setEngelPoint2(false);
				GameEngineData.setRoofPoint1(false);
				GameEngineData.setRoofPoint2(false);
			}
			else if(balla.boundingBox().getBounds2D().intersectsLine(topLine.toLine2D())){
				velocityVector = Geometry.reflectWall(topLine,velocityVector);
				notifyCollision();
				isSquareTakozAvailableForCollision = false;
				collisionTime = 0;
				balla.setY(balla.getY() - 2);
				GameEngineAudioHandler.getInstance().makeDropSound();
				GameEngineData.setTokatPoint(false);
				GameEngineData.setEngelPoint1(false);
				GameEngineData.setEngelPoint2(false);
				GameEngineData.setRoofPoint1(false);
				GameEngineData.setRoofPoint2(false);
			}
			else if(balla.boundingBox().getBounds2D().intersectsLine(bottomLine.toLine2D())){
				velocityVector = Geometry.reflectWall(bottomLine,velocityVector);
				notifyCollision();
				isSquareTakozAvailableForCollision = false;
				collisionTime = 0;
				balla.setY(balla.getY() + 2);
				GameEngineAudioHandler.getInstance().makeDropSound();
				GameEngineData.setTokatPoint(false);
				GameEngineData.setEngelPoint1(false);
				GameEngineData.setEngelPoint2(false);
				GameEngineData.setRoofPoint1(false);
				GameEngineData.setRoofPoint2(false);
			}
			else if(balla.boundingBox().getBounds2D().intersectsLine(leftLine.toLine2D())){
				velocityVector = Geometry.reflectWall(leftLine,velocityVector);
				notifyCollision();
				isSquareTakozAvailableForCollision = false;
				collisionTime = 0;
				balla.setY(balla.getY() - 2);
				GameEngineAudioHandler.getInstance().makeDropSound();
				GameEngineData.setTokatPoint(false);
				GameEngineData.setEngelPoint1(false);
				GameEngineData.setEngelPoint2(false);
				GameEngineData.setRoofPoint1(false);
				GameEngineData.setRoofPoint2(false);
			}
			else if(balla.boundingBox().getBounds2D().intersectsLine(rightLine.toLine2D())){
				velocityVector = Geometry.reflectWall(rightLine,velocityVector);
				notifyCollision();
				isSquareTakozAvailableForCollision = false;
				collisionTime = 0;
				balla.setY(balla.getY() + 2);
				GameEngineAudioHandler.getInstance().makeDropSound();
				GameEngineData.setTokatPoint(false);
				GameEngineData.setEngelPoint1(false);
				GameEngineData.setEngelPoint2(false);
				GameEngineData.setRoofPoint1(false);
				GameEngineData.setRoofPoint2(false);
			}
			balla.setVelocityVector(velocityVector);	
		}
	}
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run(){
		collisionTime++;
		if(collisionTime > 21){
			isSquareTakozAvailableForCollision = true;
		}
		if(GameEngineData.getLevel() == 1){
			squareTakozCollision(GameEngineData.getBallList().get(0));
		}else{
			squareTakozCollision(GameEngineData.getBallList().get(0));
			squareTakozCollision(GameEngineData.getBallList().get(1));
		}
	}
	/* (non-Javadoc)
	 * @see domain.item.EngineGizmo#boundingBox()
	 */
	public Rectangle boundingBox() {
		return new Rectangle(x, y, L, L);
	}
	public void setBounds(){
		
		radius = 1;
		int side = 1;
		
		leftTopCircle = new Circle(x + radius, y + radius, radius);
		rightTopCircle = new Circle(x + L - radius, y + radius, radius);
		leftBottomCircle = new Circle(x + radius, y + L - radius, radius);
		rightBottomCircle = new Circle(x + L - radius, y + L - radius, radius);
		leftLine = new LineSegment(x, y + side , x, y + L - side);
		topLine = new LineSegment(x + side, y, x + L - side, y);
		rightLine = new LineSegment(x + L - side, y + side , x + L - side, y + L - side);
		bottomLine = new LineSegment(x + side, y + L - side, x + L - side, y + L - side);
		setLeftTopVect(new Vect(leftTopCircle.getCenter().x(), leftTopCircle.getCenter().y()));
		setRightTopVect(new Vect(rightTopCircle.getCenter().x(), rightTopCircle.getCenter().y()));
		setLeftBottomVect(new Vect(leftBottomCircle.getCenter().x(), leftBottomCircle.getCenter().y()));
		setRightTopVect(new Vect(rightBottomCircle.getCenter().x(), rightBottomCircle.getCenter().y()));
	}
	/**
	 * Sets the square available.
	 *
	 * @param b the new square available
	 */
	public void setSquareAvailable(boolean b) {
		isSquareTakozAvailableForCollision = true;
	}
	/**
	 * Checks if is all hit square takoz.
	 *
	 * @return true, if is all hit square takoz
	 */
	public boolean isAllHitSquareTakoz() {
		return isSquareTakozAvailableForCollision;
	}
	/**
	 * Sets the all hit square takoz.
	 *
	 * @param allHitSquareTakoz the new all hit square takoz
	 */
	public void setSquareTakozAvailableForCollision(boolean allHitSquareTakoz) {
		this.isSquareTakozAvailableForCollision = allHitSquareTakoz;
	}
	/**
	 * Gets the left top vect.
	 *
	 * @return the left top vect
	 */
	public Vect getLeftTopVect() {
		return leftTopVect;
	}
	/**
	 * Sets the left top vect.
	 *
	 * @param leftTopVect the new left top vect
	 */
	public void setLeftTopVect(Vect leftTopVect) {
		this.leftTopVect = leftTopVect;
	}
	/**
	 * Gets the right top vect.
	 *
	 * @return the right top vect
	 */
	public Vect getRightTopVect() {
		return rightTopVect;
	}
	/**
	 * Sets the right top vect.
	 *
	 * @param rightTopVect the new right top vect
	 */
	public void setRightTopVect(Vect rightTopVect) {
		this.rightTopVect = rightTopVect;
	}
	/**
	 * Gets the left bottom vect.
	 *
	 * @return the left bottom vect
	 */
	public Vect getLeftBottomVect() {
		return leftBottomVect;
	}
	/**
	 * Sets the left bottom vect.
	 *
	 * @param leftBottomVect the new left bottom vect
	 */
	public void setLeftBottomVect(Vect leftBottomVect) {
		this.leftBottomVect = leftBottomVect;
	}
	/**
	 * Gets the right bottom vect.
	 *
	 * @return the right bottom vect
	 */
	public Vect getRightBottomVect() {
		return rightBottomVect;
	}
	/**
	 * Sets the right bottom vect.
	 *
	 * @param rightBottomVect the new right bottom vect
	 */
	public void setRightBottomVect(Vect rightBottomVect) {
		this.rightBottomVect = rightBottomVect;
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