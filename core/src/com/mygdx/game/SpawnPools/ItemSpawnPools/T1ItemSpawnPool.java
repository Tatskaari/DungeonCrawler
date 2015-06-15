package com.mygdx.game.SpawnPools.ItemSpawnPools;

import com.mygdx.game.Inventory.InventoryItems.Factory.BasicItemFactory;
import com.mygdx.game.Inventory.InventoryItems.SwordItem;

public class T1ItemSpawnPool extends ItemSpawnPool{
    //TODO add new item spawn pool tiers
    public T1ItemSpawnPool(){
        addNew(new BasicItemFactory<SwordItem>(SwordItem.class), 1);
    }
}
