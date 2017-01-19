package domain.playerdata;

/**
 * The Class ADataPlayer.
 */
public abstract class ADataPlayer {
	
	/** The score. */
	private double score = 0;
	
	/** The name. */
	private String name;
	
	/** The is win. */
	private boolean isWin = false;
	
	/** The gizmo count. */
	private int gizmoCount = 4;
	
	/**
	 * Instantiates a new a data player.
	 */
	public ADataPlayer(){}

	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	public double getScore() {
		return score;
	}

	/**
	 * Sets the score.
	 *
	 * @param score the new score
	 */
	public void setScore(double score) {
		this.score = score;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Checks if is win.
	 *
	 * @return true, if is win
	 */
	public boolean isWin() {
		return isWin;
	}

	/**
	 * Sets the win.
	 *
	 * @param isWin the new win
	 */
	public void setWin(boolean isWin) {
		this.isWin = isWin;
	}

	/**
	 * Gets the gizmo count.
	 *
	 * @return the gizmo count
	 */
	public int getGizmoCount() {
		return gizmoCount;
	}

	/**
	 * Sets the gizmo count.
	 *
	 * @param gizmoCount the new gizmo count
	 */
	public void setGizmoCount(int gizmoCount) {
		this.gizmoCount = gizmoCount;
	}
}
