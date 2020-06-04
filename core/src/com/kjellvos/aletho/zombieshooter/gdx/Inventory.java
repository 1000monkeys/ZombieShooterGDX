package com.kjellvos.aletho.zombieshooter.gdx;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.kjellvos.aletho.zombieshooter.gdx.components.BodyComponent;
import com.kjellvos.aletho.zombieshooter.gdx.components.ItemComponent;
import com.kjellvos.aletho.zombieshooter.gdx.gson.SpriteGson;

import java.util.ArrayList;

public class Inventory {
    private ZombieShooterGame parent;
    private ArrayList<InventoryItem> entities;

    private Entity closestItem = null;
    private String pickUpString = null;

    public Inventory(ZombieShooterGame parent){
        this.parent = parent;
        entities = new ArrayList<>();
    }

    public int getInventorySize(){
        return entities.size();
    }

    public void addItem(ItemComponent newItem) {
        SpriteGson spriteGson = parent.getReadJsonGameFiles().getSpriteGson(newItem.id);

        if (spriteGson.getItemData().isStackable()) {
            boolean added = false;
            for (int i = 0; i < entities.size() && !added; i++) {
                if (entities.get(i).getItemComponent().id == newItem.id && entities.get(i).getCount() < Constants.STACK_SIZE_LIMIT) {
                    entities.get(i).addToCount(1);
                    added = true;

                    if (Constants.DEBUG) {
                        System.out.println("Picked up item: " + newItem.id);
                    }
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
            System.out.println("* ["+i+"] " + "[ID:" + ic.id + "][COUNT:" + inventoryItem.getCount() + "]" + parent.getReadJsonGameFiles().getSpriteGson(ic.id).getDescription());
        }
        System.out.println("*****************");
    }

    public void pickUpClosestItem() {
        if (closestItem != null) {
            parent.getGameScreen().getPlayer().getInventory().addItem(closestItem.getComponent(ItemComponent.class));

            parent.getGameScreen().getWorld().destroyBody(closestItem.getComponent(BodyComponent.class).body);
            parent.getGameScreen().getEngine().removeEntity(closestItem);

            closestItem = null;
            pickUpString = null;
        }
    }

    public boolean itemToPickUp(){
        if (closestItem != null &&  (parent.getPlayer().getComponent(BodyComponent.class).body.getPosition().x + 64) > closestItem.getComponent(BodyComponent.class).body.getPosition().x && (parent.getPlayer().getComponent(BodyComponent.class).body.getPosition().x - 64) < closestItem.getComponent(BodyComponent.class).body.getPosition().x &&
                                    (parent.getPlayer().getComponent(BodyComponent.class).body.getPosition().y + 64) > closestItem.getComponent(BodyComponent.class).body.getPosition().y && (parent.getPlayer().getComponent(BodyComponent.class).body.getPosition().y - 64) < closestItem.getComponent(BodyComponent.class).body.getPosition().y) {
            pickUpString = "Press G to pick up: " + parent.getReadJsonGameFiles().getSpriteGson(closestItem.getComponent(ItemComponent.class).id).getDescription();

            return true;
        }
        return false;
    }

    public String getPickUpString() {
        return pickUpString;
    }

    public void setClosestItem(Entity closestItem) {
        this.closestItem = closestItem;
    }
}
