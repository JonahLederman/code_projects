Names: Haziel Cerroblanco & Jonah Lederman

Developed by Haziel:
DijkstraAlgorithm.java - The file containing the first implementation for Dijkstra's Shortest-Path Algorithm.
Edge.java - The file containing the Edge class used in DijkstraAlgorithm.java
Vertex.java - The file containing the Vertex class used in DijkstraAlgorithm.java

Developed by Jonah:
DijkstraAlgo.py - The file containing the second implementation for Dijkstra's Shortest-Path Algorithm.

TestData is the folder containing all the data files used to test the algorithm.

Instructions:
To use DijkstraAlgorithm.java, the main way is by using the sort() method of the class. However, to do so, you must provide
a list of Vertex objects and a list of adjency lists (made up of Edge objects), alongside the starting vertex's index.

The main() method in the file already has the process above by reading a data file from the TestData folder, and convering the data
into usable objects.

To use dijkstraAlgo.py, the main way is by using the algorithm() method defined in the class. To do so, use the v_count() method with your file as an input, in order to find the number of vertexes, do the same with the read_graph() method, and then input 0.
The .py file should already be set up for the dummy data from the TestData folder, just open up the .py file and run it.

Output for DijkstraAlgo.py:
Easy dataset: 
-------------------------------------------
-------------------------------------------
Counters: 
Number of steps: 16
Amount of heap pops: 8
Amount of heap pushes: 7
How long the program took to run: 0.0 seconds.
Challenge dataset: 
-------------------------------------------
-------------------------------------------
Counters:
Number of steps: 3734
Amount of heap pops: 525
Amount of heap pushes: 524
How long the program took to run: 0.0010066032409667969 seconds.
Sparse dataset:
-------------------------------------------
-------------------------------------------
Counters:
Number of steps: 12
Amount of heap pops: 7
Amount of heap pushes: 6
How long the program took to run: 0.0 seconds.
