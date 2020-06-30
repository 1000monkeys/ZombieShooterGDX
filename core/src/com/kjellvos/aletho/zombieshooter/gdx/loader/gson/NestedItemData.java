package com.kjellvos.aletho.zombieshooter.gdx.loader.gson;

public class NestedItemData {
    private boolean pickUpText;
    private boolean stackable;

    /**
     * Initializes the variables for use
     * @param pickUpText whether this item has instant pickup(false) or pickuptext(true)
     * @param stackable whether this item is stackable in the inventory or not
     */
    public NestedItemData(boolean pickUpText, boolean stackable){
        this.pickUpText = pickUpText;
        this.stackable = stackable;
    }

    /**
     * Gets the pickuptext variable value
     * @return whether this item has instant pickup(false) or pickuptext(true)
     */
    public boolean hasPickUpText(){
        return pickUpText;
    }

    /**
     * Gets the stackable variable value
     * @return whether this item is stackable in the inventory or not
     */
    public boolean isStackable() {
        return stackable;
    }
}
