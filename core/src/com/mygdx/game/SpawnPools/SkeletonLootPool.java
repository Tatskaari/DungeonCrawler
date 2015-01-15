package com.mygdx.game.SpawnPools;

import com.mygdx.game.Inventory.InventoryItems.BattleAxeItem;
import com.mygdx.game.Inventory.InventoryItems.HelmetItem;
import com.mygdx.game.Inventory.InventoryItems.LeatherTunicItem;
import com.mygdx.game.Inventory.InventoryItems.SwordItem;
import com.mygdx.game.Inventory.ItemTypes.InventoryItem;

public class SkeletonLootPool extends SpawnPool<InventoryItem> {
    public SkeletonLootPool(){
        addNew(LeatherTunicItem.class, 1);
        addNew(BattleAxeItem.class, 1);
        addNew(SwordItem.class, 3);
        addNew(HelmetItem.class, 3);
    }
}
