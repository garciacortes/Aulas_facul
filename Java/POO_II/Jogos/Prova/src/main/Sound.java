package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

	public static final String musicBackground = "/music.wav";
	public static final String hurtEffect = "/hurt.wav";
	
	private Sound() {
	}
	
	public static void play(String name) {
		try {
			new Thread() {
				public void run() {
					try {
						Clip clip = AudioSystem.getClip();
						AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getResource(name));
						clip.open(inputStream);
						clip.start();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}.start();
		}catch(Throwable e) {}
	}
	
	public static void loop(String name) {
		try {
			new Thread() {
				public void run() {
					try {
						Clip clip = AudioSystem.getClip();
						AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getResource(name));
						clip.open(inputStream);
						clip.loop(0);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}.start();
		}catch(Throwable e) {}
	}
}
