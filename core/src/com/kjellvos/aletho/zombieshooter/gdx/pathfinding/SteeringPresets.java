package com.kjellvos.aletho.zombieshooter.gdx.pathfinding;

import com.badlogic.gdx.ai.steer.behaviors.*;
import com.badlogic.gdx.ai.steer.utils.Path;
import com.badlogic.gdx.ai.steer.utils.paths.LinePath;
import com.badlogic.gdx.math.Vector2;
import com.kjellvos.aletho.zombieshooter.gdx.ashley.components.SteeringComponent;

public class SteeringPresets {

    /**
     * Gets wander steering for use in mob code
     * @param scom steering component that this steering preset is for
     * @return Wander steering for idle mobs
     */
    public static Wander<Vector2> getWander(SteeringComponent scom){
        Wander<Vector2> wander = new Wander<Vector2>(scom)
                .setWanderRate(1000F)
                .setWanderOffset(-1.6F)
                .setWanderRadius(3.2F);

        return wander;
    }

    /**
     * Gets seek steering behaviour between two steeringcomponents
     * @param seeker the steeringcomponent that has to move
     * @param target the steeringvomponent to move towards
     * @return the steering behaviour to be applied to a mob
     */
    public static Seek<Vector2> getSeek(SteeringComponent seeker, SteeringComponent target){
        Seek<Vector2> seek = new Seek<Vector2>(seeker,target);
        return seek;
    }

    /**
     * Gets seek steering behaviour between a seeking steerable component and a seekable point(x,y)
     * @param seeker the steeringcomponent you want this steering behaviour to have
     * @param target the target for the seeker
     * @return the steering behaviour to be applied to a mob
     */
    public static Seek<Vector2> getSeek(SteeringComponent seeker, SeekablePoint target){
        Seek<Vector2> seek = new Seek<Vector2>(seeker,target);
        return seek;
    }

    /**
     * Gets flee steering behaviour between two steering components
     * @param runner The steeringcomponent this behaviour will be applied to
     * @param fleeingFrom The steeringcomponent this behaviour has to run from
     * @return The steering behaviour to be applied to a mob
     */
    public static Flee<Vector2> getFlee(SteeringComponent runner, SteeringComponent fleeingFrom){
        Flee<Vector2> seek = new Flee<Vector2>(runner,fleeingFrom);
        return seek;
    }

    /**
     * Gets arrive steering behaviour
     * @param runner The steeringcomponent this behaviour will be applied to
     * @param target The steeringcomponent this behaviour will arrive at
     * @return The steering behaviour to be applied to a mob
     */
    public static Arrive<Vector2> getArrive(SteeringComponent runner, SteeringComponent target){
        Arrive<Vector2> arrive = new Arrive<Vector2>(runner, target)
                .setTimeToTarget(0.01F) // default 0.1f
                .setArrivalTolerance(0.0001F) //
                .setDecelerationRadius(1F);

        return arrive;
    }

    /**
     * Gets arrive steering behaviour
     * @param runner The steeringcomponent this behaviour will be applied to
     * @param target The seekable point(x,y) this behaviour will arrive at
     * @return The steering behaviour to be applied to a mob
     */
    public static Arrive<Vector2> getArrive(SteeringComponent runner, SeekablePoint target){
        Arrive<Vector2> arrive = new Arrive<Vector2>(runner, target)
                .setTimeToTarget(1F) // default 0.1f
                .setArrivalTolerance(0.1F) //
                .setDecelerationRadius(4F);


        return arrive;
    }

    /**
     * Gets follow path steering behaviour
     * @param steer the steeringcomponent this behaviour will be applied to
     * @param path The path the steeringcomponent will follow
     * @return The steering behaviour to be applied to a mob
     */
    public static FollowPath<Vector2, LinePath.LinePathParam> getFollowPath(SteeringComponent steer, Path<Vector2, LinePath.LinePathParam> path ) {
        FollowPath<Vector2, LinePath.LinePathParam> followPath = new FollowPath<Vector2, LinePath.LinePathParam>(steer, path)
                .setDecelerationRadius(1F)
                .setArriveEnabled(true);

        return followPath;
    }
}