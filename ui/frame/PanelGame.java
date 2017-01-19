package ui.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import domain.gameengine.GameEngineData;
import domain.gameengine.GameEngineUpdater;
import domain.physics.Vect;
import domain.gameengine.GameEngineKeyHandler;
import ui.data.UIData;
import ui.item.UIFirildak;
import ui.item.UITokat;
import ui.painter.UIPainterEdit;
import ui.painter.UIPainterPlay;

/**
 * The Class PanelGame.
 */
public class PanelGame extends JPanel implements ActionListener{

	/** The window size. */
	private int windowSize;

	/** The level. */
	private int level;

	/** The mini time. */
	private int startTime = 0;

	/** The game time. */
	private long gameTime = 0;

	/** The pause state. */
	private boolean pauseState = true;

	/** The main pause. */
	private boolean mainPause = true;

	/** The pause label. */
	private JLabel pauseLabel;

	/** The timer. */
	private Timer timer;

	/** The game frame. */
	private FrameGame gameFrame;

	/** The game update. */
	private GameEngineUpdater gameUpdate;

	/** The key board event handler. */
	private GameEngineKeyHandler keyBoardEventHandler;

	private boolean aiMode = false;

	private ExternalTaskBallMovement externalTaskBall;
	private ExternalTaskCezmiCollusion externalTaskCezmiCollusion;
	private ExternalTaskCezmiMovement externalTaskWallMovement;
	private ExternalStart exStart;

	/**
	 * Instantiates a new panel game.
	 *
	 * @param level the level
	 * @param gameWindow the game window
	 * @param gameFrame the game frame
	 * @throws FontFormatException the font format exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */

	public PanelGame(int level, int gameWindow, FrameGame gameFrame, boolean aiMode) throws FontFormatException, IOException{
		super();
		this.aiMode = aiMode;
		for (int i = 0; i < GameEngineData.getFirildakList().size(); i++) {
			UIData.addUIFirildakList(new UIFirildak(GameEngineData.getFirildakList().get(i)));
		}

		for (int i = 0; i < GameEngineData.getTokatList().size(); i++) {
			UIData.addUITokatList(new UITokat(GameEngineData.getTokatList().get(i)));
		}

		BorderLayout borderLayout = new BorderLayout();
		this.setLayout(borderLayout);
		this.gameFrame = gameFrame;
		Font mainMenuFont = Font.createFont(Font.TRUETYPE_FONT, new File("asset/fonts/mainFont4.ttf"));
		mainMenuFont = mainMenuFont.deriveFont(40f);
		GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(mainMenuFont);
		this.windowSize = gameWindow;
		this.level = level;	
		this.setSize(new Dimension(gameWindow,gameWindow));
		this.setBackground(new Color(28, 28, 28));
		InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMap = getActionMap();
		this.keyBoardEventHandler = new GameEngineKeyHandler(this,inputMap,actionMap);
		JPanel allPanel = new JPanel ();
		allPanel.setLayout(new GridBagLayout());
		allPanel.setPreferredSize(new Dimension(gameWindow,gameWindow-(6* (gameWindow/25))));
		pauseLabel = new JLabel();
		pauseLabel.setForeground(Color.BLACK);
		pauseLabel.setFont(mainMenuFont);
		allPanel.add(pauseLabel);
		this.add(allPanel,BorderLayout.NORTH);
		timer = new Timer(20, this); 
		timer.start();

		externalTaskBall = new ExternalTaskBallMovement();
		externalTaskCezmiCollusion = new ExternalTaskCezmiCollusion();
		externalTaskWallMovement = new ExternalTaskCezmiMovement();
		exStart = new ExternalStart();

		exStart.execute();

		gameUpdate = new GameEngineUpdater(gameWindow, level);
		externalTaskBall.execute();
		externalTaskWallMovement.execute();
		externalTaskCezmiCollusion.execute();
		setFocusable(true);
		requestFocusInWindow();
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g){
		super.paint(g);
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON); 
		UIPainterEdit editPaint = new UIPainterEdit(windowSize);
		UIPainterPlay playPaint = new UIPainterPlay(level);
		editPaint.paint(g2);
		playPaint.paint(g2);
		editPaint.paintEngel(g2);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		gameFrame.player1ScoreBoard.setText("Player 1 Score: " + String.valueOf(GameEngineData.getPlayer1().getScore()));
		gameFrame.player2ScoreBoard.setText("Player 2 Score: " + String.valueOf(GameEngineData.getPlayer2().getScore()));

