package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.game.LineOfSight;
import com.mygdx.game.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		startApp();
	}

	private static void startApp(){
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.vSyncEnabled = true;
		config.width = 1024;
		config.height = 768;
		config.resizable = true;
		new LwjglApplication(new MyGdxGame(), config);

//		LineOfSight.runLine(new GridPoint2(2, 6), new GridPoint2(2, 2));
	}
}
