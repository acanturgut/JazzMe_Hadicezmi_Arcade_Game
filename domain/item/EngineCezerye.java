package domain.item;

import java.util.Random;
import domain.gameengine.GameEngineAudioHandler;
import domain.gameengine.GameEngineData;
import domain.physics.Circle;
import domain.physics.Geometry;
import domain.physics.LineSegment;
import domain.physics.Vect;
import ui.data.UIData;
import ui.item.UIFirildak;

/**
 * The Class EngineCezerye.
 */
public class EngineCezerye extends EngineGizmo implements Runnable {

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
	private boolean allHitSquareTakoz = true;

	/** The collision time. */
	private long collisionTime = 0;

	/** The effect time. */
	private int effectTime = 0;

	/** The is cezerye triggered. */
	private boolean isCezeryeTriggered = false;

	/** The effect. */
	private int effect;

	/**
	 * Instantiates a new engine cezerye.
	 *
	 * @param x the x
	 * @param y the y
	 * @param L the l
	 * @param player the player
	 */
	public EngineCezerye(int x, int y, int L, int player){
		super(x, y, L, player);
		super.type = "cezerye";
		collisonBorderCreate();
	}
	
	/**
	 * Notify ball.
	 *
	 * @param balla the balla
	 */
	private void notifyBall(EngineBall balla) {

		Vect velocityVector = balla.getVelocityVector();
		int r = balla.getR();
		int xBall = balla.getX();
		int yBall = balla.getY();
		Vect centerOfBall = new Vect(xBall + r, yBall + r);
		Circle ball = new Circle(centerOfBall, r);

		if(allHitSquareTakoz){

			if(balla.boundingBox().getBounds2D().intersects(leftTopCircle.toEllipse2D().getBounds2D())){
				makeOthersTrue();
				velocityVector = Geometry.reflectRotatingCircle(leftTopCircle ,leftTopCircle.getCenter(), 0, ball, velocityVector);
				allHitSquareTakoz = false;
				collisionTime = 0;
				GameEngineAudioHandler.getInstance().makeMoveSound();
				effectTime = 0;
				isCezeryeTriggered = true;
				player = balla.getPlayer();
				GameEngineData.setTokatPoint(false);
				GameEngineData.setEngelPoint1(false);
				GameEngineData.setEngelPoint2(false);
				invoke();
				setX(10000);
				setY(10000);
				collisonBorderCreate();
			}

			else if(balla.boundingBox().getBounds2D().intersects(rightTopCircle.toEllipse2D().getBounds2D())){
				makeOthersTrue();
				velocityVector = Geometry.reflectRotatingCircle(leftTopCircle ,leftTopCircle.getCenter(), 0, ball, velocityVector);
				allHitSquareTakoz = false;
				collisionTime = 0;
				GameEngineAudioHandler.getInstance().makeMoveSound();
				effectTime = 0;
				isCezeryeTriggered = true;
				player = balla.getPlayer();
				GameEngineData.setTokatPoint(false);
				GameEngineData.setEngelPoint1(false);
				GameEngineData.setEngelPoint2(false);
				invoke();
				setX(10000);
				setY(10000);
				collisonBorderCreate();
			}

			else if(balla.boundingBox().getBounds2D().intersects(leftBottomCircle.toEllipse2D().getBounds2D())){
				makeOthersTrue();
				velocityVector = Geometry.reflectRotatingCircle(leftBottomCircle ,leftBottomCircle.getCenter(), 0, ball, velocityVector);
				allHitSquareTakoz = false;
				collisionTime = 0;
				GameEngineAudioHandler.getInstance().makeMoveSound();
				effectTime = 0;
				isCezeryeTriggered = true;
				player = balla.getPlayer();
				GameEngineData.setTokatPoint(false);
				GameEngineData.setEngelPoint1(false);
				GameEngineData.setEngelPoint2(false);
				invoke();
				setX(10000);
				setY(10000);
				collisonBorderCreate();
			}	

			else if(balla.boundingBox().getBounds2D().intersects(rightBottomCircle.toEllipse2D().getBounds2D())){
				makeOthersTrue();
				velocityVector = Geometry.reflectRotatingCircle(rightBottomCircle ,rightBottomCircle.getCenter(), 0, ball, velocityVector);
				allHitSquareTakoz = false;
				collisionTime = 0;
				GameEngineAudioHandler.getInstance().makeMoveSound();
				effectTime = 0;
				isCezeryeTriggered = true;
				player = balla.getPlayer();
				GameEngineData.setTokatPoint(false);
				GameEngineData.setEngelPoint1(false);
				GameEngineData.setEngelPoint2(false);
				invoke();
				setX(10000);
				setY(10000);
				collisonBorderCreate();
			}

			else if(balla.boundingBox().getBounds2D().intersectsLine(topLine.toLine2D())){
				makeOthersTrue();
				velocityVector = Geometry.reflectWall(topLine,velocityVector);
				allHitSquareTakoz = false;
				collisionTime = 0;
				balla.setY(balla.getY() - 2);
				effectTime = 0;
				isCezeryeTriggered = true;
				GameEngineAudioHandler.getInstance().makeMoveSound();
				player = balla.getPlayer();
				GameEngineData.setTokatPoint(false);
				GameEngineData.setEngelPoint1(false);
				GameEngineData.setEngelPoint2(false);
				invoke();
				setX(10000);
				setY(10000);
				collisonBorderCreate();
			}

			else if(balla.boundingBox().getBounds2D().intersectsLine(bottomLine.toLine2D())){
				makeOthersTrue();
				velocityVector = Geometry.reflectWall(bottomLine,velocityVector);
				allHitSquareTakoz = false;
				collisionTime = 0;
				balla.setY(balla.getY() + 2);
				effectTime = 0;
				isCezeryeTriggered = true;
				GameEngineAudioHandler.getInstance().makeMoveSound();
				player = balla.getPlayer();
				GameEngineData.setTokatPoint(false);
				GameEngineData.setEngelPoint1(false);
				GameEngineData.setEngelPoint2(false);
				invoke();
				setX(10000);
				setY(10000);
				collisonBorderCreate();
			}

			else if(balla.boundingBox().getBounds2D().intersectsLine(leftLine.toLine2D())){
				makeOthersTrue();
				velocityVector = Geometry.reflectWall(leftLine,velocityVector);
				allHitSquareTakoz = false;
				collisionTime = 0;
				balla.setY(balla.getY() - 2);
				effectTime = 0;
				isCezeryeTriggered = true;
				GameEngineAudioHandler.getInstance().makeMoveSound();
				player = balla.getPlayer();
				GameEngineData.setTokatPoint(false);
				GameEngineData.setEngelPoint1(false);
				GameEngineData.setEngelPoint2(false);
				invoke();
				setX(10000);
				setY(10000);
				collisonBorderCreate();
			}

			else if(balla.boundingBox().getBounds2D().intersectsLine(rightLine.toLine2D())){
				makeOthersTrue();
				velocityVector = Geometry.reflectWall(rightLine,velocityVector);
				allHitSquareTakoz = false;
				collisionTime = 0;
				balla.setY(balla.getY() + 2);
				effectTime = 0;
				isCezeryeTriggered = true;
				GameEngineAudioHandler.getInstance().makeMoveSound();
				player = balla.getPlayer();
				GameEngineData.setTokatPoint(false);
				GameEngineData.setEngelPoint1(false);
				GameEngineData.setEngelPoint2(false);
				invoke();
				setX(10000);
				setY(10000);
				collisonBorderCreate();
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
		if(isCezeryeTriggered ){
			
			effectTime++;			
			if((effectTime/100)%10 == 3){
				if(effect == 1){

					if(player == 1){
						GameEngineData.getCezmi2().setL(GameEngineData.getWindowSize());
						GameEngineData.getCezmi2().setY(GameEngineData.getWindowSize() - GameEngineData.getCezmi2().getRadius());
						isCezeryeTriggered = false;
					}else if(player == 2){
						GameEngineData.getCezmi1().setL(GameEngineData.getWindowSize());
						GameEngineData.getCezmi1().setY(GameEngineData.getWindowSize() - GameEngineData.getCezmi2().getRadius());
						isCezeryeTriggered = false;
					}

				}else if(effect == 2){
					if(player == 1){
						GameEngineData.getCezmi2().setCezmiStep(1);
					}else if(player == 2){
						GameEngineData.getCezmi1().setCezmiStep(1);
					}

				}else if(effect == 3){
					if(player == 1){
						for (int i = 0; i < GameEngineData.getSquareTakozList().size(); i++) {		
							if(GameEngineData.getSquareTakozList().get(i).getPlayer() == 1){
								GameEngineData.getSquareTakozList().get(i).setL(GameEngineData.getWindowSize()/25);
								GameEngineData.getSquareTakozList().get(i).setBounds();
							}
						}

						for (int i = 0; i < GameEngineData.getTriangularTakozList().size(); i++) {		
							if(GameEngineData.getTriangularTakozList().get(i).getPlayer() == 1){
								GameEngineData.getTriangularTakozList().get(i).setL(GameEngineData.getWindowSize()/25);
								GameEngineData.getTriangularTakozList().get(i).set();
							}
						}
						
						for (int i = 0; i < GameEngineData.getFirildakList().size(); i++) {		
							if(GameEngineData.getFirildakList().get(i).getPlayer() == 1){
								GameEngineData.getFirildakList().get(i).setL(GameEngineData.getWindowSize()/25);
								GameEngineData.getFirildakList().get(i).setBounds();
								UIData.getFirildakUIList().set(i, new UIFirildak(GameEngineData.getFirildakList().get(i)));
							}
						}

					}else if(player == 2){
						for (int i = 0; i < GameEngineData.getSquareTakozList().size(); i++) {		
							if(GameEngineData.getSquareTakozList().get(i).getPlayer() == 2){
								GameEngineData.getSquareTakozList().get(i).setL(GameEngineData.getWindowSize()/25);
								GameEngineData.getSquareTakozList().get(i).setBounds();
							}
						}

						for (int i = 0; i < GameEngineData.getTriangularTakozList().size(); i++) {		
							if(GameEngineData.getTriangularTakozList().get(i).getPlayer() == 2){
								GameEngineData.getTriangularTakozList().get(i).setL(GameEngineData.getWindowSize()/25);
								GameEngineData.getTriangularTakozList().get(i).set();
							}
						}
						
						for (int i = 0; i < GameEngineData.getFirildakList().size(); i++) {		
							if(GameEngineData.getFirildakList().get(i).getPlayer() == 2){
								GameEngineData.getFirildakList().get(i).setL(GameEngineData.getWindowSize()/25);
								GameEngineData.getFirildakList().get(i).setBounds();
								UIData.getFirildakUIList().set(i, new UIFirildak(GameEngineData.getFirildakList().get(i)));
							}
						}
					}
				}
			}
		}

		if(collisionTime > 25){
			allHitSquareTakoz = true;
		}

		if(GameEngineData.getLevel() == 1){
			notifyBall(GameEngineData.getBallList().get(0));
		}else if (GameEngineData.getLevel() == 2){
			notifyBall(GameEngineData.getBallList().get(0));
			notifyBall(GameEngineData.getBallList().get(1));
		}else if (GameEngineData.getLevel() == 3){
			
		}else if (GameEngineData.getLevel() == 4){
			
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
	 * Sets the square available.
	 *
	 * @param b the new square available
	 */
	public void setSquareAvailable(boolean b) {
		allHitSquareTakoz = true;
	}

	/**
	 * Checks if is all hit square takoz.
	 *
	 * @return true, if is all hit square takoz
	 */
	public boolean isAllHitSquareTakoz() {
		return allHitSquareTakoz;
	}

	/**
	 * Sets the all hit square takoz.
	 *
	 * @param allHitSquareTakoz the new all hit square takoz
	 */
	public void setAllHitSquareTakoz(boolean allHitSquareTakoz) {
		this.allHitSquareTakoz = allHitSquareTakoz;
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

	/**
	 * Invoke.
	 */
	public void invoke(){

		/*************************************************************
		 * # Cezerye Operations										 *
		 *															 *
		 * # Make the gizmos on opponents side smaller (half size)   *
		 * # Make the gizmos on players side bigger (twice)		     *
		 * # Make the opponents cezmi freeze.					  	 *
		 * 														     *
		 *************************************************************/
		Random randomEffect = new Random();
		effect = randomEffect.nextInt(3)+1;

		if(effect == 1){
			smallerSizeEffect(); // Smaller Size Effect
		}else if(effect == 2){
			freezeCezmiEffect(); // Freeze Cezmi
		}else if(effect == 3){
			doubleGizmoEffect(); // Bigger Gizmo Effecaıt
		}	 
	}

	/**
	 * Collison border create.
	 */
	public void collisonBorderCreate(){

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
	 * Freeze cezmi effect.
	 */
	private void freezeCezmiEffect() {

		if(player == 1){
			GameEngineData.getCezmi2().setCezmiStep(0);
		}else if(player == 2){
			GameEngineData.getCezmi1().setCezmiStep(0);
		}
	}
	
	/**
	 * Double gizmo effect.
	 */
	private void doubleGizmoEffect(){

		if(player == 1){
			for (int i = 0; i < GameEngineData.getSquareTakozList().size(); i++) {		
				if(GameEngineData.getSquareTakozList().get(i).getPlayer() == 1){
					GameEngineData.getSquareTakozList().get(i).setL(GameEngineData.getSquareTakozList().get(i).getL()* 2);
					GameEngineData.getSquareTakozList().get(i).setBounds();
				}
			}

			for (int i = 0; i < GameEngineData.getTriangularTakozList().size(); i++) {		
				if(GameEngineData.getTriangularTakozList().get(i).getPlayer() == 1){
					GameEngineData.getTriangularTakozList().get(i).setL(GameEngineData.getTriangularTakozList().get(i).getL()* 2);
					GameEngineData.getTriangularTakozList().get(i).set();
				}
			}
			
			for (int i = 0; i < GameEngineData.getFirildakList().size(); i++) {		
				if(GameEngineData.getFirildakList().get(i).getPlayer() == 1){
					
					GameEngineData.getFirildakList().get(i).setL(GameEngineData.getFirildakList().get(i).getL()* 2);
					GameEngineData.getFirildakList().get(i).setBounds();
					UIData.getFirildakUIList().set(i, new UIFirildak(GameEngineData.getFirildakList().get(i)));
				}
			}

		}else if(player == 2){
			for (int i = 0; i < GameEngineData.getSquareTakozList().size(); i++) {		
				if(GameEngineData.getSquareTakozList().get(i).getPlayer() == 2){
					GameEngineData.getSquareTakozList().get(i).setL(GameEngineData.getSquareTakozList().get(i).getL()* 2);
					GameEngineData.getSquareTakozList().get(i).setBounds();
				}
			}

			for (int i = 0; i < GameEngineData.getTriangularTakozList().size(); i++) {		
				if(GameEngineData.getTriangularTakozList().get(i).getPlayer() == 2){
					GameEngineData.getTriangularTakozList().get(i).setL(GameEngineData.getTriangularTakozList().get(i).getL()* 2);
					GameEngineData.getTriangularTakozList().get(i).set();
				}
			}
			
			for (int i = 0; i < GameEngineData.getFirildakList().size(); i++) {		
				if(GameEngineData.getFirildakList().get(i).getPlayer() == 2){
					GameEngineData.getFirildakList().get(i).setL(GameEngineData.getFirildakList().get(i).getL()* 2);
					GameEngineData.getFirildakList().get(i).setBounds();
					UIData.getFirildakUIList().set(i, new UIFirildak(GameEngineData.getFirildakList().get(i)));
				}
			}
		}
	}

	/**
	 * Smaller size effect.
	 */
	public void smallerSizeEffect(){

		if(player == 1){
			GameEngineData.getCezmi2().setL((GameEngineData.getCezmi2().getRadius()*25) / 2);
			GameEngineData.getCezmi2().setY(GameEngineData.getCezmi2().getY() + GameEngineData.getCezmi2().getRadius());

		}else if(player == 2){
			GameEngineData.getCezmi1().setL((GameEngineData.getCezmi1().getRadius()*25) / 2);
			GameEngineData.getCezmi1().setY(GameEngineData.getCezmi1().getY() + GameEngineData.getCezmi1().getRadius());
		}
	}
}
