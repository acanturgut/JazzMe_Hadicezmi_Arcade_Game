package domain.gameengine;

import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.DataLine.Info;

/**
 * The Class GameEngineAudioHandler.
 */
public class GameEngineAudioHandler {

	/** The audio stream. */
	private AudioInputStream audioStream;
	
	/** The audio format. */
	private AudioFormat audioFormat;
	
	/** The audio info. */
	private Info audioInfo;
	
	/** The audio clip. */
	private Clip audioClip;
	
	/** The instance. */
	private static GameEngineAudioHandler instance = new GameEngineAudioHandler();

	/**
	 * Instantiates a new game engine audio handler.
	 */
	private GameEngineAudioHandler(){}

	/**
	 * Gets the single instance of GameEngineAudioHandler.
	 *
	 * @return single instance of GameEngineAudioHandler
	 */
	public static GameEngineAudioHandler getInstance(){
		return instance;
	}

	/**
	 * Make drop sound.
	 */
	public void makeDropSound(){
		try {

			File deleteSound = new File("asset/audio/drum.wav");
			audioStream = AudioSystem.getAudioInputStream(deleteSound);
			audioFormat = audioStream.getFormat();
			audioInfo = new DataLine.Info(Clip.class, audioFormat);
			audioClip = (Clip) AudioSystem.getLine(audioInfo);
			audioClip.open(audioStream);

		}catch(Exception e){}
		audioClip.start();
	}
	
	/**
	 * Make click sound.
	 */
	public void makeClickSound(){
		try {

			File deleteSound = new File("asset/audio/switch.wav");
			audioStream = AudioSystem.getAudioInputStream(deleteSound);
			audioFormat = audioStream.getFormat();
			audioInfo = new DataLine.Info(Clip.class, audioFormat);
			audioClip = (Clip) AudioSystem.getLine(audioInfo);
			audioClip.open(audioStream);

		}catch(Exception e){}
		audioClip.start();
	}
	
	/**
	 * Make delete sound.
	 */
	public void makeDeleteSound(){
		try {

			File deleteSound = new File("asset/audio/deleter.wav");
			audioStream = AudioSystem.getAudioInputStream(deleteSound);
			audioFormat = audioStream.getFormat();
			audioInfo = new DataLine.Info(Clip.class, audioFormat);
			audioClip = (Clip) AudioSystem.getLine(audioInfo);
			audioClip.open(audioStream);

		}catch(Exception e){}
		audioClip.start();
	}

	/**
	 * Make move sound.
	 */
	public void makeMoveSound(){
		try {

			File deleteSound = new File("asset/audio/guitar.wav");
			audioStream = AudioSystem.getAudioInputStream(deleteSound);
			audioFormat = audioStream.getFormat();
			audioInfo = new DataLine.Info(Clip.class, audioFormat);
			audioClip = (Clip) AudioSystem.getLine(audioInfo);
			audioClip.open(audioStream);

		}catch(Exception e){}
		audioClip.start();
	}

	/**
	 * Make rotate sound.
	 */
	public void makeRotateSound(){
		try {

			File deleteSound = new File("asset/audio/piano.wav");
			audioStream = AudioSystem.getAudioInputStream(deleteSound);
			audioFormat = audioStream.getFormat();
			audioInfo = new DataLine.Info(Clip.class, audioFormat);
			audioClip = (Clip) AudioSystem.getLine(audioInfo);
			audioClip.open(audioStream);

		}catch(Exception e){}
		audioClip.start();
	}
}
