package com.kjellvos.aletho.zombieshooter.gdx.inventory;

import com.kjellvos.aletho.zombieshooter.gdx.ashley.components.ItemComponent;

public class InventoryItem {
    private ItemComponent itemComponent;
    private int count;


    public InventoryItem(ItemComponent itemComponent) {
        this.itemComponent = itemComponent;
        count = 1;
    }

    public InventoryItem(ItemComponent itemComponent, int count) {
        this.itemComponent = itemComponent;
        this.count = count;
    }

    public ItemComponent getItemComponent() {
        return itemComponent;
    }

    public int getCount() {
        return count;
    }

    public void addToCount(int i) {
        count += i;
    }
}
