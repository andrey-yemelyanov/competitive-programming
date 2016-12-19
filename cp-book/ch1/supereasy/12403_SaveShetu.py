
# Problem name: 12403 Save Setu
# Problem url: https://uva.onlinejudge.org/external/124/12403.pdf
# Author: Andrey Yemelyanov

import sys
import math

def main():
    n_tests = int(sys.stdin.readline())
    balance = 0
    for i in range(n_tests):
        command = sys.stdin.readline().strip().split()
        if len(command) == 2:
            balance = balance + int(command[1])
        else:
            print(balance)

if __name__=="__main__":
    main()
