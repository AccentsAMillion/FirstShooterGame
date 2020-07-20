package com.woodward.chris.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.woodward.chris.ShootingTargets;
import com.woodward.chris.accessories.BlueEnemy;
import com.woodward.chris.accessories.Bullets;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Chris on 8/29/2017.
 */

public class MainGameScreen implements Screen {

    public static final float SPEED = 1500;
    public static final float HERO_ANIMATION_SPEED = 0.25F;
    public static final int HERO_WIDTH_PIXEL = 106;
    public static final int HERO_HEIGHT_PIXEL = 120;
    public static final int HERO_WIDTH = HERO_WIDTH_PIXEL * 3;
    public static final int HERO_HEIGHT = HERO_HEIGHT_PIXEL * 3;

    //public static final float ROLL_TIMER_SWITCH_TIME = 0.25f;

    public static final float MIN_MEGAMAN_SPAWN_TIME = 0.3f;
    public static final float MAX_MEGAMAN_SPAWN_TIME = 0.6f;

    Animation[] rolls;

   // Texture img;
    float x;
    float y;
    int roll;
   // float rollTiming;
    float stateTime;
    float megaManSpawnTimer;

    Random random;

    ShootingTargets game;

    ArrayList<Bullets> bullets;
    ArrayList<BlueEnemy> blueEnemies;

    public MainGameScreen (ShootingTargets game){
        this.game = game;
        y = 15;
        x = ShootingTargets.WIDTH /2 - HERO_WIDTH /2;
        bullets = new ArrayList<Bullets>();
        blueEnemies = new ArrayList<BlueEnemy>();

        random = new Random();
        megaManSpawnTimer = random.nextFloat() * (MAX_MEGAMAN_SPAWN_TIME - MIN_MEGAMAN_SPAWN_TIME) + MIN_MEGAMAN_SPAWN_TIME;

        roll = 2;
      //  rollTiming = 0;
        rolls = new Animation[5];

        TextureRegion[][] rollSpriteSheet = TextureRegion.split(new Texture("rambo_forward.png"),HERO_WIDTH_PIXEL, HERO_HEIGHT_PIXEL);


        rolls[roll] = new Animation(HERO_ANIMATION_SPEED, rollSpriteSheet[0]);
//        rolls[0] = new Animation(HERO_ANIMATION_SPEED, rollSpriteSheet[2]);
//        rolls[1] = new Animation(HERO_ANIMATION_SPEED, rollSpriteSheet[1]);
//        rolls[2] = new Animation(HERO_ANIMATION_SPEED, rollSpriteSheet[0]);
//        rolls[3] = new Animation(HERO_ANIMATION_SPEED, rollSpriteSheet[3]);
//        rolls[4] = new Animation(HERO_ANIMATION_SPEED, rollSpriteSheet[4]);

    }

    @Override
    public void show() {


    }

    @Override
    public void render(float delta) {

        //Bullet shooting
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            bullets.add(new Bullets(x + 4));
            bullets.add(new Bullets(x + HERO_WIDTH -4));

        }

        //BlueEnemy Spawning
        megaManSpawnTimer -= delta;
        if(megaManSpawnTimer <= 0) {
            megaManSpawnTimer = random.nextFloat() * (MAX_MEGAMAN_SPAWN_TIME - MIN_MEGAMAN_SPAWN_TIME) + MIN_MEGAMAN_SPAWN_TIME;
            blueEnemies.add(new BlueEnemy(random.nextInt(Gdx.graphics.getWidth() - BlueEnemy.WIDTH)));
        }

        //Update BlueEnemies
        ArrayList<BlueEnemy> megamanRemoved = new ArrayList<BlueEnemy>();
        for (BlueEnemy blueEnemy : blueEnemies){
            blueEnemy.update(delta);
            if(blueEnemy.remove)
                megamanRemoved.add(blueEnemy);
        }
        blueEnemies.removeAll(megamanRemoved);

        //Bullet update
        ArrayList<Bullets> bulletsRemoved = new ArrayList<Bullets>();
        for (Bullets bullet : bullets){
            bullet.update(delta);
            if(bullet.remove)
                bulletsRemoved.add(bullet);
        }
        bullets.removeAll(bulletsRemoved);

       //Hero Movement
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            y += SPEED * Gdx.graphics.getDeltaTime();


        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            y -= SPEED * Gdx.graphics.getDeltaTime();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            x -= SPEED * Gdx.graphics.getDeltaTime();

            if(x <= 0)
                x = 0;
//
//            if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && !Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && roll > 0) {
//                rollTiming = 0;
//                roll--;
//            }
//
//            rollTiming -= Gdx.graphics.getDeltaTime();
//            if(Math.abs(rollTiming) > ROLL_TIMER_SWITCH_TIME && roll > 0) {
//                rollTiming =  0;
//                roll--;
//
//            }
//        }else {
//            if (roll < 2){
//                rollTiming += Gdx.graphics.getDeltaTime();
//                if(Math.abs(rollTiming) > ROLL_TIMER_SWITCH_TIME && roll < 4) {
//                    rollTiming = 0;
//                    roll++;
//                }
//            }

        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            x += SPEED * Gdx.graphics.getDeltaTime();

            if(x <= 4)
                x = 4;

//            if (x + HERO_WIDTH > Gdx.graphics.getWidth())
//                x = Gdx.graphics.getWidth() - HERO_WIDTH;
//
//            rollTiming += Gdx.graphics.getDeltaTime();
//            if (Math.abs(rollTiming) > ROLL_TIMER_SWITCH_TIME && roll < 4) {
//                rollTiming = 0;
//                roll++;
//
//            }
//        }else{
//            if (roll > 2) {
//                if (Math.abs(rollTiming) > ROLL_TIMER_SWITCH_TIME && roll > 0) {
//                    rollTiming = 0;
//                    roll--;
//                }
//            }
        }

        stateTime += delta;


        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        for (Bullets bullet : bullets) {
            bullet.render(game.batch);
        }

        for (BlueEnemy blueEnemy : blueEnemies) {
            blueEnemy.render(game.batch);
        }
        game.batch.draw((TextureRegion) rolls[roll].getKeyFrame(stateTime, true), x, y, HERO_WIDTH, HERO_HEIGHT);
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
