package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Dungeon.DungeonGenerator;
import com.mygdx.game.GameHandler;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Player.PlayerCharacter;
import com.mygdx.game.InputHandlers.PlayerInputHandler;
import com.mygdx.game.Renderers.TokenRenderer;
import com.mygdx.game.ResourceLoader;
import com.mygdx.game.Tokens.Tokens;

public class GameScreen implements Screen{
    private PlayerInputHandler inputHandler;
    private SpriteBatch batch;
    private OrthographicCamera camera;

    public GameScreen() {
        GameHandler.dungeonGenerator = new DungeonGenerator();
        GameHandler.dungeon = GameHandler.dungeonGenerator.generateDungeon(50, 50, 20);
        GameHandler.player = new PlayerCharacter();
        GameHandler.tokens = new Tokens();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.zoom = 0.5f;

        inputHandler = new PlayerInputHandler(GameHandler.player);
        batch = new SpriteBatch();

        GameHandler.dungeonGenerator.spawnMonsters(GameHandler.dungeon.getRoomCount());
        GameHandler.dungeon.monsters.add(GameHandler.player);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        updateCamera();
        batch.setProjectionMatrix(camera.combined);

        Gdx.graphics.setTitle(Gdx.graphics.getFramesPerSecond()+"");

        batch.begin();
        GameHandler.dungeon.renderer.render(delta, batch);
        GameHandler.player.renderer.render(delta, batch);
        GameHandler.tokens.renderer.render(delta, batch);

        batch.end();
    }

    public void updateCamera(){
        camera.position.x = GameHandler.player.getPosition().x * GameHandler.dungeon.getTileSize();
        camera.position.y = GameHandler.player.getPosition().y * GameHandler.dungeon.getTileSize();
        camera.update();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputHandler);
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
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
