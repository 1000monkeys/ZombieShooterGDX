package com.kjellvos.aletho.zombieshooter.gdx.pathfinding;

import com.badlogic.gdx.ai.pfa.Connection;
import com.kjellvos.aletho.zombieshooter.gdx.Constants;

public class TileConnection implements Connection<Tile> {
    private Tile fromNode, toNode;

    public TileConnection(Tile fromNode, Tile toNode){
        this.fromNode = fromNode;
        this.toNode = toNode;
    }

    @Override
    public float getCost() {
        //System.out.println("GOT COST" + (getToNode().getX() != fromNode.getX() && getToNode().getY() != getFromNode().getY() ? Constants.NON_DIAGONAL_COST : 1));
        return getToNode().getX() != fromNode.getX() && getToNode().getY() != getFromNode().getY() ? Constants.NON_DIAGONAL_COST : 1;
    }

    @Override
    public Tile getFromNode() {
        return fromNode;
    }

    @Override
    public Tile getToNode() {
        return toNode;
    }
}
