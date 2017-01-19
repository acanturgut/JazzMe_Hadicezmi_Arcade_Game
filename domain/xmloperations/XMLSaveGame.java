package domain.xmloperations;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import domain.gameengine.GameEngineData;
import domain.item.EngineBall;
import domain.item.EngineFirildak;
import domain.item.EngineSquareTakoz;
import domain.item.EngineTokat;
import domain.item.EngineTriangularTakoz;

// TODO: Auto-generated Javadoc
/**
 * The Class XMLSaveGame.
 */
public class XMLSaveGame {

	/**
	 * Instantiates a new XML save game.
	 *
	 * @param name the name
	 * @param windowSize the window size
	 * @throws ParserConfigurationException the parser configuration exception
	 * @throws TransformerException the transformer exception
	 */
	public XMLSaveGame(String name, int windowSize) throws ParserConfigurationException, TransformerException {

		windowSize /= 25;
		String val = "";

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.newDocument();

		Element board = doc.createElement("board");
		doc.appendChild(board);

		Element mode = doc.createElement("mode");
		
		Attr nm = doc.createAttribute("name");
		nm.setValue("game");
		mode.setAttributeNode(nm);
		
		Attr lvl = doc.createAttribute("level");
		String lv = "";
		lv += GameEngineData.getLevel();
		lvl.setValue(lv);
		mode.setAttributeNode(lvl);
		
		board.appendChild(mode);

		Element rootElement = doc.createElement("gizmo");
		board.appendChild(rootElement);

		for (EngineSquareTakoz sqtk : GameEngineData.getSquareTakozList()) {
			Element squareTakoz = doc.createElement("squareTakoz");

			Attr x = doc.createAttribute("x");
			val += sqtk.getX() / windowSize;
			x.setValue(val);
			squareTakoz.setAttributeNode(x);
			val = "";

			Attr y = doc.createAttribute("y");
			val += sqtk.getY() / windowSize;
			y.setValue(val);
			squareTakoz.setAttributeNode(y);
			val = "";

			Attr p = doc.createAttribute("player");
			val += sqtk.getPlayer();
			p.setValue(val);
			squareTakoz.setAttributeNode(p);
			val = "";

			rootElement.appendChild(squareTakoz);

		}

		for (EngineTriangularTakoz trtk : GameEngineData.getTriangularTakozList()) {
			Element triangularTakoz = doc.createElement("triangularTakoz");

			Attr x = doc.createAttribute("x");
			val += trtk.getX() / windowSize;
			x.setValue(val);
			triangularTakoz.setAttributeNode(x);
			val = "";

			Attr y = doc.createAttribute("y");
			val += trtk.getY() / windowSize;
			y.setValue(val);
			triangularTakoz.setAttributeNode(y);
			val = "";

			Attr p = doc.createAttribute("player");
			val += trtk.getPlayer();
			p.setValue(val);
			triangularTakoz.setAttributeNode(p);
			val = "";

			Attr s = doc.createAttribute("state");
			val += trtk.getState();
			s.setValue(val);
			triangularTakoz.setAttributeNode(s);
			val = "";

			rootElement.appendChild(triangularTakoz);
		}

		for (EngineTokat tkt : GameEngineData.getTokatList()) {
			Element tokat = doc.createElement("tokat");

			Attr x = doc.createAttribute("x");
			val += tkt.getX() / windowSize;
			x.setValue(val);
			tokat.setAttributeNode(x);
			val = "";

			Attr y = doc.createAttribute("y");
			val += tkt.getY() / windowSize;
			y.setValue(val);
			tokat.setAttributeNode(y);
			val = "";

			Attr p = doc.createAttribute("player");
			val += tkt.getPlayer();
			p.setValue(val);
			tokat.setAttributeNode(p);
			val = "";

			Attr s = doc.createAttribute("state");
			val += tkt.getState();
			s.setValue(val);
			tokat.setAttributeNode(s);
			val = "";
			rootElement.appendChild(tokat);
		}

		for (EngineFirildak frldk : GameEngineData.getFirildakList()) {
			Element firildak = doc.createElement("firildak");

			Attr x = doc.createAttribute("x");
			val += frldk.getX() / windowSize;
			x.setValue(val);
			firildak.setAttributeNode(x);
			val = "";

			Attr y = doc.createAttribute("y");
			val += frldk.getY() / windowSize;
			y.setValue(val);
			firildak.setAttributeNode(y);
			val = "";

			Attr p = doc.createAttribute("player");
			val += frldk.getPlayer();
			p.setValue(val);
			firildak.setAttributeNode(p);
			val = "";

			Attr s = doc.createAttribute("state");
			val += frldk.getState();
			s.setValue(val);
			firildak.setAttributeNode(s);
			val = "";

			Attr angle = doc.createAttribute("angle");
			val += frldk.getRotation();
			angle.setValue(val);
			firildak.setAttributeNode(angle);
			val = "";

			rootElement.appendChild(firildak);

		}

		Element player1 = doc.createElement("player1");

		Attr count1 = doc.createAttribute("count");
		val += GameEngineData.getPlayer1().getGizmoCount();
		count1.setValue(val);
		player1.setAttributeNode(count1);
		val = "";

		board.appendChild(player1);


		Element player2 = doc.createElement("player2");

		Attr count2 = doc.createAttribute("count");
		val += GameEngineData.getPlayer1().getGizmoCount();
		count2.setValue(val);
		player2.setAttributeNode(count2);
		val = "";

		board.appendChild(player2);

		for (EngineBall b : GameEngineData.getBallList()) {
			Element ball = doc.createElement("ball");

			Attr x = doc.createAttribute("x");
			val += b.getX() / windowSize;
			x.setValue(val);
			ball.setAttributeNode(x);
			val = "";

			Attr y = doc.createAttribute("y");
			val += b.getX() / windowSize;
			y.setValue(val);
			ball.setAttributeNode(y);
			val = "";

			Attr vx = doc.createAttribute("vx");
			val += b.getVelocityVector().x();
			vx.setValue(val);
			ball.setAttributeNode(vx);
			val = "";

			Attr vy = doc.createAttribute("vy");
			val += b.getVelocityVector().y();
			vy.setValue(val);
			ball.setAttributeNode(vy);
			val = "";
			
			Attr r = doc.createAttribute("r");
			val += b.getR();
			r.setValue(val);
			ball.setAttributeNode(r);
			val = "";

			board.appendChild(ball);
		}


		Element cezmi1 = doc.createElement("cezmi1");

		Attr x1 = doc.createAttribute("x");
		val += GameEngineData.getCezmi1().getX() / windowSize;
		x1.setValue(val);
		cezmi1.setAttributeNode(x1);
		val = "";

		Attr y1 = doc.createAttribute("y");
		val += GameEngineData.getCezmi1().getY() / windowSize;
		y1.setValue(val);
		cezmi1.setAttributeNode(y1);
		val = "";
		
		board.appendChild(cezmi1);
		
		Element cezmi2 = doc.createElement("cezmi2");

		Attr x2 = doc.createAttribute("x");
		val += GameEngineData.getCezmi2().getX() / windowSize;
		x2.setValue(val);
		cezmi2.setAttributeNode(x2);
		val = "";

		Attr y2 = doc.createAttribute("y");
		val += GameEngineData.getCezmi2().getY() / windowSize;
		y2.setValue(val);
		cezmi2.setAttributeNode(y2);
		val = "";
		
		board.appendChild(cezmi2);

		Element score = doc.createElement("score");
		
		Attr s1 = doc.createAttribute("s1");
		val += GameEngineData.getPlayer1().getScore();
		s1.setValue(val);
		score.setAttributeNode(s1);
		val = "";
		
		Attr s2 = doc.createAttribute("s2");
		val += GameEngineData.getPlayer2().getScore();
		s2.setValue(val);
		score.setAttributeNode(s2);
		val = "";
		
		board.appendChild(score);

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		String s = "gamedata/userdata/";
		s += name;
		s += ".xml";
		StreamResult result = new StreamResult(new File(s));
		transformer.transform(source, result);

	}
}
