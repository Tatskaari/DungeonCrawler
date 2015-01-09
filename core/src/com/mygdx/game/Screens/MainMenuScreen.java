package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ResourceLoader;

public class MainMenuScreen extends ScreenAdapter{
    private OrthographicCamera camera;
    private SpriteBatch batch;

    public MainMenuScreen(){
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2,0);
        camera.zoom = 1;
        batch = new SpriteBatch();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        renderText(0, 1, "You find yourself in an ancient sewer with nowhere to go but down.");
        renderText(0,-1, "Click anywhere to descend!");
        batch.end();

        if (Gdx.input.isTouched()) {
            MyGdxGame.myGdxGame.setScreen(new GameScreen());
            dispose();
        }
    }

    private void renderText(float xPos, float yPos, String text){
        BitmapFont.TextBounds bounds = ResourceLoader.titleFont.getBounds(text);
        float lineHeight = bounds.height+2;

        xPos = (Gdx.graphics.getWidth()-bounds.width)/2 + lineHeight*xPos;
        yPos = (Gdx.graphics.getHeight()-bounds.height)/2 + lineHeight*yPos;

        ResourceLoader.titleFont.draw(batch, text, xPos, yPos);
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.position.set(width/2,height/2,0);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
