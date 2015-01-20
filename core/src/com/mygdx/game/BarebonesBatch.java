package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;


class BarebonesBatch extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture texture;
    private Vector3 position;
    private OrthographicCamera cam;

    public void create () {
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("res/player.png"));
        position = new Vector3(0,0,0);
        cam = new OrthographicCamera();
        cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void render () {
        handleInput();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cam.position.y = 25 * position.y;
        cam.update();
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        batch.draw(texture, 100, 25 * position.y);
        batch.end();
    }

    private void handleInput(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.W)){
            position.y += 1;
        }
    }

}