package com.mygdx.game.SpawnPools;

import com.mygdx.game.Inventory.InventoryItems.*;
import com.mygdx.game.Inventory.ItemTypes.InventoryItem;
import com.mygdx.game.Inventory.ItemTypes.ShieldItem;

public class SkeletonLootPool extends SpawnPool<InventoryItem> {
    public SkeletonLootPool(){
        addNew(LeatherTunicItem.class, 1);
        addNew(BattleAxeItem.class, 1);
        addNew(SwordItem.class, 3);
        addNew(HelmetItem.class, 3);
        addNew(KiteShieldItem.class, 1);
        addNew(RoundShieldItem.class, 3);
    }
}
