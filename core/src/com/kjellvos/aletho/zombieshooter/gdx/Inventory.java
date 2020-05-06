package com.kjellvos.aletho.zombieshooter.gdx;

import com.kjellvos.aletho.zombieshooter.gdx.components.ItemComponent;
import com.kjellvos.aletho.zombieshooter.gdx.enums.TextureEnum;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<InventoryItem> entities;

    public Inventory(){
        entities = new ArrayList<>();
    }

    public int getInventorySize(){
        return entities.size();
    }

    public void addItem(ItemComponent newItem) {
        boolean present = false;
        boolean added = false;
        for (int i = 0; i < entities.size(); i++) {
            if (!added && !present) {
                InventoryItem item = entities.get(i);
                if (item.getItemComponent().id == newItem.id) {
                    present = true;
                    if (TextureEnum.findById(item.getItemComponent().id).isStackable() && item.getCount() < Constants.STACK_SIZE_LIMIT) {
                        item.addToCount(1);
                        added = true;
                    } else {
                        entities.add(new InventoryItem(newItem));
                        added = true;
                    }
                }
            }
        }
        if (!present) {
            entities.add(new InventoryItem(newItem));
        }
    }

    public void print() {
        System.out.println("*** Inventory ***");
        for(int i = 0 ; i < entities.size(); i++){
            InventoryItem inventoryItem = entities.get(i);
            ItemComponent ic = inventoryItem.getItemComponent();
            System.out.println("* ["+i+"] " + "[ID:" + ic.id + "][COUNT:" + inventoryItem.getCount() + "]" + TextureEnum.findById(ic.id).getDescription());
        }
        System.out.println("*****************");
    }
}
