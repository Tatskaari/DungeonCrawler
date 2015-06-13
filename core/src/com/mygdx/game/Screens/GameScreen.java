package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.Dungeon.DungeonGenerator;
import com.mygdx.game.Dungeon.DungeonGeneratorFactory;
import com.mygdx.game.GameHandler;
import com.mygdx.game.InputHandlers.GameInputHandler;
import com.mygdx.game.Player.PlayerCharacterEntity;
import com.mygdx.game.InputHandlers.PlayerInputHandler;
import com.mygdx.game.ResourceLoader;
import com.mygdx.game.Tokens.Tokens;
import com.mygdx.game.UserInterface.UserInterface;

class GameScreen extends ScreenAdapter {
    private final InputMultiplexer inputMultiplexer;
    private final SpriteBatch batch;
    private final OrthographicCamera camera;
    private final UserInterface ui;

    public GameScreen() {
        DungeonGenerator dungeonGenerator = DungeonGeneratorFactory.getDefaultDungeonGenerator();
        Dungeon dungeon = dungeonGenerator.generateDungeon();
        Dungeon.setActiveDungeon(dungeon);
        GameInputHandler gameInputHandler = new GameInputHandler();
        PlayerCharacterEntity player = PlayerCharacterEntity.getInstance();
        player.respawn();
        PlayerInputHandler playerInputHandler = new PlayerInputHandler(PlayerCharacterEntity.getInstance());

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.zoom = 0.5f;

        batch = new SpriteBatch();

        dungeonGenerator.spawnMonsters(dungeon, dungeon.getRoomCount());
        dungeon.monsters.add(PlayerCharacterEntity.getInstance());

        ui = new UserInterface();

        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(playerInputHandler);
        inputMultiplexer.addProcessor(ui.getInputProcessor());
        inputMultiplexer.addProcessor(gameInputHandler);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        updateCamera();
        batch.setProjectionMatrix(camera.combined);

        Gdx.graphics.setTitle(Gdx.graphics.getFramesPerSecond()+"");

        batch.begin();
        Dungeon.getActiveDungeon().renderer.render(delta, batch);
        PlayerCharacterEntity.getInstance().renderer.render(delta, batch);
        Tokens.getInstance().renderer.render(delta, batch);
        batch.end();

        ui.draw(delta);
    }

    void updateCamera(){
        PlayerCharacterEntity player = PlayerCharacterEntity.getInstance();
        camera.position.x = player.getPosition().x * ResourceLoader.getTileSize();
        camera.position.y = player.getPosition().y * ResourceLoader.getTileSize();
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
