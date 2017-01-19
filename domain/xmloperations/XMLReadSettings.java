package domain.xmloperations;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

// TODO: Auto-generated Javadoc
/**
 * The Class XMLReadSettings.
 */
public class XMLReadSettings {
	
	/** The size. */
	private String size;
	
	/** The player 1 left. */
	private String player1Left;
	
	/** The player 1 right. */
	private String player1Right;
	
	/** The player 1 tokat. */
	private String player1Tokat;
	
	/** The player 2 left. */
	private String player2Left;
	
	/** The player 2 right. */
	private String player2Right;
	
	/** The player 2 tokat. */
	private String player2Tokat;
	
	/**
	 * Instantiates a new XML read settings.
	 *
	 * @throws ParserConfigurationException the parser configuration exception
	 * @throws SAXException the SAX exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public XMLReadSettings() throws ParserConfigurationException, SAXException, IOException {
		
		File settingsFile = new File("gamedata/XML/settings.xml");
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(settingsFile);
		
		doc.getDocumentElement().normalize();
		
		NodeList scr = doc.getElementsByTagName("screen");
		Node screen = scr.item(0);
		Element screenElement = (Element) screen;	
		this.size = screenElement.getAttribute("selected");

		NodeList plyr1 = doc.getElementsByTagName("player1");
		Node player1 = plyr1.item(0);
		Element player1element = (Element) player1;
		this.player1Left = player1element.getAttribute("leftKey");
		this.player1Right = player1element.getAttribute("rightKey");
		this.player1Tokat = player1element.getAttribute("tokatKey");
		
		NodeList plyr2 = doc.getElementsByTagName("player2");
		Node player2 = plyr2.item(0);
		Element player2element = (Element) player2;	
		this.player2Left = player2element.getAttribute("leftKey");
		this.player2Right = player2element.getAttribute("rightKey");
		this.player2Tokat = player2element.getAttribute("tokatKey");
		
	}

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public String getSize() {
		return size;
	}

	/**
	 * Sets the size.
	 *
	 * @param size the new size
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/**
	 * Gets the player 1 left.
	 *
	 * @return the player 1 left
	 */
	public String getPlayer1Left() {
		return player1Left;
	}

	/**
	 * Sets the player 1 left.
	 *
	 * @param player1Left the new player 1 left
	 */
	public void setPlayer1Left(String player1Left) {
		this.player1Left = player1Left;
	}

	/**
	 * Gets the player 1 right.
	 *
	 * @return the player 1 right
	 */
	public String getPlayer1Right() {
		return player1Right;
	}

	/**
	 * Sets the player 1 right.
	 *
	 * @param player1Right the new player 1 right
	 */
	public void setPlayer1Right(String player1Right) {
		this.player1Right = player1Right;
	}

	/**
	 * Gets the player 1 tokat.
	 *
	 * @return the player 1 tokat
	 */
	public String getPlayer1Tokat() {
		return player1Tokat;
	}

	/**
	 * Sets the player 1 tokat.
	 *
	 * @param player1Tokat the new player 1 tokat
	 */
	public void setPlayer1Tokat(String player1Tokat) {
		this.player1Tokat = player1Tokat;
	}

	/**
	 * Gets the player 2 left.
	 *
	 * @return the player 2 left
	 */
	public String getPlayer2Left() {
		return player2Left;
	}

	/**
	 * Sets the player 2 left.
	 *
	 * @param player2Left the new player 2 left
	 */
	public void setPlayer2Left(String player2Left) {
		this.player2Left = player2Left;
	}

	/**
	 * Gets the player 2 right.
	 *
	 * @return the player 2 right
	 */
	public String getPlayer2Right() {
		return player2Right;
	}

	/**
	 * Sets the player 2 right.
	 *
	 * @param player2Right the new player 2 right
	 */
	public void setPlayer2Right(String player2Right) {
		this.player2Right = player2Right;
	}

	/**
	 * Gets the player 2 tokat.
	 *
	 * @return the player 2 tokat
	 */
	public String getPlayer2Tokat() {
		return player2Tokat;
	}

	/**
	 * Sets the player 2 tokat.
	 *
	 * @param player2Tokat the new player 2 tokat
	 */
	public void setPlayer2Tokat(String player2Tokat) {
		this.player2Tokat = player2Tokat;
	}
	
	
}
