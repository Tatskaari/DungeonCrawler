package com.mygdx.game.Inventory.InventoryItems.Factory;

import com.mygdx.game.Factory;
import com.mygdx.game.Inventory.ItemTypes.InventoryItem;

public class BasicItemFactory<ItemType extends InventoryItem> implements Factory<InventoryItem> {
    private final Class itemTypeClass;

    public  BasicItemFactory(Class itemTypeClass){
        this.itemTypeClass = itemTypeClass;
    }

    //TODO consider if this is the best approach.
    @Override
    public InventoryItem newInstance() {
        try {
            return (ItemType)itemTypeClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Error instantiating an item", e);
        }
    }
}
