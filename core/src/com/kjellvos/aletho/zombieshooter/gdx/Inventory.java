package com.kjellvos.aletho.zombieshooter.gdx;

import com.kjellvos.aletho.zombieshooter.gdx.components.ItemComponent;
import com.kjellvos.aletho.zombieshooter.gdx.gson.SpriteObj;

import java.util.ArrayList;

public class Inventory {
    private ZombieShooterGame parent;
    private ArrayList<InventoryItem> entities;

    public Inventory(ZombieShooterGame parent){
        this.parent = parent;
        entities = new ArrayList<>();
    }

    public int getInventorySize(){
        return entities.size();
    }

    public void addItem(ItemComponent newItem) {
        SpriteObj spriteObj = parent.getReadJsonGameFiles().getSpriteObj(newItem.id);
        if (spriteObj.isStackable()) {
            boolean added = false;
            for (int i = 0; i < entities.size() && !added; i++) {
                if (entities.get(i).getItemComponent().id == newItem.id && entities.get(i).getCount() < Constants.STACK_SIZE_LIMIT) {
                    entities.get(i).addToCount(1);
                    added = true;
                    System.out.println("TEST 123");
                }
            }
            if (!added) {
                entities.add(new InventoryItem(newItem));
            }
        }else{
            entities.add(new InventoryItem(newItem));
        }
    }

    public void print() {
        System.out.println("*** Inventory ***");
        for(int i = 0 ; i < entities.size(); i++){
            InventoryItem inventoryItem = entities.get(i);
            ItemComponent ic = inventoryItem.getItemComponent();
            System.out.println("* ["+i+"] " + "[ID:" + ic.id + "][COUNT:" + inventoryItem.getCount() + "]" + parent.getReadJsonGameFiles().getSpriteObj(ic.id).getDescription());
        }
        System.out.println("*****************");
    }
}
