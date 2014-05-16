package org.cameronmoreau.balloony;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GamePopup {

	private int score, hs;
	private float x, y;
	private float width;
	private float height = 100;
	private float startY;
	private float dw;
	private Preferences prefs;
	
	private TextureRegion bg;
	
	boolean ready = false;
	
	public GamePopup(int score, float dw, float dh) {
		this.dw = dw;
		this.score = score;
		width = dw / 1.5f;
		x = (dw - width) / 2;
		y = (dh - height) / 2;
		startY = dh;
		bg = GameImages.getImage("popup");
		prefs = Gdx.app.getPreferences("gamedata");
		hs = prefs.getInteger("high");
	}
	
	public void Show(SpriteBatch batch, ShapeRenderer shape, BitmapFont font) {
		batch.begin();
		batch.draw(bg, x, startY, width, height);
		font.draw(batch, "Your score: " + Integer.toString(score), (dw - font.getBounds("Your score: " + Integer.toString(score)).width) / 2, startY + 10);
		font.draw(batch, "High score: " + Integer.toString(hs), (dw - font.getBounds("High score: " + Integer.toString(hs)).width) / 2, startY + 50);
		font.draw(batch, "Touch to close", (dw - font.getBounds("Touch to close").width) / 2, startY + 115);
		if(hs < score)
			font.draw(batch, "New high score!", (dw - font.getBounds("New high score!").width) / 2, startY - 40);
		if(hs == score)
			font.draw(batch, "So close...", (dw - font.getBounds("So close...").width) / 2, startY - 40);
		batch.end();
	}
	
	public void Update(float delta) {
		if(startY > y) {
			startY -= 2000 * delta;
		}
		else
			ready = true;
	}
	
	public void setScore(int score) {
		this.score = score;
		if(score > hs) {
			prefs.putInteger("high", score);
			prefs.flush();
		}
	}
	public boolean isReady() { return ready; }
	
}
