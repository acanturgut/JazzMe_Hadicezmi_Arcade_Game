package domain.gameengine;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import domain.item.EngineBorder;
import domain.item.EngineCezerye;
import domain.physics.Geometry;
import domain.physics.Geometry.VectPair;
import domain.physics.Vect;

/**
 * The Class GameEngineUpdater.
 */
public class GameEngineUpdater {

	/** The game window. */
	private int gameWindow;

	/** The step size. */
	private int stepSize = 1;

	/** The level. */
	private int level;

	/** The time up. */
	private boolean timeUp = true;

	/** The square notify thread list. */
	private ArrayList<Thread> squareNotifyThreadList;

	/** The tri notify thread list. */
	private ArrayList<Thread> triNotifyThreadList;

	/** The firildak notify thread list. */
	private ArrayList<Thread> firildakNotifyThreadList;

	/** The ball 1. */
	private Thread ball1;

	/** The ball 2. */
	private Thread ball2;

	/** The engel thread. */
	private Thread engelThread;

	/** The border thread. */
	private Thread borderThread;

	/** The cezerye thread. */
	private Thread cezeryeThread;

	private Thread ball3;

	private Thread ball4;

	/**
	 * Instantiates a new game engine updater.
	 *
	 * @param gameWindow the game window
	 * @param level the level
	 */
	public GameEngineUpdater(int gameWindow, int level){
		this.gameWindow = gameWindow;
		this.level = level;

		borderThread = new Thread (GameEngineData.getBorder());

		for (int i = 0; i < GameEngineData.getTriangularTakozList().size(); i++) {
			GameEngineData.getTriangularTakozList().get(i).set();;
		}

		if(level == 1){
			ball1 = new  Thread(GameEngineData.getBallList().get(0));

		}else if(level == 2){
			ball1 = new  Thread(GameEngineData.getBallList().get(0));
			ball2 = new  Thread(GameEngineData.getBallList().get(1));
		}else if(level == 3){
			ball1 = new  Thread(GameEngineData.getBallList().get(0));
			ball2 = new  Thread(GameEngineData.getBallList().get(1));
			ball3 = new  Thread(GameEngineData.getBallList().get(2));
			ball4 = new  Thread(GameEngineData.getBallList().get(3));
		}else if(level == 4){

		}

		squareNotifyThreadList = new ArrayList<Thread>();
		for (int i = 0; i < GameEngineData.getSquareTakozList().size(); i++) {
			squareNotifyThreadList.add(new Thread(GameEngineData.getSquareTakozList().get(i)));
		}	

		triNotifyThreadList = new ArrayList<Thread>();
		for (int i = 0; i < GameEngineData.getTriangularTakozList().size(); i++) {
			triNotifyThreadList.add(new Thread(GameEngineData.getTriangularTakozList().get(i)));
		}

		firildakNotifyThreadList = new ArrayList<Thread>();
		for (int i = 0; i < GameEngineData.getFirildakList().size(); i++) {
			firildakNotifyThreadList.add(new Thread(GameEngineData.getFirildakList().get(i)));
		}

		engelThread = new Thread(GameEngineData.getEngel());


		GameEngineData.setCezerye(new EngineCezerye(100000, 1000000,gameWindow/25, 0));
		cezeryeThread = new Thread(GameEngineData.getCezerye());

	}

	/**
	 * Update game.
	 */
	public void updateGame(){
		cezeryeCreation(GameEngineData.getTime());
		cezeryeNotify();
		engelNotify();
		tokatMovement();
		squareNotify();
		triNotify();
		firildakNotify();	
		borderNotify();
		ballToBallCollision();
	}

