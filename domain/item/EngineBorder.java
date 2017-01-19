package domain.item;
import java.util.Random;
import domain.gameengine.GameEngineAudioHandler;
import domain.gameengine.GameEngineData;
import domain.physics.Circle;
import domain.physics.Geometry;
import domain.physics.LineSegment;
import domain.physics.Vect;
/**
 * The Class EngineBorder.
 */
public class EngineBorder implements Runnable{
	/** The is border available for collision. */
	private static boolean isBorderAvailableForCollision = true;
	/** The window size. */
	private int windowSize;
	/** The border collision time. */
	private long borderCollisionTime = 0;
	/**
	 * Instantiates a new engine border.
	 *
	 * @param windowSize the window size
	 */
	public EngineBorder(int windowSize){
		this.windowSize=windowSize;
	}
	/**
	 * Border collision.
	 *
	 * @param b the b
	 */
	public void borderCollision(EngineBall b){
		Vect velocityVector = b.getVelocityVector();
		LineSegment topWall = new LineSegment(0,0,windowSize,0);
		LineSegment bottomWall = new LineSegment(0,windowSize,windowSize * 10,windowSize);
		LineSegment leftWall = new LineSegment(0,0,0,windowSize);
		LineSegment rightWall = new LineSegment(windowSize,0,windowSize,windowSize);
		Circle ball = new Circle(new Vect(b.getX() + b.getR(), b.getY() + b.getR() ), b.getR()); 
		if(isBorderAvailableForCollision){
			if (ball.toEllipse2D().getBounds().intersectsLine(topWall.toLine2D())) {
				GameEngineAudioHandler.getInstance().makeDropSound();
				b.setVelocityVector(Geometry.reflectWall(topWall, velocityVector));
				notifyCollision();
				isBorderAvailableForCollision= false;
				GameEngineData.setTokatPoint(false);
				GameEngineData.setEngelPoint1(false);
				GameEngineData.setEngelPoint2(false);
				if(GameEngineData.getRoofPoint1()){
					GameEngineData.getPlayer1().setScore(GameEngineData.getPlayer1().getScore() - 0.5);
				}
				if(GameEngineData.getRoofPoint2()){
					GameEngineData.getPlayer2().setScore(GameEngineData.getPlayer2().getScore() - 0.5);
				}
				b.setY(b.getY() + 5);
				borderCollisionTime = 0;
			}else if (ball.toEllipse2D().getBounds().intersectsLine(rightWall.toLine2D())) { 
				GameEngineAudioHandler.getInstance().makeDropSound();
				b.setVelocityVector(Geometry.reflectWall(rightWall, velocityVector));
				notifyCollision();
				isBorderAvailableForCollision= false;
				GameEngineData.setTokatPoint(false);
				GameEngineData.setEngelPoint1(false);
				GameEngineData.setEngelPoint2(false);
				GameEngineData.setNoWallPoint1(false);
				GameEngineData.setRoofPoint1(false);
				GameEngineData.setRoofPoint2(false);
				b.setX(b.getX() - 5);
				borderCollisionTime = 0;
			}else if (ball.toEllipse2D().getBounds().intersectsLine(leftWall.toLine2D())) { 
				GameEngineAudioHandler.getInstance().makeDropSound();
				b.setVelocityVector(Geometry.reflectWall(leftWall, velocityVector));
				notifyCollision();
				isBorderAvailableForCollision= false;
				GameEngineData.setTokatPoint(false);
				GameEngineData.setEngelPoint1(false);
				GameEngineData.setEngelPoint2(false);
				GameEngineData.setNoWallPoint2(false);
				GameEngineData.setRoofPoint1(false);
				GameEngineData.setRoofPoint2(false);
				borderCollisionTime = 0;
				b.setX(b.getX() + 5);
			}
		}

		if (ball.toEllipse2D().getBounds().intersectsLine(bottomWall.toLine2D())) {
			GameEngineAudioHandler.getInstance().makeDropSound();
			b.setVelocityVector(Geometry.reflectWall(bottomWall, velocityVector));
			notifyCollision();
			isBorderAvailableForCollision= false;
			borderCollisionTime = 0;
			b.setY(b.getY() - 5);
			GameEngineData.setEngelPoint1(false);
			GameEngineData.setEngelPoint2(false);
			GameEngineData.setRoofPoint1(false);
			GameEngineData.setRoofPoint2(false);

			if(b.getX() > windowSize/2){
				if(GameEngineData.getTokatPoint()){
					GameEngineData.getPlayer1().setScore(GameEngineData.getPlayer1().getScore() + 2);
				}
				else{
					GameEngineData.getPlayer1().setScore(GameEngineData.getPlayer1().getScore() + 1);	
				}
				if(GameEngineData.getNoWallPoint2()){
					GameEngineData.getPlayer2().setScore(GameEngineData.getPlayer2().getScore() - 1);
				}
				else{

				}
			}else{
				if(GameEngineData.getTokatPoint()){
					GameEngineData.getPlayer2().setScore(GameEngineData.getPlayer2().getScore() + 2);
				}
				else{
					GameEngineData.getPlayer2().setScore(GameEngineData.getPlayer2().getScore() + 1);	
				}
				if(GameEngineData.getNoWallPoint1()){
					GameEngineData.getPlayer1().setScore(GameEngineData.getPlayer1().getScore() - 1);
				}
				else{

				}
			}

			if(GameEngineData.getBallList().get(0).getR() > windowSize/125){

				if((GameEngineData.getPlayer1().getScore() + GameEngineData.getPlayer2().getScore())%2 == 0 && 
						(GameEngineData.getPlayer1().getScore() + GameEngineData.getPlayer2().getScore()) != 0){

					if(GameEngineData.getLevel() == 1){

						GameEngineData.getBallList().get(0).setR(GameEngineData.getBallList().get(0).getR() - windowSize/250);

					}else{
						GameEngineData.getBallList().get(0).setR(GameEngineData.getBallList().get(0).getR() - windowSize/250);
						GameEngineData.getBallList().get(1).setR(GameEngineData.getBallList().get(1).getR() - windowSize/250);
					}
				}
			}
			
			int xStep;
			int yStep;
			Random ballLocation = new Random();
			int i = ballLocation.nextInt(100)+1;
			if(i > 50){
				xStep = 2;
				yStep = 5;
			}else{
				xStep = -2;
				yStep = 5;
			}
			b.setVelocityVector(new Vect(xStep,yStep));	
			b.setX(windowSize/2);
			b.setY(windowSize/2 - windowSize/4);	

			GameEngineData.setNoWallPoint1(false);
			GameEngineData.setNoWallPoint2(false);
			GameEngineData.setTokatPoint(false);
		}
	}
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		borderCollisionTime++;
		if(borderCollisionTime > 10){
			isBorderAvailableForCollision = true;
		}
		isBorderAvailableForCollision = true;
		if(GameEngineData.getLevel() == 1){
			borderCollision(GameEngineData.getBallList().get(0));
		}else if(GameEngineData.getLevel() == 2){
			borderCollision(GameEngineData.getBallList().get(0));
			borderCollision(GameEngineData.getBallList().get(1));
		}else if(GameEngineData.getLevel() == 3){
			borderCollision(GameEngineData.getBallList().get(0));
			borderCollision(GameEngineData.getBallList().get(1));
			borderCollision(GameEngineData.getBallList().get(2));
			borderCollision(GameEngineData.getBallList().get(3));
		}else if(GameEngineData.getLevel() == 4){


		}
	}
	public static void setBorderAvailableForCollision(boolean b) {
		isBorderAvailableForCollision = b;
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