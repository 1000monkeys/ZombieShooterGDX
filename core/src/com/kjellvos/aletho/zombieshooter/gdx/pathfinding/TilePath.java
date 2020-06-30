package com.kjellvos.aletho.zombieshooter.gdx.pathfinding;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.GraphPath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class TilePath implements GraphPath<Connection<Tile>> {
    private List<Connection<Tile>> connections;

    /**
     * Constructor sets up the variables.
     */
    public TilePath(){
        this.connections = new ArrayList<>();
    }

    /**
     * Functions is used to get connections size.
     * @return size of connections(ako amount of neighbours)
     */
    @Override
    public int getCount() {
        return connections.size();
    }

    /**
     * Get's tile at passed index from connections
     * @param index index of the connection you want to get
     * @return connection class at the index which is passed
     */
    @Override
    public Connection<Tile> get(int index) {
        return connections.get(index);
    }

    /**
     * Add connection to array
     * @param connection Connection to add.
     */
    @Override
    public void add(Connection<Tile> connection) {
        connections.add(connection);
    }

    /**
     * Remove all connections from list
     */
    @Override
    public void clear() {
        connections.clear();
    }

    /**
     * Reverses connections list
     * With the last in the list becoming the first
     */
    @Override
    public void reverse() {
        Collections.reverse(connections);
    }

    /**
     * Gets the iterator of the connections list
     * @return iterator over the elements in this list in proper sequence
     */
    @Override
    public Iterator<Connection<Tile>> iterator() {
        return connections.iterator();
    }
}
