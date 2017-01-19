package ui.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import domain.xmloperations.XMLReadSettings;
import domain.xmloperations.XMLWriteSettings;

/**
 * The Class FrameSettings.
 */
public class FrameSettings extends JFrame {

	/** The settings main panel. */
	private JPanel settingsMainPanel;
	
	/** The main menu frame. */
	private JFrame mainMenuFrame;
	
	/** The preferred size. */
	private Dimension preferredSize;
	
	/** The preferred size 2. */
	private Dimension preferredSize2;
	
	/** The click stream. */
	private AudioInputStream clickStream;
	
	/** The click format. */
	private AudioFormat clickFormat;
	
	/** The click info. */
	private DataLine.Info clickInfo;
	
	/** The click clip. */
	private Clip clickClip;
	
	/** The door stream. */
	private AudioInputStream doorStream;
	
	/** The door format. */
	private AudioFormat doorFormat;
	
	/** The door info. */
	private DataLine.Info doorInfo;
	
	/** The door clip. */
	private Clip doorClip;
	
	/** The small size. */
	private JRadioButton smallSize;
	
	/** The medium size. */
	private JRadioButton mediumSize;
	
	/** The max size. */
	private JRadioButton maxSize;
	
	/** The player 1 left key code. */
	private int player1leftKeyCode;
	
	/** The player 1 right key code. */
	private int player1RightKeyCode;
	
	/** The player 2 left key code. */
	private int player2leftKeyCode;
	
	/** The player 2 right key code. */
	private int player2RightKeyCode;
	
	/** The player 1 takoz key code. */
	private int player1TakozKeyCode;
	
	/** The player 2 takoz key code. */
	private int player2TakozKeyCode;
	
	/** The xml. */
	private XMLReadSettings xml;
	
	/** The setting size. */
	private String settingSize;

	/**
	 * Instantiates a new frame settings.
	 *
	 * @param mainMenuFrame the main menu frame
	 * @throws FontFormatException the font format exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ParserConfigurationException the parser configuration exception
	 * @throws SAXException the SAX exception
	 */
	public FrameSettings(JFrame mainMenuFrame) throws FontFormatException, IOException, ParserConfigurationException, SAXException{
		super("Settings Frame");
		
		this.mainMenuFrame = mainMenuFrame;
		
		settingsMainPanel = new JPanel();
		settingsMainPanel.setSize(500, 500);
		settingsMainPanel.setBackground(Color.BLACK);
		settingsMainPanel.setLayout(new BorderLayout());
		preferredSize = new Dimension(500,38);
		preferredSize2 = new Dimension(500,44);
		settingsMainPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		
		this.setSize(500, 500);
		this.setBackground(Color.BLACK);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.setUndecorated(true);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		ImageIcon img = new ImageIcon("asset/images/icon.png");
		this.setIconImage(img.getImage());
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		addContent();
		
		add(settingsMainPanel, BorderLayout.CENTER);
	}

