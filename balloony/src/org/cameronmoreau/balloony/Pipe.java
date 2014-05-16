package org.cameronmoreau.balloony;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class Pipe {
	
	private float gapSize = 150;
	private float width = 100;
	private float height;
	private float startY, endY;
	
	private float screenWidth;
	private float margin = 25;
	
	private boolean moving = false;
	
	private TextureRegion pipeTop;
	
	private float x;
	private int id;
	
	private int move = 0;
	private float moveX = 75;
	private float moveY = 10;

	public Pipe(int id, float screenWidth, float screenHeight) {
		this.id = id;
		this.screenWidth = screenWidth;
		this.height = screenHeight;
		this.x = screenWidth;
		pipeTop = GameImages.getImage("pipeTop");
		
		generateGap();
		setSpeeds();
	}
	
	public void Update(float delta) {
		x -= moveX * delta;
		
		if(moving) {
			if(startY >= height / 2) {
				move = 1;
			} else if(startY <= height / 2) {
				move = 2;
			}
		}
		
		if(move == 1) {
			if(startY >= 0) {
				startY -= moveY * delta;
				endY -= moveY * delta;
			}
		} else if(move == 2) {
			if(endY <= height) {
				startY += moveY * delta;
				endY += moveY * delta;
			}
		}
	}
	
	public void Draw(SpriteBatch batch, ShapeRenderer shape) {
		shape.begin(ShapeType.Filled);
		shape.setColor(Color.valueOf("e0d1af"));
		shape.rect(x, 0, width, startY);
		shape.rect(x, endY, width, height - endY);
		shape.end();
		
		batch.begin();
		batch.draw(pipeTop, x, endY, width, 30);
		batch.draw(pipeTop, x, startY - 30, width, 30);
		batch.end();
	}
	
	public float getGapSize() { return gapSize; }
	
	private void generateGap() {
		startY = new Random().nextFloat() * (height - margin - gapSize) + margin;
		endY = startY + gapSize;
		
		if(id > 3) {
			int random = new Random().nextInt(3) ;
			if(random == 1)
				moving = false;
			else
				moving = true;
		}
	}
	
	private void setSpeeds() {
		if(id > 10)
			moveY = 30;
		else if(id > 20)
			moveY = 50;
		else if(id > 30)
			moveY = 70;
	}
	
	public int getId() { return id; }
	public float checkDestory() { return x + width; }
	public Rectangle getRectTop() { return new Rectangle(x, 0, width, startY); }
	public Rectangle getRectBottom() { return new Rectangle(x, endY, width, height - endY); }
	public Rectangle getRectScore() { return new Rectangle(x + 5, startY, 25, height - endY); }

}
