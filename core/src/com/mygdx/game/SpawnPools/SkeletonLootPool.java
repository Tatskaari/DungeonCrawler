package com.mygdx.game.SpawnPools;

import com.mygdx.game.Inventory.InventoryItems.*;
import com.mygdx.game.Inventory.ItemTypes.InventoryItem;

public class SkeletonLootPool extends SpawnPool<InventoryItem> {
    public SkeletonLootPool(){
        addNew(LeatherTunicItem::new, 2);
        addNew(BattleAxeItem::new, 1);
        addNew(SwordItem::new, 3);
        addNew(HelmetItem::new, 3);
        addNew(KiteShieldItem::new, 1);
        addNew(RoundShieldItem::new, 3);
    }
}
