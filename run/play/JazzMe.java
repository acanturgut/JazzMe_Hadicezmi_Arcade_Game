package run.play;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import ui.frame.FrameIntro;

/********************************************************************************************************
*   # Jazz Me - Game Information - COMP 302 - Fall 2016 - Koc University								*
*   																									*
*	#�This game was designed and implemented under the Jazz Me game developers. 				    	*
* 	# This project uses physics packet under copyright of the MIT. 										*			
*	# This project requires Java-SWING libraries. 														*
*	# Recommended Java version: "1.8.0_111"																*
*																										*
* 	# Jazz Me - Developer Members																		*
*																										*
* 	+ Ahmet Can Turgut - aturgut@ku.edu.tr																*
* 	+ Damla Ovek - dovek@ku.edu.tr																		*
* 	+ Riza Arda Kirmizioglu - rkirmizioglu@ku.edu.tr													*
* 	+ Safak Tufekci - stufekci14@ku.edu.tr																*
*																										*
*   + Instructor               : Attila Gursoy, Ph.D													*
*	+ Group Teaching Assistant : Ozan Can Altiok														*
*																									    *
*	!�Check the "ReadMe.txt" file and read instructions.												*
*	! Check the "Documentation.pdf" file for implementation details and software design of the game.  	*
*																										*
*********************************************************************************************************/

/****************************************************************************
* 	# Packet Description 													*
*  																			*
* 	# UI     : Graphical User Interface Related Objects						*
* 	# Domain : GameEngine and Physics Related Objects(MIT)					*
* 	# Asset  : Audio Files, Images, Fonts									*
* 	# Play   : Main Class Related Objects    								*
* 																			*
 ****************************************************************************/

/****************************************************************************
* 	# Warning 																*	 
* 																			*
*   This project was protected under the Koc University plagiarism policy.  *
*   This project is open source project after this given date 15.01.2017.   *
*  																		    *
****************************************************************************/

public class JazzMe {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws ParserConfigurationException the parser configuration exception
	 * @throws SAXException the SAX exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException{
		FrameIntro.getInstance().startGame();
	}
}  