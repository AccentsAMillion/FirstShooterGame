package com.woodward.chris.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.woodward.chris.ShootingTargets;

/**
 * Created by Chris on 8/29/2017.
 */

public class MainMenuScreen  implements Screen{

    private static final int EXIT_BUTTON_WIDTH = 700;
    private static final int EXIT_BUTTON_HEIGHT = 600;
    private static final int PLAY_BUTTON_WIDTH = 700;
    private static final int PLAY_BUTTON_HEIGHT = 600;
    private static final int EXIT_BUTTON_Y = 100;
    private static final int PLAY_BUTTON_Y = 100;
    private static final int GAME_LOGO_WIDTH = 2200;
    private static final int GAME_LOGO_HEIGHT = 500;
    private static final int GAME_LOGO_Y = 100;



    ShootingTargets game;

    Texture playButtonOn;
    Texture playButtonOff;
    Texture exitButtonOn;
    Texture exitButtonOff;
    Texture gameLogoOn;

    public MainMenuScreen (ShootingTargets game){
        this.game = game;
        playButtonOn = new Texture("active_play_button.png");
        exitButtonOn = new Texture("active_exit_button.png");
        gameLogoOn = new Texture("gamelogo.jpg");

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        int x = ShootingTargets.WIDTH /2 - GAME_LOGO_WIDTH /2;
        if (Gdx.input.getX() < x + GAME_LOGO_WIDTH && Gdx.input.getX() > x && ShootingTargets.HEIGHT - Gdx.input.getY() < GAME_LOGO_Y + GAME_LOGO_HEIGHT && ShootingTargets.HEIGHT - Gdx.input.getY() > GAME_LOGO_Y){
            game.batch.draw(gameLogoOn, 100, 850, GAME_LOGO_WIDTH, GAME_LOGO_HEIGHT);
            if (Gdx.input.isTouched()) {
                Gdx.app.exit();
            }
        }else {
            game.batch.draw(gameLogoOn, 100, 850, GAME_LOGO_WIDTH, GAME_LOGO_HEIGHT);
        }

        x = ShootingTargets.WIDTH /2 - EXIT_BUTTON_WIDTH /2;
        if (Gdx.input.getX() < x + EXIT_BUTTON_WIDTH && Gdx.input.getX() > x && ShootingTargets.HEIGHT - Gdx.input.getY() < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT && ShootingTargets.HEIGHT - Gdx.input.getY() > EXIT_BUTTON_Y){
            game.batch.draw(exitButtonOn, 350, 200, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {
            Gdx.app.exit();
            }
        }else {
            game.batch.draw(exitButtonOn, 350, 200, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
        }

        x = ShootingTargets.WIDTH /2 - PLAY_BUTTON_WIDTH /2;
        if (Gdx.input.getX() < x + PLAY_BUTTON_WIDTH && Gdx.input.getX() > x && ShootingTargets.HEIGHT - Gdx.input.getY() < PLAY_BUTTON_Y + PLAY_BUTTON_HEIGHT && ShootingTargets.HEIGHT - Gdx.input.getY() > PLAY_BUTTON_Y){
            game.batch.draw(playButtonOn, 1500, 200, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
        }
            if (Gdx.input.isTouched()) {
            this.dispose();
            game.setScreen(new MainGameScreen(game));
        }else {
                game.batch.draw(playButtonOn, 1500, 200, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);

            }


        game.batch.end();


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
