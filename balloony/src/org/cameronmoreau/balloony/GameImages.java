package org.cameronmoreau.balloony;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameImages {
	
	private static HashMap<String, TextureRegion> textures;
	
	static {
		textures = new HashMap<String, TextureRegion>();
	}
	
	public static void loadImage(String path, String name) {
		TextureRegion texture = new TextureRegion(new Texture(Gdx.files.internal(path)));
		texture.flip(false, true);
		textures.put(name, texture);
	}
	
	public static TextureRegion getImage(String name) {
		return textures.get(name);
	}

}
