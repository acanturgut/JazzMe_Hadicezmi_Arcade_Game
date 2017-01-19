package ui.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import domain.gameengine.GameEngineAudioHandler;
import domain.gameengine.GameEngineData;
import domain.gameengine.GameEngineGizmoFactory;
import domain.item.EngineBall;
import domain.item.EngineBorder;
import domain.item.EngineEngel;
import ui.painter.UIPainterEdit;

/**
 * The Class PanelEdit.
 */
@SuppressWarnings("serial")
public class PanelEdit extends JPanel implements MouseMotionListener, MouseListener, ActionListener, KeyListener{

	// PANEL RELATED VAR

	/** The window size. */
	private int windowSize = 500; 

	/** The window size part. */
	private int windowSizePart = 500/25;

	/** The edit painter. */
	private UIPainterEdit editPainter;

	/** The game frame. */
	private FrameEdit gameFrame;

	// PLAYER STATE AND GIZMO COUNT

	/** The is player 1 s turn. */
	private Boolean isPlayer1sTurn = true;

	/** The player 1 gizmo count. */
	private int player1GizmoCount = 0;

	/** The player 2 gizmo count. */
	private int player2GizmoCount = 0;

	// OUT OF BOX INITILIZE to GHOST GIZMOS

	/** The x takoz selecter. */
	private int xTakozSelecter = 10000; 

	/** The y takoz selecter. */
	private int yTakozSelecter = 10000;

	/** The tokat X rec. */
	private int tokatXRec = 10000;

	/** The tokat Y rec. */
	private int tokatYRec = 10000;

	/** The tokat oval 1 X. */
	private int tokatOval1X = 10000;

	/** The tokat oval 2 X. */
	private int tokatOval2X = 10000;

	/** The tokat oval 1 y. */
	private int tokatOval1y = 10000;

	/** The tokat oval 2 y. */
	private int tokatOval2y = 10000;

	/** The x 1 tri angle. */
	private int x1TriAngle = 10000; 

	/** The x 2 tri angle. */
	private int x2TriAngle = 10000;

	/** The x 3 tri angle. */
	private int x3TriAngle = 10000 + windowSize/25;

	/** The y 1 tri angle. */
	private int y1TriAngle = 10000;

	/** The y 2 tri angle. */
	private int y2TriAngle = 10000 + windowSize/25;

	/** The y 3 tri angle. */
	private int y3TriAngle = 10000 + windowSize/25;

	// GHOST TRIANGLE GIZMOS

	/** The triangle. */
	private Polygon triangle;

	// MOVE 

	/** The state triangle selector. */
	private int stateTriangleSelector = 1;

	/** The state tokat selector. */
	private int stateTokatSelector = 1;

	/** The dragged item. */
	private String draggedItem = "";

	/** The is item dragged. */
	private Boolean isItemDragged = false;

	/** The holded item index. */
	private int holdedItemIndex = 0;

	// ROTATION STATE

	/** The rotate state. */
	private Boolean rotateState = false;

	// GIZMO MOVEMENT 

	/** The gizmo is selected. */
	private Boolean gizmoIsSelected = true;

	/** The level. */
	private int level;

	// CURSOR

	/** The blank cursor. */
	Cursor blankCursor;

	private boolean aiMode;

	// CONSTRUCTOR

