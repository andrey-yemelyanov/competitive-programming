# Problem name: 11309 Counting Chaos
# Problem url: https://uva.onlinejudge.org/external/113/11309.pdf
# Author: Andrey Yemelyanov

import sys
import math
import itertools

def readline():
    return sys.stdin.readline().strip()

def main():
    n_tests = int(readline())
    for i in range(n_tests):
        starting_time = readline()
        clock = clock_iterator(starting_time)
        while True:
            time = next(clock)
            if is_palindrome(list(itertools.dropwhile(lambda x : x == 0, time))): # drop leading zeros
                print("{0}{1}:{2}{3}".format(*time))
                break

def is_palindrome(time):
    for i in range(len(time) // 2):
        if time[i] != time[len(time) - i - 1]:
            return False
    return True

def clock_iterator(starting_time):
    hours, minutes = [int(x) for x in starting_time.split(":")]
    while True:
        minutes += 1
        if minutes == 60:
            hours += 1
            minutes = 0
            if hours == 24:
                hours = 0
        time = []
        time.extend([int(x) for x in "{0:02}".format(hours)])
        time.extend([int(x) for x in "{0:02}".format(minutes)])
        yield time

if __name__=="__main__":
    main()
