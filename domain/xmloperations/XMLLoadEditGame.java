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

import domain.gameengine.GameEngineData;
import domain.item.EngineBall;
import domain.item.EngineBorder;
import domain.item.EngineEngel;
import domain.item.EngineFirildak;
import domain.item.EngineSquareTakoz;
import domain.item.EngineTokat;
import domain.item.EngineTriangularTakoz;
import domain.physics.Vect;

/**
 * The Class XMLLoadEditGame.
 */
public class XMLLoadEditGame {
	
	private int mode;
	private int level;
	private int windowSize;
	private Document doc;
	private Document docSet;
	private String s = "gamedata/userdata/";
	private String settings = "gamedata/XML/settings.xml";
	private int player1Count;
	private int player2Count;
	/**
	 * Instantiates a new XML load edit game.
	 *
	 * @param filename the filename
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 */
	public XMLLoadEditGame(String filename) throws ParserConfigurationException, SAXException, IOException {
		
		s = s + filename + ".xml";
		settings = "gamedata/XML/settings.xml";
		
		File gameFile = new File(s);
		File settingsFile = new File(settings);
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		
		doc = dBuilder.parse(gameFile);
		docSet = dBuilder.parse(settingsFile);
		
		doc.getDocumentElement().normalize();
		docSet.getDocumentElement().normalize();
		
		
		NodeList nList = doc.getElementsByTagName("mode");
		Node mode = nList.item(0);
		Element m = (Element) mode;
		
		NodeList scr = docSet.getElementsByTagName("screen");
		Node screen = scr.item(0);
		Element screenElement = (Element) screen;
		
		if (screenElement.getAttribute("selected").equals("max")) {
			this.windowSize = 900;
		} else if (screenElement.getAttribute("selected").equals("medium")) {
			this.windowSize = 700;
		} else if (screenElement.getAttribute("selected").equals("small")) {
			this.windowSize = 500;
		}
		
		if(m.getAttribute("name").equals("edit")) {
			this.mode = 1;
			
		} else if (m.getAttribute("name").equals("game")) {
			this.mode = 2;
			
		}
		
		if (m.getAttribute("level").equals("1")) {
			this.level = 1;
			GameEngineData.setLevel(level);
		} else if (m.getAttribute("level").equals("2")) {
			this.level = 2;
			GameEngineData.setLevel(level);
		}
		GameEngineData.setBorder(new EngineBorder(windowSize));
		GameEngineData.setEngel(new EngineEngel((windowSize/2) - (windowSize/200), windowSize - (3 * (windowSize / 25)), windowSize/100 , 3*(windowSize/25)));
	}
	
	public int getMode() {
		return mode;
	}
	public int getLevel() {
		return level;
	}

	public void createEditWorld() throws SAXException, IOException, ParserConfigurationException {
		
		
		NodeList sqTakozList = doc.getElementsByTagName("squareTakoz");
		for (int i = 0; i < sqTakozList.getLength(); i++) {
			Node node = sqTakozList.item(i);
			Element e = (Element) node;
			GameEngineData.getSquareTakozList().add(new EngineSquareTakoz(Integer.parseInt(e.getAttribute("x")) * (windowSize / 25), Integer.parseInt(e.getAttribute("y")) * (windowSize / 25), windowSize/25, Integer.parseInt(e.getAttribute("player"))));
		}
		
		NodeList trTakozList = doc.getElementsByTagName("triangularTakoz");
		for (int i = 0; i < trTakozList.getLength(); i++) {
			Element e = (Element) trTakozList.item(i);
			GameEngineData.getTriangularTakozList().add(new EngineTriangularTakoz(Integer.parseInt(e.getAttribute("x")) * (windowSize / 25), Integer.parseInt(e.getAttribute("y")) * (windowSize / 25), windowSize/25, Integer.parseInt(e.getAttribute("state")), Integer.parseInt(e.getAttribute("player"))));
		}
		
		NodeList tokatList = doc.getElementsByTagName("tokat");
		for (int i = 0; i < tokatList.getLength(); i++) {
			Element e = (Element) tokatList.item(i);
			GameEngineData.getTokatList().add(new EngineTokat(Integer.parseInt(e.getAttribute("x")) * (windowSize / 25), Integer.parseInt(e.getAttribute("y")) * (windowSize / 25), windowSize/25, Integer.parseInt(e.getAttribute("state")), Integer.parseInt(e.getAttribute("player"))));
		}
		
		NodeList firildakList = doc.getElementsByTagName("firildak");
		for (int i = 0; i < firildakList.getLength(); i++) {
			Element e = (Element) firildakList.item(i);
			GameEngineData.getFirildakList().add(new EngineFirildak(Integer.parseInt(e.getAttribute("x")) * (windowSize / 25), Integer.parseInt(e.getAttribute("y")) * (windowSize / 25), windowSize/25, Integer.parseInt(e.getAttribute("player"))));
		}
		
		NodeList node1l = doc.getElementsByTagName("player1");
		Element p1 = (Element) node1l.item(0);
		player1Count = Integer.parseInt(p1.getAttribute("count"));
		GameEngineData.getPlayer1().setGizmoCount(player1Count);
		
		NodeList node2l = doc.getElementsByTagName("player2");
		Element p2 = (Element) node2l.item(0);
		player2Count = Integer.parseInt(p2.getAttribute("count"));
		GameEngineData.getPlayer2().setGizmoCount(player2Count);
		
	}
	
