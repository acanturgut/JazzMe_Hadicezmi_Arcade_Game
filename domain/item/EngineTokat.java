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
 * The Class EngineTokat.
 */
public class EngineTokat extends EngineGizmo implements Runnable {

	/** The tokat top oval X. */
	private int tokatTopOvalX = x;
	
	/** The tokat top oval Y. */
	private int tokatTopOvalY = y;

	/** The tokat rec X. */
	private int tokatRecX = x;
	
	/** The tokat rec Y. */
	private int tokatRecY = y + L/4;

	/** The tokat bottom oval X. */
	private int tokatBottomOvalX = x ;
	
	/** The tokat bottom oval Y. */
	private int tokatBottomOvalY = y + L + L/2;

	/** The tokat bottom oval X temp. */
	private int  tokatBottomOvalXTemp;
	
	/** The tokat bottom oval Y temp. */
	private int  tokatBottomOvalYTemp;

	/** The invoke. */
	private boolean invoke = false;
	
	/** The rotation. */
	private double rotation = 0;
	
	/** The reverse rotation. */
	private double reverseRotation = 0;

	/** The rotatable. */
	private boolean rotatable = true;
	
	/** The reverse rotatable. */
	private boolean reverseRotatable = false;

	/** The normal invoke. */
	private boolean normalInvoke = true;

	/** The in edit mode. */
	private boolean inEditMode = true;
	
	/** The is tokat available for collision. */
	private boolean isTokatAvailableForCollision = true;

	/** The mid rectangle. */
	private Rectangle midRectangle;

	/** The left top X. */
	int leftTopX;
	
	/** The left bottom X. */
	int leftBottomX;
	
	/** The left top Y. */
	int leftTopY;
	
	/** The left bottom Y. */
	int leftBottomY;
	
	/** The right top X. */
	int rightTopX;
	
	/** The right bottom X. */
	int rightBottomX;
	
	/** The right bottom Y. */
	int rightBottomY;

	/** The right line. */
	private LineSegment rightLine;
	
	/** The left line. */
	private LineSegment leftLine;

	/** The top circle. */
	private Circle topCircle;
	
	/** The bottom circle. */
	private Circle bottomCircle;

	private int collisionTime;

	/**
	* Instantiates a new engine tokat.
	*
	* @param x the x
	* @param y the y
	* @param L the l
	* @param state the state
	* @param player the player
	*/
	public EngineTokat(int x, int y, int L, int state, int player){
		super(x, y, L, state, player);
		super.type = "tokat";
		
		tokatBottomOvalXTemp =  tokatBottomOvalX;
		tokatBottomOvalYTemp =  tokatBottomOvalY;
		
		
		if(state%2 == 1){

			tokatTopOvalX = x;
			tokatTopOvalY = y;
			
			tokatRecX = x;
			tokatRecY = y + L/4;

			tokatBottomOvalX = x ;
			tokatBottomOvalY = y + L + L/2;

			leftLine = new LineSegment(x, y + (L / 4), x, y + (2 * L) - L / 2);
			rightLine = new LineSegment(x + (L / 2), y + (L / 4), x + (L / 2), y + (2 * L) - L / 2);
			
			topCircle = new Circle(new Vect(x + (L / 4), y + (L / 4)), L / 4);
			bottomCircle = new Circle(new Vect(x + (L / 4), y + (7 * L / 4)), L / 4);
			
			leftTopX = tokatRecX;
			leftBottomX = tokatRecX;
			leftTopY = tokatRecY;
			leftBottomY = tokatRecY + (3 * L / 4);
			rightTopX = tokatRecX + L/2;
			rightBottomX = tokatRecX + L/2;
			rightBottomY = tokatRecY + (3 * L / 4);


		}else if(state%2 == 0){


			tokatTopOvalX = x + L + L/2;
			tokatTopOvalY = y;

			tokatRecX = x + L + L/2;
			tokatRecY = y + L/4;

			tokatBottomOvalX = x + L + L/2;
			tokatBottomOvalY = y + L + L/2;

			leftLine = new LineSegment(x + (3 * L / 2), y + (L / 4), x + (3 * L / 2), y + (2 * L) - L / 2);
			rightLine = new LineSegment(x + (3 * L / 2) + (L / 2), y + (L / 4), x + (3 * L / 2) + (L / 2), y + (2 * L) - L / 2);
			
			topCircle = new Circle(new Vect(x + (7 * L / 4), y + (L / 4)), L / 4);
			bottomCircle = new Circle(new Vect(x + (7 * L / 4), y + (7 * L / 4)), L / 4);
			
			leftTopX = tokatRecX;
			leftBottomX = tokatRecX;
			leftTopY = tokatRecY;
			leftBottomY = tokatRecY + (3 * L / 4);
			rightTopX = tokatRecX + L/2;
			rightBottomX = tokatRecX + L/2;
			rightBottomY = tokatRecY + (3 * L / 4);
		}

	}

