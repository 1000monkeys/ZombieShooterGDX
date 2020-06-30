package com.kjellvos.aletho.zombieshooter.gdx.ashley.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.ai.GdxAI;
import com.badlogic.gdx.ai.steer.utils.paths.LinePath;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import com.kjellvos.aletho.zombieshooter.gdx.ZombieShooterGame;
import com.kjellvos.aletho.zombieshooter.gdx.ashley.Mapper;
import com.kjellvos.aletho.zombieshooter.gdx.ashley.components.BodyComponent;
import com.kjellvos.aletho.zombieshooter.gdx.ashley.components.SteeringComponent;
import com.kjellvos.aletho.zombieshooter.gdx.ashley.components.TextureRegionComponent;
import com.kjellvos.aletho.zombieshooter.gdx.ashley.entities.PlayerEntity;
import com.kjellvos.aletho.zombieshooter.gdx.pathfinding.*;
import com.kjellvos.aletho.zombieshooter.gdx.views.GameScreen;

public class SteeringSystem extends IteratingSystem {
    private ZombieShooterGame parent;
    private Array<Vector2> route = null;
    private int count = 0;

    /**
     * The constructor of the render system, Gets called once on creation of the class,
     * We pass the {@link GameScreen} class for use in the update function.
     * @param parent passed for use in the update method.
     */
    public SteeringSystem(ZombieShooterGame parent) {
        super(Family.all(SteeringComponent.class, BodyComponent.class, TextureRegionComponent.class).get());
        this.parent = parent;
    }



    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        GdxAI.getTimepiece().update(deltaTime);
    }


    /**
     * Processes the entity, creates movement and routes towards players
     * @param entity the entity to process
     * @param deltaTime the time since the entity was last processed
     */
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        SteeringComponent steer = Mapper.steerCom.get(entity);
        Body monsterBody = Mapper.bodyCom.get(entity).body;

        PlayerEntity playerEntity = parent.getGameScreen().getPlayer();
        Body playerBody = Mapper.bodyCom.get(playerEntity).body;

        if (route == null &&
                monsterBody.getPosition().x + 80 > playerBody.getPosition().x && playerBody.getPosition().x > monsterBody.getPosition().x - 80 &&
                monsterBody.getPosition().y + 80 > playerBody.getPosition().y && playerBody.getPosition().y > monsterBody.getPosition().y - 80
        ) {
            //System.out.println(monsterBody.getPosition().x + ":X_MONSTERBODY_Y:" + monsterBody.getPosition().y);
            Tile startNode = parent.getGameScreen().getTile(Math.round(monsterBody.getPosition().x / 16), Math.round(monsterBody.getPosition().y / 16));
            Tile endNode = parent.getGameScreen().getTile(Math.round(playerBody.getPosition().x / 16), Math.round(playerBody.getPosition().y / 16));

            TilePath path = new TilePath();
            if (parent.getGameScreen().getPathFinder().searchConnectionPath(startNode, endNode, new MandattanDistance(), path)) {
                System.out.println(path.getCount());

                if (path.getCount() > 1) {
                    route = new Array<>(path.getCount());
                    for (int i = 0; i < path.getCount(); i++) {
                        route.add(new Vector2(path.get(i).getToNode().getX() * 16 + 8, path.get(i).getToNode().getY() * 16 + 8));
                        System.out.println("Added to path: " + i + " X:" + (path.get(i).getToNode().getX() * 16 - 12) + " Y:" + (path.get(i).getToNode().getY() * 16 - 12));
                    }

                    LinePath<Vector2> linePath = new LinePath<Vector2>(route);
                    //steer.steeringBehavior = SteeringPresets.getFollowPath(steer, linePath);
                    steer.currentMode = SteeringComponent.SteeringState.SEEK;
                    steer.steeringBehavior = SteeringPresets.getSeek(steer, new SeekablePoint(route.get(count).x, route.get(count).y));
                    System.out.println("APPLIED STEERING");
                } else {
                    route = null;
                    count = 0;
                    steer.homeX = Math.round(monsterBody.getPosition().x);
                    steer.homeY = Math.round(monsterBody.getPosition().y);
                }
            }
        }else if(route != null){
            if (    route.size - 1 > count &&
                    route.get(count).x + 8 > monsterBody.getPosition().x - 4 && monsterBody.getPosition().x + 4 > route.get(count).x - 8 &&
                    route.get(count).y + 8 > monsterBody.getPosition().y - 4 && monsterBody.getPosition().y + 4 > route.get(count).y - 8
            ){
                count++;
                //steer.body.setLinearVelocity(new Vector2(0, 0));
                //steer.body.setAngularVelocity(0F);
                steer.currentMode = SteeringComponent.SteeringState.SEEK;
                steer.steeringBehavior = SteeringPresets.getSeek(steer, new SeekablePoint(route.get(count).x, route.get(count).y));
            }
            if (count == route.size - 1) {
                SteeringComponent playerSteer = Mapper.steerCom.get(playerEntity);

                steer.currentMode = SteeringComponent.SteeringState.SEEK;
                steer.steeringBehavior = SteeringPresets.getSeek(steer, playerSteer);
                route = null;
                count = 0;
                steer.homeX = Math.round(monsterBody.getPosition().x);
                steer.homeY = Math.round(monsterBody.getPosition().y);
            }
        }else {
            if (
                    !(steer.getPosition().x < steer.homeX + 40 && steer.getPosition().x > steer.homeX - 40 &&
                    steer.getPosition().y < steer.homeY + 40 && steer.getPosition().y > steer.homeY - 40)
            ) {
                steer.currentMode = SteeringComponent.SteeringState.SEEK;
                steer.steeringBehavior = SteeringPresets.getSeek(steer, new SeekablePoint(steer.homeX, steer.homeY));
            }else if (steer.currentMode != SteeringComponent.SteeringState.WANDER) {
                steer.currentMode = SteeringComponent.SteeringState.WANDER;
                steer.steeringBehavior = SteeringPresets.getWander(steer);
            }
        }

        //System.out.println(monsterBody.getPosition().x + ":X_MONSTERBODY_Y:" + monsterBody.getPosition().y);
        steer.update(deltaTime);
    }
}
