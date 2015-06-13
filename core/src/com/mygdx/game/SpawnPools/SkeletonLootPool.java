package com.mygdx.game.SpawnPools;

import com.mygdx.game.Inventory.InventoryItems.*;
import com.mygdx.game.Inventory.InventoryItems.Factory.BasicItemFactory;
import com.mygdx.game.Inventory.ItemTypes.InventoryItem;

public class SkeletonLootPool extends SpawnPool<InventoryItem> {
    public SkeletonLootPool(){
        addNew(new BasicItemFactory<LeatherTunicItem>(LeatherTunicItem.class), 2);
        addNew(new BasicItemFactory<BattleAxeItem>(BattleAxeItem.class), 1);
        addNew(new BasicItemFactory<SwordItem>(SwordItem.class), 3);
        addNew(new BasicItemFactory<HelmetItem>(HelmetItem.class), 3);
        addNew(new BasicItemFactory<KiteShieldItem>(KiteShieldItem.class), 1);
        addNew(new BasicItemFactory<RoundShieldItem>(RoundShieldItem.class), 3);
    }
}
