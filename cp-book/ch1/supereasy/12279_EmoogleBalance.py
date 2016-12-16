
# Problem name: 12279 Emoogle Balance
# Problem url: https://uva.onlinejudge.org/external/122/12279.pdf
# Author: Andrey Yemelyanov

import sys
import math

def main():
    case = 1
    while True:
        n_events = int(sys.stdin.readline())
        if n_events == 0:
            break
        events = list(map(lambda s : int(s), sys.stdin.readline().strip().split()))
        print("Case {0}: {1}".format(case, len(events) - 2 * events.count(0)))
        case = case + 1

if __name__=="__main__":
    main()
