package com.mygdx.game;

import com.badlogic.gdx.*;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.Dungeon.DungeonGenerator;
import com.mygdx.game.Player.PlayerCharacter;
import com.mygdx.game.Screens.MainMenuScreen;

public class MyGdxGame extends Game {

	public static Dungeon dungeon;
	public static DungeonGenerator dungeonGenerator;
	public static PlayerCharacter player;
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
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		ResourceLoader.dispose();
		getScreen().dispose();
	}
}
