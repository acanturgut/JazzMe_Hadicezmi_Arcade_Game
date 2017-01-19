package domain.gameengine;

import java.awt.Color;
import java.util.ArrayList;
import domain.item.EngineBall;
import domain.item.EngineBorder;
import domain.item.EngineCezerye;
import domain.item.EngineCezmi;
import domain.item.EngineEngel;
import domain.item.EngineFirildak;
import domain.item.EngineGizmo;
import domain.item.EngineSquareTakoz;
import domain.item.EngineTokat;
import domain.item.EngineTriangularTakoz;
import domain.playerdata.DataPlayer1;
import domain.playerdata.DataPlayer2;

/**
 * The Class GameEngineData.
 */
public class GameEngineData {

	/** The instance. */
	private static GameEngineData instance = new GameEngineData();	
	
	/** The ball list. */
	private static ArrayList<EngineBall> ballList = new ArrayList<EngineBall>();
	
	/** The square takoz list. */
	private static ArrayList<EngineSquareTakoz> squareTakozList = new ArrayList<EngineSquareTakoz>();
	
	/** The triangular takoz list. */
	private static ArrayList<EngineTriangularTakoz> triangularTakozList = new ArrayList<EngineTriangularTakoz>(); 
	
	/** The cezerye. */
	private static EngineCezerye cezerye;
	
	/** The firildak list. */
	private static ArrayList<EngineFirildak> firildakList = new ArrayList<EngineFirildak>();
	
	/** The tokat list. */
	private static ArrayList<EngineTokat> tokatList = new ArrayList<EngineTokat>();

	/** The cezmi 1. */
	private static EngineCezmi cezmi1 = new EngineCezmi(new Color(11, 73, 127),1);
	
	/** The cezmi 2. */
	private static EngineCezmi cezmi2 = new EngineCezmi(new Color(57, 102, 65),2);

	/** The player 1. */
	private static DataPlayer1 player1 = new DataPlayer1();
	
	/** The player 2. */
	private static DataPlayer2 player2 = new DataPlayer2();
	
	/** The engel. */
	private static EngineEngel engel;
	
	private static EngineBorder border;
	
	/** The move cezmi 1 left. */
	private static Boolean moveCezmi1Left = false;
	
	/** The move cezmi 2 left. */
	private static Boolean moveCezmi2Left = false;
	
	/** The move cezmi 1 right. */
	private static Boolean moveCezmi1Right = false;
	
	/** The move cezmi 2 right. */
	private static Boolean moveCezmi2Right = false;
	
	/** The tokat point. */
	private static Boolean tokatPoint = false;
	
	/** The no wall point 1. */
	private static Boolean noWallPoint1 = false;
	
	/** The no wall point 2. */
	private static Boolean noWallPoint2 = false;
	
	/** The engel point. */
	private static Boolean engelPoint1 = false;
	
	/** The engel point 2. */
	private static Boolean engelPoint2 = false;
	
	/** The roof point 1. */
	private static Boolean roofPoint1= false;
	
	/** The roof point 2. */
	private static Boolean roofPoint2= false;
	
	/** The window size. */
	private static int windowSize;
	
	/** The time. */
	private static long time;
	
	/** The level. */
	private static int level = 1;
	
	/**
	 * Instantiates a new game engine data.
	 */
	private GameEngineData() {}
	
	/**
	 * Gets the single instance of GameEngineData.
	 *
	 * @return single instance of GameEngineData
	 */
	public static GameEngineData getInstance(){
		return instance;
	}

	// Ball

	/**
	 * Gets the ball list.
	 *
	 * @return the ball list
	 */
	public static ArrayList<EngineBall> getBallList() {
		return ballList;
	}

	/**
	 * Adds the ball.
	 *
	 * @param b the b
	 */
	public static void addBall(EngineBall b) {
		ballList.add(b);
	}

	// Square Takoz

	/**
	 * Gets the square takoz list.
	 *
	 * @return the square takoz list
	 */
	public static ArrayList<EngineSquareTakoz> getSquareTakozList() {
		return squareTakozList;
	}

	/**
	 * Adds the gizmo.
	 *
	 * @param g the g
	 */
	// Add Gizmo
	public static void addGizmo(EngineGizmo g) {
		switch(g.getType()) {
		case "squareTakoz": squareTakozList.add((EngineSquareTakoz) g);
		break;
		case "firildak": firildakList.add((EngineFirildak) g);
		break;
		case "triangularTakoz": triangularTakozList.add((EngineTriangularTakoz) g);
		break;
		case "tokat": tokatList.add((EngineTokat) g);
		break;
		default: break;
		}
	}

	// Takoz Triangular

