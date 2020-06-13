package com.kjellvos.aletho.zombieshooter.gdx.ashley.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.ai.GdxAI;
import com.badlogic.gdx.ai.steer.utils.paths.LinePath;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import com.kjellvos.aletho.zombieshooter.gdx.pathfinding.SeekablePoint;
import com.kjellvos.aletho.zombieshooter.gdx.pathfinding.SteeringPresets;
import com.kjellvos.aletho.zombieshooter.gdx.ZombieShooterGame;
import com.kjellvos.aletho.zombieshooter.gdx.ashley.Mapper;
import com.kjellvos.aletho.zombieshooter.gdx.ashley.components.BodyComponent;
import com.kjellvos.aletho.zombieshooter.gdx.ashley.components.SteeringComponent;
import com.kjellvos.aletho.zombieshooter.gdx.ashley.components.TextureRegionComponent;
import com.kjellvos.aletho.zombieshooter.gdx.ashley.entities.PlayerEntity;
import com.kjellvos.aletho.zombieshooter.gdx.pathfinding.MandattanDistance;
import com.kjellvos.aletho.zombieshooter.gdx.pathfinding.Tile;
import com.kjellvos.aletho.zombieshooter.gdx.pathfinding.TilePath;

import static com.badlogic.gdx.math.MathUtils.floor;

public class SteeringSystem extends IteratingSystem {
    private ZombieShooterGame parent;
    private Array<Vector2> route = null;
    private int count = 0;

    public SteeringSystem(ZombieShooterGame parent) {
        super(Family.all(SteeringComponent.class, BodyComponent.class, TextureRegionComponent.class).get());
        this.parent = parent;
    }


    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        GdxAI.getTimepiece().update(deltaTime);
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        SteeringComponent steer = Mapper.steerCom.get(entity);
        Body monsterBody = Mapper.bodyCom.get(entity).body;

        PlayerEntity playerEntity = parent.getGameScreen().getPlayer();
        Body playerBody = Mapper.bodyCom.get(entity).body;


        if (route == null) {
            //System.out.println(monsterBody.getPosition().x + ":X_MONSTERBODY_Y:" + monsterBody.getPosition().y);
            Tile startNode = parent.getGameScreen().getTile(floor(monsterBody.getPosition().x / 16), floor(monsterBody.getPosition().y / 16));
            Tile endNode = parent.getGameScreen().getTile(3, 3);

            TilePath path = new TilePath();
            parent.getGameScreen().getPathFinder().searchConnectionPath(startNode, endNode, new MandattanDistance(), path);
            System.out.println(path.getCount());

            route = new Array<>(path.getCount());
            for (int i = 0; i < path.getCount(); i++) {
                route.add(new Vector2(path.get(i).getFromNode().getX() * 16 + 8, path.get(i).getFromNode().getY() * 16 + 8));
                System.out.println("Added to path: " + i + " X:" + (path.get(i).getToNode().getX() * 16 + 8) + " Y:" + (path.get(i).getToNode().getY() * 16 + 8));
            }

            if (path.getCount() > 1) {
                LinePath<Vector2> linePath = new LinePath<Vector2>(route);
                //steer.steeringBehavior = SteeringPresets.getFollowPath(steer, linePath);
                steer.steeringBehavior = SteeringPresets.getArrive(steer, new SeekablePoint(route.get(count).x, route.get(count).y));
                System.out.println("APPLIED STEERING");
            }
        }else{
            if (
                    route.get(count).x + 4 > monsterBody.getPosition().x && monsterBody.getPosition().x > route.get(count).x - 4 &&
                    route.get(count).y + 4 > monsterBody.getPosition().y && monsterBody.getPosition().y > route.get(count).y - 4
            ){
                count++;
                steer.body.setLinearVelocity(new Vector2(0, 0));
                steer.body.setAngularVelocity(0F);
                steer.steeringBehavior = SteeringPresets.getArrive(steer, new SeekablePoint(route.get(count).x, route.get(count).y));
            }
        }

        System.out.println(monsterBody.getPosition().x + ":X_MONSTERBODY_Y:" + monsterBody.getPosition().y);
        steer.update(deltaTime);
    }
}
