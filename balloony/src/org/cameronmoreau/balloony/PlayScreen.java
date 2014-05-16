package org.cameronmoreau.balloony;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class PlayScreen implements Screen {
	
	private GameMain game;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private ShapeRenderer shape;
	private BitmapFont scoreFont;
	
	private Background bg;
	private Balloon balloon;
	private GamePopup popup;
	private ArrayList<Pipe> pipes;
	
	private float gTime = 255;
	
	private int pipeCount = 0;
	private int score = 0;
	
	public PlayScreen(GameMain game, OrthographicCamera camera) {
		this.game = game;
		this.camera = camera;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		if(balloon.isAlive()) {
			gTime++;
		}
		if(gTime > 255) {
			gTime = 0;
			pipeCount++;
			pipes.add(new Pipe(pipeCount, camera.viewportWidth, camera.viewportHeight));
		}
		
		if(Gdx.input.justTouched()) {
			if(balloon.isAlive()) {
				balloon.Jump();
				Jukebox.stop("wind");
				Jukebox.play("wind");
			} else if(popup.isReady()) {
				game.setScreen(new MenuScreen(game, camera));
			}
		}
		
		camera.update();
		if(balloon.isAlive()) {
			bg.Update(delta);
			balloon.Update(delta);
			for(Pipe p : pipes) {
				p.Update(delta);
				if(balloon.getRect().overlaps(p.getRectTop()) || 
						balloon.getRect().overlaps(p.getRectBottom())) {
					//Dead
					Jukebox.play("pop");
					balloon.setAlive(false);
				} else if(balloon.getRect().overlaps(p.getRectScore())) {
					if(score < p.getId()) {
						score = p.getId();
						Jukebox.play("score");
					}
				}
			}
			if(balloon.outOfBounds(Gdx.graphics.getHeight())) {
				balloon.setAlive(false);
			}
		} else {
			popup.Update(delta);
		}
		
		batch.setProjectionMatrix(camera.combined);
		shape.setProjectionMatrix(camera.combined);
		bg.Draw(batch);
		for(Pipe p : pipes) {
			p.Draw(batch, shape);
		}
		balloon.Draw(batch);
		
		
		batch.begin();
		scoreFont.draw(batch, "Score: " + Integer.toString(score), 10, 10);
		batch.end();
		
		if(!balloon.isAlive()) {
			Jukebox.stopMusic("background");
			popup.setScore(score);
			popup.Show(batch, shape, scoreFont);
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(null);
		batch = new SpriteBatch();
		shape = new ShapeRenderer();
		scoreFont = new BitmapFont(Gdx.files.internal("fonts/white_32.fnt"), true);
		
		bg = new Background(camera.viewportHeight);
		balloon = new Balloon(camera.viewportHeight);
		popup = new GamePopup(score, camera.viewportWidth, camera.viewportHeight);
		pipes = new ArrayList<Pipe>();
		
		Jukebox.loopMusic("background");
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		batch.dispose();
		shape.dispose();
		scoreFont.dispose();
	}

}
