package com.mygdx.game.Dungeon;

import com.mygdx.game.SpawnPools.ItemSpawnPools.*;
import com.mygdx.game.SpawnPools.MonsterSpawnPools.*;

public class DungeonGeneratorFactory {

    private static final DungeonGenerator T1Dungeons;
    private static final DungeonGenerator T2Dungeons;
    private static final DungeonGenerator T3Dungeons;
    private static final DungeonGenerator T4Dungeons;
    private static final DungeonGenerator T5Dungeons;

    static {
        //TODO level the item spawn as you get deeper
        T1Dungeons = new DungeonGenerator(50, 50, 25, new T1MonsterSpawnPool(), new T1ItemSpawnPool());
        T2Dungeons = new DungeonGenerator(60, 60, 35, new T2MonsterSpawnPool(), new T2ItemSpawnPool());
        T3Dungeons = new DungeonGenerator(65, 65, 40, new T3MonsterSpawnPool(), new T3ItemSpawnPool());
        T4Dungeons = new DungeonGenerator(70, 70, 45, new T4MonsterSpawnPool(), new T4ItemSpawnPool());
        T5Dungeons = new DungeonGenerator(75, 75, 50, new T5MonsterSpawnPool(), new T5ItemSpawnPool());
    }


    public static DungeonGenerator getDungeonGenerator(int level){
        //TODO balance the level progression
        if (level <= 1) {
            //Rats and boring mobs
            return T1Dungeons;
        }
        else if (level <= 2) {
            //Skeletons and rats
            return T2Dungeons;
        }
        else if (level <= 5) {
            //Skeletons rats and goblins
            return T3Dungeons;
        }
        else if (level <= 7) {
            //Skeletons and Goblins
            return T4Dungeons;
        }
        else {
            //Skeletons, Goblins and higher tier mobs that are yet to be drawn
            return T5Dungeons;
        }
    }
}
