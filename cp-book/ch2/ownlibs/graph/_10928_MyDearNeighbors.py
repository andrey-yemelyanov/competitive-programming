# Problem name: 10928 My Dear Neighbours
# Problem url: https://uva.onlinejudge.org/external/109/10928.pdf
# Author: Andrey Yemelyanov

import sys
import math

def readline():
	return sys.stdin.readline().strip()

def main():
	for i in range(int(readline())):
		P = int(readline())
		graph = [[] for x in range(P)]
		for j in range(P):
			graph[j] = [int(x) for x in readline().split()]
		#print(graph)
		min_degree = min(len(x) for x in graph)
		best_places = []
		for j in range(P):
			if len(graph[j]) == min_degree:
				best_places.append(j)
		print(" ".join(str(x + 1) for x in best_places))
		readline()

if __name__=="__main__":
    main()
