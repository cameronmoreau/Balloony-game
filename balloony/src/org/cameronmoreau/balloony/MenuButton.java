package org.cameronmoreau.balloony;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class MenuButton extends Actor {
	
	private BitmapFont font;
	private String text;
	private TextureRegion bg;
	private float x, y;
	private float width, height;
	private float dw;
	
	public static final int MENU = 0;
	public static final int PREF = 1;
	public static final int POP = 2;

	public MenuButton(BitmapFont font, String text, float x, float y, int type, float dw) {
		this.font = font;
		this.text = text;
		this.x = x;
		this.y = y;
		this.dw = dw;
		genType(type);
	}
	
	public void Update(float delta) {
		
	}
	
	public void Draw(SpriteBatch batch) {
		batch.begin();
		batch.draw(bg, x, y, width, height);
		font.draw(batch, text, x + (width - font.getBounds(text).width) / 2, y + 10);
		batch.end();
	}
	
	public Rectangle getRectangle() {
		return new Rectangle(x, y, width, height);
	}
	
	public float getY() { return y; }
	
	private void genType(int type) {
		if(type == MenuButton.MENU) {
			width = 250;
			height = 50;
			bg = GameImages.getImage("menu_button");
			x = (dw - width) / 2;
		} else if(type == MenuButton.PREF) {
			
		} else if(type == MenuButton.POP) {
			width = 150;
			height = 50;
			bg = GameImages.getImage("menu_button");
		}
	}
	
	public Actor hit (float x, float y, boolean touchable) {
        if (touchable && getTouchable() != Touchable.enabled) return null;
        return x >= 0 && x < width && y >= 0 && y < height ? this : null;
}
	
}
