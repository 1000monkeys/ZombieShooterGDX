package com.kjellvos.aletho.zombieshooter.gdx.pathfinding;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.GraphPath;

import java.util.Iterator;

public class TilePath implements GraphPath<Connection<Tile>> {
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Connection<Tile> get(int index) {
        return null;
    }

    @Override
    public void add(Connection<Tile> node) {

    }

    @Override
    public void clear() {

    }

    @Override
    public void reverse() {

    }

    @Override
    public Iterator<Connection<Tile>> iterator() {
        return null;
    }
}
