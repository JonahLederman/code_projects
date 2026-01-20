// Dijkstra's Algorithm, Straightforward Implementation
// Author: Haziel Cerroblanco
// Creation Date: 10/27/2025

import java.util.*;

// https://www.geeksforgeeks.org/java/classes-objects-java/
// https://stackoverflow.com/questions/18478185/java-iterating-through-an-array-list

/** Programming Project: Dijkstra's Algorithm
 * 
 * This class a vertex, an object that has a tied adjency list and a toggle indicating if it has been visted or not.
 * 
 * @author Haziel Cerroblanco
 * @author Working side-by-side with Jonah Lederman
 * @version 11/4/2025
 * 
 * **/
public class Vertex {
    private ArrayList<Edge> edges;
    private boolean explored = false;

    // Constructors

    /**
     * Constructs a new object of this class.
     * 
     *      @param edges    the adjancecy list of the vertex
     */
    public Vertex(ArrayList<Edge> edges){
        this.edges = edges;
    }

    // Methods

    /**
     * Returns the adjency list
     * 
     *      @return the adjecency list
     */
    public ArrayList<Edge> getAdjecencyList(){
        return this.edges;
    }

    /**
     * Toggles the exploration state of the vertex (whether it has been visited or not).
     * 
     *      @param enable   the boolean toggle
     */
    public void setExplorationState(boolean enable){
        this.explored = enable;
    } 

    /**
     * Returns whether the vertex has been visited or not.
     * 
     *      @return exploration state
     */
    public boolean isExplored(){
        return this.explored;
    }

    /**
     * The toString method of the class, returning a formatted string of the vertex's adjecency list.
     * 
     *      @return formatted string
     */
    public String toString(){
        String formatted = "(";

        if (edges.size() == 0){
            return formatted + ")";
        }

        for (Edge edge : edges){
            formatted += edge.getAdjecentVertexIndex() + ",";
        }

        return formatted.substring(0, formatted.length()-1) + ")";
    }
}
