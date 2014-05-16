package org.cameronmoreau.balloony;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Balloon {
	
	private float x, y;
	private float width, height;
	private boolean alive = true;


	private float velX = 0;
	private float velY = 0;
	private boolean jumping = false;
	private float gravity = 10;
	private float jumpStart = -250;
	private float maxFallSpeed = 400;
	
	private TextureRegion image;
	
	public Balloon(float dh) {
		image = GameImages.getImage("balloon");
		
		width = 35;
		height = 50;
		x = 50;
		y = (dh - height) / 2;
	}
	
	public void Update(float delta) {
		x += velX * delta;
		y -= velY * delta;
		
		//Jumping
		if(jumping) {
			velY = jumpStart;
			//width = 45;
			//image.setRotation(-25);
			jumping = false;
		}
		if(!jumping) {
			velY += gravity;
			if(velY > maxFallSpeed)
				velY = maxFallSpeed;
			}
			else
				velY = 0;
	}
	
	public void Jump() {
		jumping = true;
	}
	
	public void Draw(SpriteBatch batch) {
		batch.begin();
		batch.draw(image, x, y + 30, width, height + 30);
		batch.end();
	}
	
	public Rectangle getRect() {
		return new Rectangle(x, y + 30, width, height);
	}
	
	public boolean outOfBounds(float dh) {
		if(y > dh || y + height < 0)
			return true;
		else
			return false;
	}
	
	public void setAlive(boolean b) { alive = b; }
	public boolean isAlive() { return alive; }

}
