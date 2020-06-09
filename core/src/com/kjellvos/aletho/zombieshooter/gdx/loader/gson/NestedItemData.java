package com.kjellvos.aletho.zombieshooter.gdx.gson;

public class NestedItemData {
    private boolean pickUpText;
    private boolean stackable;

    public NestedItemData(boolean pickUpText, boolean stackable){
        this.pickUpText = pickUpText;
        this.stackable = stackable;
    }

    public boolean hasPickUpText(){
        return pickUpText;
    }

    public boolean isStackable() {
        return stackable;
    }
}
