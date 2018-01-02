package com.mygdx.game.SpawnPools.ItemSpawnPools;

import com.mygdx.game.Inventory.InventoryItems.*;

public class T1ItemSpawnPool extends ItemSpawnPool{
    public T1ItemSpawnPool(){
        addNew(SwordItem::new, 1);
        addNew(RoundShieldItem::new, 3);
        addNew(LeatherTunicItem::new, 1);
        addNew(HelmetItem::new, 1);
        addNew(KnifeItem::new, 5);
        addNew(LeatherCapItem::new, 3);
    }
}
