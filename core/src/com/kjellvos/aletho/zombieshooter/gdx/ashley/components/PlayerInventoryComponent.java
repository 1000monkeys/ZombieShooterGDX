package com.kjellvos.aletho.zombieshooter.gdx.ashley.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;
import com.kjellvos.aletho.zombieshooter.gdx.Constants;
import com.kjellvos.aletho.zombieshooter.gdx.ZombieShooterGame;
import com.kjellvos.aletho.zombieshooter.gdx.ashley.Mapper;
import com.kjellvos.aletho.zombieshooter.gdx.inventory.InventoryItem;
import com.kjellvos.aletho.zombieshooter.gdx.loader.gson.SpriteGson;

import java.util.ArrayList;

public class PlayerInventoryComponent implements Component {
    private ZombieShooterGame parent;
    private ArrayList<InventoryItem> entities;

    private Entity closestItem = null;
    private String pickUpString = null;

    public PlayerInventoryComponent(ZombieShooterGame parent){
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
        if (closestItem == null){
            return false;
        }

        Body closestItemBody = Mapper.bodyCom.get(closestItem).body;
        Body playerBody = Mapper.bodyCom.get(parent.getGameScreen().getPlayer()).body;
        if (        playerBody.getPosition().x + 64 > closestItemBody.getPosition().x && playerBody.getPosition().x - 64 < closestItemBody.getPosition().x &&
                playerBody.getPosition().y + 64 > closestItemBody.getPosition().y && playerBody.getPosition().y - 64 < closestItemBody.getPosition().y) {
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
