package ui.data;

import java.util.ArrayList;

import domain.gameengine.GameEngineData;
import ui.item.UICezerye;
import ui.item.UICezmi;
import ui.item.UIFirildak;
import ui.item.UITokat;

/**
 * The Class UIData.
 */
public class UIData {

	/** The instance. */
	private static UIData instance = new UIData();
	
	/** The tokat UI list. */
	private static ArrayList<UITokat> tokatUIList = new ArrayList<UITokat>();
	
	/** The firildak UI list. */
	private static ArrayList<UIFirildak> firildakUIList = new ArrayList<UIFirildak>();
	
	/** The cezerye UI list. */
	private static ArrayList<UICezerye> cezeryeUIList = new ArrayList<UICezerye>();
	
	/**
	 * Instantiates a new UI data.
	 */
	private UIData(){}
	
	/**
	 * Gets the single instance of UIData.
	 *
	 * @return single instance of UIData
	 */
	public static UIData getInstance(){
		return instance;
	}

	/**
	 * Gets the tokat UI list.
	 *
	 * @return the tokat UI list
	 */
	public static ArrayList<UITokat> getTokatUIList() {
		return tokatUIList;
	}

	/**
	 * Adds the UI tokat list.
	 *
	 * @param tokat the tokat
	 */
	public static void addUITokatList(UITokat tokat) {
		tokatUIList.add(tokat);
	}

	/**
	 * Gets the firildak UI list.
	 *
	 * @return the firildak UI list
	 */
	public static ArrayList<UIFirildak> getFirildakUIList() {
		return firildakUIList;
	}

	/**
	 * Adds the UI firildak list.
	 *
	 * @param firildak the firildak
	 */
	public static void addUIFirildakList(UIFirildak firildak) {
		firildakUIList.add(firildak);
	}

	/**
	 * Gets the cezerye UI list.
	 *
	 * @return the cezerye UI list
	 */
	public static ArrayList<UICezerye> getCezeryeUIList() {
		return cezeryeUIList;
	}

	/**
	 * Sets the cezerye UI list.
	 *
	 * @param cezerye the new cezerye UI list
	 */
	public static void setCezeryeUIList(UICezerye cezerye) {
		cezeryeUIList.add(cezerye);
	}

	public static void clearAll() {	
		getFirildakUIList().clear();
		getCezeryeUIList().clear();
		getTokatUIList().clear();
	}
}