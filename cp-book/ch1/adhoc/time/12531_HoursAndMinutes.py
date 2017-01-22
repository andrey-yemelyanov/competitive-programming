
# Problem name: 12531 Hours and Minutes
# Problem url: https://uva.onlinejudge.org/external/125/12531.pdf
# Author: Andrey Yemelyanov

import sys
import math

def readline():
    return sys.stdin.readline().strip()

def main():
    for line in sys.stdin:
        deg = int(line.strip())
        angle_possible = False
        for hour in range(12):
            for minute in range(60):
                if min_hand_angle(hour, minute) == deg:
                    angle_possible = True
        if angle_possible:
            print("Y")
        else:
            print("N")

def min_hand_angle(hour, minute):
    n_degrees_per_mark = 6 # 6 degrees per minute
    hour_mark = hour * 5 + minute // 12
    minute_mark = minute
    diff = abs(hour_mark - minute_mark)
    return min(diff, 60 - diff) * n_degrees_per_mark

if __name__=="__main__":
    main()
