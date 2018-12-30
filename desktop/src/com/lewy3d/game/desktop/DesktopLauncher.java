package com.lewy3d.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.lewy3d.game.MainGame;
import com.lewy3d.game.Settings;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = Settings.NAME;
		config.width = 1000;
		config.height = 720;
		config.x = 500;

		new LwjglApplication(new MainGame(), config);
	}
}
