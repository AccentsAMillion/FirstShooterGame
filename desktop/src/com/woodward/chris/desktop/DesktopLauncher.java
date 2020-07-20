package com.woodward.chris.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.woodward.chris.ShootingTargets;

public class DesktopLauncher {
	public static void main (String[] arg) {

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.foregroundFPS = 160;
        config.width = ShootingTargets.WIDTH;
        config.height = ShootingTargets.HEIGHT;
        config.resizable = false;
		new LwjglApplication(new ShootingTargets(), config);
	}
}
