package com.kjellvos.aletho.zombieshooter.gdx.pathfinding;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.utils.Array;

public class TileWorld implements IndexedGraph<Tile> {
    protected Array<Tile> tiles;
    private int mapWidth, mapHeight;

    public TileWorld(Array<Tile> tiles, int mapWidth, int mapHeight){
        this.tiles = tiles;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
    }

    @Override
    public int getIndex(Tile node) {
        return node.getIndex();
    }

    @Override
    public int getNodeCount() {
        return tiles.size;
    }

    public Tile getTile(int id){
        return tiles.get(id);
    }

    @Override
    public Array<Connection<Tile>> getConnections(Tile fromNode) {
        return fromNode.getConnections();
    }

    public Tile getTile(int y, int x) {
        return tiles.get(x * mapWidth + y);
    }
}
