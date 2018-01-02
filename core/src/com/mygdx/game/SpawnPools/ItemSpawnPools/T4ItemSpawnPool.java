package com.mygdx.game.SpawnPools.ItemSpawnPools;

import com.mygdx.game.Inventory.InventoryItems.*;

public class T4ItemSpawnPool extends ItemSpawnPool {
    public T4ItemSpawnPool(){
        addNew(SwordItem::new, 1);
        addNew(DaggerItem::new, 3);
        addNew(RoundShieldItem::new, 1);
        addNew(HelmetItem::new, 2);
        addNew(KiteShieldItem::new, 3);
        addNew(LeatherTunicItem::new, 2);
        addNew(BattleAxeItem::new, 3);
    }
}
