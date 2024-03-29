package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.MoneyRainClass;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
		config.useVsync(true);
		//	config.setWindowedMode(300,300);
		config.setTitle("MoneyRain");
		new Lwjgl3Application(new MoneyRainClass(), config);
	}
}
