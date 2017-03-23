# Problem name: 11588 Image Coding
# Problem url: https://uva.onlinejudge.org/external/115/11588.pdf
# Author: Andrey Yemelyanov

import sys
import math

def readline():
	return sys.stdin.readline().strip()

def main():
	n_tests = int(readline())
	case = 1
	for x in range(n_tests):
		R, C, M, N = [int(x) for x in readline().split()]
		grid = [""] * R
		for row in range(R):
			grid[row] = readline()
		print("Case {0}: {1}".format(case, get_image_size(grid, M, N)))
		case += 1

def get_image_size(grid, M, N):
	total_pixels = len(grid) * len(grid[0])
	important_pixels = count_important_pixels(grid)
	return important_pixels * M + (total_pixels - important_pixels) * N

def count_important_pixels(grid):
	m = dict()
	for row in range(len(grid)):
		for col in range(len(grid[0])):
			if(grid[row][col] not in m):
				m[grid[row][col]] = 0
			m[grid[row][col]] += 1
	max_region_size = max(m.values())
	return sum(x for x in m.values() if x == max_region_size)

if __name__=="__main__":
    main()