	/**
	 * Instantiates a new panel edit.
	 *
	 * @param windowSize the window size
	 * @param gameFrame the game frame
	 * @param level the level
	 * @throws FontFormatException the font format exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public PanelEdit(int windowSize, FrameEdit gameFrame, int level, Boolean aiMode) throws FontFormatException, IOException{ 

		super();
		this.aiMode = aiMode;
		this.level = level;
		this.windowSize = windowSize; // TAKEN FROM XML FROM UPPER LAYER 
		this.gameFrame = gameFrame;   // GAME FRAME (UPPER LAYER)

		// MOUSE CURSOR SETTINGS

		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");

		// SAVE CEZMI, BALL, ENGEL PROP and LOCATIONS

		GameEngineData.getCezmi1().setL(windowSize);
		GameEngineData.getCezmi2().setL(windowSize);

		GameEngineData.getCezmi1().setX(windowSize/4 - windowSize/25);
		GameEngineData.getCezmi2().setX(windowSize/4 + windowSize/2  - windowSize/25);

		GameEngineData.getCezmi1().setY(windowSize - windowSize/25);
		GameEngineData.getCezmi2().setY(windowSize - windowSize/25);

		if(level == 1){
			GameEngineData.addBall(new EngineBall(windowSize/2- windowSize / 50,windowSize/2- windowSize / 50,(windowSize/50)));
			
		}else if(level == 2){
			GameEngineData.addBall(new EngineBall(windowSize/2- windowSize / 50,windowSize/2- windowSize / 50,windowSize/50));
			GameEngineData.addBall(new EngineBall(windowSize/2- windowSize / 50,windowSize / 2 - windowSize / 50 - windowSize / 25 - windowSize / 25,windowSize/50));
		}else if(level == 3){
			GameEngineData.addBall(new EngineBall(windowSize/2- windowSize / 50,windowSize/2- windowSize / 50,windowSize/50));
			GameEngineData.addBall(new EngineBall(windowSize/2- windowSize / 50,windowSize / 2 - windowSize / 50 - windowSize / 25 - windowSize / 25,windowSize/50));	
			GameEngineData.addBall(new EngineBall(windowSize/2- windowSize / 50,windowSize/2- windowSize / 50,windowSize/50));
			GameEngineData.addBall(new EngineBall(windowSize/2- windowSize / 50,windowSize / 2 - windowSize / 50 - windowSize / 25 - windowSize / 25,windowSize/50));
		
		}else if(level == 4){
			GameEngineData.addBall(new EngineBall(windowSize/2- windowSize / 50,windowSize/2- windowSize / 50,windowSize/50));
			GameEngineData.addBall(new EngineBall(windowSize/2- windowSize / 50,windowSize / 2 - windowSize / 50 - windowSize / 25 - windowSize / 25,windowSize/50));
			GameEngineData.addBall(new EngineBall(windowSize/2- windowSize / 50,windowSize/2- windowSize / 50,windowSize/50));
			GameEngineData.addBall(new EngineBall(windowSize/2- windowSize / 50,windowSize / 2 - windowSize / 50 - windowSize / 25 - windowSize / 25,windowSize/50));
			GameEngineData.addBall(new EngineBall(windowSize/2- windowSize / 50,windowSize/2- windowSize /  50,windowSize/50));
			GameEngineData.addBall(new EngineBall(windowSize/2- windowSize / 50,windowSize / 2 - windowSize / 50 - windowSize / 25 - windowSize / 25,windowSize/50));
			GameEngineData.addBall(new EngineBall(windowSize/2- windowSize / 50,windowSize/2- windowSize / 50,windowSize/50));
			GameEngineData.addBall(new EngineBall(windowSize/2- windowSize / 50,windowSize / 2 - windowSize / 50 - windowSize / 25 - windowSize / 25,windowSize/50));	
		}

		GameEngineData.setEngel(new EngineEngel((windowSize/2) - (windowSize/200), windowSize - (3 * (windowSize / 25)), windowSize/100 , 3*(windowSize/25)));		
		
		GameEngineData.setBorder(new EngineBorder(windowSize));
		// SIZE OF THE PANEL

		setPreferredSize(new Dimension (windowSize,windowSize)); 
		editPainter = new UIPainterEdit(windowSize);

		// LISTENERS RELATED INS

		addMouseMotionListener(this); 
		addMouseListener(this);
		addKeyListener(this);
		requestFocus();  
		setFocusable(true);

		// TIMER RELATED INS

		Timer timer = new Timer(1, this); 
		timer.start();

		// PANEL DIVISION - COLORFULLY

		JPanel rightPanel = new JPanel(); 
		JPanel leftPanel = new JPanel();  

		JPanel allPanel = new JPanel ();
		GridLayout k = new GridLayout(0, 2);
		BorderLayout bl = new BorderLayout();

		allPanel.setLayout(k);
		this.setLayout(bl);
		this.setBackground(new Color(20, 20, 20));
		allPanel.setPreferredSize(new Dimension(windowSize,windowSize-(6* (windowSize/25))));

		rightPanel.setBackground(new Color(33, 33, 33)); // SET COLOR HERE RIGHT PANEL
		leftPanel.setBackground(new Color(50, 51, 50));  // SET COLOR HERE LEFT  PANEL

		rightPanel.setLayout(new GridBagLayout());
		leftPanel.setLayout(new GridBagLayout());

		JLabel rightLabel = new JLabel("<HTML> <center> Player 1 <br> Add Gizmo </center> <HTML>");
		JLabel leftLabel = new JLabel("<HTML> <center> Player 2 <br> Add Gizmo </center> <HTML>");

		Font mainMenuFont = Font.createFont(Font.TRUETYPE_FONT, new File("asset/fonts/mainFont4.ttf"));

		mainMenuFont = mainMenuFont.deriveFont(30f);
		GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(mainMenuFont);

		rightLabel.setForeground(Color.GRAY);
		leftLabel.setForeground(Color.GRAY);

		rightLabel.setFont(mainMenuFont);
		leftLabel.setFont(mainMenuFont);

		rightPanel.add(rightLabel);
		leftPanel.add(leftLabel);

		allPanel.add(rightPanel);
		allPanel.add(leftPanel);

		// TRIANGLE GIZMO RELATED POLYGON INITILIZED 

		triangle = new Polygon();
		this.add(allPanel,BorderLayout.NORTH);
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */

	public void paint(Graphics g){ 
		super.paint(g); 

		windowSizePart = windowSize / 25 ;	
		Graphics2D g2 = (Graphics2D) g;

		// KEY_ANTIALIASING IS ON

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON); 

		// PAINT LINES

		editPainter.paintLines(g2);

		// PAINT GHOST BALL 

		if(GameEngineData.getLevel() == 1){
			g2.setColor(Color.WHITE);
			g2.fillOval( windowSize / 2 - windowSize / 50,  windowSize / 2 - windowSize / 50 ,  windowSize / 25,  windowSize / 25);

		}else if(GameEngineData.getLevel() == 2){
			g2.setColor(Color.WHITE);
			g2.fillOval( windowSize / 2 - windowSize / 50,  windowSize / 2 - windowSize / 50 ,  windowSize / 25,  windowSize / 25);	
			g2.fillOval( windowSize / 2 - windowSize / 50,  windowSize / 2 - windowSize / 50 - windowSize / 25 - windowSize / 25 ,  windowSize / 25,  windowSize / 25);	
		}

		// GHOST GIZMO PAINTING  

		if(gameFrame.getSelectedItem().equals("SQUARETAKOZ")){ // SQUARE TAKOZ GHOST
			Color myColour = new Color(0, 135, 81, 100);
			g2.setColor(myColour);
			g2.fillRect(xTakozSelecter, yTakozSelecter, windowSizePart, windowSizePart);
		}

		if(gameFrame.getSelectedItem().equals("FIRILDAK")){ // FIRILDAK GHOST
			Color myColour = new Color(91, 186, 173, 100);
			g2.setColor(myColour);
			g2.fillRect(xTakozSelecter, yTakozSelecter, windowSizePart, windowSizePart);
		}

		if(gameFrame.getSelectedItem().equals("TRIANGULARTAKOZ")){ // TRIANGULAR TAKOZ GHOST
			triangle.addPoint(x1TriAngle, y1TriAngle);
			triangle.addPoint(x2TriAngle, y2TriAngle);
			triangle.addPoint(x3TriAngle, y3TriAngle);
			g2.setColor(new Color(204, 59, 2, 100));
			g2.fillPolygon(triangle);
			triangle.reset();
		}

		if(gameFrame.getSelectedItem().equals("TOKAT")){ // TOKAT GHOST
			Color myColour = new Color(255, 195, 33, 100);
			g2.setColor(myColour);
			g2.fillOval(tokatOval1X, tokatOval1y, windowSizePart/2, windowSizePart/2);
			g2.fillOval(tokatOval2X, tokatOval2y, windowSizePart/2, windowSizePart/2);
			g2.fillRect(tokatXRec, tokatYRec, windowSizePart/2, (windowSizePart/2 + windowSizePart/4) * 2);
		}

