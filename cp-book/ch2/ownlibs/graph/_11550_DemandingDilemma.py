# Problem name: 11550 Demanding Dilemma
# Problem url: https://uva.onlinejudge.org/external/115/11550.pdf
# Author: Andrey Yemelyanov

import sys
import math

def readline():
	return sys.stdin.readline().strip()

def main():
	n_tests = int(readline())
	for t in range(n_tests):
		V, E = [int(x) for x in readline().split()]
		matrix = [[] for x in range(V)]
		for i in range(V):
			matrix[i] = [int(x) for x in readline().split()]
		if is_simple_graph(matrix):
			print("Yes")
		else:
			print("No")

def is_graph(matrix):
	# all columns must contain exactly two 1s
	for col in range(len(matrix[0])):
		if sum(matrix[row][col] for row in range(len(matrix))) != 2:
			return False
	return True

def is_simple_graph(matrix):
	if not is_graph(matrix):
		return False
	# make sure no pair of columns are equal to each other - ensure there are no multiple edges
	for col1 in range(len(matrix[0])):
		for col2 in range(col1 + 1, len(matrix[0])):
			if columns_equal(col1, col2, matrix): # if two columns are equal, we have multiple edges
				return False
	return True

def columns_equal(col1, col2, matrix):
	col1_list = [matrix[row][col1] for row in range(len(matrix))]
	col2_list = [matrix[row][col2] for row in range(len(matrix))]
	return col1_list == col2_list

if __name__=="__main__":
    main()
