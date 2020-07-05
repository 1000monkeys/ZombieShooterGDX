package com.kjellvos.aletho.zombieshooter.gdx.pathfinding;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.PathFinder;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.utils.Array;
import com.kjellvos.aletho.zombieshooter.gdx.ZombieShooterGame;

import java.util.ArrayList;
import java.util.List;

public class Pathfinding {
    private ZombieShooterGame parent;
    private TiledMapTileLayer walkingLayer;
    private int mapWidth, mapHeight;
    private Array<Tile> tiles = null;
    private TileWorld tileWorld = null;
    private PathFinder pathFinder = null;


    public Pathfinding(ZombieShooterGame parent, TiledMapTileLayer walkingLayer, int mapWidth, int mapHeight) {
        this.parent = parent;
        this.walkingLayer = walkingLayer;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
    }

    public PathFinder setupPathfinder(){
        if (tiles == null) {
            tiles = setupTilesArray();
        }

        tileWorld = new TileWorld(tiles, mapWidth, mapHeight);
        pathFinder = new IndexedAStarPathFinder<Tile>(tileWorld);
        return pathFinder;
    }

    public Array<Tile> setupTilesArray(){
        Array<Tile> tempTiles = new Array<>(mapWidth * mapHeight);

        for (int x = 0; x < mapWidth; x++) {
            for (int y = 0; y < mapHeight; y++) {
                int id = walkingLayer.getCell(x, y).getTile().getId() - 1;

                tempTiles.add(new Tile(x, y, parent.getReadJsonGameFiles().getSpriteGson(id).isWalkable(), x * mapWidth + y));
            }
        }

        for (int x = 0; x < mapWidth; x++) {
            for (int y = 0; y < mapHeight; y++) {
                List<Connection<Tile>> connections = new ArrayList<>();


                if (x > 0 && tempTiles.get((x - 1) * mapWidth + y).isWalkable()) {
                    connections.add(new TileConnection(tempTiles.get(x * mapWidth + y), tempTiles.get((x - 1) * mapWidth + y))); //-1, 0
                }
                if (y > 0 && tempTiles.get(x * mapWidth + (y - 1)).isWalkable()){
                    connections.add(new TileConnection(tempTiles.get(x * mapWidth + y), tempTiles.get(x * mapWidth + (y - 1)))); //0, -1
                }
                if (x < mapWidth - 1 && tempTiles.get((x  + 1) * mapWidth + y).isWalkable()){
                    connections.add(new TileConnection(tempTiles.get(x * mapWidth + y), tempTiles.get((x + 1) * mapWidth + y))); //1, 0
                }
                if (y < mapHeight - 1 && tempTiles.get(x * mapWidth + (y + 1)).isWalkable()){
                    connections.add(new TileConnection(tempTiles.get(x * mapWidth + y), tempTiles.get(x * mapWidth + (y + 1)))); //0, 1
                }

                if ((x + 1) < mapHeight && (y + 1) < mapWidth && tempTiles.get((x + 1) * mapWidth + y + 1).isWalkable()){
                    connections.add(new TileConnection(tempTiles.get(x * mapWidth + y), tempTiles.get((x + 1) * mapWidth + y + 1))); //1, 1
                }
                if ((x + 1) < mapHeight && (y - 1) > 0 && tempTiles.get((x + 1) * mapWidth + y - 1).isWalkable()){
                    connections.add(new TileConnection(tempTiles.get(x * mapWidth + y), tempTiles.get((x + 1) * mapWidth + y - 1))); //1, -1
                }
                if ((x - 1) > 0 && (y - 1) > 0 && tempTiles.get((x - 1) * mapWidth + y - 1).isWalkable()){
                    connections.add(new TileConnection(tempTiles.get(x * mapWidth + y), tempTiles.get((x - 1) * mapWidth + y - 1))); //-1, -1
                }
                if ((x - 1) > 0 && y + 1 < mapWidth && tempTiles.get((x - 1) * mapWidth + y + 1).isWalkable()){
                    connections.add(new TileConnection(tempTiles.get(x * mapWidth + y), tempTiles.get((x - 1) * mapWidth + y + 1))); //-1, -1
                }

                Array<Connection<Tile>> connectionArray = new Array<Connection<Tile>>(connections.size());
                for (int i = 0; i < connections.size(); i++){
                    connectionArray.add(connections.get(i));
                }

                tempTiles.get(x * mapWidth + y).setConnections(connectionArray);
            }
        }

        return tempTiles;
    }

    public PathFinder getPathFinder() {
        if (tiles == null) {
            tiles = setupTilesArray();
        }
        if (pathFinder == null) {
            pathFinder = setupPathfinder();
        }
        return pathFinder;
    }

    public Tile getTile(int x, int y) {
        if (tiles == null){
            tiles = setupTilesArray();
        }
        return tiles.get(x * mapWidth + y);
    }
}
