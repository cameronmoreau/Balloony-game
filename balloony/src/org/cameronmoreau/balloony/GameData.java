package org.cameronmoreau.balloony;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.badlogic.gdx.Gdx;

public class GameData {
	
	private static String filePath = "hs.sav";
	
	public static void save(int score) {
		try{
			FileOutputStream out = new FileOutputStream(filePath);
			ObjectOutputStream os = new ObjectOutputStream(out);
			os.write(score);
			os.close();
			out.close();
		} catch(Exception e) {
			e.printStackTrace();
			Gdx.app.exit();
		}
	}
	
	public static int load() {
		try{
			FileInputStream in = new FileInputStream(filePath);
			ObjectInputStream is = new ObjectInputStream(in);
			int x = is.readInt();
			is.close();
			in.close();
			return x;
		} catch(Exception e) {
			e.printStackTrace();
			Gdx.app.exit();
		}
		return 0;
	}
	
}
