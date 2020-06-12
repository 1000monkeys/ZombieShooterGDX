package com.kjellvos.aletho.zombieshooter.gdx.pathfinding;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.GraphPath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class TilePath implements GraphPath<Connection<Tile>> {
    private List<Connection<Tile>> connections;

    public TilePath(){
        this.connections = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return connections.size();
    }

    @Override
    public Connection<Tile> get(int index) {
        return connections.get(index);
    }

    @Override
    public void add(Connection<Tile> connection) {
        connections.add(connection);
    }

    @Override
    public void clear() {
        connections.clear();
    }

    @Override
    public void reverse() {
        Collections.reverse(connections);
    }

    @Override
    public Iterator<Connection<Tile>> iterator() {
        return connections.iterator();
    }
}
