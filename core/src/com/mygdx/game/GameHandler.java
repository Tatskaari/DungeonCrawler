package com.mygdx.game;

import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.Characters.CharacterEntity;
import com.mygdx.game.EventHandlers.Event;
import com.mygdx.game.EventHandlers.EventHandler;
import com.mygdx.game.EventHandlers.EventListener;
import com.mygdx.game.Player.PlayerCharacterEntity;

public class GameHandler implements EventListener{
    private static final GameHandler instance = new GameHandler();

    public static int stepPathGenCount;
    public static int stepCount = 0;

    private GameHandler(){
        EventHandler.getInstance().registerEventListener(this);
    }

    private void stepTurn(){
        stepPathGenCount = 0;
        stepCount++;
        actMonsters();
    }

    private void actMonsters(){
        Dungeon dungeon = Dungeon.getActiveDungeon();

        for(int i = 0; i < dungeon.monsters.size; i++){
           CharacterEntity characterEntity = dungeon.monsters.get(i);
           characterEntity.act();
        }
    }

    @Override
    public void handleEvent(Event event) {
        switch (event.type) {
            case DUNGEON_DESCEND:
                Dungeon.setActiveDungeon(Dungeon.getActiveDungeon().getFloorBelow());
                PlayerCharacterEntity.getInstance().placeAtStairsUp();
                break;
            case DUNGEON_ASCEND:
                Dungeon floorAbove = Dungeon.getActiveDungeon().getFloorAbove();
                if (floorAbove != null){
                    Dungeon.setActiveDungeon(floorAbove);
                    PlayerCharacterEntity.getInstance().placeAtStairsDown();
                }
                break;
            case STEP_TURN:
                stepTurn();
                break;
        }
    }
}
