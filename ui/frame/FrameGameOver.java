package ui.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;
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

/**
 * The Class FrameGameOver.
 */
public class FrameGameOver extends JFrame {

	/** The preferred size. */
	private Dimension preferredSize;

	/** The Panel game over. */
	private JPanel PanelGameOver;

	/** The player 2 win. */
	private boolean player2win;

	/** The audio clip. */
	private Clip audioClip;

	/**
	 * Instantiates a new frame game over.
	 *
	 * @param player2win the player 2 win
	 * @throws FontFormatException the font format exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public FrameGameOver(Boolean player2win) throws FontFormatException, IOException{
		super();

		this.player2win = player2win;
		PanelGameOver = new JPanel();
		PanelGameOver.setSize(500, 500);
		PanelGameOver.setBackground(Color.BLACK);
		PanelGameOver.setLayout(new BorderLayout());
		preferredSize = new Dimension(500,50);

		this.setSize(500, 500);
		this.setBackground(Color.BLACK);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.setUndecorated(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		ImageIcon img = new ImageIcon("asset/images/icon.png");
		this.setIconImage(img.getImage());
		addContent();
		add(PanelGameOver, BorderLayout.CENTER);
	}

	/**
	 * Adds the content.
	 *
	 * @throws FontFormatException the font format exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void addContent() throws FontFormatException, IOException{

		Font mainMenuFont = Font.createFont(Font.TRUETYPE_FONT, new File("asset/fonts/mainFont4.ttf"));
		mainMenuFont = mainMenuFont.deriveFont(20f);

		AudioInputStream audioStream = null;

		try {
			File audioSound = null;
			audioSound = new File("asset/audio/cezmi.wav");
			audioStream = AudioSystem.getAudioInputStream(audioSound);
			AudioFormat deleteFormat = audioStream.getFormat();
			Info deleteInfo = new DataLine.Info(Clip.class, deleteFormat);
			audioClip = (Clip) AudioSystem.getLine(deleteInfo);
			audioClip.open(audioStream);
			audioClip.start();

		}catch(Exception e1){}

		JPanel buttonsPanel = new JPanel();
		GridLayout buttonsLayout = new GridLayout(0,1);
		buttonsLayout.setVgap(0);
		buttonsPanel.setLayout(buttonsLayout);
		buttonsPanel.setBackground(Color.BLACK);

		JLabel goBackButton = new JLabel("Main Menu");
		goBackButton.setFont(mainMenuFont);
		goBackButton.setHorizontalAlignment(JLabel.CENTER);
		goBackButton.setForeground(Color.WHITE);
		goBackButton.setPreferredSize(preferredSize);
		goBackButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));

		JPanel rightPanel = new JPanel(); 
		JPanel leftPanel = new JPanel();  

		JPanel allPanel = new JPanel ();
		GridLayout k = new GridLayout(0, 2);
		BorderLayout bl = new BorderLayout();

		allPanel.setLayout(k);
		this.setLayout(bl);
		this.setBackground(new Color(20, 20, 20));
		allPanel.setPreferredSize(new Dimension(500,450));

		rightPanel.setBackground(Color.BLACK); // SET COLOR HERE RIGHT PANEL
		leftPanel.setBackground(Color.BLACK);  // SET COLOR HERE LEFT  PANEL

		rightPanel.setLayout(new GridBagLayout());
		leftPanel.setLayout(new GridBagLayout());

		JLabel rightLabel;
		JLabel leftLabel;

		if(player2win){
			rightLabel = new JLabel("<HTML> <center> Player 1 <br> Lost </center> <HTML>");
			leftLabel  = new JLabel("<HTML> <center> Player 2 <br> Win </center> <HTML>");
		}else{
			rightLabel = new JLabel("<HTML> <center> Player 1 <br> Win </center> <HTML>");
			leftLabel  = new JLabel("<HTML> <center> Player 2 <br> Lost </center> <HTML>");
		}
		
		rightPanel.setFont(mainMenuFont);

		rightPanel.setForeground(Color.WHITE);
		rightPanel.setPreferredSize(preferredSize);
		rightPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));

		leftPanel.setFont(mainMenuFont);

		leftPanel.setForeground(Color.WHITE);
		leftPanel.setPreferredSize(preferredSize);
		leftPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));

		mainMenuFont = mainMenuFont.deriveFont(30f);
		GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(mainMenuFont);

		rightLabel.setForeground(Color.WHITE);
		leftLabel.setForeground(Color.WHITE);

		rightLabel.setFont(mainMenuFont);
		leftLabel.setFont(mainMenuFont);

		rightPanel.add(rightLabel);
		leftPanel.add(leftLabel);

		allPanel.add(rightPanel);
		allPanel.add(leftPanel);

		buttonsPanel.add(goBackButton);

		PanelGameOver.add(allPanel, BorderLayout.NORTH);
		PanelGameOver.add(buttonsPanel, BorderLayout.SOUTH);

		goBackButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				setVisible(false);
				FrameMainMenu mainMenuFrame = new FrameMainMenu();
				audioClip.stop();
				dispose();
			}

			public void mouseEntered(MouseEvent e) {
				goBackButton.setForeground(Color.YELLOW);
			}

			public void mouseExited(MouseEvent e) {
				goBackButton.setForeground(Color.WHITE);
			}
		});
	}

	/**
	 * Styler.
	 *
	 * @param label the label
	 * @throws FontFormatException the font format exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void styler(JLabel label) throws FontFormatException, IOException{

		Font mainMenuFont = Font.createFont(Font.TRUETYPE_FONT, new File("asset/fonts/mainFont4.ttf"));
		mainMenuFont = mainMenuFont.deriveFont(20f);
		label.setFont(mainMenuFont);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setForeground(Color.WHITE);
		label.setBorder(BorderFactory.createLineBorder(Color.WHITE));
	}
}