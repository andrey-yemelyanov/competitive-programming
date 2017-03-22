# Problem name: 12187 Brothers
# Problem url: https://uva.onlinejudge.org/external/121/12187.pdf
# Author: Andrey Yemelyanov
# NOTE: This solution does not pass Time Limit of 3 sec.

import sys
import math

def readline():
	return sys.stdin.readline().strip()

def main():
	while(True):
		N, R, C, K = [int(x) for x in readline().split()]
		if N == 0 and R == 0 and C == 0 and K == 0:
			break
		kingdom = [0] * R
		for row in range(R):
			kingdom[row] = [int(x) for x in readline().split()]
		new_kingdom = do_war(kingdom, N, K)
		for row in new_kingdom:
			print(" ".join(str(x) for x in row))

def do_battle(kingdom, n_heirs):
	R = len(kingdom)
	C = len(kingdom[0])
	new_kingdom = [row[:] for row in kingdom]
	dr = [-1, 0, 1, 0]
	dc = [0, 1, 0, -1]
	for row in range(R):
		for col in range(C):
			next_heir = (kingdom[row][col] + 1) % n_heirs
			for d in range(4): # check each of the 4 directions
				neighbor_row = row + dr[d]
				neighbor_col = col + dc[d]
				if (neighbor_row >= 0 and neighbor_row < R and neighbor_col >= 0 and neighbor_col < C and kingdom[neighbor_row][neighbor_col] == next_heir):
					new_kingdom[neighbor_row][neighbor_col] = kingdom[row][col]
			#print("row=", row, "col=", col, new_kingdom, "next_heir=",next_heir,"kingdom=",kingdom)
	return new_kingdom

def do_war(kingdom, n_heirs, n_battles):
	for x in range(n_battles):
		kingdom = do_battle(kingdom, n_heirs)
	return kingdom

if __name__=="__main__":
    main()
