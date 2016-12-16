
# Problem name: 12289 One-Two-Three
# Problem url: https://uva.onlinejudge.org/external/122/12289.pdf
# Author: Andrey Yemelyanov

import sys
import math

def main():
    n_tests = int(sys.stdin.readline());
    for i in range(n_tests):
        word = sys.stdin.readline().strip()
        if len(word) == 5:
            print(3)
        else:
            if len(set("one") & set(word)) >= 2:
                print(1)
            else:
                print(2)

if __name__=="__main__":
    main()