	/**
	 * Gets the triangular takoz list.
	 *
	 * @return the triangular takoz list
	 */
	public static ArrayList<EngineTriangularTakoz> getTriangularTakozList() {
		return triangularTakozList;
	}

	// Tokat

	/**
	 * Gets the tokat list.
	 *
	 * @return the tokat list
	 */
	public static ArrayList<EngineTokat> getTokatList() {
		return tokatList;
	}

	// Cezerye

	/**
	 * Gets the cezerye.
	 *
	 * @return the cezerye
	 */
	public static EngineCezerye getCezerye() {
		return cezerye;
	}
	
	/**
	 * Sets the cezerye.
	 *
	 * @param cezerye the new cezerye
	 */
	public static void setCezerye(EngineCezerye cezerye) {
		GameEngineData.cezerye = cezerye;
	}

	// Firildak

	/**
	 * Gets the firildak list.
	 *
	 * @return the firildak list
	 */
	public static ArrayList<EngineFirildak> getFirildakList() {
		return firildakList;
	}

	/**
	 * Sets the firildak list.
	 *
	 * @param f the new firildak list
	 */
	public void setFirildakList(EngineFirildak f) {
		firildakList.add(f);
	}

	// Cezmi 1

	/**
	 * Gets the cezmi 1.
	 *
	 * @return the cezmi 1
	 */
	public static EngineCezmi getCezmi1() {
		return cezmi1;
	}

	/**
	 * Sets the cezmi 1.
	 *
	 * @param cezmi1 the new cezmi 1
	 */
	public static void setCezmi1(EngineCezmi cezmi1) {
		GameEngineData.cezmi1 = cezmi1;
	}

	// Cezmi 2

	/**
	 * Gets the cezmi 2.
	 *
	 * @return the cezmi 2
	 */
	public static EngineCezmi getCezmi2() {
		return cezmi2;
	}

	/**
	 * Sets the cezmi 2.
	 *
	 * @param cezmi2 the new cezmi 2
	 */
	public static void setCezmi2(EngineCezmi cezmi2) {
		GameEngineData.cezmi2 = cezmi2;
	}
	
	// Cezmi Movement
	
	/**
	 * Gets the move cezmi 1 left.
	 *
	 * @return the move cezmi 1 left
	 */
	public static Boolean getMoveCezmi1Left() {
		return moveCezmi1Left;
	}

	/**
	 * Sets the move cezmi 1 left.
	 *
	 * @param moveCezmi1Left the new move cezmi 1 left
	 */
	public static void setMoveCezmi1Left(Boolean moveCezmi1Left) {
		GameEngineData.moveCezmi1Left = moveCezmi1Left;
	}

	/**
	 * Gets the move cezmi 2 left.
	 *
	 * @return the move cezmi 2 left
	 */
	public static Boolean getMoveCezmi2Left() {
		return moveCezmi2Left;
	}

	/**
	 * Sets the move cezmi 2 left.
	 *
	 * @param moveCezmi2Left the new move cezmi 2 left
	 */
	public static void setMoveCezmi2Left(Boolean moveCezmi2Left) {
		GameEngineData.moveCezmi2Left = moveCezmi2Left;
	}

	/**
	 * Gets the move cezmi 1 right.
	 *
	 * @return the move cezmi 1 right
	 */
	public static Boolean getMoveCezmi1Right() {
		return moveCezmi1Right;
	}

	/**
	 * Sets the move cezmi 1 right.
	 *
	 * @param moveCezmi1Right the new move cezmi 1 right
	 */
	public static void setMoveCezmi1Right(Boolean moveCezmi1Right) {
		GameEngineData.moveCezmi1Right = moveCezmi1Right;
	}

	/**
	 * Gets the move cezmi 2 right.
	 *
	 * @return the move cezmi 2 right
	 */
	public static Boolean getMoveCezmi2Right() {
		return moveCezmi2Right;
	}

	/**
	 * Sets the move cezmi 2 right.
	 *
	 * @param moveCezmi2Right the new move cezmi 2 right
	 */
	public static void setMoveCezmi2Right(Boolean moveCezmi2Right) {
		GameEngineData.moveCezmi2Right = moveCezmi2Right;
	}

	// Engel

	/**
	 * Gets the engel.
	 *
	 * @return the engel
	 */
	public static EngineEngel getEngel() {
		return engel;
	}

	/**
	 * Sets the engel.
	 *
	 * @param engel the new engel
	 */
	public static void setEngel(EngineEngel engel) {
		GameEngineData.engel = engel;
	}

	// Level

	/**
	 * Gets the level.
	 *
	 * @return the level
	 */
	public static int getLevel() {
		return level;
	}

	/**
	 * Sets the level.
	 *
	 * @param level the new level
	 */
	public static void setLevel(int level) {
		GameEngineData.level = level;
	}
	
	// Clear for new state

