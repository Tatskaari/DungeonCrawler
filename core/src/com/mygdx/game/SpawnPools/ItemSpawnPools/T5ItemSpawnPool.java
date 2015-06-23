package com.mygdx.game.SpawnPools.ItemSpawnPools;

import com.mygdx.game.Inventory.InventoryItems.*;

public class T5ItemSpawnPool extends ItemSpawnPool {
    public T5ItemSpawnPool(){
        addNew(DaggerItem::new, 3);
        addNew(BattleAxeItem::new, 1);
        addNew(KiteShieldItem::new, 1);
        addNew(LeatherTunicItem::new, 0.5f);
        addNew(HelmetItem::new, 0.5f);
    }
}
