# Problem name: 10978 Let's Play Magic!
# Problem url: https://uva.onlinejudge.org/external/109/10978.pdf
# Author: Andrey Yemelyanov

import sys
import math

def readline():
	return sys.stdin.readline().strip()

def main():
	while True:
		n_cards = int(readline())
		if n_cards == 0:
			break
		ordering = []
		for i in range(n_cards):
			line = readline().split()
			ordering.append((line[0], len(line[1])))
		print(" ".join(arrange_cards(ordering)))

EMPTY = ""

def arrange_cards(ordering):
	arrangement = [EMPTY] * len(ordering)
	pos = -1
	for card, n_steps in ordering:
		next_pos = empty_slot(arrangement, pos, n_steps)
		arrangement[next_pos] = card
		pos = next_pos
	return arrangement

def empty_slot(arrangement, start_pos, n_steps):
	pos = start_pos
	dist = 0
	while dist < n_steps:
		pos = (pos + 1) % len(arrangement)
		if arrangement[pos] == EMPTY:
			dist += 1
	return pos

if __name__=="__main__":
    main()
