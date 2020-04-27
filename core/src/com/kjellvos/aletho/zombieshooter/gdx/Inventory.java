package com.kjellvos.aletho.zombieshooter.gdx;

import com.kjellvos.aletho.zombieshooter.gdx.components.ItemComponent;
import com.kjellvos.aletho.zombieshooter.gdx.enums.TextureEnum;

import java.util.HashMap;

public class Inventory {
    private HashMap<Integer, ItemComponent> entities;

    public Inventory(){
        entities = new HashMap<Integer, ItemComponent>();
    }

    public int getInventorySize(){
        return entities.size();
    }

    public void addItem(ItemComponent item) {
        entities.put(entities.size(), item);
    }

    public HashMap<Integer, ItemComponent> getEntities() {
        return entities;
    }

    public void print() {
        System.out.println("*** Inventory ***");
        for(int i = 0 ; i < entities.size(); i++){
            ItemComponent ic = entities.get(i);
            System.out.println("* ["+i+"] " + TextureEnum.findById(ic.id).getDescription());
        }
        System.out.println("*****************");
    }
}
