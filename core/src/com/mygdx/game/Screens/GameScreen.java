package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.Dungeon.DungeonGenerator;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Player.PlayerCharacter;
import com.mygdx.game.InputHandlers.PlayerInputHandler;

/**
 * Created by jony1710 on 24/12/2014.
 */
public class GameScreen implements Screen{
    private PlayerInputHandler inputHandler;

    public GameScreen() {
        MyGdxGame.dungeonGenerator = new DungeonGenerator();
        MyGdxGame.dungeon = MyGdxGame.dungeonGenerator.generateDungeon(100, 100, 200);
        MyGdxGame.player = new PlayerCharacter();
        MyGdxGame.camera.zoom = 0.5f;

        inputHandler = new PlayerInputHandler(MyGdxGame.player);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        MyGdxGame.camera.update();
        MyGdxGame.batch.setProjectionMatrix(MyGdxGame.camera.combined);

        Gdx.graphics.setTitle(Gdx.graphics.getFramesPerSecond()+"");

        MyGdxGame.batch.begin();
        MyGdxGame.dungeon.render();
        MyGdxGame.player.render();
        MyGdxGame.batch.end();
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

    }
}
