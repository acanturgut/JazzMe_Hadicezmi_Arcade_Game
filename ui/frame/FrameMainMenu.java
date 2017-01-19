package ui.frame;
import java.awt.BorderLayout;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.awt.FontFormatException;

import java.io.IOException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.Border;

import domain.gameengine.GameEngineAudioHandler;

/**
 * The Class FrameMainMenu.
 */
public class FrameMainMenu {

	/** The frame. */
	private JFrame frame;
	
	/** The main menu main panel. */
	private JPanel mainMenuMainPanel;	
	
	/** The play. */
	private JLabel play;
	
	/** The settings. */
	private JLabel settings;
	
	/** The load. */
	private JLabel load;
	
	/** The exit. */
	private JLabel exit;
	
	/** The preferred size. */
	private Dimension preferredSize;
	
	/** The dim. */
	private Dimension dim;
	
	/** The sw. */
	private FrameSettings sw;
	
	/** The un mute pic. */
	private BufferedImage unMutePic;
	
	/** The main menu font. */
	private Font mainMenuFont;
	
	/** The stream. */
	private AudioInputStream stream;
	
	/** The format. */
	private AudioFormat format;
	
	/** The info. */
	private DataLine.Info info;
	
	/** The clip. */
	private Clip clip;
		
	/** The door stream. */
	private AudioInputStream doorStream;
	
	/** The door format. */
	private AudioFormat doorFormat;
	
	/** The door info. */
	private DataLine.Info doorInfo;
	
	/** The door clip. */
	private Clip doorClip;
	
	/** The music flag. */
	private Boolean musicFlag = true;

	private JLabel ai;

	/**
	 * Instantiates a new frame main menu.
	 */
	public FrameMainMenu(){

		try{
			File yourFile = new File("asset/audio/intro.wav");
			stream = AudioSystem.getAudioInputStream(yourFile);
			format = stream.getFormat();
			info = new DataLine.Info(Clip.class, format);
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(stream);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.start();

		}catch(Exception e){}

		frame = new JFrame("Main Menu");
		mainMenuMainPanel = new JPanel();
		mainMenuMainPanel.setSize(500, 500);
		mainMenuMainPanel.setBackground(Color.BLACK);
		mainMenuMainPanel.setLayout(new BorderLayout());
		mainMenuMainPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		ImageIcon img = new ImageIcon("asset/images/icon.png");
		frame.setIconImage(img.getImage());
		preferredSize = new Dimension(500,50);
		frame.setSize(500, 500);
		frame.setBackground(Color.BLACK);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setUndecorated(true);
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		
		try{
			addItem();
		}catch(Exception e){

		}

		frame.add(mainMenuMainPanel, BorderLayout.CENTER);
		frame.setVisible(true);	
	}

	/**
	 * Adds the item.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws FontFormatException the font format exception
	 */
	public void addItem() throws IOException, FontFormatException{

		mainMenuFont = Font.createFont(Font.TRUETYPE_FONT, new File("asset/fonts/mainFont4.ttf"));
		mainMenuFont = mainMenuFont.deriveFont(30f);

		JPanel buttonsPanel = new JPanel();
		JPanel topPanel = new JPanel();
		topPanel.setBackground(Color.BLACK);
		GridLayout buttonsLayout = new GridLayout(0,1);
		
		buttonsLayout.setVgap(0);
		buttonsPanel.setLayout(buttonsLayout);
		topPanel.setLayout(buttonsLayout);
		buttonsPanel.setBackground(Color.BLACK);
		
		mainMenuMainPanel.add(buttonsPanel, BorderLayout.SOUTH);
		mainMenuMainPanel.add(topPanel, BorderLayout.NORTH);

		BufferedImage mutePic = ImageIO.read(new File("asset/images/unmute.png"));
		unMutePic = ImageIO.read(new File("asset/images/mute.png"));

		JLabel muteLabel = new JLabel(new ImageIcon(mutePic));
		muteLabel.setHorizontalAlignment(JLabel.CENTER);
		muteLabel.setBackground(Color.BLACK);

		BufferedImage startPic = ImageIO.read(new File("asset/images/start.png"));

		JLabel startLabel = new JLabel(new ImageIcon(startPic));
		startLabel.setHorizontalAlignment(JLabel.RIGHT);
		startLabel.setBackground(Color.BLACK);
		startLabel.setMaximumSize(new Dimension(50, 100));
		topPanel.add(startLabel);
		
		ai = new JLabel("Single Player");
		buttonStyle(ai);
		buttonsPanel.add(ai);
		
		play = new JLabel("Multi Player");
		buttonStyle(play);
		buttonsPanel.add(play);

		load = new JLabel("Load Game");
		buttonStyle(load);
		buttonsPanel.add(load);

		settings = new JLabel("Settings");
		buttonStyle(settings);
		buttonsPanel.add(settings);

		exit = new JLabel("Exit");
		buttonStyle(exit);
		buttonsPanel.add(exit);

		buttonsPanel.add(muteLabel);

		muteLabel.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(musicFlag){ 
					muteLabel.setIcon(new ImageIcon(unMutePic));
					clip.stop();
					musicFlag = false;
				}else{
					muteLabel.setIcon(new ImageIcon(mutePic));
					clip.start();
					musicFlag = true;
				}
			}

