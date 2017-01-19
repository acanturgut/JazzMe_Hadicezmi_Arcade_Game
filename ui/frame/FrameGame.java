package ui.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.DataLine.Info;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import domain.gameengine.GameEngineData;
import ui.data.UIData;
import domain.xmloperations.XMLReadSettings;

/**
 * The Class FrameGame.
 */
public class FrameGame extends JFrame {

	/** The window size. */
	private int windowSize = 500;

	/** The game panel. */
	private PanelGame gamePanel;

	/** The xml reader. */
	private XMLReadSettings xmlReader;

	/** The game window panel. */
	private JPanel gameWindowPanel;

	/** The main menu frame. */
	private JFrame mainMenuFrame;

	/** The preferred size. */
	private Dimension preferredSize;

	/** The upper layer clip. */
	private Clip upperLayerClip;

	/** The selected item. */
	private String selectedItem = "NONE";

	/** The audio clip. */
	private Clip audioClip;

	/** The level. */
	private int level;

	/** The muted. */
	private Boolean muted = true;

	/** The player 1 score board. */
	JLabel player1ScoreBoard; 

	/** The player 2 score board. */
	JLabel player2ScoreBoard;

	private boolean aiMode;

	/**
	 * Instantiates a new frame game.
	 *
	 * @param mainMenuFrame the main menu frame
	 * @param clip the clip
	 * @param level the level
	 */
	public FrameGame(JFrame mainMenuFrame, Clip clip, int level, boolean aiMode){

		super("Edit Frame");

		this.aiMode = aiMode;
		this.mainMenuFrame = mainMenuFrame;
		this.upperLayerClip = clip;
		this.level = level;

		ImageIcon img = new ImageIcon("asset/images/icon.png");

		this.setIconImage(img.getImage());
		try {
			xmlReader = new XMLReadSettings();
		} catch (ParserConfigurationException | SAXException | IOException e1) {

			e1.printStackTrace();
		}

		String size = xmlReader.getSize();

		if(size.equals("small")){
			windowSize = 500;

		}else if(size.equals("medium")){
			windowSize = 700;
		}else{
			windowSize = 900;
		}

		AudioInputStream audioStream = null;

		try {

			File audioSound = null;

			if(level == 1){
				audioSound = new File("asset/audio/caravan.wav");
			}else if(level == 2){
				audioSound = new File("asset/audio/playMusic.wav");
			}else if(level == 3){
				audioSound = new File("asset/audio/playMusic3.wav");
			}else if(level == 4){
				audioSound = new File("asset/audio/playMusic.wav");
			}

			audioStream = AudioSystem.getAudioInputStream(audioSound);
			AudioFormat deleteFormat = audioStream.getFormat();
			Info deleteInfo = new DataLine.Info(Clip.class, deleteFormat);
			audioClip = (Clip) AudioSystem.getLine(deleteInfo);
			audioClip.open(audioStream);
			audioClip.start();

		}catch(Exception e1){}

		upperLayerClip = clip;
		gameWindowPanel = new JPanel();
		gameWindowPanel.setSize(windowSize, windowSize);
		gameWindowPanel.setBackground(Color.BLACK);
		gameWindowPanel.setLayout(new BorderLayout());
		preferredSize = new Dimension(500,30);
		this.setSize(windowSize, windowSize+60);
		this.setBackground(Color.BLACK);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.setUndecorated(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		try {
			addButtonOp();
		} catch (FontFormatException | IOException e) {

			e.printStackTrace();
		}

		add(gameWindowPanel, BorderLayout.CENTER);
	}

	/**
	 * Adds the button op.
	 *
	 * @throws FontFormatException the font format exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void addButtonOp() throws FontFormatException, IOException{

		Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("asset/fonts/mainFont4.ttf"));
		customFont = customFont.deriveFont(10f);

		JPanel buttonsPanel = new JPanel();
		GridLayout buttonsLayout = new GridLayout(0,4);
		buttonsLayout.setVgap(0);
		buttonsPanel.setLayout(buttonsLayout);
		buttonsPanel.setBackground(Color.BLACK);

		JPanel gizmoPanel = new JPanel();
		GridLayout gizmoLayout = new GridLayout(0,3);
		gizmoLayout.setVgap(0);
		gizmoPanel.setLayout(gizmoLayout);
		gizmoPanel.setBackground(Color.BLACK);

		JLabel exitButton = new JLabel("Exit");
		buttonDesigner(exitButton);

		JLabel muteButton = new JLabel("Mute");
		buttonDesigner(muteButton);

		JLabel mainMenuButton = new JLabel("Main Menu");
		buttonDesigner(mainMenuButton);

		JLabel saveButton = new JLabel("Save");
		buttonDesigner(saveButton);

		buttonsPanel.add(mainMenuButton);
		buttonsPanel.add(muteButton);
		buttonsPanel.add(saveButton);
		buttonsPanel.add(exitButton);

		player1ScoreBoard = new JLabel("Player 1 Score: 0");
		buttonDesigner(player1ScoreBoard);

		player2ScoreBoard = new JLabel("Player 2 Score: 0");
		buttonDesigner(player2ScoreBoard);

		JLabel LevelLabel;

		if(level == 1){
			LevelLabel = new JLabel("Level 1");
			buttonDesigner(LevelLabel);

		}else{
			LevelLabel = new JLabel("Level 2");
			buttonDesigner(LevelLabel);
		}

		gizmoPanel.add(player1ScoreBoard);
		gizmoPanel.add(LevelLabel);
		gizmoPanel.add(player2ScoreBoard);

		gamePanel = new PanelGame(level,windowSize, this, aiMode);
		gameWindowPanel.add(gamePanel, BorderLayout.CENTER);
		gameWindowPanel.add(buttonsPanel, BorderLayout.NORTH);
		gameWindowPanel.add(gizmoPanel, BorderLayout.SOUTH);

		mainMenuButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if(!gamePanel.isPauseState()){

					player1ScoreBoard.setText("Player 1 Score: " + 0);
					player2ScoreBoard.setText("Player 2 Score: " + 0);

					GameEngineData.getPlayer1().setScore(0);
					GameEngineData.getPlayer2().setScore(0);
					gamePanel.setStartTime(0);
					gamePanel.setGameTime(0);
					gamePanel.externalTaskStopper();
					audioClip.stop();
					GameEngineData.clearAll();
					UIData.clearAll();
					setVisible(false);
					mainMenuFrame.setVisible(true);
					removeAll();
					dispose();

					try{
						upperLayerClip.start();
					}catch(Exception e3){}

					GameEngineData.clearAll();
				}
			}

			public void mouseEntered(MouseEvent e) {
				mainMenuButton.setForeground(Color.RED);

			}

			public void mouseExited(MouseEvent e) {
				mainMenuButton.setForeground(Color.WHITE);
			}
		});

		exitButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);		

			}

			public void mouseEntered(MouseEvent e) {
				exitButton.setForeground(Color.RED);

			}

			public void mouseExited(MouseEvent e) {
				exitButton.setForeground(Color.WHITE);
			}

		});

		muteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if(muted){

					muteButton.setText("Unmute");
					audioClip.stop();
					muted = false;

				}else{

					muteButton.setText("mute");
					audioClip.start();
					muted = true;
				}
			}

			public void mouseEntered(MouseEvent e) {
				muteButton.setForeground(Color.RED);
			}

			public void mouseExited(MouseEvent e) {
				muteButton.setForeground(Color.WHITE);
			}
		});

		saveButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				if(!gamePanel.isPauseState()){
					try {

						FrameSave sf = new FrameSave(2);

						sf.setVisible(true);
					} catch (FontFormatException | IOException e1) {

						e1.printStackTrace();
					}
				}
			}

			public void mouseEntered(MouseEvent e) {
				saveButton.setForeground(Color.RED);
			}

			public void mouseExited(MouseEvent e) {
				saveButton.setForeground(Color.WHITE);
			}
		});
	}

	/**
	 * Button designer.
	 *
	 * @param label the label
	 * @throws FontFormatException the font format exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void buttonDesigner(JLabel label) throws FontFormatException, IOException{

		Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("asset/fonts/mainFont4.ttf"));
		customFont = customFont.deriveFont(15f);
		label.setFont(customFont);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setForeground(Color.WHITE);
		label.setPreferredSize(preferredSize);
		label.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	}

	/**
	 * Gets the selected item.
	 *
	 * @return the selected item
	 */
	public String getSelectedItem() {
		return selectedItem;
	}

	/**
	 * Sets the selected item.
	 *
	 * @param selectedItem the new selected item
	 */
	public void setSelectedItem(String selectedItem) {
		this.selectedItem = selectedItem;
	}

	/**
	 * Gets the player 1 score board.
	 *
	 * @return the player 1 score board
	 */
	public JLabel getPlayer1ScoreBoard() {
		return player1ScoreBoard;
	}

	/**
	 * Sets the player 1 score board.
	 *
	 * @param player1ScoreBoard the new player 1 score board
	 */
	public void setPlayer1ScoreBoard(JLabel player1ScoreBoard) {
		this.player1ScoreBoard = player1ScoreBoard;
	}

	/**
	 * Gets the player 2 score board.
	 *
	 * @return the player 2 score board
	 */
	public JLabel getPlayer2ScoreBoard() {
		return player2ScoreBoard;
	}

	/**
	 * Sets the player 2 score board.
	 *
	 * @param player2ScoreBoard the new player 2 score board
	 */
	public void setPlayer2ScoreBoard(JLabel player2ScoreBoard) {
		this.player2ScoreBoard = player2ScoreBoard;
	}

	/**
	 * Gets the audio clip.
	 *
	 * @return the audio clip
	 */
	public Clip getAudioClip() {
		return audioClip;
	}

	/**
	 * Sets the audio clip.
	 *
	 * @param audioClip the new audio clip
	 */
	public void setAudioClip(Clip audioClip) {
		this.audioClip = audioClip;
	}
}
