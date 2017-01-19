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
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import domain.gameengine.GameEngineAudioHandler;
import domain.gameengine.GameEngineData;
import domain.xmloperations.XMLSaveEdit;
import domain.xmloperations.XMLSaveGame;

/**
 * The Class FrameSave.
 */
public class FrameError extends JFrame {
	
	/** The panel level. */
	JPanel panelLevel;

	/** The preferred size. */
	private Dimension preferredSize;
	
	/** The scroll size. */
	private Dimension scrollSize;
	
	/**
	 * Instantiates a new frame save.
	 *
	 * @param mode the mode
	 * @throws FontFormatException the font format exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public FrameError() throws FontFormatException, IOException{
		
		super("Level");
		
		panelLevel = new JPanel();
		panelLevel.setSize(300, 200);
		panelLevel.setBackground(Color.BLACK);
		panelLevel.setLayout(new BorderLayout());
		preferredSize = new Dimension(500,50);

		this.setSize(300, 200);
		this.setBackground(Color.BLACK);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.setUndecorated(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		panelLevel.setBorder(BorderFactory.createMatteBorder(10,10,10,10, Color.red));
		ImageIcon img = new ImageIcon("asset/images/icon.png");
		this.setIconImage(img.getImage());
		
		addContent();
		add(panelLevel, BorderLayout.CENTER);
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

		JLabel goBackButton = new JLabel("GO BACK");
		goBackButton.setFont(mainMenuFont);
		goBackButton.setHorizontalAlignment(JLabel.CENTER);
		goBackButton.setForeground(Color.WHITE);
		goBackButton.setPreferredSize(preferredSize);
		goBackButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		
		buttonsPanel.add(goBackButton);
		
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new GridLayout(2,0));
		textPanel.setBackground(Color.BLACK);
		
		JLabel enterFileName = new JLabel("ERROR INVALID-XML");
		enterFileName.setFont(mainMenuFont);
		enterFileName.setHorizontalAlignment(JLabel.CENTER);
		enterFileName.setForeground(Color.RED);
		enterFileName.setPreferredSize(preferredSize);
		
		Font settingMainText = Font.createFont(Font.TRUETYPE_FONT, new File("asset/fonts/mainFont4.ttf"));
		settingMainText = settingMainText.deriveFont(40f);
		
		textPanel.add(enterFileName);
	
		panelLevel.add(textPanel, BorderLayout.NORTH);
		panelLevel.add(buttonsPanel, BorderLayout.SOUTH);

		goBackButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);		
			}

			public void mouseEntered(MouseEvent e) {
				goBackButton.setForeground(Color.RED);
				GameEngineAudioHandler.getInstance().makeClickSound();
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
		label.setPreferredSize(scrollSize);
		label.setBorder(BorderFactory.createLineBorder(Color.WHITE));
	}
}
