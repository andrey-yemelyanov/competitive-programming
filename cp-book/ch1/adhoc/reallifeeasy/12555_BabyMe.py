# Problem name: 12555 Baby Me
# Problem url: https://uva.onlinejudge.org/external/125/12555.pdf
# Author: Andrey Yemelyanov

import sys
import math
from functools import reduce
import re

def readline():
	return sys.stdin.readline().strip()

def main():
	for i in range(int(readline())):
		w = [int(x) for x in re.split("\D+", readline()) if x.isdigit()]
		print("Case {0}: {1:g}".format(i + 1, reduce(lambda x, y: x + y[0] * y[1], zip(w, [0.5, 0.05]), 0.0)))

if __name__=="__main__":
    main()