	/* (non-Javadoc)
	* @see domain.item.EngineGizmo#boundingBox()
	*/
	public Rectangle boundingBox() {
		return new Rectangle(x, y, 2*L, 2*L);
	}

	/**
	* Rotate.
	*/
	public void rotate(){

		double fast = 10;

		midRectangle = new Rectangle(tokatRecX, tokatRecY, L/2, (L/2 + L/4) * 2);

		Path2D.Double path = new Path2D.Double();
		path.append(midRectangle, false);
		AffineTransform t = new AffineTransform();

		t.rotate(Math.toRadians(rotation), tokatRecX + L/4, tokatRecY);
		path.transform(t);

		rightLine = new LineSegment(path.getBounds().getMaxX() , path.getBounds().getMinY(), path.getBounds().getMaxX(),path.getBounds().getMaxY());
		leftLine = new LineSegment(path.getBounds().getMaxX() , path.getBounds().getMinY(), path.getBounds().getMaxX(),path.getBounds().getMaxY());

		topCircle = new Circle(x + L / 4, y + L / 4, L / 4);

		if(state%2 == 1){

			if(normalInvoke){
				if(rotation > - 90){

					rotation = rotation - fast;
					double rad = rotation * 0.0174533;
					int heightSquare = (3*L/2) * (3*L/2);
					tokatBottomOvalX = (int) (tokatBottomOvalXTemp + (Math.sqrt((2 * heightSquare) - (2 * heightSquare) * Math.cos(rad))) * Math.sin(90*0.0174533 - rad/2)); 
					tokatBottomOvalY = (int) (tokatBottomOvalYTemp + (Math.sqrt((2 * heightSquare) - (2 * heightSquare) * Math.cos(rad))) * Math.sin(rad/2)) + 1;

					bottomCircle = new Circle(tokatBottomOvalX + L / 4, tokatBottomOvalY  + L / 4, L / 4 );

				}else if(rotation == -90){


					invoke = false;
					normalInvoke = false;
				}
			}else{
				if(rotation < 0){
					rotation = rotation + fast;
					double rad = rotation * 0.0174533;
					int heightSquare = (3*L/2) * (3*L/2);
					tokatBottomOvalX = (int) (tokatBottomOvalXTemp + (Math.sqrt((2 * heightSquare) - (2 * heightSquare) * Math.cos(rad))) * Math.sin(90*0.0174533 - rad/2)); 
					tokatBottomOvalY = (int) (tokatBottomOvalYTemp + (Math.sqrt((2 * heightSquare) - (2 * heightSquare) * Math.cos(rad))) * Math.sin(rad/2)) + 1;

					bottomCircle = new Circle(tokatBottomOvalX + L / 4, tokatBottomOvalY  + L / 4, L / 4 );
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
					tokatBottomOvalX = (int) (tokatBottomOvalXTemp + 3*(L/2) - (Math.sqrt((2 * heightSquare) - (2 * heightSquare) * Math.cos(rad))) * Math.sin(90*0.0174533 - rad/2)); 
					tokatBottomOvalY = (int) (tokatBottomOvalYTemp - (Math.sqrt((2 * heightSquare) - (2 * heightSquare) * Math.cos(rad))) * Math.sin(rad/2)) + 1;

					bottomCircle = new Circle(tokatBottomOvalX + L / 4, tokatBottomOvalY  + L / 4, L / 4 );
				}else if(rotation == 90){

					invoke = false;
					normalInvoke = false;
				}
			}else{
				if(rotation > 0){

					rotation = rotation - fast;

					double rad = rotation * 0.0174533;
					int heightSquare = (3*L/2) * (3*L/2);
					tokatBottomOvalX = (int) (tokatBottomOvalXTemp + 3*(L/2) - (Math.sqrt((2 * heightSquare) - (2 * heightSquare) * Math.cos(rad))) * Math.sin(90*0.0174533 - rad/2)); 
					tokatBottomOvalY = (int) (tokatBottomOvalYTemp - (Math.sqrt((2 * heightSquare) - (2 * heightSquare) * Math.cos(rad))) * Math.sin(rad/2)) + 1;

					bottomCircle = new Circle(tokatBottomOvalX + L / 4, tokatBottomOvalY  + L / 4, L / 4 );
				}else if(rotation == 0){

					invoke = false;
					normalInvoke = true;
				}
			}
		}
	}

