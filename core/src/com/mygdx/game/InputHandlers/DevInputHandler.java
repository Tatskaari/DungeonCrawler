package com.mygdx.game.InputHandlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.Dungeon.DungeonGenerator;
import com.mygdx.game.Dungeon.DungeonGeneratorFactory;
import com.mygdx.game.GameHandler;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Player.PlayerCharacterEntity;
import com.mygdx.game.ResourceLoader;
import com.mygdx.game.Screens.DevScreen;
import com.mygdx.game.Screens.MapScreen;

public class DevInputHandler extends MapInputHandler {

    public DevInputHandler(DevScreen lastScreen) {
        super(lastScreen);
    }

    @Override
    public boolean keyDown(int keyCode){
        if (keyCode == Input.Keys.M){
            return false;
        }
        else if (keyCode == Input.Keys.CONTROL_LEFT) {
            MyGdxGame.myGdxGame.setScreen(getLastScreen());
        }
        else if (keyCode == Input.Keys.G){
            Dungeon oldDungeon = Dungeon.getActiveDungeon();
            Dungeon newDungeon = DungeonGeneratorFactory.getDefaultDungeonGenerator().regenerateDungeon(oldDungeon);
            Dungeon.setActiveDungeon(newDungeon);
            PlayerCharacterEntity.getInstance().placeCharacterIn(newDungeon);
        }
        else if (keyCode == Input.Keys.S) {
            DungeonGenerator dungeonGenerator = DungeonGeneratorFactory.getDefaultDungeonGenerator();
            dungeonGenerator.spawnMonsters(Dungeon.getActiveDungeon(), 1);
        }

        return super.keyDown(keyCode);
    }
}
