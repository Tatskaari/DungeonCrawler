package com.mygdx.game;

import com.badlogic.gdx.*;
import com.mygdx.game.Screens.MainMenuScreen;

public class MyGdxGame extends Game {
	public static MyGdxGame myGdxGame;
	
	@Override
	public void create () {
		ResourceLoader.loadResources();
		myGdxGame = this;
		this.setScreen(new MainMenuScreen());
	}

	@Override
	public void resize(int width, int height){
		getScreen().resize(width, height);
	}

	@Override
	public void dispose () {
		ResourceLoader.dispose();
		getScreen().dispose();
	}
}
