package com.mygdx.game;

import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.Dungeon.DungeonGenerator;
import com.mygdx.game.Characters.CharacterEntity;
import com.mygdx.game.Player.PlayerCharacterEntity;
import com.mygdx.game.Tokens.Tokens;

public class GameHandler {
    public static Dungeon dungeon;
    public static DungeonGenerator dungeonGenerator;

    public static void stepTurn(){
        actMonsters();
    }

    private static void actMonsters(){
       for(int i = 0; i < dungeon.monsters.size; i++){
           CharacterEntity characterEntity = dungeon.monsters.get(i);
           characterEntity.act();
       }
    }
}
