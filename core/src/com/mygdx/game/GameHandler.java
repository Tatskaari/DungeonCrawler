package com.mygdx.game;

import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.Characters.CharacterEntity;
import com.mygdx.game.EventHandlers.DungeonDecentEvent;
import com.mygdx.game.EventHandlers.EventHandler;
import com.mygdx.game.EventHandlers.EventListener;
import com.mygdx.game.Player.PlayerCharacterEntity;

public class GameHandler implements EventListener{
    private static final GameHandler instance = new GameHandler();

    public static int stepPathGenCount;


    public static int stepCount = 0;

    private GameHandler(){
        DungeonDecentEvent.getInstance().registerEventListener(this);
    }

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

    @Override
    public void handleEvent(EventHandler eventHandler) {
        if (eventHandler instanceof DungeonDecentEvent){
            Dungeon.setActiveDungeon(Dungeon.getActiveDungeon().getFloorBelow());
            PlayerCharacterEntity.getInstance().placeAtStairsUp();
        }
    }
}
