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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import domain.gameengine.GameEngineAudioHandler;
import domain.xmloperations.XMLLoadEditGame;

public class FrameLoad extends JFrame implements ActionListener {
	JPanel panelSettings;
	JFrame mainMenuFrame;
	Dimension preferredSize;
	Dimension scrollSize;
	Clip upperFameClip;

	private String selectedFileName;
	private GridLayout scrollLayOut;
	private JPanel scrollLoadPanel;
	private boolean aiMode = true;

	public FrameLoad(JFrame mainMenuFrame, Clip upperFameClip, boolean aiMode) throws FontFormatException, IOException{

		super("Load Window");
		this.aiMode = aiMode;
		this.mainMenuFrame = mainMenuFrame;
		this.upperFameClip = upperFameClip;
		panelSettings = new JPanel();
		panelSettings.setSize(500, 500);
		panelSettings.setBackground(Color.BLACK);
		panelSettings.setLayout(new BorderLayout());
		preferredSize = new Dimension(500,50);
		this.setSize(500, 500);
		this.setBackground(Color.BLACK);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.setUndecorated(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		ImageIcon img = new ImageIcon("asset/images/icon.png");
		this.setIconImage(img.getImage());
		Timer time = new Timer(1,this);
		time.start();

		addButtonOp();
		add(panelSettings, BorderLayout.CENTER);
	}

	public void addButtonOp()  throws FontFormatException, IOException {

		Font mainMenuFont = Font.createFont(Font.TRUETYPE_FONT, new File("asset/fonts/mainFont4.ttf"));
		mainMenuFont = mainMenuFont.deriveFont(20f);

		JPanel buttonsPanel = new JPanel();
		GridLayout buttonsLayout = new GridLayout(0,3);
		buttonsLayout.setVgap(0);
		buttonsPanel.setLayout(buttonsLayout);
		buttonsPanel.setBackground(Color.BLACK);

		JLabel goBackButton = new JLabel("Main Menu");
		goBackButton.setFont(mainMenuFont);
		goBackButton.setHorizontalAlignment(JLabel.CENTER);
		goBackButton.setForeground(Color.WHITE);
		goBackButton.setPreferredSize(preferredSize);
		goBackButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));

		JLabel deleteButton = new JLabel("Delete");
		deleteButton.setFont(mainMenuFont);
		deleteButton.setHorizontalAlignment(JLabel.CENTER);
		deleteButton.setForeground(Color.WHITE);
		deleteButton.setPreferredSize(preferredSize);
		deleteButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));

		JLabel startButton = new JLabel("Start");
		startButton.setFont(mainMenuFont);
		startButton.setHorizontalAlignment(JLabel.CENTER);
		startButton.setForeground(Color.WHITE);
		startButton.setPreferredSize(preferredSize);
		startButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));

		JPanel mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(400,400));

		scrollLoadPanel = new JPanel();

		scrollLayOut = new GridLayout(0,1);
		scrollLoadPanel.setBackground(Color.BLACK);
		scrollLoadPanel.setLayout(scrollLayOut);

		buttonsPanel.add(goBackButton);
		buttonsPanel.add(deleteButton);
		buttonsPanel.add(startButton);

		File folder = new File("gamedata/userdata");
		File[] listOfFiles = folder.listFiles();

		ArrayList<JLabel> buttonList = new ArrayList<JLabel>();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				

				if(!listOfFiles[i].getName().equals("init.jazzme")){
					String string = listOfFiles[i].getName();
					String[] parts = string.split(Pattern.quote("."));
					String name = parts[0];
					buttonList.add(new JLabel(name));
				}
			} else if (listOfFiles[i].isDirectory()) {
				
			}
		}

		for (int i = 0; i < buttonList.size(); i++) {	
			styler(buttonList.get(i));	
		}

		for (int i = 0; i < buttonList.size(); i++) {
			scrollLoadPanel.add(buttonList.get(i));
		}

		panelSettings.add(scrollLoadPanel, BorderLayout.NORTH);
		panelSettings.add(scrollLoadPanel);		
		panelSettings.add(buttonsPanel, BorderLayout.SOUTH);

		for(int i = 0; i < buttonList.size(); i++){	
			JLabel k = buttonList.get(i);	
			buttonList.get(i).addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {		
					selectedFileName =	k.getText();	
					
					k.setForeground(Color.RED);	

					for (int j = 0; j < buttonList.size(); j++) {
						if(!k.equals(buttonList.get(j))){
							buttonList.get(j).setForeground(Color.WHITE);

						}
					}
				}

				public void mouseEntered(MouseEvent e) {
					GameEngineAudioHandler.getInstance().makeClickSound();
				}

				public void mouseExited(MouseEvent e) {

				}
			});
		}

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

		deleteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				File myFile = new File("gamedata/userdata/" + selectedFileName+ ".xml");
				
				for (int j = 0; j < buttonList.size(); j++) {
					if((buttonList.get(j).getText() + ".xml").equals(myFile.getName())){
						scrollLoadPanel.remove(buttonList.get(j));
						buttonList.remove(j);				
					}
				}
				myFile.delete();
				scrollLoadPanel.revalidate();
				scrollLoadPanel.repaint();
			}

			public void mouseEntered(MouseEvent e) {
				deleteButton.setForeground(Color.RED);
				GameEngineAudioHandler.getInstance().makeClickSound();
			}

			public void mouseExited(MouseEvent e) {
				deleteButton.setForeground(Color.WHITE);
			}
		});

		startButton.addMouseListener(new MouseAdapter() {
			

			@Override
			public void mouseClicked(MouseEvent e) {
				
					XMLLoadEditGame loader = null;
					try {
						loader = new XMLLoadEditGame(selectedFileName);
					
					if(loader.getMode() == 1){
						try {
							loader.createEditWorld();
						} catch (SAXException | IOException | ParserConfigurationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						FrameEdit fe = new FrameEdit(mainMenuFrame, loader.getLevel(), upperFameClip, aiMode);
						upperFameClip.stop();
						fe.setVisible(true);
						setVisible(false);
					}else if(loader.getMode() == 2){
						loader.createPlayWorld();	
						FrameGame fe = new FrameGame(mainMenuFrame, upperFameClip, loader.getLevel(), aiMode);
						upperFameClip.stop();
						fe.setVisible(true);
						setVisible(false);
					}
				
					
					} catch (ParserConfigurationException | SAXException | IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
						try {
							FrameError fe = new FrameError();
							fe.setVisible(true);
								
						} catch (FontFormatException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				
			}

			public void mouseEntered(MouseEvent e) {
				startButton.setForeground(Color.RED);
				GameEngineAudioHandler.getInstance().makeClickSound();
			}

			public void mouseExited(MouseEvent e) {
				startButton.setForeground(Color.WHITE);
			}
		});
	}

	public void styler(JLabel label) throws FontFormatException, IOException{
		Font mainMenuFont = Font.createFont(Font.TRUETYPE_FONT, new File("asset/fonts/mainFont4.ttf"));
		mainMenuFont = mainMenuFont.deriveFont(20f);
		label.setFont(mainMenuFont);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setForeground(Color.WHITE);
		label.setPreferredSize(scrollSize);
		label.setBorder(BorderFactory.createLineBorder(Color.WHITE));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		scrollLoadPanel.repaint();
	}
}