		// EDIT PAINTER RELATED PAINT

		editPainter.paintGhostGizmos(g2);
		editPainter.paintEngel(g2);
		editPainter.paint(g2);
		editPainter.firildakPaint(g2);
		editPainter.tokatPaint(g2);

		// INFO TABLE PAINT 

		g2.setPaint(new Color(255, 255, 255, 160));

		g2.setColor(new Color(232, 232, 232));

		if(windowSize == 900){

			Font f = null;
			try {
				f = Font.createFont(Font.TRUETYPE_FONT, new File("asset/fonts/mainFont4.ttf"));
			} catch (FontFormatException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}
			f = f.deriveFont(22f);

			g2.setFont(f);

			String k  = "Player 1 Gizmo Left:  " + GameEngineData.getPlayer1().getGizmoCount();

			g2.drawString(k, 40, windowSize-170);

			String k2 = "Player 2 Gizmo Left: " + GameEngineData.getPlayer2().getGizmoCount();		

			g2.drawString(k2, 40, windowSize- 130 );

			String k3 = "Click to drop gizmo";

			g2.drawString(k3, windowSize/2 + 40, windowSize - 180);

			String k4 = "Drag to move gizmo ";		

			g2.drawString(k4, windowSize/2 + 40, windowSize - 140 );

			String k5 = "Double click to rotate gizmo ";		

			g2.drawString(k5, windowSize/2 + 40, windowSize - 100 );

			String k6 = "Right click to delete gizmo";

			g2.drawString(k6, windowSize/2 + 40, windowSize - 60);

		}else if(windowSize == 700){

			Font f = null;
			try {
				f = Font.createFont(Font.TRUETYPE_FONT, new File("asset/fonts/mainFont4.ttf"));
			} catch (FontFormatException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}
			f = f.deriveFont(18f);

			g2.setFont(f);

			String k = "Player 1 Gizmo Left:  " + GameEngineData.getPlayer1().getGizmoCount();

			g2.drawString(k, 40, windowSize-140);

			String k2 = "Player 2 Gizmo Left: " + GameEngineData.getPlayer2().getGizmoCount();		

			g2.drawString(k2, 40, windowSize- 120 );

			String k3 = "Click to drop gizmo";

			g2.drawString(k3, windowSize/2 + 40, windowSize - 140);

			String k4 = "Drag to move gizmo ";		

			g2.drawString(k4, windowSize/2 + 40, windowSize - 120 );

			String k5 = "Double click to rotate gizmo ";		

			g2.drawString(k5, windowSize/2 + 40, windowSize - 100 );

			String k6 = "Right click to delete gizmo";

			g2.drawString(k6, windowSize/2 + 40, windowSize - 80);

		}else{

			Font f = null;
			try {
				f = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/mainFont4.ttf"));
			} catch (FontFormatException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}
			f = f.deriveFont(15f);

			g2.setFont(f);

			String k = "Player 1 Gizmo Left:  " + GameEngineData.getPlayer1().getGizmoCount();

			g2.drawString(k, 40, windowSize-100);

			String k2 = "Player 2 Gizmo Left: " + GameEngineData.getPlayer2().getGizmoCount();		

			g2.drawString(k2, 40, windowSize- 80 );

			String k3 = "Click to drop gizmo";

			g2.drawString(k3, windowSize/2 + 40, windowSize - 100);

			String k4 = "Drag to move gizmo ";		

			g2.drawString(k4, windowSize/2 + 40, windowSize - 80 );

			String k5 = "Double click to rotate gizmo ";		

			g2.drawString(k5, windowSize/2 + 40, windowSize - 60 );

			String k6 = "Right click to delete gizmo";

			g2.drawString(k6, windowSize/2 + 40, windowSize - 40);

		}

		g2.setColor(new Color(201, 50, 0));

