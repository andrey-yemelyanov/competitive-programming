# Problem name: 484 The Department of Redundancy Department
# Problem url: https://uva.onlinejudge.org/external/4/484.pdf
# Author: Andrey Yemelyanov

import sys
import math
from collections import OrderedDict

def readline():
	return sys.stdin.readline().strip()

def main():
	ints = []
	for line in sys.stdin:
		ints.extend([int(x) for x in line.split()])
	freq = OrderedDict()
	for n in ints:
		if n not in freq:
			freq[n] = 0
		freq[n] += 1
	print("\n".join("{} {}".format(k, v) for k, v in freq.items()))

if __name__=="__main__":
    main()
