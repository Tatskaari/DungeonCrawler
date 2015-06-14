package com.mygdx.game.Dungeon;

import com.mygdx.game.SpawnPools.*;

public class DungeonGeneratorFactory {

    private static final DungeonGenerator T1Dungeons;
    private static final DungeonGenerator T2Dungeons;
    private static final DungeonGenerator T3Dungeons;
    private static final DungeonGenerator T4Dungeons;
    private static final DungeonGenerator T5Dungeons;

    static {
        T1Dungeons = new DungeonGenerator(50, 50, 25, new T1MonsterSpawnPool());
        T2Dungeons = new DungeonGenerator(70, 70, 35, new T2MonsterSpawnPool());
        T3Dungeons = new DungeonGenerator(80, 80, 40, new T3MonsterSpawnPool());
        T4Dungeons = new DungeonGenerator(90, 90, 45, new T4MonsterSpawnPool());
        T5Dungeons = new DungeonGenerator(100, 100, 50, new T5MonsterSpawnPool());
    }


    public static DungeonGenerator getDungeonGenerator(int level){
        //TODO balance the level progression
        if (level < 2) {
            //Rats and boring mobs
            return T1Dungeons;
        }
        else if (level < 3) {
            //Skeletons and rats
            return T2Dungeons;
        }
        else if (level < 4) {
            //Skeletons rats and goblins
            return T3Dungeons;
        }
        else if (level < 5) {
            //Skeletons and Goblins
            return T4Dungeons;
        }
        else {
            //Skeletons, Goblins and higher tier mobs that are yet to be drawn
            return T5Dungeons;
        }
    }
}
