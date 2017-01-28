
# Problem name: 12085 Mobile Casanova
# Problem url: https://uva.onlinejudge.org/external/120/12085.pdf
# Author: Andrey Yemelyanov

import sys
import math

def readline():
    return sys.stdin.readline().strip()

def main():
    case = 1
    while True:
        n = int(readline())
        if n == 0:
            break
        numbers = []
        for i in range(n):
            numbers.append(int(readline()))
        print("Case {}:".format(case))
        case += 1
        print("\n".join(compress(numbers)))
        print()

def compress(numbers):
    compressed_numbers = []
    consecutive_numbers = []
    prev_number = numbers[0]
    consecutive_numbers.append(prev_number)
    for i in range(len(numbers)):
        if i != 0:
            if numbers[i] == prev_number + 1:
                consecutive_numbers.append(numbers[i])
            else:
                compressed_numbers.append(compress_consecutive(consecutive_numbers))
                consecutive_numbers = [numbers[i]]
            prev_number = numbers[i]
    else:
        compressed_numbers.append(compress_consecutive(consecutive_numbers))
    return compressed_numbers

def compress_consecutive(consecutive_numbers):
    if len(consecutive_numbers) == 1:
        return "0" + str(consecutive_numbers[0])
    first = str(consecutive_numbers[0])
    last = str(consecutive_numbers[-1])
    diff_index = 0
    for i in range(len(last)):
        if last[i] != first[i]:
            diff_index = i
            break
    return "0" + first + "-" + "".join(last[diff_index:])

if __name__=="__main__":
    main()
