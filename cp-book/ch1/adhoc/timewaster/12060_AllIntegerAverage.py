
# Problem name: 12060 All Integer Average
# Problem url: https://uva.onlinejudge.org/external/120/12060.pdf
# Author: Andrey Yemelyanov

import sys
import math

def readline():
    return sys.stdin.readline().strip()

def main():
    case = 1
    while True:
        ints = [int(x) for x in readline().split()][1:]
        if len(ints) == 0:
            break
        print("Case {0}:".format(case))
        print(avg_to_string(*avg(ints)))
        case += 1

def avg_to_string(numerator, denominator):
    denominator_len = len(str(denominator))
    neg_part = ""
    if numerator < 0:
        neg_part = "- "
        numerator = numerator * -1
    if numerator % denominator == 0:
        return neg_part + str((numerator // denominator))
    else:
        whole_part = numerator // denominator
        if whole_part > 0:
            numerator = numerator % denominator
            whole_part = str(whole_part)
        else:
            whole_part = ""
        numerator_len = len(str(numerator))
        return "{0:s}\n{1:s}\n{2:s}".format(
            (" " * (len(neg_part) + len(whole_part) + (denominator_len - numerator_len))) + str(numerator),
            neg_part + whole_part + ("-" * denominator_len),
            (" " * (len(whole_part) + len(neg_part))) + str(denominator)
        )

def avg(ints):
    return reduce_fraction(sum(ints), len(ints))

def gcd(a, b):
    if b == 0:
        return a
    else:
        return gcd(b, a % b)

def reduce_fraction(numerator, denominator):
    greatest_common_divisor = gcd(numerator, denominator)
    return (numerator // greatest_common_divisor, denominator // greatest_common_divisor)

if __name__=="__main__":
    main()
