package org.cameronmoreau.balloony;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuScreen implements Screen {
	
	private GameMain game;
	private OrthographicCamera camera;
	
	private BitmapFont white32;
	private SpriteBatch batch;
	
	private Background bg;
	
	private TextureRegion title;
	
	private Skin skin;
	private Stage stage;
	private Table table;
	private TextButton play, rate, quit;

	public MenuScreen(GameMain game, OrthographicCamera camera) {
		this.game = game;
		this.camera = camera;
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		
		camera.update();
		bg.Update(delta);
		stage.act(delta);
		table.setBounds(0, 0, camera.viewportWidth, camera.viewportHeight);
		
		batch.setProjectionMatrix(camera.combined);
		bg.Draw(batch);
		batch.begin();
		batch.draw(title, (camera.viewportWidth - title.getRegionWidth()) / 2, 50);
		batch.end();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		stage = new Stage(camera.viewportWidth, camera.viewportHeight, true);
		Gdx.input.setInputProcessor(stage);
		table = new Table();
		batch = new SpriteBatch();
		bg = new Background(camera.viewportHeight);
		
		title = GameImages.getImage("title");
		white32 = new BitmapFont(Gdx.files.internal("fonts/white_32.fnt"));
		
		skin = new Skin(new TextureAtlas(Gdx.files.internal("images/skins.pack")));
		TextButtonStyle bStyle = new TextButtonStyle();
		bStyle.up = skin.getDrawable("menu_button");
		bStyle.font = white32;
		play = new TextButton("Play", bStyle);
		rate = new TextButton("Rate", bStyle);
		quit = new TextButton("Quit", bStyle);
		
		table.setBounds(0, 0, camera.viewportWidth, camera.viewportHeight);
		table.add(play).padBottom(15);
		table.row();
		table.add(rate).padBottom(15);
		table.row();
		table.add(quit);
		stage.addActor(table);
		
		play.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new PlayScreen(game, camera));
			}
		});
		rate.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.net.openURI("https://play.google.com/store/apps/details?id=org.cameronmoreau.balloony");
			}
		});
		quit.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});
		
		Jukebox.stopMusic("background");
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
		white32.dispose();
	}

}
