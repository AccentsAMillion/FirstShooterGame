package com.woodward.chris.accessories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/**
 * Created by Chris on 8/31/2017.
 */

public class Bullets {

    public static final int BULLET_SPEED = 500;
    public static final int DEFAULT_Y = 40;
    private static Texture texture;

    float x, y;

    public boolean remove = false;

    public Bullets (float x){
        this.x = x;
        this.y = DEFAULT_Y;

        if (texture == null)
            texture = new Texture("bullet.png");
    }

    public void update (float deltaTime) {
        y += BULLET_SPEED * deltaTime;
        if (y > Gdx.graphics.getHeight()){
            remove = true;
        }
    }

    public void render (SpriteBatch batch){
        batch.draw(texture, x , y);
    }
}
