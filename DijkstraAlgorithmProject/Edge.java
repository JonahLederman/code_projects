// Dijkstra's Algorithm, Straightforward Implementation
// Author: Haziel Cerroblanco
// Creation Date: 10/27/2025

/** Programming Project: Dijkstra's Algorithm
 * 
 * This class represents an edge, an item connecting two vertices. An edge contains the vertex it is directed to, and its weight.
 * 
 * @author Haziel Cerroblanco
 * @author Working side-by-side with Jonah Lederman
 * @version 11/4/2025
 * 
 * **/
public class Edge {
    private int adjacentVertex; // The vertex the edge is directed at.
    private int weight; // The weight of the edge.
    
    // Constructors

    /**
     * Constructs a new object of this class.
     * 
     *      @param adjVertex    the vertex the edge is directed at
     *      @param weight   the weight of the edge
     */
    public Edge(int adjVertex, int weight){
        this.adjacentVertex = adjVertex;
        this.weight = weight;
    }

    // Methods

    /**
     * Returns the vertex the edge is directed at
     * 
     *      @return the adjecent vertex
     */
    public int getAdjecentVertexIndex(){
        return adjacentVertex;
    }

    /**
     * Returns the weight of the edge
     * 
     *      @return weight of edge
     */
    public int getWeight(){
        return weight;
    }

    /**
     * The toString method of the class, returning a formatted string containing the adjecent vertex and the edge's weight.
     * 
     *      @return formatted string
     */
    public String toString(){
        return "(" + String.valueOf(adjacentVertex) + "," + String.valueOf(weight) + ")";
    }
}
