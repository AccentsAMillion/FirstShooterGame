package com.woodward.chris;

import com.badlogic.gdx.Game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.woodward.chris.screens.MainGameScreen;
import com.woodward.chris.screens.MainMenuScreen;

public class ShootingTargets extends Game {

    public static final int WIDTH = 1080;
    public static final int HEIGHT = 1920;

	public SpriteBatch batch;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
        this.setScreen(new MainMenuScreen(this));

	}

	@Override
	public void render () {
        super.render();


    }

	
	@Override
	public void dispose () {

	}
}
