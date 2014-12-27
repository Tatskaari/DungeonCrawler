package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Dungeon.DungeonGenerator;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Player.PlayerCharacter;
import com.mygdx.game.InputHandlers.PlayerInputHandler;

/**
 * Created by jony1710 on 24/12/2014.
 */
public class GameScreen implements Screen{
    private PlayerInputHandler inputHandler;
    private SpriteBatch batch;
    private OrthographicCamera camera;

    public GameScreen() {
        MyGdxGame.dungeonGenerator = new DungeonGenerator();
        MyGdxGame.dungeon = MyGdxGame.dungeonGenerator.generateDungeon(100, 100, 200);
        MyGdxGame.player = new PlayerCharacter();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.zoom = 0.5f;

        inputHandler = new PlayerInputHandler(MyGdxGame.player);
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        updateCamera();
        batch.setProjectionMatrix(camera.combined);

        Gdx.graphics.setTitle(Gdx.graphics.getFramesPerSecond()+"");

        batch.begin();
        MyGdxGame.dungeon.renderer.render(delta, batch);
        MyGdxGame.player.renderer.render(delta, batch);
        batch.end();
    }

    public void updateCamera(){
        camera.position.x = MyGdxGame.player.getPosition().x * MyGdxGame.dungeon.getTileSize();
        camera.position.y = MyGdxGame.player.getPosition().y * MyGdxGame.dungeon.getTileSize();
        camera.update();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputHandler);
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
        batch.dispose();
    }
}
