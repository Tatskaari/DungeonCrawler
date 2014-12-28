package com.mygdx.game;

import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.Dungeon.DungeonGenerator;
import com.mygdx.game.Monsters.Monster;
import com.mygdx.game.Player.PlayerCharacter;

public class GameHandler {
    public static Dungeon dungeon;
    public static DungeonGenerator dungeonGenerator;
    public static PlayerCharacter player;

    public static void stepTurn(){
        actMonsters();
    }

    private static void actMonsters(){
       for(Monster monster : dungeon.monsters){
           monster.act();
       }
    }
}
