package domain.gameengine;

import java.awt.Color;
import domain.item.EngineFirildak;
import domain.item.EngineGizmo;
import domain.item.EngineSquareTakoz;
import domain.item.EngineTokat;
import domain.item.EngineTriangularTakoz;

/**
 * A factory for creating GameEngineGizmo objects.
 */
public class GameEngineGizmoFactory {

	/**
	 * Gets the gizmo.
	 *
	 * @param type the type
	 * @param x the x
	 * @param y the y
	 * @param L the l
	 * @param color the color
	 * @param player the player
	 * @return the gizmo
	 */
	public static EngineGizmo getGizmo(String type, int x, int y, int L, Color color, int player) {
		if(type == null) {
			return null;
		}
		
		if(type.equalsIgnoreCase("SQUARETAKOZ")) {
			return new EngineSquareTakoz(x, y, L, player);

		} else if(type.equalsIgnoreCase("FIRILDAK")) {
			return new EngineFirildak(x, y, L, player);
		}	
		
		return null;		
	}

	/**
	 * Gets the gizmo.
	 *
	 * @param type the type
	 * @param x the x
	 * @param y the y
	 * @param L the l
	 * @param color the color
	 * @param state the state
	 * @param player the player
	 * @return the gizmo
	 */
	public static EngineGizmo getGizmo(String type, int x, int y, int L, Color color, int state, int player) {

		if(type == null) {
			return null;
		}
		
		if(type.equalsIgnoreCase("TRIANGULARTAKOZ")) {
			return new EngineTriangularTakoz(x, y, L, state, player);
			
		} else if(type.equalsIgnoreCase("TOKAT")) {
			return new EngineTokat(x, y, L, state, player);
		}
		
		return null;
	}
}
