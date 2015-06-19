package com.mygdx.game.SpawnPools.ItemSpawnPools;

import com.mygdx.game.Inventory.InventoryItems.*;

public class T3ItemSpawnPool extends ItemSpawnPool{
    public T3ItemSpawnPool(){
        addNew(SwordItem::new, 3);
        addNew(RoundShieldItem::new, 3);
        addNew(HelmetItem::new, 1);
        addNew(KiteShieldItem::new, 2);
        addNew(LeatherTunicItem::new, 2);
        addNew(BattleAxeItem::new, 1);
    }
}