	/**
	 * Cezerye creation.
	 *
	 * @param gameTime the game time
	 */
	public void cezeryeCreation(long gameTime){

		if((gameTime/100)%10 == 3){
			Random rand = new Random();

			int positionX = rand.nextInt(gameWindow) + 0;
			int positionY = rand.nextInt(gameWindow) - (gameWindow/25)*6;
			int windowSizePart = gameWindow/25;
			int farkX = positionX%windowSizePart;
			int farkY = positionY%windowSizePart; 

			while(!isPositionAvailable(positionX-farkX, positionY-farkY)){
				positionX = rand.nextInt(gameWindow) + 0;
				positionY = rand.nextInt(gameWindow) - (gameWindow/25)*6;
				windowSizePart = gameWindow/25;
				farkX = positionX%windowSizePart;
				farkY = positionY%windowSizePart;
			}

			if(timeUp){
				GameEngineData.getCezerye().setX(positionX - farkX);
				GameEngineData.getCezerye().setY(positionY - farkY);
				GameEngineData.getCezerye().collisonBorderCreate();
				timeUp = false;
			} 
		}

		if((gameTime/100)%10 == 6){
			if(!timeUp){
				GameEngineData.getCezerye().setX(10000);
				GameEngineData.getCezerye().setY(10000);
				GameEngineData.getCezerye().collisonBorderCreate();
				timeUp = true;
			}	
		}	
	}

	/**
	 * Engel notify.
	 */
	public void engelNotify(){
		engelThread.run();
	}

	/**
	 * Border notify.
	 */
	public void borderNotify(){
		borderThread.run();
	}

	/**
	 * Cezerye notify.
	 */
	public void cezeryeNotify(){
		cezeryeThread.run();
	}

	/**
	 * Tokat movement.
	 */
	public void tokatMovement(){
		ArrayList<Thread> tokatThreadList = new ArrayList<Thread>();
		for (int i = 0; i < GameEngineData.getTokatList().size(); i++) {
			tokatThreadList.add(new Thread(GameEngineData.getTokatList().get(i)));
			tokatThreadList.get(i).start();
		}	
	}

	/**
	 * Square notify.
	 */
	public void squareNotify(){
		for (int i = 0; i < squareNotifyThreadList.size(); i++) {
			squareNotifyThreadList.get(i).run();
		}	
	}

	/**
	 * Firildak notify.
	 */
	public void firildakNotify(){
		for (int i = 0; i < firildakNotifyThreadList.size(); i++) {
			firildakNotifyThreadList.get(i).run();
		}	
	}

	/**
	 * Tri notify.
	 */
	public void triNotify(){		
		for (int i = 0; i < triNotifyThreadList.size(); i++) {
			triNotifyThreadList.get(i).run();
		}	
	}

