package domain.gameengine;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.KeyStroke;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import domain.xmloperations.XMLReadSettings;
import ui.data.UIData;
import ui.frame.PanelGame;

/**
 * The Class GameEngineKeyHandler.
 */
public class GameEngineKeyHandler extends KeyAdapter{

	/** The is game paused. */
	private Boolean isGamePaused = true;

	/**
	 * Instantiates a new game engine key handler.
	 *
	 * @param panel the panel
	 * @param inputMap the input map
	 * @param actionMap the action map
	 */
	public GameEngineKeyHandler(PanelGame panel, InputMap inputMap, ActionMap actionMap) {

		XMLReadSettings xmlReaderForKeys = null;
		try {
			xmlReaderForKeys = new XMLReadSettings();

		}catch(Exception e){}

		int player1MoveLeft = Integer.parseInt(xmlReaderForKeys.getPlayer1Left());
		int player2MoveLeft = Integer.parseInt(xmlReaderForKeys.getPlayer2Left());

		int player1MoveRight = Integer.parseInt(xmlReaderForKeys.getPlayer1Right());
		int player2MoveRight = Integer.parseInt(xmlReaderForKeys.getPlayer2Right());

		int player1TokatTriger = Integer.parseInt(xmlReaderForKeys.getPlayer1Tokat());
		int player2TokatTriger = Integer.parseInt(xmlReaderForKeys.getPlayer2Tokat());

		inputMap.put(KeyStroke.getKeyStroke(player1MoveRight, 0, false), "pressed");
		inputMap.put(KeyStroke.getKeyStroke(player1MoveRight, 0, true), "released");

		actionMap.put("pressed", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameEngineData.setMoveCezmi1Right(true);
			}
		});

		actionMap.put("released", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameEngineData.setMoveCezmi1Right(false);
			}
		});

		inputMap.put(KeyStroke.getKeyStroke(player1MoveLeft, 0, false), "pressed1");
		inputMap.put(KeyStroke.getKeyStroke(player1MoveLeft, 0, true), "released1");

		actionMap.put("pressed1", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameEngineData.setMoveCezmi1Left(true);
			}
		});

		actionMap.put("released1", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameEngineData.setMoveCezmi1Left(false);
			}
		});

		inputMap.put(KeyStroke.getKeyStroke(player2MoveRight, 0, false), "pressed2");
		inputMap.put(KeyStroke.getKeyStroke(player2MoveRight, 0, true), "released2");

		actionMap.put("pressed2", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameEngineData.setMoveCezmi2Right(true);
			}
		});

		actionMap.put("released2", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameEngineData.setMoveCezmi2Right(false);
			}
		});

		inputMap.put(KeyStroke.getKeyStroke(player2MoveLeft, 0, false), "pressed3");
		inputMap.put(KeyStroke.getKeyStroke(player2MoveLeft, 0, true), "released3");

		actionMap.put("pressed3", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameEngineData.setMoveCezmi2Left(true);
			}
		});

		actionMap.put("released3", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameEngineData.setMoveCezmi2Left(false);
			}
		});

		inputMap.put(KeyStroke.getKeyStroke(player1TokatTriger, 0, false), "pressed4");
		inputMap.put(KeyStroke.getKeyStroke(player1TokatTriger, 0, true), "released4");

		actionMap.put("pressed4", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < GameEngineData.getTokatList().size(); i++) {
					if(GameEngineData.getTokatList().get(i).getPlayer() == 1){
						GameEngineData.getTokatList().get(i).setInvoke(true);
						UIData.getTokatUIList().get(i).setInvoke(true);
					}
				}
			}
		});

		actionMap.put("released4", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {}
		});

		inputMap.put(KeyStroke.getKeyStroke(player2TokatTriger, 0, false), "pressed5");
		inputMap.put(KeyStroke.getKeyStroke(player2TokatTriger, 0, true), "released5");

		actionMap.put("pressed5", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < GameEngineData.getTokatList().size(); i++) {
					if(GameEngineData.getTokatList().get(i).getPlayer() == 2){
						GameEngineData.getTokatList().get(i).setInvoke(true);
						UIData.getTokatUIList().get(i).setInvoke(true);
					}	
				}
			}
		});

		actionMap.put("released5", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {}
		});

		inputMap.put(KeyStroke.getKeyStroke(80, 0, false), "pressed51");
		inputMap.put(KeyStroke.getKeyStroke(80, 0, true), "released51");

		actionMap.put("pressed51", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(isGamePaused){
					panel.pause();
					isGamePaused = false;
				}else{
					panel.unPause();
					isGamePaused = true;
				}
			}
		});

		actionMap.put("released51", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {}
		});
		
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "pressed511");
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true), "released511");

		actionMap.put("pressed511", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {				
					panel.start();		
			}
		});

		actionMap.put("released511", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {}
		});
	}
}
