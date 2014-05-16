package org.cameronmoreau.balloony;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class SettingsScreen implements Screen, InputProcessor {
	
	private GameMain game;
	private OrthographicCamera camera;
	
	private BitmapFont white32;
	private SpriteBatch batch;
	private ShapeRenderer shape;
	
	private String title = "Settings";
	
	private ArrayList<MenuButton> menuButtons;

	public SettingsScreen(GameMain game, OrthographicCamera camera) {
		this.game = game;
		this.camera = camera;
	}
	

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		for(MenuButton b : menuButtons) {
			b.Update(delta);
		}
		
		batch.setProjectionMatrix(camera.combined);
		shape.setProjectionMatrix(camera.combined);
		
		shape.begin(ShapeType.Filled);
		shape.setColor(Color.valueOf("8dd35f"));
		shape.rect(0, 0, camera.viewportWidth, camera.viewportHeight);
		shape.end();
		
		batch.begin();
		white32.draw(batch, "Settings", (camera.viewportWidth - white32.getBounds(title).width) / 2, camera.viewportHeight - 25);
		batch.end();
		for(MenuButton b : menuButtons) {
			b.Draw(batch);
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);
		batch = new SpriteBatch();
		shape = new ShapeRenderer();
		
		white32 = new BitmapFont(Gdx.files.internal("fonts/white_32.fnt"), false);
		menuButtons = new ArrayList<MenuButton>();
		//menuButtons.add(new MenuButton(white32, "Play", camera.viewportHeight - 250, MenuButton.MENU, camera.viewportWidth));
		//menuButtons.add(new MenuButton(white32, "Unlockables", menuButtons.get(0).getY() - 70, MenuButton.MENU, camera.viewportWidth));
		//menuButtons.add(new MenuButton(white32, "Settings", menuButtons.get(1).getY() - 70, MenuButton.MENU, camera.viewportWidth));
		menuButtons.add(new MenuButton(white32, "Back to menu", 0, 25, MenuButton.MENU, camera.viewportWidth));
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
		white32.dispose();
	}
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		Rectangle touch = new Rectangle((float)screenX, ((float)screenY - Gdx.graphics.getHeight()) * -1, 1, 1);		
		for(int i = 0; i < menuButtons.size(); i++) {
			Rectangle item = menuButtons.get(i).getRectangle();
			if(touch.overlaps(item)) {
				Jukebox.play("select");
				if(i == 0) {
					game.setScreen(new MenuScreen(game, camera));
				} else if(i == 1) {
					
				}
			}
		}
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