	/**
	 * Cezmi movement.
	 */
	public void cezmiMovement(){
		if(GameEngineData.getMoveCezmi1Left()){
			if(0 < GameEngineData.getCezmi1().getX() +  GameEngineData.getCezmi1().getRadius() &&  gameWindow == GameEngineData.getCezmi1().getY() + GameEngineData.getCezmi1().getRadius() ){
				for (int i = 0; i < stepSize; i++) {
					GameEngineData.getCezmi1().moveLeft();
					cezmiCollusion();
				}
			}else if(0 >= GameEngineData.getCezmi1().getX()  &&  0 < GameEngineData.getCezmi1().getCenterY()){
				if(level == 2){
					for (int i = 0; i < stepSize; i++) {
						GameEngineData.getCezmi1().moveUp();
						cezmiCollusion();
					}
				}
			}
		}

		if(GameEngineData.getMoveCezmi1Right()){
			if(gameWindow/2 - (gameWindow/25)*2 > GameEngineData.getCezmi1().getX() + GameEngineData.getEngel().getWidth() &&  gameWindow == GameEngineData.getCezmi1().getY() + GameEngineData.getCezmi1().getRadius() ){
				for (int i = 0; i < stepSize; i++) {
					GameEngineData.getCezmi1().moveRight();
					cezmiCollusion();
				}
			}else if(0 >= GameEngineData.getCezmi1().getX() &&  gameWindow >  GameEngineData.getCezmi1().getY()){
				if(level == 2){
					for (int i = 0; i < stepSize; i++) {
						GameEngineData.getCezmi1().moveDown();
						cezmiCollusion();
					}
				}
			}
		}

		if(GameEngineData.getMoveCezmi2Left()){
			if(gameWindow/2  < GameEngineData.getCezmi2().getX() - GameEngineData.getEngel().getWidth() &&  gameWindow == GameEngineData.getCezmi2().getY() + GameEngineData.getCezmi2().getRadius() ){
				for (int i = 0; i < stepSize; i++) {
					GameEngineData.getCezmi2().moveLeft();
					cezmiCollusion();
				}	
			}else if(gameWindow - (gameWindow/25)*2 + GameEngineData.getCezmi2().getRadius() <= GameEngineData.getCezmi2().getX()  ){
				if(level == 2){
					for (int i = 0; i < stepSize; i++) {
						GameEngineData.getCezmi2().moveDown();
						cezmiCollusion();
					}
				}
			}
		}

		if(GameEngineData.getMoveCezmi2Right()){
			if(gameWindow - (gameWindow/25)*2 + GameEngineData.getCezmi2().getRadius() > GameEngineData.getCezmi2().getX() &&  gameWindow == GameEngineData.getCezmi2().getY() + GameEngineData.getCezmi2().getRadius()){
				for (int i = 0; i < stepSize; i++) {
					GameEngineData.getCezmi2().moveRight();
					cezmiCollusion();
				}
			}else if(gameWindow - (gameWindow/25)*2 + GameEngineData.getCezmi2().getRadius() <= GameEngineData.getCezmi2().getX() &&  0 < GameEngineData.getCezmi2().getCenterY() ){
				if(level == 2){
					for (int i = 0; i < stepSize; i++) {
						GameEngineData.getCezmi2().moveUp();
						cezmiCollusion();
					}
				}
			}
		}
	}

	/**
	 * Cezmi movement AI.
	 */
	public void cezmiMovementAI(){
		if(GameEngineData.getMoveCezmi1Left()){
			if(0 < GameEngineData.getCezmi1().getX() +  GameEngineData.getCezmi1().getRadius() &&  gameWindow == GameEngineData.getCezmi1().getY() + GameEngineData.getCezmi1().getRadius() ){
				for (int i = 0; i < stepSize; i++) {
					GameEngineData.getCezmi1().moveLeft();
					cezmiCollusion();
				}
			}else if(0 >= GameEngineData.getCezmi1().getX()  &&  0 < GameEngineData.getCezmi1().getCenterY()){
				if(level == 2){
					for (int i = 0; i < stepSize; i++) {
						GameEngineData.getCezmi1().moveUp();
						cezmiCollusion();
					}
				}
			}
		}

		if(GameEngineData.getMoveCezmi1Right()){
			if(gameWindow/2 - (gameWindow/25)*2 > GameEngineData.getCezmi1().getX() + GameEngineData.getEngel().getWidth() &&  gameWindow == GameEngineData.getCezmi1().getY() + GameEngineData.getCezmi1().getRadius() ){
				for (int i = 0; i < stepSize; i++) {
					GameEngineData.getCezmi1().moveRight();
					cezmiCollusion();
				}
			}else if(0 >= GameEngineData.getCezmi1().getX() &&  gameWindow >  GameEngineData.getCezmi1().getY()){
				if(level == 2){
					for (int i = 0; i < stepSize; i++) {
						GameEngineData.getCezmi1().moveDown();
						cezmiCollusion();
					}
				}
			}
		}

		if(gameWindow/2  < GameEngineData.getCezmi2().getX() - GameEngineData.getEngel().getWidth() &&  gameWindow == GameEngineData.getCezmi2().getY() + GameEngineData.getCezmi2().getRadius() ){
			if((GameEngineData.getCezmi2().getX() + GameEngineData.getCezmi2().getRadius()) > GameEngineData.getBallList().get(0).getX()){
				GameEngineData.getCezmi2().moveLeft();
				cezmiCollusion();
			}

		}else if(gameWindow - (gameWindow/25)*2 + GameEngineData.getCezmi2().getRadius() <= GameEngineData.getCezmi2().getX()  ){
			if(level == 2){
				GameEngineData.getCezmi2().moveDown();
				cezmiCollusion();		
			}
		}

		if(gameWindow - (gameWindow/25)*2 + GameEngineData.getCezmi2().getRadius() > GameEngineData.getCezmi2().getX() &&  gameWindow == GameEngineData.getCezmi2().getY() + GameEngineData.getCezmi2().getRadius()){
			if((GameEngineData.getCezmi2().getX() + GameEngineData.getCezmi2().getRadius()) < GameEngineData.getBallList().get(0).getX()){
				GameEngineData.getCezmi2().moveRight();
				cezmiCollusion();

			}
		}else if(gameWindow - (gameWindow/25)*2 + GameEngineData.getCezmi2().getRadius() <= GameEngineData.getCezmi2().getX() &&  0 < GameEngineData.getCezmi2().getCenterY() ){
			if(level == 2){
				GameEngineData.getCezmi2().moveUp();
				cezmiCollusion();
			}
		}
	}