	/**
	 * Adds the content.
	 *
	 * @throws FontFormatException the font format exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ParserConfigurationException the parser configuration exception
	 */
	public void addContent() throws FontFormatException, IOException, ParserConfigurationException{

		//Create xml

		try {
			xml = new XMLReadSettings();
		} catch (SAXException e1) {
			
			e1.printStackTrace();
		}

		Font mainMenuFont = Font.createFont(Font.TRUETYPE_FONT, new File("asset/fonts/mainFont4.ttf"));
		mainMenuFont = mainMenuFont.deriveFont(20f);
		Font settingMain = Font.createFont(Font.TRUETYPE_FONT, new File("asset/fonts/mainFont4.ttf"));
		settingMain = settingMain.deriveFont(30f);
		JPanel buttonsPanel = new JPanel();
		GridLayout buttonsLayout = new GridLayout(0,2);
		buttonsLayout.setVgap(0);
		buttonsPanel.setLayout(buttonsLayout);
		buttonsPanel.setBackground(Color.BLACK);
		JPanel radioButtonPanel = new JPanel();
		GridLayout rbpl = new GridLayout(0, 1);
		rbpl.setVgap(0);
		
		radioButtonPanel.setLayout(rbpl);
		radioButtonPanel.setBackground(Color.BLACK);
		JPanel player1LeftPane = new JPanel();
		GridLayout rbplp1l = new GridLayout(0, 2);
		rbplp1l.setVgap(0);
		
		Color selectorColor = new Color(35, 35, 35);
		
		player1LeftPane.setLayout(rbplp1l);
		player1LeftPane.setBackground(selectorColor);
		JPanel player1RightPane = new JPanel();
		GridLayout rbplp1r = new GridLayout(0, 2);
		rbplp1r.setVgap(0);
		
		player1RightPane.setLayout(rbplp1r);
		player1RightPane.setBackground(selectorColor);
		JPanel player2LeftPane = new JPanel();
		GridLayout rbplp2l = new GridLayout(0, 2);
		rbplp2l.setVgap(0);
		
		player2LeftPane.setLayout(rbplp2l);
		player2LeftPane.setBackground(selectorColor);
		JPanel player2RightPane = new JPanel();
		GridLayout rbplp2r = new GridLayout(0, 2);
		rbplp2r.setVgap(0);
		
		player2RightPane.setLayout(rbplp2r);
		player2RightPane.setBackground(selectorColor);
		JPanel player1TokatPane = new JPanel();
		GridLayout tokatci1 = new GridLayout(0, 2);
		tokatci1.setVgap(0);
		
		player1TokatPane.setLayout(tokatci1);
		player1TokatPane.setBackground(selectorColor);
		JPanel player2TokatPane = new JPanel();
		GridLayout tokatci2 = new GridLayout(0, 2);
		tokatci2.setVgap(0);
		
		player2TokatPane.setLayout(tokatci2);
		player2TokatPane.setBackground(selectorColor);
		settingsMainPanel.add(buttonsPanel, BorderLayout.SOUTH);
		settingsMainPanel.add(radioButtonPanel, BorderLayout.NORTH);
		
		JLabel settingsLabel = new JLabel("Settings");
		JLabel labelSize = new JLabel("Screen Size");
		JLabel labelKey = new JLabel("Key Binding");
		smallSize = new JRadioButton("Small size");
		mediumSize = new JRadioButton("Medium Size");
		maxSize = new JRadioButton("Maximum Size");
		
		ButtonGroup sizeGroup = new ButtonGroup();
		
		sizeGroup.add(smallSize);
		sizeGroup.add(mediumSize);
		sizeGroup.add(maxSize);

		//Select game size from xml object created
		if (xml.getSize().equals("small")) {
			settingSize = "small";
			smallSize.setSelected(true);
		} else if (xml.getSize().equals("medium")) {
			settingSize = "medium";
			mediumSize.setSelected(true);
		} else {
			settingSize = "max";
			maxSize.setSelected(true);
		}

		JLabel player1RightLabel = new JLabel("P1 Right");
		JLabel player1LeftLabel = new JLabel("P1 Left");
		JLabel player2RightLabel = new JLabel("P2 Right");
		JLabel player2LeftLabel = new JLabel("P2 Left");
		JLabel player1TokatLabel = new JLabel("P1 Tokat");
		JLabel player2TokatLabel = new JLabel("P2 Tokat");
		
		JTextField player1Right = new JTextField(1);
		JTextField player1Left = new JTextField(1);	    
		JTextField player2Right = new JTextField(1);
		JTextField player2Left = new JTextField(1);
		JTextField player1Tokat = new JTextField(1);
		JTextField player2Tokat = new JTextField(1);

		player1Tokat.setEditable(false);
		player2Tokat.setEditable(false);
		player1Right.setEditable(false);
		player2Right.setEditable(false);
		player1Left.setEditable(false);
		player2Left.setEditable(false);


		player1Tokat.setText(keyNameCreator(Integer.parseInt(xml.getPlayer1Tokat())));
		player2Tokat.setText(keyNameCreator(Integer.parseInt(xml.getPlayer2Tokat())));
		player1Left.setText(keyNameCreator(Integer.parseInt(xml.getPlayer1Left())));
		player1Right.setText(keyNameCreator(Integer.parseInt(xml.getPlayer1Right())));
		player2Left.setText(keyNameCreator(Integer.parseInt(xml.getPlayer2Left())));
		player2Right.setText(keyNameCreator(Integer.parseInt(xml.getPlayer2Right())));

		player1leftKeyCode = Integer.parseInt(xml.getPlayer1Left());
		player1RightKeyCode = Integer.parseInt(xml.getPlayer1Right());
		player2leftKeyCode = Integer.parseInt(xml.getPlayer2Left());
		player2RightKeyCode = Integer.parseInt(xml.getPlayer2Right());
		player1TakozKeyCode = Integer.parseInt(xml.getPlayer1Tokat());
		player2TakozKeyCode = Integer.parseInt(xml.getPlayer2Tokat());

		player1TokatLabel.setFont(mainMenuFont);
		player1TokatLabel.setHorizontalAlignment(JLabel.CENTER);
		player1TokatLabel.setForeground(Color.WHITE);
		player1TokatLabel.setPreferredSize(preferredSize);
		player1TokatLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		player1TokatLabel.setBackground(Color.BLACK);

		player2TokatLabel.setFont(mainMenuFont);
		player2TokatLabel.setHorizontalAlignment(JLabel.CENTER);
		player2TokatLabel.setForeground(Color.WHITE);
		player2TokatLabel.setPreferredSize(preferredSize);
		player2TokatLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		player2TokatLabel.setBackground(Color.BLACK);

		player1RightLabel.setFont(mainMenuFont);
		player1RightLabel.setHorizontalAlignment(JLabel.CENTER);
		player1RightLabel.setForeground(Color.WHITE);
		player1RightLabel.setPreferredSize(preferredSize);
		player1RightLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		player1RightLabel.setBackground(Color.BLACK);

		player1LeftLabel.setFont(mainMenuFont);
		player1LeftLabel.setHorizontalAlignment(JLabel.CENTER);
		player1LeftLabel.setForeground(Color.WHITE);
		player1LeftLabel.setPreferredSize(preferredSize);
		player1LeftLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		player1LeftLabel.setBackground(Color.BLACK);

		player2RightLabel.setFont(mainMenuFont);
		player2RightLabel.setHorizontalAlignment(JLabel.CENTER);
		player2RightLabel.setForeground(Color.WHITE);
		player2RightLabel.setPreferredSize(preferredSize);
		player2RightLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		player2RightLabel.setBackground(Color.BLACK);

		player2LeftLabel.setFont(mainMenuFont);
		player2LeftLabel.setHorizontalAlignment(JLabel.CENTER);
		player2LeftLabel.setForeground(Color.WHITE);
		player2LeftLabel.setPreferredSize(preferredSize);
		player2LeftLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		player2LeftLabel.setBackground(Color.BLACK);

		player1Tokat.setFont(mainMenuFont);
		player1Tokat.setHorizontalAlignment(JLabel.CENTER);
		player1Tokat.setForeground(Color.BLACK);
		player1Tokat.setPreferredSize(preferredSize);
		player1Tokat.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		player1Right.setBackground(Color.WHITE);

		player2Tokat.setFont(mainMenuFont);
		player2Tokat.setHorizontalAlignment(JLabel.CENTER);
		player2Tokat.setForeground(Color.BLACK);
		player2Tokat.setPreferredSize(preferredSize);
		player2Tokat.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		player2Tokat.setBackground(Color.WHITE);

		player1Right.setFont(mainMenuFont);
		player1Right.setHorizontalAlignment(JLabel.CENTER);
		player1Right.setForeground(Color.BLACK);
		player1Right.setPreferredSize(preferredSize);
		player1Right.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		player1Right.setBackground(Color.WHITE);

		player2Right.setFont(mainMenuFont);
		player2Right.setHorizontalAlignment(JLabel.CENTER);
		player2Right.setForeground(Color.BLACK);
		player2Right.setPreferredSize(preferredSize);
		player2Right.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		player2Right.setBackground(Color.WHITE);

		player1Left.setFont(mainMenuFont);
		player1Left.setHorizontalAlignment(JLabel.CENTER);
		player1Left.setForeground(Color.BLACK);
		player1Left.setPreferredSize(preferredSize);
		player1Left.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		player1Left.setBackground(Color.WHITE);

		player2Left.setFont(mainMenuFont);
		player2Left.setHorizontalAlignment(JLabel.CENTER);
		player2Left.setForeground(Color.BLACK);
		player2Left.setPreferredSize(preferredSize);
		player2Left.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		player2Left.setBackground(Color.WHITE);

		smallSize.setFont(mainMenuFont);
		smallSize.setHorizontalAlignment(JLabel.CENTER);
		smallSize.setForeground(Color.WHITE);
		smallSize.setPreferredSize(preferredSize);
		smallSize.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		smallSize.setBackground(selectorColor);

		settingsLabel.setFont(settingMain);
		settingsLabel.setHorizontalAlignment(JLabel.CENTER);
		settingsLabel.setForeground(Color.WHITE);
		settingsLabel.setPreferredSize(preferredSize);
		settingsLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		settingsLabel.setBackground(Color.BLACK);

		labelKey.setFont(mainMenuFont);
		labelKey.setHorizontalAlignment(JLabel.CENTER);
		labelKey.setForeground(Color.WHITE);
		labelKey.setPreferredSize(preferredSize);
		labelKey.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		labelKey.setBackground(Color.BLACK);

		mediumSize.setFont(mainMenuFont);
		mediumSize.setHorizontalAlignment(JLabel.CENTER);
		mediumSize.setForeground(Color.WHITE);
		mediumSize.setPreferredSize(preferredSize);
		mediumSize.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		mediumSize.setBackground(selectorColor);

		maxSize.setFont(mainMenuFont);
		maxSize.setHorizontalAlignment(JLabel.CENTER);
		maxSize.setForeground(Color.WHITE);
		maxSize.setPreferredSize(preferredSize);
		maxSize.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		maxSize.setBackground(selectorColor);

		labelSize.setFont(mainMenuFont);
		labelSize.setHorizontalAlignment(JLabel.CENTER);
		labelSize.setForeground(Color.WHITE);
		labelSize.setPreferredSize(preferredSize);
		labelSize.setBorder(BorderFactory.createLineBorder(Color.WHITE));

		//radioButtonPanel.add(settingsLabel);

		radioButtonPanel.add(settingsLabel);
		radioButtonPanel.add(labelSize);
		radioButtonPanel.add(smallSize);
		radioButtonPanel.add(mediumSize);
		radioButtonPanel.add(maxSize);
		radioButtonPanel.add(labelKey);

		player1LeftPane.add(player1LeftLabel);
		player1LeftPane.add(player1Left);
		player1RightPane.add(player1RightLabel);
		player1RightPane.add(player1Right);
		player2LeftPane.add(player2LeftLabel);
		player2LeftPane.add(player2Left);
		player2RightPane.add(player2RightLabel);
		player2RightPane.add(player2Right);
		player1TokatPane.add(player1TokatLabel);
		player1TokatPane.add(player1Tokat);
		player2TokatPane.add(player2TokatLabel);
		player2TokatPane.add(player2Tokat);

		radioButtonPanel.add(player1LeftPane);
		radioButtonPanel.add(player1RightPane);
		radioButtonPanel.add(player1TokatPane);
		radioButtonPanel.add(player2LeftPane);
		radioButtonPanel.add(player2RightPane);
		radioButtonPanel.add(player2TokatPane);

		JLabel defaultSettingsButton = new JLabel("Default");

		defaultSettingsButton.setFont(mainMenuFont);
		defaultSettingsButton.setHorizontalAlignment(JLabel.CENTER);
		defaultSettingsButton.setForeground(Color.WHITE);
		defaultSettingsButton.setPreferredSize(preferredSize2);
		defaultSettingsButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));

		buttonsPanel.add(defaultSettingsButton);

		JLabel saveSettingsButton = new JLabel("Save Settings");

		saveSettingsButton.setFont(mainMenuFont);
		saveSettingsButton.setHorizontalAlignment(JLabel.CENTER);
		saveSettingsButton.setForeground(Color.WHITE);
		saveSettingsButton.setPreferredSize(preferredSize2);
		saveSettingsButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));

		buttonsPanel.add(saveSettingsButton);

		smallSize.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				makeRadioSound();
				settingSize = "small";
			}
		});

		mediumSize.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				makeRadioSound();
				settingSize = "medium";
			}
		});

		maxSize.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				makeRadioSound();
				settingSize = "max";
			}
		});

		player1Left.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				player1Left.setForeground(Color.BLACK);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				player1Left.setForeground(Color.BLACK);
			}

			@Override
			public void keyReleased(KeyEvent e) {

				if(isCorrectInput(e.getKeyCode()) && isAlreadUsed(e.getKeyCode())){
					if(isSpecialArgument(e.getKeyCode())){
						player1Left.setText(specKeyDetected(e.getKeyCode()));
					}else{
						String placeHolder = "" + e.getKeyChar();
						player1Left.setText(placeHolder);
					}
					player1leftKeyCode = e.getKeyCode();
				}
			}
		});

		player1Left.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				player1Tokat.setForeground(Color.BLACK);
				player2Tokat.setForeground(Color.BLACK);
				player1Left.setForeground(Color.RED);
				player1Right.setForeground(Color.BLACK);
				player2Left.setForeground(Color.BLACK);
				player2Right.setForeground(Color.BLACK);
				makeClickSound();
			}

			public void mouseEntered(MouseEvent e) {

			}

			public void mouseExited(MouseEvent e) {

			}

		});

		player1Right.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				player1Right.setForeground(Color.BLACK);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				player1Right.setForeground(Color.BLACK);

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if(isCorrectInput(e.getKeyCode()) && isAlreadUsed(e.getKeyCode())){
					if(isSpecialArgument(e.getKeyCode())){
						player1Right.setText(specKeyDetected(e.getKeyCode()));
					}else{
						String placeHolder = "" + e.getKeyChar();
						player1Right.setText(placeHolder);
					}

					player1RightKeyCode = e.getKeyCode();

				}
			}
		});

		player1Right.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				player1Tokat.setForeground(Color.BLACK);
				player2Tokat.setForeground(Color.BLACK);
				player1Left.setForeground(Color.BLACK);
				player1Right.setForeground(Color.RED);
				player2Left.setForeground(Color.BLACK);
				player2Right.setForeground(Color.BLACK);

				makeClickSound();
			}

			public void mouseEntered(MouseEvent e) {


			}

			public void mouseExited(MouseEvent e) {

			}

		});

		player2Left.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				player2Left.setForeground(Color.BLACK);

			}

			@Override
			public void keyPressed(KeyEvent e) {
				player2Left.setForeground(Color.BLACK);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if(isCorrectInput(e.getKeyCode()) && isAlreadUsed(e.getKeyCode())){
					if(isSpecialArgument(e.getKeyCode())){
						player2Left.setText(specKeyDetected(e.getKeyCode()));
					}else{
						String placeHolder = "" + e.getKeyChar();
						player2Left.setText(placeHolder);
					}

					player2leftKeyCode = e.getKeyCode();
				}
			}
		});

		player2Left.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				player1Tokat.setForeground(Color.BLACK);
				player2Tokat.setForeground(Color.BLACK);
				player1Left.setForeground(Color.BLACK);
				player1Right.setForeground(Color.BLACK);
				player2Left.setForeground(Color.RED);
				player2Right.setForeground(Color.BLACK);
				makeClickSound();
			}

			public void mouseEntered(MouseEvent e) {


			}

			public void mouseExited(MouseEvent e) {

			}

		});


		player2Right.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				player2Right.setForeground(Color.BLACK);

			}

			@Override
			public void keyPressed(KeyEvent e) {
				player2Right.setForeground(Color.BLACK);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if(isCorrectInput(e.getKeyCode()) && isAlreadUsed(e.getKeyCode())){
					if(isSpecialArgument(e.getKeyCode())){
						player2Right.setText(specKeyDetected(e.getKeyCode()));
					}else{
						String placeHolder = "" + e.getKeyChar();
						player2Right.setText(placeHolder);
					}

					player2RightKeyCode = e.getKeyCode();

				}
			}
		});

		player2Right.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				player1Tokat.setForeground(Color.BLACK);
				player2Tokat.setForeground(Color.BLACK);
				player1Left.setForeground(Color.BLACK);
				player1Right.setForeground(Color.BLACK);
				player2Left.setForeground(Color.BLACK);
				player2Right.setForeground(Color.RED);

				makeClickSound();
			}

			public void mouseEntered(MouseEvent e) {


			}

			public void mouseExited(MouseEvent e) {

			}

		});

		player1Tokat.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				player1Tokat.setForeground(Color.BLACK);

			}

			@Override
			public void keyPressed(KeyEvent e) {
				player1Tokat.setForeground(Color.BLACK);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if(isCorrectInput(e.getKeyCode()) && isAlreadUsed(e.getKeyCode())){
					if(isSpecialArgument(e.getKeyCode())){
						player1Tokat.setText(specKeyDetected(e.getKeyCode()));
					}else{
						String placeHolder = "" + e.getKeyChar();
						player1Tokat.setText(placeHolder);
					}

					player1TakozKeyCode = e.getKeyCode();

				}			
			}
		});

		player1Tokat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				player1Tokat.setForeground(Color.RED);
				player2Tokat.setForeground(Color.BLACK);
				player1Left.setForeground(Color.BLACK);
				player1Right.setForeground(Color.BLACK);
				player2Left.setForeground(Color.BLACK);
				player2Right.setForeground(Color.BLACK);
				
				makeClickSound();
			}

			public void mouseEntered(MouseEvent e) {


			}

			public void mouseExited(MouseEvent e) {

			}

		});

		player2Tokat.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				player2Tokat.setForeground(Color.BLACK);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				player2Tokat.setForeground(Color.BLACK);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if(isCorrectInput(e.getKeyCode()) && isAlreadUsed(e.getKeyCode())){
					if(isSpecialArgument(e.getKeyCode())){
						player2Tokat.setText(specKeyDetected(e.getKeyCode()));
					}else{
						String placeHolder = "" + e.getKeyChar();
						player2Tokat.setText(placeHolder);
					}
					player2TakozKeyCode = e.getKeyCode();
				}
			}
		});

		player2Tokat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				player1Tokat.setForeground(Color.BLACK);
				player2Tokat.setForeground(Color.RED);
				player1Left.setForeground(Color.BLACK);
				player1Right.setForeground(Color.BLACK);
				player2Left.setForeground(Color.BLACK);
				player2Right.setForeground(Color.BLACK);
				makeClickSound();
			}

			public void mouseEntered(MouseEvent e) {


			}

			public void mouseExited(MouseEvent e) {

			}

		});

		defaultSettingsButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				player2leftKeyCode = 37;
				player2RightKeyCode = 39;
				player1leftKeyCode = 65;
				player1RightKeyCode = 68;
				player2TakozKeyCode = 32;
				player1TakozKeyCode = 17;

				player2Left.setText("left");
				player2Right.setText("right");
				player1Left.setText("a");
				player1Right.setText("d");
				player2Tokat.setText("space");
				player1Tokat.setText("control");
				settingSize = "medium";
				mediumSize.setSelected(true);
			}

			public void mouseEntered(MouseEvent e) {
				defaultSettingsButton.setForeground(Color.RED);
				makeClickSound();
			}

			public void mouseExited(MouseEvent e) {
				defaultSettingsButton.setForeground(Color.WHITE);
			}

		});

		saveSettingsButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// XML save current
				try {
					XMLWriteSettings xml = new XMLWriteSettings();
					xml.setSize(settingSize);
					xml.setPlayer1Left(Integer.toString(player1leftKeyCode));
					xml.setPlayer2Left(Integer.toString(player2leftKeyCode));
					xml.setPlayer1Right(Integer.toString(player1RightKeyCode));
					xml.setPlayer2Right(Integer.toString(player2RightKeyCode));
					xml.setPlayer1Tokat(Integer.toString(player1TakozKeyCode));
					xml.setPlayer2Tokat(Integer.toString(player2TakozKeyCode));
					xml.xmlFKR();
				} catch (ParserConfigurationException | SAXException | IOException | TransformerException e1) {
					
					e1.printStackTrace();
				}
				
				makeCloseSound();
				setVisible(false); 
				dispose(); 
				mainMenuFrame.setVisible(true);
			}

			public void mouseEntered(MouseEvent e) {
				saveSettingsButton.setForeground(Color.RED);
				makeClickSound();
			}

			public void mouseExited(MouseEvent e) {
				saveSettingsButton.setForeground(Color.WHITE);
			}

		});

	}

	/**
	 * Make click sound.
	 */
	public void makeClickSound(){
		File clickSound = new File("asset/audio/switch.wav");

		try{
			clickStream = AudioSystem.getAudioInputStream(clickSound);
			clickFormat = clickStream.getFormat();
			clickInfo = new DataLine.Info(Clip.class, clickFormat);
			clickClip = (Clip) AudioSystem.getLine(clickInfo);
			clickClip.open(clickStream);
			clickClip.start();
			
		}catch(Exception e){

		}
	}

	/**
	 * Make close sound.
	 */
	public void makeCloseSound(){
		File doorSound = new File("asset/audio/lock.wav");

		try{
			doorStream = AudioSystem.getAudioInputStream(doorSound);
			doorFormat = doorStream.getFormat();
			doorInfo = new DataLine.Info(Clip.class, doorFormat);
			doorClip = (Clip) AudioSystem.getLine(doorInfo);
			doorClip.open(doorStream);
			doorClip.start();
			
		}catch(Exception e){

		}

	}

	/**
	 * Make radio sound.
	 */
	public void makeRadioSound(){

		File doorSound = new File("asset/audio/radioButtonSwitch.wav");

		try{
			doorStream = AudioSystem.getAudioInputStream(doorSound);
			doorFormat = doorStream.getFormat();
			doorInfo = new DataLine.Info(Clip.class, doorFormat);
			doorClip = (Clip) AudioSystem.getLine(doorInfo);
			doorClip.open(doorStream);
			doorClip.start();
		}catch(Exception e){

		}
	}

	/**
	 * Checks if is correct input.
	 *
	 * @param k the k
	 * @return true, if is correct input
	 */
	public boolean isCorrectInput(int k){

		if( (k >= 37 && k <= 90) || k == 32 || k == 17 || k == 18 || k == 16 || k == 157){
			return true;

		}else{	
			return false;
		}
	}

	/**
	 * Checks if is special argument.
	 *
	 * @param k the k
	 * @return true, if is special argument
	 */
	public boolean isSpecialArgument(int k){

		if(k>= 48 && k <= 90 ){
			return false;

		}else{
			return true;
		}
	}

	/**
	 * Checks if is alread used.
	 *
	 * @param k the k
	 * @return true, if is alread used
	 */
	public boolean isAlreadUsed(int k){

		if(k == player1leftKeyCode || k == player1RightKeyCode || k == player2leftKeyCode || k == player2RightKeyCode || k == player1TakozKeyCode || k == player2TakozKeyCode ){
			return false;
		}else{
			return true;
		}

	}

	/**
	 * Spec key detected.
	 *
	 * @param k the k
	 * @return the string
	 */
	public String specKeyDetected(int k){

		if(k == 32){

			return "space";

		}else if(k == 18){

			return "alt";

		}else if(k == 17){

			return "control";

		}else if(k == 16){

			return "shift";

		}else if(k == 157){	

			return "command";

		}else if(k == 37){	
			return "left";

		}else if(k == 38){	
			return "up";

		}else if(k == 39){	
			return "right";

		}else if(k == 40){	
			return "down";

		}else{
			return "invalid";
		}	
	}

	/**
	 * Key name creator.
	 *
	 * @param key the key
	 * @return the string
	 */
	public String keyNameCreator(int key) {
		switch (key) {
		case 8: return "backspace";
		case 9: return "tab";
		case 13: return "enter";
		case 16: return "shift";
		case 17: return "control";
		case 18: return "alt";
		case 19: return "pause";
		case 20: return "capslock";
		case 27: return "escape";
		case 32: return "space";
		case 33: return "page up";
		case 34: return "page down";
		case 35: return "end";
		case 36: return "home";
		case 37: return "left";
		case 38: return "up";
		case 39: return "right";
		case 40: return "down";
		case 45: return "insert";
		case 46: return "delete";
		case 48: return "0";
		case 49: return "1";
		case 50: return "2";
		case 51: return "3";
		case 52: return "4";
		case 53: return "5";
		case 54: return "6";
		case 55: return "7";
		case 56: return "8";
		case 57: return "9";
		case 65: return "a";
		case 66: return "b";
		case 67: return "c";
		case 68: return "d";
		case 69: return "e";
		case 70: return "f";
		case 71: return "g";
		case 72: return "h";
		case 73: return "i";
		case 74: return "j";
		case 75: return "k";
		case 76: return "l";
		case 77: return "m";
		case 78: return "n";
		case 79: return "o";
		case 80: return "p";
		case 81: return "q";
		case 82: return "r";
		case 83: return "s";
		case 84: return "t"; 
		case 85: return "u";
		case 86: return "v";
		case 87: return "w";
		case 88: return "x";
		case 89: return "y";
		case 90: return "z";
		case 157: return "command";
		default:
			return "invalid";
		}
	}

	/**
	 * Action performed.
	 *
	 * @param e the e
	 */
	public void actionPerformed(ActionEvent e) {

	}
}