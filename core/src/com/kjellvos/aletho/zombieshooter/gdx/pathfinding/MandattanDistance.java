package com.kjellvos.aletho.zombieshooter.gdx.pathfinding;

import com.badlogic.gdx.ai.pfa.Heuristic;

public class MandattanDistance implements Heuristic<Tile> {

    /**
     * Calculates the manhattan distance between two tiles
     * @param fromTile tile to move from
     * @param toTile tile to move towards
     * @return the manhattan distance between fromTile and toTile
     */
    @Override
    public float estimate(Tile fromTile, Tile toTile) {
        return  Math.abs(fromTile.getX() - fromTile.getX()) + Math.abs(toTile.getY() - toTile.getY());
    }
}
