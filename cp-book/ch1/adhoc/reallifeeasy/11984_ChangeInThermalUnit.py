# Problem name: 11984 A Change in Thermal Unit
# Problem url: https://uva.onlinejudge.org/external/119/11984.pdf
# Author: Andrey Yemelyanov

import sys
import math

def readline():
	return sys.stdin.readline().strip()

def main():
	for i in range(int(readline())):
		C, delta_F = [int(x) for x in readline().split()]
		F = (9 / 5) * C + 32
		C_new = (F + delta_F - 32) * 5 / 9
		print("Case {0}: {1:.2f}".format(i + 1, C_new))

if __name__=="__main__":
    main()