		if(GameEngineData.getPlayer2().getGizmoCount() == 0 && GameEngineData.getPlayer1().getGizmoCount() == 0){
			g2.drawString("PRESS SPACE TO CONTINUE", 40, windowSize- 60 );
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) { 	  // ADD OR REMOVE GIZMO 
		if(SwingUtilities.isRightMouseButton(e)){ // DELETE GIZMOS WITH RIGHT CLICK

			Rectangle mouse = new Rectangle(e.getX(), e.getY(), 1, 1);

			for (int j = 0; j < GameEngineData.getSquareTakozList().size(); j++) {
				if(GameEngineData.getSquareTakozList().get(j).boundingBox().intersects(mouse)){

					Rectangle oldTakozPos = GameEngineData.getSquareTakozList().get(j).boundingBox();
					repaint(oldTakozPos.x,oldTakozPos.y,oldTakozPos.width,oldTakozPos.height);
					GameEngineData.getSquareTakozList().remove(j);

					GameEngineAudioHandler.getInstance().makeDeleteSound();


					if(e.getX() < windowSize/2){
						player1GizmoCount--;
						GameEngineData.getPlayer1().setGizmoCount(GameEngineData.getPlayer1().getGizmoCount()+1);

					}else{
						player2GizmoCount--;
						GameEngineData.getPlayer2().setGizmoCount(GameEngineData.getPlayer2().getGizmoCount()+1);
					}
				}
			}

			for (int j = 0; j < GameEngineData.getTriangularTakozList().size(); j++) {
				if(GameEngineData.getTriangularTakozList().get(j).boundingBox().intersects(mouse)){

					Rectangle oldTakozPos = GameEngineData.getTriangularTakozList().get(j).boundingBox();
					repaint(oldTakozPos.x,oldTakozPos.y,oldTakozPos.width,oldTakozPos.height);
					GameEngineData.getTriangularTakozList().remove(j);

					GameEngineAudioHandler.getInstance().makeDeleteSound();

					if(e.getX() < windowSize/2){
						player1GizmoCount--;
						GameEngineData.getPlayer1().setGizmoCount(GameEngineData.getPlayer1().getGizmoCount()+1);

					}else{
						player2GizmoCount--;
						GameEngineData.getPlayer2().setGizmoCount(GameEngineData.getPlayer2().getGizmoCount()+1);
					}
				}
			}

			for (int j = 0; j < GameEngineData.getFirildakList().size(); j++) {

				if(GameEngineData.getFirildakList().get(j).boundingBox().intersects(mouse)){

					Rectangle oldTakozPos = GameEngineData.getFirildakList().get(j).boundingBox();
					repaint(oldTakozPos.x,oldTakozPos.y,oldTakozPos.width,oldTakozPos.height);
					GameEngineData.getFirildakList().remove(j);

					GameEngineAudioHandler.getInstance().makeDeleteSound();

					if(e.getX() < windowSize/2){
						player1GizmoCount--;
						GameEngineData.getPlayer1().setGizmoCount(GameEngineData.getPlayer1().getGizmoCount()+1);


					}else{
						player2GizmoCount--;
						GameEngineData.getPlayer2().setGizmoCount(GameEngineData.getPlayer2().getGizmoCount()+1);
					}
				}
			}

			for (int j = 0; j < GameEngineData.getTokatList().size(); j++) {

				if(GameEngineData.getTokatList().get(j).boundingBox().intersects(mouse)){

					Rectangle oldTakozPos = GameEngineData.getTokatList().get(j).boundingBox();
					repaint(oldTakozPos.x,oldTakozPos.y,oldTakozPos.width,oldTakozPos.height);
					GameEngineData.getTokatList().remove(j);

					GameEngineAudioHandler.getInstance().makeDeleteSound();

					if(e.getX() < windowSize/2){
						player1GizmoCount--;
						GameEngineData.getPlayer1().setGizmoCount(GameEngineData.getPlayer1().getGizmoCount()+1);
					}else{
						player2GizmoCount--;
						GameEngineData.getPlayer2().setGizmoCount(GameEngineData.getPlayer2().getGizmoCount()+1);
					}
				}
			}

			if(GameEngineData.getPlayer1().getGizmoCount() != 0){
				isPlayer1sTurn = true;
			}
			
			repaint();
			
		}else{

			if( (e.getX() < ((windowSize/2) - windowSize/25) && e.getY() < windowSize - (windowSize/25)*6) && isPlayer1sTurn ){
				if(player1GizmoCount + player2GizmoCount <= 8){
					if(gameFrame.getSelectedItem().equals("SQUARETAKOZ")){

						Rectangle mouse = new Rectangle(e.getX(), e.getY(), 1, 1);

						if(isAvailablePosition(mouse)){
							int farkX = e.getX()%windowSizePart;
							int farkY = e.getY()%windowSizePart;
							GameEngineAudioHandler.getInstance().makeDropSound();
							GameEngineData.addGizmo(GameEngineGizmoFactory.getGizmo("SquareTakoz", e.getX()-farkX,e.getY()-farkY,windowSizePart,Color.BLACK,1));
							player1GizmoCount++;
							GameEngineData.getPlayer1().setGizmoCount(GameEngineData.getPlayer1().getGizmoCount()-1);
						}

						if(GameEngineData.getPlayer1().getGizmoCount() == 0){
							isPlayer1sTurn = false;

						}

					}else if(gameFrame.getSelectedItem().equals("TRIANGULARTAKOZ")){

						Rectangle mouse = new Rectangle(e.getX(), e.getY(), 1, 1);

						if(isAvailablePosition(mouse)){
							int farkX = e.getX()%windowSizePart;
							int farkY = e.getY()%windowSizePart;
							GameEngineAudioHandler.getInstance().makeDropSound();
							GameEngineData.addGizmo(GameEngineGizmoFactory.getGizmo("TRIANGULARTAKOZ", e.getX()-farkX,e.getY()-farkY,windowSizePart,Color.BLACK, stateTriangleSelector, 1));
							player1GizmoCount++;
							GameEngineData.getPlayer1().setGizmoCount(GameEngineData.getPlayer1().getGizmoCount()-1);
						}


						if(GameEngineData.getPlayer1().getGizmoCount() == 0){
							isPlayer1sTurn = false;

						}

					}else if(gameFrame.getSelectedItem().equals("FIRILDAK")){

						Rectangle mouse = new Rectangle(e.getX(), e.getY(), 1, 1);

						if(isAvailablePosition(mouse)){
							int farkX = e.getX()%windowSizePart;
							int farkY = e.getY()%windowSizePart;
							GameEngineAudioHandler.getInstance().makeDropSound();
							GameEngineData.addGizmo(GameEngineGizmoFactory.getGizmo("FIRILDAK", e.getX()-farkX,e.getY()-farkY,windowSizePart,Color.BLACK, 1));
							player1GizmoCount++;
							GameEngineData.getPlayer1().setGizmoCount(GameEngineData.getPlayer1().getGizmoCount()-1);
						}

						if(GameEngineData.getPlayer1().getGizmoCount() == 0){
							isPlayer1sTurn = false;
						}

					}else if(gameFrame.getSelectedItem().equals("TOKAT")){

						int farkX = e.getX()%windowSizePart;
						int farkY = e.getY()%windowSizePart;

						Rectangle mouse = new Rectangle(e.getX(), e.getY(), windowSizePart*2 - farkX , windowSizePart*2 - farkY);

						if(isAvailablePosition(mouse)){
							farkX = e.getX()%windowSizePart;
							farkY = e.getY()%windowSizePart;
							GameEngineAudioHandler.getInstance().makeDropSound();
							GameEngineData.addGizmo(GameEngineGizmoFactory.getGizmo("TOKAT", e.getX()-farkX,e.getY()-farkY,windowSizePart,Color.BLACK, stateTokatSelector, 1));
							player1GizmoCount++;
							GameEngineData.getPlayer1().setGizmoCount(GameEngineData.getPlayer1().getGizmoCount()-1);
						}

						if(GameEngineData.getPlayer1().getGizmoCount() == 0){
							isPlayer1sTurn = false;
						}
					}
				}

			}else if ( (e.getX() >= ((windowSize/2) + windowSize/25) && e.getY() < windowSize - (windowSize/25)*6 ) && !isPlayer1sTurn ){

				if(GameEngineData.getPlayer1().getGizmoCount() + GameEngineData.getPlayer2().getGizmoCount() !=  0){

					if(gameFrame.getSelectedItem().equals("SQUARETAKOZ")){

						Rectangle mouse = new Rectangle(e.getX(), e.getY(), 1, 1);

						if(isAvailablePosition(mouse)){
							int farkX = e.getX()%windowSizePart;
							int farkY = e.getY()%windowSizePart;
							GameEngineAudioHandler.getInstance().makeDropSound();
							GameEngineData.addGizmo(GameEngineGizmoFactory.getGizmo("SQUARETAKOZ", e.getX()-farkX,e.getY()-farkY,windowSizePart,Color.BLUE, 2));
							player2GizmoCount++;
							GameEngineData.getPlayer2().setGizmoCount(GameEngineData.getPlayer2().getGizmoCount()-1);
						}

						if(GameEngineData.getPlayer1().getGizmoCount() != 0){
							isPlayer1sTurn = true;
						}

					}else if(gameFrame.getSelectedItem().equals("TRIANGULARTAKOZ")){

						Rectangle mouse = new Rectangle(e.getX(), e.getY(), 1, 1);

						if(isAvailablePosition(mouse)){
							int farkX = e.getX()%windowSizePart;
							int farkY = e.getY()%windowSizePart;
							GameEngineAudioHandler.getInstance().makeDropSound();
							GameEngineData.addGizmo(GameEngineGizmoFactory.getGizmo("TRIANGULARTAKOZ", e.getX()-farkX,e.getY()-farkY,windowSizePart,Color.BLACK, stateTriangleSelector, 2));
							player2GizmoCount++;
							GameEngineData.getPlayer2().setGizmoCount(GameEngineData.getPlayer2().getGizmoCount()-1);
						}

						if(GameEngineData.getPlayer1().getGizmoCount() != 0){
							isPlayer1sTurn = true;
						}

					}else if(gameFrame.getSelectedItem().equals("FIRILDAK")){

						Rectangle mouse = new Rectangle(e.getX(), e.getY(), 1, 1);

						if(isAvailablePosition(mouse)){
							int farkX = e.getX()%windowSizePart;
							int farkY = e.getY()%windowSizePart;
							GameEngineAudioHandler.getInstance().makeDropSound();
							GameEngineData.addGizmo(GameEngineGizmoFactory.getGizmo("FIRILDAK", e.getX()-farkX,e.getY()-farkY,windowSizePart,Color.BLACK, 2));
							player2GizmoCount++;
							GameEngineData.getPlayer2().setGizmoCount(GameEngineData.getPlayer2().getGizmoCount()-1);
						}

						if(GameEngineData.getPlayer1().getGizmoCount() != 0){
							isPlayer1sTurn = true;
						}

					}else if(gameFrame.getSelectedItem().equals("TOKAT")){ // TOKAT

						int farkX = e.getX()%windowSizePart;
						int farkY = e.getY()%windowSizePart;

						Rectangle mouse = new Rectangle(e.getX(), e.getY(), windowSizePart*2-farkX, windowSizePart*2-farkY);

						if(isAvailablePosition(mouse)){
							farkX = e.getX()%windowSizePart;
							farkY = e.getY()%windowSizePart;
							GameEngineAudioHandler.getInstance().makeDropSound();
							GameEngineData.addGizmo(GameEngineGizmoFactory.getGizmo("TOKAT", e.getX()-farkX,e.getY()-farkY,windowSizePart,Color.BLACK, stateTokatSelector, 2));
							player2GizmoCount++;
							GameEngineData.getPlayer2().setGizmoCount(GameEngineData.getPlayer2().getGizmoCount()-1);
						}

						if(GameEngineData.getPlayer1().getGizmoCount() != 0){
							isPlayer1sTurn = true;

						}
					}
				}
			}
		}

		if (e.getClickCount() == 2) {

			Rectangle mouse = new Rectangle(e.getX(), e.getY(), 1, 1);

			for (int j = 0; j < GameEngineData.getTriangularTakozList().size(); j++) {
				if(GameEngineData.getTriangularTakozList().get(j).boundingBox().intersects(mouse)){
					GameEngineAudioHandler.getInstance().makeRotateSound();
					GameEngineData.getTriangularTakozList().get(j).setState(GameEngineData.getTriangularTakozList().get(j).getState() + 1);
					repaint();
				}
			}

			for (int j = 0; j < GameEngineData.getTokatList().size(); j++) {
				if(GameEngineData.getTokatList().get(j).boundingBox().intersects(mouse)){
					GameEngineAudioHandler.getInstance().makeRotateSound();
					GameEngineData.getTokatList().get(j).setState(GameEngineData.getTokatList().get(j).getState() + 1);
					repaint();
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		Rectangle mouse = new Rectangle(e.getX(), e.getY(), 1, 1);
		if(isItemDragged){

			if(draggedItem.equals("sqrtkz")){
				if(isAvailablePosition(mouse)){
					Boolean isItValidMove = true;
					if(GameEngineData.getSquareTakozList().get(holdedItemIndex).getPlayer() == 1){

						if(e.getX() >  windowSize/2){

							isItValidMove = false;
						}

					}else{

						if(e.getX() <  windowSize/2){

							isItValidMove = false;

						}
					}

					if(isItValidMove && e.getY() < windowSize - (windowSize/25) * 6){

						int farkX = e.getX()%windowSizePart;
						int farkY = e.getY()%windowSizePart;
						GameEngineAudioHandler.getInstance().makeMoveSound();
						GameEngineData.getSquareTakozList().get(holdedItemIndex).setX(e.getX()-farkX);
						GameEngineData.getSquareTakozList().get(holdedItemIndex).setY(e.getY()-farkY);

					}
				}
				gizmoIsSelected = true;
			}else if(draggedItem.equals("tritkz")){
				if(isAvailablePosition(mouse)){

					Boolean isItValidMove = true;

					if(GameEngineData.getTriangularTakozList().get(holdedItemIndex).getPlayer() == 1){

						if(e.getX() >  windowSize/2){
							isItValidMove = false;
						}

					}else{

						if(e.getX() <  windowSize/2){
							isItValidMove = false;
						}
					}

					if(isItValidMove && e.getY() < windowSize - (windowSize/25) * 6){

						int farkX = e.getX()%windowSizePart;
						int farkY = e.getY()%windowSizePart;
						GameEngineAudioHandler.getInstance().makeMoveSound();
						GameEngineData.getTriangularTakozList().get(holdedItemIndex).setX(e.getX()-farkX);
						GameEngineData.getTriangularTakozList().get(holdedItemIndex).setY(e.getY()-farkY);
					}
				}

				gizmoIsSelected = true;
			}else if(draggedItem.equalsIgnoreCase("firildak")){
				if(isAvailablePosition(mouse)){
					Boolean isItValidMove = true;
					if(GameEngineData.getFirildakList().get(holdedItemIndex).getPlayer() == 1){
						if(e.getX() >  windowSize/2){
							isItValidMove = false;
						}
					}else{
						if(e.getX() <  windowSize/2){
							isItValidMove = false;
						}
					}
					if(isItValidMove && e.getY() < windowSize - (windowSize/25) * 6){
						int farkX = e.getX()%windowSizePart;
						int farkY = e.getY()%windowSizePart;
						GameEngineAudioHandler.getInstance().makeMoveSound();
						GameEngineData.getFirildakList().get(holdedItemIndex).setX(e.getX()-farkX);
						GameEngineData.getFirildakList().get(holdedItemIndex).setY(e.getY()-farkY);
					}
				}

				gizmoIsSelected = true;

			}else if(draggedItem.equals("tokat")){
				if(isAvailablePosition(mouse)){
					Boolean isItValidMove = true;
					if(GameEngineData.getTokatList().get(holdedItemIndex).getPlayer() == 1){
						if(e.getX() >  windowSize/2){
							isItValidMove = false;
						}

					}else{

						if(e.getX() <  windowSize/2){
							isItValidMove = false;
						}
					}

					if(isItValidMove && e.getY() < windowSize - (windowSize/25) * 6){

						int farkX = e.getX()%windowSizePart;
						int farkY = e.getY()%windowSizePart;
						GameEngineAudioHandler.getInstance().makeMoveSound();
						GameEngineData.getTokatList().get(holdedItemIndex).setX(e.getX()-farkX);
						GameEngineData.getTokatList().get(holdedItemIndex).setY(e.getY()-farkY);
					}
				}		
				gizmoIsSelected = true;
			}
			repaint();
			draggedItem = "";
			isItemDragged = false;
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseDragged(MouseEvent e) {

		Rectangle mouse = new Rectangle(e.getX(), e.getY(), 1, 1);
		if(gizmoIsSelected){
			for (int j = 0; j < GameEngineData.getSquareTakozList().size(); j++) {

				if(GameEngineData.getSquareTakozList().get(j).boundingBox().intersects(mouse)){
					holdedItemIndex = j;
					isItemDragged = true;
					draggedItem = "sqrtkz";
					gizmoIsSelected = false;
				}
			}

			for (int j = 0; j < GameEngineData.getTriangularTakozList().size(); j++) {
				if(GameEngineData.getTriangularTakozList().get(j).boundingBox().intersects(mouse)){
					holdedItemIndex = j;
					isItemDragged = true;
					draggedItem = "tritkz";
					gizmoIsSelected = false;
				}
			}


			for (int j = 0; j < GameEngineData.getFirildakList().size(); j++) {
				if(GameEngineData.getFirildakList().get(j).boundingBox().intersects(mouse)){
					holdedItemIndex = j;
					isItemDragged = true;
					draggedItem = "firildak";
					gizmoIsSelected = false;
				}
			}

			for (int j = 0; j < GameEngineData.getTokatList().size(); j++) {
				if(GameEngineData.getTokatList().get(j).boundingBox().intersects(mouse)){
					holdedItemIndex = j;
					isItemDragged = true;
					draggedItem = "tokat";
					gizmoIsSelected = false;
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		Rectangle mouse = new Rectangle(e.getX(), e.getY(), 1, 1);
		stateTriangleSelector = 1;
		stateTokatSelector = 1;
		if(!rotateState){
			if(gameFrame.getSelectedItem().equals("SQUARETAKOZ")){

				if(isPlayer1sTurn){

					if( (e.getX() < ((windowSize/2) - windowSize/25) && e.getY() < windowSize - (windowSize/25)*6 )){
						setCursor(blankCursor);
						int farkX = e.getX()%windowSizePart;
						int farkY = e.getY()%windowSizePart;
						xTakozSelecter =  e.getX()-farkX;
						yTakozSelecter =	e.getY()-farkY;
						repaint();

					}else{
						setCursor(Cursor.getDefaultCursor());
						xTakozSelecter = 100000;
						yTakozSelecter = 100000;
						repaint();
					}

				}else{

					if( (e.getX() >= ((windowSize/2) + windowSize/25) && e.getY() < windowSize - (windowSize/25)*6 ) && player2GizmoCount != 4){
						setCursor(blankCursor);
						int farkX = e.getX()%windowSizePart;
						int farkY = e.getY()%windowSizePart;
						xTakozSelecter =  e.getX()-farkX;
						yTakozSelecter =  e.getY()-farkY;
						repaint();

					}else{
						setCursor(Cursor.getDefaultCursor());
						xTakozSelecter = 100000;
						yTakozSelecter = 100000;
						repaint();
					}
				}

			}else if(gameFrame.getSelectedItem().equals("TRIANGULARTAKOZ")){

				if(isPlayer1sTurn){

					if( (e.getX() < ((windowSize/2) - windowSize/25) && e.getY() < windowSize - (windowSize/25)*6 )){
						setCursor(blankCursor);
						int farkX = e.getX()%windowSizePart;
						int farkY = e.getY()%windowSizePart;

						x1TriAngle = e.getX()-farkX; 
						x2TriAngle = e.getX()-farkX;
						x3TriAngle = e.getX() + windowSize/25-farkX;

						y1TriAngle = e.getY()-farkY;
						y2TriAngle = e.getY() + windowSize/25-farkY;
						y3TriAngle = e.getY() + windowSize/25-farkY;

						triangle.addPoint(x1TriAngle, y1TriAngle);
						triangle.addPoint(x2TriAngle, y2TriAngle);
						triangle.addPoint(x3TriAngle, y3TriAngle);

						repaint();
						triangle.reset();

					}else{
						setCursor(Cursor.getDefaultCursor());
						int farkX = e.getX()%windowSizePart;
						int farkY = e.getY()%windowSizePart;

						x1TriAngle = 100000-farkX; 
						x2TriAngle = 100000-farkX;
						x3TriAngle = 100000 + windowSize/25-farkX;

						y1TriAngle = 100000-farkY;
						y2TriAngle = 100000 + windowSize/25-farkY;
						y3TriAngle = 100000 + windowSize/25-farkY;

						repaint();
						triangle.reset();
					}

				}else{

					if( (e.getX() >= ((windowSize/2) + windowSize/25) && e.getY() < windowSize - (windowSize/25)*6 ) && player2GizmoCount != 4){
						setCursor(blankCursor);
						int farkX = e.getX()%windowSizePart;
						int farkY = e.getY()%windowSizePart;

						x1TriAngle = e.getX()-farkX; 
						x2TriAngle = e.getX()-farkX;
						x3TriAngle = e.getX() + windowSize/25-farkX;

						y1TriAngle = e.getY()-farkY;
						y2TriAngle = e.getY() + windowSize/25-farkY;
						y3TriAngle = e.getY() + windowSize/25-farkY;

						triangle.addPoint(x1TriAngle, y1TriAngle);
						triangle.addPoint(x2TriAngle, y2TriAngle);
						triangle.addPoint(x3TriAngle, y3TriAngle);

						repaint();
						triangle.reset();
						
					}else{
						setCursor(Cursor.getDefaultCursor());
						int farkX = e.getX()%windowSizePart;
						int farkY = e.getY()%windowSizePart;

						x1TriAngle = 100000-farkX; 
						x2TriAngle = 100000-farkX;
						x3TriAngle = 100000 + windowSize/25-farkX;

						y1TriAngle = 100000-farkY;
						y2TriAngle = 100000 + windowSize/25-farkY;
						y3TriAngle = 100000 + windowSize/25-farkY;

						repaint();
						triangle.reset();
					}
				}

			}else if(gameFrame.getSelectedItem().equals("FIRILDAK")){

				if(isPlayer1sTurn){

					if( (e.getX() < ((windowSize/2) - windowSize/25) && e.getY() < windowSize - (windowSize/25)*6 )){
						setCursor(blankCursor);
						int farkX = e.getX()%windowSizePart;
						int farkY = e.getY()%windowSizePart;
						xTakozSelecter =  e.getX()-farkX;
						yTakozSelecter =	e.getY()-farkY;
						repaint();
					}else{
						setCursor(Cursor.getDefaultCursor());
						xTakozSelecter = 100000;
						yTakozSelecter = 100000;
						repaint();
					}

				}else {

					if( (e.getX() >= ((windowSize/2) + windowSize/25) && e.getY() < windowSize - (windowSize/25)*6 ) && player2GizmoCount != 4){
						setCursor(blankCursor);
						int farkX = e.getX()%windowSizePart;
						int farkY = e.getY()%windowSizePart;
						xTakozSelecter =  e.getX()-farkX;
						yTakozSelecter =  e.getY()-farkY;
						repaint();

					}else{
						setCursor(Cursor.getDefaultCursor());
						xTakozSelecter = 100000;
						yTakozSelecter = 100000;
						repaint();
					}
				}

			}else if(gameFrame.getSelectedItem().equals("TOKAT")){ // TOKAT

				if(isPlayer1sTurn){

					if( (e.getX() < ((windowSize/2) - windowSize/25) && e.getY() < windowSize - (windowSize/25)*6 - windowSizePart ) ){
						setCursor(blankCursor);
						int farkX = e.getX()%windowSizePart;
						int farkY = e.getY()%windowSizePart;

						tokatOval1X = e.getX()-farkX;
						tokatOval1y = e.getY()-farkY;

						tokatXRec = e.getX()-farkX ;
						tokatYRec = e.getY()-farkY + windowSize/100;

						tokatOval2X = e.getX()-farkX ;
						tokatOval2y = e.getY()-farkY + windowSize/25 + windowSize/50;

						repaint();

					}else{

						setCursor(Cursor.getDefaultCursor());	
						tokatXRec = 1000;
						tokatYRec = 1000;

						tokatOval1X = 1000;
						tokatOval2X = 1000;
						tokatOval1y = 1000;
						tokatOval2y = 1000;

						repaint();
					}

				}else{

					if( (e.getX() >= ((windowSize/2) + windowSize/25) && e.getY() < windowSize - (windowSize/25)*6 -windowSizePart ) && player2GizmoCount != 4){
						setCursor(blankCursor);
						int farkX = e.getX()%windowSizePart;
						int farkY = e.getY()%windowSizePart;

						tokatOval1X = e.getX()-farkX;
						tokatOval1y = e.getY()-farkY;

						tokatXRec = e.getX()-farkX ;
						tokatYRec = e.getY()-farkY + windowSize/100;

						tokatOval2X = e.getX()-farkX ;
						tokatOval2y = e.getY()-farkY + windowSize/25 + windowSize/50;

						repaint();

					}else{

						setCursor(Cursor.getDefaultCursor());
						tokatXRec = 1000;
						tokatYRec = 1000;

						tokatOval1X = 1000;
						tokatOval2X = 1000;
						tokatOval1y = 1000;
						tokatOval2y = 1000;

						repaint();
					}
				}
			}

		}else{

			tokatXRec = 10000;
			tokatYRec = 10000;

			tokatOval1X = 10000;
			tokatOval2X = 10000;
			tokatOval1y = 10000;
			tokatOval2y = 10000;

			xTakozSelecter = 10000;
			yTakozSelecter = 10000;

			x1TriAngle = 10000; 
			x2TriAngle = 10000;
			x3TriAngle = 10000 + windowSize/25;

			y1TriAngle = 10000;
			y2TriAngle = 10000 + windowSize/25;
			y3TriAngle = 10000 + windowSize/25;

			repaint();
		}

		if(!isAvailablePosition(mouse)){
			setCursor(Cursor.getDefaultCursor());
		}
	}	

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		int keyCode = e.getKeyChar();
		if(keyCode == 114){
			if(gameFrame.getSelectedItem().equals("TRIANGULARTAKOZ")){
				if(stateTriangleSelector%4 == 0){

					x2TriAngle = x2TriAngle - windowSize/25;
					y2TriAngle = y2TriAngle + windowSize/25;

					y1TriAngle = y1TriAngle -  windowSize/25;

					x3TriAngle = x3TriAngle + windowSize/25;
					y3TriAngle = y3TriAngle + windowSize/25;

					repaint();
					stateTriangleSelector++;

				}else if(stateTriangleSelector%4 == 1){

					x2TriAngle = x2TriAngle + windowSize/25;
					y2TriAngle = y2TriAngle - windowSize/25;
					repaint();

					stateTriangleSelector++;

				}else if(stateTriangleSelector%4 == 2){

					y1TriAngle = y1TriAngle +  windowSize/25;
					repaint();
					stateTriangleSelector++;

				}else if(stateTriangleSelector%4 == 3){

					x3TriAngle = x3TriAngle - windowSize/25;
					y3TriAngle = y3TriAngle - windowSize/25;

					repaint();
					stateTriangleSelector++;
				}

			}else if(gameFrame.getSelectedItem().equals("TOKAT")){

				if(stateTokatSelector%2 == 0){

					tokatOval1X = tokatOval1X - windowSizePart - windowSizePart/2;
					tokatXRec = tokatXRec - windowSizePart - windowSizePart/2;
					tokatOval2X =tokatOval2X - windowSizePart - windowSizePart/2;
					repaint();
					stateTokatSelector++;	

				}else if(stateTokatSelector%2 == 1){

					tokatOval1X = tokatOval1X + windowSizePart + windowSizePart/2;
					tokatXRec =  tokatXRec + windowSizePart + windowSizePart/2;
					tokatOval2X =  tokatOval2X + windowSizePart + windowSizePart/2;
					repaint();
					stateTokatSelector++;
				}
			}

		}else if(keyCode == 49){
			gameFrame.setSelectedItem("SQUARETAKOZ");
			repaint();

		}else if(keyCode == 50){
			gameFrame.setSelectedItem("TRIANGULARTAKOZ");
			repaint();


		}else if(keyCode == 51){
			gameFrame.setSelectedItem("FIRILDAK");
			repaint();


		}else if(keyCode == 52){
			gameFrame.setSelectedItem("TOKAT");
			repaint();

		}else{

			if(GameEngineData.getPlayer1().getGizmoCount() + GameEngineData.getPlayer2().getGizmoCount() == 0){
				this.removeKeyListener(this);
				gameFrame.getDeleteClip().stop();
				gameFrame.setVisible(false);
				FrameGame gw = new FrameGame(gameFrame.getMainMenuFrame(), gameFrame.getMainMenuClip(), level, aiMode);
				gw.setVisible(true);
				gameFrame.dispose();

			}
			gameFrame.setSelectedItem("NONE");
			repaint();
		}
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

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < GameEngineData.getSquareTakozList().size(); i++) {
			Rectangle oldTakozPos = GameEngineData.getSquareTakozList().get(i).boundingBox();
			repaint(oldTakozPos.x,oldTakozPos.y,oldTakozPos.width,oldTakozPos.height);
		}

		for (int i = 0; i < GameEngineData.getFirildakList().size(); i++) {
			Rectangle oldTakozPos = GameEngineData.getFirildakList().get(i).boundingBox();
			repaint(oldTakozPos.x,oldTakozPos.y,oldTakozPos.width,oldTakozPos.height);
		}

		for (int i = 0; i < GameEngineData.getTokatList().size(); i++) {
			Rectangle oldTakozPos = GameEngineData.getTokatList().get(i).boundingBox();
			repaint(oldTakozPos.x,oldTakozPos.y,oldTakozPos.width,oldTakozPos.height);
		}

		for (int i = 0; i < GameEngineData.getTriangularTakozList().size(); i++) {
			Rectangle oldTakozPos = GameEngineData.getTriangularTakozList().get(i).boundingBox();
			repaint(oldTakozPos.x,oldTakozPos.y,oldTakozPos.width,oldTakozPos.height);
		}
	}

	/**
	 * Checks if is available position.
	 *
	 * @param mouse the mouse
	 * @return true, if is available position
	 */
	public boolean isAvailablePosition(Rectangle mouse){

		Boolean existLocation = true;

		for (int j = 0; j < GameEngineData.getSquareTakozList().size(); j++) {
			if(GameEngineData.getSquareTakozList().get(j).boundingBox().intersects(mouse)){

				existLocation = false;
			}
		}

		for (int j = 0; j < GameEngineData.getTriangularTakozList().size(); j++) {
			if(GameEngineData.getTriangularTakozList().get(j).boundingBox().intersects(mouse)){

				existLocation = false;
			}
		}

		for (int j = 0; j < GameEngineData.getFirildakList().size(); j++) {
			if(GameEngineData.getFirildakList().get(j).boundingBox().intersects(mouse)){

				existLocation = false;
			}
		}

		for (int j = 0; j < GameEngineData.getTokatList().size(); j++) {
			if(GameEngineData.getTokatList().get(j).boundingBox().intersects(mouse)){

				existLocation = false;
			}
		}

		return existLocation;
	}

	public int getPlayer1GizmoCount() {
		return player1GizmoCount;
	}

	public void setPlayer1GizmoCount(int player1GizmoCount) {
		this.player1GizmoCount = player1GizmoCount;
	}

	public int getPlayer2GizmoCount() {
		return player2GizmoCount;
	}

	public void setPlayer2GizmoCount(int player2GizmoCount) {
		this.player2GizmoCount = player2GizmoCount;
	}



}