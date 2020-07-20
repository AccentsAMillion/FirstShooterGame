package com.woodward.chris.accessories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Chris on 8/31/2017.
 */

public class BlueEnemy {

    public static final int MEGAMAN_SPEED = 200;
    public static final int WIDTH = 106;
    public static final int HEIGHT = 120;
    private static Texture texture;

    float x, y;
    public boolean remove = false;

    public BlueEnemy (float x){
        this.x = x;
        this.y = Gdx.graphics.getHeight();

        if (texture == null)
            texture = new Texture("megaman.png");
    }

    public void update (float deltaTime) {
        y -= MEGAMAN_SPEED * deltaTime;
        if (y < -HEIGHT)
            remove = true;
    }

    public void render (SpriteBatch batch){
        batch.draw(texture, x , y);
    }
}


