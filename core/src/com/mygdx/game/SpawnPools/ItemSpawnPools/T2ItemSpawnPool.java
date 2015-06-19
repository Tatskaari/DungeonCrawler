package com.mygdx.game.SpawnPools.ItemSpawnPools;

import com.mygdx.game.Inventory.InventoryItems.*;

/**
 * Created by Tatskaari on 19/06/2015.
 */
public class T2ItemSpawnPool extends ItemSpawnPool {
    public T2ItemSpawnPool(){
        addNew(SwordItem::new, 3);
        addNew(LeatherTunicItem::new, 3);
        addNew(RoundShieldItem::new, 3);
        addNew(KiteShieldItem::new, 0.5f);
        addNew(HelmetItem::new, 1);

    }
}
