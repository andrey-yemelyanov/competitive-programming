# Problem name: 12150 Pole Position
# Problem url: https://uva.onlinejudge.org/external/121/12150.pdf
# Author: Andrey Yemelyanov

import sys
import math

def readline():
	return sys.stdin.readline().strip()

def main():
	while True:
		n_cars = int(readline())
		if n_cars == 0:
			break
		grid = []
		for i in range(n_cars):
			line = readline().split()
			grid.append((int(line[0]), int(line[1])));
		starting_grid = get_starting_grid(grid)
		if starting_grid != None:
			print(" ".join(str(x) for x in starting_grid))
		else:
			print(-1)

EMPTY = 0
def get_starting_grid(grid):
	starting_grid = [EMPTY] * len(grid)
	for current_pos in range(len(grid)):
		car = grid[current_pos][0]
		pos_changed = grid[current_pos][1]
		starting_pos = current_pos + pos_changed
		if starting_pos >= 0 and starting_pos < len(grid) and starting_grid[starting_pos] == EMPTY:
			starting_grid[starting_pos] = car
		else:
			return None
	return starting_grid

if __name__=="__main__":
    main()
