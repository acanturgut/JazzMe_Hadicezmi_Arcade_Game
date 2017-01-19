package domain.xmloperations;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

// TODO: Auto-generated Javadoc
/**
 * The Class XMLWriteSettings.
 */
public class XMLWriteSettings {

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
	
	/** The player 1 element. */
	private Element player1element;
	
	/** The player 2 element. */
	private Element player2element;
	
	/** The screen element. */
	private Element screenElement;
	
	/** The doc. */
	private Document doc;

	/**
	 * Instantiates a new XML write settings.
	 *
	 * @throws ParserConfigurationException the parser configuration exception
	 * @throws SAXException the SAX exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws TransformerException the transformer exception
	 */
	public XMLWriteSettings() throws ParserConfigurationException, SAXException, IOException, TransformerException {

		File settingsFile = new File("gamedata/XML/settings.xml");

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		doc = dBuilder.parse(settingsFile);

		doc.getDocumentElement().normalize();

		NodeList scr = doc.getElementsByTagName("screen");
		Node screen = scr.item(0);
		screenElement = (Element) screen;	

		NodeList plyr1 = doc.getElementsByTagName("player1");
		Node player1 = plyr1.item(0);
		player1element = (Element) player1;
		
		NodeList plyr2 = doc.getElementsByTagName("player2");
		Node player2 = plyr2.item(0);
		player2element = (Element) player2;	
			
	}

	/**
	 * Sets the size.
	 *
	 * @param size the new size
	 */
	public void setSize(String size) {
		screenElement.setAttribute("selected", size);
	}
	
	/**
	 * Sets the player 1 left.
	 *
	 * @param player1Left the new player 1 left
	 */
	public void setPlayer1Left(String player1Left) {
		player1element.setAttribute("leftKey" , player1Left);
	}

	/**
	 * Sets the player 1 right.
	 *
	 * @param player1Right the new player 1 right
	 */
	public void setPlayer1Right(String player1Right) {
		player1element.setAttribute("rightKey" , player1Right);
	}

	/**
	 * Sets the player 1 tokat.
	 *
	 * @param player1Tokat the new player 1 tokat
	 */
	public void setPlayer1Tokat(String player1Tokat) {
		player1element.setAttribute("tokatKey" , player1Tokat);
	}

	/**
	 * Sets the player 2 left.
	 *
	 * @param player2Left the new player 2 left
	 */
	public void setPlayer2Left(String player2Left) {
		player2element.setAttribute("leftKey" , player2Left);
	}

	/**
	 * Sets the player 2 right.
	 *
	 * @param player2Right the new player 2 right
	 */
	public void setPlayer2Right(String player2Right) {
		player2element.setAttribute("rightKey" , player2Right);
	}

	/**
	 * Sets the player 2 tokat.
	 *
	 * @param player2Tokat the new player 2 tokat
	 */
	public void setPlayer2Tokat(String player2Tokat) {
		player2element.setAttribute("tokatKey" , player2Tokat);
	}
 
	/**
	 * Xml FKR.
	 *
	 * @throws TransformerException the transformer exception
	 */
	public void xmlFKR() throws TransformerException{
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("gamedata/XML/settings.xml"));
		transformer.transform(source, result);
	}
}
