package org.cameronmoreau.balloony;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Background {
	
	private float speed;
	private TextureRegion[] bg;
	private float[] x;
	
	private float dh;
	
	public Background(float dh) {
		speed = 100;
		this.dh = dh;
		bg = new TextureRegion[2];
		x = new float[2];
		bg[0] = GameImages.getImage("background");
		bg[1] = bg[0];
		
		x[0] = 0;
		x[1] = bg[0].getRegionWidth() - 2;
	}
	
	public void Update(float delta) {
		x[0] -= speed * delta;
		x[1] -= speed * delta;
		
		if(x[0] + bg[0].getRegionWidth() < 0)
			x[0] = x[1] + bg[1].getRegionWidth() - 2;
		
		if(x[1] + bg[1].getRegionWidth() < 0)
			x[1] = x[0] + bg[0].getRegionWidth() - 2;
	} 
	
	public void Draw(SpriteBatch batch) {
		batch.begin();
		batch.draw(bg[0], x[0], 0, bg[0].getRegionWidth(), dh);
		batch.draw(bg[1], x[1], 0, bg[1].getRegionWidth(), dh);
		batch.end();
	}

}
