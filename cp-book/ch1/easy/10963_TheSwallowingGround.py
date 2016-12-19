
# Problem name: 10963 The Swallowing Ground
# Problem url: https://uva.onlinejudge.org/external/109/10963.pdf
# Author: Andrey Yemelyanov

import sys
import math

def main():
    n_tests = int(sys.stdin.readline())
    for i in range(n_tests):
        sys.stdin.readline() # read empty line
        W = int(sys.stdin.readline())
        s = set([])
        for j in range(W):
            north_row, south_row = [int(x) for x in sys.stdin.readline().strip().split()]
            s.add(math.fabs(north_row - south_row))
        if len(s) == 1:
            print("yes")
        else:
            print("no")
        if i < n_tests - 1:
            print()

if __name__=="__main__":
    main()
