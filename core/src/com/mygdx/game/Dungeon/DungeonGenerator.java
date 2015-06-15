package com.mygdx.game.Dungeon;

import com.mygdx.game.Player.PlayerCharacterEntity;
import com.mygdx.game.SpawnPools.ItemSpawnPools.ItemSpawnPool;
import com.mygdx.game.SpawnPools.MonsterSpawnPools.MonsterSpawnPool;

public class DungeonGenerator {
    private int requestedMapWidth;
    private int requestedMapHeight;
    private int requestedRoomCount;

    private DungeonMapGenerator mapGenerator;

    private MonsterSpawnPool monsterSpawnPool;
    private ItemSpawnPool itemSpawnPool;

    public DungeonGenerator(int mapWidth, int mapHeight, int roomCount, MonsterSpawnPool monsterSpawnPool, ItemSpawnPool itemSpawnPool){
        requestedMapHeight = mapHeight;
        requestedMapWidth = mapWidth;
        requestedRoomCount = roomCount;
        this.monsterSpawnPool = monsterSpawnPool;
        this.itemSpawnPool = itemSpawnPool;
        mapGenerator = new DungeonMapGenerator();
    }

    public Dungeon regenerateDungeon(Dungeon oldDungeon){
        Dungeon dungeon = new Dungeon(requestedMapWidth, requestedMapHeight);
        dungeon.floorAbove = oldDungeon.floorAbove;
        dungeon.floorBelow = oldDungeon.floorBelow;
        dungeon.level = oldDungeon.level;
        mapGenerator.generateDungeonTiles(dungeon, requestedRoomCount);

        return regenerateIfIncompletable(dungeon);
    }

    public Dungeon generateDungeonBelow(Dungeon parentDungeon){
        Dungeon dungeon = new Dungeon(requestedMapWidth, requestedMapHeight, parentDungeon);

        mapGenerator.generateDungeonTiles(dungeon, requestedRoomCount);

        spawnMonsters(dungeon, dungeon.getRoomCount());
        spawnItems(dungeon, dungeon.getRoomCount()/3);
        dungeon.monsters.add(PlayerCharacterEntity.getInstance());

        return regenerateIfIncompletable(dungeon);
    }

    public Dungeon generateDungeon(){
        Dungeon dungeon = new Dungeon(requestedMapWidth, requestedMapHeight);
        mapGenerator.generateDungeonTiles(dungeon, requestedRoomCount);
        spawnMonsters(dungeon, dungeon.getRoomCount());
        spawnItems(dungeon, dungeon.getRoomCount()/3);
        dungeon.monsters.add(PlayerCharacterEntity.getInstance());

        return dungeon;
    }

    private Dungeon regenerateIfIncompletable(Dungeon dungeon){
        if (!DungeonUtils.checkDungeonCompletable(dungeon)){
            return regenerateDungeon(dungeon);
        } else {
            return dungeon;
        }
    }

    public void spawnMonsters(Dungeon dungeon, int monsterCount) {
        monsterSpawnPool.initialisePool(dungeon);
        for (int i = 0; i < monsterCount; i++){
            dungeon.monsters.add(monsterSpawnPool.getNewInstance());
        }
    }

    public void spawnItems(Dungeon dungeon, int itemCount){
        itemSpawnPool.initialisePool(dungeon);
        for (int i = 0; i < itemCount; i++){
            itemSpawnPool.getNewInstance();
        }
    }
}
