# Problem name: 11044 Searching for Nessy
# Problem url: https://uva.onlinejudge.org/external/110/11044.pdf
# Author: Andrey Yemelyanov

import sys
import math

def main():
    n_tests = int(sys.stdin.readline())
    for i in range(n_tests):
        dim = list(int(token) for token in sys.stdin.readline().split())
        n, m = dim[0], dim[1]
        n_horizontal = math.ceil((m - 2) / 3)
        n_vertical = math.ceil((n - 2) / 3)
        print(n_horizontal * n_vertical)

if __name__=="__main__":
    main()