			public void mouseEntered(MouseEvent e) {
				
				muteLabel.setIcon(new ImageIcon(unMutePic));
				GameEngineAudioHandler.getInstance().makeClickSound();
				
			}

			public void mouseExited(MouseEvent e) {
				muteLabel.setIcon(new ImageIcon(mutePic));
			}

		});

		buttonsPanel.add(muteLabel);

		play.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				makeOpenSound();
				try{
					FrameLevelSelect ew = new FrameLevelSelect(frame,clip, false);
					ew.setVisible(true);
					frame.setVisible(false);
					clip.stop();
				}catch(Exception e3){}
			}

			public void mouseEntered(MouseEvent e) {
				play.setForeground(Color.RED);
				GameEngineAudioHandler.getInstance().makeClickSound();
			}

			public void mouseExited(MouseEvent e) {
				play.setForeground(Color.WHITE);
			}
		});
		
		ai.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				makeOpenSound();
				try{
					FrameLevelSelect ew = new FrameLevelSelect(frame,clip, true);
					ew.setVisible(true);
					frame.setVisible(false);
					clip.stop();
				}catch(Exception e3){

				}
			}

			public void mouseEntered(MouseEvent e) {
				ai.setForeground(Color.RED);
				GameEngineAudioHandler.getInstance().makeClickSound();
			}

			public void mouseExited(MouseEvent e) {
				ai.setForeground(Color.WHITE);
			}
		});


		load.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				makeOpenSound();
				try {
					FrameLoad lw = new FrameLoad(frame,clip,false);
					lw.setVisible(true);
				} catch (Exception e1) {
				}
				frame.setVisible(false);
			}

			public void mouseEntered(MouseEvent e) {
				load.setForeground(Color.RED);
				GameEngineAudioHandler.getInstance().makeClickSound();
			}

			public void mouseExited(MouseEvent e) {
				load.setForeground(Color.WHITE);
			}

		});

		settings.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					sw = new FrameSettings(frame);
					makeOpenSound();
					sw.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
					sw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(false);
					sw.setVisible(true);
				} catch (Exception e2) {}
			}

			public void mouseEntered(MouseEvent e) {
				settings.setForeground(Color.RED);
				GameEngineAudioHandler.getInstance().makeClickSound();
			}

			public void mouseExited(MouseEvent e) {
				settings.setForeground(Color.WHITE);
			}

		});

		exit.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}

			public void mouseEntered(MouseEvent e) {
				exit.setForeground(Color.RED);
				GameEngineAudioHandler.getInstance().makeClickSound();
			}

			public void mouseExited(MouseEvent e) {
				exit.setForeground(Color.WHITE);
			}
		});
	}


	/**
	 * Make open sound.
	 */
	public void makeOpenSound(){
		
		File doorSound = new File("asset/audio/open.wav");

		try{
			doorStream = AudioSystem.getAudioInputStream(doorSound);
			doorFormat = doorStream.getFormat();
			doorInfo = new DataLine.Info(Clip.class, doorFormat);
			doorClip = (Clip) AudioSystem.getLine(doorInfo);
			doorClip.open(doorStream);
			doorClip.start();
		}catch(Exception e){}
	}
	
	/**
	 * Button style.
	 *
	 * @param label the label
	 */
	public void buttonStyle(JLabel label){
		
		label.setFont(mainMenuFont);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setForeground(Color.WHITE);
		label.setPreferredSize(preferredSize);
		label.setBorder(BorderFactory.createLineBorder(Color.WHITE));		
	}
}