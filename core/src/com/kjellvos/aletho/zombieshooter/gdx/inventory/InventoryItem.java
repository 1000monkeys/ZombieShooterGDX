package com.kjellvos.aletho.zombieshooter.gdx.inventory;

import com.kjellvos.aletho.zombieshooter.gdx.ashley.components.ItemComponent;

public class InventoryItem {
    private ItemComponent itemComponent;
    private int count;

    /**
     * Initializes the class with the variables and a count of the item at 1
     * @param itemComponent the item
     */
    public InventoryItem(ItemComponent itemComponent) {
        this.itemComponent = itemComponent;
        count = 1;
    }

    /**
     * Initializes the class with the variables
     * @param itemComponent the itemcomponent class which holds item information
     * @param count the amount of this item
     */
    public InventoryItem(ItemComponent itemComponent, int count) {
        this.itemComponent = itemComponent;
        this.count = count;
    }

    /**
     * Gets the itemcomponent
     * @return the itemcomponent
     */
    public ItemComponent getItemComponent() {
        return itemComponent;
    }

    /**
     * Gets the amount of items in this stack
     * @return the amount of items in this stack
     */
    public int getCount() {
        return count;
    }

    /**
     * Adds an amount to this itemstack
     * @param i the amount to add to this stack
     */
    public void addToCount(int i) {
        count += i;
    }
}
