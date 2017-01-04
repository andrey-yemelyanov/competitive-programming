# Problem name: 12195 Jingle Composing
# Problem url: https://uva.onlinejudge.org/external/121/12195.pdf
# Author: Andrey Yemelyanov

import sys
import math
import functools

def readline():
	return sys.stdin.readline().strip()

def main():
	while True:
		line = readline()
		if line == "*":
			break
		composition = [measure for measure in line.split("/") if len(measure) > 0]
		print(count_measures_with_right_duration(composition, 1))

def count_measures_with_right_duration(composition, right_duration):
	return functools.reduce(lambda count, measure: count + 1 if measure_duration(measure) == right_duration else count, composition, 0)

notes = {'W':1, 'H':0.5, 'Q':0.25, 'E':0.125, 'S':0.0625, 'T':0.03125, 'X':0.015625}
def measure_duration(measure):
	return sum([notes[id] for id in measure])

if __name__=="__main__":
    main()
