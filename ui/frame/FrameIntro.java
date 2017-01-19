package ui.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import domain.xmloperations.XMLReadSettings;

/**
 * The Class FrameIntro.
 */
public class FrameIntro extends JFrame{

	/** The instance. */
	private static FrameIntro instance = new FrameIntro();
	
	/** The panel intro. */
	private JPanel panelIntro;
	
	/** The preferred size. */
	private Dimension preferredSize;
	
	/** The stream. */
	private AudioInputStream stream;
	
	/** The format. */
	private AudioFormat format;
	
	/** The info. */
	private DataLine.Info info;
	
	/** The clip. */
	private Clip clip;
	
	/** The mute pic. */
	private BufferedImage mutePic;
	
	/** The pass flag. */
	private Boolean passFlag = false;

	/**
	 * Gets the single instance of FrameIntro.
	 *
	 * @return single instance of FrameIntro
	 */
	public static FrameIntro getInstance(){
		return instance;
	}

	/**
	 * Instantiates a new frame intro.
	 */
	private FrameIntro(){
		super("Animation Window");
	}

	/**
	 * Start game.
	 *
	 * @throws ParserConfigurationException the parser configuration exception
	 * @throws SAXException the SAX exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void startGame() throws ParserConfigurationException, SAXException, IOException{

		XMLReadSettings xml = new XMLReadSettings();
		
		panelIntro = new JPanel();
		panelIntro.setSize(500, 500);
		panelIntro.setBackground(Color.BLACK);
		panelIntro.setLayout(new BorderLayout());
		preferredSize = new Dimension(500,50);
		this.setSize(500, 500);
		this.setBackground(Color.BLACK);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.setUndecorated(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		addContent();
		ImageIcon img = new ImageIcon("asset/images/icon.png");
		this.setIconImage(img.getImage());
		add(panelIntro, BorderLayout.CENTER);
		setVisible(true);

		try{
			File yourFile = new File("asset/audio/intro3.wav");

			stream = AudioSystem.getAudioInputStream(yourFile);
			format = stream.getFormat();
			info = new DataLine.Info(Clip.class, format);
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(stream);
			clip.setMicrosecondPosition(33000000);
			clip.start();

		}catch(Exception e){}

		for (int i = 0; i < 15000; i++) {
			try {
				TimeUnit.MICROSECONDS.sleep(1);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			if(passFlag){
				break;
			}
		}

		try {
			FrameMainMenu mm = new FrameMainMenu();
			
			clip.stop();
			setVisible(false);
		}catch (Exception e) {}
	}

	/**
	 * Adds the content.
	 */
	public void addContent(){

		try {
			mutePic = ImageIO.read(new File("asset/images/ray.png"));
		} catch (Exception e) {}

		JPanel buttonsPanel = new JPanel();
		JPanel topPanel = new JPanel();
		topPanel.setBackground(Color.BLACK);
		GridLayout buttonsLayout = new GridLayout(0,1);
		buttonsLayout.setVgap(0);
		buttonsPanel.setLayout(buttonsLayout);
		topPanel.setLayout(buttonsLayout);
		buttonsPanel.setBackground(Color.BLACK);
		panelIntro.add(buttonsPanel, BorderLayout.SOUTH);
		panelIntro.add(topPanel, BorderLayout.NORTH);
		JLabel muteLabel = new JLabel(new ImageIcon(mutePic));
		muteLabel.setHorizontalAlignment(JLabel.CENTER);
		muteLabel.setBackground(Color.BLACK);
		buttonsPanel.add(muteLabel);

		muteLabel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				clip.stop();
				passFlag = true;
				setVisible(false); 
				dispose(); 
			}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		});
	}
}
