package domain.item;

import java.awt.Rectangle;

/**
 * The Class EngineGizmo.
 */
public class EngineGizmo {
	
	/** The x. */
	protected int x;
	
	/** The y. */
	protected int y;
	
	/** The l. */
	protected int L;
	
	/** The type. */
	protected String type;
	
	/** The state. */
	protected int state;
	
	/** The player. */
	protected int player;
	
	/**
	 * Instantiates a new engine gizmo.
	 *
	 * @param x the x
	 * @param y the y
	 * @param L the l
	 * @param player the player
	 */
	public EngineGizmo(int x, int y, int L, int player){
		this.x=x;
		this.y=y;
		this.L=L;	
		this.player = player;	
	}
	
	/**
	 * Instantiates a new engine gizmo.
	 *
	 * @param x the x
	 * @param y the y
	 * @param L the l
	 * @param state the state
	 * @param player the player
	 */
	public EngineGizmo(int x, int y, int L,  int state, int player){
		this.x=x;
		this.y=y;
		this.L=L;	
		this.state = state;
		this.player = player;	
	}
	
	/**
	 * Bounding box.
	 *
	 * @return the rectangle
	 */
	public Rectangle boundingBox() {
		return new Rectangle(x, y, L, L);
	}
		
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets the x.
	 *
	 * @param x the new x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the y.
	 *
	 * @param y the new y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Gets the l.
	 *
	 * @return the l
	 */
	public int getL() {
		return L;
	}

	/**
	 * Sets the l.
	 *
	 * @param l the new l
	 */
	public void setL(int l) {
		L = l;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public int getState() {
		return state;
	}

	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(int state) {
		this.state = state;
	}

	/**
	 * Gets the player.
	 *
	 * @return the player
	 */
	public int getPlayer() {
		return player;
	}

	/**
	 * Sets the player.
	 *
	 * @param player the new player
	 */
	public void setPlayer(int player) {
		this.player = player;
	}
}
