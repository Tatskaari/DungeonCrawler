package com.mygdx.game.SpawnPools;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Inventory.InventoryItems.BattleAxeItem;
import com.mygdx.game.Inventory.InventoryItems.HelmetItem;
import com.mygdx.game.Inventory.InventoryItems.SwordItem;
import com.mygdx.game.Inventory.ItemTypes.InventoryItem;

public class SkeletonLootPool extends SpawnPool<InventoryItem> {
    public SkeletonLootPool(){
        addNew(BattleAxeItem.class, 1);
        addNew(SwordItem.class, 3);
        addNew(HelmetItem.class, 3);
    }
}
