# Problem name: 11661 Burger Time?
# Problem url: https://uva.onlinejudge.org/external/116/11661.pdf
# Author: Andrey Yemelyanov

import sys
import math

def main():
    while True:
        len = int(sys.stdin.readline().strip()) # consume len
        if len == 0:
            break
        highway = sys.stdin.readline().strip()
        print(min_dist(highway))

def min_dist(highway):
    """ Returns minimum distance between a restaurant (R) and a drugstore (D). """
    if "Z" in highway:
        return 0
    min_dist = 10000000
    current_dist = -1
    prev_stop = ""
    for i in range(len(highway)):
        current_dist = current_dist + 1
        if highway[i] == "D":
            if prev_stop == "R":
                min_dist = min(min_dist, current_dist)
            current_dist = 0
            prev_stop = "D"
        elif highway[i] == "R":
            if prev_stop == "D":
                min_dist = min(min_dist, current_dist)
            current_dist = 0
            prev_stop = "R"
    return min_dist

if __name__=="__main__":
    main()
