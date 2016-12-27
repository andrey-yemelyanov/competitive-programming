# Problem name: 11683 Laser Sculpture
# Problem url: https://uva.onlinejudge.org/external/116/11683.pdf
# Author: Andrey Yemelyanov

import sys
import math

def main():
    while True:
        n_rows, n_cols = [int(x) for x in sys.stdin.readline().split()]
        if n_rows == 0:
            break
        format = [int(x) for x in sys.stdin.readline().split()]
        n_cuts = n_rows - format[0]
        for i in range(1, len(format)):
            if format[i] < format[i - 1]:
                n_cuts += format[i - 1] - format[i]
        print(n_cuts)

if __name__=="__main__":
    main()
