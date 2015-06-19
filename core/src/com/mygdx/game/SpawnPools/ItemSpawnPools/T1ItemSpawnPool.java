package com.mygdx.game.SpawnPools.ItemSpawnPools;

import com.mygdx.game.Inventory.InventoryItems.HelmetItem;
import com.mygdx.game.Inventory.InventoryItems.LeatherTunicItem;
import com.mygdx.game.Inventory.InventoryItems.RoundShieldItem;
import com.mygdx.game.Inventory.InventoryItems.SwordItem;

public class T1ItemSpawnPool extends ItemSpawnPool{
    //TODO add new item spawn pool tiers
    public T1ItemSpawnPool(){
        addNew(SwordItem::new, 3);
        addNew(RoundShieldItem::new, 3);
        addNew(LeatherTunicItem::new, 1);
        addNew(HelmetItem::new, 1);
    }
}
