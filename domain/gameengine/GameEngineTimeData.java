package domain.gameengine;
import java.util.ArrayList;

/**
 * The Class GameEngineTimeData.
 */
public class GameEngineTimeData {
	
	/** The instance. */
	private static GameEngineTimeData instance = new GameEngineTimeData();
	
	/** The cezmi 1 collision time. */
	private static long cezmi1CollisionTime;
	
	/** The cezmi 2 collision time. */
	private static long cezmi2CollisionTime;
	
	/** The wall collision time. */
	private static long wallCollisionTime;
	
	/** The engel collision time. */
	private static long engelCollisionTime;
	
	/** The triangluar takoz collision time list. */
	private static ArrayList<Long> triangluarTakozCollisionTimeList;
	
	/** The square takoz collision time list. */
	private static ArrayList<Long> squareTakozCollisionTimeList;
	
	/** The tokat collision time list. */
	private static ArrayList<Long> tokatCollisionTimeList;
	
	/** The cezerye collision time list. */
	private static ArrayList<Long> cezeryeCollisionTimeList;
	
	/** The firildak collision time list. */
	private static ArrayList<Long> firildakCollisionTimeList;
	
	/**
	 * Instantiates a new game engine time data.
	 */
	private GameEngineTimeData(){}

	/**
	 * Gets the single instance of GameEngineTimeData.
	 *
	 * @return single instance of GameEngineTimeData
	 */
	public static GameEngineTimeData getInstance(){
		return instance;
	}

	/**
	 * Sets the instance.
	 *
	 * @param instance the new instance
	 */
	public static void setInstance(GameEngineTimeData instance) {
		GameEngineTimeData.instance = instance;
	}

	/**
	 * Sets the cezmi 1 collision time.
	 *
	 * @param cezmi1CollisionTime the new cezmi 1 collision time
	 */
	public static void setCezmi1CollisionTime(long cezmi1CollisionTime) {
		GameEngineTimeData.cezmi1CollisionTime = cezmi1CollisionTime;
	}

	/**
	 * Sets the cezmi 2 collision time.
	 *
	 * @param cezmi2CollisionTime the new cezmi 2 collision time
	 */
	public static void setCezmi2CollisionTime(long cezmi2CollisionTime) {
		GameEngineTimeData.cezmi2CollisionTime = cezmi2CollisionTime;
	}

	/**
	 * Sets the wall collision time.
	 *
	 * @param wallCollisionTime the new wall collision time
	 */
	public static void setWallCollisionTime(long wallCollisionTime) {
		GameEngineTimeData.wallCollisionTime = wallCollisionTime;
	}

	/**
	 * Sets the engel collision time.
	 *
	 * @param engelCollisionTime the new engel collision time
	 */
	public static void setEngelCollisionTime(long engelCollisionTime) {
		GameEngineTimeData.engelCollisionTime = engelCollisionTime;
	}

	/**
	 * Adds the triangluar takoz collision time list.
	 *
	 * @param triangluarTakozCollisionTime the triangluar takoz collision time
	 */
	public static void addTriangluarTakozCollisionTimeList(Long triangluarTakozCollisionTime) {
		GameEngineTimeData.triangluarTakozCollisionTimeList.add(triangluarTakozCollisionTime);
	}

	/**
	 * Adds the square takoz collision time list.
	 *
	 * @param squareTakozCollisionTime the square takoz collision time
	 */
	public static void addSquareTakozCollisionTimeList(Long squareTakozCollisionTime) {
		GameEngineTimeData.squareTakozCollisionTimeList.add(squareTakozCollisionTime);
	}

	/**
	 * Adds the tokat collision time list.
	 *
	 * @param tokatCollisionTime the tokat collision time
	 */
	public static void addTokatCollisionTimeList(Long tokatCollisionTime) {
		GameEngineTimeData.tokatCollisionTimeList.add(tokatCollisionTime);
	}

	/**
	 * Adds the cezerye collision time list.
	 *
	 * @param cezeryeCollisionTime the cezerye collision time
	 */
	public static void addCezeryeCollisionTimeList(Long cezeryeCollisionTime) {
		GameEngineTimeData.cezeryeCollisionTimeList.add(cezeryeCollisionTime);
	}

	/**
	 * Adds the firildak collision time list.
	 *
	 * @param firildakCollisionTime the firildak collision time
	 */
	public static void addFirildakCollisionTimeList(Long firildakCollisionTime) {
		GameEngineTimeData.firildakCollisionTimeList.add(firildakCollisionTime);
	}
	
	/**
	 * Gets the cezmi 1 collision time.
	 *
	 * @return the cezmi 1 collision time
	 */
	public static long getCezmi1CollisionTime() {
		return cezmi1CollisionTime;
	}

	/**
	 * Gets the cezmi 2 collision time.
	 *
	 * @return the cezmi 2 collision time
	 */
	public static long getCezmi2CollisionTime() {
		return cezmi2CollisionTime;
	}

	/**
	 * Gets the wall collision time.
	 *
	 * @return the wall collision time
	 */
	public static long getWallCollisionTime() {
		return wallCollisionTime;
	}

	/**
	 * Gets the engel collision time.
	 *
	 * @return the engel collision time
	 */
	public static long getEngelCollisionTime() {
		return engelCollisionTime;
	}

	/**
	 * Gets the triangluar takoz collision time list.
	 *
	 * @return the triangluar takoz collision time list
	 */
	public static ArrayList<Long> getTriangluarTakozCollisionTimeList() {
		return triangluarTakozCollisionTimeList;
	}

	/**
	 * Gets the square takoz collision time list.
	 *
	 * @return the square takoz collision time list
	 */
	public static ArrayList<Long> getSquareTakozCollisionTimeList() {
		return squareTakozCollisionTimeList;
	}

	/**
	 * Gets the tokat collision time list.
	 *
	 * @return the tokat collision time list
	 */
	public static ArrayList<Long> getTokatCollisionTimeList() {
		return tokatCollisionTimeList;
	}

	/**
	 * Gets the cezerye collision time list.
	 *
	 * @return the cezerye collision time list
	 */
	public static ArrayList<Long> getCezeryeCollisionTimeList() {
		return cezeryeCollisionTimeList;
	}

	/**
	 * Gets the firildak collision time list.
	 *
	 * @return the firildak collision time list
	 */
	public static ArrayList<Long> getFirildakCollisionTimeList() {
		return firildakCollisionTimeList;
	}
}