	/**
	* Tokat takoz collision.
	*
	* @param ball the ball
	*/
	public void tokatCollision(EngineBall ball) {

		Vect ballVelocity = ball.getVelocityVector();

		Circle ballOrigin = new Circle(new Vect(ball.getX() - ball.getR(), ball.getY()- ball.getR()), ball.getR());

		if(isTokatAvailableForCollision){

			if(ball.boundingBox().getBounds2D().intersectsLine(leftLine.toLine2D())){
				ballVelocity=Geometry.reflectWall(leftLine, ballVelocity);
				ball.setVelocityVector(ballVelocity);
				GameEngineAudioHandler.getInstance().makeDropSound();
				notifyCollision();
				isTokatAvailableForCollision = false;
				GameEngineData.setTokatPoint(true);
				GameEngineData.setEngelPoint1(false);
				GameEngineData.setEngelPoint2(false);
				GameEngineData.setRoofPoint1(false);
				GameEngineData.setRoofPoint2(false);
				
			}else if(ball.boundingBox().getBounds2D().intersectsLine(rightLine.toLine2D())){
				ballVelocity=Geometry.reflectWall(rightLine, ballVelocity);
				ball.setVelocityVector(ballVelocity);
				GameEngineAudioHandler.getInstance().makeDropSound();
				notifyCollision();
				isTokatAvailableForCollision = false;
				GameEngineData.setTokatPoint(true);
				GameEngineData.setEngelPoint1(false);
				GameEngineData.setEngelPoint2(false);
				GameEngineData.setRoofPoint1(false);
				GameEngineData.setRoofPoint2(false);
				
			}else if(ball.boundingBox().getBounds2D().intersects(topCircle.toEllipse2D().getBounds2D())){
				ballVelocity=Geometry.reflectRotatingCircle(topCircle, topCircle.getCenter(), 0.05, ballOrigin, ball.getVelocityVector());
				ball.setVelocityVector(ballVelocity);
				GameEngineAudioHandler.getInstance().makeDropSound();
				notifyCollision();
				isTokatAvailableForCollision = false;
				GameEngineData.setTokatPoint(true);
				GameEngineData.setEngelPoint1(false);
				GameEngineData.setEngelPoint2(false);
				GameEngineData.setRoofPoint1(false);
				GameEngineData.setRoofPoint2(false);
				
			}else if(ball.boundingBox().getBounds2D().intersects(bottomCircle.toEllipse2D().getBounds2D())){
				ballVelocity=Geometry.reflectRotatingCircle(bottomCircle, bottomCircle.getCenter(), 0.05, ballOrigin, ball.getVelocityVector());
				ball.setVelocityVector(ballVelocity);
				GameEngineAudioHandler.getInstance().makeDropSound();
				notifyCollision();
				isTokatAvailableForCollision = false;	
				GameEngineData.setTokatPoint(true);
				GameEngineData.setEngelPoint1(false);
				GameEngineData.setEngelPoint2(false);
				GameEngineData.setRoofPoint1(false);
				GameEngineData.setRoofPoint2(false);
			}
		}
	}