	public void createPlayWorld() {
		
		NodeList sqTakozList = doc.getElementsByTagName("squareTakoz");
		for (int i = 0; i < sqTakozList.getLength(); i++) {
			Node node = sqTakozList.item(i);
			Element e = (Element) node;
			GameEngineData.getSquareTakozList().add(new EngineSquareTakoz(Integer.parseInt(e.getAttribute("x")) * (windowSize / 25), Integer.parseInt(e.getAttribute("y")) * (windowSize / 25), windowSize/25, Integer.parseInt(e.getAttribute("player"))));
		}
		
		NodeList trTakozList = doc.getElementsByTagName("triangularTakoz");
		for (int i = 0; i < trTakozList.getLength(); i++) {
			Element e = (Element) trTakozList.item(i);
			GameEngineData.getTriangularTakozList().add(new EngineTriangularTakoz(Integer.parseInt(e.getAttribute("x")) * (windowSize / 25), Integer.parseInt(e.getAttribute("y")) * (windowSize / 25), windowSize/25, Integer.parseInt(e.getAttribute("state")), Integer.parseInt(e.getAttribute("player"))));
		}
		
		NodeList tokatList = doc.getElementsByTagName("tokat");
		for (int i = 0; i < tokatList.getLength(); i++) {
			Element e = (Element) tokatList.item(i);
			GameEngineData.getTokatList().add(new EngineTokat(Integer.parseInt(e.getAttribute("x")) * (windowSize / 25), Integer.parseInt(e.getAttribute("y")) * (windowSize / 25), windowSize/25, Integer.parseInt(e.getAttribute("state")), Integer.parseInt(e.getAttribute("player"))));
		}
		
		NodeList firildakList = doc.getElementsByTagName("firildak");
		for (int i = 0; i < firildakList.getLength(); i++) {
			Element e = (Element) firildakList.item(i);
			GameEngineData.getFirildakList().add(new EngineFirildak(Integer.parseInt(e.getAttribute("x")) * (windowSize / 25), Integer.parseInt(e.getAttribute("y")) * (windowSize / 25), windowSize/25, Integer.parseInt(e.getAttribute("player"))));
		}
		
		NodeList p1l = doc.getElementsByTagName("player1");
		Element p1 = (Element) p1l.item(0);
		player1Count = Integer.parseInt(p1.getAttribute("count"));
		GameEngineData.getPlayer1().setGizmoCount(player1Count);
		
		NodeList p2l = doc.getElementsByTagName("player2");
		Element p2 = (Element) p2l.item(0);
		player1Count = Integer.parseInt(p2.getAttribute("count"));
		GameEngineData.getPlayer2().setGizmoCount(player2Count);
		
		NodeList c1 = doc.getElementsByTagName("cezmi1");
		Element ce1 = (Element) c1.item(0);
		GameEngineData.getCezmi1().setX(Integer.parseInt(ce1.getAttribute("x")) * (windowSize / 25)  + (windowSize / 25));
		GameEngineData.getCezmi1().setY(Integer.parseInt(ce1.getAttribute("y")) * (windowSize / 25));
		GameEngineData.getCezmi1().setL(windowSize);
		
		NodeList c2 = doc.getElementsByTagName("cezmi2");
		Element ce2 = (Element) c2.item(0);
		GameEngineData.getCezmi2().setX(Integer.parseInt(ce2.getAttribute("x")) * (windowSize / 25) + (windowSize / 25));
		GameEngineData.getCezmi2().setY(Integer.parseInt(ce2.getAttribute("y")) * (windowSize / 25));
		GameEngineData.getCezmi2().setL(windowSize);
		
		NodeList ball = doc.getElementsByTagName("ball");
		for (int i = 0; i < ball.getLength(); i++) {
			Element b = (Element) ball.item(i);
			GameEngineData.getBallList().add(new EngineBall(Integer.parseInt(b.getAttribute("x")) * (windowSize/25), Integer.parseInt(b.getAttribute("y")) * (windowSize/25), Integer.parseInt(b.getAttribute("r"))));
			Vect vect = new Vect(Double.parseDouble(b.getAttribute("vx")), Double.parseDouble(b.getAttribute("vy")));
			GameEngineData.getBallList().get(i).setVelocityVector(vect);
		}
		
		NodeList scr = doc.getElementsByTagName("score");
		Element score = (Element) scr.item(0);
		GameEngineData.getPlayer1().setScore(Double.parseDouble(score.getAttribute("s1")));
		GameEngineData.getPlayer2().setScore(Double.parseDouble(score.getAttribute("s2")));
//		Element p1 = (Element) doc.getElementsByTagName("player1");
//		GameEngineData.setPlayer1(player1);
//		
//		Element p2 = (Element) doc.getElementsByTagName("player2");
	}

	public int getPlayer1Count() {
		return player1Count;
	}

	public int getPlayer2Count() {
		return player2Count;
	}
}