	/**
	 * Clear all.
	 */
	public static  void clearAll(){
		tokatList.clear();
		squareTakozList.clear();
		triangularTakozList.clear();
		firildakList.clear();	
		player1.setGizmoCount(4);
		player2.setGizmoCount(4);
		ballList.clear();	
	}

	// Player
	
	/**
	 * Gets the player 1.
	 *
	 * @return the player 1
	 */
	public static DataPlayer1 getPlayer1() {
		return player1;
	}

	/**
	 * Sets the player 1.
	 *
	 * @param player1 the new player 1
	 */
	public static void setPlayer1(DataPlayer1 player1) {
		GameEngineData.player1 = player1;
	}

	/**
	 * Gets the player 2.
	 *
	 * @return the player 2
	 */
	public static DataPlayer2 getPlayer2() {
		return player2;
	}

	/**
	 * Sets the player 2.
	 *
	 * @param player2 the new player 2
	 */
	public static void setPlayer2(DataPlayer2 player2) {
		GameEngineData.player2 = player2;
	}

	// Time
	
	/**
	 * Gets the time.
	 *
	 * @return the time
	 */
	public static long getTime() {
		return time;
	}

	/**
	 * Sets the time.
	 *
	 * @param time the new time
	 */
	public static void setTime(long time) {
		GameEngineData.time = time;
	}	
	
	/**
	 * Gets the window size.
	 *
	 * @return the window size
	 */
	public static int getWindowSize() {
		return windowSize;
	}
	
	/**
	 * Sets the window size.
	 *
	 * @param size the new window size
	 */
	public static void setWindowSize(int size) {
		windowSize = size;
	}

	/**
	 * Gets the tokat point.
	 *
	 * @return the tokat point
	 */
	public static Boolean getTokatPoint() {
		return tokatPoint;
	}

	/**
	 * Sets the tokat point.
	 *
	 * @param tokatPoint the new tokat point
	 */
	public static void setTokatPoint(Boolean tokatPoint) {
		GameEngineData.tokatPoint = tokatPoint;
	}

	/**
	 * Gets the no wall point 1.
	 *
	 * @return the no wall point 1
	 */
	public static Boolean getNoWallPoint1() {
		return noWallPoint1;
	}

	/**
	 * Sets the no wall point 1.
	 *
	 * @param noWallPoint the new no wall point 1
	 */
	public static void setNoWallPoint1(Boolean noWallPoint) {
		GameEngineData.noWallPoint1 = noWallPoint;
	}

	/**
	 * Gets the no wall point 2.
	 *
	 * @return the no wall point 2
	 */
	public static Boolean getNoWallPoint2() {
		return noWallPoint2;
	}

	/**
	 * Sets the no wall point 2.
	 *
	 * @param noWallPoint2 the new no wall point 2
	 */
	public static void setNoWallPoint2(Boolean noWallPoint2) {
		GameEngineData.noWallPoint2 = noWallPoint2;
	}

	/**
	 * Gets the engel point.
	 *
	 * @return the engel point
	 */
	public static Boolean getEngelPoint1() {
		return engelPoint1;
	}

	/**
	 * Sets the engel point.
	 *
	 * @param engelPoint the new engel point
	 */
	public static void setEngelPoint1(Boolean engelPoint) {
		GameEngineData.engelPoint1 = engelPoint;
	}

	/**
	 * Gets the engel point 2.
	 *
	 * @return the engel point 2
	 */
	public static Boolean getEngelPoint2() {
		return engelPoint2;
	}

	/**
	 * Sets the engel point 2.
	 *
	 * @param engelPoint2 the new engel point 2
	 */
	public static void setEngelPoint2(Boolean engelPoint2) {
		GameEngineData.engelPoint2 = engelPoint2;
	}

	/**
	 * Gets the roof point 1.
	 *
	 * @return the roof point 1
	 */
	public static Boolean getRoofPoint1() {
		return roofPoint1;
	}

	/**
	 * Sets the roof point 1.
	 *
	 * @param roofPoint1 the new roof point 1
	 */
	public static void setRoofPoint1(Boolean roofPoint1) {
		GameEngineData.roofPoint1 = roofPoint1;
	}

	/**
	 * Gets the roof point 2.
	 *
	 * @return the roof point 2
	 */
	public static Boolean getRoofPoint2() {
		return roofPoint2;
	}

	/**
	 * Sets the roof point 2.
	 *
	 * @param roofPoint2 the new roof point 2
	 */
	public static void setRoofPoint2(Boolean roofPoint2) {
		GameEngineData.roofPoint2 = roofPoint2;
	}

	public static EngineBorder getBorder() {
		return border;
	}

	public static void setBorder(EngineBorder border) {
		GameEngineData.border = border;
	}
}
