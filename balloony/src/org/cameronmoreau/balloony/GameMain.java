package org.cameronmoreau.balloony;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class GameMain extends Game {
	private OrthographicCamera camera;
	
	@Override
	public void create() {		
		Texture.setEnforcePotImages(false);
		camera = new OrthographicCamera();
		camera.setToOrtho(true, 400, 600);
		
		GameImages.loadImage("images/balloon.png", "balloon");
		GameImages.loadImage("images/background.png", "background");
		GameImages.loadImage("images/pipeSide.png", "pipeSide");
		GameImages.loadImage("images/pipeTop.png", "pipeTop");
		GameImages.loadImage("images/menu_button.png", "menu_button");
		GameImages.loadImage("images/popup.png", "popup");
		GameImages.loadImage("images/title.png", "title");
		Jukebox.load("sounds/pop.wav", "pop");
		Jukebox.load("sounds/wind.wav", "wind");
		Jukebox.load("sounds/score.wav", "score");
		Jukebox.load("sounds/select.wav", "select");
		Jukebox.loadMusic("sounds/Monkeys_Spinning_Monkeys.mp3", "background");
		
		setScreen(new MenuScreen(this, camera));
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {		
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
