package com.mygdx.game.Dungeon;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Characters.NonPlayerCharacterEntity;
import com.mygdx.game.Dungeon.DungeonTiles.*;
import com.mygdx.game.PathFinding.Astar;
import com.mygdx.game.PathFinding.AstarNode;
import com.mygdx.game.PathFinding.GridBasedHeuristic;
import com.mygdx.game.Player.PlayerCharacterEntity;
import com.mygdx.game.SpawnPools.MonsterSpawnPool;
import com.mygdx.game.SpawnPools.SpawnPool;

public class DungeonGenerator {
    private int requestedMapWidth;
    private int requestedMapHeight;
    private int requestedRoomCount;

    private DungeonMapGenerator mapGenerator;

    private MonsterSpawnPool monsterSpawnPool;

    //TODO validate the dungeon exit is reachable otherwise regenerate the dungeon
    public DungeonGenerator(int mapWidth, int mapHeight, int roomCount, MonsterSpawnPool monsterSpawnPool){
        requestedMapHeight = mapHeight;
        requestedMapWidth = mapWidth;
        requestedRoomCount = roomCount;
        this.monsterSpawnPool = monsterSpawnPool;
        mapGenerator = new DungeonMapGenerator();
    }

    public Dungeon regenerateDungeon(Dungeon oldDungeon){
        Dungeon dungeon = new Dungeon(requestedMapWidth, requestedMapHeight);
        dungeon.floorAbove = oldDungeon.floorAbove;
        dungeon.floorBelow = oldDungeon.floorBelow;
        dungeon.level = oldDungeon.level;
        mapGenerator.generateDungeonTiles(dungeon, requestedRoomCount);

        return dungeon;
    }

    public Dungeon generateDungeonBelow(Dungeon parentDungeon){
        Dungeon dungeon = new Dungeon(requestedMapWidth, requestedMapHeight, parentDungeon);

        mapGenerator.generateDungeonTiles(dungeon, requestedRoomCount);

        spawnMonsters(dungeon, dungeon.getRoomCount());
        dungeon.monsters.add(PlayerCharacterEntity.getInstance());

        return dungeon;
    }

    public Dungeon generateDungeon(){
        Dungeon dungeon = new Dungeon(requestedMapWidth, requestedMapHeight);
        mapGenerator.generateDungeonTiles(dungeon, requestedRoomCount);
        spawnMonsters(dungeon, dungeon.getRoomCount());
        dungeon.monsters.add(PlayerCharacterEntity.getInstance());

        return dungeon;
    }

    public void spawnMonsters(Dungeon dungeon, int monsterCount) {
        monsterSpawnPool.initialisePool(dungeon);
        for (int i = 0; i < monsterCount; i++){
            dungeon.monsters.add(monsterSpawnPool.getNewInstance());
        }
    }
}
