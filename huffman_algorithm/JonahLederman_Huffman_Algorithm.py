# Name: Jonah Lederman
# Date: 11/16/2025
# Project Description: Write a program that implements Huffman's greedy algorithm. Use your program to encode the string "KALAMAZOO CS", given the frequencies of the letters as follows:
freq = {
    'A': 80, 'C': 30, 'K': 10, 'L': 40,
    'M': 20, 'O': 70, 'S': 60, 'Z': 5, ' ': 90
}
# It works by selecting the lowest two frequency symbols/subtrees and merging them together at every step until all symbols or subtrees
# are merged into a single binary tree.
# This method ensures that the final binary tree minimizes the total number of bits required to represent the symbols in the input.
# Sources: https://favtutor.com/blogs/huffman-coding

import heapq

class Node:
    def __init__(self, freq, char=None, left=None, right=None):
        self.freq = freq      # Number: frequency of this node/subtree
        self.char = char      # Character (if leaf). None if internal node.
        self.left = left      # Left child
        self.right = right    # Right child
   
 # Important: allow heapq to compare nodes by freq
    def __lt__(self, other):
        return self.freq < other.freq
        
# Recursive function to make huffman encoding     
# node: which node of the Huffman tree we're currently visiting
# current_code: the binary string we’ve built as we descend the tree
# codes: a dictionary that stores the final mapping: {character: code}
def generateCodes(node, current_code, codes):
#-----START OF FUNCTION-----
    # If node is a leaf
    if node.left is None and node.right is None:
        codes[node.char] = current_code
        return
    # If node is on the left
    if node.left is not None:
        generateCodes(node.left, current_code + "0", codes)
    # If node is on the right
    if node.right is not None:
        generateCodes(node.right, current_code + "1", codes)
# -----END OF FUNCTION----- 



def huffman_tree():
#-----START OF FUNCTION-----
    nodeheap = [] #heap of nodes

    for letter,frequency in freq.items():
        # Construct heap of nodes
        heapq.heappush(nodeheap,Node(frequency,letter))
       
    # Building the Huffman Tree
    while len(nodeheap) > 1:
        # Pop the two smallest nodes
        node1 = heapq.heappop(nodeheap)
        node2 = heapq.heappop(nodeheap)

        # Create an internal merged node
        combined = Node(node1.freq + node2.freq, left=node1, right=node2)
        heapq.heappush(nodeheap,combined)


    codes = {}
    root = nodeheap[0]
    generateCodes(root, "", codes) 
    return codes
# -----END OF FUNCTION-----   


def encode(basestring):
# -----START OF FUNCTION-----
    codes = huffman_tree()
    total = ""
    for i in range(len(basestring)):
        basechar = basestring[i]
        if basechar in codes:
            total += codes[basechar]
    return total 
# -----END OF FUNCTION-----


l = encode("KALAMAZOO CS")
print("Original string: KALAMAZOO CS")
print(f"Encoded string: {l}")