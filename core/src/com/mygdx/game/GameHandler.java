package com.mygdx.game;

import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.Dungeon.DungeonGenerator;
import com.mygdx.game.Monsters.Monster;
import com.mygdx.game.Player.PlayerCharacter;
import com.mygdx.game.Tokens.Tokens;

public class GameHandler {
    public static Dungeon dungeon;
    public static DungeonGenerator dungeonGenerator;
    public static PlayerCharacter player;
    public static Tokens tokens;

    public static void stepTurn(){
        actMonsters();
    }

    private static void actMonsters(){
       for(int i = 0; i < dungeon.monsters.size; i++){
           Monster monster = dungeon.monsters.get(i);
           if (!monster.isDead()){
               monster.act();
           }
       }
    }
}
