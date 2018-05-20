package com.exclnetworks.nort.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.exclnetworks.nort.NortGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = NortGame.WIDTH;
		config.height = NortGame.HEIGHT;
		config.title = NortGame.TITLE;
		new LwjglApplication(new NortGame(), config);
	}
}
