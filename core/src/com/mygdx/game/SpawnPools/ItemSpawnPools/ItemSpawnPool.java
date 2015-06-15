package com.mygdx.game.SpawnPools.ItemSpawnPools;

import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.Dungeon.DungeonTile;
import com.mygdx.game.Dungeon.DungeonUtils;
import com.mygdx.game.Inventory.ItemTypes.InventoryItem;
import com.mygdx.game.SpawnPools.SpawnPool;

public abstract class ItemSpawnPool extends SpawnPool<InventoryItem>{
    private Dungeon dungeon;

    public void initialisePool(Dungeon dungeon){
        this.dungeon = dungeon;
    }

    @Override
    public InventoryItem getNewInstance() {
        InventoryItem item =  super.getNewInstance();
        DungeonTile dungeonTile = dungeon.getDungeonTile(DungeonUtils.getRandomTileInAnyRoom(dungeon));

        dungeonTile.addItem(item);

        return item;
    }
}