	/* (non-Javadoc)
	* @see java.lang.Runnable#run()
	*/
	@Override
	public void run() {
		collisionTime++;
		inEditMode = false;
		if(isInvoke()){
			rotate();
		}
		
		if(collisionTime > 21){
			isTokatAvailableForCollision = true;
		}
		
		if(GameEngineData.getLevel() == 1){
			tokatCollision(GameEngineData.getBallList().get(0));
		}else{
			tokatCollision(GameEngineData.getBallList().get(0));
			tokatCollision(GameEngineData.getBallList().get(1));
		}
	}

//	/**
//	 * Make others available for collision.
//	 */
//	public void makeOthersAvailableForCollision() {
//
//		for (int i = 0; i < GameEngineData.getTriangularTakozList().size(); i++) {
//			GameEngineData.getTriangularTakozList().get(i).setTriangleAvailableForCollision(true);
//		}
//
//		for (int i = 0; i < GameEngineData.getSquareTakozList().size(); i++) {
//			GameEngineData.getSquareTakozList().get(i).setSquareAvailable(true);
//		}
//	}

	/**
	* Checks if is invoke.
	*
	* @return true, if is invoke
	*/
	public boolean isInvoke() {
		return invoke;
	}

	/**
	* Gets the tokat top oval X.
	*
	* @return the tokat top oval X
	*/
	public int getTokatTopOvalX() {
		return tokatTopOvalX;
	}

	/**
	* Sets the tokat top oval X.
	*
	* @param tokatTopOvalX the new tokat top oval X
	*/
	public void setTokatTopOvalX(int tokatTopOvalX) {
		this.tokatTopOvalX = tokatTopOvalX;
	}

	/**
	* Gets the tokat top oval Y.
	*
	* @return the tokat top oval Y
	*/
	public int getTokatTopOvalY() {
		return tokatTopOvalY;
	}

	/**
	* Sets the tokat top oval Y.
	*
	* @param tokatTopOvalY the new tokat top oval Y
	*/
	public void setTokatTopOvalY(int tokatTopOvalY) {
		this.tokatTopOvalY = tokatTopOvalY;
	}

	/**
	* Gets the tokat rec X.
	*
	* @return the tokat rec X
	*/
	public int getTokatRecX() {
		return tokatRecX;
	}

	/**
	* Sets the tokat rec X.
	*
	* @param tokatRecX the new tokat rec X
	*/
	public void setTokatRecX(int tokatRecX) {
		this.tokatRecX = tokatRecX;
	}

	/**
	* Gets the tokat rec Y.
	*
	* @return the tokat rec Y
	*/
	public int getTokatRecY() {
		return tokatRecY;
	}

	/**
	* Sets the tokat rec Y.
	*
	* @param tokatRecY the new tokat rec Y
	*/
	public void setTokatRecY(int tokatRecY) {
		this.tokatRecY = tokatRecY;
	}

	/**
	* Gets the tokat bottom oval X.
	*
	* @return the tokat bottom oval X
	*/
	public int getTokatBottomOvalX() {
		return tokatBottomOvalX;
	}

	/**
	* Sets the tokat bottom oval X.
	*
	* @param tokatBottomOvalX the new tokat bottom oval X
	*/
	public void setTokatBottomOvalX(int tokatBottomOvalX) {
		this.tokatBottomOvalX = tokatBottomOvalX;
	}

