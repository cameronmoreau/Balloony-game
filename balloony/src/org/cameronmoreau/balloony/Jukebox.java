package org.cameronmoreau.balloony;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class Jukebox {
	
	private static HashMap<String, Sound> sounds;
	private static HashMap<String, Music> music;
	
	static {
		sounds = new HashMap<String, Sound>();
		music = new HashMap<String, Music>();
	}

	public static void load(String path, String name) {
		Sound sound = Gdx.audio.newSound(Gdx.files.internal(path));
		sounds.put(name, sound);
	}
	
	public static void loadMusic(String path, String name) {
		Music song = Gdx.audio.newMusic(Gdx.files.internal(path));
		music.put(name, song);
	} 
	
	public static void play(String name) {
		sounds.get(name).play();
	}
	
	public static void loop(String name) {
		sounds.get(name).loop();
	}
	
	public static void loopMusic(String name) {
		music.get(name).setLooping(true);
		music.get(name).play();
	}
	
	public static void stop(String name) {
		sounds.get(name).stop();
	}
	
	public static void stopMusic(String name) {
		music.get(name).stop();
	}
	
	public static void stopAllSounds() {
		for(int i = 0; i < sounds.size(); i++) {
			sounds.get(i).stop();
		}
	}
	
	public static void stopAllMusic() {
		for(int i = 0; i < music.size(); i++) {
			music.get(i).stop();
		}
	}
	
	public static void setMusicVolume(float volume) {
		for(int i = 0; i < music.size(); i++) {
			music.get(i).setVolume(volume);
		}
	}
	
}
