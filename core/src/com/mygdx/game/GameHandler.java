package com.mygdx.game;

import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.Characters.CharacterEntity;

public class GameHandler {
    public static int stepPathGenCount;

    public static int stepCount = 0;

    public static void stepTurn(){
        stepPathGenCount = 0;
        stepCount++;
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
