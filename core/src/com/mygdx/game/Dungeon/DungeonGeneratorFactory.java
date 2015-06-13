package com.mygdx.game.Dungeon;

public class DungeonGeneratorFactory {
    private static final DungeonGenerator dungeonGenerator = new DungeonGenerator(50, 50, 25);

    //TODO make different dungeon generators for deeper levels
    public static DungeonGenerator getDefaultDungeonGenerator(){
        return dungeonGenerator;
    }
}