	/**
	 * Cezmi collusion.
	 */
	public void cezmiCollusion(){
		GameEngineData.getCezmi1().run();
		GameEngineData.getCezmi2().run();
	}

	/**
	 * Checks if is position available.
	 *
	 * @param x the x
	 * @param y the y
	 * @return true, if is position available
	 */
	public boolean isPositionAvailable(int x, int y){

		Rectangle mouse = new Rectangle(x, y, 1, 1);
		Boolean existLocation = true;

		for (int j = 0; j < GameEngineData.getSquareTakozList().size(); j++) {
			if(GameEngineData.getSquareTakozList().get(j).boundingBox().intersects(mouse)){
				existLocation = false;
			}
		}

		for (int j = 0; j < GameEngineData.getTriangularTakozList().size(); j++) {
			if(GameEngineData.getTriangularTakozList().get(j).boundingBox().intersects(mouse)){
				existLocation = false;
			}
		}

		for (int j = 0; j < GameEngineData.getFirildakList().size(); j++) {
			if(GameEngineData.getFirildakList().get(j).boundingBox().intersects(mouse)){
				existLocation = false;
			}
		}

		for (int j = 0; j < GameEngineData.getTokatList().size(); j++) {
			if(GameEngineData.getTokatList().get(j).boundingBox().intersects(mouse)){
				existLocation = false;
			}
		}
		return existLocation;
	}

	public void ballToBallCollision(){
		if(GameEngineData.getLevel() == 2){
			if(GameEngineData.getBallList().get(0).boundingBox().intersects(GameEngineData.getBallList().get(1).boundingBox().getFrame())){

				VectPair vectPair = Geometry.reflectBalls(new Vect(GameEngineData.getBallList().get(0).getX() - GameEngineData.getBallList().get(0).getR(),GameEngineData.getBallList().get(0).getY() - GameEngineData.getBallList().get(0).getR() ), 
						1, GameEngineData.getBallList().get(0).getVelocityVector(), 
						new Vect(GameEngineData.getBallList().get(1).getX() - GameEngineData.getBallList().get(1).getR(),GameEngineData.getBallList().get(1).getY() - GameEngineData.getBallList().get(1).getR() ), 1, 
						GameEngineData.getBallList().get(1).getVelocityVector());

				GameEngineData.getBallList().get(0).setVelocityVector(vectPair.v1);
				GameEngineData.getBallList().get(1).setVelocityVector(vectPair.v2);
			}
		}
	}


	/**
	 * Ball movement.
	 */
	public void ballMovement(){
		if(level == 1){
			ball1.run();

		}else if(level == 2){
			ball1.run();
			ball2.run();
		}else if(level == 3){
			ball1.run();
			ball2.run();
			ball3.run();
			ball4.run();
		}
	}
}