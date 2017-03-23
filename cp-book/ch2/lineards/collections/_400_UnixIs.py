# Problem name: 400 Unix ls
# Problem url: https://uva.onlinejudge.org/external/4/400.pdf
# Author: Andrey Yemelyanov

import sys
import math

def readline():
	return sys.stdin.readline().strip()

def main():
	while True:
		line = readline()
		if line == "":
			break
		n_files = int(line)
		files = []
		for i in range(n_files):
			files.append(readline())
		print_files(files)

MAX_ROW_WIDTH = 60
def get_n_cols(L):
	return math.floor((MAX_ROW_WIDTH - L) / (L + 2) + 1)

def get_n_rows(n_files, n_cols):
	return math.ceil(n_files / n_cols)

def print_files(files):
	files.sort()
	print("-" * MAX_ROW_WIDTH)
	L = len(max(files, key=len)) # length of the longest filename
	C = get_n_cols(L)
	R = get_n_rows(len(files), C)
	for row in range(R):
		print_row(row, files, R, C, L)
		print()

def print_row(row, files, R, C, L):
	next_file = row
	for col in range(C):
		if(next_file >= len(files)):
			return

		formatStr = "{0:<" + str((L + 2)) + "s}"
		if col == C - 1:
			formatStr = "{0:<" + str(L) + "s}"
		print(formatStr.format(files[next_file]), end='')

		next_file += get_col_height(col, len(files), R, C)

def get_col_height(col, n_files, R, C):
	if n_files % C == 0 or col < C - 1:
		return R
	return n_files % C

if __name__=="__main__":
    main()
