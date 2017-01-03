# Problem name: 10098 Generating Fast
# Problem url: https://uva.onlinejudge.org/external/100/10098.pdf
# Author: Andrey Yemelyanov

import sys
import math
import itertools

def main():
    n_tests = int(sys.stdin.readline().strip())
    for i in range(n_tests):
        string = sys.stdin.readline().strip()
        perms = set("".join(p) for p in itertools.permutations(string))
        for p in sorted(perms):
            print(p)
        print()

if __name__=="__main__":
    main()
