package com.mygdx.game.InputHandlers;

import com.badlogic.gdx.Input;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.Dungeon.DungeonGenerator;
import com.mygdx.game.Dungeon.DungeonGeneratorFactory;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Player.PlayerCharacterEntity;
import com.mygdx.game.Screens.DevScreen;

public class DevInputHandler extends MapInputHandler {

    public DevInputHandler(DevScreen lastScreen) {
        super(lastScreen);
    }

    @Override
    public boolean keyDown(int keyCode){
        if (keyCode == Input.Keys.M){
            return false;
        } else if (keyCode == Input.Keys.CONTROL_LEFT) {
            MyGdxGame.myGdxGame.setScreen(getLastScreen());
        } else if (keyCode == Input.Keys.G){
            Dungeon oldDungeon = Dungeon.getActiveDungeon();
            Dungeon newDungeon = DungeonGeneratorFactory.getDungeonGenerator(oldDungeon.getLevel()).regenerateDungeon(oldDungeon);
            Dungeon.setActiveDungeon(newDungeon);
            newDungeon.monsters.add(PlayerCharacterEntity.getInstance());
            PlayerCharacterEntity.getInstance().placeCharacterIn(newDungeon);
        } else if (keyCode == Input.Keys.S) {
            Dungeon dungeon = Dungeon.getActiveDungeon();
            DungeonGenerator dungeonGenerator = DungeonGeneratorFactory.getDungeonGenerator(dungeon.getLevel());
            dungeonGenerator.spawnMonsters(dungeon, 1);
        }

        return super.keyDown(keyCode);
    }
}
