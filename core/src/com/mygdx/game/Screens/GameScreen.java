package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Dungeon.DungeonGenerator;
import com.mygdx.game.GameHandler;
import com.mygdx.game.Player.PlayerCharacterEntity;
import com.mygdx.game.InputHandlers.PlayerInputHandler;
import com.mygdx.game.ResourceLoader;
import com.mygdx.game.Tokens.Tokens;
import com.mygdx.game.UserInterface.UserInterface;

public class GameScreen extends ScreenAdapter {
    private InputMultiplexer inputMultiplexer;
    private PlayerInputHandler playerInputHandler;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private UserInterface ui;

    public GameScreen() {
        GameHandler.dungeonGenerator = new DungeonGenerator();
        GameHandler.dungeon = GameHandler.dungeonGenerator.generateDungeon(50, 50, 20);
        GameHandler.player = new PlayerCharacterEntity();
        GameHandler.tokens = new Tokens();

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.zoom = 0.5f;

        playerInputHandler = new PlayerInputHandler(GameHandler.player);
        batch = new SpriteBatch();

        GameHandler.dungeonGenerator.spawnMonsters(GameHandler.dungeon.getRoomCount());
        GameHandler.dungeon.monsters.add(GameHandler.player);

        ui = new UserInterface();

        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(playerInputHandler);
        inputMultiplexer.addProcessor(ui.getInputProcessor());
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

        ui.draw(delta);
    }

    public void updateCamera(){
        camera.position.x = GameHandler.player.getPosition().x * ResourceLoader.getTileSize();
        camera.position.y = GameHandler.player.getPosition().y * ResourceLoader.getTileSize();
        camera.update();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();

        ui.resize(width, height);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
