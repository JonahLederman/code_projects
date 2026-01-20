// Dijkstra's Algorithm, Straightforward Implementation
// Author: Haziel Cerroblanco
// Creation Date: 10/27/2025

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/** Programming Project: Dijkstra's Algorithm
 * 
 * This class contains a method that uses Dijkstra's Algorithm (straightforward implementation) to return a list
 * of distances from the chosen vertex to all other vertices.
 * 
 * It also contains a main method, which will run a basic test of the algorithm.
 * 
 * The implementation of the algorithm was created using the one found in Algorithms Illuminated Part 2, Page 80.
 * 
 * @author Haziel Cerroblanco
 * @author With assistance from Avery Brockington & Son Le
 * @author Working side-by-side with Jonah Lederman
 * @version 11/3/2025
 * 
 * **/
public class DijkstraAlgorithm {
    static ArrayList<Vertex> vertices = new ArrayList<Vertex>(); // List of all vertices used, ordered.
    static ArrayList<ArrayList<Edge>> edges = new ArrayList<ArrayList<Edge>>(); // List of all adjecency lists, ordered by index respective to the list above.
    static int startingVertex = 0; // The vertex that acts as the center-point of the algorithm.
    static boolean isRandomlyGenerated = false;

    /**
     *  The main function initiates execution of this program.
     *    @param    String[] args not used in this program
     **/
    public static void main(String[] args){
        int size;
        
        if (isRandomlyGenerated){
            size = generateRandomGraph();
        }else{
            size = generateWithFile(new File("TestData/sparseTest.txt"));
        }

        // Creates a vertex using the given size, passing its respective adjecency list to its constructor, and adding it to the vertices list.
        for (int i=0; i < size; i++){
            vertices.add(new Vertex(edges.get(i)));
        }

        System.out.println("Starting vertex is: " + startingVertex);

        ArrayList<Integer> distances = sort(startingVertex);
    
        System.out.print("Distances from vertex " + startingVertex + ": ");
        System.out.println(distances);
        System.out.println(distances.size());
    }

    /**
     * The method holding Dijkstra's Shortest-Path Algorithm from Algorithms Illuminated Part 2, Page 80.
     * 
     *      @param list the list of vertices
     *      @param edges the list of adjencency lists respective to each vertex
     *      @return list of distances ordered by vertex order
     */
    public static ArrayList<Integer> sort(int startingVertex){
        ArrayList<Integer> lengths = new ArrayList<Integer>();
        ArrayList<Vertex> closestVertices = new ArrayList<Vertex>();
        closestVertices.add(vertices.get(startingVertex));

        vertices.get(startingVertex).setExplorationState(true);

        // Initialize the lengths for all vertices
        for (int i=0; i < vertices.size(); i++){
            if (i == startingVertex){
                lengths.add(0);
                continue;
            }
            lengths.add(Integer.MAX_VALUE); // Taken from https://stackoverflow.com/questions/15004944/max-value-of-integer
        }

        int numSteps = 0;
        
        for (int i=0; i < vertices.size()-1; i++){
            int shortestLength = Integer.MAX_VALUE;
            int chosenAdjVertex = -1;
            
            int vertexIndex = 0;
            for (ArrayList<Edge> adjecencyList : edges){
                int vertexLength = lengths.get(vertexIndex);
                // if (!vertices.get(vertexIndex).isExplored()){continue;}

                for (Edge curEdge : adjecencyList){
                    int adjVertexIndex = curEdge.getAdjecentVertexIndex();
                    int weight = curEdge.getWeight();

                    // Debug
                    // System.out.print("Primary Index: ");
                    // System.out.print(vertexIndex);
                    // System.out.print(" Index: ");
                    // System.out.print(adjVertexIndex);
                    // System.out.print(" Weight: ");
                    // System.out.print(weight);
                    // System.out.print(" Length: ");
                    // System.out.println(vertexLength);
                    // End of debug

                    int curLength = vertexLength + weight;
                    Vertex adjVertex = vertices.get(adjVertexIndex);
                    
                    // The edge must cross from the set of processed vertices to the set of unprocessed vertices.
                    numSteps ++;
                    if (adjVertex.isExplored()) {continue;}

                    // To prevent overflow errors, we make sure the length isn't negative.
                    if (vertexLength != Integer.MAX_VALUE && curLength <= shortestLength){
                        shortestLength = curLength;
                        chosenAdjVertex = adjVertexIndex;
                        // break;
                    }
                }
                vertexIndex ++;
            }

            if (chosenAdjVertex == -1){
                continue;
            }

            // Debug
            // System.out.print("Shortest length: ");
            // System.out.print(shortestLength);
            // System.out.print(" Chosen Vertex: ");
            // System.out.println(chosenAdjVertex);
            // System.out.println(numSteps);
            // End of debug

            vertices.get(chosenAdjVertex).setExplorationState(true);
            if (lengths.get(chosenAdjVertex) > shortestLength){
                lengths.set(chosenAdjVertex, shortestLength);
            }
        } // End of while loop

        return lengths;
    }

    public static int generateWithFile(File dataFile){
        int size = 0;
        /** This try-catch block goes through each line of the given data file. It converts the edges within each line
         *  into Edge objects that can be used in the program.
         * 
         *  Try-catch was taken from https://www.w3schools.com/java/java_files_read.asp 
         *  Modifications were made inside the while-loop, where code for conversion is at.
         * **/
        try (Scanner myReader = new Scanner(dataFile)) {
            while (myReader.hasNextLine()) {
                // Taken from https://stackoverflow.com/questions/5455794/removing-whitespace-from-strings-in-java
                String data = myReader.nextLine().replaceAll("\t", " "); // Removes all white-space from the line.
                size ++;

                int index = Integer.parseInt(data.split(" ", 2)[0]) - 1; // Gets the vertex's index.
                String[] params = data.split(" ", 2)[1].split(" "); // Gets each edge.
                
                edges.add(new ArrayList<Edge>());
                for (String arg : params){
                    if (arg == null){continue;}

                    String adjIndexRaw = arg.split(",")[0];
                    // Failsafe
                    if (adjIndexRaw.equals("")){
                        continue;
                    }

                    String weightRaw = arg.split(",")[1];
                    // Failsafe
                    if (weightRaw.equals("")){
                        continue;
                    }

                    int adjIndex = Integer.parseInt(adjIndexRaw)-1;
                    int weight = Integer.parseInt(weightRaw);

                    edges.get(index).add(new Edge(adjIndex, weight));   
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } // end of try-catch for converting data file to edges

        return size;
    }

    public static int generateRandomGraph(){
        int randomSize = (int) (Math.random() * 1000) + 1;

        for (int i=0; i < randomSize; i++){
            edges.add(i, new ArrayList<Edge>());

            for (int n=0; n < randomSize; n++){
                if (n == i){
                    continue;
                }

                if (Math.random() <= .5 ){
                    edges.get(i).add(new Edge(n, (int) (Math.random() * 99 + 1)));
                }
            }
        }

        return randomSize;
    }
}
