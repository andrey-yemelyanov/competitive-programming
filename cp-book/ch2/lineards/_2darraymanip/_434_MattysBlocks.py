# Problem name: 434 Matty's Blocks
# Problem url: https://uva.onlinejudge.org/external/4/434.pdf
# Author: Andrey Yemelyanov

import sys
import math

def readline():
	return sys.stdin.readline().strip()

def main():
	n_tests = int(readline())
	for x in range(n_tests):
		K = int(readline())
		front = [int(x) for x in readline().split()]
		right = [int(x) for x in readline().split()]
		solution = solve(front, right, K)
		print("Matty needs at least {0} blocks, and can add at most {1} extra blocks.".format(solution[0], solution[1]))

def solve(front, right, K):
	min_blocks = get_min_blocks(front, right, K)
	return (min_blocks, get_max_blocks(front, right, K) - min_blocks)

def get_min_blocks(front, right, K):
	common = []
	front_copy = list(front)
	right_copy = list(right)
	for x in front:
		if x in right_copy:
			front_copy.remove(x)
			right_copy.remove(x)
			common.append(x)
	# print(common)
	return sum(common) + sum(front_copy) + sum(right_copy)

def get_max_blocks(front, right, K):
	m = [[0] * K for x in range(K)] # create KxK matrix
	for row in range(K):
		for col in range(K):
			m[row][col] = min(front[col], right[row])
	return sum(sum(row) for row in m)

if __name__=="__main__":
    main()
