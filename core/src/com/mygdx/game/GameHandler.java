package com.mygdx.game;

import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.Dungeon.DungeonGenerator;
import com.mygdx.game.Characters.CharacterEntity;
import com.mygdx.game.Player.PlayerCharacterEntity;
import com.mygdx.game.Tokens.Tokens;

public class GameHandler {
    public static int PATH_GEN_COUNT_THIS_STEP;

    public static void stepTurn(){
        PATH_GEN_COUNT_THIS_STEP = 0;
        actMonsters();
    }

    private static void actMonsters(){
        Dungeon dungeon = Dungeon.getActiveDungeon();

        for(int i = 0; i < dungeon.monsters.size; i++){
           CharacterEntity characterEntity = dungeon.monsters.get(i);
           characterEntity.act();
        }
    }
}
