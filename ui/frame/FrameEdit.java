package ui.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import domain.gameengine.GameEngineAudioHandler;
import domain.gameengine.GameEngineData;
import domain.xmloperations.XMLSaveGame;
import domain.xmloperations.XMLReadSettings;

/**
 * The Class FrameEdit.
 */
public class FrameEdit extends JFrame implements KeyListener{

	/** The window size. */
	private int windowSize = 500;

	/** The xml reader. */
	private XMLReadSettings xmlReader;
	
	/** The edit mode main panel. */
	private JPanel editModeMainPanel;
	
	/** The main menu frame. */
	private JFrame mainMenuFrame;
	
	/** The preferred size. */
	private Dimension preferredSize;
	
	/** The main menu clip. */
	private Clip mainMenuClip;
	
	/** The selected item. */
	private String selectedItem = "NONE";
	
	/** The delete clip. */
	private Clip deleteClip;

	/** The level. */
	private int level;

	/** The muted. */
	private Boolean muted = true;

	private Boolean aiMode;
	/**
	 * Instantiates a new frame edit.
	 *
	 * @param mainMenuFrame the main menu frame
	 * @param level the level
	 * @param clip the clip
	 */
	public FrameEdit(JFrame mainMenuFrame, int level ,Clip clip, Boolean aiMode){

		super("Edit Frame");
		this.aiMode = aiMode;
		this.mainMenuFrame = mainMenuFrame;
		this.level = level;
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

		GameEngineData.setWindowSize(windowSize);
		AudioInputStream deleteStream = null;

		try {
			File deleteSound = null;
			if(level == 1){
				deleteSound = new File("asset/audio/editMusic.wav");
			}else if (level ==2){
				deleteSound = new File("asset/audio/editMusic2.wav");
			}else if (level ==3){
				deleteSound = new File("asset/audio/editMusic3.wav");
			}else if (level ==4){
				deleteSound = new File("asset/audio/editMusic4.wav");
			}

			deleteStream = AudioSystem.getAudioInputStream(deleteSound);
		} catch (UnsupportedAudioFileException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		AudioFormat deleteFormat = deleteStream.getFormat();
		Info deleteInfo = new DataLine.Info(Clip.class, deleteFormat);

		try {
			deleteClip = (Clip) AudioSystem.getLine(deleteInfo);
		} catch (LineUnavailableException e) {

			e.printStackTrace();
		}	
		try {
			deleteClip.open(deleteStream);
		} catch (LineUnavailableException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		deleteClip.start();

		mainMenuClip = clip;
		editModeMainPanel = new JPanel();
		editModeMainPanel.setSize(windowSize, windowSize);
		editModeMainPanel.setBackground(Color.BLACK);
		editModeMainPanel.setLayout(new BorderLayout());
		editModeMainPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.GRAY));

		preferredSize = new Dimension(500,30);
		this.setSize(windowSize, windowSize+60);
		this.setBackground(Color.BLACK);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.setUndecorated(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		ImageIcon img = new ImageIcon("asset/images/icon.png");
		this.setIconImage(img.getImage());
		
		try {
			addContent();
		} catch (FontFormatException | IOException e) {

			e.printStackTrace();
		}
		add(editModeMainPanel, BorderLayout.CENTER);
	}

	/**
	 * Adds the content.
	 *
	 * @throws FontFormatException the font format exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void addContent() throws FontFormatException, IOException{

		Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("asset/fonts/mainFont4.ttf"));
		customFont = customFont.deriveFont(10f);

		JPanel buttonsPanel = new JPanel();
		GridLayout buttonsLayout = new GridLayout(0,4);
		buttonsLayout.setVgap(0);
		buttonsPanel.setLayout(buttonsLayout);
		buttonsPanel.setBackground(Color.BLACK);

		JPanel gizmoPanel = new JPanel();
		GridLayout gizmoLayout = new GridLayout(0,4);
		gizmoLayout.setVgap(0);
		gizmoPanel.setLayout(buttonsLayout);
		gizmoPanel.setBackground(Color.BLACK);

		JLabel exitButton = new JLabel("Exit");
		buttonDesigner(exitButton);

		JLabel muteButton = new JLabel("Mute");
		buttonDesigner(muteButton);

		JLabel goBackButton = new JLabel("Main Menu");
		buttonDesigner(goBackButton);

		JLabel saveButton = new JLabel("Save");
		buttonDesigner(saveButton);

		buttonsPanel.add(goBackButton);
		buttonsPanel.add(muteButton);
		buttonsPanel.add(saveButton);
		buttonsPanel.add(exitButton);

		JLabel squareTakozButton = new JLabel("Square T");
		buttonDesigner(squareTakozButton);

		JLabel triangularTakozButton = new JLabel("Triangular T");
		buttonDesigner(triangularTakozButton);

		JLabel firildakButton = new JLabel("Firildak");
		buttonDesigner(firildakButton);

		JLabel tokatButton = new JLabel("Tokat");
		buttonDesigner(tokatButton);

		gizmoPanel.add(squareTakozButton);
		gizmoPanel.add(triangularTakozButton);
		gizmoPanel.add(firildakButton);
		gizmoPanel.add(tokatButton);

		PanelEdit k = new PanelEdit(windowSize, this, level, aiMode);
		editModeMainPanel.add(k, BorderLayout.CENTER);
		editModeMainPanel.add(buttonsPanel, BorderLayout.NORTH);
		editModeMainPanel.add(gizmoPanel, BorderLayout.SOUTH);

		goBackButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				deleteClip.stop();
				GameEngineData.clearAll();
				setVisible(false);

				mainMenuFrame.setVisible(true);

				try{
					mainMenuClip.start();

				}catch(Exception e3){}

				dispose();
			}

			public void mouseEntered(MouseEvent e) {
				goBackButton.setForeground(Color.RED);
				GameEngineAudioHandler.getInstance().makeClickSound();

			}

			public void mouseExited(MouseEvent e) {
				goBackButton.setForeground(Color.WHITE);
			}

		});

		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);		

			}

			public void mouseEntered(MouseEvent e) {
				exitButton.setForeground(Color.RED);
				GameEngineAudioHandler.getInstance().makeClickSound();

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
					deleteClip.stop();
					muted = false;

				}else{

					muteButton.setText("mute");
					deleteClip.start();
					muted = true;
				}
			}

			public void mouseEntered(MouseEvent e) {
				muteButton.setForeground(Color.RED);
				GameEngineAudioHandler.getInstance().makeClickSound();

			}

			public void mouseExited(MouseEvent e) {
				muteButton.setForeground(Color.WHITE);
			}
		});

		saveButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					
					FrameSave sw = new FrameSave(1);
					sw.setVisible(true);


				} catch (FontFormatException | IOException e1) {

					e1.printStackTrace();
				}
			}

			public void mouseEntered(MouseEvent e) {
				saveButton.setForeground(Color.RED);
				GameEngineAudioHandler.getInstance().makeClickSound();
			}

			public void mouseExited(MouseEvent e) {
				saveButton.setForeground(Color.WHITE);
			}

		});

		squareTakozButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectedItem = "SQUARETAKOZ";
			}

			public void mouseEntered(MouseEvent e) {
				squareTakozButton.setForeground(new Color(255, 119, 117));
				GameEngineAudioHandler.getInstance().makeClickSound();
			}

			public void mouseExited(MouseEvent e) {
				squareTakozButton.setForeground(Color.WHITE);
			}
		});

		triangularTakozButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectedItem = "TRIANGULARTAKOZ";
			}

			public void mouseEntered(MouseEvent e) {
				triangularTakozButton.setForeground(new Color(255, 119, 117));
				GameEngineAudioHandler.getInstance().makeClickSound();
			}

			public void mouseExited(MouseEvent e) {
				triangularTakozButton.setForeground(Color.WHITE);
			}
		});

		firildakButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectedItem = "FIRILDAK";
			}

			public void mouseEntered(MouseEvent e) {
				firildakButton.setForeground(new Color(255, 119, 117));
				GameEngineAudioHandler.getInstance().makeClickSound();
			}

			public void mouseExited(MouseEvent e) {
				firildakButton.setForeground(Color.WHITE);
			}
		});

		tokatButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectedItem = "TOKAT";
			}

			public void mouseEntered(MouseEvent e) {
				tokatButton.setForeground(new Color(255, 119, 117));
				GameEngineAudioHandler.getInstance().makeClickSound();
			}

			public void mouseExited(MouseEvent e) {
				tokatButton.setForeground(Color.WHITE);
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
		Color k = new Color(217, 219, 217);
		Font mainMenuFont = Font.createFont(Font.TRUETYPE_FONT, new File("asset/fonts/mainFont4.ttf"));
		mainMenuFont = mainMenuFont.deriveFont(15f);
		label.setFont(mainMenuFont);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setForeground(k);
		label.setPreferredSize(preferredSize);
		label.setBorder(BorderFactory.createLineBorder(k,2));
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

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {	
		int k = e.getKeyChar();
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {}

	/**
	 * Gets the main menu frame.
	 *
	 * @return the main menu frame
	 */
	public JFrame getMainMenuFrame() {
		return mainMenuFrame;
	}

	/**
	 * Sets the main menu frame.
	 *
	 * @param mainMenuFrame the new main menu frame
	 */
	public void setMainMenuFrame(JFrame mainMenuFrame) {
		this.mainMenuFrame = mainMenuFrame;
	}

	/**
	 * Gets the main menu clip.
	 *
	 * @return the main menu clip
	 */
	public Clip getMainMenuClip() {
		return mainMenuClip;
	}

	/**
	 * Sets the main menu clip.
	 *
	 * @param mainMenuClip the new main menu clip
	 */
	public void setMainMenuClip(Clip mainMenuClip) {
		this.mainMenuClip = mainMenuClip;
	}

	/**
	 * Gets the delete clip.
	 *
	 * @return the delete clip
	 */
	public Clip getDeleteClip() {
		return deleteClip;
	}

	/**
	 * Sets the delete clip.
	 *
	 * @param deleteClip the new delete clip
	 */
	public void setDeleteClip(Clip deleteClip) {
		this.deleteClip = deleteClip;
	}
}
