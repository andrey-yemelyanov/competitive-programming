# Problem name: 10813 Traditional BINGO
# Problem url: https://uva.onlinejudge.org/external/108/10813.pdf
# Author: Andrey Yemelyanov

import sys
import math

def main():
    n_tests = int(sys.stdin.readline().strip())
    for i in range(n_tests):
        bingo_card = []
        for j in range(5):
            row = [int(x) for x in sys.stdin.readline().split()]
            if len(row) == 4:
                row.insert(2, 0)
            bingo_card.append(row)
        bingo_numbers = []
        while len(bingo_numbers) != 75:
            row = [int(x) for x in sys.stdin.readline().split()]
            bingo_numbers.extend(row)
        print("BINGO after {0} numbers announced".format(play_bingo(bingo_card, bingo_numbers)))

def play_bingo(bingo_card, bingo_numbers):
    bingo_status = [[0 for col in range(len(bingo_card[0]))] for row in range(len(bingo_card))]
    bingo_status[2][2] = 1 # the center space is free therefore filled in
    for i, bingo_number in enumerate(bingo_numbers):
        pos = find_pos(bingo_card, bingo_number)
        if pos != None:
            row, col = pos
            bingo_status[row][col] = 1
            if bingo(bingo_status):
                return i + 1
    return 0

def bingo(bingo_status):
    for row in range(len(bingo_status)):
        if bingo_in_row(bingo_status, row):
            return True
    for col in range(len(bingo_status[0])):
        if bingo_in_column(bingo_status, col):
            return True
    return bingo_in_diagonal(bingo_status)

def bingo_in_column(bingo_status, col):
    return all(x == 1 for x in [row[col] for row in bingo_status])

def bingo_in_row(bingo_status, row):
    return all(x == 1 for x in bingo_status[row])

def bingo_in_diagonal(bingo_status):
    diagonal1 = [bingo_status[row][col] for row in range(len(bingo_status))
                                        for col in range(len(bingo_status[0]))
                                        if row == col]
    diagonal2 = [bingo_status[row][col] for row in range(len(bingo_status))
                                        for col in range(len(bingo_status[0]))
                                        if row + col == len(bingo_status) - 1]
    return all(x == 1 for x in diagonal1) or all(x == 1 for x in diagonal2)

def find_pos(bingo_card, bingo_number):
    for i in range(len(bingo_card)):
        for j in range(len(bingo_card[0])):
            if bingo_card[i][j] == bingo_number:
                return (i, j)
    return None

if __name__=="__main__":
    main()
