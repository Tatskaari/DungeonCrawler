package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends Game {
	static SpriteBatch batch;

	public static Dungeon dungeon;
	public static DungeonGenerator dungeonGenerator;
	public static PlayerCharacter player;
	public static MyGdxGame myGdxGame;

	public static OrthographicCamera camera;
	
	@Override
	public void create () {
		ResourceLoader.loadResources();

		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(screenWidth, screenHeight);

		batch = new SpriteBatch();

		myGdxGame = this;

		this.setScreen(new MainMenuScreen());
	}

	@Override
	public void resize(int width, int height){
		getScreen().resize(width, height);
		camera.viewportWidth = width;
		camera.viewportHeight = height;
		camera.update();
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		ResourceLoader.dispose();
		batch.dispose();
	}
}
