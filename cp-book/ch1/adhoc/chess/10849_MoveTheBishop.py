# Problem name: 10849 Move the bishop
# Problem url: https://uva.onlinejudge.org/external/108/10849.pdf
# Author: Andrey Yemelyanov

import sys
import math

WHITE, BLACK = 0, 1
INFINITY = -1

def main():
    n_test_cases = int(sys.stdin.readline().strip())
    for i in range(n_test_cases):
        sys.stdin.readline()
        n_tests = int(sys.stdin.readline().strip())
        N = int(sys.stdin.readline().strip())
        for j in range(n_tests):
            from_row, from_col, to_row, to_col = [int(t) for t in sys.stdin.readline().split()]
            n_moves = count_bishop_moves(from_row, from_col, to_row, to_col)
            if n_moves == INFINITY:
                print("no move")
            else:
                print(n_moves)


def count_bishop_moves(from_row, from_col, to_row, to_col):
    if from_row == to_row and from_col == to_col:
        return 0;
    elif square_color(from_row, from_col) != square_color(to_row, to_col):
        return INFINITY
    elif on_the_same_diagonal(from_row, from_col, to_row, to_col):
        return 1
    else:
        return 2

def on_the_same_diagonal(row1, col1, row2, col2):
    return abs(row1 - row2) == abs(col1 - col2)

def square_color(row, col):
    if row % 2 == 0:
        if col % 2 == 0:
            return WHITE
        else:
            return BLACK
    else:
        if col % 2 == 0:
            return BLACK
        else:
            return WHITE

if __name__=="__main__":
    main()
