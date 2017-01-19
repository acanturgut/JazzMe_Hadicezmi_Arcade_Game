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
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import domain.gameengine.GameEngineAudioHandler;
import domain.gameengine.GameEngineData;

/**
 * The Class FrameLevelSelect.
 */
public class FrameLevelSelect extends JFrame {
	
	/** The Panel level. */
	JPanel PanelLevel;
	
	/** The main menu frame. */
	JFrame mainMenuFrame;
	
	/** The preferred size. */
	Dimension preferredSize;
	
	/** The scroll size. */
	Dimension scrollSize;
	
	/** The clip. */
	Clip clip;
	
	private Boolean aiMode;

	/**
	 * Instantiates a new frame level select.
	 *
	 * @param mainMenuFrame the main menu frame
	 * @param clip the clip
	 * @throws FontFormatException the font format exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public FrameLevelSelect(JFrame mainMenuFrame, Clip clip, Boolean aiMode) throws FontFormatException, IOException{

		super("Level");
		this.aiMode = aiMode;
		this.mainMenuFrame = mainMenuFrame;
		this.clip =clip;
		clip.stop();
		PanelLevel = new JPanel();
		PanelLevel.setSize(500, 500);
		PanelLevel.setBackground(Color.BLACK);
		PanelLevel.setLayout(new BorderLayout());
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
		add(PanelLevel, BorderLayout.CENTER);
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
		
		JPanel midPanel = new JPanel();
		JPanel mid2Panel = new JPanel();

		JPanel allPanel = new JPanel ();
		GridLayout k = new GridLayout(0, 4);
		BorderLayout bl = new BorderLayout();

		allPanel.setLayout(k);
		this.setLayout(bl);
		this.setBackground(new Color(20, 20, 20));
		allPanel.setPreferredSize(new Dimension(500,450));

		rightPanel.setBackground(Color.BLACK); // SET COLOR HERE RIGHT PANEL
		leftPanel.setBackground(Color.BLACK);  // SET COLOR HERE LEFT  PANEL
		
		midPanel.setBackground(Color.BLACK);  // SET COLOR HERE LEFT  PANEL
		mid2Panel.setBackground(Color.BLACK);  // SET COLOR HERE LEFT  PANEL

		rightPanel.setLayout(new GridBagLayout());
		leftPanel.setLayout(new GridBagLayout());
		
		midPanel.setLayout(new GridBagLayout());
		mid2Panel.setLayout(new GridBagLayout());

		JLabel rightLabel = new JLabel("<HTML> <center> Level 1 <br> Beginning </center> <HTML>");
		JLabel leftLabel  = new JLabel("<HTML> <center> Level 2 <br> Advance </center> <HTML>");
		JLabel midLabel  = new JLabel("<HTML>  <center> Level 3 <br> W. </center> <HTML>");
		JLabel mid2Label  = new JLabel("<HTML> <center> Level 4 <br> Step Up </center> <HTML>");
		
		rightPanel.setFont(mainMenuFont);
		rightPanel.setForeground(Color.WHITE);
		rightPanel.setPreferredSize(preferredSize);
		rightPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));

		leftPanel.setFont(mainMenuFont);
		leftPanel.setForeground(Color.WHITE);
		leftPanel.setPreferredSize(preferredSize);
		leftPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		
		midPanel.setFont(mainMenuFont);
		midPanel.setForeground(Color.WHITE);
		midPanel.setPreferredSize(preferredSize);
		midPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		
		mid2Panel.setFont(mainMenuFont);
		mid2Panel.setForeground(Color.WHITE);
		mid2Panel.setPreferredSize(preferredSize);
		mid2Panel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		
		mainMenuFont = mainMenuFont.deriveFont(30f);
		GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(mainMenuFont);

		rightLabel.setForeground(Color.WHITE);
		leftLabel.setForeground(Color.WHITE);

		midLabel.setForeground(Color.WHITE);
		mid2Label.setForeground(Color.WHITE);
		
		rightLabel.setFont(mainMenuFont);
		leftLabel.setFont(mainMenuFont);
		
		midLabel.setFont(mainMenuFont);
		mid2Label.setFont(mainMenuFont);
		
		rightPanel.add(rightLabel);
		leftPanel.add(leftLabel);

		midPanel.add(midLabel);
		mid2Panel.add(mid2Label);
		
		allPanel.add(rightPanel);
		allPanel.add(leftPanel);
		
		allPanel.add(midPanel);
		allPanel.add(mid2Panel);

		buttonsPanel.add(goBackButton);

		PanelLevel.add(allPanel, BorderLayout.NORTH);
		PanelLevel.add(buttonsPanel, BorderLayout.SOUTH);

		goBackButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				mainMenuFrame.setVisible(true);
			}

			public void mouseEntered(MouseEvent e) {
				goBackButton.setForeground(Color.RED);
				GameEngineAudioHandler.getInstance().makeClickSound();

			}

			public void mouseExited(MouseEvent e) {
				goBackButton.setForeground(Color.WHITE);
			}
		});

		rightPanel.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				GameEngineData.setLevel(1);
				int level = 1;

				FrameEdit ew = new FrameEdit(mainMenuFrame, level, clip, aiMode);
				ew.setVisible(true);
				dispose();

			}

			public void mouseEntered(MouseEvent e) {
				rightLabel.setForeground(new Color(68, 181, 139));
				rightPanel.setBackground(new Color(22, 22, 22));
				GameEngineAudioHandler.getInstance().makeClickSound();

			}

			public void mouseExited(MouseEvent e) {
				rightLabel.setForeground(Color.WHITE);
				rightPanel.setBackground(Color.BLACK);
			}

		});

		leftPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);

				int level = 2;
				GameEngineData.setLevel(2);
				FrameEdit ew = new FrameEdit(mainMenuFrame,level,clip, aiMode);
				ew.setVisible(true);
				dispose();
			}

			public void mouseEntered(MouseEvent e) {
				leftLabel.setForeground(new Color(68, 126, 181));
				leftPanel.setBackground(new Color(22, 22, 22));
				GameEngineAudioHandler.getInstance().makeClickSound();

			}

			public void mouseExited(MouseEvent e) {
				leftLabel.setForeground(Color.WHITE);
				leftPanel.setBackground(Color.BLACK);
			}

		});
		
		midPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);

				int level = 3;
				GameEngineData.setLevel(2);
				FrameEdit ew = new FrameEdit(mainMenuFrame,level,clip, aiMode);
				ew.setVisible(true);
				dispose();
			}

			public void mouseEntered(MouseEvent e) {
				midLabel.setForeground(new Color(175, 175, 175));
				midPanel.setBackground(new Color(22, 22, 22));
				GameEngineAudioHandler.getInstance().makeClickSound();

			}

			public void mouseExited(MouseEvent e) {
				midLabel.setForeground(Color.WHITE);
				midPanel.setBackground(Color.BLACK);
			}

		});
		
		mid2Panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);

				int level = 4;
				GameEngineData.setLevel(2);
				FrameEdit ew = new FrameEdit(mainMenuFrame,level,clip, aiMode);
				ew.setVisible(true);
				dispose();
			}

			public void mouseEntered(MouseEvent e) {
				mid2Label.setForeground(new Color(245, 108, 252));
				mid2Panel.setBackground(new Color(22, 22, 22));
				GameEngineAudioHandler.getInstance().makeClickSound();

			}

			public void mouseExited(MouseEvent e) {
				mid2Label.setForeground(Color.WHITE);
				mid2Panel.setBackground(Color.BLACK);
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
		label.setPreferredSize(scrollSize);
		label.setBorder(BorderFactory.createLineBorder(Color.WHITE));
	}
}