	/**
	* Gets the tokat bottom oval Y.
	*
	* @return the tokat bottom oval Y
	*/
	public int getTokatBottomOvalY() {
		return tokatBottomOvalY;
	}

	/**
	* Sets the tokat bottom oval Y.
	*
	* @param tokatBottomOvalY the new tokat bottom oval Y
	*/
	public void setTokatBottomOvalY(int tokatBottomOvalY) {
		this.tokatBottomOvalY = tokatBottomOvalY;
	}

	/**
	* Gets the tokat bottom oval X temp.
	*
	* @return the tokat bottom oval X temp
	*/
	public int getTokatBottomOvalXTemp() {
		return tokatBottomOvalXTemp;
	}

	/**
	* Sets the tokat bottom oval X temp.
	*
	* @param tokatBottomOvalXTemp the new tokat bottom oval X temp
	*/
	public void setTokatBottomOvalXTemp(int tokatBottomOvalXTemp) {
		this.tokatBottomOvalXTemp = tokatBottomOvalXTemp;
	}

	/**
	* Gets the tokat bottom oval Y temp.
	*
	* @return the tokat bottom oval Y temp
	*/
	public int getTokatBottomOvalYTemp() {
		return tokatBottomOvalYTemp;
	}

	/**
	* Sets the tokat bottom oval Y temp.
	*
	* @param tokatBottomOvalYTemp the new tokat bottom oval Y temp
	*/
	public void setTokatBottomOvalYTemp(int tokatBottomOvalYTemp) {
		this.tokatBottomOvalYTemp = tokatBottomOvalYTemp;
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
	* Sets the rotation.
	*
	* @param rotation the new rotation
	*/
	public void setRotation(double rotation) {
		this.rotation = rotation;
	}

	/**
	* Checks if is normal invoke.
	*
	* @return true, if is normal invoke
	*/
	public boolean isNormalInvoke() {
		return normalInvoke;
	}

	/**
	* Sets the normal invoke.
	*
	* @param normalInvoke the new normal invoke
	*/
	public void setNormalInvoke(boolean normalInvoke) {
		this.normalInvoke = normalInvoke;
	}

	/**
	* Checks if is in edit mode.
	*
	* @return true, if is in edit mode
	*/
	public boolean isInEditMode() {
		return inEditMode;
	}

	/**
	* Sets the in edit mode.
	*
	* @param inEditMode the new in edit mode
	*/
	public void setInEditMode(boolean inEditMode) {
		this.inEditMode = inEditMode;
	}

	/**
	* Checks if is tokat available for collision.
	*
	* @return true, if is tokat available for collision
	*/
	public boolean isTokatAvailableForCollision() {
		return isTokatAvailableForCollision;
	}

	/**
	* Sets the tokat available for collision.
	*
	* @param isTokatAvailableForCollision the new tokat available for collision
	*/
	public void setTokatAvailableForCollision(boolean isTokatAvailableForCollision) {
		this.isTokatAvailableForCollision = isTokatAvailableForCollision;
	}

	/**
	* Gets the mid rectangle.
	*
	* @return the mid rectangle
	*/
	public Rectangle getMidRectangle() {
		return midRectangle;
	}

	/**
	* Sets the mid rectangle.
	*
	* @param midRectangle the new mid rectangle
	*/
	public void setMidRectangle(Rectangle midRectangle) {
		this.midRectangle = midRectangle;
	}

	/**
	* Gets the left top X.
	*
	* @return the left top X
	*/
	public int getLeftTopX() {
		return leftTopX;
	}

	/**
	* Sets the left top X.
	*
	* @param leftTopX the new left top X
	*/
	public void setLeftTopX(int leftTopX) {
		this.leftTopX = leftTopX;
	}

	/**
	* Gets the left bottom X.
	*
	* @return the left bottom X
	*/
	public int getLeftBottomX() {
		return leftBottomX;
	}

	/**
	* Sets the left bottom X.
	*
	* @param leftBottomX the new left bottom X
	*/
	public void setLeftBottomX(int leftBottomX) {
		this.leftBottomX = leftBottomX;
	}

	/**
	* Gets the left top Y.
	*
	* @return the left top Y
	*/
	public int getLeftTopY() {
		return leftTopY;
	}

	/**
	* Sets the left top Y.
	*
	* @param leftTopY the new left top Y
	*/
	public void setLeftTopY(int leftTopY) {
		this.leftTopY = leftTopY;
	}

	/**
	* Gets the left bottom Y.
	*
	* @return the left bottom Y
	*/
	public int getLeftBottomY() {
		return leftBottomY;
	}

	/**
	* Sets the left bottom Y.
	*
	* @param leftBottomY the new left bottom Y
	*/
	public void setLeftBottomY(int leftBottomY) {
		this.leftBottomY = leftBottomY;
	}

	/**
	* Gets the right top X.
	*
	* @return the right top X
	*/
	public int getRightTopX() {
		return rightTopX;
	}

	/**
	* Sets the right top X.
	*
	* @param rightTopX the new right top X
	*/
	public void setRightTopX(int rightTopX) {
		this.rightTopX = rightTopX;
	}

	/**
	* Gets the right bottom X.
	*
	* @return the right bottom X
	*/
	public int getRightBottomX() {
		return rightBottomX;
	}

	/**
	* Sets the right bottom X.
	*
	* @param rightBottomX the new right bottom X
	*/
	public void setRightBottomX(int rightBottomX) {
		this.rightBottomX = rightBottomX;
	}

	/**
	* Gets the right bottom Y.
	*
	* @return the right bottom Y
	*/
	public int getRightBottomY() {
		return rightBottomY;
	}

	/**
	* Sets the right bottom Y.
	*
	* @param rightBottomY the new right bottom Y
	*/
	public void setRightBottomY(int rightBottomY) {
		this.rightBottomY = rightBottomY;
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
	* Gets the top circle.
	*
	* @return the top circle
	*/
	public Circle getTopCircle() {
		return topCircle;
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
	* Gets the bottom circle.
	*
	* @return the bottom circle
	*/
	public Circle getBottomCircle() {
		return bottomCircle;
	}

	/**
	* Sets the bottom circle.
	*
	* @param bottomCircle the new bottom circle
	*/
	public void setBottomCircle(Circle bottomCircle) {
		this.bottomCircle = bottomCircle;
	}

	/**
	* Sets the invoke.
	*
	* @param invoke the new invoke
	*/
	public void setInvoke(boolean invoke) {
		this.invoke = invoke;
	}

	/**
	* Gets the reverse rotation.
	*
	* @return the reverse rotation
	*/
	public double getReverseRotation() {
		return reverseRotation;
	}

	/**
	* Sets the reverse rotation.
	*
	* @param reverseRotation the new reverse rotation
	*/
	public void setReverseRotation(double reverseRotation) {
		this.reverseRotation = reverseRotation;
	}

	/**
	* Checks if is rotatable.
	*
	* @return true, if is rotatable
	*/
	public boolean isRotatable() {
		return rotatable;
	}

	/**
	* Sets the rotatable.
	*
	* @param rotatable the new rotatable
	*/
	public void setRotatable(boolean rotatable) {
		this.rotatable = rotatable;
	}

	/**
	* Checks if is reverse rotatable.
	*
	* @return true, if is reverse rotatable
	*/
	public boolean isReverseRotatable() {
		return reverseRotatable;
	}

	/**
	* Sets the reverse rotatable.
	*
	* @param backRotatable the new reverse rotatable
	*/
	public void setreverseRotatable(boolean backRotatable) {
		this.reverseRotatable = backRotatable;
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
