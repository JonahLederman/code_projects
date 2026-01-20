# Name: Jonah Lederman
# Date: 11/4/25
# Project: Implementations of Dijkstra's Algorithm
# Instructions: Your program should use the adjacency list representation of a graph that was developed in class. You should include appropriate counters in your code to be used in comparison with the expected running times.
# Collaborators / Sources of Outside Code:
# Haziel Cerroblanco for doing the first algorithm implementation and being my partner in the project
# Son Le Dinh Truong for contributing the read_graph() function
# https://www.geeksforgeeks.org/dsa/dijkstras-shortest-path-algorithm-greedy-algo-7/ - helped me understand algorithm and how to implement heaps in python
# https://www.tutorialspoint.com/python_data_structure/python_heaps.htm#:~:text=Create%20a%20Heap,operations%20on%20heap%20data%20structure. -info on heap methods
# https://www.geeksforgeeks.org/dsa/adjacency-list-in-python/ - learning how to use adjacency lists in python
# https://stackoverflow.com/questions/62025218/python-adjacency-list-from-text-file-into-a-dictionary
# https://www.geeksforgeeks.org/python/time-perf_counter-function-in-python/ - How to find the time a program takes

import heapq
import sys
import time

#Read_Graph function converts a file into a computer-readable list.
def read_graph(data):
    list = {}
    with open(data) as f:
        for line in f:
            parts = line.split()
            u = int(parts[0]) - 1
            list[u] = []
            for pair in parts[1:]:
                v, w = pair.split(',')
                list[u].append((int(v)-1, float(w)))
    return list

#Searches through the document, finds number of vertex's and returns it
def v_count(data):
    vertices = []
    with open(data) as f:
        for line in f:
            parts = line.split()
            u = int(parts[0]) - 1
            vertices.append(u)
    return len(vertices)

#Djikstra's Algorithm implementation with heaps
#V is vertices, edge is vertex edge, source is the very first vertex
def algorithm(V, edges, source):
    #-------------------------------------------------------------------------------
    #Setup
    edge_dist = [sys.maxsize] * V # creates a number of infinites equal to the number of vertices.
    edge_dist[source] = 0
    priority_queue = [(0,source)] # H, empty heap
    exploredSet = set() # X, empty set
    start_time = time.time()

    #Counters
    heappop = 0
    heappush = 0
    numSteps = 0

    #Begin loop
    while len(priority_queue) > 0:
        current_dist, w = heapq.heappop(priority_queue) #extract the minimum value inside the heap, which is the smallest edge distance
        heappop += 1
        #While the queue isn't empty, and w hasn't already been explored, check vertex w
        if w in exploredSet:
            continue
        exploredSet.add(w) #Explore it so we don't have repeats
        if current_dist > edge_dist[w]:
            continue
         # print("Updated exploredSet: " + str(exploredSet)) 
        for y,weight in edges[w]: #retrieves edge length, and weight [w,y]
            numSteps += 1
            if (y not in exploredSet): #Make sure the vertex hasn't already been explored
                #calculate the current distance to get to y (edge_dist of w plus edge length between w and y)
                new_dist = current_dist + weight 
                #if new edge distance is less than the current one, make current edge distance into the new one and put it into the heap alongside its vertex
                if new_dist < edge_dist[y]:
                    edge_dist[y] = new_dist 
                    #Put new distance, vertex into the queue
                    heapq.heappush(priority_queue,(new_dist, y))
                    heappush += 1

    #Printing out the edge distances
    print("-------------------------------------------")
    print("Printing out edge distances from source vertice:")
    for i in range(V):
         print(f"Distance from {0} to {i}: {edge_dist[i]}")
    
    elapsed_time = time.time() - start_time
    #Print out counters
    print("-------------------------------------------")
    print("Counters: ")
    print("Number of steps: " + str(numSteps))
    print("Amount of heap pops: " + str(heappop))
    print("Amount of heap pushes: " + str(heappush))


    print("How long the program took to run: " + str(elapsed_time) + " seconds.")
    return 

# ----END OF FUNCTION----
easyproblem = r"TestData/problem9.8test.txt"
sparseproblem = r"TestData/sparseTest.txt"
hardproblem = r"TestData/problem9.8Challenge.txt"
print("Easy dataset: ")
algorithm(v_count(easyproblem), read_graph(easyproblem), 0) 
print("Challenge dataset: ")
algorithm(v_count(hardproblem), read_graph(hardproblem), 0) 
print("Sparse dataset: ")
algorithm(v_count(sparseproblem), read_graph(sparseproblem), 0) 