		if(pauseState && !mainPause){
			gameTime = gameTime +1;
			GameEngineData.setTime(gameTime);
			gameUpdate.updateGame();
			repaint();
		}

		if(GameEngineData.getPlayer1().getScore() >= 10 || GameEngineData.getPlayer2().getScore() >= 10){

			GameEngineData.clearAll();
			UIData.clearAll();

			setVisible(false);
			FrameGameOver gameOverFrame;

			if(GameEngineData.getPlayer2().getScore() >= 10){

				try {

					GameEngineData.getPlayer1().setScore(0);
					GameEngineData.getPlayer2().setScore(0);
					gameOverFrame = new FrameGameOver(true);
					gameOverFrame.setVisible(true);

				}catch(Exception e2){}

			}else if(GameEngineData.getPlayer1().getScore() >= 10){

				try {
					GameEngineData.getPlayer1().setScore(0);
					GameEngineData.getPlayer2().setScore(0);
					gameOverFrame = new FrameGameOver(false);
					gameOverFrame.setVisible(true);

				}catch(Exception e2){}
			}
			gameFrame.getAudioClip().stop();
			gameFrame.setVisible(false);
			timer.stop();
		}
	}

	/**
	 * Checks if is pause state.
	 *
	 * @return true, if is pause state
	 */
	public boolean isPauseState() {
		return pauseState;
	}

	/**
	 * Sets the pause state.
	 *
	 * @param pauseState the new pause state
	 */
	public void setPauseState(boolean pauseState) {
		this.pauseState = pauseState;
	}

	/**
	 * Pause.
	 */
	public void pause(){
		pauseState = false;
	}

	/**
	 * Unpause.
	 */
	public void unPause(){
		pauseState = true;
	}

	/**
	 * Start.
	 */
	public void start(){
		mainPause = false;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public long getGameTime() {
		return gameTime;
	}

	public void setGameTime(long gameTime) {
		this.gameTime = gameTime;
	}

	public void externalTaskStopper(){
		exStart.cancel(true);
		externalTaskBall.cancel(true);
		externalTaskCezmiCollusion.cancel(true);
		externalTaskWallMovement.cancel(true);
	}

	class ExternalStart extends SwingWorker<List<Integer>, Integer> {

		/* (non-Javadoc)
		 * @see javax.swing.SwingWorker#doInBackground()
		 */
		@Override
		protected List<Integer> doInBackground() throws Exception {
			while(true){	
				if(!mainPause){
					if(!pauseState){
						pauseLabel.setText("<HTML> <center>&nbsp; GAME PAUSED &nbsp; <br> &nbsp; PRESS P to RESUME &nbsp;<br> _ </center> <HTML>");
					}else{
						pauseLabel.setText("");	
					}

				}else{

					if(startTime < 1000 && startTime > 0){
						pauseLabel.setText("<HTML> <center>&nbsp; PRESS SPACE TO START GAME &nbsp;<br> 3 <br> _ </center> <HTML>");
					}else if(startTime < 1500 && startTime > 1000){
						pauseLabel.setText("<HTML> <center>&nbsp; PRESS SPACE TO START GAME &nbsp;<br>  2 <br> _ </center> <HTML>");
					}else if(startTime < 2500 && startTime > 1500){
						pauseLabel.setText("<HTML> <center>&nbsp; PRESS SPACE TO START GAME &nbsp;<br>  1 <br> _ </center> <HTML>");
					}else if(startTime > 2500){
						pauseLabel.setText("<HTML> <center>&nbsp; PRESS SPACE TO START GAME &nbsp;<br>  0 <br> _ </center> <HTML>");
						mainPause = false;
					}
				}	
				startTime = startTime + 1;	
				Thread.sleep(1);
			}	
		}
	}

	/**
	 * The Class ExternalTaskCezmiCollusion.
	 */
	class ExternalTaskCezmiCollusion extends SwingWorker<List<Integer>, Integer> {

		/* (non-Javadoc)
		 * @see javax.swing.SwingWorker#doInBackground()
		 */
		@Override
		protected List<Integer> doInBackground() throws Exception {
			while(true){
				if(pauseState && !mainPause){	

					gameUpdate.cezmiCollusion();
				}
				Thread.sleep(1);
			}	
		}
	}

	/**
	 * The Class ExternalTaskCezmiMovement.
	 */
	class ExternalTaskCezmiMovement extends SwingWorker<List<Integer>, Integer> {

		/* (non-Javadoc)
		 * @see javax.swing.SwingWorker#doInBackground()
		 */
		@Override
		protected List<Integer> doInBackground() throws Exception {
			while(true){
				if(pauseState && !mainPause){
					if(aiMode){
						gameUpdate.cezmiMovementAI();
					}else{
						gameUpdate.cezmiMovement();	
					}
				}
				Thread.sleep(2);
			}	
		}
	}

	/**
	 * The Class ExternalTaskBallMovement.
	 */
	class ExternalTaskBallMovement extends SwingWorker<List<Integer>, Integer> {

		/* (non-Javadoc)
		 * @see javax.swing.SwingWorker#doInBackground()
		 */
		@Override
		protected List<Integer> doInBackground() throws Exception {
			while(true){
				if(pauseState && !mainPause){	
					gameUpdate.ballMovement();

					if(GameEngineData.getLevel() == 1){

						Double oldVX = GameEngineData.getBallList().get(0).getVelocityVector().x();
						Double newVX  = oldVX * (1 - (0.025/1000) - (0.025/windowSize/25) * Math.abs(oldVX));

						Double oldVY = GameEngineData.getBallList().get(0).getVelocityVector().y();
						Double newVY  = oldVY * (1 - (0.025/1000) - (0.025/windowSize/25) * Math.abs(oldVY));

						Vect newVel = new Vect(newVX,newVY);

						GameEngineData.getBallList().get(0).setVelocityVector(newVel);

					}else{

						Double oldVX = GameEngineData.getBallList().get(0).getVelocityVector().x();
						Double newVX  = oldVX * (1 - (0.025/1000) - (0.025/windowSize/25) * Math.abs(oldVX));

						Double oldVY = GameEngineData.getBallList().get(0).getVelocityVector().y();
						Double newVY  = oldVY * (1 - (0.025/1000) - (0.025/windowSize/25) * Math.abs(oldVY));

						Vect newVel = new Vect(newVX,newVY);

						GameEngineData.getBallList().get(0).setVelocityVector(newVel);

						Double oldVX2 = GameEngineData.getBallList().get(1).getVelocityVector().x();
						Double newVX2  = oldVX2 * (1 - (0.025/1000) - (0.025/windowSize/25) * Math.abs(oldVX2));

						Double oldVY2 = GameEngineData.getBallList().get(1).getVelocityVector().y();
						Double newVY2  = oldVY2 * (1 - (0.025/1000) - (0.025/windowSize/25) * Math.abs(oldVY2));

						Vect newVel2 = new Vect(newVX2,newVY2);

						GameEngineData.getBallList().get(1).setVelocityVector(newVel2);
					}

				}

				Thread.sleep(20);
			}	
		}
	}
}