package com.kjellvos.aletho.zombieshooter.gdx.pathfinding;

import com.badlogic.gdx.ai.steer.behaviors.*;
import com.badlogic.gdx.ai.steer.utils.Path;
import com.badlogic.gdx.ai.steer.utils.paths.LinePath;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.kjellvos.aletho.zombieshooter.gdx.ashley.components.SteeringComponent;

public class SteeringPresets {

    public static Wander<Vector2> getWander(SteeringComponent scom){
        Wander<Vector2> wander = new Wander<Vector2>(scom)
                .setFaceEnabled(false) // let wander behaviour manage facing
                .setWanderOffset(20f) // distance away from entity to set target
                .setWanderOrientation(0f) // the initial orientation
                .setWanderRadius(10f) // size of target
                .setWanderRate(MathUtils.PI2 * 2); // higher values = more spinning
        return wander;
    }

    public static Seek<Vector2> getSeek(SteeringComponent seeker, SteeringComponent target){
        Seek<Vector2> seek = new Seek<Vector2>(seeker,target);
        return seek;
    }

    public static Seek<Vector2> getSeek(SteeringComponent seeker, SeekablePoint target){
        Seek<Vector2> seek = new Seek<Vector2>(seeker,target);
        return seek;
    }

    public static Flee<Vector2> getFlee(SteeringComponent runner, SteeringComponent fleeingFrom){
        Flee<Vector2> seek = new Flee<Vector2>(runner,fleeingFrom);
        return seek;
    }

    public static Arrive<Vector2> getArrive(SteeringComponent runner, SteeringComponent target){
        Arrive<Vector2> arrive = new Arrive<Vector2>(runner, target)
                .setTimeToTarget(0.01F) // default 0.1f
                .setArrivalTolerance(0.0001F) //
                .setDecelerationRadius(1F);

        return arrive;
    }

    public static Arrive<Vector2> getArrive(SteeringComponent runner, SeekablePoint target){
        Arrive<Vector2> arrive = new Arrive<Vector2>(runner, target)
                .setTimeToTarget(0.1F) // default 0.1f
                .setArrivalTolerance(0.0001F) //
                .setDecelerationRadius(2F);

        return arrive;
    }

    public static FollowPath<Vector2, LinePath.LinePathParam> getFollowPath(SteeringComponent steer, Path<Vector2, LinePath.LinePathParam> path ) {
        FollowPath<Vector2, LinePath.LinePathParam> followPath = new FollowPath<Vector2, LinePath.LinePathParam>(steer, path)
                .setDecelerationRadius(1F)
                .setArriveEnabled(true);

        return followPath;
    }
}