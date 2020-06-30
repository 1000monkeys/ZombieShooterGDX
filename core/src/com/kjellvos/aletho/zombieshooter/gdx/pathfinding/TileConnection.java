package com.kjellvos.aletho.zombieshooter.gdx.pathfinding;

import com.badlogic.gdx.ai.pfa.Connection;
import com.kjellvos.aletho.zombieshooter.gdx.Constants;

public class TileConnection implements Connection<Tile> {
    private Tile fromTile, toTile;

    /**
     * Constructor of TileConnection
     * @param fromTile the node you are leaving from
     * @param toTile the node you are going to
     */
    public TileConnection(Tile fromTile, Tile toTile){
        this.fromTile = fromTile;
        this.toTile = toTile;
    }

    /**
     * Gets the cost for movement from the fromNode to the toNode
     * @return cost of movement(manhattan distance)
     */
    @Override
    public float getCost() {
        //System.out.println("GOT COST" + (getToNode().getX() != fromNode.getX() && getToNode().getY() != getFromNode().getY() ? Constants.NON_DIAGONAL_COST : 1));
        return toTile.getX() != fromTile.getX() && toTile.getY() != fromTile.getY() ? Constants.NON_DIAGONAL_COST : 1;
    }

    /**
     * gets the from tile
     * @return fromTile tile to move from
     */
    @Override
    public Tile getFromNode() {
        return fromTile;
    }

    /**
     * Gets the to tile
     * @return toTile tile to move towards to
     */
    @Override
    public Tile getToNode() {
        return toTile;
    }
}
