package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ResourceLoader;

public class GameOverScreen extends ScreenAdapter{
    private final OrthographicCamera camera;
    private final SpriteBatch batch;

    public GameOverScreen(){
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
        renderText(0, 8, "You grasp at your chest. Your hand comes back dark and sticky. You feel your strength leaving you.");
        renderText(0, 7, "You fall to the floor cold, alone and dead.");
        renderText(0, 4, "Continue?");
        batch.end();

        if (Gdx.input.isTouched()) {
            MyGdxGame.myGdxGame.setScreen(new MainMenuScreen());
            dispose();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }
    }

    // TODO move this to a better place (utils?)
    private void renderText(float xPos, float yPos, String text){
        BitmapFont.TextBounds bounds = ResourceLoader.titleFont.getBounds(text);
        float lineHeight = bounds.height+5;

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
